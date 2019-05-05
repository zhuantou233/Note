package com.tao.note.data.remote;

import com.tao.note.data.DataManager;
import com.tao.note.data.model.db.MyUser;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import io.reactivex.Observable;

/**
 * Created by Tao Zhou on 2019/4/17
 * Package name: com.tao.note.data.remote
 */
public interface ApiHelper {
    Observable<Integer> doRequestVerCode(String phone);

    Observable<MyUser> doSignUp(String phone, String password, String code);

    Observable<MyUser> doSignIn(String phone, String password);

    Observable<MyUser> doSignInWithCode(String phone, String code);

    Observable<MyUser> doResetPassword(String phone, String password, String code);

    Observable<MyUser> doUpdatePassword(String oldPassword, String newPassword);

    void doLogout();

    ApiHeader getApiHeader();

    Observable<MyUser> uploadAvatar(File file);

    Observable<MyUser> uploadUserInfo(MyUser user);

    Observable<MyUser> uploadUserName(String name);

    Observable<MyUser> uploadUserPhoneNumber(String phone);

    Observable<MyUser> uploadUserAccountType(DataManager.AccountType type);

    Observable<MyUser> fetchUserInfo();

}
