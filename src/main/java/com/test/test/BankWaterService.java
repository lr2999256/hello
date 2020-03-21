package com.test.test;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author : Rui
 * @date : 2019/3/21 9:26
 **/
public class BankWaterService implements Runnable{

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(2,this);

    private Executor executor = Executors.newFixedThreadPool(4);

    private ConcurrentHashMap<String,Integer> sheet = new ConcurrentHashMap<>(8);

    private void count(){
        for (int i = 0; i < 4; i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    sheet.put(Thread.currentThread().getName(),1);
                    try {
                        System.out.println("test");
                        cyclicBarrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void run() {
        int result = 0;
        for (Map.Entry<String,Integer> entry : sheet.entrySet()){
            result += entry.getValue();
        }
        sheet.put("result",result);
        System.out.println("result"+result);
    }

    public static void main(String[] args){
        BankWaterService bankWaterService = new BankWaterService();
        bankWaterService.count();
    }
}
