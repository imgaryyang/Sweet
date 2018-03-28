package com.lucky.sweet.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lucky.sweet.R;
import com.lucky.sweet.entity.InternetTime;
import com.lucky.sweet.entity.ShopCarSingleInformation;
import com.lucky.sweet.model.shoppingcar.mode.ShopProduct;
import com.lucky.sweet.properties.ServiceProperties;
import com.lucky.sweet.service.CommunicationService;
import com.lucky.sweet.utility.HttpUtils;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.ToolBar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import io.reactivex.annotations.Nullable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class OrderSubmitActivity extends BaseActivity {

    private LinearLayout ll_order_list;
    private TextView tv_cost_num;
    private TextView tv_now_time;
    private TextView tv_shop_name;
    private TextView tv_seat_num;
    private Title title;
    private static String SHOP_CAR_INFO = "carInfo";
    private static Boolean MORE_PEOPLE = false;

    public static void newInstance(Activity activity, @Nullable ShopCarSingleInformation shopCarSingleInformation) {
        Intent intent = new Intent(activity, OrderSubmitActivity.class);
        intent.putExtra(SHOP_CAR_INFO, shopCarSingleInformation);
        activity.startActivity(intent);
        MORE_PEOPLE = false;
        activity.overridePendingTransition(R.anim.act_left_in, R.anim.act_left_out);
    }

    public static void newInstance(Activity activity, String roomId) {
        Intent intent = new Intent(activity, OrderSubmitActivity.class);
        intent.putExtra(SHOP_CAR_INFO, roomId);
        activity.startActivity(intent);
        MORE_PEOPLE = true;
        activity.overridePendingTransition(R.anim.act_left_in, R.anim.act_left_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_submit);
        ToolBar toolBar = new ToolBar(this);
        toolBar.setColorNewBar(getResources().getColor(R.color.white), 0);

        setIsBindEventBus();
        initView();

        initData();

        initEvent();

    }

    @Override
    void onServiceBind(CommunicationService.MyBinder myBinder) {
        myBinder.getCurrentIntentTime();
        if (MORE_PEOPLE) {
            myBinder.getMultShopCarInfo(getIntent().getStringExtra(SHOP_CAR_INFO));
        }
    }

    private void initEvent() {
        findViewById(R.id.btn_submit_order).setOnClickListener(v -> {
            startActivity(new Intent(OrderSubmitActivity.this,
                    SettlementActivity.class));
            goFadeAnim();
        });
    }


    private void initData() {

        /*Title.ButtonInfo buttonLeft = new Title.ButtonInfo(true, Title
                .BUTTON_LEFT,R.drawable.selector_btn_titleback, null);*/
        /*title.setOnTitleButtonClickListener(new Title
                .OnTitleButtonClickListener() {
            @Override
            public void onClick(int id, Title.ButtonViewHolder viewHolder) {
                switch (id) {
                    case Title.BUTTON_LEFT:
                        Intent intent1=new Intent(OrderSubmitActivity.this,MainActivity.class);
                        startActivity(intent1);
                        goFadeAnim();
                        break;
                }
            }
        });*/
        Intent intent = getIntent();
        if (!MORE_PEOPLE) {

            try {
                ShopCarSingleInformation data = (ShopCarSingleInformation) intent.getSerializableExtra(SHOP_CAR_INFO);
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("saleSum", data.getSaleSum());
                jsonObject.put("shopName", data.getShopName());
                jsonObject.put("seatNum", data.getSeatNum());
                JSONArray jsonArray = new JSONArray();
                if (data != null) {
                    final Title.ButtonInfo buttonRigt = new Title.ButtonInfo(true, Title
                            .BUTTON_RIGHT1, R.mipmap.ic_share, null);
                    title.setTitleNameStr("确认订单");
                    title.mSetButtonInfo(buttonRigt);
                    tv_seat_num.setText(data.getSeatNum());
                    tv_shop_name.setText(data.getShopName());
                    tv_cost_num.setText(data.getSaleSum());

                    List<ShopProduct> productList = data.getProductList();
                    for (int i = 0; i < productList.size(); i++) {

                        jsonArray.put(productList);
                        View inflate = View.inflate(this, R.layout.item_orderlist, null);
                        TextView tv_dishes_num = inflate.findViewById(R.id.tv_dishes_num);
                        TextView tv_dishes_price = inflate.findViewById(R.id.tv_dishes_price);
                        TextView tv_dishes_name = inflate.findViewById(R.id.tv_dishes_name);
                        int number = productList.get(i).getNumber();
                        int salePrice = Integer.parseInt(productList.get(i).getPrice()) *
                                number;
                        tv_dishes_name.setText(productList.get(i).getGoods());
                        tv_dishes_price.setText("  " + salePrice);
                        tv_dishes_num.setText("  " + number);
                        ll_order_list.addView(inflate);
                    }
                    jsonObject.put("productList",jsonArray);
                    System.out.println(jsonObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }

    private void initView() {
        tv_seat_num = findViewById(R.id.tv_seat_num);
        tv_shop_name = findViewById(R.id.tv_shop_name);
        tv_now_time = findViewById(R.id.tv_now_time);
        tv_cost_num = findViewById(R.id.tv_cost_num);

        ll_order_list = findViewById(R.id.ll_order_list);
        title = findViewById(R.id.title);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(InternetTime info) {
        String sysTimeFormatTwo = info.getSysTimeFormatTwo();
        sysTimeFormatTwo = sysTimeFormatTwo.substring(0,
                sysTimeFormatTwo.length() - 3);
        sysTimeFormatTwo = sysTimeFormatTwo.replace
                ("-", ".");
        tv_now_time.setText(sysTimeFormatTwo);
    }

}
