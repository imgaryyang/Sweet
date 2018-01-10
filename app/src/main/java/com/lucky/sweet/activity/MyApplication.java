package com.lucky.sweet.activity;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.lucky.sweet.broadcastreceiver.NetBroadcastReceiver;

/**
 * Created by chn on 2018/1/10.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        NetBroadcastReceiver netBroadcastReceiver = new NetBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netBroadcastReceiver, intentFilter);
        netBroadcastReceiver.setNetEvent(new NetBroadcastReceiver.NetEvent() {
            @Override
            public void onNetChange(int netMobile) {
                switch (netMobile) {
                    case NetBroadcastReceiver.NETWORK_MOBILE:
                        Toast.makeText(context, "4G", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case NetBroadcastReceiver.NETWORK_NONE:
                        Toast.makeText(context, "暂无网络", Toast.LENGTH_SHORT).show();
                        break;
                    case NetBroadcastReceiver.NETWORK_WIFI:
                        Toast.makeText(context, "WI-FI", Toast.LENGTH_SHORT)
                                .show();
                        break;

                    default:
                        break;
                }

            }
        });
    }

    /**
     * 获取全局上下文
     */
    public static Context getContext() {
        return context;
    }

}
