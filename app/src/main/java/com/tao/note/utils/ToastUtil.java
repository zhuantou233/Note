package com.tao.note.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Tao Zhou on 2019/4/12
 * Package name: com.tao.note.utils
 */
public class ToastUtil {
    private Toast toast;
    private static ToastUtil toastUtil;

    private ToastUtil(Context context) {
        toast = Toast.makeText(context.getApplicationContext(), null, Toast.LENGTH_SHORT);
    }

    public static synchronized ToastUtil getInstance(Context context) {
        if (null == toastUtil) {
            toastUtil = new ToastUtil(context);
        }
        return toastUtil;
    }

    public void shortToast(String message) {
        toast.setText(message);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public void longToast(String message) {
        toast.setText(message);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}
