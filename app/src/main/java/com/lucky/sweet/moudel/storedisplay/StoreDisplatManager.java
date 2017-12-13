package com.lucky.sweet.moudel.storedisplay;

import android.app.Activity;
import android.content.Context;
import android.os.Message;
import android.widget.Toast;

import com.lucky.sweet.entity.StoreShowInfo;
import com.lucky.sweet.properties.Properties;
import com.lucky.sweet.utility.HttpUtils;
import com.lucky.sweet.utility.PanduanNet;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by c on 2017/12/10.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class StoreDisplatManager {
    private Context context;

    public StoreDisplatManager(Context context) {
        this.context = context;

    }

    public List<String> getBusinessAreaList() {
        List<String> data_list = new ArrayList<>();
        data_list.add("全部商圈");
        data_list.add("兴工街");
        data_list.add("机场");
        data_list.add("火车站");
        data_list.add("旅顺南路");
        return data_list;
    }

    public List<String> getRecreationTypeList() {
        List<String> data_list = new ArrayList<>();
        data_list.add("餐厅");
        data_list.add("咖啡店");
        data_list.add("按摩院");
        data_list.add("面包店");
        data_list.add("艺术展");
        return data_list;
    }

    public List<String> getRankTypeList() {
        List<String> data_list = new ArrayList<>();
        data_list.add("评分最高");
        data_list.add("离我最近");
        data_list.add("最新收录");
        data_list.add("消费最低");
        data_list.add("消费最高");
        return data_list;
    }

    public List<StoreShowInfo> getStoreShowInfo() {
        List<StoreShowInfo> data_list = new ArrayList<>();
        data_list.add(new StoreShowInfo());
        data_list.add(new StoreShowInfo());
        data_list.add(new StoreShowInfo());
        data_list.add(new StoreShowInfo());
        data_list.add(new StoreShowInfo());
        return data_list;

    }

    public void getDisInfo() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        HttpUtils.sendOkHttpRequest(Properties.STOREDISPLAYTPATH, new
                                Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {

                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        String string = response.body().string();
                                    }
                                });
                    }
                }).start();
    }


}
