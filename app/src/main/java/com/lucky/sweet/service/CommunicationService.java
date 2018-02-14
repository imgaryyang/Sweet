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

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lucky.sweet.activity.MyApplication;
import com.lucky.sweet.activity.OrderSeatActivity;
import com.lucky.sweet.entity.MainStoreInfo;
import com.lucky.sweet.entity.PerdetermingEntity;
import com.lucky.sweet.entity.ShopCarEntity;
import com.lucky.sweet.entity.StoreDetailedInfo;
import com.lucky.sweet.entity.StoreDisplayInfo;
import com.lucky.sweet.entity.StoreDisplaySearchEntity;
import com.lucky.sweet.entity.UserLoginInfo;
import com.lucky.sweet.entity.WeatherInfo;
import com.lucky.sweet.handler.LoginRegisterHandler;
import com.lucky.sweet.handler.OrderSeatHandler;
import com.lucky.sweet.properties.CircleProperties;
import com.lucky.sweet.properties.Properties;
import com.lucky.sweet.properties.ReserveProperties;
import com.lucky.sweet.properties.ServiceProperties;
import com.lucky.sweet.utility.HttpUtils;
import com.lucky.sweet.utility.PanduanNet;
import com.squareup.okhttp.Request;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import static com.lucky.sweet.properties.ServiceProperties.TEST_BUCKET;


