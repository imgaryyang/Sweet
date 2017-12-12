package com.lucky.sweet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.lucky.sweet.R;
import com.lucky.sweet.widgets.ToolBar;

/**
 * Created by Qiuyue on 2017/12/10.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class SplashActivity extends BasicActivity {
    private TextView tv_time;
    private MyCountDownTimer timer;
    private ToolBar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstactivity);
        initViews();
    }

    private void initViews(){
        toolBar = new ToolBar(this);
        toolBar.setImmersionBar();

        tv_time = (TextView)findViewById(R.id.tv_time);
        timer = new MyCountDownTimer(4000,1000);
        timer.start();
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

                finish();
            }
        }, 4000);
    }

    class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture,long countDownInterval){
            super(millisInFuture,countDownInterval);
        }

        @Override
        public void onFinish() {
            tv_time.setText("进入Sweet!");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tv_time.setText("倒计时("+millisUntilFinished/1000+")");
        }
    }

}