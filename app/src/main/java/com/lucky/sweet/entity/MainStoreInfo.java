package com.lucky.sweet.entity;

import java.util.List;

/**
 * Created by c on 2017/12/10.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class MainStoreInfo {


    /**
     * advertising : {"advertising_one":"https://thethreestooges.cn/merchant/img/photo_mer/1/surface.jpg","advertising_two":"https://thethreestooges.cn/merchant/img/photo_mer/1/surface.jpg","advertising_three":"https://thethreestooges.cn/merchant/img/photo_mer/1/surface.jpg","advertising_four":"https://thethreestooges.cn/merchant/img/photo_mer/1/surface.jpg"}
     * classify : [{"name":"美食","info":[{"shop_id":"1","pho_url":"https://thethreestooges.cn/merchant/img/photo_mer/1/surface.jpg","name":"老板娘烤肉店","classify":"聚餐宴请 ","pho_type":"美食","comment":"100","distance":"1"},{"shop_id":"2","pho_url":"https://thethreestooges.cn/merchant/img/photo_mer/2/surface.jpg","name":"张亮麻辣烫","classify":"聚餐宴请 ","pho_type":"美食","comment":"100","distance":"1"},{"shop_id":"3","pho_url":"https://thethreestooges.cn/merchant/img/photo_mer/3/surface.jpg","name":"玛西雅比萨炸鸡","classify":"聚餐宴请 ","pho_type":"美食","comment":"100","distance":"1"},{"shop_id":"4","pho_url":"https://thethreestooges.cn/merchant/img/photo_mer/4/surface.jpg","name":"连顺吉饭店","classify":"聚餐宴请 ","pho_type":"美食","comment":"100","distance":"1"},{"shop_id":"5","pho_url":"https://thethreestooges.cn/merchant/img/photo_mer/5/surface.jpg","name":"莲惠饭店","classify":"聚餐宴请 ","pho_type":"美食","comment":"100","distance":"1"}]},{"name":"娱乐","info":[{"shop_id":"6","pho_url":"https://thethreestooges.cn/merchant/img/photo_mer/1/surface.jpg","name":"老板娘ktv","classify":"休闲娱乐 ","pho_type":"娱乐","comment":"100","distance":"1"},{"shop_id":"7","pho_url":"https://thethreestooges.cn/merchant/img/photo_mer/2/surface.jpg","name":"乐音城音乐广场","classify":"休闲娱乐 ","pho_type":"娱乐","comment":"100","distance":"1"},{"shop_id":"8","pho_url":"https://thethreestooges.cn/merchant/img/photo_mer/3/surface.jpg","name":"弘扬量贩KTV","classify":"休闲娱乐 ","pho_type":"娱乐","comment":"100","distance":"1"},{"shop_id":"9","pho_url":"https://thethreestooges.cn/merchant/img/photo_mer/4/surface.jpg","name":"金苹果量贩ktv","classify":"休闲娱乐 ","pho_type":"娱乐","comment":"100","distance":"1"},{"shop_id":"10","pho_url":"https://thethreestooges.cn/merchant/img/photo_mer/5/surface.jpg","name":"梦幻又一ktv","classify":"休闲娱乐 ","pho_type":"娱乐","comment":"100","distance":"1"}]}]
     */

    private AdvertisingBean advertising;
    private List<ClassifyBean> classify;

    public AdvertisingBean getAdvertising() {
        return advertising;
    }

    public void setAdvertising(AdvertisingBean advertising) {
        this.advertising = advertising;
    }

    public List<ClassifyBean> getClassify() {
        return classify;
    }

    public void setClassify(List<ClassifyBean> classify) {
        this.classify = classify;
    }

    public static class AdvertisingBean {
        /**
         * advertising_one : https://thethreestooges.cn/merchant/img/photo_mer/1/surface.jpg
         * advertising_two : https://thethreestooges.cn/merchant/img/photo_mer/1/surface.jpg
         * advertising_three : https://thethreestooges.cn/merchant/img/photo_mer/1/surface.jpg
         * advertising_four : https://thethreestooges.cn/merchant/img/photo_mer/1/surface.jpg
         */

        private String advertising_one;
        private String advertising_two;
        private String advertising_three;
        private String advertising_four;

        public String getAdvertising_one() {
            return advertising_one;
        }

        public void setAdvertising_one(String advertising_one) {
            this.advertising_one = advertising_one;
        }

        public String getAdvertising_two() {
            return advertising_two;
        }

        public void setAdvertising_two(String advertising_two) {
            this.advertising_two = advertising_two;
        }

        public String getAdvertising_three() {
            return advertising_three;
        }

        public void setAdvertising_three(String advertising_three) {
            this.advertising_three = advertising_three;
        }

        public String getAdvertising_four() {
            return advertising_four;
        }

        public void setAdvertising_four(String advertising_four) {
            this.advertising_four = advertising_four;
        }
    }

    public static class ClassifyBean {
        /**
         * name : 美食
         * info : [{"shop_id":"1","pho_url":"https://thethreestooges.cn/merchant/img/photo_mer/1/surface.jpg","name":"老板娘烤肉店","classify":"聚餐宴请 ","pho_type":"美食","comment":"100","distance":"1"},{"shop_id":"2","pho_url":"https://thethreestooges.cn/merchant/img/photo_mer/2/surface.jpg","name":"张亮麻辣烫","classify":"聚餐宴请 ","pho_type":"美食","comment":"100","distance":"1"},{"shop_id":"3","pho_url":"https://thethreestooges.cn/merchant/img/photo_mer/3/surface.jpg","name":"玛西雅比萨炸鸡","classify":"聚餐宴请 ","pho_type":"美食","comment":"100","distance":"1"},{"shop_id":"4","pho_url":"https://thethreestooges.cn/merchant/img/photo_mer/4/surface.jpg","name":"连顺吉饭店","classify":"聚餐宴请 ","pho_type":"美食","comment":"100","distance":"1"},{"shop_id":"5","pho_url":"https://thethreestooges.cn/merchant/img/photo_mer/5/surface.jpg","name":"莲惠饭店","classify":"聚餐宴请 ","pho_type":"美食","comment":"100","distance":"1"}]
         */

        private String name;
        private List<InfoBean> info;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * shop_id : 1
             * pho_url : https://thethreestooges.cn/merchant/img/photo_mer/1/surface.jpg
             * name : 老板娘烤肉店
             * classify : 聚餐宴请
             * pho_type : 美食
             * comment : 100
             * distance : 1
             */

            private String shop_id;
            private String pho_url;
            private String name;
            private String classify;
            private String pho_type;
            private String comment;
            private String distance;

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String getPho_url() {
                return pho_url;
            }

            public void setPho_url(String pho_url) {
                this.pho_url = pho_url;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getClassify() {
                return classify;
            }

            public void setClassify(String classify) {
                this.classify = classify;
            }

            public String getPho_type() {
                return pho_type;
            }

            public void setPho_type(String pho_type) {
                this.pho_type = pho_type;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }
        }
    }
}
