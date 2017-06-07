package com.wk.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.wk.cpd.mvc.Constant;
import com.wk.cpd.mvc.listener.InitIntanceListener;

/**
 * 请注释掉web.xml配置后使用
 */
@Order(1)
public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(javax.servlet.ServletContext sc) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class); //初始化所有的bean
        rootContext.setServletContext(sc);
        rootContext.refresh();
        sc.addListener(new ContextLoaderListener(rootContext));
        sc.addListener(new InitIntanceListener());

        // 2、springmvc上下文
        AnnotationConfigWebApplicationContext springMvcContext = new AnnotationConfigWebApplicationContext();
        springMvcContext.register(MvcConfig.class);
        // 3、DispatcherServlet
        DispatcherServlet dispatcherServlet = new DispatcherServlet(springMvcContext);
        ServletRegistration.Dynamic dynamic = sc.addServlet("dispatcherServlet", dispatcherServlet);
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("/");

        // 4、CharacterEncodingFilter
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding(Constant.CHARSET);
        FilterRegistration filterRegistration = sc.addFilter("characterEncodingFilter", characterEncodingFilter);
        filterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/");

        // sc.addListener("org.springframework.web.util.Log4jConfigListener");

    }
}
