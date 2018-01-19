package com.lucky.sweet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.lucky.sweet.R;
import com.lucky.sweet.entity.ShopCarSingleInformation;
import com.lucky.sweet.model.shoppingcar.fragment.ProductsFragment;
import com.lucky.sweet.model.shoppingcar.mode.ProductType;
import com.lucky.sweet.model.shoppingcar.mode.ShopProduct;
import com.lucky.sweet.widgets.ToolBar;

import java.util.ArrayList;

/**
 * Created by Qiuyue on 2018/1/3.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class MerchantActivity extends FragmentActivity {


    private ProductsFragment fg_shop_car;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant);

        initToolBar();

        initView();

        initData();

        initEvent();

    }


    private ArrayList<ProductType> getShopCarInfo() {
        ArrayList<ProductType> productCategorizes = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            ProductType productCategorize = new ProductType();
            productCategorize.setType("分类信息" + i);
            ArrayList shopProductsAll = new ArrayList<>();
            for (int j = 1; j < 6; j++) {
                ShopProduct product = new ShopProduct();
                product.setId(154788 + i + j);
                product.setGoods("衬衫" + i);
                product.setPrice(18 + "");
                shopProductsAll.add(product);
            }
            productCategorize.setProduct(shopProductsAll);
            productCategorizes.add(productCategorize);
        }
        return productCategorizes;
    }

    private void initData() {
        fg_shop_car.initData(getShopCarInfo());
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
            }
        });


    }

    private void initToolBar() {
        ToolBar toolBar = new ToolBar(this);
        toolBar.setStatusBarDarkMode();

    }

    private void initView() {

        fg_shop_car = (ProductsFragment) getSupportFragmentManager().findFragmentById(R.id.fg_shop_car);

    }


}
