package com.zyapp.sm.teacher.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zyapp.sm.R;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;
import com.zyapp.sm.teacher.UpdateInfoActivity;
import com.zyapp.sm.teacher.base.BaseFragment;

/**
 * @项目名称 Student-Management
 * @包名 com.zyapp.sm.teacher.fragment
 * @作者 钟阳
 * @类名 StudentFragment
 * @日期 2020/7/6 19:12
 * @描述 TODO
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "MyFragment";
    private Button btn_update;
    private String mWork_num;
    private TextView tv_name, tv_work_num, tv_school, tv_department, tv_curriculum, tv_gender, tv_birth, tv_tel, tv_email;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_my;
    }

    @Override
    public void onStart() {
        super.onStart();
        //初始化组件
        btn_update = getActivity().findViewById(R.id.btn_update_info);
        tv_name = getActivity().findViewById(R.id.tv_name);
        tv_work_num = getActivity().findViewById(R.id.tv_work_num);
        tv_school = getActivity().findViewById(R.id.tv_school);
        tv_department = getActivity().findViewById(R.id.tv_department);
        tv_curriculum = getActivity().findViewById(R.id.tv_curriculum);
        tv_gender = getActivity().findViewById(R.id.tv_gender);
        tv_birth = getActivity().findViewById(R.id.tv_birth);
        tv_tel = getActivity().findViewById(R.id.tv_tel);
        tv_email = getActivity().findViewById(R.id.tv_email);
        //register listener
        btn_update.setOnClickListener(this);
        //接收工号
        if (getArguments() != null) {
            mWork_num = getArguments().getString("work_num");
        }
        Log.d(TAG, "接收到工号 ==> " + mWork_num);
        //检索数据库
        initDatabase();
    }

    /**
     *
     */
    private void initDatabase() {
        //打开数据库
        DBOperate dbOperate = new DBOperate();
        dbOperate.OpenDB(getActivity());

        //创建查询语句
        String query_sql = "select * from " + sqlData.TEACHER_TABLE + " where _id = '" + mWork_num + "'";
        //执行查询语句
        Cursor cursor = dbOperate.selectDB(query_sql);
        while (cursor.moveToNext()) {
            String str_id = cursor.getString(cursor.getColumnIndex("_id"));
            String str_name = cursor.getString(cursor.getColumnIndex("name"));
            String str_school = cursor.getString(cursor.getColumnIndex("school"));
            String str_department = cursor.getString(cursor.getColumnIndex("department"));
            String str_curriculum = cursor.getString(cursor.getColumnIndex("curriculum"));
            String str_gender = cursor.getString(cursor.getColumnIndex("gender"));
            String str_birthday = cursor.getString(cursor.getColumnIndex("birthday"));
            String str_tel = cursor.getString(cursor.getColumnIndex("tel"));
            String str_email = cursor.getString(cursor.getColumnIndex("email"));
            //设置显示内容
            tv_name.setText(str_name);
            tv_work_num.setText(str_id);
            tv_school.setText(str_school);
            tv_department.setText(str_department);
            tv_curriculum.setText(str_curriculum);
            tv_gender.setText(str_gender);
            tv_birth.setText(str_birthday);
            tv_tel.setText(str_tel);
            tv_email.setText(str_email);
        }

        //关闭数据库
        dbOperate.CloseDB();
    }

    /**
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update_info:
                Intent u_intent = new Intent(getActivity(), UpdateInfoActivity.class);
                u_intent.putExtra("work_num", mWork_num);
                startActivity(u_intent);
                break;
        }
    }
}
