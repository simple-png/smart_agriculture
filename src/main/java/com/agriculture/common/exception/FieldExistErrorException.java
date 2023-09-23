package com.agriculture.common.exception;

/**
 * 田地不存在异常
 */
public class FieldExistErrorException extends BaseException {

    public FieldExistErrorException() {
    }

    public FieldExistErrorException(String msg) {
        super(msg);
    }

}
