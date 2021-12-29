package com.kapcb.framework.web.configuration;

import com.kapcb.framework.web.net.SkipSslHttpRequestFactory;
import com.kapcb.framework.web.properties.RestProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * <a>Title: RestConfiguration </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RestConfiguration <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/29 22:31
 * @since 1.0
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(value = {RestProperties.class})
@ConditionalOnProperty(prefix = "kapcb.rest.enable", havingValue = "true", matchIfMissing = false)
public class RestConfiguration {

    @Resource
    private RestProperties restProperties;

    @Bean("restTemplate")
    @ConditionalOnMissingBean(value = {RestTemplate.class})
    public RestTemplate restTemplate() {
        SkipSslHttpRequestFactory factory = new SkipSslHttpRequestFactory();

        factory.setReadTimeout(restProperties.getReadTimeOut());
        factory.setConnectTimeout(restProperties.getConnectTimeOut());

        if (restProperties.getEnableProxy()) {
            String proxyHostName = restProperties.getProxyHostName();
            int port = restProperties.getPort();
            log.info("enable rest template's proxy for : {}, port number : {}", proxyHostName, port);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHostName, port));
            factory.setProxy(proxy);
        }

        return new RestTemplate(factory);
    }

}
