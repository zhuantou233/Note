package com.tao.note.ui.login.signup;

/**
 * Created by Tao Zhou on 2019/4/22
 * Package name: com.tao.note.ui.login.signup
 */
public interface SignUpNavigator {
    void goBack();

    void requestVerCode();

    void signUp();

    void handleError(Throwable throwable);

    void openMainActivity();
}
