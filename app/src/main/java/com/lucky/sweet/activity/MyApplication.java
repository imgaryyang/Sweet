package com.lucky.sweet.activity;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.lucky.sweet.broadcastreceiver.NetBroadcastReceiver;
import com.lucky.sweet.properties.Properties;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chn on 2018/1/10.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class MyApplication extends Application {
    //todo 选取session并且判读当为“”时，则视为站务登陆
    private static Context context;

    public static String sessionId = "";

    public static String CURRENT_CITY = "大连市";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        initSession();
        initBroadCastReceiver();
    }


    private void initBroadCastReceiver() {
        NetBroadcastReceiver netBroadcastReceiver = new NetBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netBroadcastReceiver, intentFilter);
        netBroadcastReceiver.setNetEvent(new NetBroadcastReceiver.NetEvent() {
            @Override
            public void onNetChange(int netMobile) {
                switch (netMobile) {
                    case NetBroadcastReceiver.NETWORK_MOBILE:

                        break;
                    case NetBroadcastReceiver.NETWORK_NONE:
                        Toast.makeText(context, "暂无网络", Toast.LENGTH_SHORT).show();
                        break;
                    case NetBroadcastReceiver.NETWORK_WIFI:

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

    public static void setSessionID(String id) {
        sessionId = id;
    }

    public static void initSession() {
        final SharedPreferences config = context.getSharedPreferences("config",
                MODE_PRIVATE);
        if (config.getBoolean("logined", false)) {
            final String id = config.getString("Id", "");
            final String psw = config.getString("Psw", "");
            new Thread() {
                @Override
                public void run() {

                    try {
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder().url
                                (Properties.LOGINPATH).post(new FormBody
                                .Builder().add("username", id).add
                                ("password", psw).build()).build();
                        Response response = client.newCall(request).execute();
                        if (response.isSuccessful()) {
                            sessionId = response.body().string();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }.start();

        }


    }

    public static void setCurrenCity(String city) {
        CURRENT_CITY = city;
    }
}
