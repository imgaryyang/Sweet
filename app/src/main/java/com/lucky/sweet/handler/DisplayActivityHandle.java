package com.lucky.sweet.handler;

import android.os.Handler;
import android.os.Message;

import com.lucky.sweet.activity.StoreDisplatActivity;
import com.lucky.sweet.entity.StoreDisplayInfo;
import com.lucky.sweet.entity.StoreDisplaySearchEntity;

/**
 * Created by chn on 2018/1/29.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class DisplayActivityHandle extends Handler {
    public static final int DISPLAYSEARCHINFO = 1;
    public static final int DISPLAYINFO = 2;
    private StoreDisplatActivity activity;

    public DisplayActivityHandle(StoreDisplatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case DISPLAYSEARCHINFO:
                activity.upDataSearchInfo((StoreDisplaySearchEntity) msg.obj);
                break;
            case DISPLAYINFO:
                activity.upDataDisplayInfo((StoreDisplayInfo) msg.obj);
                break;
            default:
                break;
        }

    }
}
