package com.tao.note.ui.login.resetpassword;

import com.tao.note.data.DataManager;
import com.tao.note.ui.base.BaseViewModel;
import com.tao.note.utils.rx.SchedulerProvider;

/**
 * Created by Tao Zhou on 2019/4/22
 * Package name: com.tao.note.ui.login.resetpassword
 */
public class ResetPasswordViewModel extends BaseViewModel<ResetPasswordNavigator> {

    private boolean result = false;

    public ResetPasswordViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNavBackClick() {
        getNavigator().goBack();
    }

    public void onRequestVerCodeClick() {
        getNavigator().requestVerCode();
    }

    public void onConfirmResetClick() {
        getNavigator().confirmReset();
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

    public boolean confirmReset(String phone, String password, String code) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doResetPassword(phone, password, code)
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
