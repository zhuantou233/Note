package com.tao.note.data.model.db;

import cn.bmob.v3.BmobObject;

/**
 * Created by Tao Zhou on 2019/4/17
 * Package name: com.tao.note.data.model.db
 */
public class Contact extends BmobObject {
    private String name;
    private String phone;
    private MyUser author;

    public Contact(String name, String phone, MyUser author) {
        this.name = name;
        this.phone = phone;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MyUser getAuthor() {
        return author;
    }

    public void setAuthor(MyUser author) {
        this.author = author;
    }
}
