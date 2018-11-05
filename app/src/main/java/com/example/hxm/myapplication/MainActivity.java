package com.example.hxm.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends Activity {


    private IUserService mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void load(View v) {
        try { mService = PluginUtil.load(this);
            if (mService != null) {
                Toast.makeText(this, "加载插件APP成功", Toast.LENGTH_SHORT).show(); return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "加载插件APP失败", Toast.LENGTH_SHORT).show();
    }

    public void login(View v) {
        if (mService != null) {
            Toast.makeText(this, mService.login("August1996", "August1996"), Toast.LENGTH_SHORT).show();
        }
    }

    public void logout(View v) {
        if (mService != null) {
            Toast.makeText(this, mService.logout(), Toast.LENGTH_SHORT).show();
        }
    }

}
