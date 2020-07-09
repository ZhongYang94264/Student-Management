package com.zyapp.sm.admin;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.zyapp.sm.R;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;

public class TeacherDetailed extends Activity {
    String ID;
    TextView tv_td_name,tv_td_id,tv_td_shool,tv_td_department,tv_td_curriculum,tv_td_gender,tv_td_birthday,tv_td_tel,tv_td_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_detailed);

        instantiation();
    }
    private void instantiation() {
        tv_td_name=findViewById(R.id.tv_td_name);
        tv_td_id=findViewById(R.id.tv_td_id);
        tv_td_shool=findViewById(R.id.tv_td_shool);
        tv_td_department=findViewById(R.id.tv_td_department);
        tv_td_curriculum=findViewById(R.id.tv_td_curriculum);
        tv_td_gender=findViewById(R.id.tv_td_gender);
        tv_td_birthday=findViewById(R.id.tv_td_birthday);
        tv_td_tel=findViewById(R.id.tv_td_tel);
        tv_td_email=findViewById(R.id.tv_td_email);
        ID=getIntent().getStringExtra("id");
        tv_td_birthday.setText(ID);
    }

    private void display() {
        DBOperate DBO=new DBOperate();
        DBO.OpenDB(this);
        String strselect =" select * from " + sqlData.TEACHER_TABLE +  "where _id = "+ID;
        Cursor cursor=DBO.selectDB(strselect);
        while (cursor.moveToNext()) {
            tv_td_name.setText(cursor.getString(cursor.getColumnIndex("name")));
            tv_td_id.setText(cursor.getString(cursor.getColumnIndex("_id")));
            tv_td_shool.setText(cursor.getString(cursor.getColumnIndex("shool")));
            tv_td_department.setText(cursor.getString(cursor.getColumnIndex("department")));
            tv_td_curriculum.setText(cursor.getString(cursor.getColumnIndex("curriculum")));
            tv_td_gender.setText(cursor.getString(cursor.getColumnIndex("gender")));
            tv_td_birthday.setText(cursor.getString(cursor.getColumnIndex("birthday")));
            tv_td_tel.setText(cursor.getString(cursor.getColumnIndex("tel")));
            tv_td_email.setText(cursor.getString(cursor.getColumnIndex("email")));
        }
        cursor.close();
    }
}
