package com.zyapp.sm.sql;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

    static final String DATABASE = "Info.db"; //数据库名
    static final int VERSION = 1;//版本号

    static final String PERSONS = "persons"; //人员表
    static final String LESSONS = "lessons"; //科目表
    static final String STUDENT_COU = "student_cou"; //学生课程表
    static final String TEACHER_LES = "teacher_les"; //老师授课表
    static final String WORD = "word"; //作业表



    public MySQLiteHelper(Context context) {
        super(context, MySQLiteHelper.DATABASE, null, MySQLiteHelper.VERSION);
    }

    //当数据库不存在时
    @Override
    public void onCreate(SQLiteDatabase db) {
        personsTable(db);
        useTable(db); //表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //更新时回调
    }

    //创建人员表
    private void personsTable(SQLiteDatabase db) {

        String CREATE_PERSONS = "create table " + MySQLiteHelper.PERSONS + " (" +
                "id varchar(10) primary key NOT NULL, " +
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

    //通用的表
    private void useTable(SQLiteDatabase db) {
        //科目表
        String CREATE_LESSONS = "create table " + MySQLiteHelper.LESSONS + " (" +
                "lesId INT(5)  PRIMARY KEY NOT NULL ," +
                "lesName VARCHAR(30) , " +
                "lesScore INT(2) DEFAULT '1') ";
        db.execSQL(CREATE_LESSONS);

        String INSERT_LESSONS = " insert into " +  MySQLiteHelper.LESSONS + " values (1,'java',3),(2,'Android',3),(3,'数据库',4),(4,'unity3D',2),(5,'算法',5),(6,'体育',1) ";
        db.execSQL(INSERT_LESSONS);

        //学生课程表
        String  CREATE_STUDENT_COU = "create table " + MySQLiteHelper.STUDENT_COU + "(id VARCHAR(10) ,lesId INT(11),stuScore INT(3)) ";
        db.execSQL(CREATE_STUDENT_COU);

        String INSERT_STUDENT_COU = " insert into " +  MySQLiteHelper.STUDENT_COU + " values ('2018000649',1,70),('2018000649',3,75),('2018000227',2,73),('2018000227',4,74) ";
        db.execSQL(INSERT_STUDENT_COU);

        //老师授课表
        String  CREATE_TEACHER_LES = "create table " + MySQLiteHelper.TEACHER_LES + "(lesId INT(11) ,Id VARCHAR(20)) ";
        db.execSQL(CREATE_TEACHER_LES);

        String INSERT_TEACHER_LES = " insert into " +  MySQLiteHelper.TEACHER_LES + " values (1,'5411001'),(2,'5411002'),(3,'5411001') ";
        db.execSQL(INSERT_TEACHER_LES);

        //作业表
        String  CREATE_WORD = "create table " + MySQLiteHelper.WORD + "(name VARCHAR(50) ,lesId VARCHAR(30) ,time DATETIME ,id VARCHAR(10) ) ";
        db.execSQL(CREATE_WORD);

        String INSERT_WORD = " insert into " +  MySQLiteHelper.WORD + " values " +
                "('Android课后练习','2','2020-07-03 15:21:26','5411002')," +
                "('冒泡排序','5',NULL,NULL),('创建表，库','3',NULL,'5411001') ";
        db.execSQL(CREATE_WORD);

    }
}
