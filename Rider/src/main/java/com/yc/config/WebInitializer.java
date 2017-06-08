package com.yc.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;


public class WebInitializer implements WebApplicationInitializer {
    //实现该接口可以应用启动时进行加载
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //以注解的方式加载配置
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        //加载spring的配置
        rootContext.register(AppConfig.class);
        rootContext.setServletContext(servletContext);
        rootContext.refresh();
        //web.xml里的contextloaderlistener
        servletContext.addListener(new ContextLoaderListener(rootContext));
        
        //设置字符集过滤器
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        FilterRegistration filterRegistration = servletContext.addFilter("characterEncodingFilter", characterEncodingFilter);
        filterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");
        
     // sc.addListener("org.springframework.web.util.Log4jConfigListener");
    }

}
