package com.zyapp.sm.teacher.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.zyapp.sm.R;
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

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_student;
    }

    @Override
    public void onStart() {
        super.onStart();
        //初始化控件
        initView();
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

    @SuppressLint("ResourceAsColor")
    private void initPopupWindow(View v) {
        //加载布局
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_teacher_popupwindow, null,
                false);
        //初始化组件

        //构建一个PopupWindow,参数依次是 加载的View、宽、高
        PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //加载动画
        //
        popupWindow.setTouchable(true);
        //修改背景
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //显示位置
        popupWindow.showAsDropDown(v,50,0);

    }
}
