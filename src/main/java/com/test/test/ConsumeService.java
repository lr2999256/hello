package com.test.test;

import java.util.List;

/**
 * @author : Rui
 * @date : 2019/3/30 11:46
 **/
public class ConsumeService implements Runnable{

    private List<Apple> list;

    public ConsumeService(List<Apple> list){
        this.list = list;
    }

    @Override
    public void run() {
        synchronized (list) {
            if (list.size() <= 0) {
                try {
                    System.out.println("consume empty apple " + list.size());

                    list.wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                list.remove(0);
                System.out.println("consume  apple " + list.size());
                list.notifyAll();
            }
        }
    }
}
