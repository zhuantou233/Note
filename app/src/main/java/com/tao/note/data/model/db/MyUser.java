package com.tao.note.data.model.db;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Tao Zhou on 2019/4/9
 * Package name: com.tao.note.data.model
 */
public class MyUser extends BmobUser {
    private String nickName;
    private BmobFile avatar;
    private int accountType;

    public MyUser() {
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public BmobFile getAvatar() {
        return avatar;
    }

    public void setAvatar(BmobFile avatar) {
        this.avatar = avatar;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }
}
