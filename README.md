# react-native-speech-recognizer
A speech-recongintion library for React Native.
# install
```
npm install react-native-speech-recognizer --save
```
# Linking
### Automatic  
  `react-native link react-native-speech-recognizer`
### Manual  
  Make alterations to the following files in your project:
#### Android

1. Add following lines to `android/settings.gradle`
```
...
include ':react-native-speech-recognizer'
project(':react-native-speech-recognizer').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-speech-recognizer/android')
...
```
2. Add the compile line to dependencies in `android/app/build.gradle`
```
...
dependencies {
    ...
    compile project(':react-native-speech-recognizer')
    ...
}
```
3. Add import and link the package in `android/app/src/.../MainApplication.java`
```
import port com.zhangxiaoduo.rnsr.SpeechRecognitionPackage;  // <--- add import

public class MainApplication extends Application implements ReactApplication {
    // ...
    @Override
    protected List<ReactPackage> getPackages() {
        return Arrays.<ReactPackage>asList(
            new MainReactPackage(),
            // ...
            new SpeechRecognitionPackage()                      // <--- add package
        );
    }
```
4. Add permission in `android/app/src/main/AndroidManifest.xml`
```
<uses-permission android:name="android.permission.RECORD_AUDIO"/>
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
```
5. Add your `APP_ID`,`API_KEY`,`SECRET_KEY` in `android/app/src/main/AndroidManifest.xml`  
Click <a href="http://yuyin.baidu.com/">here</a> to get your own `APP_ID`,`API_KEY`,`SECRET_KEY`
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
#### IOS
1. In the XCode's "Project navigator", right click on your project's Libraries folder ➜ `Add Files to <...>`
2. Go to `node_modules` ➜ `react-native-speech-recognizer` ➜ `ios` ➜ select `SpeechRecognition.xcodeproj`
3. Add `SpeechRecognition.a` to `Build Phases -> Link Binary With Libraries`
4. Add permission to your `Info.plist`
```
<dict>
  ...
  <key>NSSpeechRecognitionUsageDescription</key>
  <string>Your usage description here</string>
  <key>NSMicrophoneUsageDescription</key>
  <string>Your usage description here</string>
  ...
</dict>
```
# Usage
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
...
    <TouchableOpacity 
        onPressIn={()=> SpeechRecognizer.start()} 
        onPressOut={()=> SpeechRecognizer.finish()}>
        ...
    </TouchableOpacity>
    <Text>{this.state.result}</Text>
...
}
```