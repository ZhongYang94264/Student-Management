package com.zyapp.sm.student;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.core.content.ContextCompat;

import com.zyapp.sm.R;
import com.zyapp.sm.activity.StudentLoginActivity;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;


/**
 * 学生界面
 */
public class StudentActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "StudentActivity";
    Button btn_course, btn_word, btn_information, btn_modification, btn_out;
    private String mStudent_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        //接收学号
        mStudent_id = getIntent().getStringExtra("student_id");
        Log.d(TAG, "学生界面接收到学号 ==> " + mStudent_id);
        //初始化
        initStudent();
    }

    public void initStudent() {
        btn_course = findViewById(R.id.btn_course);
        btn_word = findViewById(R.id.btn_word);
        btn_information = findViewById(R.id.btn_information);
        btn_modification = findViewById(R.id.btn_modification);
        btn_out = findViewById(R.id.btn_out);

        btn_course.setOnClickListener(this);
        btn_word.setOnClickListener(this);
        btn_information.setOnClickListener(this);
        btn_modification.setOnClickListener(this);
        btn_out.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_course:
                Intent intent1 = new Intent(StudentActivity.this, CourseActivity.class);
                intent1.putExtra("student_id", mStudent_id);
                startActivity(intent1);
                break;
            case R.id.btn_word:
                Intent intent2 = new Intent(StudentActivity.this, WorkActivity.class);
                intent2.putExtra("student_id", mStudent_id);
                startActivity(intent2);
                break;
            case R.id.btn_information:
                Intent intent3 = new Intent(StudentActivity.this, InformationActivity.class);
                intent3.putExtra("student_id", mStudent_id);
                startActivity(intent3);
                break;
            case R.id.btn_modification:
                Intent intent4 = new Intent(StudentActivity.this, ModificationActivity.class);
                intent4.putExtra("student_id", mStudent_id);
                startActivity(intent4);
                break;
            case R.id.btn_out:
                Intent intent5 = new Intent(StudentActivity.this, StudentLoginActivity.class);
                intent5.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent5);
                finish();
                break;


        }
    }
}
