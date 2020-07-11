package com.zyapp.sm.teacher.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.zyapp.sm.R;
import com.zyapp.sm.activity.AddAchievementActivity;
import com.zyapp.sm.teacher.base.BaseFragment;

/**
 * @项目名称 Student-Management
 * @包名 com.zyapp.sm.teacher.fragment
 * @作者 钟阳
 * @类名 StudentFragment
 * @日期 2020/7/6 19:12
 * @描述 TODO
 */
public class AchievementFragment extends BaseFragment {

    private ImageView iv_add_achievement;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_achievement;
    }

    @Override
    public void onStart() {
        super.onStart();
        //初始化
        initView();
    }

    /**
     *
     */
    private void initView() {
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
