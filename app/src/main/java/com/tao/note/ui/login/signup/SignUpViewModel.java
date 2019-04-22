package com.tao.note.ui.login.signup;

import com.tao.note.data.DataManager;
import com.tao.note.ui.base.BaseViewModel;
import com.tao.note.utils.rx.SchedulerProvider;

/**
 * Created by Tao Zhou on 2019/4/22
 * Package name: com.tao.note.ui.login.signup
 */
public class SignUpViewModel extends BaseViewModel<SignUpNavigator> {
    public SignUpViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    public void onNavBackClick() {
        getNavigator().goBack();
    }
}
