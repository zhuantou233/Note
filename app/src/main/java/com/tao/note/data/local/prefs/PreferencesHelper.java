package com.tao.note.data.local.prefs;

import com.tao.note.data.DataManager;
import com.tao.note.data.model.db.MyUser;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import io.reactivex.Observable;

/**
 * Created by Tao Zhou on 2019/4/17
 * Package name: com.tao.note.data.local.prefs
 */
public interface PreferencesHelper {

    String getCurrentUserName();

    Observable<MyUser> setCurrentUserName(String name);

    String getCurrentUserPhoneNumber();

    Observable<MyUser> setCurrentUserPhoneNumber(String phoneNumber);

    BmobFile getCurrentUserAvatar();

    Observable<MyUser> setCurrentUserAvatar(BmobFile avatar);

    int getCurrentUserLoggedInMode();

    Observable<MyUser> setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    int getCurrentUserAccountType();

    Observable<MyUser> setCurrentUserAccountType(DataManager.AccountType type);

    String getCurrentUserAccountTypeName();

    String getCurrentUserAvatarUrl();

    void setCurrentUserAvatarUrl(String avatarUrl);

    void setCurrentUser(MyUser myUser);

    MyUser getCurrentUser();
}
