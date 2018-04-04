package com.lucky.sweet.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lucky.sweet.R;
import com.lucky.sweet.entity.ChangeNameRequstInfo;
import com.lucky.sweet.entity.StatueCheckBaseEntitiy;
import com.lucky.sweet.service.CommunicationService;
import com.lucky.sweet.views.CircleImageView;
import com.lucky.sweet.views.MyToast;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.ToolBar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class UserDetailctivity extends BaseActivity {
    private Title title = null;
    private CircleImageView imv_head;

    private EditText edt_change_nikname;
    private EditText edt_chang_psw;
    private CommunicationService.MyBinder myBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_detailctivity);
        setIsBindEventBus();
//        initView();
        ToolBar toolBar = new ToolBar(this);
        toolBar.setColorNewBar(getResources().getColor(R.color.white), 0);
    }

//    private void initTitle() {
//        title = findViewById(R.id.title);
//        title.setTitleNameStr("账户信息");
//        Title.ButtonInfo buttonLeft = new Title.ButtonInfo(true, Title
//                .BUTTON_LEFT, R.drawable.selector_btn_titleback, null);
//        title.setOnTitleButtonClickListener((id, viewHolder) -> {
//            switch (id) {
//                case Title.BUTTON_LEFT:
//                    finish();
//                    goPreAnim();
//                    break;
//            }
//        });
//        title.mSetButtonInfo(buttonLeft);
//    }

//    private void initView() {
//        imv_head = findViewById(R.id.imv_head);
//        imv_head.setmDrawShapeType(CircleImageView.SHAPE_CIRCLE);
//
//        edt_change_nikname = findViewById(R.id.edt_change_nikname);
//        edt_chang_psw = findViewById(R.id.edt_chang_psw);
//
//    }

    @Override
    void onServiceBind(CommunicationService.MyBinder myBinder) {
        this.myBinder = myBinder;
    }

//    public void onViewClick(View view) {
//        String text;
//        switch (view.getId()) {
//
//            case R.id.btn_change_nikeName:
//                text = edt_change_nikname.getText().toString();
//                if (!TextUtils.isEmpty(text)) {
//                    myBinder.upPersonNikName(text);
//
//                }
//                break;
//            case R.id.btn_change_psw:
//                text = edt_chang_psw.getText().toString();
//                if (!TextUtils.isEmpty(text)) {
//                    myBinder.loginedChanged(text);
//
//                }
//                break;
//
//        }
//    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Evnet(ChangeNameRequstInfo info) {
        if (info.getAttr()) {
            MyToast.showShort("匿名修改成功");

        } else

        MyToast.showShort("匿名修改失败");

    }   @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(StatueCheckBaseEntitiy info) {
        if (info.getAttr()) {
            MyToast.showShort("密码修改成功");

        } else
        MyToast.showShort("密码修改失败");

    }
}
