package com.zyapp.sm.student;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.zyapp.sm.R;
import com.zyapp.sm.activity.AdminRegisterActivity;
import com.zyapp.sm.activity.StudentLoginActivity;
import com.zyapp.sm.activity.TeacherRegisterActivity;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.MySQLiteHelper;
import com.zyapp.sm.sql.sqlData;


/**
 *  修改密码
 */
public class ModificationActivity extends Activity {
    private static final String TAG = "ModificationActivity";
    EditText et_stuPassword;//当前密码
    EditText et_stuNewpassword;//新密码
    EditText et_stuYespassword;//确认密码

    CheckBox cb_show; //显示密码
    Button btn_password;//确认修改

    private String mStr_stuPassword;
    private String mStr_stuNewpassword;
    private String mStr_stuYespassword;
    String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification);

        //调用方法
        initModification(  );
        setBtn(  );
    }

    //初始化
    public void initModification(){
        et_stuNewpassword = findViewById(R.id.et_stuNewpassword);
        et_stuPassword = findViewById(R.id.et_stuPassword);
        et_stuYespassword = findViewById(R.id.et_stuYespassword);
        cb_show = findViewById(R.id.cb_show);
        btn_password = findViewById(R.id.btn_password);
    }

    //监听事件
    public void setBtn(  ){
        btn_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入文本
                mStr_stuPassword=  et_stuPassword.getText().toString();
                mStr_stuNewpassword = et_stuNewpassword.getText().toString();
                mStr_stuYespassword=et_stuYespassword.getText().toString();

                // 是否为空(判断处理信息)
                if ( mStr_stuPassword.equals("") || mStr_stuNewpassword.equals("")||   mStr_stuYespassword.equals("")) {
                    getDialog("错误", "相关数据不能为空");
                    return;
                }

                //数据库
                DBOperate dbOperate = new DBOperate();
                dbOperate.OpenDB(ModificationActivity.this);

                String sql = "select * from " + sqlData.STUDENT  + " where _id = '" + StudentLoginActivity.stuId + "' ";
                Cursor cursor = dbOperate.selectDB(sql);
                Log.d(TAG, "数据==> " + cursor);

                // 修改密码
                while (cursor.moveToNext()) {
                    pass = cursor.getString(2);
                    Log.d(TAG, "得到密碼==> " + pass);
                }
                if ( mStr_stuPassword.equals(pass)) {
                    if(mStr_stuNewpassword.equals(pass)){
                        getDialog("提示","新密码与旧密码不能相同");
                    }else{
                        if(mStr_stuYespassword.equals(mStr_stuNewpassword)){

                            String sql2 = "update " + sqlData.STUDENT + " set password = '" + mStr_stuYespassword + "' where _id = '" + StudentLoginActivity.stuId + "' ";
                            dbOperate.operationDB(sql2);

                            getDialog("完成", "修改成功");

                        }else {
                            getDialog("提示","修改密码不一致");
                        }
                    }
                }else{
                    getDialog("提示","旧密码输入错误");
                }


                //关闭数据库
                dbOperate.CloseDB( );
            }
        });
    }

   // 弹出窗口
    public void getDialog(final String title, String str) {
        new AlertDialog.Builder(ModificationActivity.this).setTitle(title)
                .setMessage(str)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(title.equals("完成")){
                            Intent intent = new Intent(ModificationActivity.this,StudentActivity.class);
                            startActivity(intent);
                        }

                    }
                }).show( );
    }

}
