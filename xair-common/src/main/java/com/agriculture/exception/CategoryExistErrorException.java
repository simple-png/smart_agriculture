package com.agriculture.exception;

/**
 * 种类不存在异常
 */
public class CategoryExistErrorException extends BaseException {

    public CategoryExistErrorException() {
    }

    public CategoryExistErrorException(String msg) {
        super(msg);
    }

}
