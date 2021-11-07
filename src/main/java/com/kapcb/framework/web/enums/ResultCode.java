package com.kapcb.framework.web.enums;

import com.kapcb.framework.web.model.result.IResultCode;

/**
 * <a>Title: ResultCode </a>
 * <a>Author: Kapcb <a>
 * <a>Description: ResultCode <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/7 13:40
 */
public enum ResultCode implements IResultCode {

    SUCCESS("success", 200, "request process success"),
    FAILED("fail", 501, "request process fail"),
    VALIDATE_PARAM_FAIL("request param error", 401, "request param error"),
    UNAUTHORIZED("please login", 402, "un login or token expired"),
    FORBIDDEN("no resource permission", 403, "no resource access permission"),
    LOGIN_FAIL("username or password error", 405, "login username or password error");

    private String msg;

    private int code;

    private String description;

    ResultCode(String msg, int code, String description) {
        this.msg = msg;
        this.code = code;
        this.description = description;
    }

    @Override
    public String msg() {
        return this.msg;
    }

    @Override
    public int code() {
        return this.code;
    }
}
