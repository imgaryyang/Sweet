package com.lucky.sweet.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.lucky.sweet.R;
import com.lucky.sweet.service.CommunicationService;

public class DetailedDishesActivity extends BaseActivity {

    private static final String EXTRA_TRAVEL = "DETAILED_DISHES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_dishes);
    }

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, DetailedDishesActivity.class);
        //intent.putExtra(EXTRA_TRAVEL, travel);
        return intent;
    }

    @Override
    void onServiceBind(CommunicationService.MyBinder myBinder) {

    }
}
