package com.lucky.sweet.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucky.sweet.R;
import com.lucky.sweet.activity.MainSearchActiviy;
import com.lucky.sweet.activity.StoreDisplatActivity;
import com.lucky.sweet.adapter.AdViewPagerAdapter;
import com.lucky.sweet.adapter.RecFoodRecommendAdapter;
import com.lucky.sweet.adapter.RecminiAdAdapter;
import com.lucky.sweet.entity.StoreShowInfo;
import com.lucky.sweet.moudel.imstore.ImStoreManager;
import com.lucky.sweet.utility.HiddenAnimUtils;
import com.lucky.sweet.views.GradualChangeLinearLayout;
import com.lucky.sweet.views.MySearchView;
import com.lucky.sweet.viewsexpand.AdRecyCleViewOnScrollState;
import com.lucky.sweet.viewsexpand.AdViewPagerTransformer;
import com.lucky.sweet.widgets.ToolBar;

import java.util.List;

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

    private TextView tv_location;
    private TextView tv_weathertype;
    private GradualChangeLinearLayout ll_se;

    private TextView tv_moreFood;
    private ImageView imv_weather;
    private TextView tv_moreRelax;
    private RecyclerView rec_ad_show;
    private RecyclerView rec_foodStore;
    private RecyclerView rec_funStore;


    private Context context;
    private HiddenAnimUtils hiddenAnimUtils;
    private List<StoreShowInfo.foodBean> showInfo;
    private Button btn_qrcodescan;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

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

    public void updataWeather(String tmp, int res) {
        imv_weather.setImageResource(res);
        tv_weathertype.setText(tmp);

    }

    private void initData() {

        ImStoreManager imStoreManager = new ImStoreManager(this);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        hiddenAnimUtils = HiddenAnimUtils.newInstance(getContext(), ll_se,
                msv_search, displayMetrics.heightPixels);

        vp_ad.setPageTransformer(true, new AdViewPagerTransformer());

        vp_ad.setAdapter(new AdViewPagerAdapter(getContext(), imStoreManager.getAdInfoList()));


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rec_ad_show.setLayoutManager(linearLayoutManager);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rec_foodStore.setLayoutManager(linearLayoutManager);


        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rec_funStore.setLayoutManager(linearLayoutManager);


        rec_ad_show.setAdapter(new RecminiAdAdapter(context));
        rec_foodStore.setAdapter(new RecFoodRecommendAdapter(context,
                RecFoodRecommendAdapter.FOOD));
        rec_funStore.setAdapter(new RecFoodRecommendAdapter(context,
                RecFoodRecommendAdapter.FUN));

        rec_foodStore.addOnScrollListener(new AdRecyCleViewOnScrollState
                (context, AdRecyCleViewOnScrollState.FOOD));

        rec_funStore.addOnScrollListener(new AdRecyCleViewOnScrollState
                (context, AdRecyCleViewOnScrollState.FUN));

    }


    private void initView(View view) {

        vp_ad = view.findViewById(R.id.vp_ad);
        imv_weather = view.findViewById(R.id.imv_weather);
        tv_weathertype = view.findViewById(R.id.tv_weathertype);
        msv_search = view.findViewById(R.id.msv_search);
        tv_location = view.findViewById(R.id.tv_location);
        tv_moreFood = view.findViewById(R.id.tv_moreFood);
        tv_moreRelax = view.findViewById(R.id.tv_moreRelax);
        rec_foodStore = view.findViewById(R.id.rec_foodStore);
        rec_funStore = view.findViewById(R.id.rec_funStore);
        rec_ad_show = view.findViewById(R.id.rec_ad_show);
        btn_qrcodescan = view.findViewById(R.id.btn_qrcodescan);

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
        btn_qrcodescan.setOnClickListener(this);
        msv_search.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainSearchActiviy.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View v) {
        //获得父控件的对象，然后获得父控件的id
        Intent intent;
        switch (v.getId()) {
            case R.id.tv_moreFood:
                intent = new Intent(getActivity(), StoreDisplatActivity.class);
                intent.putExtra("tv_moreFood", "Food");
                startActivity(intent);
                break;
            case R.id.tv_moreRelax:
                intent = new Intent(getActivity(), StoreDisplatActivity.class);
                intent.putExtra("tv_moreRelax", "Relax");
                startActivity(intent);
                break;

        }

    }


    public void setShowInfo(List<StoreShowInfo.foodBean> showInfo) {
       /* vp_foodStore.setAdapter(new RecreationViewPagerAdapter(getContext(), showInfo));*/
    }
}

