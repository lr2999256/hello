package com.test;

import com.test.annotation.FruitFunction;
import com.test.proxy.Calculate;
import com.test.proxy.ProxyCalculate;
import com.test.util.ExtensionLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.lang.reflect.Proxy;

/**
 * Created by Rui on 2017/7/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Resource
    FruitFunction fruitFunction;

    @Test
    public void test(){
        fruitFunction.eatFruit();
    }

    @Test
    public void testProxy() throws Exception{

        String calClazz = "com.test.proxy.OtherCalculate";
        Class calculateClazz = Class.forName(calClazz);

        //将首字母转成小写
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append(Character.toLowerCase(calculateClazz.getSimpleName().charAt(0)));
        stringBuilder.append(calculateClazz.getSimpleName().substring(1));
        String beanName = stringBuilder.toString();

        //获取到Spring容器中的对象
        Object instance = ExtensionLoader.getSpringBean(beanName, calculateClazz);

        Calculate proxy = (Calculate) Proxy.newProxyInstance(
                Calculate.class.getClassLoader(),new Class[]{Calculate.class}, new ProxyCalculate(instance));
        int xx = proxy.add(10,20);
        System.out.println("============================="+xx);
    }
}
