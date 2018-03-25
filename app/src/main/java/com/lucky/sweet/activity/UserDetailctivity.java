package com.lucky.sweet.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lucky.sweet.R;
import com.lucky.sweet.entity.ChangeNameRequstInfo;
import com.lucky.sweet.entity.UserRegestInfo;
import com.lucky.sweet.service.CommunicationService;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class UserDetailctivity extends BaseActivity {

    private EditText edt_change_nikname;
    private EditText edt_chang_psw;
    private CommunicationService.MyBinder myBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_detailctivity);
        setIsBindEventBus();
        initView();
    }

    private void initView() {

        edt_change_nikname = findViewById(R.id.edt_change_nikname);
        edt_chang_psw = findViewById(R.id.edt_chang_psw);

    }

    @Override
    void onServiceBind(CommunicationService.MyBinder myBinder) {
        this.myBinder = myBinder;
    }

    public void onViewClick(View view) {
        String text;
        switch (view.getId()) {

            case R.id.btn_change_nikeName:
                text = edt_change_nikname.getText().toString();
                if (!TextUtils.isEmpty(text)) {
                    myBinder.upPersonNikName(text);

                }
                break;
            case R.id.btn_change_psw:
                text = edt_chang_psw.getText().toString();
                if (!TextUtils.isEmpty(text)) {
                    myBinder.userRegister(MyApplication.USER_ID, text, false);

                }
                break;

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Evnet(ChangeNameRequstInfo info) {
        if (info.getAttr()) {
            Toast.makeText(this, "匿名修改成功", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this,
                    "匿名修改失败", Toast.LENGTH_SHORT).show();
    }   @Subscribe(threadMode = ThreadMode.MAIN)
    public void Evnet(UserRegestInfo info) {
        if (info.getAttr()) {
            Toast.makeText(this, "密码修改成功", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this,
                    "密码修改失败", Toast.LENGTH_SHORT).show();
    }
}
