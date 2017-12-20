package com.lucky.sweet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lucky.sweet.R;

/**
 * Created by Qiuyue on 2017/12/19.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class OrderSeatActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderseat);

        findViewById(R.id.btn_order_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderSeatActivity.this, OrderActivity.class));
            }
        });
    }
}
