package com.tao.note.ui.createrecord;

import android.text.TextUtils;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.tao.note.data.DataManager;
import com.tao.note.ui.base.BaseViewModel;
import com.tao.note.utils.Util;
import com.tao.note.utils.rx.SchedulerProvider;

import java.util.Date;

import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by Tao Zhou on 2019/5/6
 * Package name: com.tao.note.ui.createrecord
 */
public class CreateRecordViewModel extends BaseViewModel<CreateRecordNavigator> {

    private final ObservableBoolean isIncomeOrExpense = new ObservableBoolean();

    private final ObservableField<String> moneyType = new ObservableField<>();

    private final ObservableField<String> recordType = new ObservableField<>();

    private final ObservableField<String> unitPrice = new ObservableField<>();

    private final ObservableField<String> quantity = new ObservableField<>();

    private final ObservableField<String> unit = new ObservableField<>();

    private final ObservableField<String> money = new ObservableField<>();

    private final ObservableField<BmobDate> recordDate = new ObservableField<>();

    private final ObservableList<BmobFile> photos = new ObservableArrayList<>();

    private final ObservableField<BmobRelation> targets = new ObservableField<>();

    private final ObservableField<String> extraInfo = new ObservableField<>();

    public CreateRecordViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public ObservableBoolean getIsIncomeOrExpense() {
        return isIncomeOrExpense;
    }

    public ObservableField<String> getMoneyType() {
        return moneyType;
    }

    public ObservableField<String> getRecordType() {
        return recordType;
    }

    public ObservableField<String> getUnitPrice() {
        return unitPrice;
    }

    public ObservableField<String> getQuantity() {
        return quantity;
    }

    public ObservableField<String> getUnit() {
        return unit;
    }

    public ObservableField<String> getMoney() {
        return money;
    }

    public ObservableField<BmobDate> getRecordDate() {
        return recordDate;
    }

    public ObservableList<BmobFile> getPhotos() {
        return photos;
    }

    public ObservableField<BmobRelation> getTargets() {
        return targets;
    }

    public ObservableField<String> getExtraInfo() {
        return extraInfo;
    }


    public void onDataLoad() {
        final BmobDate currentRecordDate = new BmobDate(new Date());
        recordDate.set(currentRecordDate);

        isIncomeOrExpense.set(false);
        moneyType.set("现金");
        recordType.set("类型1");


    }

    public void setIsIncomeOrExpense(Boolean value) {
        isIncomeOrExpense.set(value);
    }

    public void setMoneyType(String type) {
        moneyType.set(type);
    }

    public void setRecordType(String type) {
        recordType.set(type);
    }

    public void setRecordDate(Date date) {
        recordDate.set(new BmobDate(date));
    }

    public void onIncomeOrExpenseClick() {
        getNavigator().showIncomeOrExpenseDialog();
    }

    public void onMoneyTypeClick() {
        getNavigator().showMoneyTypeDialog();
    }

    public void onRecordTypeClick() {
        getNavigator().showRecordTypeDialog();
    }

    public void onRecordDateClick() {
        getNavigator().showRecordDateDialog();
    }

    @BindingAdapter(value = {"money", "unitPrice", "quantity"}, requireAll = false)
    public static void setText(TextView view, String money, String unitPrice, String quantity) {
        if (!TextUtils.isEmpty(money)) {
            view.setText(money);
        } else if (!TextUtils.isEmpty(unitPrice) && !TextUtils.isEmpty(quantity)) {
            Float result = Float.parseFloat(unitPrice) * Float.parseFloat(quantity);
            view.setText(String.valueOf(result));
        } else {
            view.setText("0");
        }
    }

}
