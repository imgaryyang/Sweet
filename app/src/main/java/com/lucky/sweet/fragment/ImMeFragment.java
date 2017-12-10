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
import com.lucky.sweet.activity.SetUserInfoActivity;
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

  /*  private void initData() {
        config = getActivity().getSharedPreferences("config", Activity.MODE_PRIVATE);
        edit = config.edit();
    }*/

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (resultCode) {
                case 0:
                    boolean login = data.getBooleanExtra("login", true);
                    if (login) {
                        edit.putBoolean("logined", true);
                        edit.commit();
                        btn_userOut.setText("退出登陆");

                    }
                    break;

                default:
                    break;
            }

        }
    }*/

    private void initView(View view) {
        /*btn_userOut = view.findViewById(R.id.btn_userOut);
        if (config.getBoolean("logined", false)) {
            btn_userOut.setText("退出登陆");
        } else {
            btn_userOut.setText("用户登陆");
        }

        btn_userOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn_userOut.getText().toString().equals("用户登陆")) {
                    Intent intent = new Intent(getActivity(), UserLoginActivity.class);
                    startActivityForResult(intent, 0);
                } else {
                    edit.putBoolean("logined", false);
                    edit.commit();
                    btn_userOut.getText().toString().equals("用户登陆");
                    return;
                }
            }
        });*/
        btn_setUserInfo = view.findViewById(R.id.btn_setUserInfo);
        btn_setUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SetUserInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
