package com.tao.note.ui.login.signin;

/**
 * Created by Tao Zhou on 2019/4/22
 * Package name: com.tao.note.ui.login.signin
 */
public interface SignInNavigator {
    void signIn();

    void showSignUpFragment();

    void showBottomSheetDialog();

    void handleError(Throwable throwable);

    void openMainActivity();
}
