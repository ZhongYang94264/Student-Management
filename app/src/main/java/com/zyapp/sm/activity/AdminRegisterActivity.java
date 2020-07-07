package com.zyapp.sm.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.zyapp.sm.R;

public class AdminRegisterActivity extends AppCompatActivity {
    EditText et_register_code, et_admin_account_num, et_passwordone, et_confirm_password;//注册码 管理员账号  密码 确定密码

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
        setBtnRegister();
    }


    //初始化
    public void initadminRegister() {
        et_register_code = findViewById(R.id.et_register_code);
        et_admin_account_num = findViewById(R.id.et_admin_account_num);
        et_passwordone = findViewById(R.id.et_passwordone);
        et_confirm_password = findViewById(R.id.et_confirm_password);
        btn_admin_register = findViewById(R.id.btn_admin_register);

    }

    //监听方法
    public void setBtnRegister() {
        btn_admin_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
                // 获取文本
                String name = et_register_code.getText().toString();//注册码
                String admin_account_num = et_admin_account_num.getText().toString();//  管理员账号
                String passwordone = et_passwordone.getText().toString();//密码
                String confirm_password = et_confirm_password.getText().toString();// 确认密码

                // 是否为空(判断处理信息)
                if (admin_account_num.equals("") || passwordone.equals("")
                        || confirm_password.equals("")) {

                    getDialog("错误", "输入密码和账号不能为空");
                    return;
                }
                // 相同密码输入（相同弹出，不同不弹出）
                if (!passwordone.equals(confirm_password)) {
                    getDialog("错误", "两次密码输入不匹配");
                    return;
                }

                startActivity(new Intent(AdminRegisterActivity.this, AdminLoginActivity.class));
            }
        });
    }

    // 弹出窗口
    // 自定义方法实现对话框
    public void getDialog(final String title, String str) {
        new AlertDialog.Builder(AdminRegisterActivity.this).setTitle(title)
                .setMessage(str)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub

                        AdminRegisterActivity.this.finish();

                    }
                }).show();
    }
}