package com.tao.note.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.tao.note.di.PreferenceInfo;

import javax.inject.Inject;

/**
 * Created by Tao Zhou on 2019/4/17
 * Package name: com.tao.note.data.local.prefs
 */
public class AppPreferencesHelper implements PreferencesHelper {
    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }
}
