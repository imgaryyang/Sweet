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

    /**
     * item_list : [{"class_id":"43","name":"热菜","item":[{"item_id":"52","name":"馒头","photo":"\r\nsweet/person/portrait/chinn96@163.com.png","univalence":"2","stick_time":"19","description":"馒头，又称之为馍、蒸馍，中国特色传统面食之一，是一种用面粉发酵蒸成的食品，形圆而隆起","num":"0","discount":1},{"item_id":"51","name":"米饭","photo":"\r\nsweet/person/portrait/chinn96@163.com.png","univalence":"1","stick_time":"18","description":"米饭，是中国人日常饮食中的主角之一，中国南方主食。米饭可与五味调配，几乎可以供给全身所需营养","num":"0","discount":"1"},{"item_id":"53","name":"小米粥","photo":"\r\nsweet/person/portrait/chinn96@163.com.png","univalence":"3","stick_time":null,"description":"小米粥是以小米作为主要食材熬制而成的粥，口味清淡，清香味，具有简单易制","num":"0","discount":"3"},{"item_id":"60","name":"清蒸鲈鱼","photo":"11111","univalence":"1","stick_time":null,"description":"168","num":"0","discount":"1"},{"item_id":"61","name":"爆炒猪肝","photo":"11111","univalence":"2","stick_time":null,"description":"58","num":"0","discount":"2"},{"item_id":"62","name":"爆炒猪肝","photo":"11111","univalence":"3","stick_time":null,"description":"58","num":"0","discount":"3"},{"item_id":"111","name":"寿司","photo":"/storage/emulated/0/20180307_182418.jpg","univalence":"180","stick_time":null,"description":"","num":"0","discount":"180"}]},{"class_id":"44","name":"饮料","item":[{"item_id":"109","name":"海参","photo":"/storage/emulated/0/20180218_153034.jpg","univalence":"80","stick_time":null,"description":"鲜美不油腻","num":"0","discount":"80"},{"item_id":"110","name":"碳烤腰果","photo":"/storage/emulated/0/20180220_001618.jpg","univalence":"不油腻，很香","stick_time":null,"description":"35","num":"0","discount":"不油腻，很香"}]},{"class_id":"48","name":"主食","item":[{"item_id":"72","name":"炭烧腰果","photo":"11111","univalence":"3","stick_time":null,"description":"","num":"0","discount":"3"},{"item_id":"73","name":"红烧土豆","photo":"11111","univalence":"1.5","stick_time":null,"description":"","num":"0","discount":"1.5"}]}]
     * VIP_name : vip2
     * discount : 0.5
     */

    private String VIP_name;
    private String discount;
    private List<ItemListBean> item_list;

    public String getVIP_name() {
        return VIP_name;
    }

    public void setVIP_name(String VIP_name) {
        this.VIP_name = VIP_name;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public List<ItemListBean> getItem_list() {
        return item_list;
    }

    public void setItem_list(List<ItemListBean> item_list) {
        this.item_list = item_list;
    }

    public static class ItemListBean {
        /**
         * class_id : 43
         * name : 热菜
         * item : [{"item_id":"52","name":"馒头","photo":"\r\nsweet/person/portrait/chinn96@163.com.png","univalence":"2","stick_time":"19","description":"馒头，又称之为馍、蒸馍，中国特色传统面食之一，是一种用面粉发酵蒸成的食品，形圆而隆起","num":"0","discount":1},{"item_id":"51","name":"米饭","photo":"\r\nsweet/person/portrait/chinn96@163.com.png","univalence":"1","stick_time":"18","description":"米饭，是中国人日常饮食中的主角之一，中国南方主食。米饭可与五味调配，几乎可以供给全身所需营养","num":"0","discount":"1"},{"item_id":"53","name":"小米粥","photo":"\r\nsweet/person/portrait/chinn96@163.com.png","univalence":"3","stick_time":null,"description":"小米粥是以小米作为主要食材熬制而成的粥，口味清淡，清香味，具有简单易制","num":"0","discount":"3"},{"item_id":"60","name":"清蒸鲈鱼","photo":"11111","univalence":"1","stick_time":null,"description":"168","num":"0","discount":"1"},{"item_id":"61","name":"爆炒猪肝","photo":"11111","univalence":"2","stick_time":null,"description":"58","num":"0","discount":"2"},{"item_id":"62","name":"爆炒猪肝","photo":"11111","univalence":"3","stick_time":null,"description":"58","num":"0","discount":"3"},{"item_id":"111","name":"寿司","photo":"/storage/emulated/0/20180307_182418.jpg","univalence":"180","stick_time":null,"description":"","num":"0","discount":"180"}]
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
             * sweet/person/portrait/chinn96@163.com.png
             * univalence : 2
             * stick_time : 19
             * description : 馒头，又称之为馍、蒸馍，中国特色传统面食之一，是一种用面粉发酵蒸成的食品，形圆而隆起
             * num : 0
             * discount : 1
             */

            private String item_id;
            private String name;
            private String photo;
            private String univalence;
            private String stick_time;
            private String description;
            private String num;
            private String discount;

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

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }
        }
    }
}
