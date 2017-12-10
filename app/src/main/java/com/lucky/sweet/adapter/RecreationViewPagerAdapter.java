package com.lucky.sweet.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucky.sweet.R;
import com.lucky.sweet.entity.RecreationInfo;

import java.util.List;

/**
 * Created by c on 2017/12/10.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class RecreationViewPagerAdapter extends PagerAdapter {
    private List<RecreationInfo> datas;
    private Context context;

    public RecreationViewPagerAdapter(Context context, List<RecreationInfo> datas) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout
                .item_viewpager_foodinfo, null);
        container.addView(view);
        return view;
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
