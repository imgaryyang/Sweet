package com.lucky.sweet.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

import com.lucky.sweet.R;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.ToolBar;

public class UserLoginActivity extends AppCompatActivity {

    private ToolBar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        toolBar = new ToolBar(this);
        toolBar.setImmersionBar();
    }

}
