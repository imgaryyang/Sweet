package com.lucky.sweet.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.ParcelUuid;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.lucky.sweet.R;
import com.lucky.sweet.entity.DetailOrderInfo;
import com.lucky.sweet.service.CommunicationService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DetailOrderActivity extends BaseActivity {
    public static final String INDENT_ID = "indent_id";

    public static void newInitialize(Activity activity, String indent_id) {
        Intent intent = new Intent(activity, DetailOrderActivity.class);
        intent.putExtra(INDENT_ID, indent_id);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);
        setIsBindEventBus();
    }

    @Override
    void onServiceBind(CommunicationService.MyBinder myBinder) {

        myBinder.getDetailedOrderInfo(getIntent().getStringExtra(INDENT_ID));
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Evnet(DetailOrderInfo info) {
        System.out.println("????");
        Toast.makeText(this, info.toString(), Toast.LENGTH_SHORT).show();
    }
}
