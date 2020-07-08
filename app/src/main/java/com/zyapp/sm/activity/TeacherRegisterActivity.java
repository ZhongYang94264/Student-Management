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
import com.zyapp.sm.sql.DBOperate;

public class TeacherRegisterActivity extends AppCompatActivity {
    EditText et_teacher_name, et_teacher_work_num, et_password, et_teacher_confirm_pwd;//姓名  教师工号  密码  确认密码
    Button btn_teacher_register;//教师按钮
    private String mStr_teacher_name;
    private String mTeacher_work_num;
    private String mStr_password;
    private String mStr_confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);
        //去掉标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //设置状态栏颜色
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorTeacherTitle));
        initTeacherRegister();
        setBtnRegister();
    }

    //初始化
    public void initTeacherRegister() {
        et_teacher_name = findViewById(R.id.et_teacher_name);
        et_teacher_work_num = findViewById(R.id.et_teacher_work_num);
        et_password = findViewById(R.id.et_teacher_password);
        et_teacher_confirm_pwd = findViewById(R.id.et_teacher_confirm_pwd);
        btn_teacher_register = findViewById(R.id.btn_teacher_register);
    }

    //监听方法
    public void setBtnRegister() {
        btn_teacher_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取文本
                mStr_teacher_name = et_teacher_name.getText().toString();//教师姓名
                mTeacher_work_num = et_teacher_work_num.getText().toString();//教师工号
                mStr_password = et_password.getText().toString();//密码
                mStr_confirm_password = et_teacher_confirm_pwd.getText().toString();// 确认密码
                // 是否为空(判断处理信息)
                if (mStr_teacher_name.equals("") || mTeacher_work_num.equals("") || mStr_password.equals("")
                        || mStr_confirm_password.equals("")) {
                    getDialog("错误", "相关数据不能为空");
                    return;
                }
                // 相同密码输入（相同弹出，不同不弹出）
                if (!mStr_password.equals(mStr_confirm_password)) {
                    getDialog("错误", "两次密码输入不匹配");
                    //清除密码
                    et_password.setText("");
                    et_teacher_confirm_pwd.setText("");
                    return;
                }
                //操作数据库

                //跳转
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
                    public void onClick(DialogInterface dialog, int which) {
                        //关闭对话框
                        dialog.dismiss();
                    }
                }).show();
    }

}
