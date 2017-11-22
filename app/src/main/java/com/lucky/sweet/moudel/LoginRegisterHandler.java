package com.lucky.sweet.moudel;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.lucky.sweet.activity.MainActivity;
import com.lucky.sweet.activity.UserCheckPwdActivity;
import com.lucky.sweet.entitty.UserInfo;
import com.lucky.sweet.properties.Properties;

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
//    登陆成功时候 返回 0
//    密码错误 返回 1
//    用户不存在 返回 2
//    提交字段有误 返回 3

    private static final int LOGINSSUCCEED = 0;
    private static final int PSWFAILD = 1;
    private static final int USERUNEXIST = 2;

    private static final int REGESTERSCUSS = 0;
    private static final int REGESTERFAIL = 1;

    public LoginRegisterHandler(Context context) {
        this.context = context;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case LoginRegisterManager.CHECKOUTEMAIL:
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
            case LoginRegisterManager.USERLOGIN:
                switch (msg.arg1) {
                    case LOGINSSUCCEED:
                        Toast.makeText(context, "登陆成功", Toast.LENGTH_SHORT)
                                .show();
                        context.startActivity(new Intent(context,
                                MainActivity.class));

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
            case LoginRegisterManager.USERREGISTER:
                switch (msg.arg1) {
                    case REGESTERSCUSS:
                        Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, MainActivity.class));

                        break;
                    case REGESTERFAIL:
                        Toast.makeText(context, "注册失败", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }

                break;
            case LoginRegisterManager.EMAILVER:
                switch (msg.arg1) {
                    case VERSSUCCEED:
                        Toast.makeText(context, "验证成功！", Toast.LENGTH_SHORT)
                                .show();
                        Intent intent = new Intent(context, UserCheckPwdActivity.class);
                        intent.putExtra("userEmail", ((UserInfo) msg.obj).getUserName());
                        context.startActivity(intent);
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

            default:
                break;
        }
    }
}
