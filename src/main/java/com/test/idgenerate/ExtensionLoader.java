package com.test.idgenerate;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.*;

/**
 * 扩展加载
 * @author yuzhou
 * @date 2017年6月14日
 * @time 上午10:45:48
 *
 */
public class ExtensionLoader implements ApplicationContextAware {
    
    private static final String PREFIX = "META-INF/services/";
    
    private static ApplicationContext applicationContext;

    public static <T> T getSpringBean(String beanName, Class<T> clazz) {
        Object obj = applicationContext.getBean(beanName);
        if(null == obj) {
            return null;
        }
        return clazz.cast(obj);
    }
    
    public static <T> T getSpringBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static <T> Map<String, T> getSpringBeansOfType(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz);
    }
    
    public static <T> Map<String, T> getSpringBeansOfType(ApplicationContext applicationContext, Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz);
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        ExtensionLoader.applicationContext = applicationContext;
    }

    /**
     * JAVA SPI机制
     * 获取接口／抽象类的扩展实例，扩展文件在META-INF/services/ 下；文件格式：className
     * 
     * @param service
     * @return
     */
    public static <T> List<T> getExtensionServices(Class<T> service) {
        List<T> list = new ArrayList<>();
        ServiceLoader<T> loader = ServiceLoader.load(service);
        Iterator<T> iterator = loader.iterator();
        while (true) {
            if (iterator.hasNext()) {
                list.add(iterator.next());
            } else {
                break;
            }
        }
        return list;
    }
}
