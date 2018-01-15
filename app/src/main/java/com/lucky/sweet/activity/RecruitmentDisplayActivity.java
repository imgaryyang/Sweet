package com.lucky.sweet.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lucky.sweet.R;
import com.lucky.sweet.views.PictureDisplayLayout;
import com.lucky.sweet.widgets.ToolBar;

import java.util.ArrayList;

public class RecruitmentDisplayActivity extends BaseActivity {

    private PictureDisplayLayout photo_show;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruitment_display);
        ToolBar toolBar = new ToolBar(this);
        toolBar.setColorNewBar(getResources().getColor(R.color.white), 0);

        initView();

        initEvent();
    }

    private void initView() {
        photo_show = findViewById(R.id.ll_cover_photo_show);
    
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add("");
        }
        photo_show.initDatas(strings);

    }

    private void initEvent() {
        findViewById(R.id.btn_back_store).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                goDownAnim();
            }
        });

    }


}
