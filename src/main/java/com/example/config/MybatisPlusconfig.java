package com.example.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//通过@mapperScan注解指定要变成实现类的接口所在的包，然后包下面的所有接口在编译之后都会生成相应的实现类
@Configuration
@EnableTransactionManagement
@MapperScan("com.example.mapper")
public class MybatisPlusconfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {

        /*
        * paginationInterceptor :分页工具
        *
        * */
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
}

