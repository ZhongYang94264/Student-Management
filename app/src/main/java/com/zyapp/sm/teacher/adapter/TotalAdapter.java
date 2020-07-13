package com.zyapp.sm.teacher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zyapp.sm.R;
import com.zyapp.sm.teacher.array.totalBean;

import java.util.List;

/**
 * @项目名称 Student-Management
 * @包名 com.zyapp.sm.teacher.adapter
 * @作者 钟阳
 * @类名 TotalAdapter
 * @日期 2020/7/11 11:16
 * @描述 TODO
 */
public class TotalAdapter extends ArrayAdapter<totalBean> {

    private final int resourceId;

    public TotalAdapter(@NonNull Context context, int resource, @NonNull List<totalBean> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        totalBean item = getItem(position);
        View view;
        ViewHolder holder;
        if (convertView == null) {
            //通过layoutInflater加载布局
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            //通过ViewHolder加载控件
            holder = new ViewHolder();
            holder.tv_li_order = view.findViewById(R.id.tv_li_order);
            holder.tv_li_student_id = view.findViewById(R.id.tv_li_student_id);
            holder.tv_li_total = view.findViewById(R.id.tv_li_total);
            //存放布局
            view.setTag(holder);
        } else {
            //直接加载布局
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        //设置控件显示内容
        int order = item.getOrder();//获得排名
        holder.tv_li_order.setText(String.valueOf(order));
        holder.tv_li_student_id.setText(item.getStudent_id());
        double total = item.getTotal();
        holder.tv_li_total.setText(String.valueOf(total));
        //返回view
        return view;
    }

    //
    class ViewHolder {
        TextView tv_li_order;
        TextView tv_li_student_id;
        TextView tv_li_total;
    }
}
