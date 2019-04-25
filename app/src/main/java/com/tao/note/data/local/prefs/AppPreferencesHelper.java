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

/**
 * Created by Tao Zhou on 2019/4/17
 * Package name: com.tao.note.data.local.prefs
 */
public class AppPreferencesHelper implements PreferencesHelper {
    private final SharedPreferences mPrefs;
    private MyUser user;

    @Inject
    public AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
        user = BmobUser.getCurrentUser(MyUser.class);
    }

    @Override
    public String getCurrentUserName() {
        return user == null ? AppConstants.DEFAULT_USER_NAME : user.getNickName();
    }

    @Override
    public void setCurrentUserName(String name) {
        if (user != null) {
            user.setNickName(name);
            updateToBmob();
        }
    }


    @Override
    public String getCurrentUserPhoneNumber() {
        return user == null ? AppConstants.DEFAULT_USER_PHONE : user.getMobilePhoneNumber();
    }

    @Override
    public void setCurrentUserPhoneNumber(String phoneNumber) {
        if (user != null) {
            user.setMobilePhoneNumber(phoneNumber);
            updateToBmob();
        }
    }

    @Override
    public BmobFile getCurrentUserAvatar() {
        return user == null ? null : user.getAvatar();
    }

    @Override
    public void setCurrentUserAvatar(BmobFile avatar) {
        if (user != null) {
            user.setAvatar(avatar);
            updateToBmob();
        }
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return user == null ?
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()
                : DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_IN.getType();
    }

    @Override
    public void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode) {
        // todo
    }

    @Override
    public int getCurrentUserAccountType() {
        return user == null ?
                DataManager.AccountType.ACCOUNT_TYPE_NORMAL.getType()
                : user.getAccountType();
    }

    @Override
    public void setCurrentUserAccountType(DataManager.AccountType type) {
        if (user != null) {
            user.setAccountType(type.getType());
            updateToBmob();
        }
    }

    private void updateToBmob() {
        if (user != null) {
            user.update(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        L.i("更新用户信息成功");
                    } else {
                        L.i("更新用户信息失败");
                        L.e(e.getMessage());
                    }
                }
            });
        }
    }
}
