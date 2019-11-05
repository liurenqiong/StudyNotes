package com.snail.framework.log.aop;

import lombok.Data;

import java.io.Serializable;

@Data
public class OperatorLog implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private long endTime;
    private long requestTime;
    private String beanName;
    private String methodName;
    private String uri;
    private String remoteAddr;
    private String sessionId;
    private String curUser;
    private String method;
    private String params;
    private String result;
    

}
