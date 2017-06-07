package com.wk.cpd.mvc.listener;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.http.nio.reactor.IOReactorException;
import org.springframework.core.io.ClassPathResource;

import com.wk.cpd.mvc.utils.DataUtils;
import com.wk.cpd.mvc.utils.http.HttpNIOPoolManager;
import com.wk.cpd.mvc.utils.http.WKHttpUtils;

/**
 * @description: 上下文监听
 */
public class InitIntanceListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            WKHttpUtils.initClilent();
            HttpNIOPoolManager.getInstance().initPool();
            Properties properties = DataUtils.getConfigProperties(new ClassPathResource("const.properties"),
                    new ClassPathResource("dataResource.properties"));

        } catch (IOReactorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        ;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            WKHttpUtils.closeClient();
            HttpNIOPoolManager.getInstance().destroy();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
