package com.lucky.sweet.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lucky.sweet.R;
import com.lucky.sweet.activity.StoreDisplatActivity;
import com.lucky.sweet.activity.StoreParticularInfoActivity;
import com.lucky.sweet.entity.StoreShowInfo;

import java.util.List;

/**
 * Created by c on 2017/12/10.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class RecreationViewPagerAdapter extends PagerAdapter {
    private List<StoreShowInfo.foodBean> datas;
    private Context context;

    public RecreationViewPagerAdapter(Context context, List<StoreShowInfo.foodBean> datas) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_viewpager_foodinfo, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StoreParticularInfoActivity
                        .class);
                intent.putExtra("shopid", position);
                context.startActivity(intent);
            }
        });
        ImageView imv_photo = view.findViewById(R.id.imv_photo);
        Glide.with(context).load(datas.get(position).getPho_url()).into(imv_photo);
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
