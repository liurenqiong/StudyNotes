package com.snail.framework.jdbc.multi.autoconfiguration;

import com.snail.framework.jdbc.multi.aspect.DynamicDataSourceAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author snail
 * @create 2019/8/26.
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({SnailMultiJDBCAutoConfiguration.class, DynamicDataSourceAspect.class})
@Documented
public @interface EnableSnailMultiJDBC {

}
