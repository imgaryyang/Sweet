package com.lucky.sweet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lucky.sweet.R;
import com.lucky.sweet.adapter.CircleListViewAdapter;
import com.lucky.sweet.entity.StoreDetailedInfo;
import com.lucky.sweet.moudel.particularinfo.ParticularInfoManager;

import java.util.ArrayList;

/**
 * Created by c on 2017/12/13.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class StoreParticularInfoActivity extends BaseActivity{

    private ImageView imv_back;
    private ImageView imv_show_fir;
    private ImageView imv_show_sec;
    private ImageView imv_show_sed;
    private TextView tv_shop_title;
    private TextView tv_shop_int;
    private TextView tv_shop_worktime;
    private TextView tv_shop_des;
    private ListView lv_circle;
    private Button btn_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeinfo);

        initView();

        initData();

        ParticularInfoManager particularInfoManager = new ParticularInfoManager(this);
    }

    private void initData() {
        Intent intent = getIntent();
        int shopid = intent.getIntExtra("shopid", 0);
        Toast.makeText(this, "点击店铺ID：" + shopid, Toast.LENGTH_SHORT).show();
        ArrayList<String> objects = new ArrayList<String>();
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        lv_circle.setAdapter(new CircleListViewAdapter(objects,StoreParticularInfoActivity.this));
    }

    public void upData(StoreDetailedInfo.MerinfoBean info) {

        Glide.with(this).load(info.getSurface()).into(imv_back);
        Glide.with(this).load(info.getThumbnail_one()).into(imv_show_fir);
        Glide.with(this).load(info.getThumbnail_two()).into(imv_show_sec);
        Glide.with(this).load(info.getThumbnail_three()).into(imv_show_sed);
        tv_shop_title.setText(info .getName());
        tv_shop_int.setText(info.getClassify());
        tv_shop_worktime.setText(info.getBusiness_hours());
        tv_shop_des.setText(info.getIntroduce());

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
        lv_circle = (ListView)findViewById(R.id.lv_circle);
        btn_order = (Button)findViewById(R.id.btn_order);
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StoreParticularInfoActivity.this,OrderSeatActivity.class);
                startActivity(intent);
            }
        });
    }




}
