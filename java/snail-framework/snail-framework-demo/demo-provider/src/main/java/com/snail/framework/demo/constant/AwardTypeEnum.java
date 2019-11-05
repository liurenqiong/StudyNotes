package com.snail.framework.demo.constant;

public enum AwardTypeEnum {

    RATE_VOUCHER("1", "加息券"),
    ENTITY("2", "实体物品"),
    TNANKS("99", "谢谢惠顾")
        ;

    private String code;
    private String msg;

    private AwardTypeEnum(String code, String msg) {
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
