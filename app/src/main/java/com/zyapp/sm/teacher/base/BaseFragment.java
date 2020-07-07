package com.zyapp.sm.teacher.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @项目名称 Student-Management
 * @包名 com.zyapp.sm.teacher.base
 * @作者 钟阳
 * @类名 BaseFragment
 * @日期 2020/7/6 19:12
 * @描述 TODO
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return loadRooatView(inflater, container, savedInstanceState);
    }

    protected View loadRooatView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int resId = getRootViewResId();
        return inflater.inflate(resId, container, false);
    }

    protected abstract int getRootViewResId();
}
