package com.snail.framework.log.autoconfiguration;

import com.snail.framework.log.aop.LoggerAdvice;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author snail
 * @create 2019/10/4.
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({LoggerAdvice.class})
@Documented
public @interface EnableSnailLogAdvice {
}
