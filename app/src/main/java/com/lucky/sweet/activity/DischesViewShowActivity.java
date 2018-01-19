package com.lucky.sweet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.lucky.sweet.R;
import com.lucky.sweet.model.wholedishes.adapter.TravelViewPagerAdapter;
import com.lucky.sweet.model.wholedishes.lib.ExpandingPagerFactory;
import com.lucky.sweet.model.wholedishes.lib.fragments.ExpandingFragment;
import com.lucky.sweet.model.wholedishes.model.Travel;
import com.lucky.sweet.service.CommunicationService;

import java.util.ArrayList;
import java.util.List;

public class DischesViewShowActivity extends BaseActivity implements
        ExpandingFragment.OnExpandingClickListener {

    private Button btn_cancle;
    private ViewPager vp_dishes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disches_view_show);

        initView();

        initData();

        initEvent();
    }

    @Override
    void onServiceBind(CommunicationService.MyBinder myBinder) {

    }

    private void initEvent() {
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

    private List<Travel> generateTravelList() {

        List<Travel> travels = new ArrayList<>();
        for (int i = 0; i < 20; ++i) {
            travels.add(new Travel("Seychelles", R.mipmap.test_product));
        }

        return travels;
    }

    private void initData() {
        TravelViewPagerAdapter adapter = new TravelViewPagerAdapter(getSupportFragmentManager());
        adapter.addAll(generateTravelList());
        Intent intent = getIntent();

        int positon = intent.getIntExtra("POSITON", 0);

        ExpandingPagerFactory.setupViewPager(vp_dishes);
        vp_dishes.setAdapter(adapter);
        vp_dishes.setCurrentItem(positon);

    }

    @Override
    public void onBackPressed() {
        if (!ExpandingPagerFactory.onBackPressed(vp_dishes)) {
            super.onBackPressed();
        }
    }


    private void initView() {

        vp_dishes = findViewById(R.id.vp_dishes);
        btn_cancle = findViewById(R.id.btn_cancle);

    }

    protected boolean enableSliding() {
        return false;
    }

    @Override
    public void onExpandingClick(View v) {
        startActivity(new Intent(this, DetailedDishesActivity.class));
        overridePendingTransition(R.anim.act_left_in, R.anim.act_left_out);
    }
}
