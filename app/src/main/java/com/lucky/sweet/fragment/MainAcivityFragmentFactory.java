package com.lucky.sweet.fragment;


import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by c on 2017/12/13.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class MainAcivityFragmentFactory {
    private Context context;
    private static ImMeFragment mMeFragment = new ImMeFragment();
    private static ImStoreFragment mStoreFragment = new ImStoreFragment();
    private static ImCircleFragment mCircleFragment = new ImCircleFragment();

    public static final int EM = 0;
    public static final int STORE = 1;
    public static final int CIRCLE = 2;

//    public Fragment getFragment(int type) {
//        switch (type) {
//            case EM:
//                ImMeFragment.instantiate()
//                return mMeFragment;
//
//            break;
//            case STORE:
//
//                return mStoreFragment;
//
//            break;
//            case CIRCLE:
//
//                return mCircleFragment;
//
//            break;
//
//            default:
//                break;
//        }
//        return null;
//    }

}
