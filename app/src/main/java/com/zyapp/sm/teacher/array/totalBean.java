package com.zyapp.sm.teacher.array;

/**
 * @项目名称 Student-Management
 * @包名 com.zyapp.sm.teacher.array
 * @作者 钟阳
 * @类名 studentBean
 * @日期 2020/7/10 15:14
 * @描述 TODO
 */
public class totalBean {
    private String student_id;
    private int order;
    private double total;

    public totalBean(String student_id, int order, double total) {
        this.student_id = student_id;
        this.order = order;
        this.total = total;
    }

    public String getStudent_id() {
        return student_id;
    }

    public int getOrder() {
        return order;
    }

    public double getTotal() {
        return total;
    }
}
