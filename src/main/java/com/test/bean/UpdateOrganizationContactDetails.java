package com.test.bean;

import java.util.Map;

/**
 * @author : Rui
 * @Date : 2018/1/7
 * @Time : 15:09
 **/
public class UpdateOrganizationContactDetails {
    private Map<String,String> addContactRecord;

    public Map<String, String> getAddContactRecord() {
        return addContactRecord;
    }

    public void setAddContactRecord(Map<String, String> addContactRecord) {
        this.addContactRecord = addContactRecord;
    }
}
