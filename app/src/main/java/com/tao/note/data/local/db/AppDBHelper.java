package com.tao.note.data.local.db;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Tao Zhou on 2019/4/17
 * Package name: com.tao.note.data.local.db
 */
@Singleton
public class AppDBHelper implements DBHelper {
    private final AppDatabase mAppDatabase;

    @Inject
    public AppDBHelper(AppDatabase mAppDatabase) {
        this.mAppDatabase = mAppDatabase;
    }
}
