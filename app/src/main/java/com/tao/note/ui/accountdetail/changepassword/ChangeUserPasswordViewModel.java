package com.tao.note.ui.accountdetail.changepassword;

import com.tao.note.data.DataManager;
import com.tao.note.data.model.db.MyUser;
import com.tao.note.ui.base.BaseViewModel;
import com.tao.note.utils.L;
import com.tao.note.utils.rx.SchedulerProvider;

import io.reactivex.observers.DefaultObserver;

/**
 * Created by Tao Zhou on 2019/5/5
 * Package name: com.tao.note.ui.accountdetail.changepassword
 */
public class ChangeUserPasswordViewModel extends BaseViewModel<ChangeUserPasswordCallback> {

    public ChangeUserPasswordViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onCancelClick() {
        getNavigator().dismissDialog();
    }

    public void onConfirmClick() {
        getNavigator().confirmReset();
    }

    public void confirmReset(String oldPassword, String newPassword) {
        setIsLoading(true);
        getDataManager()
                .doUpdatePassword(oldPassword, newPassword)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new DefaultObserver<MyUser>() {
                    @Override
                    public void onNext(MyUser user) {
                        setIsLoading(false);
                        L.i("更新成功");
                        getDataManager().setCurrentUser(user);
                        getNavigator().handleError(new Throwable("更新成功"));
                        getNavigator().dismissDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        setIsLoading(false);
                        L.i("更新失败：" + e.getMessage() + "\n");
                        getNavigator().handleError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
