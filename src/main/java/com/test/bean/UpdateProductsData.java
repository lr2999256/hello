package com.test.bean;

import java.util.List;
import java.util.Map;

/**
 * @author : Rui
 * @Date : 2018/1/7
 * @Time : 15:25
 **/
public class UpdateProductsData {
    private List<Map<String,String>> addProduct;

    public List<Map<String, String>> getAddProduct() {
        return addProduct;
    }

    public void setAddProduct(List<Map<String, String>> addProduct) {
        this.addProduct = addProduct;
    }
}
