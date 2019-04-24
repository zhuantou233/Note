package com.tao.note.ui.login.bottomdialog;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Tao Zhou on 2019/4/24
 * Package name: com.tao.note.ui.login.bottomdialog
 */
@Module
public abstract class ResetDialogProvider {
    @ContributesAndroidInjector
    abstract ResetDialog provideResetDialogFactory();
}
