package com.lucky.sweet.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucky.sweet.R;
import com.lucky.sweet.activity.MainActivity;
import com.lucky.sweet.activity.StoreDisplatActivity;
import com.lucky.sweet.adapter.AdViewPagerAdapter;
import com.lucky.sweet.adapter.RecreationViewPagerAdapter;
import com.lucky.sweet.moudel.imstore.ImStoreManager;
import com.lucky.sweet.utility.HiddenAnimUtils;
import com.lucky.sweet.viewpagerexpand.AdViewPagerTransformer;
import com.lucky.sweet.views.GradualChangeLinearLayout;
import com.lucky.sweet.views.MySearchView;
import com.lucky.sweet.widgets.ToolBar;

/**
 * Created by Qiuyue on 2017/11/15.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class ImStoreFragment extends Fragment implements View.OnClickListener {


    private ViewPager vp_ad;
    private MySearchView msv_search;
    private ViewPager vp_funne;
    private TextView tv_location;
    private TextView tv_weathertype;
    private GradualChangeLinearLayout ll_se;

    private TextView tv_moreFood;
    private ImageView imv_weather;
    private TextView tv_moreRelax;
    private ViewPager vp_foodStore;

    private Context context;



    private HiddenAnimUtils hiddenAnimUtils;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_imstore, container, false);
        context = getContext();

        initView(view);

        initData();

        initEvent();

        return view;
    }

    public void updataLocation(String location) {
        tv_location.setText(location);
    }

    public void updataWeather(String tmp,int res){
        imv_weather.setImageResource(res);
        tv_weathertype.setText(tmp);

    }

    private void initData() {
        ImStoreManager imStoreManager = new ImStoreManager(this);

        hiddenAnimUtils = HiddenAnimUtils.newInstance(getContext(), ll_se, msv_search, 1600);

        vp_ad.setPageTransformer(true, new AdViewPagerTransformer());

        vp_ad.setAdapter(new AdViewPagerAdapter(getContext(), imStoreManager.getAdInfoList()));
        vp_foodStore.setAdapter(new RecreationViewPagerAdapter(getActivity(), imStoreManager.getFoodList()));
        vp_funne.setAdapter(new RecreationViewPagerAdapter(getActivity(), imStoreManager.getFoodList()));

    }


    private void initView(View view) {


        vp_ad = view.findViewById(R.id.vp_ad);
        imv_weather = view.findViewById(R.id.imv_weather);
        tv_weathertype = view.findViewById(R.id.tv_weathertype);
        msv_search = view.findViewById(R.id.msv_search);
        tv_location = view.findViewById(R.id.tv_location);
        tv_moreFood = view.findViewById(R.id.tv_moreFood);
        tv_moreRelax = view.findViewById(R.id.tv_moreRelax);
        vp_foodStore = view.findViewById(R.id.vp_foodStore);
        vp_funne = view.findViewById(R.id.vp_funne);
        ll_se = view.findViewById(R.id.ll_se);


        vp_foodStore.setPageMargin(-100);
        vp_foodStore.setOffscreenPageLimit(3);

        vp_funne.setPageMargin(-100);
        vp_funne.setOffscreenPageLimit(3);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ToolBar toolBar = new ToolBar(getActivity());
            toolBar.setStatusBarDarkMode();
            int statusBarHeight = toolBar.getStatusBarHeight(getActivity());
            View view_margin = view.findViewById(R.id.view_margin);
            ViewGroup.LayoutParams lp;
            lp = view_margin.getLayoutParams();
            lp.height = statusBarHeight;
            view_margin.setLayoutParams(lp);
        }
    }

    private void initEvent() {
        tv_moreFood.setOnClickListener(this);
        tv_moreRelax.setOnClickListener(this);
        ll_se.setOnSearchClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hiddenAnimUtils.toggle(((MainActivity) getActivity()).getTabView());
            }
        });
        msv_search.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hiddenAnimUtils.toggle(((MainActivity) getActivity()).getTabView());

            }
        });
    }


    @Override
    public void onClick(View v) {
        System.out.println(v.getId());
        //获得父控件的对象，然后获得父控件的id
        switch (v.getId()) {
            case R.id.tv_moreFood:
                Intent intent = new Intent(getActivity(), StoreDisplatActivity.class);
                intent.putExtra("tv_moreFood", "Food");
                startActivity(intent);
                break;
            case R.id.tv_moreRelax:
                Intent intent1 = new Intent(getActivity(), StoreDisplatActivity.class);
                intent1.putExtra("tv_moreRelax", "Relax");
                startActivity(intent1);
                break;

        }
    }


}

