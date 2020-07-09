package com.zyapp.sm.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.zyapp.sm.R;
import com.zyapp.sm.admin.AdministratorsActivity;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;


public class AdminLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_register;
    private Button btn_login;
    private EditText et_login, et_psaa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        //去掉标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //设置状态栏颜色
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorAdminTitle));
        //初始化
        initView();
    }

    /**
     * 初始化方法
     */
    private void initView() {
        tv_register = findViewById(R.id.tv_register);
        btn_login = findViewById(R.id.btn_admin_login);
        btn_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        et_login = findViewById(R.id.et_admin_account_num);
        et_psaa = findViewById(R.id.et_admin_password);
        et_login.setOnClickListener(this);
        et_psaa.setOnClickListener(this);
    }

    /**
     * 实现监听接口
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_admin_login:
                String login = et_login.getText().toString();
                String pass = et_psaa.getText().toString();
                if ("".equals(login) || "".equals(pass)) {
                    Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                //访问数据库
                DBOperate dbOperate = new DBOperate();
                dbOperate.OpenDB(AdminLoginActivity.this);
                //调用查询方法，判断用户是否存在
                //创建查询语句
                String login_sql = "select * from " + sqlData.ADMIN_TABLE + " where _id = '" + login + "'";
                //执行查询语句，返回一个游标
                Cursor cursor = dbOperate.selectDB(login_sql);
                //判断游标数量
                if (cursor.getCount() < 1) {
                    Toast.makeText(AdminLoginActivity.this, "该账号未注册", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    //创建密码的查询语句
                    String pass_sql = "select * from " + sqlData.ADMIN_TABLE + " where _id = '" + login + "' and password = '" + pass + "'";
                    //执行查询语句，返回一个游标
                    Cursor cursors = dbOperate.selectDB(pass_sql);
                    //判断游标数量
                    if (cursors.moveToNext()) {
                        startActivity(new Intent(AdminLoginActivity.this, AdministratorsActivity.class));
                    }
                    else{
                        Toast.makeText(AdminLoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //关闭数据库
                    dbOperate.CloseDB();
                }
                break;
            case R.id.tv_register:
                startActivity(new Intent(AdminLoginActivity.this, AdminRegisterActivity.class));

                break;
        }
    }
}
