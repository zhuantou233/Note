package com.tao.note.ui.login.bottomdialog;

import com.tao.note.data.DataManager;
import com.tao.note.ui.base.BaseViewModel;
import com.tao.note.utils.rx.SchedulerProvider;

/**
 * Created by Tao Zhou on 2019/4/24
 * Package name: com.tao.note.ui.login.bottomdialog
 */
public class ResetViewModel extends BaseViewModel<ResetCallback> {
    public ResetViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onResetPasswordClick() {
        getNavigator().showResetPasswordFragment();
        getNavigator().dismissDialog();
    }

    public void onSignInWithCodeClick() {
        getNavigator().showSignInWithCodeFragment();
        getNavigator().dismissDialog();
    }

    public void onCancelClick() {
        getNavigator().dismissDialog();
    }
}
