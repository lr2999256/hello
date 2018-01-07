package com.test;

import com.alibaba.fastjson.JSON;
import com.test.annotation.FruitFunction;
import com.test.bean.SimpleKYCUpdateData;
import com.test.bean.TestDTO;
import com.test.bean.UpdateOrganizationContactDetails;
import com.test.bean.UpdateProductsData;
import com.test.proxy.Calculate;
import com.test.proxy.ProxyCalculate;
import com.test.util.ExtensionLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * Created by Rui on 2017/7/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Resource
    FruitFunction fruitFunction;

    @Test
    public void test(){
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
}
