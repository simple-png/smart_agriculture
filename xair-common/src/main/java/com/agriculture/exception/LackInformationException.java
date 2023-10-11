package com.agriculture.exception;

/**
 * 缺少信息异常
 */
public class LackInformationException extends BaseException {

    public LackInformationException() {
    }

    public LackInformationException(String msg) {
        super(msg);
    }

}
