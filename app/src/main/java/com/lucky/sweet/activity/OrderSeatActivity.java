package com.lucky.sweet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.lucky.sweet.R;
import com.lucky.sweet.moudel.OrderSeatManager;
import com.lucky.sweet.views.DishesOrderDialog;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.ToolBar;

/**
 * Created by Qiuyue on 2017/12/19.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class OrderSeatActivity extends BaseActivity {
    private Title title = null;
    private TextView tv_timeSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderseat);

        initView();

        initEvent();

    }

    private void initView() {
        tv_timeSelect = findViewById(R.id.tv_timeSelect);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initTitle();

    }

    private void initEvent() {
        tv_timeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DishesOrderDialog dishesOrderDialog = new DishesOrderDialog();
                dishesOrderDialog.showDialog(OrderSeatActivity.this,
                        OrderSeatManager.getDate(), OrderSeatManager.getTime())
                        .setDateSelectListener
                        (new DishesOrderDialog.OnDateSelectListener() {
                    @Override
                    public void onDateSelected(String dates) {
                        tv_timeSelect.setText(dates);
                    }
                });

            }
        });
        findViewById(R.id.btn_order_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderSeatActivity.this, OrderActivity.class));
            }
        });
    }

    private void initTitle() {
        ToolBar toolBar = new ToolBar(this);
        toolBar.setColorNewBar(getResources().getColor(R.color.white), 0);
        title = (Title) findViewById(R.id.title);
        title.setTitleNameStr("预订座位");
        Title.ButtonInfo buttonLeft = new Title.ButtonInfo(true, Title
                .BUTTON_LEFT, R.drawable.selector_btn_titleback, null);
        title.setOnTitleButtonClickListener(new Title
                .OnTitleButtonClickListener() {
            @Override
            public void onClick(int id, Title.ButtonViewHolder viewHolder) {
                switch (id) {
                    case Title.BUTTON_LEFT:
                        hintInputKb();
                        finish();
                        break;

                }
            }
        });
        title.mSetButtonInfo(buttonLeft);

    }
}
