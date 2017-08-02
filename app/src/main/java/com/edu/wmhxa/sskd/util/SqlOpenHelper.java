package com.edu.wmhxa.sskd.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/5/27.
 */

public class SqlOpenHelper extends SQLiteOpenHelper {

    public SqlOpenHelper(Context context) {
        super(context, "WangLeMa.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        db.execSQL("create table chat( messageid integer primary key, projectid integer, eventcontext varchar(10000), eventbegin datetime, eventend datetime, eventdeletedate datetime,eventvoicesrc varchar(512), eventlevel integer, eventimgsrc varchar(512), eventpwd varchar(200), eventissuccess bool);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
