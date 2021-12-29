package com.kapcb.framework.web.net;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/**
 * <a>Title: SkipSslHttpRequestFactory </a>
 * <a>Author: Kapcb <a>
 * <a>Description: SkipSslHttpRequestFactory <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/29 22:34
 * @since 1.0
 */
@Slf4j
public class SkipSslHttpRequestFactory extends SimpleClientHttpRequestFactory {

    @Override
    protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
        if (connection instanceof HttpsURLConnection) {
            prepareHttpsConnection((HttpsURLConnection) connection);
        }
        super.prepareConnection(connection, httpMethod);
    }

    private void prepareHttpsConnection(HttpsURLConnection connection) {
        connection.setHostnameVerifier(new SkipHostnameVerifier());

        try {
            connection.setSSLSocketFactory(getSSLContext().getSocketFactory());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            log.error("get ssl socket factory error, error message is : {}", e.getMessage());
        }

    }

    /**
     * 信任自签证书
     */
    private static SSLContext getSSLContext() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[]{new SkipX509TrustManager()}, new SecureRandom());
        return sslContext;
    }


    private static class SkipX509TrustManager implements X509TrustManager {

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

    }

}
