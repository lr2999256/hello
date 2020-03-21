package com.test.test;

/**
 * @author : Rui
 * @date : 2019/3/30 11:45
 **/
public class Apple implements Cloneable{

    private int count;

    public Apple(){
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public Object clone() {
        Apple apple = null;
        try{
            //浅复制
            apple = (Apple)super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return apple;
    }
}
