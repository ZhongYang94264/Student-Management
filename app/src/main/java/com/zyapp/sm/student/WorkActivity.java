package com.zyapp.sm.student;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.zyapp.sm.R;
import com.zyapp.sm.activity.StudentLoginActivity;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;


/**
 * 我的作业
 */
public class WorkActivity extends AppCompatActivity {

    private static final String TAG = "WorkActivity";
    ListView lv_stuWork;
    private String mStudent_id;
    private LinearLayout layout_student_work_empty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //接收学号
        mStudent_id = getIntent().getStringExtra("student_id");
        Log.d(TAG, "接收到学号 ==> " + mStudent_id);
        AddDate();
    }


    //添加数据，适配器
    public void AddDate() {

        // 1. 初始化lIstView控件
        lv_stuWork = findViewById(R.id.lv_stuWork);
        layout_student_work_empty = findViewById(R.id.layout_student_work_empty);

        // 2. 从数据库中获得数据
        // 打开数据库
        DBOperate dbOperate = new DBOperate();
        dbOperate.OpenDB(WorkActivity.this);


        // 创建查询语句
        String sql = "select * from " + sqlData.WORK + " where _id = '" + mStudent_id + "' ";
        Cursor cursor = dbOperate.selectDB(sql);
        Log.d(TAG, "作业==>11 " + cursor);

        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String lesName = cursor.getString(2);
            String time = cursor.getString(3);
            Log.d(TAG, "作业名==> " + name + " 课程名 ===> " + lesName + " 发布时间 ===> " + time);
        }


        // 3. 实例化适配器
        // SimpleCursorAdapter 参数1：上下文 子条目布局文件 查询数据库的游标对象(null) 控件对应的数据表中的字段名 控件id
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                WorkActivity.this,
                R.layout.stu_word_lv,
                cursor,
                new String[]{"name", "lesName", "time"},
                new int[]{R.id.tv_stuWordName, R.id.tv_stuCourse, R.id.tv_stuWordTime},
                0);

        // 4. 设置适配器
        Log.d(TAG, "作业==>12 " + cursor);

        lv_stuWork.setAdapter(adapter);
        lv_stuWork.setEmptyView(layout_student_work_empty);

        // 5. 关闭数据库
        dbOperate.CloseDB();

    }
}
