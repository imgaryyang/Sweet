package com.lucky.sweet.entity;

import com.lucky.sweet.utility.OssUtils;

/**
 * Created by C on 2018/3/21.
 */

public class SearchFriendInfo {


    /**
     * nickname : 22阿元测试
     * photo : sweet/circle/a/20180321165609/3jpg
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
        if (!photo.contains("http")) {
            photo = OssUtils.getOSSExtranetPath(photo);
        }
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
