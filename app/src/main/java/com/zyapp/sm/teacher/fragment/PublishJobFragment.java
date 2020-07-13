package com.zyapp.sm.teacher.fragment;

import android.database.Cursor;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.zyapp.sm.R;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;
import com.zyapp.sm.teacher.adapter.WorkAdapter;
import com.zyapp.sm.teacher.array.workBean;
import com.zyapp.sm.teacher.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @项目名称 Student-Management
 * @包名 com.zyapp.sm.teacher.fragment
 * @作者 钟阳
 * @类名 PublishJobFragment
 * @日期 2020/7/12 8:55
 * @描述 TODO
 */
public class PublishJobFragment extends BaseFragment {

    private static final String TAG = "PublishJobFragment";
    private ListView lv_publish_job;
    private List<workBean> mWorkData;
    private String mWork_num;
    private LinearLayout layout_publish_job_empty;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_publish_job;
    }

    @Override
    public void onStart() {
        super.onStart();
        //接收教师工号
        if (getArguments() != null) {
            mWork_num = getArguments().getString("work_num");
            Log.d(TAG, "接收到教师工号 ==>" + mWork_num);
        }
        //初始化
        lv_publish_job = Objects.requireNonNull(getActivity()).findViewById(R.id.li_publish_job);
        layout_publish_job_empty = Objects.requireNonNull(getActivity()).findViewById(R.id.layout_publish_job_empty);
        //添加数据
        mWorkData = new ArrayList<>();
        initWork();//检索数据库
        //实例化适配器
        WorkAdapter adapter = new WorkAdapter(getActivity(), R.layout.item_publish_job, mWorkData);
        //设置适配器
        if (lv_publish_job != null) {
            lv_publish_job.setAdapter(adapter);
            lv_publish_job.setEmptyView(layout_publish_job_empty);
        }
    }

    private void initWork() {
        DBOperate dbOperate = new DBOperate();
        dbOperate.OpenDB(getActivity());

        Cursor cursor = dbOperate.selectDB("select * from " + sqlData.WORK + " " +
                "where teacherId = '" + mWork_num + "' ");
        while (cursor.moveToNext()) {
            String str_name = cursor.getString(cursor.getColumnIndex("name"));
            String str_time = cursor.getString(cursor.getColumnIndex("time"));
            String str_lesname = cursor.getString(cursor.getColumnIndex("lesName"));
            mWorkData.add(new workBean(str_name, str_time, str_lesname));
        }
        cursor.close();
        dbOperate.CloseDB();
    }
}
