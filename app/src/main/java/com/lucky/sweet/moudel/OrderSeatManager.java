package com.lucky.sweet.moudel;

/**
 * Created by chn on 2017/12/23.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class OrderSeatManager {
    public static String[] getDate() {
        return new String[]{"1", "2", "3", "4", "5", "6"};
    }

    public static String[][] getTime() {
        return new String[][]{
                {
                        "1", "2", "3", "4", "5", "6"
                },
                {
                        "?", "?"
                },
                {
                        "aaa", "bbbb", "cccc", "dddd", "eee"
                },
                {
                        "aaaaa", "cccc"
                },
                {
                        "1", "2", "3", "4", "5", "6"
                },
                {
                        "?", "?"
                }
        };
    }
}
