package com.tao.note.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.tao.note.data.DataManager;
import com.tao.note.data.model.db.MyUser;
import com.tao.note.di.PreferenceInfo;
import com.tao.note.utils.AppConstants;
import com.tao.note.utils.L;

import javax.inject.Inject;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import io.reactivex.Observable;

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
    public Observable<Void> setCurrentUserName(String name) {
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
    public Observable<Void> setCurrentUserPhoneNumber(String phoneNumber) {
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
    public Observable<Void> setCurrentUserAvatar(BmobFile avatar) {
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
    public Observable<Void> setCurrentUserLoggedInMode(DataManager.LoggedInMode mode) {
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
    public Observable<Void> setCurrentUserAccountType(DataManager.AccountType type) {
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
        return mPrefs.getString(PREF_KEY_CURRENT_USER_AVATAR_URL, null);
    }

    @Override
    public void setCurrentUserAvatarUrl(String avatarUrl) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_AVATAR_URL, avatarUrl).apply();
    }

    private Observable<Void> updateToBmob() {
        return Observable.create(emitter -> user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    emitter.onComplete();
                } else {
                    emitter.onError(e);
                }
            }
        }));
    }
}
