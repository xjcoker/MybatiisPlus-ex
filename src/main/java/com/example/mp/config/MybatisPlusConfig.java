package com.example.mp.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.incrementer.DB2KeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.H2KeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author xiangjun.song
 * @date 2020/3/9
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.example.mp.**.mapper")
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
         paginationInterceptor.setOverflow(true);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
         paginationInterceptor.setLimit(100);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }

    //配置主键生成策略方式1
    @Bean
    public MybatisPlusPropertiesCustomizer plusPropertiesCustomizer() {
    //        new DB2KeyGenerator();
    //        new OracleKeyGenerator();
        return plusProperties -> plusProperties.getGlobalConfig().getDbConfig().setKeyGenerator(new H2KeyGenerator());
    }
    //配置主键生成策略方式2
    //    @Bean
    //    public IKeyGenerator keyGenerator() {
    //        return new H2KeyGenerator();
    //    }

    //配置主键生成策略方式3
    //        @Bean
    //        public GlobalConfig globalConfig() {
    //            GlobalConfig conf = new GlobalConfig();
    //            conf.setDbConfig(new GlobalConfig.DbConfig().setKeyGenerator(new H2KeyGenerator()));
    //            return conf;
    //        }

    //自定义id生成器
    @Bean
    public IdentifierGenerator idGenerator() {
        return new CustomIdGenerator();
    }

    //    @Bean
    //    public Jackson2ObjectMapperBuilderCustomizer customizer(){
    //        return builder -> builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    //    }
}
