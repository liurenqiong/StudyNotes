package com.snail.framework.common.constant;

/**
 * @author snail
 * @create 2019-05-03 15:52
 */
public enum ESystemMsg {

    SUCCESS("000000", "交易成功"),

    ERROR("999999", "系统异常"),

    ;

    private String code;

    private String msg;

    ESystemMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
