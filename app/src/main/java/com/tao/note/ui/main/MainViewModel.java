package com.tao.note.ui.main;

import com.tao.note.data.DataManager;
import com.tao.note.ui.base.BaseViewModel;
import com.tao.note.utils.rx.SchedulerProvider;

/**
 * Created by Tao Zhou on 2019/4/22
 * Package name: com.tao.note.ui.main
 */
public class MainViewModel extends BaseViewModel<MainNavigator> {
    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

}
