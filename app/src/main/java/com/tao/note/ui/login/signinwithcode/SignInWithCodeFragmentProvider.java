package com.tao.note.ui.login.signinwithcode;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Tao Zhou on 2019/4/24
 * Package name: com.tao.note.ui.login.signinwithcode
 */

@Module
public abstract class SignInWithCodeFragmentProvider {

    @ContributesAndroidInjector
    abstract SignInWithCodeFragment provideSignInWithCodeFragmentFactory();
}