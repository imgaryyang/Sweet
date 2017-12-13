package com.lucky.sweet.moudel.imstore;

import android.content.Context;
import android.os.Message;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lucky.sweet.R;
import com.lucky.sweet.entity.RecreationInfo;
import com.lucky.sweet.entity.StoreShowInfo;
import com.lucky.sweet.entity.WeatherInfo;
import com.lucky.sweet.fragment.ImStoreFragment;
import com.lucky.sweet.properties.Properties;
import com.lucky.sweet.utility.HttpUtils;
import com.lucky.sweet.utility.PanduanNet;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by c on 2017/12/6.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class ImStoreManager {
    private TencentLocationManager locationManager;
    private ImStoreFragment ImStoreFragment;
    private Context context;
    private final int MAX_TIME = 3000;
    private final ImStoreHandler imStoreHandler;

    public ImStoreManager(ImStoreFragment ImStoreFragment) {
        this.ImStoreFragment = ImStoreFragment;


        context = ImStoreFragment.getContext();
        imStoreHandler = new ImStoreHandler(ImStoreFragment);

        initLocation();

        initShopInfo();

    }

    public ArrayList<Integer> getAdInfoList() {
        ArrayList<Integer> strings = new ArrayList<Integer>();
        strings.add(R.mipmap.adtestone);
        strings.add(R.mipmap.adtesttwo);
        strings.add(R.mipmap.adtestthree);
        strings.add(R.mipmap.adtestfour);
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

    private void initLocation() {
        boolean networkState = PanduanNet.detect(ImStoreFragment.getActivity());
        if (!networkState) {
            Toast.makeText(context, "网络错误，无法获取当前位置", Toast.LENGTH_SHORT).show();
        } else {
            locationManager = TencentLocationManager.getInstance(context);
            locationManager.setCoordinateType(TencentLocationManager.COORDINATE_TYPE_GCJ02);
            TencentLocationRequest request = TencentLocationRequest.create();
            request.setInterval(MAX_TIME);
            request.setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_ADMIN_AREA);
            int requestCode = locationManager.requestLocationUpdates(request, mListener);
            if (requestCode == 0) {

            }
        }
    }

    private TencentLocationListener mListener = new
            TencentLocationListener() {
                @Override
                public void onLocationChanged(TencentLocation tencentLocation, int error, String s) {

                    if (error == TencentLocation.ERROR_OK) {

                        String city = tencentLocation.getCity().trim();
                        if (city.length() > 3) {
                            city = city.substring(0, 2) + "...";
                        }
                        ImStoreFragment.updataLocation(city);
                        if (city.contains("市"))
                            initWeather(city.substring(0, city.length() - 1));

                        System.out.println("纬度" + tencentLocation.getLatitude() + "经度" + tencentLocation.getLongitude());
                    }
                    stopLocation();
                }

                @Override
                public void onStatusUpdate(String s, int i, String s1) {

                }
            };


    private void stopLocation() {
        locationManager.removeUpdates(mListener);
    }

    private void initWeather(String city) {
        final String url = Properties.WEATHERREQUESTBODY + city;
        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpUtils.sendOkHttpRequest(url, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        Gson gson = new Gson();
                        WeatherInfo weatherInfo = gson.fromJson(response.body().string(), WeatherInfo.class);
                        Message message = new Message();

                        message.what = ImStoreHandler.UPDATAWEATHER;
                        message.obj = weatherInfo;
                        imStoreHandler.sendMessage(message);

                    }


                });
            }
        }).start();

    }

    private void initShopInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpUtils.sendOkHttpRequest(Properties.MAINSHOWPLAYTPATH, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        Gson gson = new Gson();
                        StoreShowInfo storeShowInfo = gson.fromJson(response.body
                                ().string(), StoreShowInfo.class);
                        Message message = new Message();
                        message.what = ImStoreHandler.UPSHOWINFO;
                        message.obj = storeShowInfo;
                        imStoreHandler.sendMessage(message);

                    }


                });
            }
        }).start();
    }
}
