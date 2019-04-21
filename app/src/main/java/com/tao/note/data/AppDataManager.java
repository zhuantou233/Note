package com.tao.note.data;

import android.content.Context;

import com.google.gson.Gson;
import com.tao.note.data.local.db.DBHelper;
import com.tao.note.data.local.prefs.PreferencesHelper;
import com.tao.note.data.model.db.MyUser;
import com.tao.note.data.remote.ApiHeader;
import com.tao.note.data.remote.ApiHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Tao Zhou on 2019/4/17
 * Package name: com.tao.note.data
 */
@Singleton
public class AppDataManager implements DataManager {
    private final ApiHelper mApiHelper;

    private final Context mContext;

    private final DBHelper mDBHelper;

    private final Gson mGson;

    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(ApiHelper mApiHelper, Context mContext, DBHelper mDBHelper, Gson mGson, PreferencesHelper mPreferencesHelper) {
        this.mApiHelper = mApiHelper;
        this.mContext = mContext;
        this.mDBHelper = mDBHelper;
        this.mGson = mGson;
        this.mPreferencesHelper = mPreferencesHelper;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public void setUserAsLoggedOut() {
        BmobUser.logOut();
    }

    @Override
    public String getCurrentUserName() {
        return mPreferencesHelper.getCurrentUserName();
    }

    @Override
    public void setCurrentUserName(String name) {
        mPreferencesHelper.setCurrentUserName(name);
    }

    @Override
    public String getCurrentUserPhoneNumber() {
        return mPreferencesHelper.getCurrentUserPhoneNumber();
    }

    @Override
    public void setCurrentUserPhoneNumber(String phoneNumber) {
        mPreferencesHelper.setCurrentUserPhoneNumber(phoneNumber);
    }

    @Override
    public BmobFile getCurrentUserAvatar() {
        return mPreferencesHelper.getCurrentUserAvatar();
    }

    @Override
    public void setCurrentUserAvatar(BmobFile avatar) {
        mPreferencesHelper.setCurrentUserAvatar(avatar);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public int getCurrentUserAccountType() {
        return mPreferencesHelper.getCurrentUserAccountType();
    }

    @Override
    public void setCurrentUserAccountType(AccountType type) {
        mPreferencesHelper.setCurrentUserAccountType(type);
    }
}
