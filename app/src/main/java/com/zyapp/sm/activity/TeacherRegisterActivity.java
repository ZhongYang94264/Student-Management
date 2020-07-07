package com.zyapp.sm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.zyapp.sm.R;

public class TeacherRegisterActivity extends AppCompatActivity {
    EditText  et_register_name,et_teacher_account_num,et_password,et_work_number;//姓名  教师工号  密码  确认密码
    Button btn_teacher_register;//教师按钮
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);
        //去掉标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //设置状态栏颜色
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorTeacher));
        initTeacherRegister();
        setBtnRegister();
    }

    //初始化
    public void initTeacherRegister() {
        et_register_name = findViewById(R.id.et_register_name);
        et_teacher_account_num = findViewById(R.id.et_teacher_account_num);
        et_password = findViewById(R.id.et_password);
        et_work_number = findViewById(R.id.et_work_number);
        btn_teacher_register = findViewById(R.id.btn_teacher_register);
    }
    //监听方法
    public void setBtnRegister(){
        btn_teacher_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherRegisterActivity.this, TeacherLoginActivity.class));
            }
        });
    }
}
