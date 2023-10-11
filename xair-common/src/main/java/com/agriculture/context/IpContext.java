package com.agriculture.context;

import lombok.Getter;

public class IpContext {
    @Getter
    private static String ipAddress;

    public static void setIpAddress(String ip) {
        ipAddress = ip;
    }

}
