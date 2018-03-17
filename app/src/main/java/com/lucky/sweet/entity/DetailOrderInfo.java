package com.lucky.sweet.entity;

/**
 * Created by C on 2018/3/17.
 */

public class DetailOrderInfo {

    /**
     * mer_id : 4
     * create_time : 2018-03-15 18:25:57
     * money : null
     * trolley_list : {}
     * type_sign : 3
     */

    private String mer_id;
    private String create_time;
    private Object money;
    private String trolley_list;
    private String type_sign;

    public String getMer_id() {
        return mer_id;
    }

    public void setMer_id(String mer_id) {
        this.mer_id = mer_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Object getMoney() {
        return money;
    }

    public void setMoney(Object money) {
        this.money = money;
    }

    public String getTrolley_list() {
        return trolley_list;
    }

    public void setTrolley_list(String trolley_list) {
        this.trolley_list = trolley_list;
    }

    public String getType_sign() {
        return type_sign;
    }

    @Override
    public String toString() {
        return "DetailOrderInfo{" +
                "mer_id='" + mer_id + '\'' +
                ", create_time='" + create_time + '\'' +
                ", money=" + money +
                ", trolley_list='" + trolley_list + '\'' +
                ", type_sign='" + type_sign + '\'' +
                '}';
    }

    public void setType_sign(String type_sign) {
        this.type_sign = type_sign;
    }
}
