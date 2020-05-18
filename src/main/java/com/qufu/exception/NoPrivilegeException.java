package com.qufu.exception;


/**
 * 无特权错误
 */
public class NoPrivilegeException extends BaseException {

    public NoPrivilegeException(int code, String msg) {
        super(code, msg);
    }

}
