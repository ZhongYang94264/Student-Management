package com.zyapp.sm.teacher.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zyapp.sm.R;
import com.zyapp.sm.activity.AddAchievementActivity;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;
import com.zyapp.sm.teacher.adapter.TotalAdapter;
import com.zyapp.sm.teacher.array.totalBean;
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
public class AchievementFragment extends BaseFragment {

    private static final String TAG = "AchievementFragment";
    private ImageView iv_add_achievement;
    private ListView lv_achievement;
    private List<totalBean> mStudentData;
    private String mWork_num;
    private LinearLayout layout_achievement_empty;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_achievement;
    }

    @Override
    public void onStart() {
        super.onStart();
        //初始化
        initView();
        //创建List集合
        mStudentData = new ArrayList<totalBean>();
        //检索数据库
        initDate();
        //实例化适配器
        TotalAdapter adapter = new TotalAdapter(getActivity(), R.layout.item_li_score, mStudentData);
        //设置适配器
        if (lv_achievement != null) {
            lv_achievement.setAdapter(adapter);
            lv_achievement.setEmptyView(layout_achievement_empty);
        }
    }

    /**
     *
     */
    private void initDate() {
        //打开数据库
        DBOperate dbOperate = new DBOperate();
        dbOperate.OpenDB(getActivity());

        //
        Cursor cursor = dbOperate.selectDB("select * from " + sqlData.TOTAL + " order by total desc");
        //定义排名
        int i = 0;
        while (cursor.moveToNext()) {
            i++;
            String stuId = cursor.getString(cursor.getColumnIndex("stuId"));
            String total = cursor.getString(cursor.getColumnIndex("total"));
            double d_total = Double.parseDouble(total);
            //修改数据库排名
            dbOperate.operationDB("update " + sqlData.TOTAL + " set ranking = '" + i + "' " +
                    "where stuId = '" + stuId + "'");
            mStudentData.add(new totalBean(stuId, i, d_total));
        }
        cursor.close();
        //关闭数据库
        dbOperate.CloseDB();
    }

    /**
     *
     */
    private void initView() {
        layout_achievement_empty = getActivity().findViewById(R.id.layout_achievement_empty);
        lv_achievement = getActivity().findViewById(R.id.li_achievement);
        iv_add_achievement = getActivity().findViewById(R.id.iv_add_achievement);
        iv_add_achievement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到添加成绩界面
                startActivity(new Intent(getActivity(), AddAchievementActivity.class));
            }
        });
    }
}
