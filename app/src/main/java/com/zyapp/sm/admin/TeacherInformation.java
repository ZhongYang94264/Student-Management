package com.zyapp.sm.admin;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.zyapp.sm.R;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;

import java.sql.SQLData;

public class TeacherInformation extends Activity {
    EditText ed_teacher_name;
    ListView lv_t_info;
    SimpleCursorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_information);

        lv_t_info=findViewById(R.id.lv_t_info);
        ed_teacher_name=findViewById(R.id.ed_teacher_name);
        display("");
        Ed_text_tw();
    }

    //显示教师
    private void display(String string){
        DBOperate DBO=new DBOperate();
        DBO.OpenDB(this);
        String strselect =" select * from " + sqlData.TEACHER_TABLE + string;
        Cursor cursor=DBO.selectDB(strselect);
        adapter = new SimpleCursorAdapter(this, R.layout.teacher, cursor,
                new String[]{"_id", "name"}, new int[]{R.id.tv_t_name, R.id.tv_t_id},0);
        //显示到 listView
        lv_t_info.setAdapter(adapter);
        adapter.notifyDataSetChanged();//更新适配器内容
        DBO.CloseDB();
    }


    //监听输入框输入的内容
    private void Ed_text_tw() {
        //添加文本更改侦听器                文本观察程序
        ed_teacher_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(!ed_teacher_name.getText().toString().equals("")){
                    display(" where name like ? " + "%" + ed_teacher_name.getText().toString()+"%");
                }
            }
        });
    }
}
