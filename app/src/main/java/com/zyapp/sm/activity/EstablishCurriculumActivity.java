package com.zyapp.sm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zyapp.sm.R;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;

public class EstablishCurriculumActivity extends AppCompatActivity {

    private EditText et_student_id, et_les_name, et_stuScore, et_teacher_name;
    private String mWork_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establish_curriculum);
        //去掉标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //设置系统状态栏的颜色
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorTeacher));
        //接收教师工号
        mWork_num = getIntent().getStringExtra("work_num");
        //初始化
        initView();
    }

    private void initView() {
        et_student_id = findViewById(R.id.et_curriculum_student_id);
        et_les_name = findViewById(R.id.et_les_name);
        et_stuScore = findViewById(R.id.et_stu_score);
        et_teacher_name = findViewById(R.id.et_curriculum_teacher_name);
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
    public void confirmOnClick3(View view) {
        //获取数据
        String str_student_id = et_student_id.getText().toString().trim();
        String str_les_name = et_les_name.getText().toString().trim();
        String str_stuScore = et_stuScore.getText().toString().trim();
        String str_teacher_name = et_teacher_name.getText().toString().trim();
        //判空
        if (str_student_id.isEmpty()) {
            Toast.makeText(this, "学生学号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (str_les_name.isEmpty()) {
            Toast.makeText(this, "课程名称不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (str_stuScore.isEmpty()) {
            Toast.makeText(this, "学生成绩不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (str_teacher_name.isEmpty()) {
            Toast.makeText(this, "教师姓名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        //操作数据库
        DBOperate dbOperate = new DBOperate();
        dbOperate.OpenDB(this);

        Cursor cursor = dbOperate.selectDB("select * from " + sqlData.STUDENT_COU + " " +
                "where lesName = '" + str_les_name + "'");
        if (cursor.getCount() > 0) {
            cursor.close();
            Toast.makeText(this, "课程已存在", Toast.LENGTH_SHORT).show();
            return;
        } else {
            //写入数据库
            dbOperate.operationDB("insert into " + sqlData.STUDENT_COU + " (_id,lesName,stuScore,teacherName,teacher_id)" +
                    " values ('" + str_student_id + "','" + str_les_name + "','" + str_stuScore + "'," +
                    "'" + str_teacher_name + "','" + mWork_num + "')");
            Toast.makeText(this, "创建成功", Toast.LENGTH_SHORT).show();
            this.finish();
        }

        dbOperate.CloseDB();
    }
}
