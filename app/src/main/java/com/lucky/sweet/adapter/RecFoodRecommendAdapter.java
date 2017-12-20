package com.lucky.sweet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucky.sweet.R;

/**
 * Created by chn on 2017/12/20.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class RecFoodRecommendAdapter extends RecyclerView.Adapter {
    private Context context;
    private final LayoutInflater inflater;

    public RecFoodRecommendAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new mViewHolder(inflater.inflate(R.layout.item_viewpager_foodinfo, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 100;
    }

    static class mViewHolder extends RecyclerView.ViewHolder {
        ImageView imv_photo;
        TextView tv_info;
        TextView tv_comment;
        TextView tv_recreation;
        TextView tv_dis;


        public mViewHolder(View itemView) {
            super(itemView);
            imv_photo = itemView.findViewById(R.id.imv_photo);
            tv_info = itemView.findViewById(R.id.tv_info);
            tv_comment = itemView.findViewById(R.id.tv_comment);
            tv_recreation = itemView.findViewById(R.id.tv_recreation);
            tv_dis = itemView.findViewById(R.id.tv_dis);
        }
    }
}
