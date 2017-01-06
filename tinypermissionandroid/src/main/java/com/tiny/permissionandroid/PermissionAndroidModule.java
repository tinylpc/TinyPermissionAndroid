package com.tiny.permissionandroid;

import android.Manifest;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by tiny on 17/1/6.
 */

public class PermissionAndroidModule extends ReactContextBaseJavaModule {

    private static final String NAME = "TinyPermissionAndroid";
    private static final String WRITE_EXTERNAL_STORAGE = "WRITE_EXTERNAL_STORAGE";
    private static final String READ_EXTERNAL_STORAGE = "READ_EXTERNAL_STORAGE";
    private static final String RECEIVE_MMS = "RECEIVE_MMS";
    private static final String RECEIVE_WAP_PUSH = "RECEIVE_WAP_PUSH";
    private static final String READ_SMS = "READ_SMS";
    private static final String RECEIVE_SMS = "RECEIVE_SMS";
    private static final String SEND_SMS = "SEND_SMS";
    private static final String BODY_SENSORS = "BODY_SENSORS";
    private static final String PROCESS_OUTGOING_CALLS = "PROCESS_OUTGOING_CALLS";
    private static final String USE_SIP = "USE_SIP";
    private static final String ADD_VOICEMAIL = "ADD_VOICEMAIL";
    private static final String WRITE_CALL_LOG = "WRITE_CALL_LOG";
    private static final String READ_CALL_LOG = "READ_CALL_LOG";
    private static final String CALL_PHONE = "CALL_PHONE";
    private static final String READ_PHONE_STATE = "READ_PHONE_STATE";
    private static final String RECORD_AUDIO = "RECORD_AUDIO";
    private static final String ACCESS_COARSE_LOCATION = "ACCESS_COARSE_LOCATION";
    private static final String ACCESS_FINE_LOCATION = "ACCESS_FINE_LOCATION";
    private static final String GET_ACCOUNTS = "GET_ACCOUNTS";
    private static final String WRITE_CONTACTS = "WRITE_CONTACTS";
    private static final String READ_CONTACTS = "READ_CONTACTS";
    private static final String CAMERA = "CAMERA";
    private static final String WRITE_CALENDAR = "WRITE_CALENDAR";
    private static final String READ_CALENDAR = "READ_CALENDAR";


    public PermissionAndroidModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(WRITE_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        constants.put(READ_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
        constants.put(RECEIVE_MMS, Manifest.permission.RECEIVE_MMS);
        constants.put(RECEIVE_WAP_PUSH, Manifest.permission.RECEIVE_WAP_PUSH);
        constants.put(READ_SMS, Manifest.permission.READ_SMS);
        constants.put(RECEIVE_SMS, Manifest.permission.RECEIVE_SMS);
        constants.put(SEND_SMS, Manifest.permission.SEND_SMS);
        constants.put(BODY_SENSORS, Manifest.permission.BODY_SENSORS);
        constants.put(PROCESS_OUTGOING_CALLS, Manifest.permission.PROCESS_OUTGOING_CALLS);
        constants.put(USE_SIP, Manifest.permission.USE_SIP);
        constants.put(ADD_VOICEMAIL, Manifest.permission.ADD_VOICEMAIL);
        constants.put(WRITE_CALL_LOG, Manifest.permission.WRITE_CALL_LOG);
        constants.put(READ_CALL_LOG, Manifest.permission.READ_CALL_LOG);
        constants.put(CALL_PHONE, Manifest.permission.CALL_PHONE);
        constants.put(READ_PHONE_STATE, Manifest.permission.READ_PHONE_STATE);
        constants.put(RECORD_AUDIO, Manifest.permission.RECORD_AUDIO);
        constants.put(ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
        constants.put(ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION);
        constants.put(GET_ACCOUNTS, Manifest.permission.GET_ACCOUNTS);
        constants.put(WRITE_CONTACTS, Manifest.permission.WRITE_CONTACTS);
        constants.put(READ_CONTACTS, Manifest.permission.READ_CONTACTS);
        constants.put(CAMERA, Manifest.permission.CAMERA);
        constants.put(WRITE_CALENDAR, Manifest.permission.WRITE_CALENDAR);
        constants.put(READ_CALENDAR, Manifest.permission.READ_CALENDAR);
        return constants;
    }

    @ReactMethod
    public void requestPermission(ReadableArray array, final Promise promise) {
        int size = array.size();
        final String[] permissions = new String[size];
        for (int i = 0; i < size; i++) {
            permissions[i] = array.getString(i);
        }
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new RxPermissions(getCurrentActivity()).request(permissions).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        WritableMap map = Arguments.createMap();
                        map.putBoolean("isGranted", aBoolean);
                        promise.resolve(map);
                    }
                });
            }
        });
    }
}
