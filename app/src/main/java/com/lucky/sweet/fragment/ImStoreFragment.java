package com.lucky.sweet.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucky.sweet.R;
import com.lucky.sweet.adapter.AdViewPager;
import com.lucky.sweet.moudel.ImStoreFragmentManager.ImStoreManager;
import com.lucky.sweet.widgets.ToolBar;

/**
 * Created by Qiuyue on 2017/11/15.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class ImStoreFragment extends Fragment {


    private ViewPager vp_ad;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_imstore, container, false);

        initView(view);

        initData();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ToolBar toolBar = new ToolBar(getActivity());
            toolBar.setStatusBarDarkMode();
            int statusBarHeight = toolBar.getStatusBarHeight(getActivity());
            View view_margin = view.findViewById(R.id.view_margin);
            ViewGroup.LayoutParams lp;
            lp = view_margin.getLayoutParams();
            lp.height = statusBarHeight;
            view_margin.setLayoutParams(lp);
        } else {

        }

        return view;
    }

    private void initData() {
        context = getContext();
        ImStoreManager imStoreManager = new ImStoreManager(context);
        vp_ad.setPageMargin(20);
        vp_ad.setAdapter(new AdViewPager(getContext(), imStoreManager.getAdInfoList()));
    }


    private void initView(View view) {


        vp_ad = view.findViewById(R.id.vp_ad);

    }


}

