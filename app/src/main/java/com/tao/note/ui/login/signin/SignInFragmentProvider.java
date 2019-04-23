package com.tao.note.ui.login.signin;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Tao Zhou on 2019/4/23
 * Package name: com.tao.note.ui.login.signin
 */

@Module
public abstract class SignInFragmentProvider {

    @ContributesAndroidInjector
    abstract SignInFragment provideSignInFragmentFactory();
}