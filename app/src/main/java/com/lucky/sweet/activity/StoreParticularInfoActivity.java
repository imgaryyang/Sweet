package com.lucky.sweet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.lucky.sweet.R;

/**
 * Created by c on 2017/12/13.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class StoreParticularInfoActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeinfo);

        getData();


    }

    private void getData() {
        Intent intent = getIntent();
        int shopid = intent.getIntExtra("shopid", 0);
        Toast.makeText(this, "点击店铺ID：" + shopid, Toast.LENGTH_SHORT).show();
    }
}
