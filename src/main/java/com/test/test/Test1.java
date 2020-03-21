package com.test.test;

import java.util.LinkedList;
import java.util.Random;

/**
 * @author : Rui
 * @date : 2019/3/30 13:17
 **/
public class Test1 {
    private LinkedList<Integer> list = new LinkedList<>();
    final static int MAX_SIZE = 10;

    public void in(Integer i) {
        synchronized (list) {
            while (list.size() == 10) {
                try {
                    System.out.println("队列已满，阻塞");
                    list.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            list.add(i);
            list.notifyAll();
            System.out.println("已读进值此时队列大小为" + list.size());
        }
    }

    public void out() {
        synchronized (list) {
            while (list.size() == 0) {
                try {
                    System.out.println("队列已空，阻塞");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.remove();
            list.notifyAll();
            System.out.println("已删除值此时队列大小为" + list.size());
        }
    }

    public static void main(String[] args) {
        Test1 queue = new Test1();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    Random r = new Random();
                    int temp = r.nextInt(100);
                    if (temp > 70) {
                        queue.in(2);
                    } else {
                        queue.out();
                    }
                }
            }).start();
        }

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
