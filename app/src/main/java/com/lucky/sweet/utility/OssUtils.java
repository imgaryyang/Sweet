package com.lucky.sweet.utility;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.google.gson.Gson;
import com.lucky.sweet.activity.MyApplication;
import com.lucky.sweet.entity.OssBean;
import com.lucky.sweet.properties.Properties;

import java.io.IOException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by chn on 2018/1/18.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class OssUtils {

    private static String KEY_ID = "";
    private static String SECRET_KEY_ID = "";
    private static String TOKEN = "";


    private final static String requestPah = "https://thethreestooges.cn:5210/identity/oss/token.php";
    private static void initOSS(final OnOSSContecent onOSSContecent) {

        getInfo(new OnLoginSuccessful() {
            @Override
            public void onKeyUpData() {
                OSSCredentialProvider credentialProvider = new
                        OSSStsTokenCredentialProvider(KEY_ID, SECRET_KEY_ID, TOKEN
                );
                ClientConfiguration conf = new ClientConfiguration();
                conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
                conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
                conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个
                conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次

                onOSSContecent.onClientSuccessful(new OSSClient(MyApplication.getContext().getApplicationContext(), Properties.END_POINT, credentialProvider));

            }
        });

    }

    private interface OnLoginSuccessful {
        void onKeyUpData();
    }

    private interface OnOSSContecent {
        void onClientSuccessful(OSS oss);
    }


    public static void down(final String objectKey,
                            final OSSCompletedCallback<GetObjectRequest,
                                    GetObjectResult> call)

    {
        initOSS(new OnOSSContecent() {
            @Override
            public void onClientSuccessful(OSS oss) {
                GetObjectRequest get = new GetObjectRequest(Properties
                        .TEST_BUCKET,
                        objectKey);

                get.setxOssProcess("image/resize,m_fixed,w_100," + "h_100/quality,q_50");
                oss.asyncGetObject(get, call);
            }
        });


    }


    private static void getInfo(final OnLoginSuccessful onLoginSuccessful) {
        HttpUtils.sendOkHttpRequest(Properties.REQUEST_PATH, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                OssBean ossBean = gson.fromJson(response.body().string
                        (), OssBean.class);
                KEY_ID = ossBean.getCredentials().getAccessKeyId();
                TOKEN = ossBean.getCredentials().getSecurityToken();
                SECRET_KEY_ID = ossBean.getCredentials().getAccessKeySecret();
                onLoginSuccessful.onKeyUpData();

            }
        });
    }
}
