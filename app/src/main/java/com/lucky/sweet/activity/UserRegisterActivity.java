package com.lucky.sweet.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.lucky.sweet.R;
import com.lucky.sweet.moudel.LoginRegisterHandler;
import com.lucky.sweet.moudel.LoginRegisterManager;
import com.lucky.sweet.utility.EmailUtiliy;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.ToolBar;

public class UserRegisterActivity extends BasicActivity {
    private Title title = null;
    private ToolBar toolBar;
    private EditText edt_userEmail;
    private EditText edt_verPassword;
    private LoginRegisterManager loginRegisterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        loginRegisterManager = new LoginRegisterManager(this, new LoginRegisterHandler(this));
        initView();

        initTitle();

    }

    private void initView() {
        toolBar = new ToolBar(this);
        toolBar.setImmersionBar();
        edt_userEmail = findViewById(R.id.edt_userEmail);
        edt_verPassword = findViewById(R.id.edt_verPassword);
        findViewById(R.id.btn_nextStep).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt_userEmail.getText().toString().trim();
                String verPsw = edt_verPassword.getText().toString().trim();
                if (!email.isEmpty() && !verPsw.isEmpty()) {
                    System.out.println("email:"+email+"verPsw"+verPsw);
                    loginRegisterManager.emailVer(email,verPsw);
                } else
                    Toast.makeText(UserRegisterActivity.this, "请填写完整信息", Toast
                            .LENGTH_SHORT).show();
            }
        });

    }

    public void delete(View view) {
        edt_userEmail.setText("");
    }

    public void getVerification(View view) {
        String email = edt_userEmail.getText().toString().trim();
        if (!email.isEmpty()) {
            if (EmailUtiliy.checkEmail(email))
                loginRegisterManager.CheckOutEmail(email);
            else
                Toast.makeText(this, "请输入正确邮箱", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
    }

    public void nextStep(View view) {
       /* String email = edt_userEmail.getText().toString().trim();
        String verPsw = edt_verPassword.getText().toString().trim();
        if (!email.isEmpty() && !verPsw.isEmpty()) {
            loginRegisterManager.CheckOutEmailFirPsw(email,verPsw);
        } else
            Toast.makeText(this, "请填写完整信息", Toast.LENGTH_SHORT).show();*/
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
