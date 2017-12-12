package com.lucky.sweet.moudel.storedisplay;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

/**
 * Created by c on 2017/12/12.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class StoreDisplatHandler extends Handler {
    private Context context;

    public StoreDisplatHandler(Context context) {
        this.context = context;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                break;
            case 3:
                break;
            case 2:
                break;

            default:
                break;
        }

    }
}
