package com.tao.note.ui.profile;

import android.text.TextUtils;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;

import com.facebook.drawee.view.SimpleDraweeView;
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

    private final ObservableField<String> avatarUrl = new ObservableField<>();

    public ProfileViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public ObservableField<String> getUserName() {
        return userName;
    }

    public ObservableField<String> getAvatarUrl() {
        return avatarUrl;
    }

    public void onDataLoad() {
        final String currentUserName = getDataManager().getCurrentUserName();
        if (!TextUtils.isEmpty(currentUserName)) {
            userName.set(currentUserName);
        }

        final String currentAvatarUrl = getDataManager().getCurrentUserAvatarUrl();
        if (!TextUtils.isEmpty(currentAvatarUrl)) {
            avatarUrl.set(currentAvatarUrl);
        }
    }

    public void onAccountDetailClick() {
        getNavigator().openAccountDetailActivity();
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(SimpleDraweeView view, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
            view.setImageURI(imageUrl);
        }
    }
}
