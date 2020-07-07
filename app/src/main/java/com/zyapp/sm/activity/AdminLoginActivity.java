package com.zyapp.sm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.zyapp.sm.R;
import com.zyapp.sm.admin.AdministratorsActivity;


public class AdminLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_register;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        //去掉标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //设置状态栏颜色
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorAdmin));
        //初始化
        initView();
    }

    /**
     * 初始化方法
     */
    private void initView() {
        tv_register = findViewById(R.id.tv_register);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }

    /**
     * 实现监听接口
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                startActivity(new Intent(AdminLoginActivity.this, AdministratorsActivity.class));
                break;
            case R.id.tv_register:
                startActivity(new Intent(AdminLoginActivity.this, AdminRegisterActivity.class));

                break;
        }
    }
}
