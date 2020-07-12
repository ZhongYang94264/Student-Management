package com.zyapp.sm.teacher.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zyapp.sm.R;
import com.zyapp.sm.teacher.array.curriculumBean;

import java.util.List;

/**
 * @项目名称 Student-Management
 * @包名 com.zyapp.sm.teacher.adapter
 * @作者 钟阳
 * @类名 WorkAdapter
 * @日期 2020/7/12 11:18
 * @描述 TODO
 */
public class CurriculumAdapter extends ArrayAdapter<curriculumBean> {

    private final int resourceId;

    public CurriculumAdapter(@NonNull Context context, int resource, @NonNull List<curriculumBean> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }


    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //
        curriculumBean item = getItem(position);
        View view;
        ViewHolder holder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            //初始化控件
            holder = new ViewHolder();
            holder.tv_teacherName = view.findViewById(R.id.tv_c_teacherName);
            holder.tv_curriculumName = view.findViewById(R.id.tv_c_curriculumName);
            holder.tv_establish_time = view.findViewById(R.id.tv_c_create_time);
            //存放布局
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        //设置控件显示内容
        holder.tv_teacherName.setText(item.getTeacherName());
        holder.tv_curriculumName.setText(item.getCurriculumName());
        holder.tv_establish_time.setText(item.getCreateTime());

        return view;
    }

    class ViewHolder {
        TextView tv_teacherName, tv_curriculumName, tv_establish_time;
    }
}
