package com.zyapp.sm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zyapp.sm.R;
import com.zyapp.sm.student.StudentActivity;

public class StudentLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_help;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        //去掉标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //设置状态栏颜色
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorStudentTitle));
        //初始化
        initVIew();
    }

    /**
     *
     */
    private void initVIew() {
        btn_login = findViewById(R.id.btn_student_login);
        tv_help = findViewById(R.id.tv_student_help);
        //注册监听
        btn_login.setOnClickListener(this);
        tv_help.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_student_login:
                startActivity(new Intent(StudentLoginActivity.this, StudentActivity.class));
                break;
            case R.id.tv_student_help:
                new AlertDialog.Builder(this)
                        .setIcon(R.mipmap.icon_student_head)
                        .setTitle("提示")
                        .setMessage("学生账号由教师提供，若没有账号，请联系老师")
                        .setPositiveButton("确定", null)
                        .show();
                break;
        }
    }
}
