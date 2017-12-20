package com.lucky.sweet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lucky.sweet.R;

/**
 * Created by chn on 2017/12/20.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class RecminiAdAdapter extends RecyclerView.Adapter {
    private Context context;
    private final LayoutInflater inflater;

    public RecminiAdAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new mViewHolder(inflater.inflate(R.layout
                .item_gridview_adshouw, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    static class mViewHolder extends RecyclerView.ViewHolder {
        ImageView imv_rec_ad;


        public mViewHolder(View itemView) {
            super(itemView);
            imv_rec_ad = itemView.findViewById(R.id.imv_rec_ad);
        }
    }

}
