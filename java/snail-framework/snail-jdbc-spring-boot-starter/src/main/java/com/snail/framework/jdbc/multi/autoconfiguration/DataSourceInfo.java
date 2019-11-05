package com.snail.framework.jdbc.multi.autoconfiguration;

import lombok.Data;

/**
 * @author snail
 * @create 2019/9/18.
 **/
@Data
class DataSourceInfo {

    private String driverClassName;//: com.mysql.jdbc.Driver
    private String url;//: 
    private String username;//:;// snail
    private String password;//: "sewewtwer"
    private String dataSourceName;//: master
    private Integer master;
}
