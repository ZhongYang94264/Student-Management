package com.zyapp.sm.admin;
import com.zyapp.sm.R;
import com.zyapp.sm.activity.AdminLoginActivity;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
        AlertDialog.Builder Dhk = new AlertDialog.Builder(Information.this);//新建对话框
        Dhk.setTitle("是否注销");
        Dhk.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBOperate DBO=new DBOperate();
                DBO.OpenDB(Information.this);
                String sentence=" delete from "+ sqlData.ADMIN_TABLE + " where _id='" + tv_admin_id.getText().toString()+"'";
                DBO.operationDB(sentence);
                DBO.CloseDB();
                Toast.makeText(Information.this, "注销成功", Toast.LENGTH_SHORT).show();
                Intent AdminLoginActivity=new Intent(Information.this, AdminLoginActivity.class);
                AdminLoginActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(AdminLoginActivity);
                Information.this.finish();
            }
        });
        Dhk.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        Dhk.show();
    }
}
