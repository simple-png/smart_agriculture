package com.agriculture.context;

public class UserAgentContext {

    private static ThreadLocal<String> userAgentThreadLocal = new ThreadLocal<>();

    public static String getUserAgent() {
        return userAgentThreadLocal.get();
    }

    public static void setUserAgent(String userAgent) {
        userAgentThreadLocal.set(userAgent);
    }

    public static void clearUserAgent() {
        userAgentThreadLocal.remove();
    }
}

