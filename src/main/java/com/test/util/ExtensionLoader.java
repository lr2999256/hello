package com.test.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 扩展加载
 * @author yuzhou
 * @date 2017年6月14日
 * @time 上午10:45:48
 *
 */
@Component
public class ExtensionLoader implements ApplicationContextAware {
    
    private static ApplicationContext applicationContext;

    public static <T> T getSpringBean(String beanName, Class<T> clazz) {
        Object obj = applicationContext.getBean(beanName);
        if(null == obj) {
            return null;
        }
        return clazz.cast(obj);
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        ExtensionLoader.applicationContext = applicationContext;
    }
}
