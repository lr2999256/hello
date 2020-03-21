package com.test.config;

import java.util.concurrent.Callable;

/**
 * @author : Rui
 * @date : 2019/8/7 17:40
 **/
public class ResultNotifyThread implements Callable<Object> {

    private int subDay;

    public ResultNotifyThread() {
    }

    @Override
    public Object call() throws Exception {
        int time =  (int)(1+Math.random()*10)*1000;
        System.out.println( Thread.currentThread().getName() + "  停留  :"+time);
        Thread.sleep(time);
        return null;
    }
}
