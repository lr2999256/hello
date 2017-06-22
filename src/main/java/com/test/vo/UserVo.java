package com.test.vo;

import com.test.persistence.beans.TTestUser;

import java.io.Serializable;

/**
 * Created by Rui on 2017/5/27.
 */
public class UserVo implements Serializable {
    private static final long serialVersionUID = -7401616465129303484L;
    private int pageNum = 1;
    private int pageSize = 5;
    private int totalCount = 0;
    private int pageCount = 1;
    private TTestUser tTestUser;

    public UserVo(){
        tTestUser = new TTestUser();
    }

    public TTestUser gettTestUser() {
        return tTestUser;
    }

    public void settTestUser(TTestUser tTestUser) {
        this.tTestUser = tTestUser;
    }

    public int getPageNum() {
        return pageNum;
    }

    public Long getId() {
        return this.tTestUser.getId();
    }

    public void setId(Long id) {
        this.tTestUser.setId(id);
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;

    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getUsrNm() {
        return this.tTestUser.getUsrNm();
    }

    public void setUsrNm(String usrNm) {
        this.tTestUser.setUsrNm(usrNm);
    }

    public String getPassWd() {
        return this.tTestUser.getPassWd();
    }

    public void setPassWd(String passWd) {
        this.tTestUser.setPassWd(passWd);
    }

    public int getStartIndex() {
        int pageNum = this.getPageNum() > 0 ? this.getPageNum() - 1 : 0;
        return pageNum * this.getPageSize();
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
