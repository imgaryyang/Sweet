package com.lucky.sweet.entity;

import java.util.List;

/**
 * Created by chn on 2018/1/29.
 * (╯°□°）╯︵ ┻━┻ MMP好气啊！
 * (╯‵□′)╯︵┻━┻ 老子怒掀桌子！
 * ┻━┻︵╰(‵□′)╯︵┻━┻老子双手掀桌！
 * ┬─┬﻿ ノ( ゜-゜ノ) 算了，我不生气了！日子还得过老老实实敲吧~
 */

public class StoreDisplaySearchEntity {

    /**
     * liist : {"circle":["软件学院","经管"],"classify":["聚餐宴请 ","KTV "],"order":["最新收录","消费最低(未写)"]}
     */

    private LiistBean liist;

    public LiistBean getLiist() {
        return liist;
    }

    public void setLiist(LiistBean liist) {
        this.liist = liist;
    }

    public static class LiistBean {
        private List<String> circle;
        private List<String> classify;
        private List<String> order;

        public List<String> getCircle() {
            return circle;
        }

        public void setCircle(List<String> circle) {
            this.circle = circle;
        }

        public List<String> getClassify() {
            return classify;
        }

        public void setClassify(List<String> classify) {
            this.classify = classify;
        }

        public List<String> getOrder() {
            return order;
        }

        public void setOrder(List<String> order) {
            this.order = order;
        }
    }
}
