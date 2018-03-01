package com.lucky.sweet.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lucky.sweet.R;
import com.lucky.sweet.activity.CropIwaActivity;
import com.lucky.sweet.activity.HistoricalOderActivity;
import com.lucky.sweet.activity.MainActivity;
import com.lucky.sweet.activity.MyApplication;
import com.lucky.sweet.activity.MyCommentActivity;
import com.lucky.sweet.activity.SetUserInfoActivity;
import com.lucky.sweet.activity.SettingActivity;
import com.lucky.sweet.activity.VipCardActiviry;
import com.lucky.sweet.views.CircleImageView;
import com.lucky.sweet.widgets.ToolBar;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.io.InputStream;

/**
 * Created by Qiuyue on 2017/11/15.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class ImMeFragment extends Fragment {

    private Button btn_setUserInfo;
    private CircleImageView imv_head;
    private MainActivity activity;
    public final static int CROP_PHOTO = 1000;
    private Bitmap bitmap;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        initView(getView());

        initEvent();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_imme, container, false);

        activity = (MainActivity) getActivity();
        return view;
    }

    public void upPersonPortrait(InputStream filePath) {
        bitmap = BitmapFactory.decodeStream(filePath);
        if (imv_head == null)
            return;
        imv_head.setImageBitmap(bitmap);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            switch (requestCode) {
                case REQUEST_CODE_CHOOSE:
                    Uri uri = Matisse.obtainResult(data).get(0);

                    Intent intent = new Intent(getActivity(),
                            CropIwaActivity.class);
                    intent.putExtra("img_uri", uri.toString());
                    activity.startActivityForResult(intent, CROP_PHOTO);
                    break;
                default:
                    break;
            }

        }

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
                Intent intent = new Intent(getActivity(), SettingActivity.class);
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
        view.findViewById(R.id.rl_hisorder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HistoricalOderActivity.class));
                getActivity().overridePendingTransition(R.anim.act_left_in, R.anim.act_left_out);

            }
        });

        imv_head = view.findViewById(R.id.imv_head);
        if (bitmap!=null) {
            imv_head.setImageBitmap(bitmap);
        }
        imv_head.setmDrawShapeType(CircleImageView.SHAPE_CIRCLE);
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

    }

    private final static int REQUEST_CODE_CHOOSE = 10000;

    private void initEvent() {
        imv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MyApplication.sessionId.equals("")) {
                    Matisse.from(ImMeFragment.this)
                            .choose(MimeType.allOf())
                            .countable(true)
                            .maxSelectable(1)
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                            .thumbnailScale(0.85f)
                            .imageEngine(new GlideEngine())
                            .forResult(REQUEST_CODE_CHOOSE);
                }

            }
        });
    }

}
