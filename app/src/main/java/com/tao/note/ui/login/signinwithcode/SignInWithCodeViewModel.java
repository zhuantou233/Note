package com.tao.note.ui.login.signinwithcode;

import com.tao.note.data.DataManager;
import com.tao.note.data.model.db.MyUser;
import com.tao.note.ui.base.BaseViewModel;
import com.tao.note.utils.L;
import com.tao.note.utils.rx.SchedulerProvider;

import io.reactivex.observers.DefaultObserver;

/**
 * Created by Tao Zhou on 2019/4/22
 * Package name: com.tao.note.ui.login.signinwithcode
 */
public class SignInWithCodeViewModel extends BaseViewModel<SignInWithCodeNavigator> {

    public SignInWithCodeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNavBackClick() {
        getNavigator().goBack();
    }

    public void onRequestVerCodeClick() {
        getNavigator().requestVerCode();
    }

    public void onSignInClick() {
        getNavigator().signIn();
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

    public void signIn(String phone, String password) {
        setIsLoading(true);
        getDataManager()
                .doSignInWithCode(phone, password)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new DefaultObserver<MyUser>() {
                    @Override
                    public void onNext(MyUser myUser) {
                        setIsLoading(false);
                        L.i("登录成功");
                        getNavigator().openMainActivity();
                    }

                    @Override
                    public void onError(Throwable e) {
                        setIsLoading(false);
                        L.i("登录失败：" + e.getMessage() + "\n");
                        getNavigator().handleError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
