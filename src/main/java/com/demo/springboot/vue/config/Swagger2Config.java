package com.demo.springboot.vue.config;

import com.demo.springboot.vue.base.view.PackResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.ModelReference;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

/*Swagger2的配置类*/
@Configuration
@EnableSwagger2
public class Swagger2Config {
  @Bean
  public Docket customDocket() {

    final List<ResponseMessage> globalResponses = Arrays.asList(
            new ResponseMessageBuilder()
                    .code(200)
                    .message("OK")
                    .build());

    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo()).useDefaultResponseMessages(false)
            //统一说明响应码
            .globalResponseMessage(RequestMethod.GET, globalResponses) //统一对响应码进行说明
            .globalResponseMessage(RequestMethod.POST, globalResponses)
            .globalResponseMessage(RequestMethod.DELETE, globalResponses)
            .globalResponseMessage(RequestMethod.PATCH, globalResponses)
            .select()
            //哪包报下的Controller要生成API文档，可以指定多个，不能使用Ant Path
            .apis(RequestHandlerSelectors.basePackage("com.demo.springboot.vue.sample.view"))
            .apis(RequestHandlerSelectors.basePackage("com.demo.springboot.vue.sample.view"))
            .paths(PathSelectors.any())
            .build();


  }

  private ApiInfo apiInfo() {
    //显示在UI界面中的联系信息
    Contact contact = new Contact("老王", "", "");
    return new ApiInfoBuilder()
            .title("API接口")
            .description("API接口")
            .contact(contact)
            .version("1.0")
            .build();
  }

}

