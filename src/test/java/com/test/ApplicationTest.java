package com.test;

import com.alibaba.fastjson.JSON;
import com.test.annotation.FruitFunction;
import com.test.bean.SimpleKYCUpdateData;
import com.test.bean.TestDTO;
import com.test.bean.UpdateOrganizationContactDetails;
import com.test.bean.UpdateProductsData;
import com.test.config.ResultNotifyThread;
import com.test.idgenerate.IdGenUtils;
import com.test.idgenerate.IdGenerator;
import com.test.lock.RedisLock;
import com.test.lock.RedisLock2;
import com.test.proxy.Calculate;
import com.test.proxy.ProxyCalculate;
import com.test.util.ExtensionLoader;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringEscapeUtils.unescapeHtml4;

/**
 * Created by Rui on 2017/7/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Resource
    FruitFunction fruitFunction;
    @Resource
    private ExecutorService ctoResultNotifyThreadPool;
    @Resource
    private RedisLock redisLock;
    @Resource
    private RedisLock2 redisLock2;

    @Test
    public void test(){
        int arr2[][] = new int[3][4];
        fruitFunction.eatFruit();
    }

    @Test
    public void testProxy() throws Exception{

        String abc = null;
        String temp = Optional.ofNullable(abc).orElse("test");

        String calClazz = "com.test.proxy.OtherCalculate";
        Class calculateClazz = Class.forName(calClazz);

        //将首字母转成小写
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append(Character.toLowerCase(calculateClazz.getSimpleName().charAt(0)));
        stringBuilder.append(calculateClazz.getSimpleName().substring(1));
        String beanName = stringBuilder.toString();

        //获取到Spring容器中的对象
        Object instance = ExtensionLoader.getSpringBean(beanName, calculateClazz);

        Calculate proxy = (Calculate) Proxy.newProxyInstance(
                Calculate.class.getClassLoader(),new Class[]{Calculate.class}, new ProxyCalculate(instance));
        int xx = proxy.add(10,20);
        System.out.println("============================="+xx);
    }

    @Test
    public void testJPA() throws Exception{

        String calClazz = "com.test.proxy.OtherCalculate";
        Class calculateClazz = Class.forName(calClazz);

        //将首字母转成小写
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append(Character.toLowerCase(calculateClazz.getSimpleName().charAt(0)));
        stringBuilder.append(calculateClazz.getSimpleName().substring(1));
        String beanName = stringBuilder.toString();

        //获取到Spring容器中的对象
        Object instance = ExtensionLoader.getSpringBean(beanName, calculateClazz);

        Calculate proxy = (Calculate) Proxy.newProxyInstance(
                Calculate.class.getClassLoader(),new Class[]{Calculate.class}, new ProxyCalculate(instance));
        int xx = proxy.add(10,20);
        System.out.println("============================="+xx);
    }

    @Test
    public void testJson() throws Exception{
        TestDTO testDTO = new TestDTO();

        testDTO.setOrganizationName("50008");

        SimpleKYCUpdateData simpleTimeZone = new SimpleKYCUpdateData();
        Map<String,String> map = new HashMap<>(2);
        map.put("KYCName","[KYC][Address Details][Postal Address]");
        map.put("KYCValue","Shenzhen Nanshan");
        List<Map<String,String>> addField = new ArrayList<>(2);
        addField.add(map);
        addField.add(map);
        simpleTimeZone.setAddField(addField);

        UpdateOrganizationContactDetails updateOrganizationContactDetails = new UpdateOrganizationContactDetails();
        Map<String,String> updateMap = new HashMap<>(8);
        updateMap.put("ContactTypeValue","01");
        updateMap.put("ContactFirstName","chen");
        updateOrganizationContactDetails.setAddContactRecord(updateMap);

        UpdateProductsData updateProductsData = new UpdateProductsData();
        HashMap<String,String> map1 = new HashMap<>(2);
        map1.put("ProductID","10000043");
        List<Map<String,String>> addProduct = new ArrayList<>(2);
        addProduct.add(map1);
        addProduct.add(map1);
        updateProductsData.setAddProduct(addProduct);

        testDTO.setSimpleKYCUpdateData(simpleTimeZone);
        testDTO.setUpdateOrganizationContactDetails(updateOrganizationContactDetails);
        testDTO.setUpdateProductsData(updateProductsData);

        String toJsonStr = JSON.toJSON(testDTO).toString();
        System.out.println(toJsonStr);
    }

    @Test
    public void testFormat() throws Exception{
        String dateStr = "20180317145120";

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(dateStr.substring(0,4)).append("-");
        stringBuffer.append(dateStr.substring(4,6)).append("-");
        stringBuffer.append(dateStr.substring(6,8)).append(" ");
        stringBuffer.append(dateStr.substring(8,10)).append(":");
        stringBuffer.append(dateStr.substring(10,12)).append(":");
        stringBuffer.append(dateStr.substring(12,14));

        System.out.println(stringBuffer.toString());

        String timeStr = "145120";
        stringBuffer = new StringBuffer();
        stringBuffer.append(timeStr.substring(0,2)).append(":");
        stringBuffer.append(timeStr.substring(2,4)).append(":");
        stringBuffer.append(timeStr.substring(4,6));

        String time = "03";
        int abc = Integer.parseInt(time);
        System.out.print(abc);
        System.out.println(stringBuffer.toString());

        String starttime = "092000";
        String bcd = starttime.substring(0,5)+"1";
        System.out.print(bcd);
    }

    @Test
    public void testGetTime() throws Exception{
        double aaa = (-89.00)/(149.00);
        int value1 = 5;
        int value2 = (value1*-1);
        List<String> list = new ArrayList<>();
        //处罚(IP黑名单)
        for (int i = 0; i < list.size(); i++) {
            //防重
            String abc = list.get(i);
        }

        String a1 = "1219051930476";
        String a2 = "1219032742775";
        double d1 = Double.parseDouble(a1);
        double d2 = Double.parseDouble(a2);
        double tmp = (d1-d2)/1024/1024;


        int a = -9;
        int b = 11;
        double ab = (double)a/(double) b;
        int value = (int) (ab*100);
        System.out.print(ab);

        double[] datas = new double[2];
        Pattern p = Pattern.compile("[^0-9]");

        String abc = "  eth0: 1219139450736 6316310674    0    0    0     0          0      8086 3321104342698 6960296382    0    0    0     0       0          0";
        Matcher m = p.matcher(abc);
        String str = m.replaceAll(" ").trim().replaceAll(" {2,}", " ");
        String[] array = str.split(" ");
        datas[0] += Double.parseDouble(array[2]);//收到流量
        datas[1] += Double.parseDouble(array[10]);//发出流量



        String dateStr = "092000";
        for(int i=0;i<60;i++) {
            if(i<10){
                dateStr = "090"+i+"33";
            }else{
                dateStr = "09"+i+"33";
            }
            String subStr = dateStr.substring(2, 4);

            int minute = Integer.parseInt(subStr);

            int count = minute / 5;
            String getTime;
            if (count <= 1) {
                getTime = dateStr.substring(0, 2) + "0" + count * 5 + dateStr.substring(4, 6);
            } else {
                getTime = dateStr.substring(0, 2) + count * 5 + dateStr.substring(4, 6);
            }

            System.out.println(getTime);
        }
    }

    @Test
    public void test2() throws Exception{
        int abc = -17;
        String abc1 = abc+"";
        Integer abc2 = (Integer)abc;
        System.out.println(StringEscapeUtils.unescapeHtml4("0f88bfcbcaf802eced9488691a9df4c5ff0b51b0a946beadf8045e8129ca52d6," +
                "                                                 f06f555e664bcc5a302fda4a96b6f4ec6de1f4071618805293079558794dc0aa"));
    }

    @Test
    public void testlua() throws Exception{
        while (true) {
            Long id = IdGenUtils.generateId("ABC");
            System.out.println("id="+id);
            Thread.sleep(10000);
        }
    }

    @Test
    public void testThreadPool() throws  Exception {
        for (int i=0;i< 50;i++) {
            ctoResultNotifyThreadPool.submit(new ResultNotifyThread());
        }
        System.out.println("结束！！！");
        Thread.sleep(100000000);
    }

    @Test
    public void lock() throws Exception {
        for(int i=0;i<10;i++){
            Thread thread =new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if(redisLock2.tryLock("redisLock", 1000, 2000, TimeUnit.MILLISECONDS)){
                            System.out.println(Thread.currentThread()+"我获取到锁了！当前时间:"+LocalDateTime.now());
                        } else {
                            System.out.println(Thread.currentThread()+"我获取到锁失败！当前时间:"+LocalDateTime.now());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"Thread-"+i);
            thread.start();
        }
        Thread.currentThread().join();
    }

}
