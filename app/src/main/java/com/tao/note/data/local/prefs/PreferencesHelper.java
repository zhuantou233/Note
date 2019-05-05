package com.tao.note.data.local.prefs;

import com.tao.note.data.model.db.MyUser;


import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Tao Zhou on 2019/4/17
 * Package name: com.tao.note.data.local.prefs
 */
public interface PreferencesHelper {

    String getCurrentUserName();

    String getCurrentUserPhoneNumber();

    BmobFile getCurrentUserAvatar();

    int getCurrentUserLoggedInMode();

    int getCurrentUserAccountType();

    String getCurrentUserAccountTypeName();

    String getCurrentUserAvatarUrl();

    void setCurrentUser(MyUser myUser);

    MyUser getCurrentUser();
}
