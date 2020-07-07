package com.zyapp.sm.DBH;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *  搭建数据库
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    static final String DATABASE = "Info.db"; //数据库名
    static final int VERSION = 1;//版本号

    public MySQLiteHelper(Context context) {
        // TODO Auto-generated constructor stub
        super(context, MySQLiteHelper.DATABASE, null, MySQLiteHelper.VERSION);
    }

    //当数据库不存在时
    @Override
    public void onCreate(SQLiteDatabase db) {
        studentTable(db);
    }

    //创建学生表
    private  void studentTable(SQLiteDatabase db){

    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
