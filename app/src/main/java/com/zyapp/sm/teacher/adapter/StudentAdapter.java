package com.zyapp.sm.teacher.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zyapp.sm.R;
import com.zyapp.sm.teacher.array.studentBean;

import java.util.List;

/**
 * @项目名称 Student-Management
 * @包名 com.zyapp.sm.teacher.adapter
 * @作者 钟阳
 * @类名 StudentAdapter
 * @日期 2020/7/10 15:10
 * @描述 TODO
 */
public class StudentAdapter extends ArrayAdapter<studentBean> {

    private final int resourId;

    public StudentAdapter(@NonNull Context context, int resource, @NonNull List<studentBean> objects) {
        super(context, resource, objects);
        resourId = resource;
    }
    //复写getView方法

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //获取条目操作对象
        studentBean studentData = getItem(position);
        //
        View view;
        ViewHolder holder;
        //加载布局
        if (convertView == null) {
            //通过LayoutInflater加载布局,并存放到view中
            view = LayoutInflater.from(getContext()).inflate(resourId, parent, false);
            //通过ViewHolder加载控件
            holder = new ViewHolder();
            holder.iv_head = view.findViewById(R.id.iv_li_head);
            holder.tv_name = view.findViewById(R.id.tv_student_name);
            holder.tv_class = view.findViewById(R.id.tv_student_class);
            //存放布局
            view.setTag(holder);
        } else {
            //直接加载布局
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        //设置控件的展示信息
        String gender = studentData.getGender();
        if (gender.equals("男")) {
            holder.iv_head.setImageResource(R.mipmap.icon_li_male);
        }
        if (gender.equals("女")) {
            holder.iv_head.setImageResource(R.mipmap.icon_li_female);
        }
        holder.tv_name.setText(studentData.getStudent_name());
        holder.tv_class.setText(studentData.getClass_name());
        //最后返回view
        return view;
    }

    /**
     *
     */
    class ViewHolder {
        ImageView iv_head;
        TextView tv_name;
        TextView tv_class;
    }
}
