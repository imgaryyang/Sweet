package com.lucky.sweet.activity;

import android.os.Bundle;
import android.view.View;

import com.lucky.sweet.R;

public class MainActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //收拾qqq
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View viewById = findViewById(R.id.contentLayout);
    }
}
