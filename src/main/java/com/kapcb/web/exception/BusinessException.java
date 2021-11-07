package com.kapcb.web.exception;

import com.kapcb.web.model.result.IResultCode;

/**
 * <a>Title: BusinessException </a>
 * <a>Author: Kapcb <a>
 * <a>Description: BusinessException <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/7 13:41
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -1451544713774371776L;

    private IResultCode resultCode;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(IResultCode resultCode) {
        super(resultCode.msg());
        this.resultCode = resultCode;
    }

    public BusinessException(Throwable throwable) {
        super(throwable);
    }

    public BusinessException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public IResultCode getResultCode() {
        return this.resultCode;
    }
}
