package com.kapcb.framework.web.net;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * <a>Title: SkipHostnameVerifier </a>
 * <a>Author: Kapcb <a>
 * <a>Description: SkipHostnameVerifier <a>
 * <p>
 * 配置 RestTemplate 跳过 Hostname 检查
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/29 22:33
 * @since 1.0
 */
public class SkipHostnameVerifier implements HostnameVerifier {

    @Override
    public boolean verify(String hostname, SSLSession session) {
        return true;
    }

}
