package com.gyj.exercise_0407.adapaters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gyj.exercise_0407.R;
import com.gyj.exercise_0407.bean.JsonBean;

import java.util.ArrayList;
import java.util.List;

/**
 * data:2017/4/7
 * author:郭彦君(Administrator)
 * function:
 */
public class MyBaseAdapter extends BaseAdapter {
    private Context context;
    private List<JsonBean.ListBean> list;

    public MyBaseAdapter(Context context, List<JsonBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.listview_layout, null);
            holder = new ViewHolder();
            holder.textView1 = (TextView) convertView.findViewById(R.id.text1);
            holder.textView2 = (TextView) convertView.findViewById(R.id.text2);
            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView1.setText(list.get(position).getSite_name());
        holder.textView2.setText(list.get(position).getAddress());

        return convertView;
    }

    class ViewHolder {
        TextView textView1;
        TextView textView2;
    }
}
