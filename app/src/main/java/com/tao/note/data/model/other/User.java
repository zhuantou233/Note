package com.tao.note.data.model.other;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Tao Zhou on 2019/4/22
 * Package name: com.tao.note.data.model.other
 */
@Entity(tableName = "users")
public class User {

    @ColumnInfo(name = "created_at")
    public String createdAt;

    @PrimaryKey
    public Long id;

    public String name;

    @ColumnInfo(name = "updated_at")
    public String updatedAt;
}
