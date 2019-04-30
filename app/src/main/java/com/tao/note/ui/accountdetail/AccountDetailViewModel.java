package com.tao.note.ui.accountdetail;

import android.text.TextUtils;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tao.note.data.DataManager;
import com.tao.note.data.model.db.MyUser;
import com.tao.note.ui.base.BaseViewModel;
import com.tao.note.utils.L;
import com.tao.note.utils.PhotoUtil;
import com.tao.note.utils.rx.SchedulerProvider;

import java.io.File;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DefaultObserver;

/**
 * Created by Tao Zhou on 2019/4/28
 * Package name: com.tao.note.ui.accountdetail
 */
public class AccountDetailViewModel extends BaseViewModel<AccountDetailNavigator> {

    private final ObservableField<String> userName = new ObservableField<>();

    private final ObservableField<String> avatarUrl = new ObservableField<>();

    private final ObservableField<String> userPhoneNumber = new ObservableField<>();

    public AccountDetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public ObservableField<String> getUserPhoneNumber() {
        return userPhoneNumber;
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

        final String currentUserPhoneNumber = getDataManager().getCurrentUserPhoneNumber();
        if (!TextUtils.isEmpty(currentUserPhoneNumber)) {
            userPhoneNumber.set(currentUserPhoneNumber);
        }

        final String currentAvatarUrl = getDataManager().getCurrentUserAvatarUrl();
        if (!TextUtils.isEmpty(currentAvatarUrl)) {
            avatarUrl.set(currentAvatarUrl);
        }
    }

    public void onChangeAccountType() {
        // todo 更改用户类型,admin或者normal
    }

    public void onCancelClick() {
        getNavigator().dismissDialog();
    }

    public void onNameConfirmClick() {
        setIsLoading(true);
        getDataManager()
                .setCurrentUserName(userName.get())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new DefaultObserver<Void>() {
                    @Override
                    public void onNext(Void aVoid) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        setIsLoading(false);
                        L.i("更新失败：" + e.getMessage() + "\n");
                        getNavigator().handleError(e);
                    }

                    @Override
                    public void onComplete() {
                        setIsLoading(false);
                        L.i("更新成功");
                        getNavigator().dismissDialog();
                    }
                });
    }

    public void onPhoneConfirmClick() {
        setIsLoading(true);
        getDataManager()
                .setCurrentUserPhoneNumber(userPhoneNumber.get())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new DefaultObserver<Void>() {
                    @Override
                    public void onNext(Void aVoid) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        setIsLoading(false);
                        L.i("更新失败：" + e.getMessage() + "\n");
                        getNavigator().handleError(e);
                    }

                    @Override
                    public void onComplete() {
                        setIsLoading(false);
                        L.i("更新成功");
                        getNavigator().dismissDialog();
                    }
                });
    }

    public void onUploadAvatar(File file) {
        setIsLoading(true);
        getDataManager()
                .uploadFile(file)
                .doOnNext(bmobFile -> {
                    BmobUser.getCurrentUser(MyUser.class).setAvatar(bmobFile);
                    L.i("doAfterNext");
                })
                .concatMap(bmobFile -> getDataManager().uploadUserInfo())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new DefaultObserver<MyUser>() {
                    @Override
                    public void onNext(MyUser myUser) {
                        setIsLoading(false);
                        L.i("更新成功");
                        getDataManager().updateUserInfo(BmobUser.getCurrentUser(MyUser.class));
                        onDataLoad();
                    }

                    @Override
                    public void onError(Throwable e) {
                        setIsLoading(false);
                        L.i("更新失败：" + e.getMessage() + "\n");
                        getNavigator().handleError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(SimpleDraweeView view, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
            view.setImageURI(imageUrl);
        }
    }
}