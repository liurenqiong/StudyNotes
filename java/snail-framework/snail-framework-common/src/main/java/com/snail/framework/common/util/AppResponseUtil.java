package com.snail.framework.common.util;

import com.snail.framework.common.constant.ESystemMsg;
import com.snail.framework.common.model.AbsAppResponse;
import com.snail.framework.common.model.AppResponse;

/**
 * @author snail
 * @create 2019/9/2.
 **/
public class AppResponseUtil {

   public static  <T extends AbsAppResponse> AppResponse<T> response(String code , String message , T response) {
        AppResponse<T> appResponse = new AppResponse<>();
        appResponse.setCode(code);
        appResponse.setMsg(message);
        appResponse.setData(response);
        return appResponse;
    }

   public static  <T extends AbsAppResponse> AppResponse<T> responseSuccess(T response) {
       AppResponse<T> appResponse = new AppResponse<>();
        appResponse.setCode(ESystemMsg.SUCCESS.getCode());
        appResponse.setMsg(ESystemMsg.SUCCESS.getMsg());
        appResponse.setData(response);
        return appResponse;
   }

    public static  <T extends AbsAppResponse> AppResponse<T> responseSysError (T response) {
        AppResponse<T> appResponse = new AppResponse<>();
        appResponse.setCode(ESystemMsg.ERROR.getCode());
        appResponse.setMsg(ESystemMsg.ERROR.getMsg());
        appResponse.setData(response);
        return appResponse;
    }
}
