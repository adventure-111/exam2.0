package cn.cuit.exam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
@ComponentScan(basePackages = {"cn.cuit.exam.controller"})
public class SwaggerConfig {
    @Bean
    public Docket customDocket() {return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()); }

    private ApiInfo apiInfo() {
        Contact contact = new Contact(null, null, null);
//        Contact contact = new Contact("adventure", "http://baidu.com","2715150056@qq.com");
        return new ApiInfoBuilder()
                .title("高等院校考试计划安排系统API接口")
                .description("API接口描述")
                .contact(contact)
                .version("0.0.1")
                .build();
    }
}
