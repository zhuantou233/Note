package com.tao.note.ui.main.createrecord;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Tao Zhou on 2019/5/6
 * Package name: com.tao.note.ui.main.createrecord
 */

@Module
public abstract class CreateRecordFragmentProvider {

    @ContributesAndroidInjector
    abstract CreateRecordFragment provideCreateRecordFragmentFactory();
}
