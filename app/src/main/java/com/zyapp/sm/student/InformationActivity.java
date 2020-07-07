package com.zyapp.sm.student;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.zyapp.sm.R;


/**
 * 个人信息
 */
public class InformationActivity extends Activity {
    TextView tv_stuName,tv_stuId,tv_stuScore; //姓名,学号,得分

    TextView tv_school,tv_department,tv_profession;//学校,院系，专业
    TextView tv_stuGenre;//学生类别(在校生)
    TextView tv_stuSex,tv_stuDate;//性别，出生日期


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        initInformation();
    }

    //初始化
    public void initInformation(){
        tv_stuName = findViewById(R.id.tv_stuName);
        tv_stuId = findViewById(R.id.tv_stuId);
        tv_stuScore = findViewById(R.id.tv_stuScore);
        tv_school = findViewById(R.id.tv_school);
        tv_department = findViewById(R.id.tv_department);
        tv_profession = findViewById(R.id.tv_profession);
        tv_stuGenre = findViewById(R.id.tv_stuGenre);
        tv_stuSex = findViewById(R.id.tv_stuSex);
        tv_stuDate = findViewById(R.id.tv_stuDate);
        
    }
}
