package com.agriculture.context;

import lombok.Getter;

public class IpContext {
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void setCurrentIp(String ip) {
        threadLocal.set(ip);
    }

    public static String getCurrentIp() {
        return threadLocal.get();
    }

    public static void removeCurrentIp() {
        threadLocal.remove();
    }

}
