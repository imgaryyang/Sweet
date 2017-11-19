package com.lucky.sweet.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.lucky.sweet.R;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.ToolBar;

/**
 * Created by Qiuyue on 2017/11/15.
 */

// ╭︿︿︿╮  ╭︿︿︿╮  ╭︿︿︿╮
// {/ o o /} {/ . . /} {/ ︿︿ /}
// ( (oo) )  ( (oo) )  ( (oo) )
//   ︶︶︶     ︶︶︶     ︶︶︶

public class UserCheckPwdActivity extends BasicActivity {
    private Title title = null;
    private ToolBar toolBar;
    private EditText edt_psw;
    private EditText edt_verifypsw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_checkpwd);
        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("userEmail");
        Log.i("userEmail",userEmail);

        initView();

        initTitle();

    }

    private void initView() {
        toolBar = new ToolBar(this);
        toolBar.setImmersionBar();
        edt_psw = findViewById(R.id.edt_psw);
        edt_verifypsw = findViewById(R.id.edt_verifypsw);

    }

    public void finishRegister(View view) {
        String psw = edt_psw.getText().toString().trim();
        String verifypsw = edt_verifypsw.getText().toString().trim();
        if (psw.equals(verifypsw)) {
            Toast.makeText(this, "successful", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "两次密码不同", Toast.LENGTH_SHORT).show();
        }
    }

    private void initTitle() {
        title = (Title) findViewById(R.id.title);
        title.setTitleNameStr("注册");
        title.setTheme(Title.Theme.THEME_TRANSLATE_NODIVIDER);
//        Title.ButtonInfo buttonLeft=new Title.ButtonInfo(true,Title.BUTTON_LEFT,0,"立即注册");
        Title.ButtonInfo buttonLeft = new Title.ButtonInfo(true, Title
                .BUTTON_LEFT, R.drawable.selector_btn_titlebackblack, null);
        title.setOnTitleButtonClickListener(new Title
                .OnTitleButtonClickListener() {
            @Override
            public void onClick(int id, Title.ButtonViewHolder viewHolder) {
                switch (id) {
                    case Title.BUTTON_LEFT:
                        hintInputKb();
                        finish();
                        break;

                }
            }
        });
        title.mSetButtonInfo(buttonLeft);

    }

    protected void hintInputKb() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context
                .INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
