package com.tao.note.ui.main.today;

import com.tao.note.data.DataManager;
import com.tao.note.ui.base.BaseViewModel;
import com.tao.note.utils.rx.SchedulerProvider;

/**
 * Created by Tao Zhou on 2019/5/5
 * Package name: com.tao.note.ui.main.today
 */
public class RecordTodayViewModel extends BaseViewModel<RecordTodayNavigator> {
    public RecordTodayViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