/**
 * Created by chn on 2018/1/17.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class CommunicationService extends Service {

    private TencentLocationManager locationManager;


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

        public void requestImStoreInfo(Activity activity) {

            initLocation(activity);

        }


        public void requestShopDisplay(String mer_id) {

            CommunicationService.this.getParticularInfo(mer_id);
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

        public void requestStoreDisplayInfo(String project, String circle, String city, String rank, String num) {

            getStoreDisplayInfo(project, circle, city, rank, num);
            getStoreDisplaySearchTitle(city);

        }

        public void sendOrderSeatInfo(String time, String num, String peopleNum, String photo, String des) {
            requestOrderSeatInfo(time, num, peopleNum, photo, des);
        }

        public void ossUpdata(final String objectkey, final String filepath) {

            CommunicationService.this.ossUpdata(objectkey, filepath, null);
        }


        public void ossCirclePicUpdata(final ArrayList<String> paths, String
                content) {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// HH:mm:ss
            //获取当前时间
            final Date date = new Date(System.currentTimeMillis());
            JsonArray jsonElements = new JsonArray();
            for (int i = 0; i < paths.size(); i++) {
                final int finalI = i;
                final String ossPath = CircleProperties.SEND_CIRCLE_PIC_OSS_PAHT +
                        MyApplication.USER_ID + "/" + simpleDateFormat.format(date) + "/" + i + ".png";
                jsonElements.add(ossPath);
                new Thread() {
                    @Override
                    public void run() {
                        ossUpdata(ossPath, paths.get(finalI));
                    }
                }.start();
            }

            sendCircleInfo(jsonElements.toString().trim(), content);
        }

        public void ossUpdata(final String objectkey, final byte[] bytes) {
            CommunicationService.this.ossUpdata(
                    objectkey, bytes, null);
        }

        public void ossDownload(final String objectKey) {
            ossPicDown(objectKey);

        }

        public void getPersonPortrait(final String objectKey) {
            ossPicDown(objectKey);
        }

        public void upDataPersonPortrait(final String objectkey, final String
                filepath, OnUpdaSuccess onUpdaSuccess) {

            CommunicationService.this.ossUpdata(objectkey, filepath, onUpdaSuccess);
        }

        public void shopCarRequest(String mer_id) {

            requestShopCarInfo(mer_id);
        }

        public void sendCircleInfo(String photoPath, String content) {
            CommunicationService.this.sendCircleInfo(photoPath, content);
        }

        public void CreateReserveRoom(String password) {
            CommunicationService.this.CreateReserveRoom(password);
        }
    }

    private void CreateReserveRoom(String password) {
        HashMap<String, String> map = new HashMap<>();
        map.put("session", MyApplication.sessionId);
        map.put("mer_id", MyApplication.USER_ID);
        map.put("password", password);
        map.put("old_password", "");
        HttpUtils.sendOkHttpRequest(ReserveProperties.CREATE_OR_ALTER_ROOM,
                new com.zhy.http.okhttp.callback.Callback() {
                    @Override
                    public Object parseNetworkResponse(com.squareup.okhttp.Response response) throws IOException {
                        String string = response.body().string();
                        switch (string) {
                            case ReserveProperties.CREATE_OR_ALTER_ROOM_FAIL:

                                break;
                            default:
                                break;
                        }
                        System.out.println();
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

    //todo 确认mer_id
    private void sendCircleInfo(String photoPath, String content) {
        HashMap<String, String> map = new HashMap<>();
        map.put("photo", photoPath);
        map.put("session", MyApplication.sessionId);
        map.put("mer_id", "1");
        map.put("content", content);
        HttpUtils.sendOkHttpRequest(CircleProperties.SEND_CIRCLE, new com.zhy.http
                .okhttp.callback.Callback() {
            @Override
            public Object parseNetworkResponse(com.squareup.okhttp.Response response) throws IOException {
                try {
                    Gson gson = new Gson();
                    System.out.println(response.body().string());
                    switch (response.body().string()) {
                        case "1":
                            //todo 通知用户上传成功
                            break;
                        case "0":
                            //todo 通知用户上传失败
                            break;
                    }
                } catch (Exception e) {

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


    private void ossUpdata(final String objectkey, final
    String filepath, final OnUpdaSuccess onUpdaSuccess) {
        OSSClient ossClient = MyApplication.getOSSClient();
        if (ossClient != null) {
            PutObjectRequest put = new PutObjectRequest(TEST_BUCKET, objectkey, filepath);
            setServiceCallBack(put, ossClient, onUpdaSuccess);
        }


    }

    private void ossUpdata(final String objectkey, final
    byte[] bytes, final OnUpdaSuccess onUpdaSuccess) {
        OSSClient ossClient = MyApplication.getOSSClient();
        if (ossClient != null) {
            PutObjectRequest put = new PutObjectRequest(TEST_BUCKET, objectkey, bytes);
            setServiceCallBack(put, MyApplication.getOSSClient(), onUpdaSuccess);
        }


    }

    private void ossPicDown(final String objectKey)

    {
        GetObjectRequest request = new GetObjectRequest(TEST_BUCKET, objectKey);
        request.setxOssProcess("image/resize,m_fixed,w_100," + "h_100/quality,q_50");
        OSSClient ossClient = MyApplication.getOSSClient();
        if (ossClient != null) {
            ossClient.asyncGetObject(request, new
                    OSSCompletedCallback<GetObjectRequest, GetObjectResult>() {
                        @Override
                        public void onSuccess(GetObjectRequest request, GetObjectResult result) {

                            EventBus.getDefault().post(result);

                        }

                        @Override
                        public void onFailure(GetObjectRequest request, ClientException clientException, ServiceException serviceException) {

                        }
                    });

        }


    }

    public interface OnUpdaSuccess {
        void success();
    }

    private static void setServiceCallBack(PutObjectRequest put, OSS oss,
                                           final OnUpdaSuccess onUpdaSuccess) {

        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
            }
        });
        oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                if (onUpdaSuccess != null) {
                    onUpdaSuccess.success();
                }
                Log.d("PutObject", "UploadSuccess");
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }


    private void requestOrderSeatInfo(String time, String num, String peopleNum, String photo, String des) {
        HashMap<String, String> map = new HashMap<>();
        map.put("tim", time);
        map.put("num", num);
        map.put("peopleNum", peopleNum);
        map.put("photo", photo);
        map.put("des", des);
        map.put("session", MyApplication.sessionId);
        HttpUtils.sendOkHttpRequest(Properties.MAINSHOWPLAYTPATH, new com.zhy.http.okhttp.callback.Callback() {
            @Override
            public Object parseNetworkResponse(com.squareup.okhttp.Response response) throws IOException {
                try {
                    Gson gson = new Gson();
                    System.out.println(response.toString());
                } catch (Exception e) {
                    Log.e("Service", "requestOrder");
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

    private void requestShopCarInfo(String mer_id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("mer_id", "1");
        map.put("session", MyApplication.sessionId);
        HttpUtils.sendOkHttpRequest(Properties.SHOP_CAR, new com.zhy.http.okhttp
                .callback.Callback() {
            @Override
            public Object parseNetworkResponse(com.squareup.okhttp.Response response) throws IOException {
                String string = response.body().string();

                try {
                    Gson gson = new Gson();
                    ShopCarEntity shopCarEntity = gson.fromJson(string, ShopCarEntity.class);
                    EventBus.getDefault().post(shopCarEntity);
                } catch (Exception e) {

                    if (string.equals("250")) {
                        System.out.println("session过期");
                    }
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

    private void getStoreDisplaySearchTitle(String city) {
        HashMap<String, String> map = new HashMap<>();
        map.put("city", city);
        HttpUtils.sendOkHttpRequest(Properties.DISPLAYSEARCHTITLE, new com.zhy.http.okhttp
                .callback.Callback() {
            @Override
            public Object parseNetworkResponse(com.squareup.okhttp.Response response) throws IOException {
                try {
                    Gson gson = new Gson();

                    //todo 修改商品展示检索栏
                    StoreDisplaySearchEntity storeDisplaySearchEntity = gson.fromJson(response.body().string(), StoreDisplaySearchEntity.class);
                    EventBus.getDefault().post(storeDisplaySearchEntity);

                } catch (Exception e) {
                    Log.e("Service", "StoreDisplay");
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

    private interface PerdetermingRequest {
        void getIt(PerdetermingEntity entitys);
    }

    private void requestPredetermined(PerdetermingRequest request) {
        String[] data = {"2-1", "2-2", "2-3", "2-4",
                "2-5", "2-6"};
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

    private void initShopInfo(final double lat, final double lon) {
        HashMap<String, String> map = new HashMap<>();
        map.put("long", String.valueOf(lon));
        map.put("lat", String.valueOf(lat));
        HttpUtils.sendOkHttpRequest(Properties.MAINSHOWPLAYTPATH, new com.zhy.http.okhttp.callback.Callback() {
            @Override
            public Object parseNetworkResponse(com.squareup.okhttp.Response response) throws IOException {
                try {
                    Gson gson = new Gson();
                    MainStoreInfo mainStoreInfo = gson.fromJson(response.body().string(), MainStoreInfo.class);
                    EventBus.getDefault().post(mainStoreInfo);

                } catch (Exception e) {
                    Log.e("Service", "initShopInfo");
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

    private void initLocation(Activity activity) {
        boolean networkState = PanduanNet.detect(activity);

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
        final String url = ServiceProperties.WEATHERREQUESTBODY + city;
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
                        EventBus.getDefault().post(weatherInfo);


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
                        MyApplication.setCurrenCity(city);
                        if (city.length() > 3) {
                            city = city.substring(0, 2) + "...";
                            EventBus.getDefault().post(city);
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


    private void getParticularInfo(String shopid) {
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
                                EventBus.getDefault().post(gson.fromJson(string, StoreDetailedInfo.class));
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


    private void getStoreDisplayInfo(String project, String circle, String city, String rank, String num) {
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

                                EventBus.getDefault().post(gson.fromJson(string, StoreDisplayInfo.class));

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
