package com.zyapp.sm.teacher.array;

/**
 * @项目名称 Student-Management
 * @包名 com.zyapp.sm.teacher.array
 * @作者 钟阳
 * @类名 classBean
 * @日期 2020/7/10 18:51
 * @描述 TODO
 */
public class classBean {
    public String class_id;
    public String class_name;
    public String class_num;

    public classBean(String class_id, String class_name, String class_num) {
        this.class_id = class_id;
        this.class_name = class_name;
        this.class_num = class_num;
    }

    public String getClass_id() {
        return class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public String getClass_num() {
        return class_num;
    }
}
