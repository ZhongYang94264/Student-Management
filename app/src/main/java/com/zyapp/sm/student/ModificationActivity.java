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
    String l_id, oldpass;
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
        et_stuNewpassword = findViewById(R.id.et_stuNewpassword);//新密码
        et_stuPassword = findViewById(R.id.et_stuPassword);//当前密码
        et_stuYespassword = findViewById(R.id.et_stuYespassword);//确认密码
        cb_show = findViewById(R.id.cb_show);//显示密码
        btn_password = findViewById(R.id.btn_password);//确认修改
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
                if ( mStr_stuPassword.equals("") || mStr_stuNewpassword.equals("")
                        ||   mStr_stuYespassword.equals("")) {
                    getDialog("错误", "相关数据不能为空");
                    return;
                }
                //操作数据库
                //1. 打开数据库
                DBOperate dbOperate = new DBOperate();
                dbOperate.OpenDB(ModificationActivity.this);
                //调用查询方法，判断用户是否存在
                //创建查询语句
                String query_sql = "select * from " + sqlData.PERSONS  + " where _id = '1' ";
                //执行语句
                Cursor cursor = dbOperate.selectDB(query_sql);
                // 修改密码
                while (cursor.moveToNext()) {
                    mStr_stuPassword = cursor.getString(2);
                    Log.d(TAG, "得到密碼==> " +oldpass);
                }
                if (  mStr_stuPassword.equals(et_stuPassword)) {
                    if (mStr_stuPassword.equals( oldpass )) {
                        getDialog("错误", "当前密码和修改密码不能一致");
                    } else {
                        dbOperate.operationDB("update "+ sqlData.PERSONS  +" set password = '"+ mStr_stuNewpassword +"' where _id = '1' ");
                        getDialog("完成", "修改成功");
                    }
                } else {
                    getDialog("错误", "当前密码输入错误");
                }
                //关闭数据库
                dbOperate.CloseDB( );
            }
        });
    }

   // 弹出窗口
    // 自定义方法实现对话框
    public void getDialog(final String title, String str) {
        new AlertDialog.Builder(ModificationActivity.this).setTitle(title)
                .setMessage(str)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //关闭对话框
                        dialog.dismiss( );
                    }
                }).show( );
    }

}
