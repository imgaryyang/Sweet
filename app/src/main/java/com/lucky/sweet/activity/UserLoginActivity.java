package com.lucky.sweet.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lucky.sweet.R;
import com.lucky.sweet.moudel.loginregister.LoginRegisterManager;
import com.lucky.sweet.widgets.ToolBar;

public class UserLoginActivity extends BaseActivity {

    private ToolBar toolBar;
    private EditText edt_userEmail;
    private EditText edt_password;
    private LoginRegisterManager loginRegisterManager;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        initData();

        initView();



    }

    private void initData() {
        loginRegisterManager = new LoginRegisterManager(this);

    }


    private void initView() {
        toolBar = new ToolBar(this);
        toolBar.setImmersionBar();
        edt_userEmail = findViewById(R.id.edt_userEmail);
        edt_password = findViewById(R.id.edt_password);
    }


    public void login(View view) {
        String userID = edt_userEmail.getText().toString().trim();
        String password = edt_password.getText().toString().trim();
        if (!userID.isEmpty() && !password.isEmpty()) {
            loginRegisterManager.userLogin(userID, password);
        } else {
            Toast.makeText(this, "请输入账号或者密码", Toast.LENGTH_SHORT).show();
        }
    }

    public void forgetPsw(View view) {
        Intent intent = new Intent(this, UserRegisterActivity.class);
        intent.putExtra("isRegister", false);
        startActivity(intent);

    }

    public void register(View view) {
        Intent intent = new Intent(this,
                UserRegisterActivity.class);
        intent.putExtra("isRegister", true);
        startActivity(intent);
    }


}
