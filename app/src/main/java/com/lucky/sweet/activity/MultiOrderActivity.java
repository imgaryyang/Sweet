package com.lucky.sweet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.push.CommonCallback;
import com.lucky.sweet.R;
import com.lucky.sweet.entity.JoinInRoomInfo;
import com.lucky.sweet.entity.ReservationInfo;
import com.lucky.sweet.properties.ReserveProperties;
import com.lucky.sweet.service.CommunicationService;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.ToolBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Qiuyue on 2018/2/14.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class MultiOrderActivity extends BaseActivity {

    @Bind(R.id.tv_room_id)
    TextView tv_room_id;
    @Bind(R.id.edt_create_room)
    EditText edt_create_room;
    @Bind(R.id.btn_create_room)
    Button btn_create_room;

    @Bind(R.id.edt_join_room)
    EditText edt_join_room;
    @Bind(R.id.edt_join_sec)
    EditText edt_join_sec;
    @Bind(R.id.btn_join_room)
    Button btn_join_room;
    private Title title = null;
    private CommunicationService.MyBinder myBinder;
    private String mer_id;
    private String roomId;

    @Override
    void onServiceBind(CommunicationService.MyBinder myBinder) {
        this.myBinder = myBinder;

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordermulti);

        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mer_id = intent.getStringExtra("mer_id");
        initTitle();
    }


    private void initTitle() {
        ToolBar toolBar = new ToolBar(this);
        toolBar.setColorNewBar(getResources().getColor(R.color.white), 0);
        title = findViewById(R.id.title);
        title.setTitleNameStr("多人订单");
        Title.ButtonInfo buttonLeft = new Title.ButtonInfo(true, Title
                .BUTTON_LEFT, 0, "取消");
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

    @OnClick({R.id.btn_create_room, R.id.btn_join_room})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;

            case R.id.btn_create_room:
                myBinder.createReserveRoom(edt_create_room.getText().toString().trim(), mer_id);
                break;

            case R.id.btn_join_room:

                roomId = edt_join_room.getText().toString();

                myBinder.joinInReserveRoom(roomId.trim(), edt_join_sec.getText().toString().trim());

                break;

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(ReservationInfo info) {
        final String request = info.getRoomId();
        tv_room_id.setText(request);
        switch (request) {
            case ReserveProperties.CREATE_OR_ALTER_ROOM_FAIL:
                Toast.makeText(this, "申请房间失败请重试", Toast.LENGTH_SHORT).show();
                break;
            default:
                MyApplication.bindPushAccount(request, new CommonCallback() {
                    @Override
                    public void onSuccess(String s) {
                        jionToOrder(request);
                    }

                    @Override
                    public void onFailed(String s, String s1) {

                    }
                });
                break;
        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(final JoinInRoomInfo menu) {

        MyApplication.bindPushAccount(roomId, new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                if (menu.getState()) {
                    jionToOrder(roomId);
                    Toast.makeText(MultiOrderActivity.this, "加入房间成功", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(MultiOrderActivity.this, "密码或房间有误", Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onFailed(String s, String s1) {
                Toast.makeText(MultiOrderActivity.this, "加入房间失败，请重试", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String menu) {

        MyApplication.bindPushAccount(roomId, new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                jionToOrder(roomId);

            }


            @Override
            public void onFailed(String s, String s1) {
                Toast.makeText(MultiOrderActivity.this, "加入房间失败，请重试", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void jionToOrder(String roomId) {
        Intent intent = new Intent(MultiOrderActivity.this, MerchantActivity.class);
        intent.putExtra("mer_id", mer_id);
        intent.putExtra("multiOrder", true);
        intent.putExtra("room_id", roomId);
        startActivity(intent);

    }


}
