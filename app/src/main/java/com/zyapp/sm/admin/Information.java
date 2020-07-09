package com.zyapp.sm.admin;
import com.zyapp.sm.R;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.SQLData;

public class Information extends Activity {
TextView tv_admin_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information2);
        tv_admin_id=findViewById(R.id.tv_admin_id);
    }
    //注销管理员
    public void cancellation(View view) {
        DBOperate DBO=new DBOperate();
        DBO.OpenDB(this);
        String sentence=" delete from "+ sqlData.ADMIN_TABLE + " where id=" + tv_admin_id.getText().toString();
        DBO.operationDB(sentence);
        DBO.CloseDB();
    }
}
