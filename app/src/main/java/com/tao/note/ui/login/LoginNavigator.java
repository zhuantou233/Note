package com.tao.note.ui.login;

/**
 * Created by Tao Zhou on 2019/4/18
 * Package name: com.tao.note.ui.login
 */
public interface LoginNavigator {
    void handleError(Throwable throwable);

    void openMainActivity();
}
