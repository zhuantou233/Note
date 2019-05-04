package com.tao.note.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.tao.note.data.DataManager;
import com.tao.note.data.model.db.MyUser;
import com.tao.note.di.PreferenceInfo;
import com.tao.note.utils.AppConstants;
import com.tao.note.utils.L;

import java.io.File;

import javax.inject.Inject;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by Tao Zhou on 2019/4/17
 * Package name: com.tao.note.data.local.prefs
 */
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_CURRENT_USER_AVATAR_URL = "PREF_KEY_CURRENT_USER_AVATAR_URL";

    private final SharedPreferences mPrefs;

    private MyUser user;

    @Inject
    public AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
        user = BmobUser.getCurrentUser(MyUser.class);
    }


    @Override
    public String getCurrentUserName() {
        if (user == null || user.getNickName() == null) {
            return AppConstants.DEFAULT_USER_NAME;
        } else {
            return user.getNickName();
        }
    }

    @Override
    public Observable<MyUser> setCurrentUserName(String name) {
        if (user != null) {
            user.setNickName(name);
            return updateToBmob();
        }
        return null;
    }


    @Override
    public String getCurrentUserPhoneNumber() {
        return user == null ? AppConstants.DEFAULT_USER_PHONE : user.getMobilePhoneNumber();
    }

    @Override
    public Observable<MyUser> setCurrentUserPhoneNumber(String phoneNumber) {
        if (user != null) {
            user.setMobilePhoneNumber(phoneNumber);
            return updateToBmob();
        }
        return null;
    }

    @Override
    public BmobFile getCurrentUserAvatar() {
        return user == null ? null : user.getAvatar();
    }

    @Override
    public Observable<MyUser> setCurrentUserAvatar(BmobFile avatar) {
        if (user != null) {
            user.setAvatar(avatar);
            return updateToBmob();
        }
        return null;
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return user == null ?
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()
                : DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_IN.getType();
    }

    @Override
    public Observable<MyUser> setCurrentUserLoggedInMode(DataManager.LoggedInMode mode) {
        // todo
        return null;
    }

    @Override
    public int getCurrentUserAccountType() {
        return user == null ?
                DataManager.AccountType.ACCOUNT_TYPE_NORMAL.getType()
                : user.getAccountType();
    }

    @Override
    public Observable<MyUser> setCurrentUserAccountType(DataManager.AccountType type) {
        if (user != null) {
            user.setAccountType(type.getType());
            return updateToBmob();
        }
        return null;
    }

    @Override
    public String getCurrentUserAccountTypeName() {
        String typeName = "Normal";
        switch (getCurrentUserAccountType()) {
            case 0:
                typeName = "Normal";
                break;
            case 1:
                typeName = "Admin";
                break;
            default:
                break;
        }
        return typeName;
    }

    @Override
    public String getCurrentUserAvatarUrl() {
        if (user != null && user.getAvatar() != null) {
            return user.getAvatar().getUrl();
        } else {
            return null;
        }
    }

    @Override
    public void setCurrentUserAvatarUrl(String avatarUrl) {
//        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_AVATAR_URL, avatarUrl).apply();
    }

    @Override
    public void setCurrentUser(MyUser myUser) {
        if (myUser != null) {
            user = myUser;
        }
    }

    @Override
    public MyUser getCurrentUser() {
        return user;
    }

    private Observable<MyUser> updateToBmob() {
        return Observable.create(emitter -> user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    emitter.onNext(user);
                    emitter.onComplete();
                } else {
                    emitter.onError(e);
                }
            }
        }));
    }
}
