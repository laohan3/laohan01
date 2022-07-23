package com.laohan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
   @Bean
   public Docket getDocket(){
      return new Docket(DocumentationType.SWAGGER_2)
              .apiInfo(apiInfo())//显示信息
              .groupName("老汉a")//要配置多个分组,创建多个Docket实例即可
              .select().apis(RequestHandlerSelectors.basePackage("com.swagger.swaggerTest.controller")).build();//swagger扫描的包
               //默认扫描全部

   }


   @Bean
   public Docket getDocket2(){
      return new Docket(DocumentationType.SWAGGER_2);


   }
   private ApiInfo apiInfo(){
      return new ApiInfoBuilder().title("老汉的Swagger")
              .description("暂无描述")
              .version("1.0x")
              .contact(new Contact("HanLao", "https://laohan.com", "884xxx939@qq.com"))
              .build();

   }

}
