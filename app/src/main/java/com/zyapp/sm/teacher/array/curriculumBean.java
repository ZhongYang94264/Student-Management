package com.zyapp.sm.teacher.array;

/**
 * @项目名称 Student-Management
 * @包名 com.zyapp.sm.teacher.array
 * @作者 钟阳
 * @类名 curriculumBean
 * @日期 2020/7/12 15:18
 * @描述 TODO
 */
public class curriculumBean {
    private String teacherName;
    private String curriculumName;
    private String createTime;

    public curriculumBean(String teacherName, String curriculumName, String createTime) {
        this.teacherName = teacherName;
        this.curriculumName = curriculumName;
        this.createTime = createTime;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getCurriculumName() {
        return curriculumName;
    }

    public String getCreateTime() {
        return createTime;
    }
}
