package com.wanyu.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by samsung on 2017/11/29.
 */
@Configuration
@AutoConfigureAfter(MybatisConfig.class)//先执行MybatisConfig
public class MybatisMapperConfig {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapper=new MapperScannerConfigurer();
        mapper.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapper.setBasePackage("com.wanyu.mapper");
        return mapper;
    }
}
