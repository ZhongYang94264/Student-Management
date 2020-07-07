package com.zyapp.sm.ui.DBH;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 *  操作数据库
 */
public class DBOperate {
    MySQLiteHelper mysqlite;
    SQLiteDatabase db;

    // 打开数据库
    public void OpenDB(Context context) {
        mysqlite = new MySQLiteHelper(context);
        db = mysqlite.getWritableDatabase();
    }

    // 关闭数据库
    public void CloseDB() {
        if (db.isOpen()) {
            db.close();
        }
    }

    // 输入数据
    public void insertDB(String strinsert) {
        db.execSQL(strinsert);
    }

    // 删除数据
    public void deleteDB(String strdelete) {
        db.execSQL(strdelete);
    }

    // 修改数据
    public void updateDB(String strupdate) {
        db.execSQL(strupdate);
    }

    // 查询数据
    public Cursor selectDB(String strselect) {
        return db.rawQuery(strselect, null);
    }
}
