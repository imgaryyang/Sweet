package com.lucky.sweet.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.lucky.sweet.R;
import com.lucky.sweet.adapter.PersonalCircleAdapter;
import com.lucky.sweet.entity.CircleDetail;
import com.lucky.sweet.entity.CircleMainInfo;
import com.lucky.sweet.service.CommunicationService;
import com.lucky.sweet.views.CircleImageView;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.ToolBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private ListView lv_comments;

    private CircleMainInfo.CircleListBean circle_info;
    private TextView tv_content;
    private TextView tv_name;
    private String circle_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_circle);
        circle_info = (CircleMainInfo.CircleListBean) getIntent().getSerializableExtra("circle_info");
        if (circle_info != null) {
            circle_id = circle_info.getCircle_id();
        }
        initTitle();
        initViews();
        initDatas();
    }

    private void initDatas() {
        tv_name.setText(circle_info.getNikcname());
        tv_content.setText(circle_info.getContent());
    }

    @Override
    protected void onPause() {
        EventBus.getDefault().unregister(this);

        super.onPause();
    }

    @Override
    protected void onResume() {
        EventBus.getDefault().register(this);

        super.onResume();
    }

    @Override
    void onServiceBind(CommunicationService.MyBinder myBinder) {
        if (circle_id!=null) {

            myBinder.sendCircledetailsInfo(circle_id);
        }
    }

    private void initViews() {
        ToolBar toolBar = new ToolBar(this);
        toolBar.setStatusBarDarkMode();
        imv_head = (CircleImageView) findViewById(R.id.imv_head);
        imv_head.setmDrawShapeType(CircleImageView.SHAPE_CIRCLE);
        lv_comments = findViewById(R.id.lv_comments);
        tv_name = findViewById(R.id.tv_name);
        tv_content = findViewById(R.id.tv_content);

    }

    private void initTitle() {
        title = findViewById(R.id.title);
        title.setTitleNameStr("评论");
        title.setTheme(Title.Theme.THEME_TRANSLATE);
        Title.ButtonInfo buttonLeft = new Title.ButtonInfo(true, Title
                .BUTTON_LEFT, R.drawable.selector_btn_titleback, null);

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(CircleDetail info) {

        lv_comments.setAdapter(new PersonalCircleAdapter(info.getComment(), this));
    }
}
