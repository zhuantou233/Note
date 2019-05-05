package com.tao.note.data.model.db;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by Tao Zhou on 2019/4/17
 * Package name: com.tao.note.data.model.db
 */
public class Record extends BmobObject {
    private Float money;                // 金额
    private Float unitPrice;            // 单价
    private Float quantity;             // 数量
    private BmobDate recordDate;        // 记录时间
    private Boolean isIncomeOrExpense;  // 收入还是支出
    private String recordType;          // 支出类型
    private String moneyType;           // 交易方式
    private String extraInfo;           // 备注信息
    private MyUser author;              // 发布者
    private List<BmobFile> photos;      // 照片
    private BmobRelation targets;       // 关联对象，对应contact

    public Record(Float money, Float unitPrice, Float quantity, BmobDate recordDate,
                  Boolean isIncomeOrExpense, String recordType, String moneyType,
                  String extraInfo, MyUser author, List<BmobFile> photos, BmobRelation targets) {
        this.money = money;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.recordDate = recordDate;
        this.isIncomeOrExpense = isIncomeOrExpense;
        this.recordType = recordType;
        this.moneyType = moneyType;
        this.extraInfo = extraInfo;
        this.author = author;
        this.photos = photos;
        this.targets = targets;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public BmobDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(BmobDate recordDate) {
        this.recordDate = recordDate;
    }

    public Boolean getIncomeOrExpense() {
        return isIncomeOrExpense;
    }

    public void setIncomeOrExpense(Boolean incomeOrExpense) {
        isIncomeOrExpense = incomeOrExpense;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public MyUser getAuthor() {
        return author;
    }

    public void setAuthor(MyUser author) {
        this.author = author;
    }

    public List<BmobFile> getPhotos() {
        return photos;
    }

    public void setPhotos(List<BmobFile> photos) {
        this.photos = photos;
    }

    public BmobRelation getTargets() {
        return targets;
    }

    public void setTargets(BmobRelation targets) {
        this.targets = targets;
    }
}
