package com.zyapp.sm.teacher.array;

/**
 * @项目名称 Student-Management
 * @包名 com.zyapp.sm.teacher.array
 * @作者 钟阳
 * @类名 workBean
 * @日期 2020/7/12 11:15
 * @描述 TODO
 */
public class workBean {
    private String work_name;
    private String publish_time;
    private String les_name;

    public workBean(String work_name, String publish_time, String les_name) {
        this.work_name = work_name;
        this.publish_time = publish_time;
        this.les_name = les_name;
    }

    public String getWork_name() {
        return work_name;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public String getLes_name() {
        return les_name;
    }
}
