package com.zyapp.sm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import com.zyapp.sm.teacher.TeacherActivity;

public class TeacherLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_register, tv_F_password;
    private Button btn_teacher_login;
    private EditText et_account, et_pwd;
    private String mStr_work_num;
    private SharedPreferences sp;
    private ImageButton ib_S_password;
    CheckBox cb_T_password;
    private boolean v_flag;

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
        Remember();
    }

    /**
     *
     */
    private void initView() {
        tv_register = findViewById(R.id.tv_register);
        btn_teacher_login = findViewById(R.id.btn_teacher_login);
        et_account = findViewById(R.id.et_admin_account_num);
        et_pwd = findViewById(R.id.et_teacher_password);
        tv_F_password = findViewById(R.id.tv_F_password);
        cb_T_password = findViewById(R.id.cb_T_password);
        ib_S_password = findViewById(R.id.ib_S_password);
        sp = this.getSharedPreferences("user", Context.MODE_PRIVATE);
        //注册监听
        ib_S_password.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        btn_teacher_login.setOnClickListener(this);
        et_account.setOnClickListener(this);
        et_pwd.setOnClickListener(this);
        tv_F_password.setOnClickListener(this);
    }

    private void Remember() {//记住密码
        if (sp.getBoolean("teacher", false)) {
            et_account.setText(sp.getString("key_teacher_user", null));
            et_pwd.setText(sp.getString("key_teacher_pwd", null));
            //默认勾选
            cb_T_password.setChecked(true);
        }
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
                        cursors.close();
                        //勾选记住密码
                        if (cb_T_password.isChecked()) {
                            SharedPreferences.Editor edit = sp.edit();
                            //填写账号密码
                            edit.putString("key_teacher_user", et_account.getText().toString());
                            edit.putString("key_teacher_pwd", et_pwd.getText().toString());
                            edit.putBoolean("teacher", true);//是否勾选
                            //提交事务
                            edit.commit();
                        }
                        //取消勾选
                        else {
                            SharedPreferences.Editor edit = sp.edit();
                            //填写空数据
                            edit.putString("key_teacher_user", "");
                            edit.putString("key_teacher_pwd", "");
                            edit.putBoolean("teacher", false);
                            //题交事务
                            edit.commit();
                        }
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
            case R.id.tv_F_password:
                getDialog("错误", "请找管理员重置密码");
                break;
            case R.id.ib_S_password:
                if (v_flag == true) {
                    et_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ib_S_password.setBackgroundResource(R.mipmap.icon_invisible);
                    v_flag = false;
                } else {
                    et_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ib_S_password.setBackgroundResource(R.mipmap.icon_visible);
                    v_flag = true;
                }
                break;
        }
    }

    public void getDialog(final String title, String str) {
        new AlertDialog.Builder(TeacherLoginActivity.this).setTitle(title)
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
