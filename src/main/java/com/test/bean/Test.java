package com.test.bean;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author : Rui
 * @Date : 2018/7/3
 * @Time : 16:09
 **/
public class Test {
    public void testBranch(){
        System.out.print("ddd");
        System.out.print("abc");
        System.out.print("abc");
        System.out.print("abc");
        System.out.print("bcd");
        System.out.print("abc");
    }

    public static void main(String[] args){
        LocalDateTime now = LocalDateTime.of(2019,1,7,4,15,0);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
//        LocalDateTime now = LocalDateTime.now();
        String date = df.format(now);
        df = DateTimeFormatter.ofPattern("HHmm");
        String time = df.format(now);
        Integer temp = Integer.parseInt(time);

        ChildA childA = new ChildA();
        childA.testB();
    }
}
