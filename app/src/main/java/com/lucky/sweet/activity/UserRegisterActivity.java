package com.lucky.sweet.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.lucky.sweet.R;
import com.lucky.sweet.entity.GetMailVerInfo;
import com.lucky.sweet.entity.MailValiInfo;
import com.lucky.sweet.entity.UserLoginInfo;
import com.lucky.sweet.entity.UserRegestInfo;
import com.lucky.sweet.fragment.EmailSubmitFragment;
import com.lucky.sweet.fragment.PassWordSubimitFragment;
import com.lucky.sweet.service.CommunicationService;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.ToolBar;
import com.lucky.sweet.widgets.noscrollview.DepthPageTransformer;
import com.lucky.sweet.widgets.noscrollview.FragAdapter;
import com.lucky.sweet.widgets.noscrollview.NoScrollViewPager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class UserRegisterActivity extends BaseActivity {
    private Title title = null;
    private ToolBar toolBar;

    private NoScrollViewPager vp_reg;
    private PassWordSubimitFragment passWordSubimitFragment;
    private EmailSubmitFragment emailSubmitFragment;
    private Boolean isRegister;
    private CommunicationService.MyBinder myBinder;
    private SharedPreferences.Editor edit;
    private final static String INTENTTAG = "isRegister";

    public static void newInstance(Activity activity, boolean isRegister, int result) {
        Intent intent = new Intent(activity, UserRegisterActivity.class);
        intent.putExtra(INTENTTAG, isRegister);
        activity.startActivityForResult(intent, result);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        setIsBindEventBus();

        isRegister = getIntent().getBooleanExtra(INTENTTAG, true);


        initView();

        initTitle();

        initData();

        initEvent();

    }

    @Override
    void onServiceBind(CommunicationService.MyBinder myBinder) {
        this.myBinder = myBinder;
    }

    @SuppressLint("CommitPrefEdits")
    private void initData() {

        edit = getSharedPreferences("config", Activity.MODE_PRIVATE).edit();

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
        title.setOnTitleButtonClickListener((id, viewHolder) -> {
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
        });
        title.mSetButtonInfo(buttonLeft);

    }

    private void initEvent() {
        emailSubmitFragment.setOnEmailVerListener(new EmailSubmitFragment.OnEmailVer() {
            @Override
            public void onEmailVer(String email, String verPsw) {
                myBinder.emailVer(email, verPsw);
            }

            @Override
            public void onGetValidate(String email, String verPsw) {
                myBinder.forgetValidate(UserRegisterActivity.this, email, verPsw);
            }

            @Override
            public void checkOutEmail(String email) {
                myBinder.checkOutEmail(email);
            }

            @Override
            public void forgetSubmit(String email) {
                myBinder.forgetSubmit(UserRegisterActivity.this, email);
            }
        });
        passWordSubimitFragment.setOnUserUpdataInfo(new PassWordSubimitFragment.OnUserUpdataInfo() {
            @Override
            public void onUserRegister(String email, String psw) {
                myBinder.userRegister(email, psw);
            }

            @Override
            public void onUserForget(String email, String psw) {
                myBinder.userRegister(email, psw);

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


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(GetMailVerInfo info) {
        if (info.getAttr()) {
            Toast.makeText(this, "请耐心等待邮件", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "邮箱已存在或操作过于频繁", Toast.LENGTH_SHORT).show();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MailValiInfo info) {
        if (info.getAttr()) {
            passWordSubimitFragment.setCurrentEmail(emailSubmitFragment.getEmail());
            Toast.makeText(this, "验证成功请进行下一步", Toast.LENGTH_SHORT).show();
            moveToNextStep();
        } else
            Toast.makeText(this, "验证失败", Toast.LENGTH_SHORT).show();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(UserRegestInfo info) {
        if (isRegister) {
            if (info.getAttr()) {
                edit.putBoolean("logined", true);
                edit.putString("Id", info.getUserID());
                edit.putString("Psw", info.getPassword());
                if (edit.commit()) {
                    Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                    MyApplication.initSession(flag -> {
                        if (flag) {
                            Intent intent = new Intent();
                            intent.putExtra("Id", info.getUserID());
                            intent.putExtra("Psw", info.getPassword());
                            setResult(RESULT_OK, intent);
                        }
                    });
                }
            } else
                Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();

        } else {
            if (info.getAttr()) {
                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "原密码不能重复", Toast.LENGTH_SHORT).show();

        }

    }
}
