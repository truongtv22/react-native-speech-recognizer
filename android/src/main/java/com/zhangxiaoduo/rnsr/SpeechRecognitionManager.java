package com.zhangxiaoduo.rnsr;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by zhangxiaoduo on 2017/11/6.
 */

public class SpeechRecognitionManager extends ReactContextBaseJavaModule implements EventListener {

    private EventManager asr;

    final private static String RECOGNIZE_SUCC = "RECOGNIZE_SUCC";

    public SpeechRecognitionManager(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "SpeechRecognition";
    }

    @ReactMethod
    public void startRecognition(String language) {
        int lang = 1536;//default Chinese
        if (language != null && "eng".equals(language)) {
            lang = 1736;//English
        }

        try {
            asr = EventManagerFactory.create(this.getCurrentActivity(), "asr");
            asr.registerListener(this);

            Map<String, Object> params = new LinkedHashMap<String, Object>();
            String event = null;
            event = SpeechConstant.ASR_START;

            params.put(SpeechConstant.PID, lang);//1536(Chinese),1736(English),default:1536
            params.put(SpeechConstant.ACCEPT_AUDIO_VOLUME, false);
            params.put(SpeechConstant.VAD, SpeechConstant.VAD_DNN);
            params.put(SpeechConstant.PROP, 10008);//数字偏好

            String json = null;
            json = new JSONObject(params).toString();
            asr.send(event, json, null, 0, 0);
        } catch (Exception e) {
            getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit(RECOGNIZE_SUCC, "start failed");
            e.printStackTrace();
        }
    }

    @Override
    public void onEvent(String name, String params, byte[] data, int offset, int length) {
        getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(RECOGNIZE_SUCC, params);
    }

    @ReactMethod
    public void finishRecognition() {
        asr.send(SpeechConstant.ASR_STOP, null, null, 0, 0);
    }
}
