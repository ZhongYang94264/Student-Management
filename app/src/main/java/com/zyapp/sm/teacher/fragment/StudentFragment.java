package com.zyapp.sm.teacher.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.zyapp.sm.R;
import com.zyapp.sm.activity.AddClassActivity;
import com.zyapp.sm.activity.AddStudentActivity;
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
public class StudentFragment extends BaseFragment implements View.OnClickListener {

    private ImageView iv_menu;
    private LinearLayout layout_add_student, layout_add_class, layout_update_pwd, layout_exit, layout_my_student,
            layout_my_class;
    private PopupWindow mPopupWindow;
    private String mWork_num;
    private MyStudentFragment mStudentFragment;
    private MyClassFragment mClassFragment;
    private FragmentManager mFm;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_student;
    }

    @Override
    public void onStart() {
        super.onStart();
        //初始化控件
        initView();
        //接收教师工号
        if (getArguments() != null) {
            mWork_num = getArguments().getString("work_num");
        }
        //初始化碎片
        initFragment();
        //传递工号
        Bundle bundle = new Bundle();
        bundle.putString("work_num", mWork_num);
        mStudentFragment.setArguments(bundle);
        mClassFragment.setArguments(bundle);
    }

    /**
     *
     */
    private void initFragment() {
        mStudentFragment = new MyStudentFragment();
        mClassFragment = new MyClassFragment();
        //拿到开启事务的支持包
        mFm = getActivity().getSupportFragmentManager();
        //设置默认显示页
        switchFragment(mStudentFragment);
    }

    /**
     *
     */
    private void initView() {
        iv_menu = Objects.requireNonNull(getActivity()).findViewById(R.id.iv_menu);
        layout_my_student = getActivity().findViewById(R.id.layout_my_student);
        layout_my_class = getActivity().findViewById(R.id.layout_my_class);
        //注册监听
        iv_menu.setOnClickListener(this);
        layout_my_student.setOnClickListener(this);
        layout_my_class.setOnClickListener(this);
    }

    /**
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_menu:
                initPopupWindow(v);
                break;
            case R.id.iv_search:
                break;
            case R.id.layout_my_student:
                switchFragment(mStudentFragment);
                break;
            case R.id.layout_my_class:
                switchFragment(mClassFragment);
                break;
        }
    }

    /**
     * @param studentFragment
     */
    private void switchFragment(BaseFragment studentFragment) {
        //开启事务
        FragmentTransaction transaction = mFm.beginTransaction();

        //切换
        transaction.replace(R.id.fl_student, studentFragment);

        //提交事务
        transaction.commit();
    }

    private void initPopupWindow(View v) {
        //加载布局
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_teacher_popupwindow, null,
                false);
        //构建一个PopupWindow,参数依次是 加载的View、宽、高
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //初始化组件
        layout_add_student = view.findViewById(R.id.layout_add_student);
        layout_add_class = view.findViewById(R.id.layout_add_class);
        layout_update_pwd = view.findViewById(R.id.layout_update_pwd);
        layout_exit = view.findViewById(R.id.layout_exit);
        //修改背景
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        //显示位置
        mPopupWindow.showAsDropDown(iv_menu, 50, 0);
        //初始化弹窗子条目的点击事件
        initListener();
    }

    private void initListener() {
        layout_add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到添加学生界面
                Intent a_intent = new Intent(getActivity(), AddStudentActivity.class);
                a_intent.putExtra("work_num", mWork_num);
                startActivity(a_intent);
                mPopupWindow.dismiss();
            }
        });
        layout_add_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到添加学生界面
                Intent a_intent = new Intent(getActivity(), AddClassActivity.class);
                a_intent.putExtra("work_num", mWork_num);
                startActivity(a_intent);
                mPopupWindow.dismiss();
            }
        });
        layout_update_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "点击了修改密码", Toast.LENGTH_SHORT).show();
                mPopupWindow.dismiss();
            }
        });
        layout_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "点击了退出", Toast.LENGTH_SHORT).show();
                mPopupWindow.dismiss();
            }
        });
    }
}
