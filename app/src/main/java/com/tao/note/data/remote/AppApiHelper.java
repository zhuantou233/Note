package com.tao.note.data.remote;

import com.tao.note.data.model.db.MyUser;
import com.tao.note.utils.L;

import javax.inject.Inject;
import javax.inject.Singleton;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.UpdateListener;
import io.reactivex.Observable;

/**
 * Created by Tao Zhou on 2019/4/17
 * Package name: com.tao.note.data.remote
 */
@Singleton
public class AppApiHelper implements ApiHelper {
    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public Observable<Integer> doRequestVerCode(String phone) {
        return BmobSMS.requestSMSCodeObservable(phone, "");
    }

    @Override
    public Observable<MyUser> doSignUp(String phone, String password, String code) {
        MyUser user = new MyUser();
        user.setMobilePhoneNumber(phone);
        user.setPassword(password);
        return user.signOrLoginObservable(MyUser.class, code);
    }

    @Override
    public Observable<MyUser> doSignIn(String phone, String password) {
        return BmobUser.loginByAccountObservable(MyUser.class, phone, password);
    }

    @Override
    public Observable<MyUser> doSignInWithCode(String phone, String code) {
        return Observable.create(emitter -> BmobUser.loginBySMSCode(phone, code, new LogInListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                if (e == null) {
                    emitter.onNext(myUser);
                } else {
                    emitter.onError(e);
                }
            }
        }));
    }

    @Override
    public Observable<BmobException> doResetPassword(String phone, String password, String code) {
        return BmobUser.resetPasswordBySMSCodeObservable(code, password);
    }

    @Override
    public void doLogout() {
        BmobUser.logOut();
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }
}
