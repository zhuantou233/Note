package com.tao.note.di.component;

import android.app.Application;

import com.tao.note.NoteApp;
import com.tao.note.di.builder.ActivityBuilder;
import com.tao.note.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by Tao Zhou on 2019/4/18
 * Package name: com.tao.note.di.component
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    void inject(NoteApp app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
