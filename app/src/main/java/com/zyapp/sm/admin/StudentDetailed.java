package com.zyapp.sm.admin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.zyapp.sm.R;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;

public class StudentDetailed extends Activity {
    String ID;
    TextView tv_sd_name, tv_sd_id, tv_sd_school, tv_sd_department, tv_sd_proClass, tv_sd_sex,tv_sd_genre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detailed);
        instantiation();
        display();
    }

    private void display() {
        DBOperate DBO = new DBOperate();
        DBO.OpenDB(this);
        String strselect = " select * from " + sqlData.STUDENT + " where _id = '" + ID + "'";
        Cursor cursor = DBO.selectDB(strselect);
        while (cursor.moveToNext()) {
            tv_sd_name.setText(cursor.getString(cursor.getColumnIndex("name")));
            tv_sd_id.setText(cursor.getString(cursor.getColumnIndex("_id")));
            tv_sd_school.setText(cursor.getString(cursor.getColumnIndex("school")));
            tv_sd_department.setText(cursor.getString(cursor.getColumnIndex("department")));
            tv_sd_proClass.setText(cursor.getString(cursor.getColumnIndex("proClass")));
            tv_sd_sex.setText(cursor.getString(cursor.getColumnIndex("sex")));
            tv_sd_genre.setText(cursor.getString(cursor.getColumnIndex("genre")));
        }
        cursor.close();
    }
    private void instantiation() {
        tv_sd_name = findViewById(R.id.tv_sd_name);
        tv_sd_id = findViewById(R.id.tv_sd_id);
        tv_sd_school = findViewById(R.id.tv_sd_school);
        tv_sd_department = findViewById(R.id.tv_sd_department);
        tv_sd_proClass = findViewById(R.id.tv_sd_proClass);
        tv_sd_sex = findViewById(R.id.tv_sd_sex);
        tv_sd_genre=findViewById(R.id.tv_sd_genre);
        ID=getIntent().getStringExtra("id");


    }

    public void change(View view) {
        AlertDialog.Builder Dhk = new AlertDialog.Builder(StudentDetailed.this);//新建对话框
        Dhk.setTitle("更改成功");
        Dhk.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBOperate DBO=new DBOperate();
                DBO.OpenDB(StudentDetailed.this);
                String sentence=" Update "+ sqlData.STUDENT + " set genre = '"+tv_sd_genre.getText().toString()+"' where _id='" + ID+"'";
                DBO.operationDB(sentence);
                DBO.CloseDB();
                StudentDetailed.this.finish();
            }
        });
        Dhk.show();
    }

    public void cancel(View view) {
        this.finish();
    }

    public void modify(View view) {
        if(tv_sd_genre.getText().toString().equals("在校")){
            tv_sd_genre.setText("离校");
        }
        else {
            tv_sd_genre.setText("在校");
        }
    }
}
