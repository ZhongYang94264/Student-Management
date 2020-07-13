package com.zyapp.sm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zyapp.sm.R;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;

import java.util.Calendar;

public class PublishJobActivity extends AppCompatActivity {

    private static final String TAG = "PublishJobActivity";
    private Button btn_confirm;
    private EditText et_publish_job_student_id, et_publish_job_work_name, et_publish_job_les_name;
    private String mWork_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_job);
        //去掉标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //设置系统状态栏的颜色
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorTeacher));
        //初始化
        initView();
        //监听事件
        initListener();
        //接收教师工号
        mWork_num = getIntent().getStringExtra("work_num");
        Log.d(TAG, "接收到教师工号 ==> " + mWork_num);
    }

    private void initListener() {
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取数据
                String str_student_id = et_publish_job_student_id.getText().toString().trim();
                String str_work_name = et_publish_job_work_name.getText().toString().trim();
                String str_les_name = et_publish_job_les_name.getText().toString().trim();
                //判空
                if (str_student_id.isEmpty()) {
                    Toast.makeText(PublishJobActivity.this, "学生学号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (str_work_name.isEmpty()) {
                    Toast.makeText(PublishJobActivity.this, "作业名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (str_les_name.isEmpty()) {
                    Toast.makeText(PublishJobActivity.this, "课程名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                //写入数据库
                DBOperate dbOperate = new DBOperate();
                dbOperate.OpenDB(PublishJobActivity.this);
                //
                Cursor cursor = dbOperate.selectDB("select * from " + sqlData.WORK + " where name = '" + str_work_name + "'");
                //获取当前时间
                Calendar calendar = Calendar.getInstance();
                int now_year = calendar.get(Calendar.YEAR);
                int now_month = calendar.get(Calendar.MONTH);
                int now_day = calendar.get(Calendar.DAY_OF_MONTH);
                int now_hour = calendar.get(Calendar.HOUR_OF_DAY);
                int now_min = calendar.get(Calendar.MINUTE);
                String date = now_year + "-" + (now_month + 1) + "-" + now_day + "/" + now_hour + ":" + now_min;
                Log.d(TAG, "当前时间==> " + date);

                Cursor cursor1 = dbOperate.selectDB("select * from " + sqlData.WORK + " where name = '" + str_work_name + "'");
                if (cursor1.getCount() > 0) {
                    cursor.close();
                    Toast.makeText(PublishJobActivity.this, "作业重复", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    //写入数据库
                    dbOperate.operationDB("insert into " + sqlData.WORK + " (_id,name,lesName,time,teacherId)" +
                            " values ('" + str_student_id + "','" + str_work_name + "','" + str_les_name + "'," +
                            "'" + date + "','" + mWork_num + "')");
                    Toast.makeText(PublishJobActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                    PublishJobActivity.this.finish();
                }

                dbOperate.CloseDB();
            }
        });
    }

    private void initView() {
        btn_confirm = findViewById(R.id.btn_confirm);
        et_publish_job_student_id = findViewById(R.id.et_publish_job_student_id);
        et_publish_job_work_name = findViewById(R.id.et_publish_job_work_name);
        et_publish_job_les_name = findViewById(R.id.et_publish_job_les_name);
    }

    /**
     * 返回按钮点击事件
     *
     * @param view
     */
    public void backOnClick(View view) {
        this.finish();
    }
}
