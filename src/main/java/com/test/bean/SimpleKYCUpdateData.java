package com.test.bean;

import java.util.List;
import java.util.Map;

/**
 * @author : Rui
 * @Date : 2018/1/7
 * @Time : 14:58
 **/
public class SimpleKYCUpdateData {
    private List<Map<String,String>> addField;

    public List<Map<String, String>> getAddField() {
        return addField;
    }

    public void setAddField(List<Map<String, String>> addField) {
        this.addField = addField;
    }
}
