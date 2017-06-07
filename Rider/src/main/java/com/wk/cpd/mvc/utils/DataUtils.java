package com.wk.cpd.mvc.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.wk.cpd.mvc.Constant;

/**
 * @description:数据初始化工具
 */
public class DataUtils {

    /**
     * @title: initMapFromProperties
     * @description: 将properties中的数据初始化成map
     * @param map
     * @param properties
     */
    public static void initMapFromProperties(Map<String, String> map, Resource resources) {

        Properties properties = getConfigProperties(resources);

        Map<String, String> data = new HashMap<String, String>();
        for (Object key : properties.keySet()) {
            String value = (String) properties.getProperty((String) key);
            data.put((String) key, value);
        }

        map.putAll(data);
    }

    /**
     * @title: getConfigProperties
     * @description: 读取配置文件
     * @param resources
     * @return
     */
    public static Properties getConfigProperties(Resource... resources) {
        Properties properties = new Properties();
        for (Resource resource : resources) {
            try {
                PropertiesLoaderUtils.fillProperties(properties, new EncodedResource(resource, Constant.CHARSET));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return properties;
    }

}
