package com.joymain.jecs.po.model;

import java.util.Date;

/**
 * Created by zhangjingl02 on 2016/8/30.
 */
public class JpoNycQualified {

    private Long id;
    private String userCode;
    private String userName;
    private String paperNumber;
    private String recommendNo;
    private String recommendName;
    private String memberOrderNo;
    private String productNo;
    private String productName;
    private String qualify;
    private Date checkDate;
    private Date qualifyDate;
    private Date checkTime;
    private Long jsWeek;
    private String status;
    private String remarks;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPaperNumber() {
        return paperNumber;
    }

    public void setPaperNumber(String paperNumber) {
        this.paperNumber = paperNumber;
    }

    public String getRecommendNo() {
        return recommendNo;
    }

    public void setRecommendNo(String recommendNo) {
        this.recommendNo = recommendNo;
    }

    public String getRecommendName() {
        return recommendName;
    }

    public void setRecommendName(String recommendName) {
        this.recommendName = recommendName;
    }

    public String getMemberOrderNo() {
        return memberOrderNo;
    }

    public void setMemberOrderNo(String memberOrderNo) {
        this.memberOrderNo = memberOrderNo;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQualify() {
        return qualify;
    }

    public void setQualify(String qualify) {
        this.qualify = qualify;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Date getQualifyDate() {
        return qualifyDate;
    }

    public void setQualifyDate(Date qualifyDate) {
        this.qualifyDate = qualifyDate;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Long getJsWeek() {
        return jsWeek;
    }

    public void setJsWeek(Long jsWeek) {
        this.jsWeek = jsWeek;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
