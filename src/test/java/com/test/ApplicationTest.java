package com.test;

import com.test.annotation.FruitFunction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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
}
