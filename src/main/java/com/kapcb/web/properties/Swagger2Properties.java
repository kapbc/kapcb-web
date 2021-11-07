package com.kapcb.web.properties;

import lombok.Data;

/**
 * <a>Title: Swagger2Properties </a>
 * <a>Author: Kapcb <a>
 * <a>Description: Swagger2Properties <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/7 13:12
 */
@Data
public class Swagger2Properties {

    private String title;

    private String description;

    private String contactName;

    private String contactUrl;

    private String contactEmail;

    private String version;

    private String basePackage;

    private boolean enableSecurity;
}
