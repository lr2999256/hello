package com.test.proxy;

import org.springframework.stereotype.Service;

/**
 * Created by Rui on 2017/7/23.
 */
@Service
public class MyCalculate implements Calculate{

    @Override
    public int add(int a, int b) {
        return a+b+100;
    }
}
