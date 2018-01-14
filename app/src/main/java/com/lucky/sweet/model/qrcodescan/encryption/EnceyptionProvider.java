package com.lucky.sweet.model.qrcodescan.encryption;

/**
 * Created by Administrator on 2017/12/14.
 */

public class EnceyptionProvider {
    private static String cdKey;
    public static String getcdKey() {
        return cdKey;
    }

    public static void setcdKey(String cdKey) {
        EnceyptionProvider.cdKey = cdKey;
    }
}
