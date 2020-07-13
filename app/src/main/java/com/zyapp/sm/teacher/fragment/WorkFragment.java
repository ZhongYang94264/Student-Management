package com.zyapp.sm.teacher.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.zyapp.sm.R;
import com.zyapp.sm.activity.EstablishCurriculumActivity;
import com.zyapp.sm.activity.PublishJobActivity;
import com.zyapp.sm.activity.WorkSearchActivity;
import com.zyapp.sm.sql.DBOperate;
import com.zyapp.sm.sql.sqlData;
import com.zyapp.sm.teacher.base.BaseFragment;

import java.util.Objects;

/**
 * @项目名称 Student-Management
 * @包名 com.zyapp.sm.teacher.fragment
 * @作者 钟阳
 * @类名 StudentFragment
 * @日期 2020/7/6 19:12
 * @描述 TODO
 */
public class WorkFragment extends BaseFragment {

    private static final String TAG = "WorkFragment";
    private LinearLayout layout_publish_job, layout_to_be_corrected;
    private PublishJobFragment mPublishJobFragment;
    private MyCurriculumFragment mMyCurriclumFragment;
    private FragmentManager mFm;
    private ImageView iv_publish_job, iv_establish_curriculum;
    private static String mWork_num;
    private EditText et_work_search;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_work;
    }

    @Override
    public void onStart() {
        super.onStart();
        //初始化控件
        initView();
        //监听事件
        initListener();
        //初始化碎片
        initFragment();
        //接收教师工号
        if (getArguments() != null) {
            mWork_num = getArguments().getString("work_num");
            Log.d(TAG, "接收到教师工号 ==> " + mWork_num);
        }
        //传递工号
        Bundle bundle = new Bundle();
        bundle.putString("work_num", mWork_num);
        mPublishJobFragment.setArguments(bundle);
        mMyCurriclumFragment.setArguments(bundle);
    }

    private void initFragment() {
        mPublishJobFragment = new PublishJobFragment();
        mMyCurriclumFragment = new MyCurriculumFragment();
        //拿到开启事务的支持包
        mFm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        //设置默认显示页
        switchFragment(mPublishJobFragment);
    }


    private void initListener() {
        layout_publish_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(mPublishJobFragment);
            }
        });
        layout_to_be_corrected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(mMyCurriclumFragment);
            }
        });
        iv_publish_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到发布作业界面
                Intent p_intent = new Intent(getActivity(), PublishJobActivity.class);
                p_intent.putExtra("work_num", mWork_num);
                startActivity(p_intent);
            }
        });
        iv_establish_curriculum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到创建课程界面
                Intent e_intent = new Intent(getActivity(), EstablishCurriculumActivity.class);
                e_intent.putExtra("work_num", mWork_num);
                startActivity(e_intent);
            }
        });
    }


    private void switchFragment(BaseFragment baseFragment) {
        //开启事务
        FragmentTransaction transaction = mFm.beginTransaction();

        //切换
        transaction.replace(R.id.fl_content, baseFragment);

        //提交事务
        transaction.commit();
    }


    private void initView() {
        layout_publish_job = Objects.requireNonNull(getActivity()).findViewById(R.id.layout_publish_job);
        layout_to_be_corrected = getActivity().findViewById(R.id.layout_to_be_corrected);
        iv_publish_job = getActivity().findViewById(R.id.iv_publish_job);
        iv_establish_curriculum = getActivity().findViewById(R.id.iv_establish_curriculum);
        et_work_search = getActivity().findViewById(R.id.et_work_search);
        //
        et_work_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WorkSearchActivity.class));
            }
        });
    }
}
