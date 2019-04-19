package com.tao.note.data;

import com.tao.note.data.local.db.DBHelper;
import com.tao.note.data.local.prefs.PreferencesHelper;
import com.tao.note.data.remote.ApiHelper;

/**
 * Created by Tao Zhou on 2019/4/17
 * Package name: com.tao.note.data
 */
public interface DataManager extends DBHelper, ApiHelper, PreferencesHelper {

}
