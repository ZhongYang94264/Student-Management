package com.zyapp.sm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.zyapp.sm.R;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;
import com.zyapp.sm.teacher.UpdateInfoActivity;

import java.util.regex.Pattern;

public class AddStudentActivity extends AppCompatActivity {

    private EditText et_student_id, et_name, et_school, et_department, et_class_name;
    private RadioButton rb_male, rb_female;
    private ToggleButton tb_type;
    private DBOperate mDbOperate;
    private String mWork_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        //去掉标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //修改系统状态栏的颜色
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorTeacher));
        //初始化
        initView();
        //接收教师工号
        mWork_num = getIntent().getStringExtra("work_num");
    }

    /**
     *
     */
    private void initView() {
        et_student_id = findViewById(R.id.et_student_id);
        et_name = findViewById(R.id.et_name);
        et_school = findViewById(R.id.et_school);
        et_department = findViewById(R.id.et_department);
        et_class_name = findViewById(R.id.et_class_name);
        rb_male = findViewById(R.id.rb_male);
        rb_female = findViewById(R.id.rb_female);
        tb_type = findViewById(R.id.tb_type);
    }

    /**
     * 确定按钮点击事件
     *
     * @param view
     */
    public void confirmOnClick(View view) {
        //获取数据
        String str_student_id = et_student_id.getText().toString();
        String str_name = et_name.getText().toString();
        String str_school = et_school.getText().toString();
        String str_department = et_department.getText().toString();
        String str_class = et_class_name.getText().toString();
        String str_gender = "";
        if (rb_male.isChecked()) {
            str_gender = "男";
        } else {
            str_gender = "女";
        }
        //判空
        if (str_student_id.isEmpty()) {
            Toast.makeText(this, "请输入学号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (str_name.isEmpty()) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (str_school.isEmpty()) {
            Toast.makeText(this, "请输入学校", Toast.LENGTH_SHORT).show();
            return;
        }
        if (str_department.isEmpty()) {
            Toast.makeText(this, "请输入院系", Toast.LENGTH_SHORT).show();
            return;
        }
        if (str_class.isEmpty()) {
            Toast.makeText(this, "请输入班级", Toast.LENGTH_SHORT).show();
            return;
        }
        //正则表达式
        if (!Pattern.compile("^2020000(\\d{3})+$").matcher(str_student_id).matches()) {
            Toast.makeText(this, "学号格式错误", Toast.LENGTH_SHORT).show();
            return;
        }
        //操作数据库
        mDbOperate = new DBOperate();
        mDbOperate.OpenDB(this);

        //查询学生是否已被添加
        Cursor cursor = mDbOperate.selectDB("select * from " + sqlData.STUDENT + " where _id = '" + str_student_id + "' ");
        if (cursor.getCount() > 0) {
            cursor.close();
            Toast.makeText(this, "该学生已存在", Toast.LENGTH_SHORT).show();
            return;
        } else {
            //添加
            mDbOperate.operationDB("insert into " + sqlData.STUDENT + " (_id,name,password,sex,school," +
                    "department,proClass,genre,teacher_id) values ('" + str_student_id + "','" + str_name + "','" + str_student_id + "'," +
                    "'" + str_gender + "','" + str_school + "','" + str_department + "','" + str_class + "'," +
                    "'" + tb_type.getText().toString() + "','" + mWork_num + "')");
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setIcon(R.mipmap.icon_teacher_head)
                    .setMessage("学生添加成功,密码即学号")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            AddStudentActivity.this.finish();
                        }
                    }).show();
        }

        mDbOperate.CloseDB();
    }

    /**
     * 返回按钮点击事件
     *
     * @param view
     */
    public void backOnClick(View view) {
        //关闭当前Activity
        this.finish();
    }
}
