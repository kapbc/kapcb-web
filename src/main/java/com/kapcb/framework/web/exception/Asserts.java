package com.kapcb.framework.web.exception;

import com.kapcb.framework.common.result.IResultCode;
import lombok.experimental.UtilityClass;

/**
 * <a>Title: Asserts </a>
 * <a>Author: Kapcb <a>
 * <a>Description: Asserts <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/7 14:04
 */
@UtilityClass
public class Asserts {

    public static void failed(String msg) {
        throw new BusinessException(msg);
    }

    public static void failed(IResultCode resultCode) {
        throw new BusinessException(resultCode);
    }

}
