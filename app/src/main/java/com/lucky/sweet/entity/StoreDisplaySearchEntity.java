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
     * liist : {"select":[{"name":"万达商业区","list":[{"classify":"聚餐宴请 "},{"classify":"真人CS"},{"classify":"KTV "},{"classify":"轰趴馆"}]},{"name":"旅顺商业区","list":[{"classify":"聚餐宴请 "},{"classify":"KTV "},{"classify":"棋牌室"},{"classify":"采摘/农家乐"}]},{"name":"软件学院","list":[{"classify":"KTV "}]},{"name":"hahaha","list":[{"classify":"酒吧"},{"classify":"更多休闲娱乐"},{"classify":"棋牌室"}]}],"order":["最新收录","消费最低(未写)"]}
     */

    private LiistBean liist;

    public LiistBean getLiist() {
        return liist;
    }

    public void setLiist(LiistBean liist) {
        this.liist = liist;
    }

    public static class LiistBean {
        private List<SelectBean> select;
        private List<String> order;

        public List<SelectBean> getSelect() {
            return select;
        }

        public void setSelect(List<SelectBean> select) {
            this.select = select;
        }

        public List<String> getOrder() {
            return order;
        }

        public void setOrder(List<String> order) {
            this.order = order;
        }

        public static class SelectBean {
            /**
             * name : 万达商业区
             * list : [{"classify":"聚餐宴请 "},{"classify":"真人CS"},{"classify":"KTV "},{"classify":"轰趴馆"}]
             */

            private String name;
            private List<ListBean> list;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * classify : 聚餐宴请
                 */

                private String classify;

                public String getClassify() {
                    return classify;
                }

                public void setClassify(String classify) {
                    this.classify = classify;
                }
            }
        }
    }
}
