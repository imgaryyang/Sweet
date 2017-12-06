package com.lucky.sweet.moudel.ImStoreFragmentManager;

import android.content.Context;

import com.lucky.sweet.R;

import java.util.ArrayList;

/**
 * Created by c on 2017/12/6.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class ImStoreManager {
    private Context context;

    public ImStoreManager(Context context) {
        this.context = context;
    }

    public ArrayList<Integer> getAdInfoList() {
        ArrayList<Integer> strings = new ArrayList<Integer>();
        strings.add(R.mipmap.adtestone);
        strings.add(R.mipmap.adtesttwo);
        strings.add(R.mipmap.adtestthree);
        strings.add(R.mipmap.adtestfour);
        return strings;
    }
}
