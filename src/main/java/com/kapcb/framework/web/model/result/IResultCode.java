package com.kapcb.framework.web.model.result;

/**
 * <a>Title: IResultCode </a>
 * <a>Author: Kapcb <a>
 * <a>Description: IResultCode <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/7 13:39
 */
public interface IResultCode {

    /**
     * message
     *
     * @return String
     */
    String msg();

    /**
     * error code
     *
     * @return int
     */
    int code();
}
