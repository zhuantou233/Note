package com.tao.note.ui.login.signin;

import com.tao.note.data.DataManager;
import com.tao.note.ui.base.BaseViewModel;
import com.tao.note.utils.rx.SchedulerProvider;

/**
 * Created by Tao Zhou on 2019/4/22
 * Package name: com.tao.note.ui.login.signin
 */
public class SignInViewModel extends BaseViewModel<SignInNavigator> {
    public SignInViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    private boolean result = false;

    public void onSignInClick() {
        getNavigator().signIn();
    }

    public void onSignUpClick() {
        getNavigator().showSignUpFragment();
    }

    public void onResetPasswordClick() {
        getNavigator().showBottomSheetDialog();
    }

    public boolean signIn(String phone, String password) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doSignIn(phone, password)
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
