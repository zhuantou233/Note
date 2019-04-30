package com.tao.note.data.remote;

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

    Observable<BmobException> doResetPassword(String phone, String password, String code);

    void doLogout();

    ApiHeader getApiHeader();

    Observable<BmobFile> uploadFile(File file);

    Observable<MyUser> uploadUserInfo();
}
