package com.lucky.sweet.entity;

import java.util.List;

/**
 * Created by chn on 2018/2/7.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class ShopCarEntity {
    private List<TrolleyInfoBean> trolley_info;

    public List<TrolleyInfoBean> getTrolley_info() {
        return trolley_info;
    }

    public void setTrolley_info(List<TrolleyInfoBean> trolley_info) {
        this.trolley_info = trolley_info;
    }

    public static class TrolleyInfoBean {
        /**
         * class_id : 43
         * name : 饮料
         * item : [{"item_id":"52","name":"馒头","photo":"\r\nsweet/person/portrait/chinn96@163.com.png","univalence":"2","stick_time":"19","description":"馒头，又称之为馍、蒸馍，中国特色传统面食之一，是一种用面粉发酵蒸成的食品，形圆而隆起"},{"item_id":"51","name":"米饭","photo":"\r\nsweet/person/portrait/chinn96@163.com.png","univalence":"1","stick_time":"18","description":"米饭，是中国人日常饮食中的主角之一，中国南方主食。米饭可与五味调配，几乎可以供给全身所需营养"},{"item_id":"53","name":"小米粥","photo":"\r\nsweet/person/portrait/chinn96@163.com.png","univalence":"3","stick_time":null,"description":"小米粥是以小米作为主要食材熬制而成的粥，口味清淡，清香味，具有简单易制"},{"item_id":"60","name":"清蒸鲈鱼","photo":"11111","univalence":"1","stick_time":null,"description":"168"},{"item_id":"61","name":"爆炒猪肝","photo":"11111","univalence":"2","stick_time":null,"description":"58"},{"item_id":"62","name":"爆炒猪肝","photo":"11111","univalence":"3","stick_time":null,"description":"58"}]
         */

        private String class_id;
        private String name;
        private List<ItemBean> item;

        public String getClass_id() {
            return class_id;
        }

        public void setClass_id(String class_id) {
            this.class_id = class_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ItemBean> getItem() {
            return item;
        }

        public void setItem(List<ItemBean> item) {
            this.item = item;
        }

        public static class ItemBean {
            /**
             * item_id : 52
             * name : 馒头
             * photo :
             sweet/person/portrait/chinn96@163.com.png
             * univalence : 2
             * stick_time : 19
             * description : 馒头，又称之为馍、蒸馍，中国特色传统面食之一，是一种用面粉发酵蒸成的食品，形圆而隆起
             */

            private String item_id;
            private String name;
            private String photo;
            private String univalence;
            private String stick_time;
            private String description;

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getUnivalence() {
                return univalence;
            }

            public void setUnivalence(String univalence) {
                this.univalence = univalence;
            }

            public String getStick_time() {
                return stick_time;
            }

            public void setStick_time(String stick_time) {
                this.stick_time = stick_time;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }
    }
}
