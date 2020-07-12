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
import com.zyapp.sm.teacher.array.workBean;

import java.util.List;

/**
 * @项目名称 Student-Management
 * @包名 com.zyapp.sm.teacher.adapter
 * @作者 钟阳
 * @类名 WorkAdapter
 * @日期 2020/7/12 11:18
 * @描述 TODO
 */
public class WorkAdapter extends ArrayAdapter<workBean> {

    private final int resourceId;

    public WorkAdapter(@NonNull Context context, int resource, @NonNull List<workBean> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //
        workBean item = getItem(position);
        View view;
        ViewHolder holder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            //初始化控件
            holder = new ViewHolder();
            holder.tv_work_name = view.findViewById(R.id.tv_work_name);
            holder.tv_publish_time = view.findViewById(R.id.tv_publish_time);
            holder.tv_les_name = view.findViewById(R.id.tv_les_name);
            //存放布局
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        //设置控件显示内容
        holder.tv_work_name.setText(item.getWork_name());
        holder.tv_publish_time.setText(item.getPublish_time());
        holder.tv_les_name.setText(item.getLes_name());
        return view;
    }

    class ViewHolder {
        TextView tv_work_name, tv_publish_time, tv_les_name;
    }
}
