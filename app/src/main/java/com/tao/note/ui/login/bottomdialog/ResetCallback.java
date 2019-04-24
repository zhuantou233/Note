package com.tao.note.ui.login.bottomdialog;

/**
 * Created by Tao Zhou on 2019/4/24
 * Package name: com.tao.note.ui.login.bottomdialog
 */
public interface ResetCallback {
    void dismissDialog();

    void showSignInWithCodeFragment();

    void showResetPasswordFragment();
}
