package com.lucky.sweet.service;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lucky.sweet.activity.StoreParticularInfoActivity;
import com.lucky.sweet.entity.MainStoreInfo;
import com.lucky.sweet.entity.StoreDetailedInfo;
import com.lucky.sweet.entity.WeatherInfo;
import com.lucky.sweet.fragment.ImStoreFragment;
import com.lucky.sweet.model.StoreParticularInfoHandler;
import com.lucky.sweet.model.imstore.ImStoreHandler;
import com.lucky.sweet.properties.Properties;
import com.lucky.sweet.utility.HttpUtils;
import com.lucky.sweet.utility.PanduanNet;
import com.squareup.okhttp.Request;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * Created by chn on 2018/1/17.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class CommunicationService extends Service {

    private TencentLocationManager locationManager;
    private OnMainDsplay onMainDsplay;

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends Binder {

        public void requestImStoreInfo(Activity activity, final ImStoreFragment fragment) {
            CommunicationService.this.initLocation(activity, new OnMainDsplay() {
                ImStoreHandler imStoreHandler = new ImStoreHandler(fragment);
                Message message;

                @Override
                public void upDataLocation(String city) {
                    message = new Message();
                    message.what = ImStoreHandler.UPDATALOCATION;
                    message.obj = city;
                    imStoreHandler.sendMessage(message);
                }

                @Override
                public void upDataWeather(WeatherInfo weatherInfo) {
                    message = new Message();
                    message.what = ImStoreHandler.UPDATAWEATHER;
                    message.obj = weatherInfo;
                    imStoreHandler.sendMessage(message);
                }

                @Override
                public void upDataShowInfo(MainStoreInfo mainStoreInfo) {
                    message = new Message();
                    message.what = ImStoreHandler.UPSHOWINFO;
                    message.obj = mainStoreInfo;
                    imStoreHandler.sendMessage(message);
                }
            });

        }


        public void requestShopDisplay(final StoreParticularInfoActivity
                                               activity, String mer_id) {

            final StoreParticularInfoHandler storeParticularInfoHandler = new StoreParticularInfoHandler(activity);

            CommunicationService.this.getParticularInfo(mer_id, new
                    OnParticularDis() {
                        @Override
                        public void upDataInfo(StoreDetailedInfo storeDetailedInfo) {
                            Message message = new Message();
                            message.what = StoreParticularInfoHandler.UPDATA;
                            message.obj = storeDetailedInfo;
                            storeParticularInfoHandler.sendMessage(message);
                        }
                    });
        }

}


private interface OnMainDsplay {
    void upDataLocation(String city);

    void upDataWeather(WeatherInfo weatherInfo);

    void upDataShowInfo(MainStoreInfo mainStoreInfo);
}
    private void initShopInfo(final double lat, final double lon) {
        HashMap<String, String> map = new HashMap<>();
        map.put("long", String.valueOf(lon));
        map.put("lat", String.valueOf(lat));
        HttpUtils.sendOkHttpRequest(Properties.MAINSHOWPLAYTPATH, new com.zhy.http.okhttp.callback.Callback() {
            @Override
            public Object parseNetworkResponse(com.squareup.okhttp.Response response) throws IOException {

                Gson gson = new Gson();
                MainStoreInfo mainStoreInfo = gson.fromJson(response.body().string(), MainStoreInfo.class);
                if (onMainDsplay != null) {
                    onMainDsplay.upDataShowInfo(mainStoreInfo);
                }

                return null;
            }

            @Override
            public void onError(com.squareup.okhttp.Request request, Exception e) {

            }

            @Override
            public void onResponse(Object response) {

            }
        }, map);

    }

    private void initLocation(Activity activity, OnMainDsplay onMainDsplay) {
        boolean networkState = PanduanNet.detect(activity);
        this.onMainDsplay = onMainDsplay;
        if (!networkState) {
            Toast.makeText(this, "网络错误，无法获取当前位置", Toast.LENGTH_SHORT).show();
        } else {
            locationManager = TencentLocationManager.getInstance(this);
            locationManager.setCoordinateType(TencentLocationManager.COORDINATE_TYPE_GCJ02);
            TencentLocationRequest request = TencentLocationRequest.create();
            request.setInterval(3000);
            request.setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_ADMIN_AREA);
            int requestCode = locationManager.requestLocationUpdates(request, mListener);
            if (requestCode == 0) {

            }
        }
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

                        if (onMainDsplay != null) {
                            onMainDsplay.upDataWeather(weatherInfo);
                        }

                    }


                });
            }
        }).start();

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
                        if (onMainDsplay != null) {
                            onMainDsplay.upDataLocation(city);
                        }
                        if (city.contains("市"))
                            initWeather(city.substring(0, city.length() - 1));

                        System.out.println("纬度" + tencentLocation.getLatitude() + "经度" + tencentLocation.getLongitude());
                        initShopInfo(tencentLocation.getLatitude(), tencentLocation.getLongitude());
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


    private void getParticularInfo(String shopid, final OnParticularDis
            onParticularDis) {
        final HashMap<String, String> map = new HashMap<>();
        map.put("mer_id", shopid);
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(Properties.STOREDETAILEDPATH, new
                        com.zhy.http.okhttp.callback.Callback() {
                            @Override
                            public Object parseNetworkResponse(com.squareup.okhttp.Response response) throws IOException {
                                String string = response.body().string();
                                Gson gson = new Gson();
                                StoreDetailedInfo storeDetailedInfo = gson.fromJson(string, StoreDetailedInfo.class);
                                onParticularDis.upDataInfo(storeDetailedInfo);
                                return null;
                            }

                            @Override
                            public void onError(Request request, Exception e) {

                            }

                            @Override
                            public void onResponse(Object response) {

                            }
                        }, map);
            }
        }.start();

    }

    public interface OnParticularDis {
        void upDataInfo(StoreDetailedInfo storeDetailedInfo);
    }
}
