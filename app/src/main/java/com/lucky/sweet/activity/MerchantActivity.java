package com.lucky.sweet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lucky.sweet.R;
import com.lucky.sweet.entity.ShopCarSingleInformation;
import com.lucky.sweet.model.shoppingcar.fragment.ProductsFragment;
import com.lucky.sweet.model.shoppingcar.mode.ProductType;
import com.lucky.sweet.model.shoppingcar.mode.ShopProduct;
import com.lucky.sweet.model.wholedishes.adapter.TravelViewPagerAdapter;
import com.lucky.sweet.model.wholedishes.lib.ExpandingPagerFactory;
import com.lucky.sweet.model.wholedishes.lib.fragments.ExpandingFragment;
import com.lucky.sweet.model.wholedishes.model.Travel;
import com.lucky.sweet.views.SlidingLayoutView;
import com.lucky.sweet.widgets.ToolBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qiuyue on 2018/1/3.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class MerchantActivity extends FragmentActivity implements ExpandingFragment.OnExpandingClickListener {


    private ProductsFragment fg_shop_car;
    private ViewPager vp_dishes;
    private View vv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant);

        initToolBar();

        initView();

        initData();

        initEvent();

    }

    private List<Travel> generateTravelList(ArrayList<ProductType> data) {
        int count = 0;
        for (ProductType list : data) {
            List<ShopProduct> product = list.getProduct();
            for (int i = 0; i < product.size(); i++) {
                count++;
            }

        }
        List<Travel> travels = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            travels.add(new Travel("Seychelles", R.mipmap.test_product));
  /*          travels.add(new Travel("Shang Hai", R.mipmap.test_product));
            travels.add(new Travel("New York", R.mipmap.test_product));
            travels.add(new Travel("castle", R.mipmap.test_product));*/
        }
        System.out.println("Seychelles：" + travels.size());
        return travels;
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
        TravelViewPagerAdapter adapter = new TravelViewPagerAdapter(getSupportFragmentManager());
        fg_shop_car.initData(getShopCarInfo());
        adapter.addAll(generateTravelList(getShopCarInfo()));
        vp_dishes.setAdapter(adapter);
        ExpandingPagerFactory.setupViewPager(vp_dishes);
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
                vp_dishes.setCurrentItem(position,false);
                if (vv_back.getVisibility() == View.GONE) {
                    vp_dishes.setVisibility(View.VISIBLE);
                    vv_back.setVisibility(View.VISIBLE);
                }

            }
        });
        vv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vv_back.getVisibility() == View.VISIBLE) {
                    vv_back.setVisibility(View.GONE);
                    vp_dishes.setVisibility(View.GONE);
                }
            }
        });
        vp_dishes.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ExpandingFragment expandingFragment = ExpandingPagerFactory.getCurrentFragment(vp_dishes);
                if (expandingFragment != null && expandingFragment.isOpenend()) {
                    expandingFragment.close();
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initToolBar() {
        ToolBar toolBar = new ToolBar(this);
        toolBar.setColorNewBar(getResources().getColor(R.color.white), 0);
        SlidingLayoutView rootView = new SlidingLayoutView(this);
        rootView.bindActivity(this);
    }

    private void initView() {
        vv_back = findViewById(R.id.vv_back);
        vp_dishes = findViewById(R.id.vp_dishes);
        fg_shop_car = (ProductsFragment) getSupportFragmentManager().findFragmentById(R.id.fg_shop_car);

    }


    @Override
    public void onExpandingClick(View v) {

    }


}
