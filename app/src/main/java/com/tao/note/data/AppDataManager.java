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
    public Observable<BmobException> doResetPassword(String phone, String password, String code) {
        return mApiHelper.doResetPassword(phone, password, code);
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
    public Observable<BmobFile> uploadFile(File file) {
        return mApiHelper.uploadFile(file);
    }

    @Override
    public Observable<MyUser> uploadUserInfo() {
        return mApiHelper.uploadUserInfo();
    }

    @Override
    public Observable<Boolean> seedAppSettings() {
        // todo app初始化加载固定资源或设置
        return Observable.just(true);
    }

    @Override
    public void setUserAsLoggedOut() {
        BmobUser.logOut();
        setCurrentUserAvatarUrl(null);
    }

    @Override
    public void updateUserInfo(MyUser user) {
        BmobFile avatarFile = user.getAvatar();
        if (avatarFile != null && getCurrentUserAvatarUrl() == null) {
            File saveFile = new File(Environment.getExternalStorageDirectory(), avatarFile.getFilename());
            avatarFile.download(saveFile, new DownloadFileListener() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        setCurrentUserAvatarUrl(s);
                        L.i("下载成功" + s);
                    } else {
                        L.i("下载失败: " + e.getErrorCode() + "-" + e.getMessage());
                    }
                }

                @Override
                public void onProgress(Integer integer, long l) {

                }
            });
        }
    }

    @Override
    public String getCurrentUserName() {
        return mPreferencesHelper.getCurrentUserName();
    }

    @Override
    public Observable<Void> setCurrentUserName(String name) {
        return mPreferencesHelper.setCurrentUserName(name);
    }

    @Override
    public String getCurrentUserPhoneNumber() {
        return mPreferencesHelper.getCurrentUserPhoneNumber();
    }

    @Override
    public Observable<Void> setCurrentUserPhoneNumber(String phoneNumber) {
        return mPreferencesHelper.setCurrentUserPhoneNumber(phoneNumber);
    }

    @Override
    public BmobFile getCurrentUserAvatar() {
        return mPreferencesHelper.getCurrentUserAvatar();
    }

    @Override
    public Observable<Void> setCurrentUserAvatar(File avatar) {
        return mPreferencesHelper.setCurrentUserAvatar(avatar);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public Observable<Void> setCurrentUserLoggedInMode(LoggedInMode mode) {
        return mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public int getCurrentUserAccountType() {
        return mPreferencesHelper.getCurrentUserAccountType();
    }

    @Override
    public Observable<Void> setCurrentUserAccountType(AccountType type) {
        return mPreferencesHelper.setCurrentUserAccountType(type);
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
    public void setCurrentUserAvatarUrl(String avatarUrl) {
        mPreferencesHelper.setCurrentUserAvatarUrl(avatarUrl);
    }
}
