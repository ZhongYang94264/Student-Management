package com.zyapp.sm.teacher.fragment;

import android.database.Cursor;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zyapp.sm.R;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;
import com.zyapp.sm.teacher.adapter.StudentAdapter;
import com.zyapp.sm.teacher.array.studentBean;
import com.zyapp.sm.teacher.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @项目名称 Student-Management
 * @包名 com.zyapp.sm.teacher.fragment
 * @作者 钟阳
 * @类名 StudentFragment
 * @日期 2020/7/6 19:12
 * @描述 TODO
 */
public class MyStudentFragment extends BaseFragment {

    private static final String TAG = "MyStudentFragment";
    private ListView li_my_student;
    private List<studentBean> mStudentData;
    private DBOperate mDbOperate;
    private String mWork_num;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_my_student;
    }

    @Override
    public void onStart() {
        super.onStart();
        //接收教师工号
        if (getArguments() != null) {
            mWork_num = getArguments().getString("work_num");
        }
        Log.d(TAG, "接收到教师工号==> " + mWork_num);
        //初始化控件
        li_my_student = getActivity().findViewById(R.id.li_my_student);
        //添加数据
        mStudentData = new ArrayList<studentBean>();//创建list集合
        //检索数据库
        initStudent();
        //实例化适配器
        StudentAdapter adapter = new StudentAdapter(getActivity(), R.layout.item_li_student, mStudentData);
        //设置适配器
        li_my_student.setAdapter(adapter);
    }

    /**
     *
     */
    private void initStudent() {
        //打开数据库
        mDbOperate = new DBOperate();
        mDbOperate.OpenDB(getActivity());

        //查询数据库
        Cursor cursor = mDbOperate.selectDB("select * from " + sqlData.STUDENT + " where teacher_id = '" + mWork_num + "'");
        //取出当前学号对应的数据
        while (cursor.moveToNext()) {
            String str_student_id = cursor.getString(cursor.getColumnIndex("_id"));
            String str_name = cursor.getString(cursor.getColumnIndex("name"));
            String str_sex = cursor.getString(cursor.getColumnIndex("sex"));
            String str_school = cursor.getString(cursor.getColumnIndex("school"));
            String str_department = cursor.getString(cursor.getColumnIndex("department"));
            String str_class = cursor.getString(cursor.getColumnIndex("proClass"));
            String str_type = cursor.getString(cursor.getColumnIndex("genre"));
            mStudentData.add(new studentBean(str_student_id, str_name, str_sex, str_school, str_department,
                    str_class, str_type));
        }
        //关闭数据库
        mDbOperate.CloseDB();
    }
}
