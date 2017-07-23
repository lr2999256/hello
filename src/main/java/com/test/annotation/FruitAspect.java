package com.test.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by Rui on 2017/7/23.
 */
@Component
@Aspect
@Order(-99)
public class FruitAspect {

    @Before("@annotation(fruit)")
    // 拦截被Fruit注解的方法；如果你需要拦截指定package指定规则名称的方法，可以使用表达式execution(...)，具体百度一下资料一大堆
    public void beforeTest(JoinPoint point, Fruit fruit) throws Throwable {
        System.out.println("beforeTest:" + fruit.name());
    }

    @Around("@annotation(fruit)")
    public void getFruit(ProceedingJoinPoint pjp, Fruit fruit) throws Throwable {
        String xx = fruit.name();
        System.out.println("=========================水果："+xx+"================");
        pjp.proceed();
    }
}
