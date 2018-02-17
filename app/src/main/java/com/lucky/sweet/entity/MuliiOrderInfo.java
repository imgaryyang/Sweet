package com.lucky.sweet.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chn on 2018/2/17.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class MuliiOrderInfo {

    /**
     * type : 操作类型
     * name : 用户姓名
     * do : true加菜/flase减菜
     * des : 88/菜品ID
     */

    private String type;
    private String name;
    @SerializedName("do")
    private boolean doX;
    private int des;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDoX() {
        return doX;
    }

    public void setDoX(boolean doX) {
        this.doX = doX;
    }

    public int getDes() {
        return des;
    }

    public void setDes(int des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "MuliiOrderInfo{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", doX=" + doX +
                ", des=" + des +
                '}';
    }
}
