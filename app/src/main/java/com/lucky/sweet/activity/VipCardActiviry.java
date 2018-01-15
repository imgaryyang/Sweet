package com.lucky.sweet.activity;

import android.os.Bundle;

import com.lucky.sweet.R;
import com.lucky.sweet.widgets.cardstack.CardStackView;
import com.lucky.sweet.widgets.cardstack.TestStackAdapter;

import java.util.ArrayList;

public class VipCardActiviry extends BaseActivity{

    private CardStackView cardStackView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_card_activiry);

        initView();

    }

    private void initView() {
        cardStackView = findViewById(R.id.stackview_vip_card);
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < 20;
             i++) {
            objects.add("");
        }

        final TestStackAdapter objectStackAdapter = new TestStackAdapter
                (this, objects);
        cardStackView.setAdapter(objectStackAdapter);
        cardStackView.setItemExpendListener(new CardStackView.ItemExpendListener() {
            @Override
            public void onItemExpend(boolean expend) {

            }
        });

    }
}
