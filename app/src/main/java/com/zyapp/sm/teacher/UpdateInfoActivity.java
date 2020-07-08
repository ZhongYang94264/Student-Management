package com.zyapp.sm.teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zyapp.sm.R;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;

import java.util.Calendar;

public class UpdateInfoActivity extends AppCompatActivity {

    private static final String TAG = "UpdateInfoActivity";
    private Spinner sp_department, sp_curriculum, sp_gender, sp_school;
    private TextView tv_birth;
    private EditText et_tel, et_email;
    private int now_year, now_month, now_day;
    private String mStr_birth;
    private String mStr_tel;
    private String mStr_email;
    private String mWork_num;
    private DBOperate mDbOperate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        //去掉标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //修改系统状态栏的颜色
        this.getWindow().setStatusBarColor(ContextCompat.getColor(UpdateInfoActivity.this, R.color.colorTeacher));
        //初始化组件
        initView();
        //接收工号
        mWork_num = getIntent().getStringExtra("work_num");
        Log.d(TAG, "接收到工号==> " + mWork_num);
        //打开数据库
        mDbOperate = new DBOperate();
        //给Spinner组件填充数据
//        initSpinner();
    }

    /**
     *
     */
    private void initSpinner() {
        String data[] = {"重庆科创职业学院", "重庆财经职业学院", "重庆水电职业学院", "重庆文理学院", "重庆科技职业学院"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, data);
        sp_school.setAdapter(adapter);
    }

    /**
     *
     */
    private void initView() {
        sp_school = findViewById(R.id.sp_school);
        sp_department = findViewById(R.id.sp_department);
        sp_curriculum = findViewById(R.id.sp_curriculum);
        sp_gender = findViewById(R.id.sp_gender);
        tv_birth = findViewById(R.id.tv_birth);
        et_tel = findViewById(R.id.et_tel);
        et_email = findViewById(R.id.et_email);
    }

    /**
     * 生日字体的点击事件
     *
     * @param view
     */
    public void selectBirth(View view) {
        //获取当前日
        Calendar calendar = Calendar.getInstance();
        now_year = calendar.get(Calendar.YEAR);
        now_month = calendar.get(Calendar.MONTH);
        now_day = calendar.get(Calendar.DAY_OF_MONTH);
        //弹出日期选择对话框
        new DatePickerDialog(UpdateInfoActivity.this, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //设置文本显示内容
                tv_birth.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        }, now_year, now_month, now_day).show();
    }

    /**
     * 确定按钮的点击事件
     *
     * @param view
     */
    public void confirmOnClick(View view) {
        Log.d(TAG, "点击了确定按钮");
        //获取数据
        mStr_birth = tv_birth.getText().toString();
        mStr_tel = et_tel.getText().toString();
        mStr_email = et_email.getText().toString();
        //判空
        if (sp_department.equals("请选择您的所属院系")) {
            Toast.makeText(this, "请选择院校", Toast.LENGTH_SHORT).show();
            return;
        }
        if (sp_curriculum.equals("请选择您的授课课程")) {
            Toast.makeText(this, "请选择授课课程", Toast.LENGTH_SHORT).show();
            return;
        }
        if (sp_gender.equals("请选择您的性别")) {
            Toast.makeText(this, "请选择性别", Toast.LENGTH_SHORT).show();
            return;
        }
        if (tv_birth.getText().toString().equals("请选择您的生日")) {
            Toast.makeText(this, "请选择生日", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mStr_tel.isEmpty()) {
            Toast.makeText(this, "请输入您的电话号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mStr_email.isEmpty()) {
            Toast.makeText(this, "请输入您的邮件地址", Toast.LENGTH_SHORT).show();
            return;
        }
        //操作数据库
        mDbOperate.OpenDB(this);
        //修改数据库中的数据
        String update_sql = "update " + sqlData.TEACHER_TABLE + " set " +
                "school = '" + sp_school.getSelectedItem().toString() + "' " +
                ",department = '" + sp_department.getSelectedItem().toString() + "' " +
                ",curriculum = '" + sp_curriculum.getSelectedItem().toString() + "' " +
                ",gender = '" + sp_gender.getSelectedItem().toString() + "'" +
                ",birthday = '" + mStr_birth + "'" +
                ",tel = '" + mStr_tel + "' " +
                ",email = '" + mStr_email + "' where _id = '" + mWork_num + "'";
        //
        mDbOperate.operationDB(update_sql);
        //
        mDbOperate.CloseDB();
        //
        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    /**
     * 返回点击事件
     *
     * @param view
     */
    public void backOnClick(View view) {
        this.finish();
    }
}
