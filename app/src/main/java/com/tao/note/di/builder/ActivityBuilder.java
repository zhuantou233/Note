package com.tao.note.di.builder;

import com.tao.note.ui.login.LoginActivity;
import com.tao.note.ui.login.signin.SignInFragmentProvider;
import com.tao.note.ui.login.signup.SignUpFragmentProvider;
import com.tao.note.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Tao Zhou on 2019/4/18
 * Package name: com.tao.note.di.builder
 */
@Module
public abstract class ActivityBuilder {

    //    @ContributesAndroidInjector(modules = {
//            FeedActivityModule.class,
//            BlogFragmentProvider.class,
//            OpenSourceFragmentProvider.class})
//    abstract FeedActivity bindFeedActivity();
//
    @ContributesAndroidInjector(modules = {
            SignInFragmentProvider.class,
            SignUpFragmentProvider.class
    })
    abstract LoginActivity bindLoginActivity();

    //
//    @ContributesAndroidInjector(modules = {
//            AboutFragmentProvider.class,
//            RateUsDialogProvider.class})
//    abstract MainActivity bindMainActivity();
//
    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();
}
