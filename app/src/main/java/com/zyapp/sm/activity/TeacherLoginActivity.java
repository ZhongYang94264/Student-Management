package com.zyapp.sm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zyapp.sm.R;
import com.zyapp.sm.teacher.TeacherActivity;

public class TeacherLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_register;
    private Button btn_teacher_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        //去掉标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //设置状态栏颜色
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorTeacherTitle));
        //初始化
        initView();
    }

    /**
     *
     */
    private void initView() {
        tv_register = findViewById(R.id.tv_register);
        btn_teacher_login = findViewById(R.id.btn_teacher_login);
        //注册监听
        tv_register.setOnClickListener(this);
        btn_teacher_login.setOnClickListener(this);
    }

    /**
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_teacher_login:
                startActivity(new Intent(TeacherLoginActivity.this, TeacherActivity.class));
                break;
            case R.id.tv_register:
                startActivity(new Intent(TeacherLoginActivity.this, TeacherRegisterActivity.class));
                break;
        }
    }
}
