package com.mark.Config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){
        //DocumentationType.OAS_30是Swagger实例的接口文档版本，因为使用的是Swagger3，所以选择用 OAS_30
        return new Docket(DocumentationType.OAS_30)
                // 文档信息配置
                .apiInfo(apiInfo())
                // 配置扫描的接口
                .select()
                //为当前包下controller生成API文档,配置扫描哪里的接口
                .apis(RequestHandlerSelectors.basePackage("com.mark.Controller"))
                //为有@Api注解的Controller生成API文档
                //.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //为有@ApiOperation注解的方法生成API文档
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 过滤请求
                .paths(PathSelectors.any())
                .build()
                // 设置是否启动Swagger，默认为true（不写即可），关闭后Swagger就不生效了
                .enable(true);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Mark Rest API")// 文档标题
                .description("Mark")// 文档基本描述
                // 联系人信息
                .contact(new Contact("Mark","https://127.0.0.1:8081","Mark_mwl@qq.com"))
                .version("1.0")
                .build();
    }
}
