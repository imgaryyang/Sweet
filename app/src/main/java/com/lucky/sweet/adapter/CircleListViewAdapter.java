package com.lucky.sweet.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lucky.sweet.R;
import com.lucky.sweet.entity.CirclePicData;
import com.lucky.sweet.views.CircleImageView;
import com.lucky.sweet.widgets.ImageViewWatcher.MessagePicturesLayout;
import com.lucky.sweet.widgets.ImageViewWatcher.SquareImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c on 2017/12/13.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class CircleListViewAdapter extends BaseAdapter {
    private List<String> datas;
    private Context context;
    private final ArrayList<Integer> integers;
    private MessagePicturesLayout.Callback mcallback;

    public CircleListViewAdapter(List<String> datas, Context context) {
        this.datas = datas;
        this.context = context;


        integers = new ArrayList<>();
        integers.add(R.mipmap.test1);
        integers.add(R.mipmap.test2);
        integers.add(R.mipmap.test3);
        integers.add(R.mipmap.test4);
    }

    public CircleListViewAdapter setCallBack(MessagePicturesLayout.Callback mcallback){
        this.mcallback = mcallback;
        return this;
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
            convertView = View.inflate(context, R.layout.item_circle, null);
            viewHolder = new ViewHolder();
            viewHolder.imv_head = convertView.findViewById(R.id.imv_head);
            viewHolder.messPicLayout = convertView.findViewById(R.id.l_pictures);
            viewHolder.i_picture = convertView.findViewById(R.id.i_picture);
            viewHolder.messPicLayout.setCallback(mcallback);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imv_head.setmDrawShapeType(CircleImageView.SHAPE_CIRCLE);

        viewHolder.messPicLayout.set(integers);

        return convertView;

    }

    static public class ViewHolder {
        TextView type;
        CircleImageView imv_head;
        MessagePicturesLayout messPicLayout;
        SquareImageView i_picture;
    }

}
