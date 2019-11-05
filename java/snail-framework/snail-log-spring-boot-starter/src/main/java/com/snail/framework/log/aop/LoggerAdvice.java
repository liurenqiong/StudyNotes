package com.snail.framework.log.aop;

import com.alibaba.fastjson.JSON;
import com.snail.framework.log.annotation.LoggerManage;
import com.snail.framework.log.util.JsonTool;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@Aspect
@Component
public class LoggerAdvice implements Serializable {


    private ThreadLocal<OperatorLog> tlocal = new ThreadLocal<OperatorLog>();

    @Before("@annotation(loggerManage)")
    public void addBeforeLogger(JoinPoint joinPoint, LoggerManage loggerManage) throws Throwable {
        try {
            long beginTime = System.currentTimeMillis();
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            String uri = request.getRequestURI();

            String beanName = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            String remoteAddr = getIpAddr(request);
            String sessionId = request.getSession().getId();
            String user = (String) request.getSession().getAttribute("user");
            // 记录下请求内容
            log.info("**************************执行【{}】接口请求开始********************************",
                    loggerManage.description());
            log.info("URL={}", request.getRequestURL().toString());
            Enumeration<String> reqHeadInfos = request.getHeaderNames();//获取所有的请求头
            StringBuffer headerString = new StringBuffer("headers:");
            while (reqHeadInfos.hasMoreElements()) {
                String headName = reqHeadInfos.nextElement();
                String headValue = request.getHeader(headName);//根据请求头的名字获取对应的请求头的值
                headerString.append(headName).append(":").append(headValue).append("|");
            }
            log.info(new String(headerString));
            log.info("HTTP_METHOD={}", request.getMethod());
            log.info("IP={}", remoteAddr);
            log.info("CLASS_METHOD={}",
                    joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            String method = request.getMethod();
            String params = "";
            if ("POST".equals(method)) {
                Object[] paramsArray = joinPoint.getArgs();
                List<Object> result = removeNoSerializable(paramsArray);
                params = JSON.toJSONString(result);
            } else {
                Map<?, ?> paramsMap = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
                params = paramsMap.toString();
            }
            log.info("ARGS={}", params);

            OperatorLog optLog = new OperatorLog();
            optLog.setBeanName(beanName);
            optLog.setCurUser(user);
            optLog.setMethodName(methodName);
            optLog.setParams(params != null ? params : "");
            optLog.setRemoteAddr(remoteAddr);
            optLog.setSessionId(sessionId);
            optLog.setUri(uri);
            optLog.setRequestTime(beginTime);
            tlocal.set(optLog);
        } catch (Exception e) {
            log.error("***操作请求日志记录失败doBefore()***", e);
        }
    }

    @AfterReturning(returning = "ret",pointcut ="within(com.snail..*) && @annotation(loggerManage)")
    public void addAfterReturningLogger(Object ret, LoggerManage loggerManage) throws Throwable {
        try {
            // 处理完请求，返回内容
            OperatorLog optLog = tlocal.get();
            optLog.setResult(JsonTool.getString(ret));
            long beginTime = optLog.getRequestTime();
            long requestTime = System.currentTimeMillis() - beginTime;
            optLog.setRequestTime(requestTime);
            //输出服务信息
            log.info("RESPONSE={}", JsonTool.getString(ret));
            log.info("请求耗时：{}" , optLog.getRequestTime()+"ms");
            log.info("**************************执行【{}】接口请求结束********************************",loggerManage.description());
        } catch (Exception e) {
            log.error("***操作请求日志记录失败doAfterReturning()***", e);
        }
        log.info("执行 " + loggerManage.description() + " 结束");
    }

    @AfterThrowing(pointcut = "within(com.snail..*) && @annotation(loggerManage)", throwing = "ex")
    public void addAfterThrowingLogger(JoinPoint joinPoint, LoggerManage loggerManage, Exception ex) {
        log.error("执行 " + loggerManage.description() + " 异常", ex);
    }

//	private String parseParames(Object[] parames) {
//		if (null == parames || parames.length <= 0) {
//			return "";
//		}
//		StringBuffer param = new StringBuffer("传入参数[{}] ");
//		for (Object obj : parames) {
//			param.append(ToStringBuilder.reflectionToString(obj)).append("  ");
//		}
//		return param.toString();
//	}

    /**
     * 获取登录用户远程主机ip地址
     *
     * @param request
     * @return
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 请求参数拼装
     *
     * @param paramsArray
     * @return
     */
    private String argsArrayToString(Object[] paramsArray) {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0) {
            params = Arrays.stream(paramsArray).map(o -> JsonTool.getString(o) + " ").collect(Collectors.joining());
        }
        return params.trim();
    }

    private List<Object> removeNoSerializable(Object[] args) {
        List<Object> logArgs = Arrays.stream(args)
                .filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse)))
                .collect(Collectors.toList());
        return logArgs;
    }
}

