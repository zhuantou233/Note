package com.tao.note.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Util {
    // String regExp = "^[1](([3|5|8][\\\\d])|([4][5,6,7,8,9])|([6][5,6])|([7][3,4,5,6,7,8])|([9][8,9]))[\\\\d]{8}$";
    // 不准确匹配
    private static final String regExpForPhone = "^((1[345][0-9])|(16[56])|(17[0-8])|(18[0-9])|(19[8-9])|(147,145))\\d{8}$";

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            return mNetworkInfo != null &&
                    mNetworkInfo.isConnectedOrConnecting();
        }
        return false;
    }

    public static boolean isPhoneFormatValid(String phone) {
        Pattern pattern = Pattern.compile(regExpForPhone);
        Matcher matcher = pattern.matcher(phone);
        return !TextUtils.isEmpty(phone) && matcher.matches();
    }

    public static boolean isPasswordFormatValid(String password) {
        return !TextUtils.isEmpty(password);
    }

    public static boolean isCodeFormatValid(String code) {
        return !TextUtils.isEmpty(code);
    }
}
