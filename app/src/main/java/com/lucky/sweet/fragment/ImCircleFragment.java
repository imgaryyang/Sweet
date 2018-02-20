package com.lucky.sweet.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.lucky.sweet.R;
import com.lucky.sweet.activity.MainActivity;
import com.lucky.sweet.activity.PersonalCircleActivity;
import com.lucky.sweet.activity.SendCircleActivity;
import com.lucky.sweet.adapter.CircleListViewAdapter;
import com.lucky.sweet.entity.CircleMainInfo;
import com.lucky.sweet.views.CircleImageView;
import com.lucky.sweet.widgets.ImageViewWatcher.ImageWatcher;
import com.lucky.sweet.widgets.ImageViewWatcher.MessagePicturesLayout;
import com.lucky.sweet.widgets.ToolBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.forward.androids.views.StringScrollPicker;

/**
 * Created by Qiuyue on 2017/11/15.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class ImCircleFragment extends Fragment implements
        MessagePicturesLayout.Callback {
    //    private Title title = null;
    private static final String[] title = {"朋友", "今日", "广场"};
    private CircleImageView imv_head;
    private ImageWatcher vImageWatcher;
    private ListView lv_circle;
    private CircleListViewAdapter circleListViewAdapter;
    private List<CircleMainInfo.CircleListBean> circle_list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_imcircle, container, false);
        imv_head = view.findViewById(R.id.imv_head);
        imv_head.setmDrawShapeType(CircleImageView.SHAPE_CIRCLE);
        imv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SendCircleActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.act_left_in, R.anim.act_left_out);
            }
        });

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

        lv_circle = view.findViewById(R.id.lv_circle);
        final StringScrollPicker sc_picker = view.findViewById(R.id.sc_picker);
        final ArrayList<CharSequence> charSequences = new ArrayList<CharSequence>(Arrays.asList(title));

        sc_picker.setData(charSequences);

        lv_circle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int i, long l) {
                Intent intent = new Intent(getActivity(), PersonalCircleActivity.class);

                intent.putExtra("circle_info", circle_list.get(i));
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.act_left_in, R.anim.act_left_out);
            }
        });
        vImageWatcher = view.findViewById(R.id.imv_watcger);
        // 如果是透明状态栏，你需要给ImageWatcher标记 一个偏移值，以修正点击ImageView查看的启动动画的Y轴起点的不正确
//        vImageWatcher.setTranslucentStatus(!isTranslucentStatus ?
//                ImageViewWatcherUtils.calcStatusBarHeight(getContext()) : 0);

//        if (vImageWatcher.getVisibility()==View.GONE)
//        {
//
//        }


        //长按
        vImageWatcher.setOnPictureLongPressListener(new ImageWatcher.OnPictureLongPressListener() {
            @Override
            public void onPictureLongPress() {

            }
        });
        //是否用手势
        vImageWatcher.setOnGesture(true);
    }

    @Override
    public void onThumbPictureClick(ImageView i, List<ImageView>
            imageGroupList, List<String> urlList) {

        vImageWatcher.show(i, imageGroupList, urlList);

        ((MainActivity) getActivity()).hideTabView();
/*
        if (vImageWatcher.getVisibility()==View.VISIBLE)
        {
            ((MainActivity) getActivity()).showTabView();

        }*/
    }

    public void initData(CircleMainInfo info) {
        circle_list = info.getCircle_list();
        circleListViewAdapter = new CircleListViewAdapter(getActivity(), circle_list).setCallBack(this);
        lv_circle.setAdapter(circleListViewAdapter);
    }




   /* @Override
    public void onBackPressed() {
        if (!vImageWatcher.handleBackPressed()) {
            super.onBackPressed();
        }
    }*/

//    //重写长按
//    @Override
//    public void onPictureLongPress() {
//        Toast.makeText(this,"11111",Toast.LENGTH_SHORT).show();
//    }

}
