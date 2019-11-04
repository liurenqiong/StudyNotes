package com.snail.framework.swagger.autoconfiguration;

import lombok.Data;

/**
 * @author snail
 * @create 2019/8/27.
 **/
@Data
public class SwaggerParam {

    private String name;

    private String description;

    private String defaultValue;

    private boolean required;
}
