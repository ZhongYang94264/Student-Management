package com.zyapp.sm.teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.zyapp.sm.R;
import com.zyapp.sm.activity.TeacherLoginActivity;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;

public class TeacherUpdatePwdActivity extends AppCompatActivity {

    private static final String TAG = "TeacherUpdate";
    private String mWork_num;
    private EditText et_now_pwd, et_new_pwd, et_confirm_pwd;
    private CheckBox cb_display_pwd;
    private String mStr_now_pwd;
    private String mStr_new_pwd;
    private String mStr_confirm_pwd;
    private boolean v_flag;
    private DBOperate mDbOperate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_update_pwd);
        //去掉标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //设置系统状态栏的颜色
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorTeacher));
        //接收教师工号
        mWork_num = getIntent().getStringExtra("work_num");
        Log.d(TAG, "接收到教师工号==> " + mWork_num);
        //初始化控件
        initView();
    }


    private void initView() {
        et_now_pwd = findViewById(R.id.et_now_pwd);
        et_new_pwd = findViewById(R.id.et_new_pwd);
        et_confirm_pwd = findViewById(R.id.et_confirm_pwd);
        cb_display_pwd = findViewById(R.id.cb_display_pwd);
        //复选框的选择事件
        cb_display_pwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    v_flag = false;
                    et_new_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_confirm_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    v_flag = true;
                    et_new_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et_confirm_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    /**
     * 返回按钮点击事件
     *
     * @param view
     */
    public void backOnClick(View view) {
        this.finish();
    }

    /**
     * 确定按钮点击事件
     *
     * @param view
     */
    public void confirmOnClick(View view) {
        //获取数据
        mStr_now_pwd = et_now_pwd.getText().toString().trim();
        mStr_new_pwd = et_new_pwd.getText().toString().trim();
        mStr_confirm_pwd = et_confirm_pwd.getText().toString().trim();
        //判空
        if (mStr_now_pwd.isEmpty()) {
            Toast.makeText(this, "当前密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mStr_new_pwd.isEmpty()) {
            Toast.makeText(this, "新密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mStr_confirm_pwd.isEmpty()) {
            Toast.makeText(this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        //操作数据库
        mDbOperate = new DBOperate();
        mDbOperate.OpenDB(this);

        Cursor cursor = mDbOperate.selectDB("select * from " + sqlData.TEACHER_TABLE + "" +
                " where password = '" + mStr_now_pwd + "'");
        if (cursor.moveToNext()) {
            if (mStr_now_pwd.equals(mStr_new_pwd)) {
                Toast.makeText(this, "新密码不能与当前密码相同", Toast.LENGTH_SHORT).show();
                return;
            } else if (!mStr_confirm_pwd.equals(mStr_new_pwd)) {
                Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                return;
            } else {
                //操作数据库修改密码
                mDbOperate.operationDB("update " + sqlData.TEACHER_TABLE + " set password = '" + mStr_new_pwd + "'" +
                        " where _id = '" + mWork_num + "'");
                //
                Toast.makeText(this, "修改成功，请重新登录", Toast.LENGTH_SHORT).show();
                //
                Intent back_intent = new Intent(this,TeacherLoginActivity.class);
                back_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back_intent);
                finish();
            }
            cursor.close();
        } else {
            Toast.makeText(this, "当前密码错误", Toast.LENGTH_SHORT).show();
            cursor.close();
            return;
        }

        mDbOperate.CloseDB();
    }
}
