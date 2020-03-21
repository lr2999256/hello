package com.test;

import com.test.test.Apple;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Rui
 * @date : 2019/11/1 14:12
 **/
public class LocalTest {

    @Test
    public void test1(){
        String abc = "00009200";
        char[] test = abc.toCharArray();
        for (char a: test){
            if(a=='0'){
                System.out.println("!!!"+a);
            } else {
                System.out.println("@@@"+a);
            }
        }
    }

    @Test
    public void test2() throws InterruptedException {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 3, 1000,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        long start = System.currentTimeMillis();
        Future future = null;
        List<Future> list = new ArrayList<>();
        CountDownLatch endGate = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            future = pool.submit(new TestThread());
            list.add(future);
        }

        for (Future future1 : list) {
            try {
                future1.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                endGate.countDown();
            }
        }
        try {
            endGate.await(60 * 60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("时间结束：" + (end - start));
//        Thread.sleep(10000);
    }

    @Test
    public void testSplit() {
        String message = "[ERRORCODE=YBLA058161A1] [转账不成功，@@[ERRORCODE=YBLA0332XX02] [客户账号37101002710051003563-0001可用余额不足，不允许交易#106001#FBSAAQKA##:外呼A01831507失败,错误码YBLA01816079]@@]";


        Pattern pattern = Pattern.compile("(\\s*\\[\\s*ERRORCODE\\=(\\w)*\\])([\\s\\S]*)");
        Matcher matcher = pattern.matcher(message);
//        System.out.println(matcher.group());
        //3.String 对象中的matches 方法就是通过这个Matcher和pattern来实现的。
        if(matcher.matches()){
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group(1));
        }
        System.out.println("[ERRORCODE=YBLA058161A1]".substring(11,"[ERRORCODE=YBLA058161A1]".length()-1));
//        System.out.println(matcher.find());
//        boolean abc = message.matches("\\s*\\[\\s*ERRORCODE\\=\\w*\\]([\\s\\S]*)");
//        System.out.println(matcher.group(1));
        //查找符合规则的子串
//        while(matcher.find()){
//            //获取 字符串
//            System.out.println(matcher.group());
//            //获取的字符串的首位置和末位置
//            System.out.println(matcher.start()+"--"+matcher.end());
//        }

    }

    @Test
    public void testAA(){
        String errInfo = "PR08_4转账失败";
        String aa = Optional.ofNullable(errInfo).map(m -> m.split(":", -1)).map(n -> n[0]).orElse(null);
        System.out.println(aa.startsWith("PR08_4"));
    }

    @Test
    public void testBB(){
        Apple apple = new Apple();
        apple.setCount(1);
        Apple apple1 = (Apple) apple.clone();
        apple1.setCount(2);
    }

    @Test
    public void testCC(){
        String aa = "处理成功";
        if(aa.contains("成功")){
            System.out.println("ok");
        }
    }

    @Test
    public void testDD(){
        byte charAt = (byte)("a".charAt(0));
        byte extendChar = ++charAt;
        System.out.println((char)extendChar);
        String aa = null;
        String aab = Optional.ofNullable(aa).orElse("");
        System.out.println("a");
    }

    @Test
    public void testEE(){
        int a = (int)(Math.random()*9999);
        System.out.println(a);
    }
}
