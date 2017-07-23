package com.test.proxy;

import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Rui on 2017/7/23.
 */
@Component
public class ProxyCalculate implements InvocationHandler {

    private Object proxied = null;

    public ProxyCalculate(){

    }
    public ProxyCalculate(Object proxied){
        this.proxied  = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxied, args);
    }
}
