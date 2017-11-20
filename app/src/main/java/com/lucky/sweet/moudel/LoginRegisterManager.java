package com.lucky.sweet.moudel;

import android.content.Context;
import android.os.Message;

import com.lucky.sweet.properties.Properties;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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

    public LoginRegisterManager(Context context, LoginRegisterHandler handler) {
        this.context = context;
        this.handler = handler;
    }

    /**
     * 用户登陆验证
     *
     * @param mailAddress 用户账号
     * @param password    用户密码
     * @return 情况反馈
     * 登陆成功时候 返回 0
     * 密码错误 返回 1
     * 用户不存在 返回 2
     * 提交字段有误 返回 3
     */
    public void UserLogin(final String mailAddress, final String password) {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder().add("username", mailAddress).add("password", password).build();
                    Request request = new Request.Builder().url(Properties.LOGINREQUESTPATH).post(requestBody).build();
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String str = response.body().string();
                        System.out.println("服务器响应为: " + str);
                        Message message = new Message();
                        message.what = Properties.USERLOGIN;
                        message.arg1 = Integer.parseInt(str);
                        handler.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    static final int SUBMITSUCCESSFUL = 0;
    static final int EMAILEXIST = 1;
    static final int STRINGERROR = 3;

    /**
     * 请求验证码
     *
     * @param email 邮箱字符串
     * @return 情况反馈
     * 邮箱已存在返回 1
     * 发送成功返回   0
     * 提交字段有误 返回 3
     */
    public void CheckOutEmail(final String email) {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder().add
                            ("mail_address",
                                    email).build();
                    Request request = new Request.Builder().url(Properties
                            .MAILSUBMITPATH).post(requestBody).build();
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String str = response.body().string();
                        System.out.println("服务器响应为: " + str);
                        Message msg = new Message();
                        msg.what = Properties.CHECKOUTEMAIL;
                        msg.arg1 = Integer.parseInt(str);
                        handler.sendMessage(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    /**
     * 提交验证码和邮件地址
     *
     * @param email       邮件地址
     * @param verPassword 验证码
     * @return
     */
    public void CheckOutEmailFirPsw(final String email, final String verPassword) {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder().add("mail_address",
                            email).add(" mail_ver", verPassword).build();
                    Request request = new Request.Builder().url(Properties
                            .MAILVERIFICATION).post(requestBody).build();
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String str = response.body().string();
                        Message msg = new Message();
                        msg.what = Properties.CHECKOUTEMAILFIRPSW;
                        msg.arg1 = Integer.parseInt(str);
                        handler.sendMessage(msg);
                        System.out.println("服务器响应为: " + str);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    /**
     * 提交邮箱和密码
     *
     * @param email    邮箱
     * @param Password 密码
     * @return 情况反馈
     * 成功返回 0
     * 验证码未通过匹配 1
     * 邮箱不存在或已过期返回 2
     * 提交字段有误 返回 3
     */
    public void RegestUser(final String email, final String Password) {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder().add("mail_address", email).add(" password", Password).build();
                    Request request = new Request.Builder().url(Properties.REGISTERQUESTPATH).post(requestBody).build();
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String str = response.body().string();
                        Message msg = new Message();
                        msg.what = Properties.REGESTUSER;
                        msg.arg1 = Integer.parseInt(str);
                        handler.sendMessage(msg);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    /**
     *
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
