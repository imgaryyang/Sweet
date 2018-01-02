package com.lucky.sweet.viewsexpand;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by c on 2017/12/6.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class AdViewPagerTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {

        //左边0~-90度,右边90~0度,
        //左边x 0~-width，右边x width~0；
        if (position < -1) {

        } else if (position <= 1) // a页滑动至b页 ； a页从 0.0 ~ -1 ；b页从1 ~ 0.0
        { // [-1,1]
            if (position < 0)//滑动中左边页面
            {
                page.setPivotX(page.getMeasuredWidth());
                page.setRotationY(position * 90);
            } else//滑动中右边页面
            {
                page.setPivotX(0);
                page.setRotationY(position * 90);
            }

        } else { // (1,+Infinity]

        }
    }
}
