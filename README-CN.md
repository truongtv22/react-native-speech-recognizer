<a href="https://github.com/Mrzhangxiaoduo/react-native-speech-recognizer/blob/master/README.md">英文文档</a>
# react-native-speech-recognizer
一个基于ReactNative的语音识别库（支持中文和英文）中文基于<a href="http://yuyin.baidu.com/docs/asr/186">百度API</a>.
# 安装
```
npm install react-native-speech-recognizer --save
```
# 链接原生库
### 自动链接  
```
react-native link react-native-speech-recognizer
```
### 手动链接
在你的工程中更改以下文件：
#### Android

1. 添加以下代码在 `android/settings.gradle`中
```
...
include ':react-native-speech-recognizer'
project(':react-native-speech-recognizer').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-speech-recognizer/android')
...
```
2. 添加编译依赖在 `android/app/build.gradle`中
```
...
dependencies {
    ...
    compile project(':react-native-speech-recognizer')
    ...
}
```
3. 添加导入在 `android/app/src/.../MainApplication.java`中
```
import port com.zhangxiaoduo.rnsr.SpeechRecognitionPackage;  // <--- 添加导入

public class MainApplication extends Application implements ReactApplication {
    // ...
    @Override
    protected List<ReactPackage> getPackages() {
        return Arrays.<ReactPackage>asList(
            new MainReactPackage(),
            // ...
            new SpeechRecognitionPackage()                      // <--- 添加包
            );
    }
```
#### IOS
1. 在`XCode`中, 右键单击工程的`Libraries` 文件夹 ➜ 点击`Add Files to <...>`
2. 去`node_modules` ➜ `react-native-speech-recognizer` ➜ `ios` ➜ 选择 `SpeechRecognition.xcodeproj`
3. 新增`SpeechRecognition.a` 在 `Build Phases -> Link Binary With Libraries`中
# Permission
### Android
1. 添加权限 `android/app/src/main/AndroidManifest.xml`
```
<uses-permission android:name="android.permission.RECORD_AUDIO"/>
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
```
2. 从Android M(6.0)之后, 需要在应用运行时动态授权. 增加以下代码在`android/app/src/.../MainActivity.java`中
```
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    checkAndRequestPermissions();
}
private void checkAndRequestPermissions() {
      int sdkVersion = Build.VERSION.SDK_INT;
      if (sdkVersion >= Build.VERSION_CODES.M) {
          if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                  != PackageManager.PERMISSION_GRANTED
                  || ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                  != PackageManager.PERMISSION_GRANTED
                  || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE)
                  != PackageManager.PERMISSION_GRANTED
                  || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                  != PackageManager.PERMISSION_GRANTED) {
              ActivityCompat.requestPermissions(this,
                      new String[]{Manifest.permission.RECORD_AUDIO,
                              Manifest.permission.INTERNET,
                              Manifest.permission.ACCESS_NETWORK_STATE,
                              Manifest.permission.READ_PHONE_STATE
                      },
                      1);
          }
      }
  }
```
3. 增加你的 `APP_ID`,`API_KEY`,`SECRET_KEY` 在 `android/app/src/main/AndroidManifest.xml` 中
点击 <a href="http://yuyin.baidu.com/">这里</a> 去获取你的 `APP_ID`,`API_KEY`,`SECRET_KEY`
```
<application
    ...
    <meta-data
        android:name="com.baidu.speech.APP_ID"
        android:value="10337431" />
    <meta-data
        android:name="com.baidu.speech.API_KEY"
        android:value="Ptivienw5Qx75uRnKalq7Eq2" />
   <meta-data
       android:name="com.baidu.speech.SECRET_KEY"
       android:value="bd5c689721bfa3530330c677e87303a6" />
    ...
</application>
```
### IOS
新增权限在 `Info.plist`中
```
<dict>
  ...
  <key>NSSpeechRecognitionUsageDescription</key>
  <string>你的描述</string>
  <key>NSMicrophoneUsageDescription</key>
  <string>你的描述</string>
  ...
</dict>
```
# 用法
```
import SpeechRecognizer from 'react-native-speech-recognizer';

...

componentWillMount() {
    this.state = {
        result: '';
    };
    SpeechRecognizer.init(result=>this.setState({result}));
}

...

componentWillUnmount() {
    SpeechRecognizer.end();
}

...

render() {
//SpeechRecognizer.start('zh');'zh'（中文）或者'eng'（英文）,默认:'zh'
...
    <TouchableOpacity 
        onPressIn={()=> SpeechRecognizer.start('zh')} 
        onPressOut={()=> SpeechRecognizer.finish()}>
        ...
    </TouchableOpacity>
    <Text>{this.state.result}</Text>
...
}
```
