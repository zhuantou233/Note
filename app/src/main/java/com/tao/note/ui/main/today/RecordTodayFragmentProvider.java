package com.tao.note.ui.main.today;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Tao Zhou on 2019/5/5
 * Package name: com.tao.note.ui.main.today
 */

@Module
public abstract class RecordTodayFragmentProvider {

    @ContributesAndroidInjector
    abstract RecordTodayFragment provideRecordTodayFragmentFactory();
}