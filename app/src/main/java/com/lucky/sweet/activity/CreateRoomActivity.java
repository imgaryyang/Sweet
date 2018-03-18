package com.lucky.sweet.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.lucky.sweet.R;
import com.lucky.sweet.adapter.FlowFriendAdapter;
import com.lucky.sweet.entity.FlowPeople;
import com.lucky.sweet.entity.InvitationInfo;
import com.lucky.sweet.service.CommunicationService;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.ToolBar;

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

    private CommunicationService.MyBinder myBinder;
    private String mer_id;
    private final static String SHOPID = "mer_id";
    private ListView lv_add_friend;
    private FlowFriendAdapter flowFriendAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createroom);
        setIsBindEventBus();
        initView();
        initData();

    }

    public final static void newInStrance(Activity activity, String mer_id) {
        Intent intent = new Intent(activity, CreateRoomActivity.class);
        intent.putExtra("mer_id", mer_id);
        activity.startActivity(intent);
    }

    private void initData() {
        Intent intent = getIntent();
        mer_id = intent.getStringExtra(SHOPID);
    }

    private void initView() {

        lv_add_friend = findViewById(R.id.lv_add_friend);
        initTitle();
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

        myBinder.createReserveRoom(mer_id);

    }

    public void cancelCreate(View v) {

    }


    @Override
    void onServiceBind(CommunicationService.MyBinder myBinder) {
        this.myBinder = myBinder;
        myBinder.getFlowFriends();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(FlowPeople info) {
        List<FlowPeople.AttentListBean> attent_list = info.getAttent_list();
        flowFriendAdapter = new FlowFriendAdapter(attent_list, this);
        lv_add_friend.setAdapter(flowFriendAdapter);

        flowFriendAdapter.setOnInvitationClick(userId -> {
            Toast.makeText(this, userId, Toast.LENGTH_SHORT).show();
            if (roomID != null) {
                myBinder.invitationFriend(userId, new InvitationInfo(MyApplication.USER_ID, roomID, mer_id));
            } else
                Toast.makeText(this, "请创建房间", Toast.LENGTH_SHORT).show();


        });

    }

    private String roomID;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String info) {
        roomID = info;
        Toast.makeText(this, "建立房间成功：" + info, Toast.LENGTH_SHORT).show();
    }
}
