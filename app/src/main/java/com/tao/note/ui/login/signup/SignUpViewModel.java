package com.tao.note.ui.login.signup;

import com.tao.note.data.DataManager;
import com.tao.note.ui.base.BaseViewModel;
import com.tao.note.utils.rx.SchedulerProvider;

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

    public boolean requestVerCode(String phone) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doRequestVerCode(phone)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(aBoolean -> {
                    setIsLoading(false);
                    result = aBoolean;
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
        return result;
    }

    public boolean signUp(String phone, String password, String code) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doSignUp(phone, password, code)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(aBoolean -> {
                    setIsLoading(false);
                    result = aBoolean;
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
        return result;
    }
}
