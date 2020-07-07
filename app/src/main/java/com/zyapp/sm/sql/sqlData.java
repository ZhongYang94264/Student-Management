package com.zyapp.sm.sql;

/**
 * @项目名称 Student-Management
 * @包名 com.zyapp.sm.sql
 * @作者 钟阳
 * @类名 sqlData
 * @日期 2020/7/7 18:44
 * @描述 这个类用于存放数据库常用字段
 */
class sqlData {

    static final String DATABASE = "Info.db"; //数据库名
    static final int VERSION = 1;//版本号
    static final String PERSONS = "t_persons"; //人员表
    static final String LESSONS = "t_lessons"; //科目表
    static final String STUDENT_COU = "t_student_course"; //学生课程表
    static final String TEACHER_LES = "t_teacher_les"; //老师授课表
    static final String WORD = "t_word"; //作业表
}
