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
    private static ImMeFragment mMeFragment;
    private static ImStoreFragment mStoreFragment;
    private static ImCircleFragment mCircleFragment;

    private static MainAcivityFragmentFactory factory;

    static {

        mMeFragment = new ImMeFragment();

        mStoreFragment = new ImStoreFragment();

        mCircleFragment = new ImCircleFragment();

    }

    public static final int ME = 0;
    public static final int STORE = 1;
    public static final int CIRCLE = 2;

    private MainAcivityFragmentFactory() {

    }

    public static MainAcivityFragmentFactory getSingleFactoryInstance() {
        if (factory == null) {
            synchronized (MainAcivityFragmentFactory.class) {
                if (factory == null) {
                    factory = new MainAcivityFragmentFactory();
                }
            }
        }
        return factory;
    }

    public Fragment getFragment(int type) {

        switch (type) {
            case ME:

                return mMeFragment;


            case STORE:

                return mStoreFragment;


            case CIRCLE:

                return mCircleFragment;


            default:
                break;
        }
        return null;
    }

}
