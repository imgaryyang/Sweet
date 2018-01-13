package com.lucky.sweet.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.lucky.sweet.R;

public class MainSearchActiviy extends AppCompatActivity {

    private ImageView imv_search;
    private EditText edt_search;
    private Button btn_clear_search;
    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_search_activiy);

        initView();

        initEvent();
    }

    private void initEvent() {
        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() != 0 || s.equals("")) {
                    if (edt_search.getGravity() == Gravity.CENTER) {
                        edt_search.setGravity(Gravity.LEFT);
                        edt_search.setTextSize(20);
                    }
                    if (imv_search.getVisibility() == View.VISIBLE) {
                        imv_search.setVisibility(View.GONE);
                    }
                } else {
                    edt_search.setGravity(Gravity.CENTER);
                    imv_search.setVisibility(View.VISIBLE);
                    edt_search.setTextSize(12);
                }
            }
        });
        btn_clear_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_search.setText("");
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        imv_search = findViewById(R.id.imv_search);
        edt_search = findViewById(R.id.edt_search);
        btn_clear_search = findViewById(R.id.btn_clear_search);
        btn_back = findViewById(R.id.btn_back);

    }
}
