package com.lucky.sweet.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.inputmethod.InputMethodManager;

import com.lucky.sweet.R;
import com.lucky.sweet.fragment.EmailSubmitFragment;
import com.lucky.sweet.fragment.PassWordSubimitFragment;
import com.lucky.sweet.service.CommunicationService;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.ToolBar;
import com.lucky.sweet.widgets.noscrollview.DepthPageTransformer;
import com.lucky.sweet.widgets.noscrollview.FragAdapter;
import com.lucky.sweet.widgets.noscrollview.NoScrollViewPager;

import java.util.ArrayList;

public class UserRegisterActivity extends BaseActivity {
    private Title title = null;
    private ToolBar toolBar;

    private NoScrollViewPager vp_reg;
    private PassWordSubimitFragment passWordSubimitFragment;
    private EmailSubmitFragment emailSubmitFragment;
    private Boolean isRegister;
    private CommunicationService.MyBinder myBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        isRegister = getIntent().getBooleanExtra("isRegister", true);


        initView();

        initTitle();

        initData();

        initEvent();

    }

    @Override
    void onServiceBind(CommunicationService.MyBinder myBinder) {
        this.myBinder = myBinder;
    }

    private void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(emailSubmitFragment);
        fragments.add(passWordSubimitFragment);

        vp_reg.setAdapter(new FragAdapter(getSupportFragmentManager(), fragments));
        vp_reg.setPageTransformer(true, new DepthPageTransformer());
    }

    private void initView() {
        toolBar = new ToolBar(this);
        toolBar.setImmersionBar();
        vp_reg = findViewById(R.id.vp_reg);

        emailSubmitFragment = new EmailSubmitFragment(isRegister);
        passWordSubimitFragment = new PassWordSubimitFragment(isRegister);

    }

    private void initTitle() {
        title = (Title) findViewById(R.id.title);
        if (isRegister)
            title.setTitleNameStr("注册");
        else
            title.setTitleNameStr("找回密码");
        title.setTheme(Title.Theme.THEME_TRANSLATE_GRAYDIVIDER);

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
                        if (vp_reg.getCurrentItem() != 0) {
                            vp_reg.setCurrentItem(vp_reg.getCurrentItem() -
                                    1, true);
                            return;
                        }
                        finish();
                        break;

                }
            }
        });
        title.mSetButtonInfo(buttonLeft);

    }

    private void initEvent() {
        emailSubmitFragment.setOnEmailVer(new EmailSubmitFragment.OnEmailVer() {
            @Override
            public void onEmailVer(String email, String verPsw) {
                myBinder.emailVer(UserRegisterActivity.this, email, verPsw);
            }

            @Override
            public void onGetValidate(String email, String verPsw) {
                myBinder.forgetValidate(UserRegisterActivity.this, email, verPsw);
            }

            @Override
            public void checkOutEmail(String email) {
                myBinder.checkOutEmail(UserRegisterActivity.this, email);
            }

            @Override
            public void forgetSubmit(String email) {
                myBinder.forgetSubmit(UserRegisterActivity.this, email);
            }
        });
        passWordSubimitFragment.setOnUserUpdataInfo(new PassWordSubimitFragment.OnUserUpdataInfo() {
            @Override
            public void onUserRegister(String email, String psw) {
                myBinder.userRegister(UserRegisterActivity.this, email, psw);
            }

            @Override
            public void onUserForget(String email, String psw) {
                myBinder.userRegister(UserRegisterActivity.this, email, psw);

            }
        });
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

    public void setEmail(String email) {
        passWordSubimitFragment.setCurrentEmail(email);
    }

    public void moveToNextStep() {
        vp_reg.setCurrentItem(vp_reg.getCurrentItem() + 1, true);
    }

    @Override
    public void onBackPressed() {
        if (vp_reg.getCurrentItem() != 0) {
            vp_reg.setCurrentItem(vp_reg.getCurrentItem() - 1, true);
            return;
        }
        super.onBackPressed();
    }
}
