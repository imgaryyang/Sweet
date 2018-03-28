package com.lucky.sweet.entity;

import java.util.List;

/**
 * Created by C on 2018/3/21.
 */

public class SearchFriendInfo {

    /**
     * nickname_list : [{"nickname":"aaaa","user":"a","id":"5"}]
     * user_list : {"nickname":"aaaa","photo":"sweet/person/portrait/a.png"}
     */

    private UserListBean user_list;
    private List<NicknameListBean> nickname_list;

    public UserListBean getUser_list() {
        return user_list;
    }

    public void setUser_list(UserListBean user_list) {
        this.user_list = user_list;
    }

    public List<NicknameListBean> getNickname_list() {
        return nickname_list;
    }

    public void setNickname_list(List<NicknameListBean> nickname_list) {
        this.nickname_list = nickname_list;
    }

    public static class UserListBean {
        /**
         * nickname : aaaa
         * photo : sweet/person/portrait/a.png
         */

        private String nickname;
        private String photo;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }

    public static class NicknameListBean {
        /**
         * nickname : aaaa
         * user : a
         * id : 5
         */

        private String nickname;
        private String user;
        private String id;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
