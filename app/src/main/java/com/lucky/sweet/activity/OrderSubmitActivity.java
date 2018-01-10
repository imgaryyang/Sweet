package com.lucky.sweet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lucky.sweet.R;
import com.lucky.sweet.entity.InternetTime;
import com.lucky.sweet.entity.ShopCarSingleInformation;
import com.lucky.sweet.model.shoppingcar.mode.ShopProduct;
import com.lucky.sweet.properties.Properties;
import com.lucky.sweet.utility.HttpUtils;
import com.lucky.sweet.widgets.Title;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class OrderSubmitActivity extends AppCompatActivity {

    private LinearLayout ll_order_list;
    private TextView tv_cost_num;
    private TextView tv_now_time;
    private TextView tv_shop_name;
    private TextView tv_seat_num;
    private Title title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_submit);


        initView();

        initData();

        initEvent();

    }

    private void initEvent() {
        findViewById(R.id.btn_submit_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(OrderSubmitActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        ShopCarSingleInformation data = (ShopCarSingleInformation) intent.getSerializableExtra("data");


        final Title.ButtonInfo buttonRigt = new Title.ButtonInfo(true, Title
                .BUTTON_RIGHT1, R.mipmap.share, null);
        title.setTitleNameStr("确认订单");
        title.mSetButtonInfo(buttonRigt);

        tv_seat_num.setText(data.getSeatNum());
        tv_shop_name.setText(data.getShopName());
        tv_cost_num.setText(data.getSaleSum());

        HttpUtils.sendOkHttpRequest(Properties.TIMECHECK, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Gson gson = new Gson();
                            InternetTime internetTime = gson.fromJson(response.body().string(),
                                    InternetTime.class);
                            String sysTimeFormatTwo = internetTime.getSysTimeFormatTwo();
                            sysTimeFormatTwo = sysTimeFormatTwo.substring(0,
                                    sysTimeFormatTwo.length() - 3);
                            sysTimeFormatTwo = sysTimeFormatTwo.replace
                                    ("-", ".");
                            tv_now_time.setText(sysTimeFormatTwo);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        List<ShopProduct> productList = data.getProductList();
        for (int i = 0; i < productList.size(); i++) {
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
    }

    private void initView() {
        tv_seat_num = findViewById(R.id.tv_seat_num);
        tv_shop_name = findViewById(R.id.tv_shop_name);
        tv_now_time = findViewById(R.id.tv_now_time);
        tv_cost_num = findViewById(R.id.tv_cost_num);
        ll_order_list = findViewById(R.id.ll_order_list);
        title = findViewById(R.id.title);


    }

}
