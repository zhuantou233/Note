package com.tao.note.ui.main.all;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Tao Zhou on 2019/5/5
 * Package name: com.tao.note.ui.main.all
 */

@Module
public abstract class RecordAllFragmentProvider {

    @ContributesAndroidInjector
    abstract RecordAllFragment provideRecordAllFragmentFactory();
}