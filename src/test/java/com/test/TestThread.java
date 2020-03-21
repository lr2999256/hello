package com.test;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @author : Rui
 * @date : 2019/11/14 17:39
 **/
public class TestThread implements Callable<Object> {
    @Override
    public Object call() throws Exception {
        Thread.sleep(100);
        System.out.println("aaa"+new Date());
        return null;
    }
}
