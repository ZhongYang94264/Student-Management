package com.zyapp.sm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zyapp.sm.R;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;

public class AddAchievementActivity extends AppCompatActivity {

    private static final String TAG = "AddAchievementActivity";
    private EditText et_student_id, et_chinese, et_math, et_english, et_politics, et_writing, et_computer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_achievement);
        //去掉标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //设置系统状态栏的颜色
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorTeacher));
        //初始化
        initView();
    }

    /**
     *
     */
    private void initView() {
        et_student_id = findViewById(R.id.et_student_id);
        et_chinese = findViewById(R.id.et_chinese);
        et_math = findViewById(R.id.et_math);
        et_english = findViewById(R.id.et_english);
        et_politics = findViewById(R.id.et_politics);
        et_writing = findViewById(R.id.et_writing);
        et_computer = findViewById(R.id.et_computer);
    }

    /**
     * @param view
     */
    public void backOnClick(View view) {
        this.finish();
    }

    /**
     * @param view
     */
    public void confirmOnClick(View view) {
        //判空
        if (et_student_id.getText().toString().isEmpty()) {
            Toast.makeText(this, "请输入学生学号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (et_chinese.getText().toString().isEmpty() || et_math.getText().toString().isEmpty() ||
                et_english.getText().toString().isEmpty() || et_politics.getText().toString().isEmpty() ||
                et_writing.getText().toString().isEmpty() || et_computer.getText().toString().isEmpty()) {
            Toast.makeText(this, "成绩不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        //获取数据
        String str_student_id = et_student_id.getText().toString().trim();
        int chinese = Integer.parseInt(et_chinese.getText().toString());
        int math = Integer.parseInt(et_math.getText().toString());
        int english = Integer.parseInt(et_english.getText().toString());
        int politics = Integer.parseInt(et_politics.getText().toString());
        int writing = Integer.parseInt(et_writing.getText().toString());
        int computer = Integer.parseInt(et_computer.getText().toString());
        int total_score = (chinese + math + english + politics + writing + computer);
        Log.d(TAG, "学号 ==> " + str_student_id + "；总分==> " + total_score);
        //操作数据库
        DBOperate dbOperate = new DBOperate();
        dbOperate.OpenDB(this);

        //
        Cursor cursor = dbOperate.selectDB("select * from " + sqlData.TOTAL + " where stuId = '" + str_student_id + "'");
        if (cursor.getCount() > 0) {
            Toast.makeText(this, "该学生已存在", Toast.LENGTH_SHORT).show();
        } else {
            //修改数据库
            dbOperate.operationDB("insert into " + sqlData.TOTAL + " (stuId,total) values" +
                    " ('" + str_student_id + "','" + total_score + "')");
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
            this.finish();
            return;
        }

        //关闭数据库
        dbOperate.CloseDB();
    }
}
