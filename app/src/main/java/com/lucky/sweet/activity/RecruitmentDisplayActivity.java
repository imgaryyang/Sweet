package com.lucky.sweet.activity;

import android.os.Bundle;
import android.view.View;

import com.lucky.sweet.R;
import com.lucky.sweet.service.CommunicationService;
import com.lucky.sweet.views.PictureDisplayLayout;
import com.lucky.sweet.views.TitleIndicatorView;
import com.lucky.sweet.widgets.ToolBar;

import java.util.ArrayList;

public class RecruitmentDisplayActivity extends BaseActivity {

    private PictureDisplayLayout photo_show;
    private TitleIndicatorView titie_search;
    private String[] title={"菜品" ,"环境","其他"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruitment_display);
        ToolBar toolBar = new ToolBar(this);
        toolBar.setColorNewBar(getResources().getColor(R.color.white), 0);

        initView();

        initEvent();
    }

    @Override
    void onServiceBind(CommunicationService.MyBinder myBinder) {

    }

    private void initView() {
        photo_show = findViewById(R.id.ll_cover_photo_show);
        titie_search = findViewById(R.id.titie_search);
        titie_search.initializationData(title);
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
