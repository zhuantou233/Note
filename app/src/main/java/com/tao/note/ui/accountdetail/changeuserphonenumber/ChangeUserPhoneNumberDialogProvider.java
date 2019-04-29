package com.tao.note.ui.accountdetail.changeuserphonenumber;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Tao Zhou on 2019/4/29
 * Package name: com.tao.note.ui.accountdetail.changeuserphonenumber
 */
@Module
public abstract class ChangeUserPhoneNumberDialogProvider {

    @ContributesAndroidInjector
    abstract ChangeUserPhoneNumberDialog provideChangeUserPhoneNumberDialogFactory();
}