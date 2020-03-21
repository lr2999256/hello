package com.test.bean;

/**
 * @author : Rui
 * @Date : 2019/1/16
 * @Time : 17:42
 **/
public class ChildA extends AbstractA {

    public void testB(){
        super.abc = "你好";
        this.testA();
    }
}
