package com.lucky.sweet.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lucky.sweet.R;
import com.lucky.sweet.entity.FlowPeople;

import java.util.List;

/**
 * Created by C on 2018/3/14.
 */

public class FlowFriendAdapter extends BaseAdapter {
    private List<FlowPeople.AttentListBean> attent_list;
    private Context context;

    public FlowFriendAdapter(List<FlowPeople.AttentListBean> attent_list, Context context) {
        this.attent_list = attent_list;
        this.context = context;
    }

    @Override

    public int getCount() {
        return attent_list.size();
    }

    @Override
    public Object getItem(int position) {
        return attent_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        FlowPeople.AttentListBean attentListBean = attent_list.get(position);
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_flow_friend, null);
            viewHolder = new ViewHolder();
            viewHolder.name = convertView.findViewById(R.id.tv_flow_friend);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(attentListBean.getNickname());
        return convertView;
    }

    static public class ViewHolder {
        TextView name;
    }

}
