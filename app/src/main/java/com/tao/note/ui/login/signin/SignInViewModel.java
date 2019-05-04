package com.tao.note.ui.login.signin;

import com.tao.note.data.DataManager;
import com.tao.note.data.model.db.MyUser;
import com.tao.note.ui.base.BaseViewModel;
import com.tao.note.utils.L;
import com.tao.note.utils.rx.SchedulerProvider;

import io.reactivex.observers.DefaultObserver;

/**
 * Created by Tao Zhou on 2019/4/22
 * Package name: com.tao.note.ui.login.signin
 */
public class SignInViewModel extends BaseViewModel<SignInNavigator> {
    public SignInViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onSignInClick() {
        getNavigator().signIn();
    }

    public void onSignUpClick() {
        getNavigator().showSignUpFragment();
    }

    public void onResetPasswordClick() {
        getNavigator().showBottomSheetDialog();
    }

    public void signIn(String phone, String password) {
        setIsLoading(true);
        getDataManager()
                .doSignIn(phone, password)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new DefaultObserver<MyUser>() {
                    @Override
                    public void onNext(MyUser myUser) {
                        setIsLoading(false);
                        L.i("登录成功");
                        getDataManager().setCurrentUser(myUser);
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
