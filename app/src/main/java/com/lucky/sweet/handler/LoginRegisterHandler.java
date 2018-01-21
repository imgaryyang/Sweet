package com.lucky.sweet.handler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.lucky.sweet.activity.MyApplication;
import com.lucky.sweet.activity.UserLoginActivity;
import com.lucky.sweet.activity.UserRegisterActivity;
import com.lucky.sweet.entity.UserLoginInfo;
import com.lucky.sweet.properties.UserLoginProperties;

/**
 * Created by c on 2017/11/20.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class LoginRegisterHandler extends Handler {
    private Context context;
    /*
   *确认邮箱返回成功 0
   * 邮箱已经存在 1
   * 验证码发送频率过快 2
   */
    private static final int SUBMITSUCCESSFUL = 0;
    private static final int EMAILEXIST = 3;
    private static final int FREQUENCYFAST = 2;
    /*
      *执行成功返回 0
      * 验证码不匹配 1
      * 验证码过期或邮箱不存在返回 2
      */
    private static final int VERSSUCCEED = 0;
    private static final int VERSFAILD = 1;
    private static final int VERTIMOUT = 2;
//    登陆成功时候 返回 -1
//    密码错误 返回 1
//    用户不存在 返回 2
//    提交字段有误 返回 3

    public static final int LOGINSSUCCEED = -1;
    private static final int PSWFAILD = 1;
    private static final int USERUNEXIST = 2;
    /**
     * 注册失败
     * 注册成功
     */
    private static final int REGESTERSCUSS = 0;
    private static final int REGESTERFAIL = 1;
    /**
     * 发送成功返回   0
     * 发送失败返回   1
     * 验证码发送频率过快返回 2  （测试时间100秒）
     * 邮箱已存在返回 3
     */
    private static final int FORGETSUCCESS = 0;
    private static final int FORGETFAIL = 1;
    private static final int FORGETFAST = 2;
    private static final int FORGETEMAILEXIST = 3;
    /**
     * 执行成功返回 0
     * 验证码错误 1
     * 验证码过期并重新发送失败返回 3
     * 无该用户记录返回 5
     * 提交字段有误 返回 4
     */
    private static final int FORGETVERSUCCESS = 0;
    private static final int FORGETVERFAIL = 1;
    private static final int FORGETTIMEOUT = 3;
    private static final int FORGETUNEMAILEXIST = 5;

    /**
     * 成功返回 0
     * 失败返回 1
     */
    private static final int FORGETCHANGESUCCESS = 0;
    private static final int FORGETCHANGRFAIL = 1;

    private final SharedPreferences.Editor edit;

    public LoginRegisterHandler(Context context) {
        this.context = context;
        SharedPreferences config = context.getSharedPreferences("config",
                Activity.MODE_PRIVATE);
        edit = config.edit();
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case UserLoginProperties.CHECKOUTEMAIL:
                switch (msg.arg1) {
                    case SUBMITSUCCESSFUL:
                        Toast.makeText(context, "请等待邮件", Toast.LENGTH_SHORT).show();
                        break;
                    case EMAILEXIST:
                        Toast.makeText(context, "当前邮箱已注册", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case FREQUENCYFAST:
                        Toast.makeText(context, " 验证码发送频率过快", Toast.LENGTH_SHORT)
                                .show();
                        break;
                }
                break;
            case UserLoginProperties.USERLOGIN:
                switch (msg.arg1) {
                    case LOGINSSUCCEED:
                        Toast.makeText(context, "登陆成功", Toast.LENGTH_SHORT).show();
                        UserLoginInfo info = (UserLoginInfo) msg.obj;
                        if (loginSucced(info, true)) {
              /*              Intent intent = new Intent();
                            intent.putExtra("login", true);
                            context.startActivity(new Intent(context, MainActivity.class));*/
                            ((Activity) context).finish();

                        }
                        break;
                    case PSWFAILD:
                        Toast.makeText(context, "用户不存在或密码错误", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case USERUNEXIST:
                        Toast.makeText(context, "当前账号不存在", Toast.LENGTH_SHORT)
                                .show();
                        break;
                }

                break;
            case UserLoginProperties.USERREGISTER:
                switch (msg.arg1) {
                    case REGESTERSCUSS:
                        Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
                        UserLoginInfo info = (UserLoginInfo) msg.obj;
                        if (loginSucced(info, false)) {
                            ((Activity) context).finish();
                        }
                        break;
                    case REGESTERFAIL:
                        Toast.makeText(context, "注册失败", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }

                break;
            case UserLoginProperties.EMAILVER:
                switch (msg.arg1) {
                    case VERSSUCCEED:
                        if (context instanceof UserRegisterActivity) {
                            Toast.makeText(context, "验证成功！", Toast.LENGTH_SHORT).show();
                            ((UserRegisterActivity) context).moveToNextStep();
                            ((UserRegisterActivity) context).setEmail(((UserLoginInfo) msg.obj).getEmail());
                        }
                        break;
                    case VERSFAILD:
                        Toast.makeText(context, "验证码正确但写入失败", Toast.LENGTH_SHORT).show();
                        break;
                    case VERTIMOUT:
                        Toast.makeText(context, "验证码过期并重新发送成功返回", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(context, "验证失败", Toast.LENGTH_SHORT)
                                .show();
                        break;
                }
                break;

            case UserLoginProperties.FORGETSUBMIT:
                switch (msg.arg1) {
                    case FORGETSUCCESS:
                        Toast.makeText(context, "发送成功请稍等", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case FORGETFAIL:
                        Toast.makeText(context, "发送失败", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case FORGETFAST:
                        Toast.makeText(context, "验证码发送频率过快", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case FORGETEMAILEXIST:
                        Toast.makeText(context, "邮箱已存在", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    default:
                        break;
                }
                break;
            case UserLoginProperties.FORGETVALIDATE:
                switch (msg.arg1) {
                    case FORGETVERSUCCESS:
                        if (context instanceof UserRegisterActivity) {
                            ((UserRegisterActivity) context).moveToNextStep();
                            ((UserRegisterActivity) context).setEmail(((String) msg.obj));
                        }
                        break;
                    case FORGETVERFAIL:
                        Toast.makeText(context, "验证码错误", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case FORGETTIMEOUT:
                        Toast.makeText(context, "验证码过期并重新发送失败返回 ", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case FORGETUNEMAILEXIST:
                        Toast.makeText(context, "无该用户记录返回 ", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    default:
                        break;
                }
                break;
            case UserLoginProperties.USERFORGET:
                switch (msg.arg1) {
                    case FORGETCHANGESUCCESS:
                        Toast.makeText(context, "修改成功 ", Toast.LENGTH_SHORT)
                                .show();
                        context.startActivity(new Intent(context, UserLoginActivity.class));
                        break;
                    case FORGETCHANGRFAIL:
                        Toast.makeText(context, "失败返回 ", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    default:
                        break;
                }

                break;
            default:
                break;
        }
    }

    public boolean loginSucced(UserLoginInfo info, boolean flag) {
        edit.putBoolean("logined", true);
        edit.putString("Id", info.getEmail());
        edit.putString("Psw", info.getPsw());

        if (flag) MyApplication.setSessionID(info.getSession());
        return edit.commit();
    }
}
