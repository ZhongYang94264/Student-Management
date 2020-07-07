package com.zyapp.sm.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.zyapp.sm.R;

public class AdminRegisterActivity extends AppCompatActivity {
    EditText et_register_code, et_admin_account_num, et_passwordone, et_confirm_password;//注册码，管理员账号， 密码，确定密码

    Button btn_admin_register;//注册按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);
        //去掉标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //设置状态栏颜色
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorAdmin));

        initadminRegister();
    }


    //初始化
    public void initadminRegister() {
        et_register_code = findViewById(R.id.et_register_code);
        et_admin_account_num = findViewById(R.id.et_admin_account_num);
        et_passwordone = findViewById(R.id.et_passwordone);
        et_confirm_password= findViewById(R.id.et_confirm_password);
        btn_admin_register = findViewById(R.id.btn_admin_register);
    }
}