package com.zyapp.sm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.zyapp.sm.activity.AdminLoginActivity;
import com.zyapp.sm.activity.StudentLoginActivity;
import com.zyapp.sm.activity.TeacherLoginActivity;

public class SelectLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_admin_head, iv_teacher_head, iv_student_head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_login);
        //去掉标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //去掉系统栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //初始化
        initView();
    }

    /**
     * 这个方法用于初始化
     */
    private void initView() {
        iv_admin_head = findViewById(R.id.iv_admin_head);
        iv_teacher_head = findViewById(R.id.iv_teacher_head);
        iv_student_head = findViewById(R.id.iv_student_head);
        //设置监听
        iv_admin_head.setOnClickListener(this);
        iv_teacher_head.setOnClickListener(this);
        iv_student_head.setOnClickListener(this);
    }

    /**
     * 实现监听接口,实现的方法
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_admin_head:
                //跳转到管理员登录界面
                startActivity(new Intent(SelectLoginActivity.this, AdminLoginActivity.class));
                break;
            case R.id.iv_teacher_head:
                startActivity(new Intent(SelectLoginActivity.this, TeacherLoginActivity.class));
                //跳转到教师登录界面
                break;
            case R.id.iv_student_head:
                startActivity(new Intent(SelectLoginActivity.this, StudentLoginActivity.class));
                //跳转到学生登录界面
                break;
        }
    }
}
