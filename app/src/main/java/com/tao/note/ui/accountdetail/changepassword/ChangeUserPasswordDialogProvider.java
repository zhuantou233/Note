package com.tao.note.ui.accountdetail.changepassword;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Tao Zhou on 2019/5/5
 * Package name: com.tao.note.ui.accountdetail.changepassword
 */
@Module
public abstract class ChangeUserPasswordDialogProvider {

    @ContributesAndroidInjector
    abstract ChangeUserPasswordDialog provideChangeUserPasswordDialogFactory();
}