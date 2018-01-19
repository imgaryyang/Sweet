package com.lucky.sweet.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lucky.sweet.R;
import com.lucky.sweet.entity.PerdetermingEntity;
import com.lucky.sweet.service.CommunicationService;
import com.lucky.sweet.views.DishesOrderDialog;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.ToolBar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private TextView edt_num;
    private EditText et_input_phone;
    private String[] data;
    private String[][] time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderseat);

        initView();

        initEvent();

    }

    private CommunicationService.MyBinder myBinder;

    @Override
    void onServiceBind(CommunicationService.MyBinder myBinder) {
        this.myBinder = myBinder;
        myBinder.requestPerdeterming(this);
    }

    private void initView() {
        tv_timeSelect = findViewById(R.id.tv_timeSelect);
        edt_num = findViewById(R.id.edt_num);
        et_input_phone = findViewById(R.id.et_input_phone);
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

                String phone = et_input_phone.getText().toString().trim();
                boolean result = isPhoneNumber(phone);
                if (result == true) {
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
                                    goNextAnim();

                                }
                            })
                            .create();
                    dialog.show();
                } else {
                    Toast.makeText(getApplication(), "手机号不对哦，请重新输入", Toast.LENGTH_SHORT).show();
                }


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

                }
            }
        });
        title.mSetButtonInfo(buttonLeft);

    }

    public static boolean isPhoneNumber(String phoneNo) {
        if (TextUtils.isEmpty(phoneNo)) {
            return false;
        }
        if (phoneNo.length() == 11) {
            for (int i = 0; i < 11; i++) {
                if (!PhoneNumberUtils.isISODigit(phoneNo.charAt(i))) {
                    return false;
                }
            }
            Pattern p = Pattern.compile("^((13[^4,\\D])" + "|(134[^9,\\D])" +
                    "|(14[5,7])" +
                    "|(15[^4,\\D])" +
                    "|(17[3,6-8])" +
                    "|(18[0-9]))\\d{8}$");
            Matcher m = p.matcher(phoneNo);
            return m.matches();
        }
        return false;
    }


}
