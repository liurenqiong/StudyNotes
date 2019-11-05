package com.snail.framework.demo.constant;

/**
 * @author CR
 * @date 2019/2/25
 */
public enum HeaderParamKeyEnum {
	app_id("app_id","app_id"),
	order_no("order_no","order_no"),
    timestamp("timestamp","timestamp"),
    version("version","version"),
    global_token("global_token","global_token"),
    ;
    private String code;
    private String desc;

    HeaderParamKeyEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }

}
