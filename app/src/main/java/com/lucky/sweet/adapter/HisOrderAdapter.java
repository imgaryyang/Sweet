package com.lucky.sweet.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lucky.sweet.R;

import java.util.List;

/**
 * Created by Qiuyue on 2018/3/2.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class HisOrderAdapter extends BaseAdapter {

    private List<String> datas;
    private Context context;

    public HisOrderAdapter(List<String> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }
    static public class ViewHolder {
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HisOrderAdapter.ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_hisorder, null);
            viewHolder = new HisOrderAdapter.ViewHolder();
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (HisOrderAdapter.ViewHolder) convertView.getTag();
        }

        return convertView;
    }
}
