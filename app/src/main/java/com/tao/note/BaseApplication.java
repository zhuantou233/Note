package com.tao.note;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.tao.note.utils.Constants;

import cn.bmob.v3.Bmob;

public class BaseApplication extends Application {

    private static BaseApplication INSTANCE = null;
    private static Context context;

    public static BaseApplication getInstance() {
        return INSTANCE;
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        context = getApplicationContext();
        Bmob.initialize(this, Constants.BMOB_APPID);
    }
}
