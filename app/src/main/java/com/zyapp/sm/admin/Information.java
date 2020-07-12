package com.zyapp.sm.admin;
import com.zyapp.sm.R;
import com.zyapp.sm.activity.AdminLoginActivity;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLData;

public class Information extends Activity {
    String ID;
    TextView tv_admin_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information2);
        tv_admin_id=findViewById(R.id.tv_admin_id);
        ID=getIntent().getStringExtra("id");

        tv_admin_id.setText(ID);
    }
    //注销管理员
    public void cancellation(View view) {
        DBOperate DBO=new DBOperate();
        DBO.OpenDB(this);
        String sentence=" delete from "+ sqlData.ADMIN_TABLE + " where _id='" + tv_admin_id.getText().toString()+"'";
        DBO.operationDB(sentence);
        DBO.CloseDB();
        Toast.makeText(this, "注销成功", Toast.LENGTH_SHORT).show();
        this.finish();
        startActivity(new Intent(this, AdminLoginActivity.class));
    }
}
