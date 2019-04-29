package com.tao.note.ui.profile;

import android.text.TextUtils;

import androidx.databinding.ObservableField;

import com.tao.note.data.DataManager;
import com.tao.note.ui.base.BaseViewModel;
import com.tao.note.utils.rx.SchedulerProvider;

import io.reactivex.Observable;

/**
 * Created by Tao Zhou on 2019/4/28
 * Package name: com.tao.note.ui.profile
 */
public class ProfileViewModel extends BaseViewModel<ProfileNavigator> {

    private final ObservableField<String> userName = new ObservableField<>();

    public ProfileViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public ObservableField<String> getUserName() {
        return userName;
    }

    public void onDataLoad() {
        final String currentUserName = getDataManager().getCurrentUserName();
        if (!TextUtils.isEmpty(currentUserName)) {
            userName.set(currentUserName);
        }
    }

    public void onAccountDetailClick() {
        getNavigator().openAccountDetailActivity();
    }

}
