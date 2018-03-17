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
import com.lucky.sweet.entity.AlterOrderInfo;
import com.lucky.sweet.entity.CircleDetail;
import com.lucky.sweet.entity.CircleLikePoint;
import com.lucky.sweet.entity.CircleMainInfo;
import com.lucky.sweet.entity.DetailOrderInfo;
import com.lucky.sweet.entity.FlowPeople;
import com.lucky.sweet.entity.JoinInRoomInfo;
import com.lucky.sweet.entity.MainStoreInfo;
import com.lucky.sweet.entity.PerdetermingEntity;
import com.lucky.sweet.entity.PersonInfo;
import com.lucky.sweet.entity.ReservationInfo;
import com.lucky.sweet.entity.ShopCarEntity;
import com.lucky.sweet.entity.StoreDetailedInfo;
import com.lucky.sweet.entity.StoreDisplayInfo;
import com.lucky.sweet.entity.StoreDisplaySearchEntity;
import com.lucky.sweet.entity.UserLoginInfo;
import com.lucky.sweet.entity.VipCardInfo;
import com.lucky.sweet.entity.WeatherInfo;
import com.lucky.sweet.handler.LoginRegisterHandler;
import com.lucky.sweet.handler.OrderSeatHandler;
import com.lucky.sweet.properties.CircleProperties;
import com.lucky.sweet.properties.PersonProperties;
import com.lucky.sweet.properties.Properties;
import com.lucky.sweet.properties.ReserveProperties;
import com.lucky.sweet.properties.ServiceProperties;
import com.lucky.sweet.utility.HttpUtils;
import com.lucky.sweet.utility.MyOkhttpHelper;
import com.lucky.sweet.utility.PanduanNet;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

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

        public void getParticularCircleInfo(String mer_id, int start) {
            CommunicationService.this.getParticularCircleInfo(mer_id, start);


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

        public void requestStoreDisplayInfo(String project, String circle, String rank, String num) {

            getStoreDisplayInfo(project, circle, rank, num);

        }

        public void requestStoreSearch() {
            getStoreDisplaySearchTitle();

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
                final String path = paths.get(i);
                String[] split = path.split("\\.");
                final String ossPath = CircleProperties.SEND_CIRCLE_PIC_OSS_PAHT +
                        MyApplication.USER_ID + "/" + simpleDateFormat.format(date) + "/" + i + split[split.length - 1];
                jsonElements.add(ossPath);
                new Thread() {
                    @Override
                    public void run() {
                        ossUpdata(ossPath, path);
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

        public void createReserveRoom(String password, String mer_id) {

            CommunicationService.this.createReserveRoom(mer_id, password);

        }

        public void joinInReserveRoom(String roomId, String psw) {

            CommunicationService.this.joinInReserveRoom(roomId, psw);

        }


        public void dishesMenuUpdata(String roomId, String item,
                                     String incr_decr, String key) {

            CommunicationService.this.dishesMenuUpdata(roomId, item,
                    incr_decr, key);
        }

        public void requestCircleInfo(String type, String loacation) {

            CommunicationService.this.requestCircleInfo(type, loacation);
        }

        public void sendCircledetailsInfo(String circle_id) {
            CommunicationService.this.sendCircledetailsInfo(circle_id);

        }

        public void circleLikeIt(String circle_id, int position) {
            CommunicationService.this.circleLikeIt(circle_id, position);
        }

        public void requestPersonVipCard() {
            CommunicationService.this.requestPersonVipCard();
        }

        public void getFlowFriends() {
            CommunicationService.this.getFlowFriends();
        }

        public void invitationFriend(String user_id) {
            CommunicationService.this.invitationFriend(user_id);
        }

        public void getPersonInfo() {
            CommunicationService.this.getPersonInfo();
        }

        public void getAlterOrder() {
            CommunicationService.this.getAlterOrder();
        }

        public void getDetailedOrderInfo(String indent_id) {
            CommunicationService.this.getDetailedOrderInfo(indent_id);
        }
    }

    private void getDetailedOrderInfo(String indent_id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("session", MyApplication.sessionId);
        map.put("indent_id", indent_id);
        HttpUtils.sendOkHttpRequest(PersonProperties.DETAILED_ORDER_INFO, new MyOkhttpHelper() {
            @Override
            public void onResponseSuccessfulString(String string) {
                System.out.println(string);
                EventBus.getDefault().post(new Gson().fromJson(string, DetailOrderInfo.class));

            }

            @Override
            public void afterNewRequestSession() {
                getPersonInfo();
            }
        }, map);
    }

    public void getAlterOrder() {
        HashMap<String, String> map = new HashMap<>();
        map.put("session", MyApplication.sessionId);
        HttpUtils.sendOkHttpRequest(PersonProperties.ALTER_ORDER, new MyOkhttpHelper() {
            @Override
            public void onResponseSuccessfulString(String string) {

                EventBus.getDefault().post(new Gson().fromJson(string, AlterOrderInfo.class));
            }

            @Override
            public void afterNewRequestSession() {
                getPersonInfo();
            }
        }, map);
    }

    public void getPersonInfo() {
        HashMap<String, String> map = new HashMap<>();
        map.put("session", MyApplication.sessionId);
        HttpUtils.sendOkHttpRequest(PersonProperties.PERSON_INFO, new MyOkhttpHelper() {
            @Override
            public void onResponseSuccessfulString(String string) {
                EventBus.getDefault().post(new Gson().fromJson(string, PersonInfo.class));
            }

            @Override
            public void afterNewRequestSession() {
                getPersonInfo();
            }
        }, map);
    }

    private void invitationFriend(String inviteUserId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("invite_user_id", inviteUserId);
        map.put("session", MyApplication.sessionId);
        map.put("key_value", "????");
        HttpUtils.sendOkHttpRequest(ReserveProperties.INVITATION_FRIEND, new MyOkhttpHelper() {
            @Override
            public void onResponseSuccessfulString(String string) {

            }

            @Override
            public void afterNewRequestSession() {
                invitationFriend(inviteUserId);
            }
        }, map);
    }

    private void getFlowFriends() {
        HashMap<String, String> map = new HashMap<>();
        map.put("session", MyApplication.sessionId);
        HttpUtils.sendOkHttpRequest(PersonProperties.FOLW_FRIEND, new MyOkhttpHelper() {
            @Override
            public void onResponseSuccessfulString(String string) {
                EventBus.getDefault().post(new Gson().fromJson(string, FlowPeople.class));
            }

            @Override
            public void afterNewRequestSession() {

            }
        }, map);
    }

    private void requestPersonVipCard() {
        HashMap<String, String> map = new HashMap<>();
        map.put("session", MyApplication.sessionId);
        HttpUtils.sendOkHttpRequest(PersonProperties.PERSON_VIP_CARD,
                new MyOkhttpHelper() {

                    @Override
                    public void onResponseSuccessfulString(String string) {

                        EventBus.getDefault().post(new Gson().fromJson(string, VipCardInfo.class));
                    }

                    @Override
                    public void afterNewRequestSession() {
                        requestPersonVipCard();
                    }
                }, map);


    }

    private void circleLikeIt(final String circle_id, final int position) {

        HashMap<String, String> map = new HashMap<>();
        map.put("circle_id", circle_id);
        map.put("session", MyApplication.sessionId);
        HttpUtils.sendOkHttpRequest(CircleProperties.LIKE_POINT,
                new MyOkhttpHelper() {

                    @Override
                    public void onResponseSuccessfulString(String string) {
                        CircleLikePoint circleLikePoint = new CircleLikePoint();
                        circleLikePoint.setPosition(position);
                        circleLikePoint.setRequest(string);
                        EventBus.getDefault().post(circleLikePoint);
                    }

                    @Override
                    public void afterNewRequestSession() {
                        circleLikeIt(circle_id, position);
                    }
                }, map);
    }

    private void sendCircledetailsInfo(final String circle_id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("circle_id", circle_id);
        map.put("session", MyApplication.sessionId);
        HttpUtils.sendOkHttpRequest(CircleProperties.CIRCLE_CONTENT_DETAILS,
                new MyOkhttpHelper() {

                    @Override
                    public void onResponseSuccessfulString(String string) {
                        EventBus.getDefault().post(new Gson().fromJson(string, CircleDetail.class));
                    }

                    @Override
                    public void afterNewRequestSession() {
                        sendCircledetailsInfo(circle_id);
                    }
                }, map);
    }

    private void requestCircleInfo(final String type, final String location) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("session", MyApplication.sessionId);
        map.put("start", location);
        HttpUtils.sendOkHttpRequest(CircleProperties.CIRCLE_MAIN_SHOW,
                new MyOkhttpHelper() {
                    @Override
                    public void onResponseSuccessfulString(String string) {

                        EventBus.getDefault().post(new Gson().fromJson(string, CircleMainInfo.class));
                    }

                    @Override
                    public void afterNewRequestSession() {
                        requestCircleInfo(type, location);
                    }
                }, map);

    }

    private void dishesMenuUpdata(final String roomId, final String item, final String incr_decr, final String key) {
        HashMap<String, String> map = new HashMap<>();
        map.put("session", MyApplication.sessionId);
        map.put("room_id", roomId);
        map.put("item", item);
        map.put("incr_decr", incr_decr);
        map.put("key_value", key);
        HttpUtils.sendOkHttpRequest(ReserveProperties.UPDATA_MENU,
                new MyOkhttpHelper() {
                    @Override
                    public void onResponseSuccessfulString(String string) {


                    }

                    @Override
                    public void afterNewRequestSession() {
                        dishesMenuUpdata(roomId, item, incr_decr, key);
                    }
                }, map);
    }

    private void joinInReserveRoom(final String roomId, final String psw) {
        try {
            HashMap<String, String> map = new HashMap<>();
            String userId = MyApplication.USER_ID;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", userId);
            map.put("session", MyApplication.sessionId);
            map.put("room_id", roomId);
            map.put("password", psw);
            map.put("key_value", jsonObject.toString());

            HttpUtils.sendOkHttpRequest(ReserveProperties.JOIN_IN_ROOM_TEST,
                    new MyOkhttpHelper() {

                        @Override
                        public void onResponseSuccessfulString(String string) {
                            System.out.println(string);
                            EventBus.getDefault().post(new JoinInRoomInfo(string));

                        }

                        @Override
                        public void afterNewRequestSession() {
                            joinInReserveRoom(roomId, psw);
                        }
                    }, map);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void createReserveRoom(final String mer_id, final String password) {
        HashMap<String, String> map = new HashMap<>();
        map.put("session", MyApplication.sessionId);
        map.put("mer_id", mer_id);
        map.put("password", password);
        HttpUtils.sendOkHttpRequest(ReserveProperties.CREATE_ROOM,
                new MyOkhttpHelper() {

                    @Override
                    public void onResponseSuccessfulString(String string) {
                        ReservationInfo reservationInfo = new ReservationInfo();
                        reservationInfo.setRoomId(string);
                        EventBus.getDefault().post(reservationInfo);
                    }

                    @Override
                    public void afterNewRequestSession() {
                        createReserveRoom(mer_id, password);
                    }
                }, map);
    }

    //todo 确认mer_id
    private void sendCircleInfo(final String photoPath, final String content) {
        HashMap<String, String> map = new HashMap<>();
        map.put("photo", photoPath);
        map.put("session", MyApplication.sessionId);
        map.put("mer_id", "1");
        map.put("content", content);
        HttpUtils.sendOkHttpRequest(CircleProperties.SEND_CIRCLE, new MyOkhttpHelper() {

            @Override
            public void onResponseSuccessfulString(String string) {
                EventBus.getDefault().post(string);
            }

            @Override
            public void afterNewRequestSession() {
                sendCircleInfo(photoPath, content);
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


    private void requestOrderSeatInfo(final String time, final String num, final String peopleNum, final String photo, final String des) {
        HashMap<String, String> map = new HashMap<>();
        map.put("tim", time);
        map.put("num", num);
        map.put("peopleNum", peopleNum);
        map.put("photo", photo);
        map.put("des", des);
        map.put("session", MyApplication.sessionId);
        HttpUtils.sendOkHttpRequest(Properties.MAINSHOWPLAYTPATH, new MyOkhttpHelper() {
            @Override
            public void onResponseSuccessfulString(String string) {
                EventBus.getDefault().post(string);
            }

            @Override
            public void afterNewRequestSession() {
                requestOrderSeatInfo(time, num, peopleNum, photo, des);
            }

        }, map);
    }

    private void requestShopCarInfo(final String mer_id) {
        final HashMap<String, String> map = new HashMap<>();
        map.put("mer_id", "1");
        map.put("session", MyApplication.sessionId);
        HttpUtils.sendOkHttpRequest(Properties.SHOP_CAR, new MyOkhttpHelper() {

            @Override
            public void onResponseSuccessfulString(String string) {
                EventBus.getDefault().post(new Gson().fromJson(string, ShopCarEntity.class));

            }

            @Override
            public void afterNewRequestSession() {
                requestShopCarInfo(mer_id);
            }
        }, map);
    }

    private void getStoreDisplaySearchTitle() {
        HashMap<String, String> map = new HashMap<>();
        map.put("city", MyApplication.CURRENT_CITY);
        HttpUtils.sendOkHttpRequest(Properties.DISPLAYSEARCHTITLE, new MyOkhttpHelper() {
            @Override
            public void onResponseSuccessfulString(String string) {
                EventBus.getDefault().post(new Gson().fromJson(string, StoreDisplaySearchEntity.class));
            }

            @Override
            public void afterNewRequestSession() {
                getStoreDisplaySearchTitle();
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
        HttpUtils.sendOkHttpRequest(Properties.MAINSHOWPLAYTPATH, new MyOkhttpHelper() {

            @Override
            public void onResponseSuccessfulString(String string) {
                EventBus.getDefault().post(new Gson().fromJson(string, MainStoreInfo.class));
            }

            @Override
            public void afterNewRequestSession() {
                initShopInfo(lat, lon);
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

        }
    }

    private void initWeather(String city) {
        final String url = ServiceProperties.WEATHERREQUESTBODY + city;
        new Thread(() -> HttpUtils.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Gson gson = new Gson();

                WeatherInfo weatherInfo = gson.fromJson(response.body().string(), WeatherInfo.class);
                EventBus.getDefault().post(weatherInfo);


            }


        })).start();

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
                        System.out.println("cityLocation");
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


    private void getParticularInfo(final String shopid) {
        final HashMap<String, String> map = new HashMap<>();
        map.put("mer_id", shopid);
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(Properties.STOREDETAILEDPATH, new
                        MyOkhttpHelper() {

                            @Override
                            public void onResponseSuccessfulString(String string) {
                                EventBus.getDefault().post(new Gson().fromJson(string, StoreDetailedInfo.class));
                            }

                            @Override
                            public void afterNewRequestSession() {
                                getParticularInfo(shopid);
                            }
                        }, map);
            }
        }.start();

    }

    private void getParticularCircleInfo(final String shopId, int start) {
        final HashMap<String, String> map = new HashMap<>();
        map.put("mer_id", "1");
        map.put("session", MyApplication.sessionId);
        map.put("start", start + "");
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(CircleProperties.SHOP_CIRCLE, new
                        MyOkhttpHelper() {

                            @Override
                            public void onResponseSuccessfulString(String string) {

                                EventBus.getDefault().post(new Gson().fromJson(string, CircleMainInfo.class));
                            }

                            @Override
                            public void afterNewRequestSession() {
                                getParticularCircleInfo(shopId, start);
                            }
                        }, map);
            }
        }.start();

    }


    private void getStoreDisplayInfo(final String project, final String circle, final String rank, final String num) {
        final HashMap<String, String> map = new HashMap<>();
        map.put("small_project", project);
        map.put("circle", circle);
        map.put("city", MyApplication.CURRENT_CITY);
        map.put("rank", rank);
        map.put("num_start", num);
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(Properties.DISPLAYMAINSHOWPATH, new
                        MyOkhttpHelper() {
                            @Override
                            public void onResponseSuccessfulString(String string) {
                                System.out.println(string);
                                EventBus.getDefault().post(new Gson().fromJson(string, StoreDisplayInfo.class));
                            }

                            @Override
                            public void afterNewRequestSession() {
                                getStoreDisplayInfo(project, circle, rank, num);
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

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
