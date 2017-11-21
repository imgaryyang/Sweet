package com.lucky.sweet.moudel;

import android.content.Context;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lucky.sweet.properties.Properties;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by c on 2017/11/19.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class LoginRegisterManager {
    private Context context;
    private LoginRegisterHandler handler;
    /**
     * USERLOGIN ：用户登陆
     * CHECKOUTEMAIL ：邮箱检测
     * EMAILVER ：确认验证码
     * USERREGISTER ：用户注册
     */
    public final static int USERLOGIN = 1;
    public final static int CHECKOUTEMAIL = 2;
    public final static int EMAILVER = 3;
    public final static int USERREGISTER = 4;

    public LoginRegisterManager(Context context, LoginRegisterHandler handler) {
        this.context = context;
        this.handler = handler;
    }

    /**
     * 发送数据请求
     *
     * @param type     数据请求类型
     * @param email    邮箱
     * @param password 密码
     */
    private void sendRequest(final int type, final String email, @Nullable final
    String password) {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = null;
                    switch (type) {
                        case USERLOGIN:
                            request = new Request.Builder().url(Properties.LOGINREQUESTPATH).post(new FormBody.Builder().add("username", email).add("password", password).build()).build();
                            break;
                        case CHECKOUTEMAIL:
                            request = new Request.Builder().url(Properties.CHECKOUTEMAILPATH).post(new FormBody.Builder().add("mail_address", email).build()).build();
                            break;
                        case EMAILVER:
                            request = new Request.Builder().url(Properties.MAILVERPATH).post(new FormBody.Builder().add("mail_address", email).add(" mail_ver", password).build()).build();
                            break;
                        case USERREGISTER:
                            request = new Request.Builder().url(Properties.USERREGISTERPATH).post(new FormBody.Builder().add("mail_address", email).add(" password", password).build()).build();
                            break;
                        default:
                            break;
                    }

                    Response response = client.newCall(request).execute();

                    if (response.isSuccessful()) {
                        String str = response.body().string();
                        Log.i("ServerBackCode:", str);
                        Message message = new Message();
                        message.what = type;
                        message.arg1 = Integer.parseInt(str);
                        handler.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 用户登陆
     *
     * @param email    用户邮箱
     * @param password 密码
     */
    public void UserLogin(String email, String password) {

        sendRequest(USERLOGIN, email, password);


    }

    /**
     * 请求验证码
     *
     * @param email 邮箱字符串
     */
    public void CheckOutEmail(String email) {

        sendRequest(CHECKOUTEMAIL, email, null);

    }

    /**
     * 提交验证码和邮件地址
     *
     * @param email       邮件地址
     * @param verPassword 验证码
     * @return
     */
    public void emailVer(String email, String verPassword) {

        sendRequest(EMAILVER, email, verPassword);

    }

    /**
     * 提交邮箱和密码
     *
     * @param email    邮箱
     * @param password 密码
     */
    public void UserRegister(String email, String password) {

        sendRequest(USERREGISTER, email, password);

    }

    /**
     * 删除表
     */
    public static void deleteTable() {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("  http://thethreestooges.cn/consumer/test/temp_delete.php").get().build();
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        System.out.println(response.body().string());
                    }
                    /*request= new Request.Builder().url(" " + "http://thethreestooges.cn/consumer/test/user_delete.php").get().build();
                    Response execute = client.newCall(request).execute();*/
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
