package com.zyapp.sm.student;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.zyapp.sm.R;



/**
 *  修改密码
 */
public class ModificationActivity extends Activity {

    EditText et_stuPassword;//当前密码
    EditText et_stuNewpassword;
    EditText et_stuYespassword;

    CheckBox cb_show; //显示密码

    Button btn_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification);
        initModification();
        setBtn();
    }

    public void initModification(){
        et_stuNewpassword = findViewById(R.id.et_stuNewpassword);
        et_stuPassword = findViewById(R.id.et_stuPassword);
        et_stuYespassword = findViewById(R.id.et_stuYespassword);
        cb_show = findViewById(R.id.cb_show);
        btn_password = findViewById(R.id.btn_password);
    }

    public void setBtn(){
        btn_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
