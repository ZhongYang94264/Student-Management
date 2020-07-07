package com.zyapp.sm.activity;

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

public class TeacherRegisterActivity extends AppCompatActivity {
    EditText et_register_name, et_teacher_account_num, et_password, et_work_number;//姓名  教师工号  密码  确认密码
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
    public void initTeacherRegister( ) {
        et_register_name = findViewById(R.id.et_register_name);
        et_teacher_account_num = findViewById(R.id.et_teacher_account_num);
        et_password = findViewById(R.id.et_password);
        et_work_number = findViewById(R.id.et_work_number);
        btn_teacher_register = findViewById(R.id.btn_teacher_register);
    }

    //监听方法
    public void setBtnRegister() {
        btn_teacher_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
                // 获取文本
                String name = et_register_name.getText().toString();//注册码
                String teacher_account_num = et_teacher_account_num.getText().toString();//  管理员账号
                String password = et_password.getText().toString();//密码
                String confirm_password = et_work_number.getText().toString();// 确认密码

                // 是否为空(判断处理信息)
                if (teacher_account_num.equals("") || password.equals("")
                        || confirm_password.equals("")) {

                    getDialog("错误", "输入密码和账号不能为空");
                    return;
                }
                // 相同密码输入（相同弹出，不同不弹出）
                if (!password.equals(confirm_password)) {
                    getDialog("错误", "两次密码输入不匹配");
                    return;
                }

                startActivity(new Intent(TeacherRegisterActivity.this, TeacherLoginActivity.class));
            }
        });
    }

    // 弹出窗口
    // 自定义方法实现对话框
    public void getDialog(final String title, String str) {
        new AlertDialog.Builder(TeacherRegisterActivity.this).setTitle(title)
                .setMessage(str)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub

                        TeacherRegisterActivity.this.finish();

                    }
                }).show();
    }

}
