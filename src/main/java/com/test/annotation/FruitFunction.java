package com.test.annotation;

import org.springframework.stereotype.Component;

/**
 * Created by Rui on 2017/7/23.
 */
@Component
public class FruitFunction {

    @Fruit(name="apple")
    public void eatFruit(){
        System.out.println("I eat fruit !!!");
    }
}
