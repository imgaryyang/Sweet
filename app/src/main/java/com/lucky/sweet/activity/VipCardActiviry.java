package com.lucky.sweet.activity;

import android.os.Bundle;

import com.lucky.sweet.R;
import com.lucky.sweet.entity.VipCardInfo;
import com.lucky.sweet.widgets.Title;
import com.lucky.sweet.widgets.cardstack.CardStackView;
import com.lucky.sweet.widgets.cardstack.TestStackAdapter;

import java.util.ArrayList;

public class VipCardActiviry extends BaseActivity {

    private CardStackView cardStackView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_card_activiry);

        initTitle();

        initView();

    }

    private void initTitle() {
        Title title = findViewById(R.id.title);
        title.setTitleNameStr("我的卡包");
        Title.ButtonInfo buttonLeft = new Title.ButtonInfo(true, Title
                .BUTTON_LEFT, R.drawable.selector_btn_titleback, null);
        title.setOnTitleButtonClickListener(new Title
                .OnTitleButtonClickListener() {
            @Override
            public void onClick(int id, Title.ButtonViewHolder viewHolder) {
                switch (id) {
                    case Title.BUTTON_LEFT:
                        finish();
                        break;
                }
            }
        });
        title.mSetButtonInfo(buttonLeft);
    }

    private void initView() {

        cardStackView = findViewById(R.id.stackview_vip_card);
        ArrayList<VipCardInfo> objects = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            objects.add(new VipCardInfo());
        }

        final TestStackAdapter objectStackAdapter = new TestStackAdapter(this, objects);
        cardStackView.setAdapter(objectStackAdapter);
        cardStackView.setItemExpendListener(new CardStackView.ItemExpendListener() {
            @Override
            public void onItemExpend(boolean expend) {

            }
        });

    }
}
