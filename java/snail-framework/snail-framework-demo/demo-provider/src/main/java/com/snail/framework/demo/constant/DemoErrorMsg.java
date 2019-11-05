package com.snail.framework.demo.constant;

/**
 * @author snail
 * @create 2019/9/3.
 **/
public enum  DemoErrorMsg {

    BAD_REQUEST_COLUMN("100000" , "请求参数{0},{1}"),
    UPLOAD_ERROR("200008","上传文件异常"),
   
    ;

    private String code;
    private String msg;

    private DemoErrorMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
