package com.zyapp.sm.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 操作数据库
 */
public class DBOperate {

    com.zyapp.sm.sql.MySQLiteHelper mysqlite;
    SQLiteDatabase db;

    // 打开数据库
    public void OpenDB(Context context) {
        mysqlite = new com.zyapp.sm.sql.MySQLiteHelper(context);
        db = mysqlite.getWritableDatabase();
    }

    // 关闭数据库
    public void CloseDB() {
        if (db.isOpen()) {
            db.close();
        }
    }

    //增 删 改
    public void operationDB(String strsql) {
        db.execSQL(strsql);
    }

    // 查询数据
    public Cursor selectDB(String strselect) {
        return db.rawQuery(strselect, null);
    }
}
