package com.lucky.sweet.entity;

import java.util.List;

/**
 * Created by C on 2018/3/12.
 */

public class FlowPeople {

    private List<AttentListBean> attent_list;

    public List<AttentListBean> getAttent_list() {
        return attent_list;
    }

    public void setAttent_list(List<AttentListBean> attent_list) {
        this.attent_list = attent_list;
    }

    public static class AttentListBean {
        /**
         * user_id : 4
         * nickname : 大外只有一个浩南
         */

        private String user_id;
        private String nickname;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
