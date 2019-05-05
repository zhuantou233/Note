package com.tao.note.ui.accountdetail.changepassword;

/**
 * Created by Tao Zhou on 2019/5/5
 * Package name: com.tao.note.ui.accountdetail.changepassword
 */
public interface ChangeUserPasswordCallback {
    void dismissDialog();

    void handleError(Throwable throwable);

    void confirmReset();
}
