package com.lucky.sweet.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lucky.sweet.R;
import com.lucky.sweet.adapter.CircleListViewAdapter;
import com.lucky.sweet.views.IndicatorView;
import com.lucky.sweet.widgets.ToolBar;

import java.util.ArrayList;
import java.util.Arrays;

import cn.forward.androids.views.ScrollPickerView;
import cn.forward.androids.views.StringScrollPicker;

/**
 * Created by Qiuyue on 2017/11/15.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class ImCircleFragment extends Fragment {
    //    private Title title = null;
    private static final String[] title = {"朋友", "今日", "广场"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_imcircle, container, false);
        initView(view);
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
//        title = view.findViewById(R.id.title);
//        title.setTitleNameStr("甜甜圈");
//        Title.ButtonInfo buttonLeft = new Title.ButtonInfo(true, Title
//                .BUTTON_LEFT,0,null);
//
//        title.mSetButtonInfo(buttonLeft);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initView(View view) {

        ListView lv_circle = view.findViewById(R.id.lv_circle);
        final StringScrollPicker sc_picker = view.findViewById(R.id.sc_picker);
        final IndicatorView indicator = view.findViewById(R.id.indicator);
        final ArrayList<CharSequence> charSequences = new ArrayList<CharSequence>(Arrays.asList(title));
        sc_picker.setOnSelectedListener(new ScrollPickerView.OnSelectedListener() {
            @Override
            public void onSelected(ScrollPickerView scrollPickerView, int position) {
                indicator.playByStartPointToNext(position);
            }
        });

        sc_picker.setData(charSequences);
        indicator.initIndicator(charSequences.size());
        ArrayList<String> objects = new ArrayList<String>();
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        lv_circle.setAdapter(new CircleListViewAdapter(objects, getActivity()));
    }

}
