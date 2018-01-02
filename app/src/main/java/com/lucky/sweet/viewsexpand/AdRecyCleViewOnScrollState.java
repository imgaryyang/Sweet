package com.lucky.sweet.viewsexpand;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lucky.sweet.activity.StoreDisplatActivity;

/**
 * Created by chn on 2018/1/1.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class AdRecyCleViewOnScrollState extends RecyclerView.OnScrollListener {
    private boolean flag = false;
    private Context context;
    private int type;

    public final static int FUN = 1;
    public final static int FOOD = 2;

    public AdRecyCleViewOnScrollState(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            int lastVisiblePosition = manager.findLastVisibleItemPosition();
            if (lastVisiblePosition >= manager
                    .getItemCount() - 1)
                if (flag) {
                    Intent intent;

                    switch (type) {
                        case FOOD:
                            intent = new Intent(context, StoreDisplatActivity.class);
                            intent.putExtra("tv_moreFood", "Food");
                            context.startActivity(intent);
                            break;
                        case FUN:
                            intent = new Intent(context, StoreDisplatActivity.class);
                            intent.putExtra("tv_moreRelax", "Relax");
                            context.startActivity(intent);
                            break;
                    }
                    flag = false;
                    return;
                }
            flag = true;
        }
    }
}
