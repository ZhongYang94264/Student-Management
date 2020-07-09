package com.zyapp.sm.student;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.zyapp.sm.R;
import com.zyapp.sm.activity.StudentLoginActivity;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;


/**
 *  我的课程
 */
public class CourseActivity extends Activity {

    private static final String TAG = "CourseActivity";

    ListView lv_course;
    String cGrade;//班级

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        AddDate();
    }


    /*
        添加数据，适配器
     */
    public void AddDate(){

        // 1. 初始化lIstView控件
        lv_course = findViewById(R.id.lv_course);

        // 2. 从数据库中获得数据
        // 打开数据库
        DBOperate dbOperate  = new DBOperate();
        dbOperate.OpenDB(CourseActivity.this);
        // 创建查询语句

        //班级
        String sql1 = "select concat(profession,class) as cg from " + sqlData.PERSONS + " where id = ' " + StudentLoginActivity.stuId + " '";
        Cursor classGrade = dbOperate.selectDB(sql1);
        Log.d(TAG,"显示：" + classGrade);
        while (classGrade.moveToNext()){
            cGrade = classGrade.getString(0);
            Log.d(TAG, "得到密碼==> " + cGrade);
        }







        // 3. 实例化适配器
        //SimpleCursorAdapter 参数1：上下文 子条目布局文件 查询数据库的游标对象(null) 控件对应的数据表中的字段名 控件id

//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
//                CourseActivity.this,
//                R.layout.stu_course_lv,
//                null,
//                new String[] { "friendname", "telephone" },
//                new int[]{ },
//                0);
//
//        // 4. 设置适配器
//        lv_course.setAdapter(adapter);
        // 5. 关闭数据库
        dbOperate.CloseDB();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub

        menu.add(0, 1, 0, "添加课程");
        menu.add(0, 2, 0, "我的作业");
        menu.add(0, 3, 0, "个人信息");
        menu.add(0, 4, 0, "修改密码");
        menu.add(0, 5, 0, "退出");
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case 1:

                break;
            case 2:
                Intent intent2 = new Intent(CourseActivity.this, WorkActivity.class);
                startActivity(intent2);
                break;
            case 3:
                Intent intent3 = new Intent(CourseActivity.this, InformationActivity.class);
                startActivity(intent3);
                break;
            case 4:
                Intent intent4 = new Intent(CourseActivity.this, ModificationActivity.class);
                startActivity(intent4);
                break;
            case 5:
                Intent intent5 = new Intent(CourseActivity.this, StudentActivity.class);
                startActivity(intent5);
                break;

        }
        return true;
    }


}
