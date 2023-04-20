package com.siants.wiki.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//@ComponentScan(basePackages = {"com.siants", "com.test"})
@ComponentScan(basePackages = {"com.siants"})
@SpringBootApplication
@MapperScan(basePackages = {"com.siants.wiki.mapper"})
@Slf4j
@EnableCaching
@EnableScheduling
@EnableAsync
public class WikiApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WikiApplication.class);
        Environment env = app.run(args).getEnvironment();
        log.info("启动成功！！");
        log.info("地址：\thttp://127.0.0.1:{}", env.getProperty("server.port"));

    }

}
