package com.lucky.sweet.model.particularinfo;

import com.google.gson.Gson;
import com.lucky.sweet.activity.StoreParticularInfoActivity;
import com.lucky.sweet.entity.StoreDetailedInfo;
import com.lucky.sweet.properties.Properties;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by c on 2017/12/13.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class ParticularInfoManager {
    private StoreParticularInfoActivity activity;

    public ParticularInfoManager(StoreParticularInfoActivity activity) {
        this.activity = activity;
        getParticularInfo();
    }

    private void getParticularInfo() {
        new Thread() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request build = new Request.Builder().url(Properties.STOREDETAILEDPATH).build();
                Response response = null;
                try {
                    response = okHttpClient.newCall(build).execute();
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                        Gson gson = new Gson();
                        final StoreDetailedInfo storeDetailedInfo = gson.fromJson(string, StoreDetailedInfo.class);
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                activity.upData(storeDetailedInfo.getMerinfo());
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
