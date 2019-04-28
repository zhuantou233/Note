package com.tao.note.ui.profile;

import com.tao.note.data.DataManager;
import com.tao.note.ui.base.BaseViewModel;
import com.tao.note.utils.rx.SchedulerProvider;

/**
 * Created by Tao Zhou on 2019/4/28
 * Package name: com.tao.note.ui.profile
 */
public class ProfileViewModel extends BaseViewModel<ProfileNavigator> {
    public ProfileViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onAccountDetailClick() {
        getNavigator().openAccountDetailActivity();
    }

}
