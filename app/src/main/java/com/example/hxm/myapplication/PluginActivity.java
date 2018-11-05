package com.example.hxm.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

/**
 * 所有插件Activity都必须继承的基类
 */
@SuppressLint("Registered")
public class PluginActivity extends Activity implements Pluginable, Attachable<Activity> {
    private Activity mProxyActivity;

    @Override
    public Window getWindow() {
        return mProxyActivity.getWindow();
    }

    @Override
    public View findViewById(int id) {
        return mProxyActivity.findViewById(id);
    }

    /**
     * 使用代理Activity去设置加载到的资源, 因为代理Activity本身就是优先使用插件APK的ClassLoader和Resource, 所以该方法会加载到插件APK的布局 * * @param layoutResID
     */
    @Override
    public void setContentView(int layoutResID) {
        mProxyActivity.setContentView(layoutResID);
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onRestart() {
        super.onRestart();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void attach(Activity data) {
        this.mProxyActivity = data;
    }
}

