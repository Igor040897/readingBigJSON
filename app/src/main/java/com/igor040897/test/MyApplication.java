package com.igor040897.test;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by fanre on 9/16/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}