package com.tao.note.ui.login.signup;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Tao Zhou on 2019/4/23
 * Package name: com.tao.note.ui.login.signup
 */

@Module
public abstract class SignUpFragmentProvider {

    @ContributesAndroidInjector
    abstract SignUpFragment provideSignUpFragmentFactory();
}