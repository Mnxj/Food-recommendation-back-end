package com.qufu.exception;


/**
 * 无效参数
 */

public class InvalidParamException extends BaseException {

    public InvalidParamException(int code, String msg) {
        super(code, msg);
    }

}
