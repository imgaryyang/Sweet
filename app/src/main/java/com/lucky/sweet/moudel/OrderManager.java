package com.lucky.sweet.moudel;

/**
 * Created by chn on 2017/12/23.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class OrderManager {

    public static String[] getDishesType() {
        return new String[]{"面食类", "盖饭", "寿司", "烧烤", "酒水", "凉菜", "小吃", "粥", "休闲"};
    }
    public static String[][] getDishesList(){
        return new String[][]{{"热干面", "臊子面", "烩面"},
                {"番茄鸡蛋", "红烧排骨", "农家小炒肉"},
                {"芝士", "丑小丫", "金枪鱼"}, {"羊肉串", "烤鸡翅", "烤羊排"}, {"长城干红", "燕京鲜啤", "青岛鲜啤"},
                {"拌粉丝", "大拌菜", "菠菜花生"}, {"小食组", "紫薯"},
                {"小米粥", "大米粥", "南瓜粥", "玉米粥", "紫米粥"}, {"儿童小汽车", "悠悠球", "熊大", " 熊二", "光头强"}
        };

    }
}
