package com.tao.note.data.remote;

import com.tao.note.data.DataManager;
import com.tao.note.data.model.db.MyUser;
import com.tao.note.utils.L;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import io.reactivex.Observable;

/**
 * Created by Tao Zhou on 2019/4/17
 * Package name: com.tao.note.data.remote
 */
@Singleton
public class AppApiHelper implements ApiHelper {
    private ApiHeader mApiHeader;
    private MyUser user;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
        user = BmobUser.getCurrentUser(MyUser.class);
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
        user.setNickName(phone);
        // todo 控制账号类型
        user.setAccountType(DataManager.AccountType.ACCOUNT_TYPE_ADMIN.getType());
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

    @Override
    public Observable<BmobFile> uploadFile(File file) {
        BmobFile bmobFile = new BmobFile(file);
        return Observable.create(emitter -> bmobFile.upload(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    emitter.onNext(bmobFile);
                    L.i("uploadFile");
                } else {
                    emitter.onError(e);
                }
            }
        }));
    }

    @Override
    public Observable<MyUser> uploadUserInfo() {
        return Observable.create(emitter -> user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    L.i("uploadUserInfo");
                    emitter.onNext(user);
                } else {
                    emitter.onError(e);
                }
            }
        }));
    }
}
