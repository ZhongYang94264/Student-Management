package com.zyapp.sm.sql;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public MySQLiteHelper(Context context) {
        super(context, sqlData.DATABASE, null, sqlData.VERSION);
    }

    //当数据库不存在时
    @Override
    public void onCreate(SQLiteDatabase db) {
        studentTable(db); //学生表
        userTable(db);//注册信息表
        adminTable(db);//管理员表
        scoreTable(db);
        wordTable(db);
        totalTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //更新时回调
    }

    //学生表
    private void studentTable(SQLiteDatabase db) {
        String CREATE_STUDENT = "create table " + sqlData.STUDENT + " (" +
                "_id varchar primary key NOT NULL, " +
                "name varchar NOT NULL, " +
                "password varchar NOT NULL," +
                "sex VARCHAR," +
                "school VARCHAR," +
                "department VARCHAR," +
                "proClass VARCHAR ," +
                "genre VARCHAR," +
                "teacher_id ) ";

        db.execSQL(CREATE_STUDENT);
    }

    //学生课程表 + 成绩
    private void scoreTable(SQLiteDatabase db) {
        String CREATE_STUDENT_COU = "create table " + sqlData.STUDENT_COU + "(_id VARCHAR ," +
                "lesName VARCHAR,stuScore integer,teacherName varchar,teacher_id varchar) ";
        db.execSQL(CREATE_STUDENT_COU);
    }

    //总分表
    private void totalTable(SQLiteDatabase db) {
        String CRETE_TOTAL = "create table " + sqlData.TOTAL + " (stuId VARCHAR primary key, total VARCHAR)";
        db.execSQL(CRETE_TOTAL);

        String INSERT_TOTAL = "insert into " + sqlData.TOTAL + " values " +
                "('2018000649','70')," +
                "('2018000227','72')";
        db.execSQL(INSERT_TOTAL);

    }

    //作业表
    private void wordTable(SQLiteDatabase db) {
        //作业表
        String CREATE_WORD = "create table " + sqlData.WORK + "(_id VARCHAR ," +
                "name VARCHAR," +
                "lesName VARCHAR ," +
                "time DATETIME ," +
                "teacherId VARCHAR ) ";
        db.execSQL(CREATE_WORD);

        String INSERT_WORD = " insert into " + sqlData.WORK + " values " +
                "('2018000649','Android课后练习','安卓实训','2020-07-03 15:21:26','5411002')," +
                "('2018000649','冒泡排序','java基础','2020-07-03 15:21:26','541101')," +
                "('2018000227','java课后练习','java基础','2020-07-03 15:21:26','5411001') ";
        db.execSQL(INSERT_WORD);

    }

    /**
     * 这个方法是注册信息时的表
     */
    private void userTable(SQLiteDatabase db) {
        //教师注册表
        String create_sql = "create table " + sqlData.TEACHER_TABLE + " (_id integer primary key not null," +
                "name varchar not null," +
                "password varchar not null," +
                "school varchar," +
                "department varchar," +
                "curriculum varchar," +
                "gender varchar," +
                "birthday varchar," +
                "tel varchar," +
                "email varchar)";
        db.execSQL(create_sql);
        //教师班级表
        String create_class_sql = "create table " + sqlData.CLASS_TABLE + " (_id varchar primary key not null," +
                "class_name varchar," +
                "class_num varchar," +
                "teacher_id varchar)";
        db.execSQL(create_class_sql);
    }

    private void adminTable(SQLiteDatabase db) {
        //管理员注册表
        String create_sql = "create table " + sqlData.ADMIN_TABLE + " (_id integer primary key not null," +
                "password varchar not null," +
                "name varchar)";
        db.execSQL(create_sql);
    }
}
