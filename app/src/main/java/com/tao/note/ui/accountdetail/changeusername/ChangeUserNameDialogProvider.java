package com.tao.note.ui.accountdetail.changeusername;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Tao Zhou on 2019/4/29
 * Package name: com.tao.note.ui.accountdetail.changeusername
 */
@Module
public abstract class ChangeUserNameDialogProvider {

    @ContributesAndroidInjector
    abstract ChangeUserNameDialog provideChangeUserNameDialogFactory();
}