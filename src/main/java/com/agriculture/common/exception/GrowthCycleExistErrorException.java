package com.agriculture.common.exception;

/**
 * 生长周期不存在异常
 */
public class GrowthCycleExistErrorException extends BaseException {

    public GrowthCycleExistErrorException() {
    }

    public GrowthCycleExistErrorException(String msg) {
        super(msg);
    }

}
