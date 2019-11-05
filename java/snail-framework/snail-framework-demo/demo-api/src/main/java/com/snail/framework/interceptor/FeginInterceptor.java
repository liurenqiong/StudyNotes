package com.snail.framework.interceptor;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.snail.framework.demo.constant.HeaderParamKeyEnum;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @author snail
 * @date 2019/3/4
 */
public class FeginInterceptor implements RequestInterceptor{


    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(HeaderParamKeyEnum.app_id.getCode(), getHeaders(getHttpServletRequest()).get(HeaderParamKeyEnum.app_id.getCode()));
        requestTemplate.header(HeaderParamKeyEnum.order_no.getCode(), getHeaders(getHttpServletRequest()).get(HeaderParamKeyEnum.order_no.getCode()));
        requestTemplate.header(HeaderParamKeyEnum.timestamp.getCode(), getHeaders(getHttpServletRequest()).get(HeaderParamKeyEnum.timestamp.getCode()));
        requestTemplate.header(HeaderParamKeyEnum.version.getCode(), getHeaders(getHttpServletRequest()).get(HeaderParamKeyEnum.version.getCode()));
        requestTemplate.header(HeaderParamKeyEnum.global_token.getCode(), getHeaders(getHttpServletRequest()).get(HeaderParamKeyEnum.global_token.getCode()));       
    }


    private HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

}
