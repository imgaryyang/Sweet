package com.lucky.sweet.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chn on 2018/2/19.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class JoinInRoomMenu implements Serializable {

    /**
     * mer_id : 1
     * item : [{"item_id":"51","num":"1"},{"item_id":"53","num":"1"},{"item_id":"60","num":"1"},{"item_id":"61","num":"1"},{"item_id":"62","num":"1"}]
     */

    private String mer_id;
    private List<ItemBean> item;

    public String getMer_id() {
        return mer_id;
    }

    public void setMer_id(String mer_id) {
        this.mer_id = mer_id;
    }

    public List<ItemBean> getItem() {
        return item;
    }

    public void setItem(List<ItemBean> item) {
        this.item = item;
    }

    public static class ItemBean implements Serializable  {
        /**
         * item_id : 51
         * num : 1
         */

        private String item_id;
        private String num;

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
