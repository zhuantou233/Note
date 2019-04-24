package com.tao.note.data.remote;

import com.tao.note.R;
import com.tao.note.data.model.db.MyUser;
import com.tao.note.utils.L;

import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import dagger.Provides;
import io.reactivex.Observable;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/**
 * Created by Tao Zhou on 2019/4/17
 * Package name: com.tao.note.data.remote
 */
@Singleton
public class AppApiHelper implements ApiHelper {
    private ApiHeader mApiHeader;
    private boolean result = false;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public Observable<Boolean> doRequestVerCode(String phone) {
        // 这里有点问题，应该使用REST API通过RxAndroid进行请求
        BmobSMS.requestSMSCode(phone, "", new QueryListener<Integer>() {
            @Override
            public void done(Integer smsId, BmobException e) {
                if (e == null) {
                    L.i("发送验证码成功，短信ID：" + smsId + "\n");
                    result = true;
                } else {
                    L.i("发送验证码失败：" + e.getErrorCode() + "-" + e.getMessage() + "\n");
                    result = false;
                }
            }
        });
        return Observable.just(result);
    }

    @Override
    public Observable<Boolean> doSignUp(String phone, String password, String code) {
        MyUser user = new MyUser();
        user.setMobilePhoneNumber(phone);
        user.setPassword(password);
        user.signOrLogin(code, new SaveListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                if (e == null) {
                    L.i("发送验证码成功");
                    result = true;
                } else {
                    L.i("发送验证码失败：" + e.getErrorCode() + "-" + e.getMessage() + "\n");
                    result = false;
                }
            }
        });
        return Observable.just(result);
    }

    @Override
    public Observable<Boolean> doSignIn(String phone, String password) {
        BmobUser.loginByAccount(phone, password, new LogInListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                if (e == null) {
                    L.i("登录成功");
                    result = true;
                } else {
                    L.i("登录失败：" + e.getErrorCode() + "-" + e.getMessage() + "\n");
                    result = false;
                }
            }
        });
        return Observable.just(result);
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }
}
