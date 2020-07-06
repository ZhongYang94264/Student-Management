package com.zyapp.sm.ui.student;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zyapp.sm.R;
import com.zyapp.sm.ui.StudentLoginActivity;


/**
 *  学生界面
 */
public class StudentActivity extends Activity implements View.OnClickListener {

    Button btn_course,btn_word,btn_information,btn_modification,btn_out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        initStudent();
    }

    public void initStudent(){
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
        switch (v.getId()){
            case R.id.btn_course:
                Intent intent1 = new Intent(StudentActivity.this,CourseActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_word:
                Intent intent2 = new Intent(StudentActivity.this,WorkActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_information:
                Intent intent3 = new Intent(StudentActivity.this,InformationActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_modification:
                Intent intent4 = new Intent(StudentActivity.this,ModificationActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn_out:
                Intent intent5 = new Intent(StudentActivity.this, StudentLoginActivity.class);
                startActivity(intent5);
                break;


        }
    }
}
