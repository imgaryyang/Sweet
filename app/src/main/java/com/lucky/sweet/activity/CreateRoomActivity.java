package com.lucky.sweet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.lucky.sweet.R;
import com.lucky.sweet.adapter.FlowFriendAdapter;
import com.lucky.sweet.entity.FlowPeople;
import com.lucky.sweet.entity.MainStoreInfo;
import com.lucky.sweet.entity.ReservationInfo;
import com.lucky.sweet.service.CommunicationService;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.ToolBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by Qiuyue on 2018/3/7.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class CreateRoomActivity extends BaseActivity {
    private Title title = null;
    private EditText input_password;
    private CommunicationService.MyBinder myBinder;
    private String mer_id;

    private ListView lv_add_friend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createroom);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        mer_id = intent.getStringExtra("mer_id");
    }

    private void initView() {
        input_password = findViewById(R.id.input_password);

        lv_add_friend = findViewById(R.id.lv_add_friend);
        initTitle();
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    private void initTitle() {
        ToolBar toolBar = new ToolBar(this);
        toolBar.setColorNewBar(getResources().getColor(R.color.white), 0);
        title = findViewById(R.id.title);
        title.setTitleNameStr("创建房间");
        Title.ButtonInfo buttonLeft = new Title.ButtonInfo(true, Title
                .BUTTON_LEFT, R.drawable.selector_btn_titleback, null);

        title.setOnTitleButtonClickListener((id, viewHolder) -> {
            switch (id) {
                case Title.BUTTON_LEFT:
                    finish();
                    goPreAnim();
                    break;
            }
        });

        title.mSetButtonInfo(buttonLeft);
    }

    public void submitCreate(View v) {
        String password = input_password.getText().toString().trim();
        if (!password.equals("")) {
            myBinder.createReserveRoom(password, mer_id);
        }
    }

    public void cancelCreate(View v) {

    }


    @Override
    void onServiceBind(CommunicationService.MyBinder myBinder) {
        this.myBinder = myBinder;
        myBinder.getFlowFriends();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(ReservationInfo reservationInfo) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(FlowPeople info) {
        List<FlowPeople.AttentListBean> attent_list = info.getAttent_list();
        FlowFriendAdapter flowFriendAdapter = new FlowFriendAdapter(attent_list, this);
        lv_add_friend.setAdapter(flowFriendAdapter);
        lv_add_friend.setOnItemClickListener((parent, view, position, id) -> {
            myBinder.invitationFriend(attent_list.get(position).getUser_id());

        });
    }
}
