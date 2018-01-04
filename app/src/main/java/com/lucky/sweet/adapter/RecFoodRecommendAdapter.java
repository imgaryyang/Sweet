package com.lucky.sweet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private int type;

    public final static int FOOD = 0;
    public final static int FUN = 1;


    public RecFoodRecommendAdapter(Context context, int type) {
        this.context = context;
        this.type = type;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getItemViewType(int position) {

        return position;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 4) {
            return new mMoreHolder(inflater.inflate(R.layout.item_recycle_more, parent,
                    false));
        }
        return new mViewHolder(inflater.inflate(R.layout.item_viewpager_foodinfo, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof mViewHolder) {
            mViewHolder mViewHolder = (mViewHolder) holder;
            mViewHolder.ll_cover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != onItemClickListener) {
                        onItemClickListener.onItemClickListener(position, 0);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    static class mViewHolder extends RecyclerView.ViewHolder {
        ImageView imv_photo;
        TextView tv_info;
        TextView tv_comment;
        TextView tv_recreation;
        TextView tv_dis;
        LinearLayout ll_cover;

        public mViewHolder(View itemView) {
            super(itemView);
            imv_photo = itemView.findViewById(R.id.imv_photo);
            tv_info = itemView.findViewById(R.id.tv_info);
            tv_comment = itemView.findViewById(R.id.tv_comment);
            tv_recreation = itemView.findViewById(R.id.tv_recreation);
            tv_dis = itemView.findViewById(R.id.tv_dis);
            ll_cover = itemView.findViewById(R.id.ll_cover);
        }
    }

    static class mMoreHolder extends RecyclerView.ViewHolder {

        public mMoreHolder(View itemView) {
            super(itemView);

        }
    }

  public   interface OnItemClickListener {
         void onItemClickListener(int position, int shopId);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
