package com.lucky.sweet.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.lucky.sweet.R;
import com.lucky.sweet.model.OrderSeatManager;
import com.lucky.sweet.views.DishesOrderDialog;
import com.lucky.sweet.views.PeopleNumOrderDialog;
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
    private TextView tv_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderseat);

        initView();

        initEvent();

    }

    private void initView() {
        tv_timeSelect = findViewById(R.id.tv_timeSelect);
        tv_num = findViewById(R.id.tv_num);
        getWindow().setSoftInputMode(WindowManager.LayoutParams
                .SOFT_INPUT_ADJUST_PAN);
        initTitle();

    }

    private void initEvent() {
        tv_timeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DishesOrderDialog dishesOrderDialog = new DishesOrderDialog
                        (OrderSeatActivity.this,
                        OrderSeatManager.getDate(), OrderSeatManager.getTime());
                dishesOrderDialog.setDateSelectListener
                        (new DishesOrderDialog.OnDateSelectListener() {
                            @Override
                            public void onDateSelected(String dates) {
                                tv_timeSelect.setText(dates);
                            }
                        });

            }
        });
        tv_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PeopleNumOrderDialog peopleNumOrderDialog = new
                        PeopleNumOrderDialog(OrderSeatActivity.this,
                        OrderSeatManager.getPeopleNum());
                peopleNumOrderDialog.setDateSelectListener(new PeopleNumOrderDialog.OnNumelectListener() {
                    @Override
                    public void onNumSelected(String nums) {
                        tv_num.setText(nums);
                    }
                });
            }
        });
        findViewById(R.id.btn_order_commit).setOnClickListener(new View
                .OnClickListener() {
            @Override
            public void onClick(View v) {

//                startActivity(new Intent(OrderSeatActivity.this,
// OrderActivity.class));

                AlertDialog dialog = new AlertDialog.Builder(OrderSeatActivity.this)
//                        .setTitle("标题")
                        .setMessage("需要提前点菜吗？")
                        .setNegativeButton("直接订座", new DialogInterface
                                .OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int
                                    which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("提前点菜", new DialogInterface
                                .OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int
                                    which) {
                                startActivity(new Intent(OrderSeatActivity
                                        .this, MerchantActivity.class));

                            }
                        })
                        .create();
                dialog.show();

            }
        });
    }

    private void initTitle() {
//

        ToolBar toolBar = new ToolBar(this);
        toolBar.setImmersionBar();
        int statusBarHeight = toolBar.getStatusBarHeight(OrderSeatActivity
                .this);
        View view_margin = findViewById(R.id.title_detail);
        ViewGroup.LayoutParams lp;
        lp = view_margin.getLayoutParams();
        lp.height = statusBarHeight;
        view_margin.setLayoutParams(lp);


        title = (Title) findViewById(R.id.title);
        title.setTheme(Title.Theme.THEME_TRANSLATE_NODIVIDER);
        title.setTitleNameStr("预订座位");
        Title.ButtonInfo buttonLeft = new Title.ButtonInfo(true, Title
                .BUTTON_LEFT, R.drawable.selector_btn_titleback, null);
        Title.ButtonInfo buttonRight = new Title.ButtonInfo(true, Title
                .BUTTON_RIGHT1, R.drawable.selector_btn_titleback, null);
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
