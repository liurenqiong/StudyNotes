package com.snail.framework.demo.exception;

import com.snail.framework.demo.constant.DemoErrorMsg;

/**
 * @author snail
 * @create 2019/9/3.
 **/
public class DemoException extends RuntimeException {

    private String code ;

    private String msg;

    public DemoException(DemoErrorMsg msg) {
        this.code = msg.getCode();
        this.msg = msg.getMsg();
    }

    public DemoException(String code , String msg) {
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
