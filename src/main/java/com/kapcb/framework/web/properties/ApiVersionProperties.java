package com.kapcb.framework.web.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <a>Title: ApiVersionProperties </a>
 * <a>Author: Kapcb <a>
 * <a>Description: ApiVersionProperties <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/29 23:54
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "kapcb.api.version")
public class ApiVersionProperties {

    private Boolean enable = true;

    private double minimumVersion;

    private String versionPlaceholder = "{version}";

}
