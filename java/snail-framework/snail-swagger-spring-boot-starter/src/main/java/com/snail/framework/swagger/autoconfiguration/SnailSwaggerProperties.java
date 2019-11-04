package com.snail.framework.swagger.autoconfiguration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author snail
 * @create 2019/8/27.
 **/
@Data
@ConfigurationProperties(prefix = SnailSwaggerProperties.SNAIL_SWAGGER_PREFIX)
public class SnailSwaggerProperties {

    public final static String SNAIL_SWAGGER_PREFIX = "snail.swagger";

    private String title;

    private String description;

    private String serviceUrl;

    private String version = "1.0";

    private boolean enable;

    private List<SwaggerParam> swaggerHeaderParams = new ArrayList<>();

    private String swaggerUrl;
}
