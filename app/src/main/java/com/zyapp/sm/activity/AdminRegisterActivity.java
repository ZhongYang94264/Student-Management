package com.zyapp.sm.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.zyapp.sm.R;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;

public class AdminRegisterActivity extends AppCompatActivity {

    private static final String TAG = "AdminRegisterActivity";
    EditText et_register_code, et_admin_account_num, et_password, et_admin_confirm_pwd;//注册码 管理员账号  密码 确定密码
    Button btn_admin_register;//注册按钮
    private ImageButton ib_help;
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
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorAdminTitle));
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
        ib_help = findViewById(R.id.ib_help);
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
                //1. 打开数据库
                DBOperate dbOperate = new DBOperate();
                dbOperate.OpenDB(AdminRegisterActivity.this);
                //创建查询语句
                String query_sql = "select * from " + sqlData.ADMIN_TABLE + " where _id = '" + mStr_admin_account_num + "'";
                //执行查询语句，返回一个游标
                Cursor cursor = dbOperate.selectDB(query_sql);
                //判断游标数量
                if (cursor.getCount() > 0) {
                    Toast.makeText(AdminRegisterActivity.this, "该账号已被注册", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    //否则就执行插入语句
                    //
                    dbOperate.operationDB("insert into " + sqlData.ADMIN_TABLE+ " (_id,name,password) " +
                            "values ('" + mStr_admin_account_num + "', '" + mStr_register_code + "'," +
                            "'" + mStr_confirm_password + "')");
                    //跳转
                    startActivity(new Intent(AdminRegisterActivity.this, AdminLoginActivity.class));
                    //提示
                    Toast.makeText(AdminRegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    //结束跳转
                    finish();
                }

                //关闭数据库
                dbOperate.CloseDB();
            }
        });
        ib_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog("提示", "注册码由内部开发人员提供");
            }
        });
    }

    // 弹出窗口
    // 自定义方法实现对话框
    public void getDialog(final String title, String str) {
        new AlertDialog.Builder(AdminRegisterActivity.this).setTitle(title)
                .setMessage(str)
                .setIcon(R.mipmap.icon_admin_head)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //关闭对话框
                        dialog.dismiss();
                    }
                }).show();
    }
}