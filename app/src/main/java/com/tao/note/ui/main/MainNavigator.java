package com.tao.note.ui.main;

/**
 * Created by Tao Zhou on 2019/4/22
 * Package name: com.tao.note.ui.main
 */

public interface MainNavigator {

    void handleError(Throwable throwable);

    void openLoginActivity();

    void openProfileActivity();
}