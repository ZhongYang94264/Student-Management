package com.zyapp.sm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.zyapp.sm.R;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;

public class WorkSearchActivity extends AppCompatActivity {

    private EditText et_work_search;
    private ListView lv_search_result;
    private TextView tv_search_empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_search);
        //去掉标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //设置系统状态栏的颜色
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorTeacher));
        //初始化
        initView();
        //
        searchResult();
    }

    private void searchResult() {
        et_work_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str_search = et_work_search.getText().toString().trim();
                //
                DBOperate dbOperate = new DBOperate();
                dbOperate.OpenDB(WorkSearchActivity.this);

                Cursor cursor = dbOperate.selectDB("select * from " + sqlData.WORK + " where name like '%" + str_search + "%'");
                //添加数据
                //实例化适配器
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(WorkSearchActivity.this, R.layout.item_work_sarch,
                        cursor, new String[]{"name", "time"}, new int[]{R.id.tv_search_curriculumName, R.id.tv_search_publish_time, 0});
                //设置适配器
                lv_search_result.setAdapter(adapter);
                lv_search_result.setEmptyView(tv_search_empty);
                dbOperate.CloseDB();
            }
        });
    }


    private void initView() {
        et_work_search = findViewById(R.id.et_work_search);
        lv_search_result = findViewById(R.id.lv_search_result);
        tv_search_empty = findViewById(R.id.tv_search_empty);
    }
}
