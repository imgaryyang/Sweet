package com.lucky.sweet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lucky.sweet.R;
import com.lucky.sweet.entity.JoinInRoomMenu;
import com.lucky.sweet.entity.JoinRoomInfo;
import com.lucky.sweet.entity.MuliiOrderInfo;
import com.lucky.sweet.entity.ShopCarEntity;
import com.lucky.sweet.entity.ShopCarSingleInformation;
import com.lucky.sweet.model.shoppingcar.fragment.ProductsFragment;
import com.lucky.sweet.model.shoppingcar.mode.ProductType;
import com.lucky.sweet.model.shoppingcar.mode.ShopProduct;
import com.lucky.sweet.service.CommunicationService;
import com.lucky.sweet.widgets.ToolBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qiuyue on 2018/1/3.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class MerchantActivity extends BaseActivity {


    private ProductsFragment fg_shop_car;
    private Button btn_back;
    private CommunicationService.MyBinder myBinder;
    private JoinInRoomMenu info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant);

        Serializable menu = getIntent().getSerializableExtra("menu");
        if (menu != null && menu instanceof JoinInRoomMenu) {
            info = (JoinInRoomMenu) menu;

        }
        initToolBar();

        initView();

        initEvent();

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

    @Override
    void onServiceBind(CommunicationService.MyBinder myBinder) {

        myBinder.shopCarRequest(getIntent().getStringExtra("mer_id"));
        this.myBinder = myBinder;
    }


    private void initEvent() {
        fg_shop_car.setOnClickListener(new ProductsFragment.OnClickListener() {
            @Override
            public void onClickSubimt(ShopCarSingleInformation shopCarSingleInformation) {
                Intent intent = new Intent(MerchantActivity.this,
                        OrderSubmitActivity.class);
                intent.putExtra("data", shopCarSingleInformation);
                startActivity(intent);
                overridePendingTransition(R.anim.act_left_in, R.anim.act_left_out);
            }

            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MerchantActivity.this,
                        DischesViewShowActivity.class);
                intent.putExtra("POSITON", position);
                startActivity(intent);
                overridePendingTransition(R.anim.act_left_in, R.anim.act_left_out);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                goPreAnim();
            }
        });

    }

    private void initToolBar() {
        ToolBar toolBar = new ToolBar(this);
        toolBar.setStatusBarDarkMode();

    }

    private void initView() {

        fg_shop_car = (ProductsFragment) getSupportFragmentManager().findFragmentById(R.id.fg_shop_car);
        fg_shop_car.setCurrenType(getIntent().getBooleanExtra("multiOrder", false));
        btn_back = findViewById(R.id.btn_back);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(ShopCarEntity entity) {
        if (info != null) {
            List<ShopCarEntity.TrolleyInfoBean> trolley_info = entity.getTrolley_info();

                    }
        ArrayList<ProductType> productCategorizes = new ArrayList<>();

        for (ShopCarEntity.TrolleyInfoBean entiy : entity.getTrolley_info()) {
            ProductType productCategorize = new ProductType();
            productCategorize.setType(entiy.getName());
            ArrayList shopProductsAll = new ArrayList<>();

            for (ShopCarEntity.TrolleyInfoBean.ItemBean item : entiy.getItem()) {
                ShopProduct product = new ShopProduct();
                product.setId(Integer.parseInt(item.getItem_id()));
                product.setGoods(item.getName());
                product.setPrice(item.getUnivalence());
                product.setPicture(item.getPhoto());
                shopProductsAll.add(product);
            }
            productCategorize.setProduct(shopProductsAll);
            productCategorizes.add(productCategorize);

        }

        fg_shop_car.initData(productCategorizes);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(JoinRoomInfo joinRoomInfo) {

        String name = joinRoomInfo.getName();
        if (!name.equals(MyApplication.USER_ID))
            Toast.makeText(this, "感谢这位老铁：" + name + "加入房间", Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MuliiOrderInfo info) {

        fg_shop_car.multiOrderUpdata(info);

    }

    public void upMenuInfo(MuliiOrderInfo muliiOrderInfo) {

        String num;
        if (muliiOrderInfo.isaddDis()) {
            num = "1";
        } else {
            num = "-1";
        }

        myBinder.dishesMenuUpdata(getIntent().getStringExtra("room_id"), muliiOrderInfo.getItem_id() + "", num, muliiOrderInfo.toString());

    }
}
