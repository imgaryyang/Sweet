package com.lucky.sweet.activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;

import com.lucky.sweet.R;
import com.lucky.sweet.model.qrcodescan.ScanningQR;
import com.lucky.sweet.views.SlidingLayoutView;
import com.lucky.sweet.widgets.ToolBar;


/**
 * 拍照的Activity
 *
 *
 */
public class CaptureActivity extends FragmentActivity {
private ScanningQR fragment;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        initView();
        initEvent();
        initToolBar();
        SlidingLayoutView rootView = new SlidingLayoutView(this);
        rootView.bindActivity(this);
    }

    private void initToolBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ToolBar toolBar = new ToolBar(this);
            toolBar.setImmersionBar();
            int statusBarHeight = toolBar.getStatusBarHeight(this);
            View view_margin = findViewById(R.id.view_margin);
            ViewGroup.LayoutParams lp;
            lp = view_margin.getLayoutParams();
            lp.height = statusBarHeight;
            view_margin.setLayoutParams(lp);
        } else {}
    }

    private void initView() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
         fragment= (ScanningQR) supportFragmentManager.findFragmentById(R.id.fragment_scanningqr);
findViewById(R.id.mo_scanner_photo);
    }
    private void initEvent() {
       fragment.setBackOnClickListener(new ScanningQR.BackOnClickListener() {
           @Override
           public void setonBackClickListener(View view) {
               finish();
           }
       });
       fragment.setObtainResultCodeListener(new ScanningQR.GetDecode() {
           @Override
           public void getDecodeString(String decode) {
               Intent data = new Intent();
               data.putExtra("result", decode);
               setResult(300, data);
               finish();
               overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

           }
       });
    }
}