package com.lucky.sweet.activity;

import android.content.Context;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lucky.sweet.R;
import com.lucky.sweet.adapter.CircleListViewAdapter;
import com.lucky.sweet.entity.StoreDetailedInfo;
import com.lucky.sweet.service.CommunicationService;
import com.lucky.sweet.thread.BlurBitmapThread;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.ToolBar;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.mapsdk.raster.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private LinearLayout ll_store_part_info;
    private String mer_id;
    private ImageButton ibtn_back;
    private LinearLayout ll_null;
    private LinearLayout ll_share;
    private TextView tv_weichat;
    private TextView tv_circle;
    private TextView tv_weibo;
    private ImageButton ibtn_order_seat;
    private ImageButton ibtn_orderSingle;
    private ImageButton ibtn_orderMulti;
    private LinearLayout layout_order_content;
    private LinearLayout ll_orderbtn;

    public static void inStance(Context context, String shopId) {

        Intent intent = new Intent(context, StoreParticularInfoActivity.class);
        intent.putExtra("shopid", shopId);
        context.startActivity(intent);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeinfo);

        initView();

        initEvent();

        initTitle();

    }

    @Override
    void onServiceBind(CommunicationService.MyBinder myBinder) {
        Intent intent = getIntent();
        mer_id = intent.getStringExtra("shopid");

        myBinder.requestShopDisplay(mer_id);
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
        sv_storeInfo = findViewById(R.id.sv_storeInfo);
        sv_storeInfo.smoothScrollTo(0, 0);
        ll_store_part_info = findViewById(R.id.ll_store_part_info);
        imv_shop_one = findViewById(R.id.imv_shop_one);
        imv_shop_two = findViewById(R.id.imv_shop_two);
        imv_shop_three = findViewById(R.id.imv_shop_three);
        ibtn_back = findViewById(R.id.ibtn_back);
        ll_null = findViewById(R.id.ll_null);
        ll_share = findViewById(R.id.ll_share);
        ibtn_order_seat = findViewById(R.id.ibtn_order_seat);
        ibtn_orderSingle = findViewById(R.id.ibtn_orderSingle);
        ibtn_orderMulti = findViewById(R.id.ibtn_orderMulti);
        tv_weichat = findViewById(R.id.tv_weichat);
        tv_circle = findViewById(R.id.tv_circle);
        tv_weibo = findViewById(R.id.tv_weibo);
        layout_order_content = findViewById(R.id.layout_order_content);
        ll_orderbtn = findViewById(R.id.ll_orderbtn);


//        StarLevelIndicatorView startIndicator = findViewById(R.id.startIndicator);
//        startIndicator.initStarNumber(4);
        map = findViewById(R.id.map);
        findViewById(R.id.btn_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_order_content.setVisibility(View.VISIBLE);
                startbtnAnim();

            }
        });

        ListView lv_circle = findViewById(R.id.lv_circle);

       /* lv_circle.setAdapter(new CircleListViewAdapter(this, info.getCircle_list()));*/
        setListViewHeightBasedOnChildren(lv_circle);

        ibtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hintInputKb();
                finish();
                goPreAnim();
            }
        });
        ll_null.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelbtnAnim();

            }
        });
        ibtn_order_seat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MyApplication.sessionId.equals("")) {
                    Toast.makeText(StoreParticularInfoActivity.this, "请先登陆",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                new BlurBitmapThread(StoreParticularInfoActivity.this,
                        ll_store_part_info, 20) {
                    @Override
                    public void onBulerBitmapFinish() {
                        Intent intent = new Intent
                                (StoreParticularInfoActivity.this,
                                        OrderSeatActivity.class);
                        intent.putExtra("mer_id", mer_id);
                        startActivity(intent);

                        goNextAnim();
                    }
                }.run();
            }
        });
        ibtn_orderSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MyApplication.sessionId.equals("")) {
                    Toast.makeText(StoreParticularInfoActivity.this, "请先登陆",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(StoreParticularInfoActivity
                        .this, MerchantActivity.class);
                startActivity(intent);
                goNextAnim();
            }
        });

        ibtn_orderMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MyApplication.sessionId.equals("")) {
                    Toast.makeText(StoreParticularInfoActivity.this, "请先登陆",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                /*Intent intent = new Intent(StoreParticularInfoActivity
                        .this, MultiOrderActivity.class);

                startActivity(intent);
                goNextAnim();*/
                new AlertDialog.Builder(StoreParticularInfoActivity.this)
                        .setMessage("请选择加入方式")
                        .setPositiveButton("加入房间", (dialog, which) -> {
                            Intent intent = new Intent(StoreParticularInfoActivity.this, JoinRoomActivity.class);
                            intent.putExtra("mer_id", mer_id);
                            startActivityForResult(intent, 0);
                            goNextAnim();
                        })
                        .setNegativeButton("创建房间", (dialog, which) -> {
                            Intent intent = new Intent(StoreParticularInfoActivity.this, CreateRoomActivity.class);
                            intent.putExtra("mer_id", mer_id);
                            startActivityForResult(intent, 0);
                            goNextAnim();
                        })
                        .create()
                        .show();
            }
        });

    }

    private void initTitle() {
        ToolBar toolBar = new ToolBar(this);
        toolBar.setStatusBarDarkMode();

    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(StoreDetailedInfo entity) {
        StoreDetailedInfo.InfoBean info = entity.getInfo();

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


    private void startbtnAnim() {
        //阴影遮罩透明度
        ll_null.setBackgroundColor((Color.parseColor("#6e000000")));
        int btnNum = 4;
        final Animation[] T_Anim = new Animation[btnNum];
        for (int i = 0; i < btnNum; i++) {
            T_Anim[i] = new TranslateAnimation(0f, 0, 350f, 0f);
            T_Anim[i].setDuration(85);
            T_Anim[i].setInterpolator(new BounceInterpolator());
            T_Anim[i].setFillAfter(true);
        }
        ll_share.startAnimation(T_Anim[0]);
        T_Anim[0].setAnimationListener(new AnimationListerAdapter() {

            @Override
            public void onAnimationEnd(Animation animation) {

                ibtn_order_seat.setVisibility(View.VISIBLE);
                tv_circle.setVisibility(View.VISIBLE);
                ibtn_order_seat.startAnimation(T_Anim[1]);
                tv_circle.startAnimation(T_Anim[1]);
            }
        });

        T_Anim[1].setAnimationListener(new AnimationListerAdapter() {

            @Override
            public void onAnimationEnd(Animation animation) {

                ibtn_orderSingle.setVisibility(View.VISIBLE);
                tv_weichat.setVisibility(View.VISIBLE);
                ibtn_orderSingle.startAnimation(T_Anim[2]);
                tv_weichat.startAnimation(T_Anim[2]);
            }
        });
        T_Anim[2].setAnimationListener(new AnimationListerAdapter() {

            @Override
            public void onAnimationEnd(Animation animation) {

                ibtn_orderMulti.setVisibility(View.VISIBLE);
                tv_weibo.setVisibility(View.VISIBLE);
                ibtn_orderMulti.startAnimation(T_Anim[3]);
                tv_weibo.startAnimation(T_Anim[3]);
            }
        });

    }

    private void cancelbtnAnim() {
        ll_null.setBackgroundColor((Color.parseColor("#00000000")));
        int btnNum = 4;
        final Animation[] T_Anim = new TranslateAnimation[btnNum];
        for (int i = 0; i < btnNum; i++) {
            T_Anim[i] = new TranslateAnimation(0f, 0, 0f, 350f);
            T_Anim[i].setDuration(80);
        }

        ibtn_order_seat.startAnimation(T_Anim[0]);
        T_Anim[0].setAnimationListener(new AnimationListerAdapter() {

            @Override
            public void onAnimationEnd(Animation animation) {
                ibtn_order_seat.setVisibility(View.GONE);
                tv_circle.setVisibility(View.GONE);
                ibtn_order_seat.clearAnimation();
                tv_circle.clearAnimation();
                ibtn_orderSingle.startAnimation(T_Anim[1]);
                tv_circle.startAnimation(T_Anim[1]);
            }
        });

        T_Anim[1].setAnimationListener(new AnimationListerAdapter() {

            @Override
            public void onAnimationEnd(Animation animation) {
                ibtn_orderSingle.setVisibility(View.GONE);
                tv_weichat.setVisibility(View.GONE);
                ibtn_orderSingle.clearAnimation();
                tv_weichat.clearAnimation();
                ibtn_orderMulti.startAnimation(T_Anim[2]);
                tv_weibo.startAnimation(T_Anim[2]);

            }
        });
        T_Anim[2].setAnimationListener(new AnimationListerAdapter() {

            @Override
            public void onAnimationEnd(Animation animation) {
                ibtn_orderMulti.setVisibility(View.GONE);
                tv_weibo.setVisibility(View.GONE);
                ibtn_orderMulti.clearAnimation();
                tv_weibo.clearAnimation();
                ll_share.startAnimation(T_Anim[3]);
                layout_order_content.setVisibility(View.GONE);

            }
        });

    }

    private abstract class AnimationListerAdapter implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public abstract void onAnimationEnd(Animation animation);

    }


}
