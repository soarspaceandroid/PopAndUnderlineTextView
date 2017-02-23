package com.example.administrator.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class SelectListAdapter extends BaseAdapter {
    private Context context;
    private List<MenuItemBean> listData;

    public SelectListAdapter(Context context, List<MenuItemBean> listData) {
        this.context = context;
        this.listData = listData;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_select_list, null);
            holder = new ViewHolder();
            assert convertView != null;
            holder.tvSelect = (TextView) convertView.findViewById(R.id.tvSelect);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvSelect.setText(listData.get(position).getMenuString());
        holder.tvSelect.setTextColor(listData.get(position).getColor());
        return convertView;
    }

    public static class ViewHolder {
        public TextView tvSelect;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
