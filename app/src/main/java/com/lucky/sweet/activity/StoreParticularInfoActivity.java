package com.lucky.sweet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lucky.sweet.R;
import com.lucky.sweet.adapter.CircleListViewAdapter;
import com.lucky.sweet.entity.StoreDetailedInfo;
import com.lucky.sweet.service.CommunicationService;
import com.lucky.sweet.views.StarLevelIndicatorView;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.ToolBar;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.mapsdk.raster.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c on 2017/12/13.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class StoreParticularInfoActivity extends BaseActivity {

    private Title title = null;

    private ImageView imv_back;
    private ImageView imv_show_fir;
    private ImageView imv_show_sec;
    private ImageView imv_show_sed;

    private ImageView imv_shop_one;
    private ImageView imv_shop_two;
    private ImageView imv_shop_three;

    private TextView tv_shop_title;
    private TextView tv_shop_int;
    private TextView tv_shop_worktime;
    private TextView tv_shop_des;
    private MapView map;
    private Button btn_map_position;
    private TencentMap maps;
    private LatLng latLng;
    private TextView tv_moreFood;
    private TextView tv_moreevr;
    private ScrollView sv_storeInfo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeinfo);

        initView();

        initEvent();

        initTitle();

        /*ParticularInfoManager particularInfoManager = new ParticularInfoManager(this);*/
    }

    @Override
    void onServiceBind(CommunicationService.MyBinder myBinder) {
        Intent intent = getIntent();
        String mer_id = intent.getStringExtra("shopid");
        Toast.makeText(this, "点击店铺ID：" + mer_id, Toast.LENGTH_SHORT).show();
        myBinder.requestShopDisplay(this, mer_id);
    }


    private void initEvent() {
        btn_map_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maps.setCenter(latLng);
            }
        });
        tv_moreFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoreParticularInfoActivity.this,
                        RecruitmentDisplayActivity.class);
                startActivity(intent);
                goUpAnim();
            }
        });
        tv_moreevr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoreParticularInfoActivity.this,
                        RecruitmentDisplayActivity.class);
                startActivity(intent);
                goUpAnim();
            }
        });
    }

    public void upData(StoreDetailedInfo info) {
        initShopDesInfo(info.getInfo());
    }

    private void initShopDesInfo(StoreDetailedInfo.InfoBean info) {

        Glide.with(this).load(info.getSurface()).into(imv_back);

        List<String> environment = info.getEnvironment();

        Glide.with(this).load(environment.get(0)).into
                (imv_show_fir);
        Glide.with(this).load(environment.get(1)).into
                (imv_show_sec);
        Glide.with(this).load(environment.get(2)).into
                (imv_show_sed);
        List<String> recommend = info.getRecommend();
        Glide.with(this).load(recommend.get(0)).into
                (imv_shop_one);
        Glide.with(this).load(recommend.get(1)).into
                (imv_shop_two);
        Glide.with(this).load(recommend.get(2)).into
                (imv_shop_three);


        StoreDetailedInfo.InfoBean.ShopdesBean shopdes = info.getShopdes();

        tv_shop_int.setText(shopdes.getClassify());
        tv_shop_title.setText(shopdes.getName());
        tv_shop_des.setText(shopdes.getIntroduce());
        tv_shop_worktime.setText(shopdes.getBusiness_hours());

        latLng = new LatLng(Double.valueOf(shopdes.getLatitude()),
                Double.valueOf(shopdes.getLongitude()));
        maps = map.getMap();

        maps.setCenter(latLng);
        maps.setZoom(75);
        Marker marker = this.map.addMarker(new MarkerOptions().title(shopdes.getName()).anchor(0.5f, 0.5f).position(latLng));
        marker.showInfoWindow();
    }


    private void initView() {
        imv_back = findViewById(R.id.imv_back);
        imv_show_fir = findViewById(R.id.imv_show_fir);
        tv_moreFood = findViewById(R.id.tv_moreFood);
        tv_moreevr = findViewById(R.id.tv_moreevr);
        imv_show_sec = findViewById(R.id.imv_show_sec);
        imv_show_sed = findViewById(R.id.imv_show_sed);
        tv_shop_title = findViewById(R.id.tv_shop_title);
        tv_shop_int = findViewById(R.id.tv_shop_int);
        tv_shop_worktime = findViewById(R.id.tv_shop_worktime);
        tv_shop_des = findViewById(R.id.tv_shop_des);
        btn_map_position = findViewById(R.id.btn_map_position);
        sv_storeInfo = (ScrollView) findViewById(R.id.sv_storeInfo);
        sv_storeInfo.smoothScrollTo(0, 0);

        imv_shop_one = findViewById(R.id.imv_shop_one);
        imv_shop_two = findViewById(R.id.imv_shop_two);
        imv_shop_three = findViewById(R.id.imv_shop_three);

        StarLevelIndicatorView startIndicator = findViewById(R.id.startIndicator);
        startIndicator.initStarNumber(4);
        map = findViewById(R.id.map);
        findViewById(R.id.btn_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StoreParticularInfoActivity.this,
                        OrderSeatActivity.class));
                goNextAnim();
            }
        });

        ListView lv_circle = (ListView) findViewById(R.id.lv_circle);
        ArrayList<String> objects = new ArrayList<String>();
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        lv_circle.setAdapter(new CircleListViewAdapter(objects, this));
        setListViewHeightBasedOnChildren(lv_circle);
    }

    private void initTitle() {
        ToolBar toolBar = new ToolBar(this);
        toolBar.setColorNewBar(getResources().getColor(R.color.white), 0);
        title = (Title) findViewById(R.id.title);
        title.setTitleNameStr("");
        Title.ButtonInfo buttonLeft = new Title.ButtonInfo(true, Title
                .BUTTON_LEFT, R.drawable.selector_btn_titleback, null);
        Title.ButtonInfo buttonRight = new Title.ButtonInfo(true, Title
                .BUTTON_RIGHT1, R.mipmap.circle_star, null);
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
        title.mSetButtonInfo(buttonRight);

    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        //获得adapter
        CircleListViewAdapter adapter = (CircleListViewAdapter) listView.getAdapter();
        if (adapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(0, 0);
            //计算总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        //计算分割线高度
        params.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        //给listview设置高度
        listView.setLayoutParams(params);
    }


}
