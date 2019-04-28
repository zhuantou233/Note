package com.tao.note.data;

import com.tao.note.data.local.db.DBHelper;
import com.tao.note.data.local.prefs.PreferencesHelper;
import com.tao.note.data.model.db.MyUser;
import com.tao.note.data.remote.ApiHelper;

import io.reactivex.Observable;

/**
 * Created by Tao Zhou on 2019/4/17
 * Package name: com.tao.note.data
 */
public interface DataManager extends DBHelper, ApiHelper, PreferencesHelper {

    Observable<Boolean> seedAppSettings();

    void setUserAsLoggedOut();

    void updateUserInfo(MyUser user);

    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_LOGGED_IN(1);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }

    enum AccountType {

        ACCOUNT_TYPE_NORMAL(0),
        ACCOUNT_TYPE_ADMIN(1);

        private final int mType;

        AccountType(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }

    }
}
