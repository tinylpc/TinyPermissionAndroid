# TinyPermissionAndroid
用于RN中的Android权限申请，RN官方的询问方式情况下点击允许或拒绝，无法收到结果，用此项目可以，另外可以多个权限一起申请

# 使用方式
1、在app的build.gradle文件中加入
```
compile 'com.tinylpc:tinypermissionandroid:1.0.0'
compile "com.facebook.react:react-native:+"
compile 'com.tbruyelle.rxpermissions:rxpermissions:0.9.0@aar'
compile 'io.reactivex:rxandroid:1.2.1'
compile 'io.reactivex:rxjava:1.1.6'
compile 'com.artemzin.rxjava:proguard-rules:1.1.6.0'
```

2、注册包到RN,我的是在MainActivity中添加
```
mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName("index.android.bundle")
                .setJSMainModuleName("index.android")
                .addPackage(new MainReactPackage())
                .addPackage(new MyToastPackage())
                .addPackage(new MyTellProgressPackage())
                .addPackage(new PermissionAndroidPackage()) //<<--------这一行
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
有的可能是在MianApplication中添加，大家自己根据实际情况处理下
```

3、新建js文件，文件名TinyPermissionAndroid（文件名大家自己定义），内容如下
```
'use strict'
import { NativeModules } from 'react-native';

export default NativeModules.TinyPermissionAndroid;
```

4、导入
```
import TinyPermissionAndroid from './TinyPermissionAndroid';
```
5、使用
```
async _requestSDcardPermission() {
        try {
            var permissions = [];
            permissions[0] = TinyPermissionAndroid.WRITE_EXTERNAL_STORAGE;
            var {
                isGranted
            } =await TinyPermissionAndroid.requestPermission(permissions);
            alert(isGranted)
        } catch (err) {
            alert(err)
        }
    }
若申请多个权限，只有所有权限都获取到,isGranted的值才会为true,另外，大家申请的权限需要在AndroidMainfest.xml中配置    
```
