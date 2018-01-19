package com.lucky.sweet.handler;

import android.os.Handler;
import android.os.Message;

import com.lucky.sweet.activity.StoreParticularInfoActivity;
import com.lucky.sweet.entity.StoreDetailedInfo;

/**
 * Created by chn on 2018/1/17.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */


public class StoreParticularInfoHandler extends Handler {
    private StoreParticularInfoActivity activity;

    public final static int UPDATA = 0;


    public StoreParticularInfoHandler(StoreParticularInfoActivity activity) {
        this.activity = activity;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case UPDATA:

                activity.upData((StoreDetailedInfo) msg.obj);

                break;
        }

    }


}


