package com.tacomall.common.exceptionInterceptor.exception;

public class BizException extends RuntimeException {
    public BizException(String message) {
        super(message);
    }
}
