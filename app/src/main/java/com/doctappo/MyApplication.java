package com.doctappo;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

public class MyApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        MultiDex.install(this);
        super.onCreate();
    }
}
