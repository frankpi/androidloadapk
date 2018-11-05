package com.example.hxm.myapplication;

import android.os.Bundle;

public interface Pluginable {
    void onCreate(Bundle var1);

    void onRestart();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();
}

