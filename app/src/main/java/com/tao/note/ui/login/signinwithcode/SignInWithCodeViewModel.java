package com.tao.note.ui.login.signinwithcode;

import com.tao.note.data.DataManager;
import com.tao.note.ui.base.BaseViewModel;
import com.tao.note.utils.rx.SchedulerProvider;

/**
 * Created by Tao Zhou on 2019/4/22
 * Package name: com.tao.note.ui.login.signinwithcode
 */
public class SignInWithCodeViewModel extends BaseViewModel<SignInWithCodeNavigator> {

    private boolean result = false;

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

    public boolean signIn(String phone, String code) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doSignInWithCode(phone, code)
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
