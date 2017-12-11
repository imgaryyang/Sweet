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
import android.widget.TextView;
import android.widget.Toast;

import com.lucky.sweet.R;
import com.lucky.sweet.activity.MainActivity;
import com.lucky.sweet.activity.StoreDisplatActivity;
import com.lucky.sweet.adapter.AdViewPagerAdapter;
import com.lucky.sweet.adapter.RecreationViewPagerAdapter;
import com.lucky.sweet.moudel.ImStoreManager;
import com.lucky.sweet.properties.Properties;
import com.lucky.sweet.utility.HiddenAnimUtils;
import com.lucky.sweet.utils.PanduanNet;
import com.lucky.sweet.viewpagerexpand.AdViewPagerTransformer;
import com.lucky.sweet.views.GradualChangeLinearLayout;
import com.lucky.sweet.views.MySearchView;
import com.lucky.sweet.widgets.ToolBar;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

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
    private GradualChangeLinearLayout ll_se;

    private TextView tv_moreFood;
    private TextView tv_moreRelax;
    private ViewPager vp_foodStore;

    private Context context;

    private final int MAXTIME = 300000000;

    private TencentLocationManager locationManager;
    private HiddenAnimUtils hiddenAnimUtils;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_imstore, container, false);
        context = getContext();

        initView(view);

        initData();

        initEvent();
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
        }
        return view;
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

    private TencentLocationListener listener = new
            TencentLocationListener() {
                @Override
                public void onLocationChanged(TencentLocation tencentLocation, int error, String s) {

                    if (error == TencentLocation.ERROR_OK) {
                        String city = tencentLocation.getCity().toString()
                                .trim();
                        //initWeather(city);
                        if (city.length() > 3) {
                            city = city.substring(0, 2) + "...";
                        }
                        tv_location.setText(city);
                    }
                    stopLocation();
                }

                @Override
                public void onStatusUpdate(String s, int i, String s1) {

                }
            };


    private void stopLocation() {
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

        hiddenAnimUtils = HiddenAnimUtils.newInstance(getContext(),
                ll_se, msv_search, 1600);

        vp_ad.setPageTransformer(true, new AdViewPagerTransformer());

        vp_ad.setAdapter(new AdViewPagerAdapter(getContext(), imStoreManager.getAdInfoList()));
        vp_foodStore.setAdapter(new RecreationViewPagerAdapter(getActivity(), imStoreManager.getFoodList()));
        vp_funne.setAdapter(new RecreationViewPagerAdapter(getActivity(), imStoreManager.getFoodList()));

    }


    private void initView(View view) {


        vp_ad = view.findViewById(R.id.vp_ad);
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

    private void initWeather(String city) {
        if (city.indexOf("市") > 0) {
            city = city.substring(0, city.length() - 1);
        }
        String param = Properties.WEATHERREQUESTBODY + city;
        StringBuilder sb = new StringBuilder();
        InputStream is = null;
        BufferedReader br = null;
        try {
            //接口地址
            String url = "https://api.heweather.com/s6/weather";
            URL uri = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(10000);
            connection.setRequestProperty("accept", "*/*");
            //发送参数
            connection.setDoOutput(true);
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            out.print(param);
            out.flush();
            //接收结果
            is = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            String line;
            //缓冲逐行读取
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            System.out.println(sb.toString() + "aaaaaaaaa");
        } catch (Exception ignored) {
        } finally {
            //关闭流
            try {
                if (is != null) {
                    is.close();
                }
                if (br != null) {
                    br.close();
                }

            } catch (IOException e2) {
            }
        }

    }
}

