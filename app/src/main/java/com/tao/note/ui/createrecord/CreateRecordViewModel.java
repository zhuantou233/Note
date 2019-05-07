package com.tao.note.ui.createrecord;

import com.tao.note.data.DataManager;
import com.tao.note.ui.base.BaseViewModel;
import com.tao.note.utils.rx.SchedulerProvider;

/**
 * Created by Tao Zhou on 2019/5/6
 * Package name: com.tao.note.ui.createrecord
 */
public class CreateRecordViewModel extends BaseViewModel<CreateRecordNavigator> {
    public CreateRecordViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
