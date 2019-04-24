package com.tao.note.ui.login.resetpassword;

/**
 * Created by Tao Zhou on 2019/4/22
 * Package name: com.tao.note.ui.login.resetpassword
 */
public interface ResetPasswordNavigator {
    void goBack();

    void requestVerCode();

    void confirmReset();

    void handleError(Throwable throwable);

    void openMainActivity();
}
