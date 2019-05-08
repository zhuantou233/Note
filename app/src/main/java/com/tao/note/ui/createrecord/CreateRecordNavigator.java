package com.tao.note.ui.createrecord;

/**
 * Created by Tao Zhou on 2019/5/6
 * Package name: com.tao.note.ui.createrecord
 */
public interface CreateRecordNavigator {

    void showIncomeOrExpenseDialog();

    void showMoneyTypeDialog();

    void showRecordTypeDialog();

    void showRecordDateDialog();

    void handleError(Throwable throwable);

}
