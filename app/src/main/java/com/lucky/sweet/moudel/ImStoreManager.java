package com.lucky.sweet.moudel;

import android.content.Context;

import com.lucky.sweet.R;
import com.lucky.sweet.entity.RecreationInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c on 2017/12/6.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class ImStoreManager {
    private Context context;
    private Object foodList;

    public ImStoreManager(Context context) {
        this.context = context;
    }

    public ArrayList<Integer> getAdInfoList() {
        ArrayList<Integer> strings = new ArrayList<Integer>();
        strings.add(R.mipmap.test_adone);
        strings.add(R.mipmap.test_adtwo);
        strings.add(R.mipmap.test_adthird);
        strings.add(R.mipmap.test_adtwo);
        return strings;
    }


    public List<RecreationInfo> getFoodList() {
        List<RecreationInfo> recreationInfos = new ArrayList<>();
        recreationInfos.add(new RecreationInfo());
        recreationInfos.add(new RecreationInfo());
        recreationInfos.add(new RecreationInfo());
        recreationInfos.add(new RecreationInfo());
        return recreationInfos;
    }
}
