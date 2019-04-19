package com.tao.note;

import android.app.Activity;
import android.app.Application;

import com.tao.note.utils.Constants;

import javax.inject.Inject;

import cn.bmob.v3.Bmob;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.github.inflationx.calligraphy3.CalligraphyConfig;

public class BaseApplication extends Application implements HasActivityInjector {

//    private static BaseApplication INSTANCE = null;
//    private static Context context;
//
//    public static BaseApplication getInstance() {
//        return INSTANCE;
//    }
//
//    public static Context getContext() {
//        return context;
//    }

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        INSTANCE = this;
//        context = getApplicationContext();
//        DaggerAppComponent.builder()
//                .application(this)
//                .build()
//                .inject(this);
        Bmob.initialize(this, Constants.BMOB_APPID);
    }
}
