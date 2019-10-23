package com.demo.springboot.vue.config;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *主配置文件
 */
@Configuration
@ComponentScan(basePackages = "com.demo.springboot.vue.**.view")
@ComponentScan(basePackages = "com.demo.springboot.vue.**.dao")
@ComponentScan(basePackages = "com.demo.springboot.vue.**.service")
public class RootConfig {

}
