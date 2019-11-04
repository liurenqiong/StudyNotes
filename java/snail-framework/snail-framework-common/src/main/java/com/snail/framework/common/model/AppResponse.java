package com.snail.framework.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author snail
 * @create 2019/4/20.
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppResponse<T extends AbsAppResponse> {

    private String code;

    private String msg;

    private T data;

    public AppResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
