package com.test.test;

import java.util.List;

/**
 * @author : Rui
 * @date : 2019/3/30 11:43
 **/
public class ProductService implements Runnable {

    private List<Apple> list;

    public ProductService(List<Apple> list){
        this.list = list;
    }

    @Override
    public void run() {
        synchronized (list) {
            if (list.size() > 10) {
                System.out.println("product full apple " + list.size());
                try {

                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }else{
                list.add(new Apple());
                System.out.println("product  apple " + list.size());
                    list.notifyAll();
            }
        }
    }
}
