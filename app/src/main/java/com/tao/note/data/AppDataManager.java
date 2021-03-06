package com.tao.note.data;

import android.content.Context;
import android.os.Environment;

import com.google.gson.Gson;
import com.tao.note.data.local.db.DBHelper;
import com.tao.note.data.local.prefs.PreferencesHelper;
import com.tao.note.data.model.db.MyUser;
import com.tao.note.data.remote.ApiHeader;
import com.tao.note.data.remote.ApiHelper;
import com.tao.note.utils.L;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import io.reactivex.Observable;

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
    public Observable<Integer> doRequestVerCode(String phone) {
        return mApiHelper.doRequestVerCode(phone);
    }

    @Override
    public Observable<MyUser> doSignUp(String phone, String password, String code) {
        return mApiHelper.doSignUp(phone, password, code);
    }

    @Override
    public Observable<MyUser> doSignIn(String phone, String password) {
        return mApiHelper.doSignIn(phone, password);
    }

    @Override
    public Observable<MyUser> doSignInWithCode(String phone, String code) {
        return mApiHelper.doSignInWithCode(phone, code);
    }

    @Override
    public Observable<MyUser> doResetPassword(String phone, String password, String code) {
        return mApiHelper.doResetPassword(phone, password, code);
    }

    @Override
    public Observable<MyUser> doUpdatePassword(String oldPassword, String newPassword) {
        return mApiHelper.doUpdatePassword(oldPassword, newPassword);
    }

    @Override
    public void doLogout() {
        mApiHelper.doLogout();
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public Observable<MyUser> uploadAvatar(File file) {
        return mApiHelper.uploadAvatar(file);
    }

    @Override
    public Observable<MyUser> uploadUserInfo(MyUser user) {
        return mApiHelper.uploadUserInfo(user);
    }

    @Override
    public Observable<MyUser> uploadUserName(String name) {
        return mApiHelper.uploadUserName(name);
    }

    @Override
    public Observable<MyUser> uploadUserPhoneNumber(String phone) {
        return mApiHelper.uploadUserPhoneNumber(phone);
    }

    @Override
    public Observable<MyUser> uploadUserAccountType(AccountType type) {
        return mApiHelper.uploadUserAccountType(type);
    }

    @Override
    public Observable<MyUser> fetchUserInfo() {
        return mApiHelper.fetchUserInfo();
    }

    @Override
    public Observable<Boolean> seedAppSettings() {
        // todo app初始化加载固定资源或设置
        return Observable.just(true);
    }

    @Override
    public String getCurrentUserName() {
        return mPreferencesHelper.getCurrentUserName();
    }

    @Override
    public String getCurrentUserPhoneNumber() {
        return mPreferencesHelper.getCurrentUserPhoneNumber();
    }

    @Override
    public BmobFile getCurrentUserAvatar() {
        return mPreferencesHelper.getCurrentUserAvatar();
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public int getCurrentUserAccountType() {
        return mPreferencesHelper.getCurrentUserAccountType();
    }

    @Override
    public String getCurrentUserAccountTypeName() {
        return mPreferencesHelper.getCurrentUserAccountTypeName();
    }

    @Override
    public String getCurrentUserAvatarUrl() {
        return mPreferencesHelper.getCurrentUserAvatarUrl();
    }

    @Override
    public void setCurrentUser(MyUser myUser) {
        mPreferencesHelper.setCurrentUser(myUser);
    }

    @Override
    public MyUser getCurrentUser() {
        return mPreferencesHelper.getCurrentUser();
    }
}
