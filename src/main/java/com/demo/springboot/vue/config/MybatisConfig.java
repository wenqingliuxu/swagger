package com.demo.springboot.vue.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.demo.springboot.vue.*.dao")
public  class MybatisConfig {

}
