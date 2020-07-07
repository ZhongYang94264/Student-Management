package com.zyapp.sm.student;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zyapp.sm.R;


/**
 *  我的作业
 */
public class WorkActivity extends AppCompatActivity {

    ListView lv_stuWork;

    TextView tv_stuWordName;//作业名称
    TextView tv_stuWordTime,tv_stuCourse,btn_word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
    }

    public void initWord(){

    }
    //添加数据，适配器
    public void AddDate(){

        // 1. 初始化lIstView控件
        lv_stuWork = findViewById(R.id.lv_course);

        // 2. 从数据库中获得数据
        // 打开数据库

        // 创建查询语句


        // 3. 实例化适配器
        //SimpleCursorAdapter 参数1：上下文 子条目布局文件 查询数据库的游标对象(null) 控件对应的数据表中的字段名 控件id

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                WorkActivity.this,
                R.layout.stu_word_lv,
                null,
                new String[] { "friendname", "telephone" },
                new int[]{ },
                0);

        // 4. 设置适配器
        lv_stuWork.setAdapter(adapter);
        // 5. 关闭数据库

    }
}
