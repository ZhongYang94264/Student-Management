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
                "genre VARCHAR ) ";

        db.execSQL(CREATE_STUDENT);

        String INSERT_STUDENT = " insert into " + sqlData.STUDENT + "(_id,name,password,sex,school,department,proClass,genre) values " +
                "('2018000227','罗海艳','227000','女','重庆科创职业学院','人工智能学院','软件技术Zk1801','在校生')," +
                "('2018000649','谢丽','649000','女','重庆科创职业学院','人工智能学院','软件技术ZK1801','在校生') ";

        db.execSQL(INSERT_STUDENT);
    }

    //学生课程表 + 成绩
    private void scoreTable(SQLiteDatabase db) {
        String CREATE_STUDENT_COU = "create table " + sqlData.STUDENT_COU + "(_id VARCHAR ,lesName VARCHAR,stuScore int(3),teacherName varcher) ";
        db.execSQL(CREATE_STUDENT_COU);

        String INSERT_STUDENT_COU = " insert into " + sqlData.STUDENT_COU + " values " +
                "('2018000649','java基础',70,'罗罗')," +
                "('2018000649','安卓实训',75,'肖肖')," +
                "('2018000227','java基础',73,'罗罗')," +
                "('2018000227','大学英语',74,'李倩') ";
        db.execSQL(INSERT_STUDENT_COU);
    }

//    //老师授课表  课程名，老师id，授课班级
//    private void lesTable(SQLiteDatabase db) {
//        String CREATE_TEACHER_LES = "create table " + sqlData.TEACHER_LES + "(lesName VARCHAR,Id VARCHAR,class VARCHAR) ";
//        db.execSQL(CREATE_TEACHER_LES);
//
//        String INSERT_TEACHER_LES = " insert into " + sqlData.TEACHER_LES + " values " +
//                "('java基础','5411001','软件技术ZK1801')," +
//                "('安卓实训','5411002','软件技术ZK1802')," +
//                "('php开发','5411001','软件技术ZK1801')," +
//                "('大学英语','5411003','软件技术ZK1802')";
//        db.execSQL(INSERT_TEACHER_LES);
//    }
    //作业表
    private void wordTable(SQLiteDatabase db) {
        //作业表
        String CREATE_WORD = "create table " + sqlData.WORD + "(_id VARCHAR ,name VARCHAR,lesName VARCHAR ,time DATETIME ,teacherId VARCHAR ) ";
        db.execSQL(CREATE_WORD);

        String INSERT_WORD = " insert into " + sqlData.WORD + " values " +
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
    }

    private void adminTable(SQLiteDatabase db) {
        //教师注册表
        String create_sql = "create table " + sqlData.ADMIN_TABLE + " (_id integer primary key not null," +
                "password varchar not null," +
                "name varchar)";
        db.execSQL(create_sql);
    }
}
