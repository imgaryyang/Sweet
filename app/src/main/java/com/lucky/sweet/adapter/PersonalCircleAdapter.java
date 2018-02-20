package com.lucky.sweet.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lucky.sweet.R;
import com.lucky.sweet.entity.CircleDetail;
import com.lucky.sweet.views.CircleImageView;

import java.util.List;

/**
 * Created by Qiuyue on 2018/1/18.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class PersonalCircleAdapter extends BaseAdapter {
    List<CircleDetail.CommentBean> datas;
    private Context context;

    public PersonalCircleAdapter(List<CircleDetail.CommentBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_personalcircle, null);
            viewHolder = new ViewHolder();
            viewHolder.imv_head = convertView.findViewById(R.id.imv_head);
            viewHolder.name = convertView.findViewById(R.id.tv_name);
            viewHolder.con = convertView.findViewById(R.id.tv_content);
            viewHolder.time = convertView.findViewById(R.id.tv_time);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(datas.get(position).getNikcname());
        viewHolder.con.setText(datas.get(position).getContent());
        viewHolder.time.setText(datas.get(position).getCreate_time());

        viewHolder.imv_head.setmDrawShapeType(CircleImageView.SHAPE_CIRCLE);

        return convertView;
    }

    static public class ViewHolder {
        CircleImageView imv_head;
        TextView name;
        TextView con;
        TextView time;

    }


}
