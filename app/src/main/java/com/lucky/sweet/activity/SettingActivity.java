package com.lucky.sweet.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lucky.sweet.R;
import com.lucky.sweet.service.CommunicationService;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.ToolBar;

/**
 * Created by Qiuyue on 2018/3/2.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class SettingActivity extends BaseActivity {
    private Title title = null;
    private SharedPreferences config;
    private SharedPreferences.Editor edit;
    private Button btn_userOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initTitle();
        initData();
        initViews();
        onClick();
    }

    @Override
    void onServiceBind(CommunicationService.MyBinder myBinder) {

    }

    private void initTitle() {
        ToolBar toolBar = new ToolBar(this);
        toolBar.setColorNewBar(getResources().getColor(R.color.white), 0);

        title = findViewById(R.id.title);
        title.setTitleNameStr("设置");
        Title.ButtonInfo buttonLeft = new Title.ButtonInfo(true, Title
                .BUTTON_LEFT, R.drawable.selector_btn_titleback, null);
        title.setOnTitleButtonClickListener(new Title
                .OnTitleButtonClickListener() {
            @Override
            public void onClick(int id, Title.ButtonViewHolder viewHolder) {
                switch (id) {
                    case Title.BUTTON_LEFT:
                        finish();
                        goPreAnim();
                        break;
                }
            }
        });
        title.mSetButtonInfo(buttonLeft);
    }

    private void initData() {
        config = this.getSharedPreferences("config", Activity.MODE_PRIVATE);
        edit = config.edit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (resultCode) {
                case 0:
                    boolean login = data.getBooleanExtra("login", true);
                    if (login) {
                        edit.putBoolean("logined", true);
                        edit.commit();
                        btn_userOut.setText("退出登陆");

                    }
                    break;

                default:
                    break;
            }

        }


    }

    private void initViews() {
        btn_userOut = (Button) findViewById(R.id.btn_userOut);
        if (config.getBoolean("logined", false)) {
            btn_userOut.setText("退出登陆");
        } else {
            btn_userOut.setText("用户登陆");
        }

        btn_userOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn_userOut.getText().toString().equals("退出登陆")) {
                    new AlertDialog.Builder(SettingActivity.this)
                            .setMessage("确定退出账号？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(SettingActivity.this, UserLoginActivity.class);
                                    startActivityForResult(intent, 0);
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create()
                            .show();
                } else {
                    edit.putBoolean("logined", false);
                    edit.commit();
                    btn_userOut.getText().toString().equals("用户登陆");
                    startActivity(new Intent(SettingActivity.this, UserLoginActivity.class));
                    return;
                }
            }
        });
    }

    private void onClick(){
        findViewById(R.id.ll_setUserInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, VipCardActiviry.class));
                overridePendingTransition(R.anim.act_left_in, R.anim.act_left_out);
            }
        });
    }
}
