package com.lucky.sweet.entity;

/**
 * Created by chn on 2017/12/19.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class StoreDetailedInfo {

    /**
     * merinfo : {"lat":"121.533662","lon":"38.862153","mer_id":"1","name":"老板娘烤肉店","classify":"聚餐宴请 ","introduce":"我们不一样","phone":"15141433601","address":"辽宁省大连市旅顺口区旅顺盐场海鲜街45号（大连医科大学、大连外国语学院）","business_hours":"8：00-21：30","status":"营业中","reserve":"true","surface":"https://thethreestooges.cn/merchant/img/photo_mer/1/surface.jpg","Thumbnail_one":"https://thethreestooges.cn/merchant/img/photo_mer/1/Thumbnail_one.jpg","Thumbnail_three":"https://thethreestooges.cn/merchant/img/photo_mer/1/Thumbnail_three.jpg","Thumbnail_two":"https://thethreestooges.cn/merchant/img/photo_mer/1/Thumbnail_two.jpg"}
     */

    private MerinfoBean merinfo;

    public MerinfoBean getMerinfo() {
        return merinfo;
    }

    public void setMerinfo(MerinfoBean merinfo) {
        this.merinfo = merinfo;
    }

    public static class MerinfoBean {
        /**
         * lat : 121.533662
         * lon : 38.862153
         * mer_id : 1
         * name : 老板娘烤肉店
         * classify : 聚餐宴请
         * introduce : 我们不一样
         * phone : 15141433601
         * address : 辽宁省大连市旅顺口区旅顺盐场海鲜街45号（大连医科大学、大连外国语学院）
         * business_hours : 8：00-21：30
         * status : 营业中
         * reserve : true
         * surface : https://thethreestooges.cn/merchant/img/photo_mer/1/surface.jpg
         * Thumbnail_one : https://thethreestooges.cn/merchant/img/photo_mer/1/Thumbnail_one.jpg
         * Thumbnail_three : https://thethreestooges.cn/merchant/img/photo_mer/1/Thumbnail_three.jpg
         * Thumbnail_two : https://thethreestooges.cn/merchant/img/photo_mer/1/Thumbnail_two.jpg
         */

        private String lat;
        private String lon;
        private String mer_id;
        private String name;
        private String classify;
        private String introduce;
        private String phone;
        private String address;
        private String business_hours;
        private String status;
        private String reserve;
        private String surface;
        private String Thumbnail_one;
        private String Thumbnail_three;
        private String Thumbnail_two;

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getMer_id() {
            return mer_id;
        }

        public void setMer_id(String mer_id) {
            this.mer_id = mer_id;
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

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBusiness_hours() {
            return business_hours;
        }

        public void setBusiness_hours(String business_hours) {
            this.business_hours = business_hours;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getReserve() {
            return reserve;
        }

        public void setReserve(String reserve) {
            this.reserve = reserve;
        }

        public String getSurface() {
            return surface;
        }

        public void setSurface(String surface) {
            this.surface = surface;
        }

        public String getThumbnail_one() {
            return Thumbnail_one;
        }

        public void setThumbnail_one(String Thumbnail_one) {
            this.Thumbnail_one = Thumbnail_one;
        }

        public String getThumbnail_three() {
            return Thumbnail_three;
        }

        public void setThumbnail_three(String Thumbnail_three) {
            this.Thumbnail_three = Thumbnail_three;
        }

        public String getThumbnail_two() {
            return Thumbnail_two;
        }

        public void setThumbnail_two(String Thumbnail_two) {
            this.Thumbnail_two = Thumbnail_two;
        }
    }
}
