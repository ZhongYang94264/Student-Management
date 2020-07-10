package com.zyapp.sm.teacher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zyapp.sm.R;
import com.zyapp.sm.teacher.array.classBean;

import java.util.List;

/**
 * @项目名称 Student-Management
 * @包名 com.zyapp.sm.teacher.adapter
 * @作者 钟阳
 * @类名 ClassAdapter
 * @日期 2020/7/10 19:02
 * @描述 TODO
 */
public class ClassAdapter extends ArrayAdapter<classBean> {

    private final int resourceId;

    public ClassAdapter(@NonNull Context context, int resource, @NonNull List<classBean> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        classBean classData = getItem(position);
        //
        View view;
        ViewHolder holder;
        //加载布局
        if (convertView == null) {
            //通过LayoutInflater加载布局,并存放到view中
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            //通过ViewHolder加载控件
            holder = new ViewHolder();
            holder.tv_class_name = view.findViewById(R.id.tv_class_name);
            holder.tv_class_num = view.findViewById(R.id.tv_class_num);
            //存放布局
            view.setTag(holder);
        } else {
            //直接加载布局
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        holder.tv_class_name.setText(classData.getClass_name());
        holder.tv_class_num.setText(classData.getClass_num());
        //最后返回view
        return view;
    }

    /**
     *
     */
    class ViewHolder {
        TextView tv_class_name;
        TextView tv_class_num;
    }
}
