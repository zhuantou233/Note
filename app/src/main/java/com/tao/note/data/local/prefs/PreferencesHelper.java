package com.tao.note.data.local.prefs;

import com.tao.note.data.DataManager;

import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Tao Zhou on 2019/4/17
 * Package name: com.tao.note.data.local.prefs
 */
public interface PreferencesHelper {

    String getCurrentUserName();

    void setCurrentUserName(String name);

    String getCurrentUserPhoneNumber();

    void setCurrentUserPhoneNumber(String phoneNumber);

    BmobFile getCurrentUserAvatar();

    void setCurrentUserAvatar(BmobFile avatar);

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    int getCurrentUserAccountType();

    void setCurrentUserAccountType(DataManager.AccountType type);

    String getCurrentUserAccountTypeName();

    String getCurrentUserAvatarUrl();

    void setCurrentUserAvatarUrl(String avatarUrl);
}
