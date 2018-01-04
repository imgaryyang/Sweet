package com.lucky.sweet.model.loginregister;

import android.content.Context;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lucky.sweet.entity.UserLoginInfo;
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
    private Boolean isLogin = false;
    private Context context;
    private LoginRegisterHandler handler;
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

    public LoginRegisterManager(Context context) {
        this.context = context;
        this.handler = new LoginRegisterHandler(context);
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
                            isLogin = true;
                            request = new Request.Builder().url(Properties.LOGINPATH).post(new FormBody.Builder().add("username", email).add("password", password).build()).build();
                            break;
                        case CHECKOUTEMAIL:
                            request = new Request.Builder().url(Properties.MAILSUBMITPATH).post(new FormBody.Builder().add("mail_address", email).build()).build();
                            break;
                        case EMAILVER:
                            request = new Request.Builder().url(Properties.MAILVALIDATEPATH).post(new FormBody.Builder().add("mail_address", email).add(" mail_ver", password).build()).build();
                            break;
                        case USERREGISTER:
                            request = new Request.Builder().url(Properties.USERWRITEPATH).post(new FormBody.Builder().add("mail_address", email).add(" password", password).build()).build();
                            break;
                        case FORGETSUBMIT:
                            request = new Request.Builder().url(Properties.FORGETSUBMITPATH).post(new FormBody.Builder().add("mail_address", email).build()).build();
                            break;
                        case FORGETVALIDATE:
                            request = new Request.Builder().url(Properties.FORGETVALIDATEPATH).post(new FormBody.Builder().add("mail_address", email).add(" mail_ver", password).build()).build();
                            break;
                        case USERFORGET:
                            request = new Request.Builder().url(Properties.USERFORGETPATH).post(new FormBody.Builder().add("mail_address", email).add(" password", password).build()).build();
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

    /**
     * 用户登陆
     *
     * @param email    用户邮箱
     * @param password 密码
     */
    public void userLogin(String email, String password) {

        sendRequest(USERLOGIN, email, password);

    }

    /**
     * 请求验证码
     *
     * @param email 邮箱字符串
     */
    public void checkOutEmail(String email) {

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
    public void userRegister(String email, String password) {

        sendRequest(USERREGISTER, email, password);

    }

    /**
     * 忘记密码确认邮箱
     *
     * @param email
     */
    public void forgetSubmit(String email) {

        sendRequest(FORGETSUBMIT, email, null);

    }


    public void forgetValidate(String email, String verPassword) {

        sendRequest(FORGETVALIDATE, email, verPassword);

    }

    public void userForget(String email, String password) {

        sendRequest(USERFORGET, email, password);

    }

    /**
     * 删除表
     */
    public void deleteTable() {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://thethreestooges.cn/consumer/test/temp_delete.php").get().build();
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
