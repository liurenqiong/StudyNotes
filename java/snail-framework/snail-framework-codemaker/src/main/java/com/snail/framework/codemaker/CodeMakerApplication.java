package com.snail.framework.codemaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.snail.framework.swagger.autoconfiguration.EnableSnailSwagger;

/**
 * @author snail
 * @create 2019/8/27.
 **/
@EnableSnailSwagger
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class , FreeMarkerAutoConfiguration.class})
public class CodeMakerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeMakerApplication.class , args);
    }
}

