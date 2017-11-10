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
