package com.snail.framework.jdbc.multi.autoconfiguration;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author snail
 * @create 2019/9/18.
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.get();
    }

}
