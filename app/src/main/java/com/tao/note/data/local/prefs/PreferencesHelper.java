package com.tao.note.data.local.prefs;

import com.tao.note.data.DataManager;

import cn.bmob.v3.datatype.BmobFile;
import io.reactivex.Observable;

/**
 * Created by Tao Zhou on 2019/4/17
 * Package name: com.tao.note.data.local.prefs
 */
public interface PreferencesHelper {

    String getCurrentUserName();

    Observable<Void> setCurrentUserName(String name);

    String getCurrentUserPhoneNumber();

    Observable<Void> setCurrentUserPhoneNumber(String phoneNumber);

    BmobFile getCurrentUserAvatar();

    Observable<Void> setCurrentUserAvatar(BmobFile avatar);

    int getCurrentUserLoggedInMode();

    Observable<Void> setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    int getCurrentUserAccountType();

    Observable<Void> setCurrentUserAccountType(DataManager.AccountType type);

    String getCurrentUserAccountTypeName();

    String getCurrentUserAvatarUrl();

    void setCurrentUserAvatarUrl(String avatarUrl);
}
