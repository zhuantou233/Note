package com.tao.note.ui.main;

import androidx.databinding.ObservableField;

import com.tao.note.data.DataManager;
import com.tao.note.ui.base.BaseViewModel;
import com.tao.note.utils.rx.SchedulerProvider;

/**
 * Created by Tao Zhou on 2019/4/22
 * Package name: com.tao.note.ui.main
 */
public class MainViewModel extends BaseViewModel<MainNavigator> {

    private final ObservableField<String> userName = new ObservableField<>();

    private final ObservableField<String> accountType = new ObservableField<>();

    private final ObservableField<String> avatarUrl = new ObservableField<>();

    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onLogout() {
        setIsLoading(true);
        getDataManager().doLogout();
        getNavigator().openLoginActivity();
    }

    public void onNavMenuCreated() {

    }
}
