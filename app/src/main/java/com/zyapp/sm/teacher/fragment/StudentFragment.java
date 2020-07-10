package com.zyapp.sm.teacher.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.zyapp.sm.R;
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

    private ImageView iv_search, iv_menu;
    private LinearLayout layout_add_student, layout_add_class, layout_update_pwd, layout_exit;
    private PopupWindow mPopupWindow;
    private String mWork_num;

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
        mWork_num = getArguments().getString("work_num");
    }

    /**
     *
     */
    private void initView() {
        iv_menu = Objects.requireNonNull(getActivity()).findViewById(R.id.iv_menu);
        //注册监听
        iv_menu.setOnClickListener(this);
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
        }
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
                Toast.makeText(getActivity(), "点击了创建班级", Toast.LENGTH_SHORT).show();
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
