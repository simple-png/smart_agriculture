package com.agriculture.common.exception;

/**
 * 农作物不存在异常
 */
public class CropExistErrorException extends BaseException {

    public CropExistErrorException() {
    }

    public CropExistErrorException(String msg) {
        super(msg);
    }

}
