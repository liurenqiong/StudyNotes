package com.snail.framework.swagger.autoconfiguration;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author snail
 * @create 2019/8/27.
 **/
@Slf4j
@Configuration
@EnableSwaggerBootstrapUI
@EnableSwagger2
@EnableConfigurationProperties(SnailSwaggerProperties.class)
public class SnailSwaggerAutoConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/swagger/**").addResourceLocations("classpath:/static/swagger/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
    }

    @Bean
    public Docket createRestApi(SnailSwaggerProperties properties) {
        log.info("swagger开始初始化");
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, customerResponseMessage())
                .globalResponseMessage(RequestMethod.POST,customerResponseMessage())
                .apiInfo(apiInfo(properties.getTitle() , properties.getDescription() , properties.getServiceUrl() , properties.getVersion()))
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))           //加了ApiOperation注解的方法，生成接口文档
                .paths(PathSelectors.any())
                .build()
                .enable(properties.isEnable())
                .globalOperationParameters(parameters(properties.getSwaggerHeaderParams()))
                ;
    }

    /**
     * 自定义返回信息
     *
     * @param
     * @return
     */
    private List<ResponseMessage> customerResponseMessage() {
        List<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder()
                .code(400)
                .message("请求出错")
                .responseModel(new ModelRef("由于语法格式有误，服务器无法理解此请求。不作修改，客户程序就无法重复此请求。 "))
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(401)
                .message("服务未授权")
                .responseModel(new ModelRef("访问由于凭据无效被拒绝。"))
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(403)
                .message("服务器上文件或目录拒绝访问")
                .responseModel(new ModelRef("服务器上文件或目录拒绝访问"))
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(404)
                .message("服务器找不到您所请求的文件或脚本。请检查URL 以确保路径正确")
                .responseModel(new ModelRef("服务器找不到您所请求的文件或脚本。请检查URL 以确保路径正确"))
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(405)
                .message("请确保为所请求的资源设置了正确的 MIME 类型")
                .responseModel(new ModelRef("请确保为所请求的资源设置了正确的 MIME 类型"))
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(406)
                .message("请求所标识的资源只能生成内容特征为“不可接受”的响应实体")
                .responseModel(new ModelRef("请求所标识的资源只能生成内容特征为"))
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(407)
                .message("请登录到代理服务器，然后重试")
                .responseModel(new ModelRef("请登录到代理服务器，然后重试"))
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(412)
                .message("未满足前提条件")
                .responseModel(new ModelRef("未满足前提条件"))
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(414)
                .message("Request-URL太长，服务器拒绝服务此请求")
                .responseModel(new ModelRef("Request-URL太长，服务器拒绝服务此请求"))
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(500)
                .message("服务器的内部错误，服务器不能执行此请求。请稍后重试此请求")
                .responseModel(new ModelRef("服务器的内部错误，服务器不能执行此请求。请稍后重试此请求"))
                .build());
        return responseMessages;
    }

    private ApiInfo apiInfo(String title , String description , String serviceUrl , String version) {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(serviceUrl)
                .version(version)
                .build();
    }

    private List<Parameter> parameters(List<SwaggerParam> swaggerParams) {
        ParameterBuilder headerParamBuilder = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();

        if(!swaggerParams.isEmpty()) {
            for(SwaggerParam swaggerParam : swaggerParams) {
                pars.add( headerParamBuilder.name(swaggerParam.getName())
                        .description(swaggerParam.getDescription())
                        .modelRef(new ModelRef("string"))
                        .defaultValue(swaggerParam.getDefaultValue())
                        .parameterType("header")
                        .required(swaggerParam.isRequired()).build());
            }
        }
        return pars;
    }

}
