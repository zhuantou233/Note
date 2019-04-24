package com.tao.note.ui.login.resetpassword;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Tao Zhou on 2019-04-24.
 */
@Module
public abstract class ResetPasswordFragmentProvider {
    @ContributesAndroidInjector
    abstract ResetPasswordFragment provideResetPasswordFragmentFactory();
}
