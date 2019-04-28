package com.tao.note.ui.login.signup;

import com.tao.note.data.DataManager;
import com.tao.note.data.model.db.MyUser;
import com.tao.note.ui.base.BaseViewModel;
import com.tao.note.utils.L;
import com.tao.note.utils.rx.SchedulerProvider;

import io.reactivex.observers.DefaultObserver;

/**
 * Created by Tao Zhou on 2019/4/22
 * Package name: com.tao.note.ui.login.signup
 */
public class SignUpViewModel extends BaseViewModel<SignUpNavigator> {

    private boolean result = false;

    public SignUpViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNavBackClick() {
        getNavigator().goBack();
    }

    public void onRequestVerCodeClick() {
        getNavigator().requestVerCode();
    }

    public void onSignUpClick() {
        getNavigator().signUp();
    }

    public void requestVerCode(String phone) {
        setIsLoading(true);
        getDataManager()
                .doRequestVerCode(phone)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new DefaultObserver<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        setIsLoading(false);
                        L.i("发送验证码成功，短信ID：" + integer + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        setIsLoading(false);
                        getNavigator().handleError(e);
                        L.i("发送验证码失败：" + e.getMessage() + "\n");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void signUp(String phone, String password, String code) {
        setIsLoading(true);
        getDataManager()
                .doSignUp(phone, password, code)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new DefaultObserver<MyUser>() {
                    @Override
                    public void onNext(MyUser myUser) {
                        setIsLoading(false);
                        L.i("注册成功");
                        getDataManager().updateUserInfo(myUser);
                        getNavigator().openMainActivity();
                    }

                    @Override
                    public void onError(Throwable e) {
                        setIsLoading(false);
                        L.i("注册失败：" + e.getMessage() + "\n");
                        getNavigator().handleError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
