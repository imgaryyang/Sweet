package com.lucky.sweet.service;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lucky.sweet.activity.OrderSeatActivity;
import com.lucky.sweet.activity.StoreDisplatActivity;
import com.lucky.sweet.activity.StoreParticularInfoActivity;
import com.lucky.sweet.entity.MainStoreInfo;
import com.lucky.sweet.entity.PerdetermingEntity;
import com.lucky.sweet.entity.StoreDetailedInfo;
import com.lucky.sweet.entity.StoreDisplayInfo;
import com.lucky.sweet.entity.StoreDisplaySearchEntity;
import com.lucky.sweet.entity.UserLoginInfo;
import com.lucky.sweet.entity.WeatherInfo;
import com.lucky.sweet.fragment.ImStoreFragment;
import com.lucky.sweet.handler.DisplayActivityHandle;
import com.lucky.sweet.handler.ImStoreHandler;
import com.lucky.sweet.handler.LoginRegisterHandler;
import com.lucky.sweet.handler.OrderSeatHandler;
import com.lucky.sweet.handler.StoreParticularInfoHandler;
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
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
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
        /**
         * 用户登陆
         *
         * @param email    用户邮箱
         * @param password 密码
         */
        public void userLogin(Context context, String email, String password) {

            sendLoginRegesiterRequest(context, USERLOGIN, email, password);

        }

        /**
         * 请求验证码
         *
         * @param email 邮箱字符串
         */
        public void checkOutEmail(Context context, String email) {

            sendLoginRegesiterRequest(context, CHECKOUTEMAIL, email, null);

        }

        /**
         * 提交验证码和邮件地址
         *
         * @param email       邮件地址
         * @param verPassword 验证码
         * @return
         */
        public void emailVer(Context context, String email, String verPassword) {

            sendLoginRegesiterRequest(context, EMAILVER, email, verPassword);

        }

        /**
         * 提交邮箱和密码
         *
         * @param email    邮箱
         * @param password 密码
         */
        public void userRegister(Context context, String email, String password) {

            sendLoginRegesiterRequest(context, USERREGISTER, email, password);

        }

        /**
         * 忘记密码确认邮箱
         *
         * @param email
         */
        public void forgetSubmit(Context context, String email) {

            sendLoginRegesiterRequest(context, FORGETSUBMIT, email, null);

        }


        public void forgetValidate(Context context, String email, String
                verPassword) {

            sendLoginRegesiterRequest(context, FORGETVALIDATE, email,
                    verPassword);

        }

        public void userForget(Context context, String email, String password) {

            sendLoginRegesiterRequest(context, USERFORGET, email, password);

        }

        private void sendLoginRegesiterRequest(Context context, final int type, final String email, @Nullable final String password) {
            LoginRegisterHandler handler = new LoginRegisterHandler(context);
            CommunicationService.this.requestLoginRegister(type, email, password, handler);

        }

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

        public void requestPerdeterming(OrderSeatActivity activity) {
            final OrderSeatHandler orderSeatHandler = new OrderSeatHandler(activity);
            CommunicationService.this.requestPredetermined(new PerdetermingRequest() {
                @Override
                public void getIt(PerdetermingEntity entitys) {

                    Message msg = new Message();
                    msg.obj = entitys;
                    msg.what = OrderSeatHandler.UPDATE_DIALOGDAT;
                    orderSeatHandler.sendMessage(msg);
                }


            });
        }

        public void requestStoreDisplayInfo(StoreDisplatActivity activity, String project,
                                            String
                                                    circle,
                                            String city, String rank, String num) {
            final DisplayActivityHandle displayActivityHandle = new
                    DisplayActivityHandle(activity);
            getStoreDisplayInfo(project, circle, city, rank, num, new OnDisPlayInfoRequest() {
                @Override
                public void DisplayInfo(StoreDisplayInfo storeDisplayInfo) {
                    Message message = new Message();
                    message.what = DisplayActivityHandle.DISPLAYINFO;
                    message.obj = storeDisplayInfo;
                    displayActivityHandle.sendMessage(message);
                }
            });
            getStoreDisplaySearchTitle(city, new OnDisPlaySearchRequest() {
                @Override
                public void DisplaySearch(StoreDisplaySearchEntity storeDisplaySearchEntity) {
                    Message message = new Message();
                    message.what = DisplayActivityHandle.DISPLAYSEARCHINFO;
                    message.obj = storeDisplaySearchEntity;
                    displayActivityHandle.sendMessage(message);
                }
            });


        }

    }

    private void getStoreDisplaySearchTitle(
            String
                    city, final OnDisPlaySearchRequest
                    onDisPlaySearchRequest) {
        HashMap<String, String> map = new HashMap<>();
        map.put("city", city);
        HttpUtils.sendOkHttpRequest(Properties.DISPLAYSEARCHTITLE, new com.zhy.http.okhttp
                .callback.Callback() {
            @Override
            public Object parseNetworkResponse(com.squareup.okhttp.Response response) throws IOException {

                Gson gson = new Gson();
                StoreDisplaySearchEntity storeDisplaySearchEntity = gson.fromJson(response.body().string(),
                        StoreDisplaySearchEntity.class);
                onDisPlaySearchRequest.DisplaySearch(storeDisplaySearchEntity);
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

    private interface PerdetermingRequest {
        void getIt(PerdetermingEntity entitys);
    }

    private void requestPredetermined(PerdetermingRequest request) {
        String[] data = {"一月十九", "一月二十", "一月二十一", "一月二十二", "一月二十三",
                "一月二十四"};
        String[][] time = {
                {
                        "1", "2", "3", "4", "5", "6"
                },
                {
                        "?", "?"
                },
                {
                        "aaa", "bbbb", "cccc", "dddd", "eee"
                },
                {
                        "aaaaa", "cccc"
                },
                {
                        "1", "2", "3", "4", "5", "6"
                },
                {
                        "?", "?"
                }
        };
        request.getIt(new PerdetermingEntity().setData(data).setTime(time));
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

    interface OnDisPlayInfoRequest {

        void DisplayInfo(StoreDisplayInfo storeDisplayInfo);


    }

    interface OnDisPlaySearchRequest {
        void DisplaySearch(StoreDisplaySearchEntity storeDisplaySearchEntity);

    }

    private void getStoreDisplayInfo(String project, String circle, String city, String rank, String num, final OnDisPlayInfoRequest
            onDisPlayInfoRequest) {
        final HashMap<String, String> map = new HashMap<>();
        map.put("small_project", project);
        map.put("circle", circle);
        map.put("city", city);
        map.put("rank", rank);
        map.put("num_start", num);
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(Properties.DISPLAYMAINSHOWPATH, new
                        com.zhy.http.okhttp.callback.Callback() {
                            @Override
                            public Object parseNetworkResponse(com.squareup.okhttp.Response response) throws IOException {
                                String string = response.body().string();
                                Gson gson = new Gson();
                                System.out.println(string);
                                StoreDisplayInfo storeDisplayInfo = gson.fromJson(string, StoreDisplayInfo.class);
                                onDisPlayInfoRequest.DisplayInfo(storeDisplayInfo);
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

    private Boolean isLogin = false;
    private Context context;
    /**
     * USERLOGIN ：用户登陆
     */
    public final static int USERLOGIN = 1;
    /**
     * CHECKOUTEMAIL ：邮箱检测
     * EMAILVER ：确认验证码
     * USERREGISTER ：用户注册
     */
    public final static int CHECKOUTEMAIL = 2;
    public final static int EMAILVER = 3;
    public final static int USERREGISTER = 4;
    /**
     * FORGETSUBMIT:找回密码确认邮箱
     * FORGETVALIDATE：验证邮箱和对应邮箱验证码
     * USERFORGET：修改密码
     */
    public final static int FORGETSUBMIT = 5;
    public final static int FORGETVALIDATE = 6;
    public final static int USERFORGET = 7;

    /**
     * 发送数据请求
     *
     * @param type     数据请求类型
     * @param email    邮箱
     * @param password 密码
     */
    private void requestLoginRegister(final int type, final String email, @Nullable final
    String password, final LoginRegisterHandler handler) {
        new Thread() {

            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    okhttp3.Request request = null;
                    switch (type) {
                        case USERLOGIN:
                            isLogin = true;
                            request = new okhttp3.Request.Builder().url(Properties.LOGINPATH).post(new FormBody.Builder().add("username", email).add("password", password).build()).build();
                            break;
                        case CHECKOUTEMAIL:
                            request = new okhttp3.Request.Builder().url(Properties.MAILSUBMITPATH).post(new FormBody.Builder().add("mail_address", email).build()).build();
                            break;
                        case EMAILVER:
                            request = new okhttp3.Request.Builder().url(Properties.MAILVALIDATEPATH).post(new FormBody.Builder().add("mail_address", email).add(" mail_ver", password).build()).build();
                            break;
                        case USERREGISTER:
                            request = new okhttp3.Request.Builder().url(Properties.USERWRITEPATH).post(new FormBody.Builder().add("mail_address", email).add(" password", password).build()).build();
                            break;
                        case FORGETSUBMIT:
                            request = new okhttp3.Request.Builder().url(Properties.FORGETSUBMITPATH).post(new FormBody.Builder().add("mail_address", email).build()).build();
                            break;
                        case FORGETVALIDATE:
                            request = new okhttp3.Request.Builder().url(Properties.FORGETVALIDATEPATH).post(new FormBody.Builder().add("mail_address", email).add(" mail_ver", password).build()).build();
                            break;
                        case USERFORGET:
                            request = new okhttp3.Request.Builder().url(Properties.USERFORGETPATH).post(new FormBody.Builder().add("mail_address", email).add(" password", password).build()).build();
                            break;

                        default:
                            break;
                    }

                    Response response = client.newCall(request).execute();

                    if (response.isSuccessful()) {
                        int responseType = -1;
                        String str = response.body().string();
                        Log.i("ServerBackCode:", str);
                        UserLoginInfo info = new UserLoginInfo(email, password);
                        Message message = new Message();
                        message.what = type;
                        try {
                            responseType = Integer.parseInt(str);
                        } catch (NumberFormatException e) {
                            info.setSession(str);
                            responseType = LoginRegisterHandler.LOGINSSUCCEED;
                            isLogin = false;
                        }
                        message.arg1 = responseType;
                        message.obj = info;
                        handler.sendMessage(message);
                    } else {
                        System.out.println("发送失败");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


}
