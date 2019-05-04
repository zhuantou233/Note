package com.tao.note;

import android.app.Activity;
import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tao.note.di.component.DaggerAppComponent;
import com.tao.note.utils.Util;

import javax.inject.Inject;

import cn.bmob.v3.Bmob;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class NoteApp extends Application implements HasActivityInjector {

//    private static NoteApp INSTANCE = null;
//    private static Context context;
//
//    public static NoteApp getInstance() {
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
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
        Bmob.initialize(this, BuildConfig.API_KEY);

        Fresco.initialize(this);

        AndroidNetworking.initialize(getApplicationContext());

        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }

        Logger.addLogAdapter(new AndroidLogAdapter());

        Util.syncIsDebug(getApplicationContext());

        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
    }
}
