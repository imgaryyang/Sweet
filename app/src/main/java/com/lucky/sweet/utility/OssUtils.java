package com.lucky.sweet.utility;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.lucky.sweet.activity.MyApplication;

import java.io.InputStream;

import static com.lucky.sweet.properties.Properties.TEST_BUCKET;

/**
 * Created by chn on 2018/1/18.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class OssUtils {
    public interface OnInpustreamBack {
        void inputstreamFinish(InputStream inputStream);
    }

    public static void down(final String objectKey,
                            final OnInpustreamBack onInpustreamBack)

    {

        GetObjectRequest request = new GetObjectRequest(TEST_BUCKET,
                objectKey.trim());
        MyApplication.getOSSClient().asyncGetObject(request, new
                OSSCompletedCallback<GetObjectRequest, GetObjectResult>() {
                    @Override
                    public void onSuccess(GetObjectRequest request, GetObjectResult result) {
                        onInpustreamBack.inputstreamFinish(result.getObjectContent());

                    }

                    @Override
                    public void onFailure(GetObjectRequest request, ClientException clientException, ServiceException serviceException) {


                    }
                });


    }
}
