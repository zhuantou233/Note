package com.tao.note.ui.main.week;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Tao Zhou on 2019/5/5
 * Package name: com.tao.note.ui.main.week
 */

@Module
public abstract class RecordWeekFragmentProvider {

    @ContributesAndroidInjector
    abstract RecordWeekFragment provideRecordWeekFragmentFactory();
}