package com.lucky.sweet.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lucky.sweet.R;
import com.lucky.sweet.views.CircleImageView;

import java.util.List;

/**
 * Created by Qiuyue on 2018/3/8.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class MyLikeStoreAdapter extends BaseAdapter {
    private List<String> datas;
    private Context context;

    public MyLikeStoreAdapter(List<String> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }
    static public class ViewHolder {
        CircleImageView imv_storeHead;
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
        MyLikeStoreAdapter.ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_mylikestore, null);
            viewHolder = new ViewHolder();
            viewHolder.imv_storeHead = convertView.findViewById(R.id.imv_storeHead);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyLikeStoreAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.imv_storeHead.setmDrawShapeType(CircleImageView.SHAPE_CIRCLE);

        return convertView;
    }


}
