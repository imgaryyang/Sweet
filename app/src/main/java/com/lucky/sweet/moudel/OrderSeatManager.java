package com.lucky.sweet.moudel;

import java.util.ArrayList;
import java.util.List;

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

    public static List<String> getPeopleNum() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.add("5");
        strings.add("6");
        strings.add("7");
        strings.add("8");
        strings.add("9");
        strings.add("10");
        strings.add("11");
        strings.add("12");
        strings.add("13");
        strings.add("14");
        strings.add("15");

        return strings;
    }
}
