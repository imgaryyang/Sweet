package com.lucky.sweet.activity;

import android.os.Bundle;

import com.lucky.sweet.R;
import com.lucky.sweet.views.CircleImageView;
import com.lucky.sweet.views.ShopCarPopWindow;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.ToolBar;

/**
 * Created by Qiuyue on 2018/1/16.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class PersonalCircleActivity extends BaseActivity {
    private Title title = null;
    private CircleImageView imv_head;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_circle);
        initTitle();
        initViews();
    }

    private void initViews() {
        ToolBar toolBar = new ToolBar(this);
        toolBar.setColorNewBar(getResources().getColor(R.color.white), 0);
        imv_head = (CircleImageView)findViewById(R.id.imv_head);
        imv_head.setmDrawShapeType(CircleImageView.SHAPE_CIRCLE);
    }

    private void initTitle() {
        title = (Title) findViewById(R.id.title);
        title.setTitleNameStr("评论");

        Title.ButtonInfo buttonLeft = new Title.ButtonInfo(true, Title
                .BUTTON_LEFT,R.drawable.selector_btn_titleback, null);

        title.setOnTitleButtonClickListener(new Title
                .OnTitleButtonClickListener() {
            @Override
            public void onClick(int id, Title.ButtonViewHolder viewHolder) {
                switch (id) {
                    case Title.BUTTON_LEFT:
                        finish();
                        goPreAnim();
                        break;
                }
            }
        });

        title.mSetButtonInfo(buttonLeft);
    }

}
