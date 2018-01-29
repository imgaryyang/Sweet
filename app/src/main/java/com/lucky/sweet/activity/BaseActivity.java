package com.lucky.sweet.activity;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

import com.lucky.sweet.R;
import com.lucky.sweet.service.CommunicationService;
import com.lucky.sweet.views.SlidingLayoutView;

public abstract class BaseActivity extends AppCompatActivity {

    public static String sessionId = "";

    private MyConn conn = new MyConn();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindService();
        if (enableSliding()) {
            SlidingLayoutView rootView = new SlidingLayoutView(this);
            rootView.bindActivity(this);
        }

    }


    private void bindService() {

        bindService(new Intent(this, CommunicationService.class), conn, BIND_AUTO_CREATE);

    }


    @Override
    protected void onResume() {
        super.onResume();
        bindService();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(conn);
    }


    abstract void onServiceBind(CommunicationService.MyBinder myBinder);



    private class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder
                iBinder) {
            CommunicationService.MyBinder myBinder = (CommunicationService.MyBinder) iBinder;
            onServiceBind(myBinder);

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }

    public void initializeSessionId() {
        SharedPreferences config = getSharedPreferences("config", MODE_PRIVATE);
        if (config.getBoolean("logined", false)) {
            sessionId = config.getString("Session", "");
        }
    }


    public AlertDialog showDialogBaseAct(String title, String message,
                                         String natureName,
                                         DialogInterface.OnClickListener
                                                 onClickNature,
                                         String positiveName, DialogInterface.OnClickListener onClickPositive,
                                         String negativeName,
                                         DialogInterface.OnClickListener
                                                 onClickNegative, Context
                                                 context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle(title)
                .setNegativeButton(natureName, onClickNature).setMessage
                        (message);
        if (null != positiveName)
            builder.setPositiveButton(positiveName, onClickPositive);
        if (null != negativeName)
            builder.setNegativeButton(negativeName, onClickNegative);
        AlertDialog dialog = builder.create();
        dialog.show();
        return dialog;

    }

    protected void hintInputKb() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context
                .INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


    /**
     * 启动下一个Activity的动画
     */
    public void goNextAnim() {
        overridePendingTransition(R.anim.act_left_in, R.anim.act_left_out);
    }

    /**
     * 返回上一个Activity的动画
     */
    public void goPreAnim() {
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    /*淡入淡出动画*/
    public void goFadeAnim() {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    /*从下至上动画*/
    public void goUpAnim() {
        overridePendingTransition(R.anim.act_up_in, R.anim.act_down_out);
    }

    /*从上至下动画*/
    public void goDownAnim() {
        overridePendingTransition(R.anim.act_up, R.anim.act_down);
    }

    /*关闭侧滑手势*/
    protected boolean enableSliding() {
        return true;
    }
}
