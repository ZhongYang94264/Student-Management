package com.zyapp.sm.teacher.fragment;

import android.database.Cursor;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.zyapp.sm.R;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;
import com.zyapp.sm.teacher.adapter.CurriculumAdapter;
import com.zyapp.sm.teacher.array.curriculumBean;
import com.zyapp.sm.teacher.base.BaseFragment;

import java.util.ArrayList;
import java.util.Calendar;
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
public class MyCurriculumFragment extends BaseFragment {

    private static final String TAG = "MyCurriculumFragment";
    private ListView lv_my_curriculum;
    private String mWork_num;
    private List<curriculumBean> mCurriculumBeans;
    private LinearLayout layout_my_curriculum_empty;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_my_curriculum;
    }

    @Override
    public void onStart() {
        super.onStart();
        //获取教师工号
        if (getArguments() != null) {
            mWork_num = getArguments().getString("work_num");
            Log.d(TAG, "获得到教室工号==> " + mWork_num);
        }
        //初始化
        lv_my_curriculum = Objects.requireNonNull(getActivity()).findViewById(R.id.li_to_be_corrected);
        layout_my_curriculum_empty = Objects.requireNonNull(getActivity()).findViewById(R.id.layout_my_curriculum_empty);
        //添加数据
        mCurriculumBeans = new ArrayList<>();
        //检索数据库
        initData();
        //实例化适配器
        CurriculumAdapter adapter = new CurriculumAdapter(getActivity(), R.layout.item_my_curriculum, mCurriculumBeans);
        //设置适配器
        if (lv_my_curriculum != null) {
            lv_my_curriculum.setAdapter(adapter);
            lv_my_curriculum.setEmptyView(layout_my_curriculum_empty);
        }
    }

    private void initData() {
        DBOperate dbOperate = new DBOperate();
        dbOperate.OpenDB(getActivity());

        //获得当前时间
        Calendar calendar = Calendar.getInstance();
        int now_year = calendar.get(Calendar.YEAR);
        int now_month = calendar.get(Calendar.MONTH);
        int now_day = calendar.get(Calendar.DAY_OF_MONTH);
        int now_hour = calendar.get(Calendar.HOUR_OF_DAY);
        int now_min = calendar.get(Calendar.MINUTE);
        int now_second = calendar.get(Calendar.SECOND);
        String data = now_year + "-" + (now_month + 1) + "-" + now_day + "/" + now_hour + ":" + now_min + ":" + now_second;
        //
        Cursor cursor = dbOperate.selectDB("select * from " + sqlData.STUDENT_COU + " " +
                "where teacher_id = '" + mWork_num + "'");
        while (cursor.moveToNext()) {
            String str_curriculumName = cursor.getString(cursor.getColumnIndex("lesName"));
            String str_teacherName = cursor.getString(cursor.getColumnIndex("teacherName"));
            mCurriculumBeans.add(new curriculumBean(str_teacherName, str_curriculumName, data));
        }
        cursor.close();
        dbOperate.CloseDB();
    }
}
