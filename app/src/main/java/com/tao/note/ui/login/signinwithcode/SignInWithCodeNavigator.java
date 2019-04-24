package com.tao.note.ui.login.signinwithcode;

/**
 * Created by Tao Zhou on 2019/4/22
 * Package name: com.tao.note.ui.login.signinwithcode
 */
public interface SignInWithCodeNavigator {
    void goBack();

    void requestVerCode();

    void signIn();

    void handleError(Throwable throwable);

    void openMainActivity();
}
