package com.lucky.sweet.entity;

import java.util.List;

/**
 * Created by chn on 2018/2/20.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class CircleDetail {

    private List<CommentBean> comment;

    public List<CommentBean> getComment() {
        return comment;
    }

    public void setComment(List<CommentBean> comment) {
        this.comment = comment;
    }

    public static class CommentBean {
        /**
         * circle_id : 5_10
         * user_id : 5
         * mer_id : 1
         * content : 看谁不爽就赏他一句：井底之蛙，却不知道如果这一生能够把那一小片天空看个清楚，你也比别人看的远。
         * create_time : 2018-02-14 23:35:01
         * nikcname : 阿元测试
         */

        private String circle_id;
        private String user_id;
        private String mer_id;
        private String content;
        private String create_time;
        private String nikcname;

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
    }
}
