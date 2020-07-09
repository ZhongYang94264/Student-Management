package com.zyapp.sm.teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zyapp.sm.R;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;
import com.zyapp.sm.teacher.array.data;

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
        initSpinner();
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
        if (sp_school.equals("请选择您的学校")) {
            Toast.makeText(this, "请选择学校", Toast.LENGTH_SHORT).show();
            return;
        }
        if (sp_department.equals("请选择您的所属院系")) {
            Toast.makeText(this, "请选择所属院系", Toast.LENGTH_SHORT).show();
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

    /**
     *
     */
    private void initSpinner() {
        //学校
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                data.school);
        adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
        sp_school.setAdapter(adapter);
        //院系
        ArrayAdapter<String> d_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                data.department);
        d_adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
        sp_department.setAdapter(d_adapter);
        //课程
        ArrayAdapter<String> c_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                data.curriculum);
        c_adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
        sp_curriculum.setAdapter(c_adapter);
        //点击事件
        sp_school.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //
                if (sp_school.getSelectedItem().toString().equals("请选择您的学校")) {
                    sp_department.setSelection(0, true);
                    sp_curriculum.setSelection(0, true);
                }
                if (sp_school.getSelectedItem().toString().equals("重庆科创职业学院")) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateInfoActivity.this,
                            android.R.layout.simple_spinner_item, data.kc_department);
                    adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
                    sp_department.setAdapter(adapter);
                    //监听事件
                    sp_department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (sp_department.getSelectedItem().toString().equals("请选择您的所属院系")) {
                                sp_curriculum.setSelection(0, true);
                            }
                            if (sp_department.getSelectedItem().toString().equals("人工智能学院")) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateInfoActivity.this,
                                        android.R.layout.simple_spinner_item, data.kc1_curriculum);
                                adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
                                sp_curriculum.setAdapter(adapter);
                            }
                            if (sp_department.getSelectedItem().toString().equals("智能制造学院")) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateInfoActivity.this,
                                        android.R.layout.simple_spinner_item, data.kc2_curriculum);
                                adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
                                sp_curriculum.setAdapter(adapter);
                            }
                            if (sp_department.getSelectedItem().toString().equals("汽车工程学院")) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateInfoActivity.this,
                                        android.R.layout.simple_spinner_item, data.kc3_curriculum);
                                adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
                                sp_curriculum.setAdapter(adapter);
                            }
                            if (sp_department.getSelectedItem().toString().equals("建筑工程学院")) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateInfoActivity.this,
                                        android.R.layout.simple_spinner_item, data.kc4_curriculum);
                                adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
                                sp_curriculum.setAdapter(adapter);
                            }
                            if (sp_department.getSelectedItem().toString().equals("经济管理学院")) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateInfoActivity.this,
                                        android.R.layout.simple_spinner_item, data.kc5_curriculum);
                                adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
                                sp_curriculum.setAdapter(adapter);
                            }
                            if (sp_department.getSelectedItem().toString().equals("艺术与教育学院")) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateInfoActivity.this,
                                        android.R.layout.simple_spinner_item, data.kc6_curriculum);
                                adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
                                sp_curriculum.setAdapter(adapter);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                if (sp_school.getSelectedItem().toString().equals("重庆财经职业学院")) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateInfoActivity.this,
                            android.R.layout.simple_spinner_item, data.cj_department);
                    adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
                    sp_department.setAdapter(adapter);
                    //
                    sp_department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (sp_department.getSelectedItem().toString().equals("请选择您的所属院系")) {
                                sp_curriculum.setSelection(0, true);
                            }
                            if (sp_department.getSelectedItem().toString().equals("工商管理学院")) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateInfoActivity.this,
                                        android.R.layout.simple_spinner_item, data.cj1_curriculum);
                                adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
                                sp_curriculum.setAdapter(adapter);
                            }
                            if (sp_department.getSelectedItem().toString().equals("经济贸易学院")) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateInfoActivity.this,
                                        android.R.layout.simple_spinner_item, data.cj2_curriculum);
                                adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
                                sp_curriculum.setAdapter(adapter);
                            }
                            if (sp_department.getSelectedItem().toString().equals("金融学院")) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateInfoActivity.this,
                                        android.R.layout.simple_spinner_item, data.cj3_curriculum);
                                adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
                                sp_curriculum.setAdapter(adapter);
                            }
                            if (sp_department.getSelectedItem().toString().equals("会计学院")) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateInfoActivity.this,
                                        android.R.layout.simple_spinner_item, data.cj4_curriculum);
                                adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
                                sp_curriculum.setAdapter(adapter);
                            }
                            if (sp_department.getSelectedItem().toString().equals("商务大数据学院")) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateInfoActivity.this,
                                        android.R.layout.simple_spinner_item, data.cj5_curriculum);
                                adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
                                sp_curriculum.setAdapter(adapter);
                            }
                            if (sp_department.getSelectedItem().toString().equals("应用技术学院")) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateInfoActivity.this,
                                        android.R.layout.simple_spinner_item, data.cj6_curriculum);
                                adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
                                sp_curriculum.setAdapter(adapter);
                            }if (sp_department.getSelectedItem().toString().equals("马克思主义学院")) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateInfoActivity.this,
                                        android.R.layout.simple_spinner_item, data.cj7_curriculum);
                                adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
                                sp_curriculum.setAdapter(adapter);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                if (sp_school.getSelectedItem().toString().equals("重庆文理学院")) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateInfoActivity.this,
                            android.R.layout.simple_spinner_item, data.wl_department);
                    adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
                    sp_department.setAdapter(adapter);
                    //
                    sp_department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (sp_department.getSelectedItem().toString().equals("请选择您的所属院系")) {
                                sp_curriculum.setSelection(0, true);
                            }
                            if (sp_department.getSelectedItem().toString().equals("文化与传媒学院")) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateInfoActivity.this,
                                        android.R.layout.simple_spinner_item, data.wl1_curriculum);
                                adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
                                sp_curriculum.setAdapter(adapter);
                            }
                            if (sp_department.getSelectedItem().toString().equals("数学与大数据学院")) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateInfoActivity.this,
                                        android.R.layout.simple_spinner_item, data.wl2_curriculum);
                                adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
                                sp_curriculum.setAdapter(adapter);
                            }
                            if (sp_department.getSelectedItem().toString().equals("外国语学院")) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateInfoActivity.this,
                                        android.R.layout.simple_spinner_item, data.wl3_curriculum);
                                adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
                                sp_curriculum.setAdapter(adapter);
                            }
                            if (sp_department.getSelectedItem().toString().equals("体育学院")) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateInfoActivity.this,
                                        android.R.layout.simple_spinner_item, data.wl4_curriculum);
                                adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
                                sp_curriculum.setAdapter(adapter);
                            }
                            if (sp_department.getSelectedItem().toString().equals("音乐学院")) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateInfoActivity.this,
                                        android.R.layout.simple_spinner_item, data.wl5_curriculum);
                                adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
                                sp_curriculum.setAdapter(adapter);
                            }
                            if (sp_department.getSelectedItem().toString().equals("美术与设计学院")) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateInfoActivity.this,
                                        android.R.layout.simple_spinner_item, data.wl6_curriculum);
                                adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
                                sp_curriculum.setAdapter(adapter);
                            }if (sp_department.getSelectedItem().toString().equals("土木工程学院")) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateInfoActivity.this,
                                        android.R.layout.simple_spinner_item, data.wl7_curriculum);
                                adapter.setDropDownViewResource(R.layout.item_teacher_spinner_text);
                                sp_curriculum.setAdapter(adapter);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
