package com.tao.note.ui.main;

import android.net.Uri;
import android.text.TextUtils;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tao.note.R;
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

    public ObservableField<String> getAccountType() {
        return accountType;
    }

    public ObservableField<String> getUserName() {
        return userName;
    }

    public ObservableField<String> getAvatarUrl() {
        return avatarUrl;
    }

    public void onLogout() {
        setIsLoading(true);
        getDataManager().doLogout();
        getNavigator().openLoginActivity();
    }

    public void onNavMenuCreated() {
        final String currentUserName = getDataManager().getCurrentUserName();
        if (!TextUtils.isEmpty(currentUserName)) {
            userName.set(currentUserName);
        }

        final String currentAccountType = getDataManager().getCurrentUserAccountTypeName();
        if (!TextUtils.isEmpty(currentAccountType)) {
            accountType.set(currentAccountType);
        }

        final String currentAvatarUrl = getDataManager().getCurrentUserAvatarUrl();
        if (!TextUtils.isEmpty(currentAvatarUrl)) {
            avatarUrl.set(currentAvatarUrl);
        }
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(SimpleDraweeView view, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
            view.setImageURI(imageUrl);
        }
    }

    public void onProfileClick() {
        getNavigator().openProfileActivity();
    }
}
