package com.zyapp.sm.teacher.fragment;

import android.database.Cursor;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.zyapp.sm.R;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;
import com.zyapp.sm.teacher.adapter.ClassAdapter;
import com.zyapp.sm.teacher.array.classBean;
import com.zyapp.sm.teacher.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @项目名称 Student-Management
 * @包名 com.zyapp.sm.teacher.fragment
 * @作者 钟阳
 * @类名 StudentFragment
 * @日期 2020/7/6 19:12
 * @描述 TODO
 */
public class MyClassFragment extends BaseFragment {

    private static final String TAG = "MyClassFragment";
    private ListView lv_my_class;
    private List<classBean> mClassData;
    private String mWork_num;
    private LinearLayout layout_my_class_empty;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_my_class;
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
        lv_my_class = Objects.requireNonNull(getActivity()).findViewById(R.id.li_my_class);
        layout_my_class_empty = Objects.requireNonNull(getActivity()).findViewById(R.id.layout_my_class_empty);
        //添加数据
        mClassData = new ArrayList<classBean>();
        initClass();
        //实例化适配器
        ClassAdapter adapter = new ClassAdapter(getActivity(), R.layout.item_li_class, mClassData);
        //设置适配器
        if (lv_my_class != null) {
            lv_my_class.setAdapter(adapter);
            lv_my_class.setEmptyView(layout_my_class_empty);
        }
    }

    /**
     *
     */
    private void initClass() {
        //打开数据库
        DBOperate dbOperate = new DBOperate();
        dbOperate.OpenDB(getActivity());

        //
        Cursor cursor = dbOperate.selectDB("select * from " + sqlData.CLASS_TABLE + " where " +
                "teacher_id = '" + mWork_num + "'");
        while (cursor.moveToNext()) {
            String str_class_id = cursor.getString(cursor.getColumnIndex("_id"));
            String str_class_name = cursor.getString(cursor.getColumnIndex("class_name"));
            String str_class_num = cursor.getString(cursor.getColumnIndex("class_num"));
            mClassData.add(new classBean(str_class_id, str_class_name, str_class_num));
        }
        cursor.close();
        //关闭数据库
        dbOperate.CloseDB();
    }
}
