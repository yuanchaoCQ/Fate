package com.wk.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.wk.cpd.mvc.inteceptor.LocalInterceptor;
import com.wk.cpd.mvc.inteceptor.LogInterceptor;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.wk.cpd.mvc.controller", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class }) })
public class MvcConfig extends WebMvcConfigurationSupport {

    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();

        mapping.setInterceptors(new Object[] { getLocalInterceptor(), getLogInterceptor() });
        return mapping;
    }

    /**
     * 获取{@link LocalInterceptor}实例
     * 
     * @return 返回{@link LocalInterceptor}实例
     */
    private LocalInterceptor getLocalInterceptor() {
        return localInterceptor;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(getLocalInterceptor()).addPathPatterns("/v1.0/feed");
        registry.addInterceptor(getLogInterceptor()).addPathPatterns("/v1.0/feed");
    }

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
    }

    /**
     * 获取{@link LogInterceptor}实例
     * 
     * @return 返回{@link LogInterceptor}实例
     */
    private LogInterceptor getLogInterceptor() {
        return logInterceptor;
    }
    
    private LogInterceptor logInterceptor = LogInterceptor.instance();

    private LocalInterceptor localInterceptor = LocalInterceptor.instance();
}
