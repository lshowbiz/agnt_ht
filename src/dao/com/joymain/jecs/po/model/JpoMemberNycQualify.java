package com.joymain.jecs.po.model;
// Generated 2016-9-5 12:03:35 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPO_MEMBER_NYC_QUALIFY"
 *     
 */

public class JpoMemberNycQualify  implements java.io.Serializable {


    // Fields    

    private Long id;
    private String userCode;
    private String userName;
    private String papernumber;
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
    private String remarks;


    // Constructors

    /** default constructor */
    public JpoMemberNycQualify() {
    }

    
    /** full constructor */
    public JpoMemberNycQualify(Long id, String userCode, String userName, String papernumber, String recommendNo, String recommendName, String memberOrderNo, String productNo, String productName, String qualify, Date checkDate, Date qualifyDate, Date checkTime, Long jsWeek) {
        this.id = id;
        this.userCode = userCode;
        this.userName = userName;
        this.papernumber = papernumber;
        this.recommendNo = recommendNo;
        this.recommendName = recommendName;
        this.memberOrderNo = memberOrderNo;
        this.productNo = productNo;
        this.productName = productName;
        this.qualify = qualify;
        this.checkDate = checkDate;
        this.qualifyDate = qualifyDate;
        this.checkTime = checkTime;
        this.jsWeek = jsWeek;
    }
    

   
    // Property accessors
    /**
     * * @hibernate.id generator-class="sequence"  type="java.lang.Long"
     * column="ID"
     *
     * @hibernate.generator-param name="sequence" value="SEQ_PO"
     *
     */

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="USER_CODE"
     *             
     */

    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="USER_NAME"
     *             
     */

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="PAPERNUMBER"
     *             
     */

    public String getPapernumber() {
        return this.papernumber;
    }
    
    public void setPapernumber(String papernumber) {
        this.papernumber = papernumber;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="RECOMMEND_NO"
     *             
     */

    public String getRecommendNo() {
        return this.recommendNo;
    }
    
    public void setRecommendNo(String recommendNo) {
        this.recommendNo = recommendNo;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="RECOMMEND_NAME"
     *             
     */

    public String getRecommendName() {
        return this.recommendName;
    }
    
    public void setRecommendName(String recommendName) {
        this.recommendName = recommendName;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="MEMBER_ORDER_NO"
     *             
     */

    public String getMemberOrderNo() {
        return this.memberOrderNo;
    }
    
    public void setMemberOrderNo(String memberOrderNo) {
        this.memberOrderNo = memberOrderNo;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="PRODUCT_NO"
     *             
     */

    public String getProductNo() {
        return this.productNo;
    }
    
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="PRODUCT_NAME"
     *             
     */

    public String getProductName() {
        return this.productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="QUALIFY"
     *             
     */

    public String getQualify() {
        return this.qualify;
    }
    
    public void setQualify(String qualify) {
        this.qualify = qualify;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="CHECK_DATE"
     *             
     */

    public Date getCheckDate() {
        return this.checkDate;
    }
    
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="QUALIFY_DATE"
     *             
     */

    public Date getQualifyDate() {
        return this.qualifyDate;
    }
    
    public void setQualifyDate(Date qualifyDate) {
        this.qualifyDate = qualifyDate;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="CHECK_TIME"
     *             
     */

    public Date getCheckTime() {
        return this.checkTime;
    }
    
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="JS_WEEK"
     *             
     */

    public Long getJsWeek() {
        return this.jsWeek;
    }
    
    public void setJsWeek(Long jsWeek) {
        this.jsWeek = jsWeek;
    }

    /**
     *      *            @hibernate.id
     *             generator-class="assigned"
     *
     *                @hibernate.property
     *                 column="REMARKS"
     *
     */
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
