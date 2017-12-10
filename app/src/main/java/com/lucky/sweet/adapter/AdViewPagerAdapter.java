package com.lucky.sweet.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lucky.sweet.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c on 2017/12/6.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class AdViewPagerAdapter extends PagerAdapter {
    List<Integer> datas;
    Context context;

    public AdViewPagerAdapter(Context context, List<Integer> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout
                .iitem_viewpager_ad, null);
        ImageView imv_ab = view.findViewById(R.id.imv_ab);
        Glide.with(context).load(datas.get(position)).diskCacheStrategy
                (DiskCacheStrategy.ALL).into(imv_ab);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }
}
