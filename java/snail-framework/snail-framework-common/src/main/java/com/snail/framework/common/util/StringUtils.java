package com.snail.framework.common.util;

/**
 * @author snail
 * @create 2019/8/28.
 **/
public class StringUtils {

    /**
     * 为空判断
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 非空判断
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
