package com.lucky.sweet.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lucky.sweet.R;
import com.lucky.sweet.activity.BaseActivity;
import com.lucky.sweet.model.loginregister.LoginRegisterManager;
import com.lucky.sweet.utility.StringFormatUtility;

/**
 * A simple {@link Fragment} subclass.
 */
public class PassWordSubimitFragment extends Fragment {


    private final Boolean isRegister;
    private String userEmail;
    private LoginRegisterManager loginRegisterManager;
    private EditText edt_psw;
    private EditText edt_verifypsw;
    private Button btn_submit_regerist;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_pass_word_subimit, container, false);
        // Inflate the layout for this fragment
        initView(inflate);
        initEvent();
        return inflate;

    }

    public PassWordSubimitFragment(LoginRegisterManager loginRegisterManager, Boolean isRegister) {
        this.loginRegisterManager = loginRegisterManager;
        this.isRegister = isRegister;
    }


    private void initEvent() {
        btn_submit_regerist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String psw = edt_psw.getText().toString().trim();
                String verifypsw = edt_verifypsw.getText().toString().trim();
                if (psw.equals(verifypsw)) {
                    if (psw.length() >= 6 && StringFormatUtility.checkOutPSW(psw)) {
                        if (isRegister) {
                            loginRegisterManager.userRegister(userEmail, psw);
                        } else {
                            loginRegisterManager.userForget(userEmail, psw);
                        }
                    } else {
                        ((BaseActivity) getContext()).showDialogBaseAct
                                (null, "密码必须同时包含数字和字母。并且长度不小于6位", "确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {dialogInterface.cancel();}}, null, null, null, null, getContext());
                    }
                } else {
                    Toast.makeText(getActivity(), "两次密码不同", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView(View inflate) {
        edt_psw = inflate.findViewById(R.id.edt_psw);
        edt_verifypsw = inflate.findViewById(R.id.edt_verifypsw);
        btn_submit_regerist = inflate.findViewById(R.id
                .btn_submit_regerist);
    }


    public void setCurrentEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
