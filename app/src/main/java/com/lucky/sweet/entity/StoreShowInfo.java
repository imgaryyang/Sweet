package com.lucky.sweet.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by c on 2017/12/10.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class StoreShowInfo {

    @SerializedName("休闲娱乐")
    private List<?> recreation;
    @SerializedName("测试使用")
    private List<?> testUse;
    @SerializedName("美食")
    private List<foodBean> food;

    public List<?> getRecreation() {
        return recreation;
    }

    public void setRecreation(List<?> recreation) {
        this.recreation = recreation;
    }

    public List<?> getTestUse() {
        return testUse;
    }

    public void setTestUse(List<?> testUse) {
        this.testUse = testUse;
    }

    public List<foodBean> getFood() {
        return food;
    }

    public void setFood(List<foodBean> food) {
        this.food = food;
    }

    public static class foodBean {
        /**
         * user_id : 1
         * pho_url : https://thethreestooges.cn/merchant/img/photo_mer/1/surface.jpg
         */

        private String user_id;
        private String pho_url;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getPho_url() {
            return pho_url;
        }

        public void setPho_url(String pho_url) {
            this.pho_url = pho_url;
        }
    }
}
