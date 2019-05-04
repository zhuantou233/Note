package com.tao.note.utils;

import android.text.TextUtils;
import android.util.Log;

import com.orhanobut.logger.Logger;

public class L {
    private static String TAG = "MyNote";

    public static void init(String tag) {
        if (TextUtils.isEmpty(tag)) {
            return;
        }
        TAG = tag;
    }

    /**
     * log.d
     *
     * @param msg
     */
    public static void d(Object msg) {
        if (Util.isDebug()) {
            Logger.d(msg);
        }
    }

    /**
     * log.e
     *
     * @param msg
     */
    public static void e(String msg) {
        if (Util.isDebug()) {
            Logger.e(msg);
        }
    }

    /**
     * log.e
     *
     * @param msg
     */
    public static void e(String msg, Throwable throwable) {
        if (Util.isDebug()) {
            Logger.e(msg, throwable);
        }
    }

    /**
     * log.w
     *
     * @param msg
     */
    public static void w(String msg) {
        if (Util.isDebug()) {
            Logger.w(msg);
        }
    }

    /**
     * Log.i
     *
     * @param msg
     */
    public static void i(String msg) {
        if (Util.isDebug()) {
            Logger.i(msg);
        }
    }

    /**
     * log.v
     *
     * @param msg
     */
    public static void v(String msg) {
        if (Util.isDebug()) {
            Logger.v(msg);
        }
    }
}
