package com.zyapp.sm.teacher.array;

/**
 * @项目名称 Student-Management
 * @包名 com.zyapp.sm.teacher.array
 * @作者 钟阳
 * @类名 studentBean
 * @日期 2020/7/10 15:14
 * @描述 TODO
 */
public class studentBean {
    public String student_id;
    public String student_name;
    public String gender;
    public String school;
    public String department;
    public String class_name;
    public String type;

    public studentBean(String student_id, String student_name, String gender, String school,
                       String department, String class_name, String type) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.gender = gender;
        this.school = school;
        this.department = department;
        this.class_name = class_name;
        this.type = type;
    }

    public String getStudent_id() {
        return student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public String getGender() {
        return gender;
    }

    public String getSchool() {
        return school;
    }

    public String getDepartment() {
        return department;
    }

    public String getClass_name() {
        return class_name;
    }

    public String getType() {
        return type;
    }
    }

