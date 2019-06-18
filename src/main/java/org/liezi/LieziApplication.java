package org.liezi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author: lake.lei
 * @date: 2018/8/13 21:01
 * @description:
 */
@SpringBootApplication
@ComponentScan(basePackages = {"org.liezi.config", "org.liezi.modules.*.controller",
        "org.liezi.modules.*.service", "org.liezi.modules.*.dao", "org.liezi.filter",
        "org.liezi.quartz","org.liezi.quartz.task","org.liezi.common.utils",
        "org.liezi.common.aspect","org.liezi.datasources.*","org.liezi.modules.system.shiro",
        "top.zhumang.crypto.common"})
public class LieziApplication {
    /**
     * @param args
     * @author: lake.lei
     * @date: 2019/3/1 17:13
     * @return: void
     */
    public static void main(String[] args) {
        SpringApplication.run(LieziApplication.class, args);
        System.out.println("http://127.0.0.1:8080/swagger-ui.html");
    }

}
