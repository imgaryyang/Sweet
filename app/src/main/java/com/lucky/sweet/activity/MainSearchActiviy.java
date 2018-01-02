package com.lucky.sweet.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.lucky.sweet.R;

public class MainSearchActiviy extends AppCompatActivity {

    private ImageView imv_search;
    private EditText edt_search;

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
                if (imv_search.getVisibility()== View.VISIBLE) {
                    imv_search.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initView() {
        imv_search = findViewById(R.id.imv_search);
        edt_search = findViewById(R.id.edt_search);
    }
}
