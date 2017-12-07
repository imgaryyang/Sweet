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
import android.widget.TextView;
import android.widget.Toast;

import com.lucky.sweet.R;
import com.lucky.sweet.adapter.AdViewPager;
import com.lucky.sweet.moudel.ImStoreFragmentManager.ImStoreManager;
import com.lucky.sweet.utils.PanduanNet;
import com.lucky.sweet.viewpagerexpand.AdViewPagerTransformer;
import com.lucky.sweet.widgets.ToolBar;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

/**
 * Created by Qiuyue on 2017/11/15.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class ImStoreFragment extends Fragment {


    private ViewPager vp_ad;
    private TextView tv_location;

    private Context context;

    private final int MAXTIME = 300000000;

    private TencentLocationManager locationManager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_imstore, container, false);
        context = getContext();

        initView(view);

        initData();

        initLocation();


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

    private TencentLocationListener listener = new
            TencentLocationListener() {
                @Override
                public void onLocationChanged(TencentLocation
                                                      tencentLocation, int
                                                      error, String s) {

                    if (error == TencentLocation.ERROR_OK) {
                        tv_location.setText(tencentLocation.getCity().toString().trim());
                    }
                    stopLocation();
                }

                @Override
                public void onStatusUpdate(String s, int i, String s1) {

                }
            };

    public void stopLocation() {
        locationManager.removeUpdates(listener);
    }

    private void initLocation() {
        boolean networkState = PanduanNet.detect(getActivity());
        if (!networkState) {
            Toast.makeText(context, "网络错误，无法获取当前位置", Toast.LENGTH_SHORT).show();
        } else {
            locationManager = TencentLocationManager.getInstance(getActivity());
            locationManager.setCoordinateType(TencentLocationManager.COORDINATE_TYPE_GCJ02);
            TencentLocationRequest request = TencentLocationRequest.create();
            request.setInterval(MAXTIME);
            request.setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_ADMIN_AREA);
            int requestCode = locationManager.requestLocationUpdates(request,
                    listener);
            if (requestCode == 0) {

            }
        }
    }


    private void initData() {


        ImStoreManager imStoreManager = new ImStoreManager(context);
        vp_ad.setPageMargin(20);
        vp_ad.setOffscreenPageLimit(3);
        vp_ad.setPageTransformer(true, new AdViewPagerTransformer());
        vp_ad.setAdapter(new AdViewPager(getContext(), imStoreManager.getAdInfoList()));
    }


    private void initView(View view) {

        vp_ad = view.findViewById(R.id.vp_ad);
        tv_location = view.findViewById(R.id.tv_location);

    }


}

