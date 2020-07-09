package com.zyapp.sm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zyapp.sm.R;
import com.zyapp.sm.admin.AdministratorsActivity;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;
import com.zyapp.sm.teacher.TeacherActivity;

public class TeacherLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_register;
    private Button btn_teacher_login;
    private EditText et_account, et_pwd;
    private String mStr_work_num;

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
        et_account = findViewById(R.id.et_admin_account_num);
        et_pwd = findViewById(R.id.et_teacher_password);
        //注册监听
        tv_register.setOnClickListener(this);
        btn_teacher_login.setOnClickListener(this);
        et_account.setOnClickListener(this);
        et_pwd.setOnClickListener(this);
    }

    /**
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_teacher_login:
                //获取数据
                mStr_work_num = et_account.getText().toString();
                String pas = et_pwd.getText().toString();
                //判断  账号   密码
                if ("".equals(mStr_work_num) || "".equals(pas)) {
                    Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                //访问数据库
                DBOperate dbOperate = new DBOperate();
                dbOperate.OpenDB(TeacherLoginActivity.this);
                //调用查询方法，判断用户是否存在
                //创建查询语句
                String login_sql = "select * from " + sqlData.TEACHER_TABLE + " where _id = '" + mStr_work_num + "'";
                //执行查询语句，返回一个游标
                Cursor cursor = dbOperate.selectDB(login_sql);
                //判断游标数量
                if (cursor.getCount() < 1) {
                    Toast.makeText(TeacherLoginActivity.this, "该账号未注册", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    //创建密码的查询语句
                    String pass_sql = "select * from " + sqlData.TEACHER_TABLE + " where _id = '" + mStr_work_num + "' " +
                            "and password = '" + pas + "'";
                    //执行查询语句，返回一个游标
                    Cursor cursors = dbOperate.selectDB(pass_sql);
                    //判断游标数量
                    if (cursors.moveToNext()) {
                        Intent t_a_intent = new Intent(TeacherLoginActivity.this, TeacherActivity.class);
                        t_a_intent.putExtra("work_num", mStr_work_num);
                        startActivity(t_a_intent);
                    } else {
                        Toast.makeText(TeacherLoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //关闭数据库
                    dbOperate.CloseDB();
                }
                break;
            case R.id.tv_register:
                startActivity(new Intent(TeacherLoginActivity.this, TeacherRegisterActivity.class));
                break;
        }
    }
}
