package com.test.bean;

/**
 * @author : Rui
 * @Date : 2018/1/7
 * @Time : 14:50
 **/
public class TestDTO {

    private String organizationName;

    private SimpleKYCUpdateData simpleKYCUpdateData;

    private UpdateOrganizationContactDetails updateOrganizationContactDetails;

    private UpdateProductsData updateProductsData;

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public SimpleKYCUpdateData getSimpleKYCUpdateData() {
        return simpleKYCUpdateData;
    }

    public void setSimpleKYCUpdateData(SimpleKYCUpdateData simpleKYCUpdateData) {
        this.simpleKYCUpdateData = simpleKYCUpdateData;
    }

    public UpdateOrganizationContactDetails getUpdateOrganizationContactDetails() {
        return updateOrganizationContactDetails;
    }

    public void setUpdateOrganizationContactDetails(UpdateOrganizationContactDetails updateOrganizationContactDetails) {
        this.updateOrganizationContactDetails = updateOrganizationContactDetails;
    }

    public UpdateProductsData getUpdateProductsData() {
        return updateProductsData;
    }

    public void setUpdateProductsData(UpdateProductsData updateProductsData) {
        this.updateProductsData = updateProductsData;
    }
}
