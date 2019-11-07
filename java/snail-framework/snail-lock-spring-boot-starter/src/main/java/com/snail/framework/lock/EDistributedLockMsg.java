package com.snail.framework.lock;

/**
 * @author snail
 * @create 2019/9/3.
 **/
public enum  EDistributedLockMsg {

    TRY_LOCK_TIME_OUT("LOCK_10001" , "服务器繁忙，请稍后再试"),

    TRY_LOCK_FAIL("LOCK_10002" , "服务器繁忙，请稍后再试!"),

    ;

    private String code;

    private String msg;

    EDistributedLockMsg(String code, String msg) {
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
