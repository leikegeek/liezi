package org.liezi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 *
 * @Author: lake.lei
 * @Date: 2018/8/5 13:54
 * @Description:swagger配置
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {
    /**
     * @Author Lake
     * @Description swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     * @Date 2018/8/4 23:13
     * @Param []
     **/
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.liezi.modules"))
                .paths(PathSelectors.any())
                .build();
    }
    /***
     * @Author Lake
     * @Description //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
     * @Date 2018/8/4 23:12
     * @Param []
     **/
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("liezi")
                //创建人
                .contact(new Contact("lake.lei", "http://zhumang.top", ""))
                //版本号
                .version("1.0")
                //描述
                .description("1.新增&更新方法里面:createBy,createDt,updateBy,updateDt无需传值\n" +
                        "2.分页查询里面:current表示当前页码,size表示每页容纳的条数 int类型\n" +
                        "3.排序方法,ascs为正序排序传String类型数组,descs为倒序,传String类型数组")
                .build();
    }
}