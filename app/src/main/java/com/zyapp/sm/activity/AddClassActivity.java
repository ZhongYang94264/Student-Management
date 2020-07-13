package com.zyapp.sm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zyapp.sm.R;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;

public class AddClassActivity extends AppCompatActivity {

    private static final String TAG = "AddClassActivity";
    private String mWork_num;
    private EditText et_class_name, et_class_num, et_class_id;
    private DBOperate mDbOperate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        //初始化
        initView();
        //去掉标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //修改系统状态栏的颜色
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorTeacher));
        //接收教师工号
        mWork_num = getIntent().getStringExtra("work_num");
    }

    /**
     *
     */
    private void initView() {
        et_class_name = findViewById(R.id.et_class_name);
        et_class_id = findViewById(R.id.et_class_id);
        et_class_num = findViewById(R.id.et_class_num);
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
        Log.d(TAG,"点击了确定按钮");
        //获取数据
        String str_name = et_class_name.getText().toString().trim();
        String str_class_id = et_class_id.getText().toString().trim();
        String str_num = et_class_num.getText().toString().trim();
        //判空
        if (str_name.isEmpty()) {
            Toast.makeText(this, "请输入班级名称", Toast.LENGTH_SHORT).show();
            return;
        }
        if (str_num.isEmpty()) {
            Toast.makeText(this, "请输入班级人员总数", Toast.LENGTH_SHORT).show();
            return;
        }
        if (str_class_id.isEmpty()) {
            Toast.makeText(this, "请输入班级编号", Toast.LENGTH_SHORT).show();
            return;
        }
        //操作数据库
        mDbOperate = new DBOperate();
        mDbOperate.OpenDB(this);

        //
        Cursor cursor = mDbOperate.selectDB("select * from " + sqlData.CLASS_TABLE + " where _id = '" +
                str_class_id + "' ");
        if (cursor.getCount() > 0) {
            cursor.close();
            Toast.makeText(this, "该班级已存在", Toast.LENGTH_SHORT).show();
            return;
        } else {
            mDbOperate.operationDB("insert into " + sqlData.CLASS_TABLE + " (_id,class_name,class_num,teacher_id) " +
                    "values ('" + str_class_id + "','" + str_name + "','" + str_num + "','" + mWork_num + "')");
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
            this.finish();
        }

        //关闭数据库
        mDbOperate.CloseDB();
    }
}
