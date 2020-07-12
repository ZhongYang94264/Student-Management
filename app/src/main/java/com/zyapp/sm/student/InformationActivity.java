package com.zyapp.sm.student;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.zyapp.sm.R;
import com.zyapp.sm.activity.StudentLoginActivity;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;


/**
 * 个人信息
 */
public class InformationActivity extends Activity {
    private static final String TAG = "InformationActivity";
    TextView tv_stuName, tv_stuId, tv_stuScore; //姓名,学号,得分

    TextView tv_school, tv_department, tv_profession;//学校,院系，专业
    TextView tv_stuGenre;//学生类别(在校生)
    TextView tv_stuSex;//性别，出生日期

    //从数据库获取的信息
    String[] stu = new String[8];
    String score;
    private String mStudent_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        //接收学号
        mStudent_id = getIntent().getStringExtra("student_id");
        Log.d(TAG, "接收到学号 ==> " + mStudent_id);
        initInformation();
        setData();
    }

    //初始化
    public void initInformation() {
        tv_stuName = findViewById(R.id.tv_stuName);
        tv_stuId = findViewById(R.id.tv_stuId);
        tv_stuScore = findViewById(R.id.tv_stuScore);
        tv_school = findViewById(R.id.tv_school);
        tv_department = findViewById(R.id.tv_department);
        tv_profession = findViewById(R.id.tv_profession);
        tv_stuGenre = findViewById(R.id.tv_stuGenre);
        tv_stuSex = findViewById(R.id.tv_stuSex);

    }

    public void setData() {

        DBOperate dbOperate = new DBOperate();
        dbOperate.OpenDB(InformationActivity.this);

        String sql = "select * from " + sqlData.STUDENT + " where _id = '" + mStudent_id + "' ";
        Cursor cursor = dbOperate.selectDB(sql);

        //分数
        String sql1 = "select * from " + sqlData.TOTAL + " where stuId = '" + mStudent_id + "' ";

        Cursor cursor1 = dbOperate.selectDB(sql1);
        Log.d(TAG, "分数 ==>" + cursor1);

        //获取
        while (cursor1.moveToNext()) {
            score = cursor1.getString(1);
            Log.d(TAG, "分数 ==>" + score);

        }

        //判断是否有数据
        while (!cursor.moveToNext()) {
            Log.d(TAG, "显示：没有数据");
            return;
        }
        //获得数据
        int i;
        for (i = 0; i < stu.length; i++) {
            stu[i] = cursor.getString(i);
        }

        //放入数据

        tv_stuId.setText(stu[0]);
        tv_stuName.setText(stu[1]);
        //密码
        tv_stuSex.setText(stu[3]);
        tv_school.setText(stu[4]);
        tv_department.setText(stu[5]);
        tv_profession.setText(stu[6]); //班级
        tv_stuGenre.setText(stu[7]);

        tv_stuScore.setText(score); //分数

        dbOperate.CloseDB();

    }
}
