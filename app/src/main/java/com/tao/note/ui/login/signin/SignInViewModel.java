package com.tao.note.ui.login.signin;

import com.tao.note.data.DataManager;
import com.tao.note.ui.base.BaseViewModel;
import com.tao.note.utils.rx.SchedulerProvider;

/**
 * Created by Tao Zhou on 2019/4/22
 * Package name: com.tao.note.ui.login.signin
 */
public class SignInViewModel extends BaseViewModel<SignInNavigator> {
    public SignInViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onServerLoginClick() {
        getNavigator().login();
    }

    public void onSignUpClick() {
        getNavigator().showSignUpFragment();
    }

    public void onResetPasswordClick() {
        getNavigator().showBottomSheetDialog();
    }
}
