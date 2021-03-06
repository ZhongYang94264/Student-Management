package com.zyapp.sm.sql;

/**
 * @项目名称 Student-Management
 * @包名 com.zyapp.sm.sql
 * @作者 钟阳
 * @类名 sqlData
 * @日期 2020/7/7 18:44
 * @描述 这个类用于存放数据库常用字段
 */
public class sqlData {

    public static final String DATABASE = "Info.db"; //数据库名
    public static final int VERSION = 1;//版本号
    public static final String STUDENT = "t_student"; //学生表
    public static final String STUDENT_COU = "t_student_course"; //学生课程表
    public static final String TOTAL = "t_total"; //学生总分表
    public static final String WORK = "t_word"; //作业表
    public static final String TEACHER_TABLE = "t_teacher";//教师表
    public static final String CLASS_TABLE = "t_class";
    public static final String ADMIN_TABLE = "t_admin";
}
