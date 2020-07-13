package com.zyapp.sm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.DialogCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.ImageButton;

import android.widget.TextView;
import android.widget.Toast;

import com.zyapp.sm.R;


import com.zyapp.sm.admin.AdministratorsActivity;

import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;
import com.zyapp.sm.student.StudentActivity;
import com.zyapp.sm.teacher.TeacherActivity;

public class StudentLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "StudentLoginActivity";
    private TextView tv_help;
    private Button btn_login;
    private ImageButton ib_S_password;
    private EditText et_student, et_password;
    private TextView tv_F_password;
    private CheckBox cb_R_password;

    private SharedPreferences sp;
    private boolean v_flag;

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
        Remember();


    }

    /**
     *
     */
    @SuppressLint("WrongViewCast")
    private void initVIew() {
        btn_login = findViewById(R.id.btn_student_login);
        tv_help = findViewById(R.id.tv_student_help);
        et_student = findViewById(R.id.et_s_student_id);
        et_password = findViewById(R.id.et_student_password);
        tv_F_password = findViewById(R.id.tv_F_password);
        cb_R_password = findViewById(R.id.cb_R_password);
        ib_S_password = findViewById(R.id.ib_S_password);
        sp = this.getSharedPreferences("user", Context.MODE_PRIVATE);

        //注册监听
        btn_login.setOnClickListener(this);
        tv_help.setOnClickListener(this);
        tv_F_password.setOnClickListener(this);
        ib_S_password.setOnClickListener(this);
    }

    private void Remember() {//记住密码
        if (sp.getBoolean("student", false)) {
            et_student.setText(sp.getString("key_student_user", null));
            et_password.setText(sp.getString("key_student_pwd", null));
            //默认勾选
            cb_R_password.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_student_login:

                String student = et_student.getText().toString();
                String password = et_password.getText().toString();
                Log.d(TAG, "学生登录界面输入的学号==> " + student);
                if ("".equals(student) || "".equals(password)) {
                    Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                //访问数据库
                DBOperate dbOperate = new DBOperate();
                dbOperate.OpenDB(StudentLoginActivity.this);
                //调用查询方法，判断用户是否存在
                //创建查询语句
                String login_sql = "select * from " + sqlData.STUDENT + " where _id = '" + student + "'";
                //执行查询语句，返回一个游标
                Cursor cursor = dbOperate.selectDB(login_sql);
                //判断游标数量
                if (cursor.getCount() < 1) {
                    Toast.makeText(StudentLoginActivity.this, "该账号未注册", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    //创建密码的查询语句
                    String pass_sql = "select * from " + sqlData.STUDENT + " where _id = '" + student + "' and password = '" + password + "'";
                    //执行查询语句，返回一个游标
                    Cursor cursors = dbOperate.selectDB(pass_sql);
                    //判断游标数量
                    if (cursors.moveToNext()) {
                        Intent s_intent = new Intent(StudentLoginActivity.this, StudentActivity.class);
                        s_intent.putExtra("student_id", student);
                        startActivity(s_intent);
                        cursor.close();
                        Toast.makeText(this, "账号密码正确", Toast.LENGTH_SHORT).show();
                        //勾选记住密码
                        if (cb_R_password.isChecked()) {
                            SharedPreferences.Editor edit = sp.edit();
                            //填写账号密码
                            edit.putString("key_student_user", et_student.getText().toString());
                            edit.putString("key_student_pwd", et_password.getText().toString());
                            edit.putBoolean("student", true);//是否勾选
                            //提交事务
                            edit.apply();
                        }
                        //取消勾选
                        else {
                            SharedPreferences.Editor edit = sp.edit();
                            //填写空数据
                            edit.putString("key_student_user", "");
                            edit.putString("key_student_pwd", "");
                            edit.putBoolean("student", false);
                            //题交事务
                            edit.apply();
                        }
                    } else {
                        Toast.makeText(StudentLoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //关闭数据库
                    dbOperate.CloseDB();
                }

                break;
            case R.id.tv_student_help:
                new AlertDialog.Builder(this)
                        .setIcon(R.mipmap.icon_student_head)
                        .setTitle("提示")
                        .setMessage("学生账号由教师提供，若没有账号，请联系老师")
                        .setPositiveButton("确定", null)
                        .show();
                break;
            case R.id.tv_F_password:
                getDialog("错误", "请找管理员重置密码");
                break;
            case R.id.ib_S_password:
                if (v_flag == true) {
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ib_S_password.setBackgroundResource(R.mipmap.icon_invisible);
                    v_flag = false;
                } else {
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ib_S_password.setBackgroundResource(R.mipmap.icon_visible);
                    v_flag = true;
                }
                break;
        }
    }

    public void getDialog(final String title, String str) {
        new AlertDialog.Builder(StudentLoginActivity.this).setTitle(title)
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

