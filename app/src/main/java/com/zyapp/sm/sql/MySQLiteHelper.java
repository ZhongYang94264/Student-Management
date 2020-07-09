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
        useTable(db); //表
        userTable(db);//注册信息表
        adminTable(db);//管理员表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //更新时回调
    }

    private void useTable(SQLiteDatabase db) {
        //创建人员表
        String CREATE_PERSONS = "create table " + sqlData.PERSONS + " (" +
                "_id varchar primary key NOT NULL, " +
                "name varchar NOT NULL, " +
                "password varchar NOT NULL," +
                "sex VARCHAR," +
                "school VARCHAR," +
                "department VARCHAR," +
                "profession VARCHAR ," +
                "class VARCHAR," +
                "genre VARCHAR ) ";

        db.execSQL(CREATE_PERSONS);

                String INSERT_PERSONS = " insert into " + sqlData.PERSONS + "(_id,name,password,sex,school,department,profession,class,genre) values " +
                "('2018000227','罗海艳','227000','女','重庆科创职业学院','人工智能学院','软件技术','Zk1801','在校生')," +
                "('2018000649','谢丽','649000','女','重庆科创职业学院','人工智能学院','软件技术','ZK1801','在校生') ";

        db.execSQL(INSERT_PERSONS);

        //科目表
        String CREATE_LESSONS = "create table " + sqlData.LESSONS + " (" +
                "_lesId integher  PRIMARY KEY NOT NULL ," +
                "lesName VARCHAR , " +
                "lesScore integer DEFAULT '1') ";
        db.execSQL(CREATE_LESSONS);

//        String INSERT_LESSONS = " insert into " + sqlData.LESSONS + " values (1,'java',3),(2,'Android',3),(3,'数据库',4),(4,'unity3D',2),(5,'算法',5),(6,'体育',1) ";
//        db.execSQL(INSERT_LESSONS);

        //学生课程表 + 成绩
        String CREATE_STUDENT_COU = "create table " + sqlData.STUDENT_COU + "(id VARCHAR(10) ,lesId INT(11),stuScore INT(3)) ";
        db.execSQL(CREATE_STUDENT_COU);

        String INSERT_STUDENT_COU = " insert into " + sqlData.STUDENT_COU + " values ('2018000649',1,70),('2018000649',3,75),('2018000227',2,73),('2018000227',4,74) ";
        db.execSQL(INSERT_STUDENT_COU);

        //老师授课表
        String CREATE_TEACHER_LES = "create table " + sqlData.TEACHER_LES + "(lesId INT(11) ,Id VARCHAR(20)) ";
        db.execSQL(CREATE_TEACHER_LES);

//        String INSERT_TEACHER_LES = " insert into " + sqlData.TEACHER_LES + " values (1,'5411001'),(2,'5411002'),(3,'5411001') ";
//        db.execSQL(INSERT_TEACHER_LES);

        //作业表
        String CREATE_WORD = "create table " + sqlData.WORD + "(name VARCHAR(50) ,lesId VARCHAR(30) ,time DATETIME ,id VARCHAR(10) ) ";
        db.execSQL(CREATE_WORD);

//        String INSERT_WORD = " insert into " + sqlData.WORD + " values " +
//                "('Android课后练习','2','2020-07-03 15:21:26','5411002')," +
//                "('冒泡排序','5',NULL,NULL),('创建表，库','3',NULL,'5411001') ";
//        db.execSQL(CREATE_WORD);

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
