package com.lucky.sweet.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.lucky.sweet.R;
import com.lucky.sweet.adapter.HisOrderAdapter;
import com.lucky.sweet.adapter.MyCommentAdapter;
import com.lucky.sweet.service.CommunicationService;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.ToolBar;

import java.util.ArrayList;

/**
 * Created by Qiuyue on 2018/3/2.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class HistoricalOderActivity extends BaseActivity {

    private Title title = null;
    private ListView lv_hisOrder;


    @Override
    void onServiceBind(CommunicationService.MyBinder myBinder) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historicalorder);
        initTitle();
        initViews();
    }

    private void initViews() {
        lv_hisOrder = (ListView)findViewById(R.id.lv_hisOrder);
        ArrayList<String> objects = new ArrayList<String>();
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        objects.add("");
        lv_hisOrder.setAdapter(new HisOrderAdapter(objects,this));
    }

    private void initTitle() {
        ToolBar toolBar = new ToolBar(this);
        toolBar.setColorNewBar(getResources().getColor(R.color.white), 0);
        title = (Title) findViewById(R.id.title);
        title.setTitleNameStr("历史订单");

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
