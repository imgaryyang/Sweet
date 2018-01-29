package com.lucky.sweet.entity;

import java.util.List;

/**
 * Created by chn on 2018/1/29.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class StoreDisplayInfo {
    private List<MerListBean> mer_list;

    public List<MerListBean> getMer_list() {
        return mer_list;
    }

    public void setMer_list(List<MerListBean> mer_list) {
        this.mer_list = mer_list;
    }

    public static class MerListBean {
        /**
         * mer_id : 5
         * mer_address : 辽宁省大连市旅顺口区旅顺盐场海鲜街45号（大连医科大学、大连外国语学院）
         * name : 老板
         * environment : null
         * surface : https://thethreestooges.cn/merchant/img/photo_mer/5/surface.png
         */

        private String mer_id;
        private String mer_address;
        private String name;
        private Object environment;
        private String surface;

        public String getMer_id() {
            return mer_id;
        }

        public void setMer_id(String mer_id) {
            this.mer_id = mer_id;
        }

        public String getMer_address() {
            return mer_address;
        }

        public void setMer_address(String mer_address) {
            this.mer_address = mer_address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getEnvironment() {
            return environment;
        }

        public void setEnvironment(Object environment) {
            this.environment = environment;
        }

        public String getSurface() {
            return surface;
        }

        public void setSurface(String surface) {
            this.surface = surface;
        }
    }
}
