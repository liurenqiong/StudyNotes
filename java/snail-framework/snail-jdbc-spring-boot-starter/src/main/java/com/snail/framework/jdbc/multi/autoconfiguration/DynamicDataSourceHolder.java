package com.snail.framework.jdbc.multi.autoconfiguration;

/**
 * @author snail
 * @create 2019/9/18.
 **/
public class DynamicDataSourceHolder {

    private static ThreadLocal<String> datasourceLocal = new ThreadLocal<>();

    public static String get() {
        return datasourceLocal.get();
    }

    public static void clear() {
        datasourceLocal.remove();
    }

    public static void set(String key) {
        datasourceLocal.set(key);
    }
}
