package com.test.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Rui
 * @date : 2019/3/30 11:54
 **/
public class TestMain {

    static List<Apple> list = new ArrayList<>(16);

    public static void main(String[] args){


        ConsumeService1 consumeService = new ConsumeService1();
        ProductService1 productService = new ProductService1();

        for(int i = 0;i<=10;i++) {
            Thread thread2 = new Thread(productService);
            thread2.start();
            Thread thread1 = new Thread(consumeService);
            thread1.start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class ConsumeService1 implements Runnable{

        @Override
        public void run() {
            while (true) {
                if (list.size() <= 0) {
                    try {
                        System.out.println("consume empty apple " + list.size());
                        synchronized (list) {
                            list.wait();
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    list.remove(0);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("consume  apple " + list.size());
                    synchronized (list) {
                        list.notifyAll();
                    }
                }
            }
        }
    }

    static class ProductService1 implements Runnable {

        @Override
        public void run() {
            while (true) {
                if (list.size() > 10) {
                    System.out.println("product full apple " + list.size());
                    try {
                        synchronized (list) {
                            list.wait();
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                } else {
                    list.add(new Apple());
                    System.out.println("product  apple " + list.size());
                    synchronized (list) {
                        list.notifyAll();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
