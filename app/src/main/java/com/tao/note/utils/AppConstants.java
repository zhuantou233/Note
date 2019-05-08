package com.tao.note.utils;

/**
 * Created by Tao Zhou on 2019-04-21.
 */
public final class AppConstants {
    public static final String DB_NAME = "note.db";
    public static final String PREF_NAME = "note_pref";
    public static final String DEFAULT_USER_NAME = "default name";
    public static final String DEFAULT_USER_PHONE = "default phone number";

    public static final String[] MONEY_TYPE_LIST = new String[]{"现金", "银行卡", "支付宝", "微信", "其他"};
    public static final String[] RECORD_TYPE_LIST = new String[]{"类型1", "类型2", "类型3", "类型4", "类型5", "其他"};
    public static final String[] INCOME_OR_EXPENSE = new String[]{"收入", "支出"};

    private AppConstants() {
        // This utility class is not publicly instantiable
    }
}
