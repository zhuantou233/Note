package com.tao.note.ui.main.all;

import com.tao.note.data.DataManager;
import com.tao.note.ui.base.BaseViewModel;
import com.tao.note.utils.rx.SchedulerProvider;

/**
 * Created by Tao Zhou on 2019/5/5
 * Package name: com.tao.note.ui.main.all
 */
public class RecordAllViewModel extends BaseViewModel<RecordAllNavigator> {
    public RecordAllViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
