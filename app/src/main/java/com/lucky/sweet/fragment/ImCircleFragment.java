package com.lucky.sweet.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lucky.sweet.R;
import com.lucky.sweet.adapter.CircleListViewAdapter;
import com.lucky.sweet.widgets.ToolBar;

import java.util.ArrayList;

/**
 * Created by Qiuyue on 2017/11/15.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class ImCircleFragment extends Fragment {
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
        } else {
        }


        return view;
    }

    private void initView(View view) {
        ListView lv_circle = view.findViewById(R.id.lv_circle);
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
