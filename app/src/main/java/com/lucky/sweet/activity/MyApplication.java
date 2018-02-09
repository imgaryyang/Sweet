package com.lucky.sweet.activity;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.google.gson.Gson;
import com.lucky.sweet.broadcastreceiver.NetBroadcastReceiver;
import com.lucky.sweet.entity.OssBean;
import com.lucky.sweet.properties.Properties;
import com.lucky.sweet.utility.HttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.lucky.sweet.properties.Properties.END_POINT;
import static com.lucky.sweet.properties.Properties.REQUEST_PATH;

/**
 * Created by chn on 2018/1/10.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class MyApplication extends Application {
    //todo 选取session并且判读当为“”时，则视为暂无登陆
    private static Context context;
    private static OSSClient ossClient;

    public static String sessionId = "";

    public static String CURRENT_CITY = "大连市";
    public static String USER_ID = "";

    public static String KEY_ID = "";
    public static String SECRET_KEY_ID = "";
    public static String TOKEN = "";


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        initSession();

        initOssClient();

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

    public static OSSClient getOSSClient() {
        return ossClient;
    }

    public static void setSessionID(String id) {
        sessionId = id;
    }

    public static void setCurrenCity(String city) {
        CURRENT_CITY = city;
    }

    public static void initSession() {
        final SharedPreferences config = context.getSharedPreferences("config",
                MODE_PRIVATE);
        if (config.getBoolean("logined", false)) {
            USER_ID = config.getString("Id", "");
            final String psw = config.getString("Psw", "");
            new Thread() {
                @Override
                public void run() {

                    try {
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder().url
                                (Properties.LOGINPATH).post(new FormBody
                                .Builder().add("username", USER_ID).add
                                ("password", psw).build()).build();
                        Response response = client.newCall(request).execute();
                        if (response.isSuccessful()) {
                            sessionId = response.body().string();
                            System.out.println("session:" + sessionId);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }.start();

        }


    }

    public static void initOssClient() {

        HttpUtils.sendOkHttpRequest(REQUEST_PATH, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                OssBean ossBean = gson.fromJson(response.body().string
                        (), OssBean.class);
                String KEY_ID = ossBean.getCredentials().getAccessKeyId();
                String TOKEN = ossBean.getCredentials().getSecurityToken();
                String SECRET_KEY_ID = ossBean.getCredentials().getAccessKeySecret();
                OSSCredentialProvider credentialProvider = new
                        OSSStsTokenCredentialProvider(KEY_ID, SECRET_KEY_ID, TOKEN
                );
                ClientConfiguration conf = new ClientConfiguration();
                conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
                conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
                conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个
                conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次

                ossClient = new OSSClient(context, END_POINT, credentialProvider);

            }
        });
    }


}
