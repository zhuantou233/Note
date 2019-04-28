package com.tao.note.ui.accountdetail;

import com.tao.note.data.DataManager;
import com.tao.note.ui.base.BaseViewModel;
import com.tao.note.utils.rx.SchedulerProvider;

/**
 * Created by Tao Zhou on 2019/4/28
 * Package name: com.tao.note.ui.accountdetail
 */
public class AccountDetailViewModel extends BaseViewModel<AccountDetailNavigator> {
    public AccountDetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onAvatarClick() {

    }

    public void onDeleteAccountClick() {

    }

    public void onNameClick() {
    }

    public void onPhoneClick() {
    }

    public void onChangePasswordClick() {
    }
}
