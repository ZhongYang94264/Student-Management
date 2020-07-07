package com.zyapp.sm.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.zyapp.sm.R;

public class AdminRegisterActivity extends AppCompatActivity {

    private static final String TAG = "AdminRegisterActivity";
    EditText et_register_code, et_admin_account_num, et_password, et_admin_confirm_pwd;//注册码 管理员账号  密码 确定密码
    Button btn_admin_register;//注册按钮
    private String mStr_register_code, mStr_admin_account_num, mStr_password, mStr_confirm_password;

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
        //初始化管理员注册
        initadminRegister();
        //监听事件
        setBtnRegister();
    }


    //初始化
    public void initadminRegister() {
        et_register_code = findViewById(R.id.et_register_code);
        et_admin_account_num = findViewById(R.id.et_admin_account_num);
        et_password = findViewById(R.id.et_teacher_password);
        et_admin_confirm_pwd = findViewById(R.id.et_admin_confirm_pwd);
        btn_admin_register = findViewById(R.id.btn_admin_register);
    }

    //监听方法
    public void setBtnRegister() {
        btn_admin_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "点击了注册按钮");
                // TODO Auto-generated method stub
                // 获取文本
                mStr_register_code = et_register_code.getText().toString();//注册码
                mStr_admin_account_num = et_admin_account_num.getText().toString();//  管理员账号
                mStr_password = et_password.getText().toString();//密码
                mStr_confirm_password = et_admin_confirm_pwd.getText().toString();// 确认密码

                // 是否为空(判断处理信息)
                if (mStr_register_code.equals("") || mStr_admin_account_num.equals("") || mStr_password.equals("") ||
                        mStr_confirm_password.equals("")) {
                    getDialog("错误", "相关数据不能为空");
                    return;
                }
                // 相同密码输入（相同弹出，不同不弹出）
                if (!mStr_password.equals(mStr_confirm_password)) {
                    getDialog("错误", "两次密码输入不匹配");
                    return;
                }
                //这里把注册码写死——959570
                if (!mStr_register_code.equals("959570")) {
                    getDialog("错误", "注册码错误");
                    //清空输入框
                    et_register_code.setText("");
                    return;
                }
                //操作数据库

                //跳转
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
                    public void onClick(DialogInterface dialog, int which) {
                        //关闭对话框
                        dialog.dismiss();
                    }
                }).show();
    }
}