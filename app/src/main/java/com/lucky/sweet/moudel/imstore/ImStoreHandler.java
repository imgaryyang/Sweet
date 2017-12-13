package com.lucky.sweet.moudel.imstore;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.lucky.sweet.entity.StoreShowInfo;
import com.lucky.sweet.entity.WeatherInfo;
import com.lucky.sweet.fragment.ImStoreFragment;

import java.util.List;

/**
 * Created by c on 2017/12/13.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class ImStoreHandler extends Handler {

    public static final int UPDATAWEATHER = 1;

    public static final int UPSHOWINFO = 2;

    private ImStoreFragment fragment;
    private Context context;

    public ImStoreHandler(ImStoreFragment fragment) {
        this.fragment = fragment;
        context = fragment.getContext();
    }


    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case UPDATAWEATHER:
                WeatherInfo weatherInfo = (WeatherInfo) msg.obj;
                String cond_txt = weatherInfo.getHeWeather6().get(0).getNow
                        ().getCond_txt();
                String tmp = weatherInfo.getHeWeather6().get(0).getNow
                        ().getTmp();
                cond_txt = cond_txt + " " + tmp;
                int resId = context.getResources().getIdentifier("w" +
                        weatherInfo.getHeWeather6().get(0).getNow().getCond_code(), "mipmap", context.getPackageName());
                fragment.updataWeather(cond_txt, resId);
                break;
            case UPSHOWINFO:
                StoreShowInfo info = (StoreShowInfo) msg.obj;
                List<StoreShowInfo.foodBean> food = info.getFood();
                fragment.setShowInfo(food);
                break;


            default:
                break;
        }

    }
}

