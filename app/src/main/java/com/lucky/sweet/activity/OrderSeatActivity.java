package com.lucky.sweet.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lucky.sweet.R;
import com.lucky.sweet.entity.PerdetermingEntity;
import com.lucky.sweet.service.CommunicationService;
import com.lucky.sweet.utility.RegularUtils;
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
    private CommunicationService.MyBinder myBinder;
    private Title title = null;
    private TextView tv_timeSelect;
    private TextView edt_num;
    private EditText et_input_phone;
    private EditText edt_people_num;
    private EditText edt_order_des;
    private String[] data;
    private String[][] time;
    private LinearLayout ll_order_seat;
    private String mer_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderseat);

        initView();

        initData();

        initEvent();

    }

    private void initData() {
        try {
            /*ll_order_seat.setBackground(BitmapDrawable.createFromPath(Properties.ORDER_SEAT_BACKGROUND_PATH +
                    "/background.png"));*/
            Intent intent = getIntent();
            mer_id = intent.getStringExtra("mer_id");
        } catch (Exception e) {

        }
    }

    @Override
    void onServiceBind(CommunicationService.MyBinder myBinder) {
        this.myBinder = myBinder;
        myBinder.requestPerdeterming(this);
    }

    private void initView() {
        tv_timeSelect = findViewById(R.id.tv_timeSelect);
        edt_num = findViewById(R.id.edt_num);
        ll_order_seat = findViewById(R.id.ll_order_seat);
        et_input_phone = findViewById(R.id.et_input_phone);
        edt_people_num = findViewById(R.id.edt_people_num);
        edt_order_des = findViewById(R.id.edt_order_des);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initTitle();
    }

    public void upDateRequest(PerdetermingEntity entity) {
        data = entity.getData();
        time = entity.getTime();
    }

    private void initEvent() {
        tv_timeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DishesOrderDialog dishesOrderDialog = new DishesOrderDialog
                        (OrderSeatActivity.this,
                                data, time);
                dishesOrderDialog.setDateSelectListener
                        (new DishesOrderDialog.OnDateSelectListener() {
                            @Override
                            public void onDateSelected(String dates) {
                                tv_timeSelect.setText(dates);
                            }
                        });

            }
        });

        findViewById(R.id.btn_order_commit).setOnClickListener(new View
                .OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyOrder();
            }
        });
    }

    private void verifyOrder() {
        String phone = et_input_phone.getText().toString().trim();
        boolean result = RegularUtils.isPhoneNumber(phone);
        if (result == true) {
            AlertDialog dialog = new AlertDialog.Builder(OrderSeatActivity.this)
                    .setMessage("需要提前点菜吗？")
                    .setNegativeButton("直接订座", (dialog12, which) -> {
                        sendOrderSeatInfo();
                        dialog12.dismiss();
                    })
                    .setPositiveButton("提前点菜", (dialog1, which) -> {
                        sendOrderSeatInfo();
                        MerchantActivity.newSingleOrderInStance(OrderSeatActivity.this, mer_id);
                        goNextAnim();

                    })
                    .create();
            dialog.show();
        } else {
            Toast.makeText(getApplication(), "手机号不对哦，请重新输入", Toast.LENGTH_SHORT).show();
        }
    }

    private void initTitle() {
        ToolBar toolBar = new ToolBar(this);
        toolBar.setColorNewBar(getResources().getColor(R.color.white), 0);
        title = (Title) findViewById(R.id.title);
        title.setTitleNameStr("预订座位");
        Title.ButtonInfo buttonLeft = new Title.ButtonInfo(true, Title
                .BUTTON_LEFT, 0, "取消");
        Title.ButtonInfo buttonRight = new Title.ButtonInfo(true, Title.BUTTON_RIGHT1, 0, "完成");
        title.setOnTitleButtonClickListener(new Title
                .OnTitleButtonClickListener() {
            @Override
            public void onClick(int id, Title.ButtonViewHolder viewHolder) {
                switch (id) {
                    case Title.BUTTON_LEFT:
                        hintInputKb();
                        finish();
                        goPreAnim();
                        break;
                    case Title.BUTTON_RIGHT1://不好用？
                        verifyOrder();
                        startActivity(new Intent(OrderSeatActivity.this, OrderSubmitActivity.class));
                        break;
                }
            }
        });

        title.mSetButtonInfo(buttonLeft);
        title.mSetButtonInfo(buttonRight);
    }


    private void sendOrderSeatInfo() {
       /* String time = tv_timeSelect.getText().toString().trim();
        String num = edt_num.getText().toString().trim();
        String peopleNum = edt_people_num.getText().toString().trim();
        String photo = et_input_phone.getText().toString().trim();
        String des = edt_order_des.getText().toString().trim();
        myBinder.sendOrderSeatInfo(time,num,peopleNum,photo,des);*/
    }

}
