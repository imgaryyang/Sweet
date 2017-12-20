package com.lucky.sweet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lucky.sweet.R;
import com.lucky.sweet.adapter.CircleListViewAdapter;
import com.lucky.sweet.entity.StoreDetailedInfo;
import com.lucky.sweet.moudel.particularinfo.ParticularInfoManager;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.mapsdk.raster.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.ToolBar;

import java.util.ArrayList;

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
    private TextView tv_shop_title;
    private TextView tv_shop_int;
    private TextView tv_shop_worktime;
    private TextView tv_shop_des;
    private MapView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeinfo);

        initView();

        initData();

        initTitle();

        ParticularInfoManager particularInfoManager = new ParticularInfoManager(this);
    }

    private void initData() {
        Intent intent = getIntent();
        int shopid = intent.getIntExtra("shopid", 0);
        Toast.makeText(this, "点击店铺ID：" + shopid, Toast.LENGTH_SHORT).show();
    }

    public void upData(StoreDetailedInfo.MerinfoBean info) {

        Glide.with(this).load(info.getSurface()).into(imv_back);
        Glide.with(this).load(info.getThumbnail_one()).into(imv_show_fir);
        Glide.with(this).load(info.getThumbnail_two()).into(imv_show_sec);
        Glide.with(this).load(info.getThumbnail_three()).into(imv_show_sed);
        tv_shop_title.setText(info.getName());
        tv_shop_int.setText(info.getClassify());
        tv_shop_worktime.setText(info.getBusiness_hours());
        tv_shop_des.setText(info.getIntroduce());
        LatLng latLng = new LatLng(Double.valueOf(info.getLat()),
                Double.valueOf(info.getLon()));
        TencentMap maps = map.getMap();
        maps.setCenter(latLng);
        Marker marker = this.map.addMarker(new MarkerOptions().title(info.getName()).anchor(0.5f,
                0.5f).position(latLng));
        marker.showInfoWindow();
    }


    private void initView() {
        imv_back = findViewById(R.id.imv_back);
        imv_show_fir = findViewById(R.id.imv_show_fir);
        imv_show_sec = findViewById(R.id.imv_show_sec);
        imv_show_sed = findViewById(R.id.imv_show_sed);
        tv_shop_title = findViewById(R.id.tv_shop_title);
        tv_shop_int = findViewById(R.id.tv_shop_int);
        tv_shop_worktime = findViewById(R.id.tv_shop_worktime);
        tv_shop_des = findViewById(R.id.tv_shop_des);
        map = findViewById(R.id.map);
        findViewById(R.id.btn_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StoreParticularInfoActivity.this,
                        OrderSeatActivity.class));
            }
        });

        ListView lv_circle = (ListView)findViewById(R.id.lv_circle);
        ArrayList<String> objects = new ArrayList<String>();
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        lv_circle.setAdapter(new CircleListViewAdapter(objects,this));
        setListViewHeightBasedOnChildren(lv_circle);
    }

    private void initTitle() {
        ToolBar toolBar = new ToolBar(this);
        toolBar.setColorNewBar(getResources().getColor(R.color.white), 0);
        title = (Title) findViewById(R.id.title);
        title.setTitleNameStr(" ");
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
