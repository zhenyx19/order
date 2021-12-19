package com.example.orderspringboot.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.orderspringboot.mapper")
public class MybatisPlusConfig {
}
