package com.zyapp.sm.admin;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.zyapp.sm.R;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;

public class StudentInformation extends Activity {
    EditText ed_student_name;
    ListView lv_s_info;
    SimpleCursorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);

        lv_s_info=findViewById(R.id.lv_s_info);
        ed_student_name=findViewById(R.id.ed_student_name);
        Ed_text_tw();
        display("");
        detailed();
    }
    //显示教师
    private void display(String string){
        DBOperate DBO=new DBOperate();
        DBO.OpenDB(this);
        String strselect =" select * from " + sqlData.STUDENT +  string ;
        Cursor cursor=DBO.selectDB(strselect);
        adapter = new SimpleCursorAdapter(this, R.layout.student, cursor,
                new String[]{"name", "_id"}, new int[]{R.id.tv_s_name, R.id.tv_s_id},0);
        //显示到 listView
        lv_s_info.setAdapter(adapter);
        adapter.notifyDataSetChanged();//更新适配器内容
        DBO.CloseDB();
    }

    //监听输入框输入的内容
    private void Ed_text_tw() {
        //添加文本更改侦听器                文本观察程序
        ed_student_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(!ed_student_name.getText().toString().equals("")){
                    display(" where name like  " + "'%" + ed_student_name.getText().toString() + "%'" );
                }
            }
        });

    }

    private void detailed(){
        lv_s_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view =adapter.getView(i,null,null);
                TextView tv=view.findViewById(R.id.tv_s_id);

                Intent intent = new Intent(StudentInformation.this,StudentDetailed.class);
                intent.putExtra("id",tv.getText().toString());
                startActivity(intent);
            }
        });

    }
}
