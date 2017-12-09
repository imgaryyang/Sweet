package com.lucky.sweet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lucky.sweet.R;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.ToolBar;

/**
 * Created by Qiuyue on 2017/12/8.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class StoreDisplatActivity extends BasicActivity {

    private Title title = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storedisplay);

        initTitle();

    }

    private void initTitle() {
        ToolBar toolBar = new ToolBar(this);
        toolBar.setColorNewBar(getResources().getColor(R.color.white), 0);
        title = (Title) findViewById(R.id.title);

        Intent intent = getIntent();
        if (intent.getStringExtra("tv_moreFood") != null && intent.getStringExtra("tv_moreFood").equals("Food")) {
            title.setTitleNameStr("餐厅");
        }
        else if (intent.getStringExtra("tv_moreRelax") != null && intent.getStringExtra("tv_moreRelax").equals("Relax")){
            title.setTitleNameStr("休闲");
        }
        Title.ButtonInfo buttonLeft = new Title.ButtonInfo(true, Title
                .BUTTON_LEFT, R.drawable.selector_btn_titleback, null);
        title.setOnTitleButtonClickListener(new Title
                .OnTitleButtonClickListener() {
            @Override
            public void onClick(int id, Title.ButtonViewHolder viewHolder) {
                switch (id) {
                    case Title.BUTTON_LEFT:
                        finish();
                        break;
                }
            }
        });
        title.mSetButtonInfo(buttonLeft);
    }
}
