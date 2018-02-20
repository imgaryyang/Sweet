package com.lucky.sweet.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chn on 2018/2/20.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class CircleMainInfo {

    private List<CircleListBean> circle_list;

    public List<CircleListBean> getCircle_list() {
        return circle_list;
    }

    public void setCircle_list(List<CircleListBean> circle_list) {
        this.circle_list = circle_list;
    }

    public static class CircleListBean implements Serializable {
        /**
         * circle_id : 5_4
         * user_id : 5
         * mer_id : 1
         * content : 总该长大，总该试着去成熟，放弃一些曾经你以为不可分离的东西，即使这个过程漫长且难捱，当做睡前关上的灯。
         * create_time : 2018-02-13 14:10:52
         * nikcname : 阿元测试
         * exp : 500
         * relation_sign : 2
         * mer_name : 老板娘烤肉店
         * photo_url : []
         * comment : []
         */

        private String circle_id;
        private String user_id;
        private String mer_id;
        private String content;
        private String create_time;
        private String nikcname;
        private String exp;
        private String relation_sign;
        private String mer_name;
        private List<?> photo_url;
        private List<?> comment;

        public String getCircle_id() {
            return circle_id;
        }

        public void setCircle_id(String circle_id) {
            this.circle_id = circle_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getMer_id() {
            return mer_id;
        }

        public void setMer_id(String mer_id) {
            this.mer_id = mer_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getNikcname() {
            return nikcname;
        }

        public void setNikcname(String nikcname) {
            this.nikcname = nikcname;
        }

        public String getExp() {
            return exp;
        }

        public void setExp(String exp) {
            this.exp = exp;
        }

        public String getRelation_sign() {
            return relation_sign;
        }

        public void setRelation_sign(String relation_sign) {
            this.relation_sign = relation_sign;
        }

        public String getMer_name() {
            return mer_name;
        }

        public void setMer_name(String mer_name) {
            this.mer_name = mer_name;
        }

        public List<?> getPhoto_url() {
            return photo_url;
        }

        public void setPhoto_url(List<?> photo_url) {
            this.photo_url = photo_url;
        }

        public List<?> getComment() {
            return comment;
        }

        public void setComment(List<?> comment) {
            this.comment = comment;
        }
    }
}
