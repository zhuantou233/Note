package com.tao.note.data.local.db;

import com.tao.note.data.local.db.dao.ContactDao;
import com.tao.note.data.local.db.dao.MyUserDao;
import com.tao.note.data.local.db.dao.RecordDao;
import com.tao.note.data.model.db.Contact;
import com.tao.note.data.model.db.MyUser;
import com.tao.note.data.model.db.Record;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Created by Tao Zhou on 2019/4/17
 * Package name: com.tao.note.data.local.db
 */
//@Database(entities = {MyUser.class, Contact.class, Record.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MyUserDao optionDao();

    public abstract ContactDao questionDao();

    public abstract RecordDao userDao();
}
