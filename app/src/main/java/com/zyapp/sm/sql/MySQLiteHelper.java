package com.zyapp.sm.sql;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

    static final String DATABASE = "Info.db"; //数据库名
    static final int VERSION = 1;//版本号

    static final String PERSONS = "persons";

    public MySQLiteHelper(Context context) {
        super(context, MySQLiteHelper.DATABASE, null, MySQLiteHelper.VERSION);
    }

    //当数据库不存在时
    @Override
    public void onCreate(SQLiteDatabase db) {
        personsTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //更新时回调
    }

    //创建人员表
    private void personsTable(SQLiteDatabase db) {

        String CREATE_PERSONS = "create table " + MySQLiteHelper.PERSONS + " (id varchar(10) primary key NOT NULL, " +
                "name varchar(20) NOT NULL, " +
                "password varchar(6) NOT NULL," +
                "school VARCHAR(50)," +
                "department VARCHAR(40)," +
                "profession VARCHAR(30) ," +
                "class VARCHAR(20)," +
                "genre VARCHAR(10) ," +
                "date DATE ) ";

        db.execSQL(CREATE_PERSONS);


        String INSERT_PERSONS = " insert into " +  MySQLiteHelper.PERSONS + " values " +
                "('001','Administrator','000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL)," +
                "('2018000227','罗海艳','227000','女','重庆科创职业学院','人工智能学院','软件技术','Zk1801','在校生','2015-01-07')," +
                "('2018000649','谢丽','649000','女','重庆科创职业学院','人工智能学院','软件技术','ZK1801','在校生','1999-12-01')," +
                "('5411001','卢朝江','100000','男','重庆科创职业学院','人工智能学院',NULL,NULL,NULL,NULL)," +
                "('5411002','罗娜','110000','女','重庆科创职业学院','人工智能学院',NULL,NULL,NULL,NULL) ";

        db.execSQL(INSERT_PERSONS);

    }
}
