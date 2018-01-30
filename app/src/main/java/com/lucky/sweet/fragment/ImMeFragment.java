package com.lucky.sweet.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lucky.sweet.R;
import com.lucky.sweet.activity.MyCommentActivity;
import com.lucky.sweet.activity.OrderSubmitActivity;
import com.lucky.sweet.activity.SetUserInfoActivity;
import com.lucky.sweet.activity.VipCardActiviry;
import com.lucky.sweet.views.CircleImageView;
import com.lucky.sweet.widgets.ToolBar;

/**
 * Created by Qiuyue on 2017/11/15.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class ImMeFragment extends Fragment {
    /*private Button btn_userOut;
    private SharedPreferences config;
    private SharedPreferences.Editor edit;*/
    private Button btn_setUserInfo;
    private CircleImageView imv_head;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_imme, container, false);
//        initData();

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

        btn_setUserInfo = view.findViewById(R.id.btn_setUserInfo);
        view.findViewById(R.id.rl_vipcard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), VipCardActiviry.class));
                getActivity().overridePendingTransition(R.anim.act_left_in, R.anim.act_left_out);

            }
        });
        btn_setUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SetUserInfoActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.act_left_in, R.anim.act_left_out);

            }
        });

        view.findViewById(R.id.rl_mycomment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyCommentActivity.class));
                getActivity().overridePendingTransition(R.anim.act_left_in, R.anim.act_left_out);

            }
        });

        imv_head = view.findViewById(R.id.imv_head);
        imv_head.setmDrawShapeType(CircleImageView.SHAPE_CIRCLE);
    }
}
