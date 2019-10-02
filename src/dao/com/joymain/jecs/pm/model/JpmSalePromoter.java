package com.joymain.jecs.pm.model;

// Generated 2013-7-2 16:41:19 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *                  table="JPM_SALE_PROMOTER"
 * 
 */

public class JpmSalePromoter extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Long spno;

    private Date startime;

    private Date endtime;

    private BigDecimal discount;

    private Integer spNum;

    private String presentno;

    private String presentname;

    private String presentlimit;

    private BigDecimal amount = new BigDecimal(0);

    private Long pv = 0L;

    private String isorder = "0";

    private BigDecimal integral;

    private String ispresent = "0";

    private BigDecimal reintegral;

    private String teamno;

    private String ordertype;

    private String companyno;

    private String saleType;

    private String descr;

    private String isactiva = "0";

    private String appendPresent;

    private String isiter = "0";

    private String userLevel;

    private String preOrderType;

    private Integer orderProNum;

    private Set<JpmSalepromoterPro> saleProductSet = new HashSet<JpmSalepromoterPro>();
    
    private String spname; //策略名字

    // Constructors

    /** default constructor */
    public JpmSalePromoter() {
    }

    /** minimal constructor */
    public JpmSalePromoter(Timestamp startime, Timestamp endtime) {
        this.startime = startime;
        this.endtime = endtime;
    }

    /** full constructor */
    public JpmSalePromoter(Date startime, Date endtime, BigDecimal discount, Integer spNum, String presentno, String presentname, String presentlimit, BigDecimal amount, Long pv, String isorder, BigDecimal integral, String ispresent, BigDecimal reintegral, String teamno, String ordertype, String companyno, String saleType, String descr, String isactiva) {
        this.startime = startime;
        this.endtime = endtime;
        this.discount = discount;
        this.spNum = spNum;
        this.presentno = presentno;
        this.presentname = presentname;
        this.presentlimit = presentlimit;
        this.amount = amount;
        this.pv = pv;
        this.isorder = isorder;
        this.integral = integral;
        this.ispresent = ispresent;
        this.reintegral = reintegral;
        this.teamno = teamno;
        this.ordertype = ordertype;
        this.companyno = companyno;
        this.saleType = saleType;
        this.descr = descr;
        this.isactiva = isactiva;
    }

    // Property accessors
    /**
     * * @hibernate.id
     * generator-class="native"
     * type="java.lang.Long"
     * column="SPNO"
     */

    public Long getSpno() {
        return this.spno;
    }

    public void setSpno(Long spno) {
        this.spno = spno;
    }

    /**
     * * @hibernate.property
     * column="STARTIME"
     * length="11"
     * not-null="true"
     * 
     */

    public Date getStartime() {
        return this.startime;
    }

    /**
     * @spring.validator type="required"
     */
    public void setStartime(Date startime) {
        this.startime = startime;
    }

    /**
     * * @hibernate.property
     * column="ENDTIME"
     * length="11"
     * not-null="true"
     * 
     */

    public Date getEndtime() {
        return this.endtime;
    }

    /**
     * @spring.validator type="required"
     */
    public void setEndtime(Date date) {
        this.endtime = date;
    }

    /**
     * * @hibernate.property
     * column="DISCOUNT"
     * length="18"
     * 
     */
    public BigDecimal getDiscount() {
        return this.discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    /**
     * * @hibernate.property
     * column="SP_NUM"
     * length="5"
     * 
     */

    public Integer getSpNum() {
        return this.spNum;
    }

    public void setSpNum(Integer spNum) {
        this.spNum = spNum;
    }

    /**
     * * @hibernate.property
     * column="PRESENTNO"
     * length="20"
     * 
     */

    public String getPresentno() {
        return this.presentno;
    }

    public void setPresentno(String presentno) {
        this.presentno = presentno;
    }

    /**
     * * @hibernate.property
     * column="PRESENTNAME"
     * length="50"
     * not-null="false"
     */

    public String getPresentname() {
        return this.presentname;
    }

    /**
     * @spring.validator type="required"
     */
    public void setPresentname(String presentname) {
        this.presentname = presentname;
    }

    /**
     * * @hibernate.property
     * column="PRESENTLIMIT"
     * length="100"
     * 
     */

    public String getPresentlimit() {
        return this.presentlimit;
    }

    public void setPresentlimit(String presentlimit) {
        this.presentlimit = presentlimit;
    }

    /**
     * * @hibernate.property
     * column="AMOUNT"
     * length="18"
     * 
     */

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * * @hibernate.property
     * column="PV"
     * length="10"
     * 
     */

    public Long getPv() {
        return this.pv;
    }

    public void setPv(Long pv) {
        this.pv = pv;
    }

    /**
     * * @hibernate.property
     * column="ISORDER"
     * length="1"
     * 
     */

    public String getIsorder() {
        return this.isorder;
    }

    public void setIsorder(String isorder) {
        this.isorder = isorder;
    }

    /**
     * * @hibernate.property
     * column="INTEGRAL"
     * length="5"
     * 
     */

    public BigDecimal getIntegral() {
        return this.integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    /**
     * * @hibernate.property
     * column="ISPRESENT"
     * length="1"
     * 
     */

    public String getIspresent() {
        return this.ispresent;
    }

    public void setIspresent(String ispresent) {
        this.ispresent = ispresent;
    }

    /**
     * * @hibernate.property
     * column="REINTEGRAL"
     * length="18"
     * 
     */

    public BigDecimal getReintegral() {
        return this.reintegral;
    }

    public void setReintegral(BigDecimal reintegral) {
        this.reintegral = reintegral;
    }

    /**
     * * @hibernate.property
     * column="TEAMNO"
     * length="1"
     * 
     */

    public String getTeamno() {
        return this.teamno;
    }

    public void setTeamno(String teamno) {
        this.teamno = teamno;
    }

    /**
     * * @hibernate.property
     * column="ORDERTYPE"
     * length="50"
     * 
     */

    public String getOrdertype() {
        return this.ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    /**
     * * @hibernate.property
     * column="COMPANYNO"
     * length="100"
     * 
     */

    public String getCompanyno() {
        return this.companyno;
    }

    public void setCompanyno(String companyno) {
        this.companyno = companyno;
    }

    /**
     * * @hibernate.property
     * column="SALE_TYPE"
     * length="1"
     * 
     */

    public String getSaleType() {
        return this.saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    /**
     * * @hibernate.property
     * column="DESCR"
     * length="200"
     * 
     */

    public String getDescr() {
        return this.descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    /**
     * * @hibernate.property
     * column="ISACTIVA"
     * length="1"
     * 
     */

    public String getIsactiva() {
        return this.isactiva;
    }

    public void setIsactiva(String isactiva) {
        this.isactiva = isactiva;
    }

    /**
     * * @hibernate.property
     * column="APPENDPRESENT"
     * length="200"
     * 
     */
    public String getAppendPresent() {
        return appendPresent;
    }

    public void setAppendPresent(String appendPresent) {
        this.appendPresent = appendPresent;
    }

    /**
     * * @hibernate.property
     * column="ISITER"
     * length="1"
     * 
     */
    public String getIsiter() {
        return isiter;
    }

    public void setIsiter(String isiter) {
        this.isiter = isiter;
    }

    /**
     * * @hibernate.property
     * column="USERLEVEL"
     * length="50"
     * 
     */
    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    /**
     * * @hibernate.property
     * column="PREORDERTYPE"
     * length="50"
     * 
     */
    public String getPreOrderType() {
        return preOrderType;
    }

    public void setPreOrderType(String preOrderType) {
        this.preOrderType = preOrderType;
    }

    /**
     * @hibernate.property
     *                     column="ORDERPRONUM"
     */
    public Integer getOrderProNum() {
        return orderProNum;
    }

    public void setOrderProNum(Integer orderProNum) {
        this.orderProNum = orderProNum;
    }

    /**
     * @hibernate.property
     *                     column="SPNAME"
     */
	public String getSpname() {
		return spname;
	}

	public void setSpname(String spname) {
		this.spname = spname;
	}
	
    
    /**
     * @hibernate.set table="jpm_salepromoter_pro" lazy="false" cascade="all" inverse="true"
     * @hibernate.collection-key column="spno"
     * @hibernate.collection-one-to-many 
     *                                   class="com.joymain.jecs.pm.model.JpmSalepromoterPro"
     */
    public Set<JpmSalepromoterPro> getSaleProductSet() {
        return saleProductSet;
    }

    public void setSaleProductSet(Set<JpmSalepromoterPro> saleProductSet) {
        this.saleProductSet = saleProductSet;
    }

    /**
     * toString
     * 
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("startime").append("='").append(getStartime()).append("' ");
        buffer.append("endtime").append("='").append(getEndtime()).append("' ");
        buffer.append("discount").append("='").append(getDiscount()).append("' ");
        buffer.append("spNum").append("='").append(getSpNum()).append("' ");
        buffer.append("presentno").append("='").append(getPresentno()).append("' ");
        buffer.append("presentname").append("='").append(getPresentname()).append("' ");
        buffer.append("presentlimit").append("='").append(getPresentlimit()).append("' ");
        buffer.append("amount").append("='").append(getAmount()).append("' ");
        buffer.append("pv").append("='").append(getPv()).append("' ");
        buffer.append("isorder").append("='").append(getIsorder()).append("' ");
        buffer.append("integral").append("='").append(getIntegral()).append("' ");
        buffer.append("ispresent").append("='").append(getIspresent()).append("' ");
        buffer.append("reintegral").append("='").append(getReintegral()).append("' ");
        buffer.append("teamno").append("='").append(getTeamno()).append("' ");
        buffer.append("ordertype").append("='").append(getOrdertype()).append("' ");
        buffer.append("companyno").append("='").append(getCompanyno()).append("' ");
        buffer.append("saleType").append("='").append(getSaleType()).append("' ");
        buffer.append("descr").append("='").append(getDescr()).append("' ");
        buffer.append("isactiva").append("='").append(getIsactiva()).append("' ");
        buffer.append("]");

        return buffer.toString();
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof JpmSalePromoter))
            return false;
        JpmSalePromoter castOther = (JpmSalePromoter) other;

        return ((this.getSpno() == castOther.getSpno()) || (this.getSpno() != null && castOther.getSpno() != null && this.getSpno().equals(castOther.getSpno()))) && ((this.getStartime() == castOther.getStartime()) || (this.getStartime() != null && castOther.getStartime() != null && this.getStartime().equals(castOther.getStartime()))) && ((this.getEndtime() == castOther.getEndtime()) || (this.getEndtime() != null && castOther.getEndtime() != null && this.getEndtime().equals(castOther.getEndtime()))) && ((this.getSpNum() == castOther.getSpNum()) || (this.getSpNum() != null && castOther.getSpNum() != null && this.getSpNum().equals(castOther.getSpNum()))) && ((this.getPresentno() == castOther.getPresentno()) || (this.getPresentno() != null && castOther.getPresentno() != null && this.getPresentno().equals(castOther.getPresentno()))) && ((this.getPresentname() == castOther.getPresentname()) || (this.getPresentname() != null && castOther.getPresentname() != null && this.getPresentname().equals(castOther.getPresentname()))) && ((this.getPresentlimit() == castOther.getPresentlimit()) || (this.getPresentlimit() != null && castOther.getPresentlimit() != null && this.getPresentlimit().equals(castOther.getPresentlimit()))) && ((this.getPv() == castOther.getPv()) || (this.getPv() != null && castOther.getPv() != null && this.getPv().equals(castOther.getPv()))) && ((this.getIsorder() == castOther.getIsorder()) || (this.getIsorder() != null && castOther.getIsorder() != null && this.getIsorder().equals(castOther.getIsorder()))) && ((this.getIspresent() == castOther.getIspresent()) || (this.getIspresent() != null && castOther.getIspresent() != null && this.getIspresent().equals(castOther.getIspresent())))

        && ((this.getTeamno() == castOther.getTeamno()) || (this.getTeamno() != null && castOther.getTeamno() != null && this.getTeamno().equals(castOther.getTeamno()))) && ((this.getOrdertype() == castOther.getOrdertype()) || (this.getOrdertype() != null && castOther.getOrdertype() != null && this.getOrdertype().equals(castOther.getOrdertype()))) && ((this.getCompanyno() == castOther.getCompanyno()) || (this.getCompanyno() != null && castOther.getCompanyno() != null && this.getCompanyno().equals(castOther.getCompanyno()))) && ((this.getSaleType() == castOther.getSaleType()) || (this.getSaleType() != null && castOther.getSaleType() != null && this.getSaleType().equals(castOther.getSaleType()))) && ((this.getDescr() == castOther.getDescr()) || (this.getDescr() != null && castOther.getDescr() != null && this.getDescr().equals(castOther.getDescr()))) && ((this.getIsactiva() == castOther.getIsactiva()) || (this.getIsactiva() != null && castOther.getIsactiva() != null && this.getIsactiva().equals(castOther.getIsactiva())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getSpno() == null ? 0 : this.getSpno().hashCode());
        result = 37 * result + (getStartime() == null ? 0 : this.getStartime().hashCode());
        result = 37 * result + (getEndtime() == null ? 0 : this.getEndtime().hashCode());
        result = 37 * result + (getSpNum() == null ? 0 : this.getSpNum().hashCode());
        result = 37 * result + (getPresentno() == null ? 0 : this.getPresentno().hashCode());
        result = 37 * result + (getPresentname() == null ? 0 : this.getPresentname().hashCode());
        result = 37 * result + (getPresentlimit() == null ? 0 : this.getPresentlimit().hashCode());
        result = 37 * result + (getPv() == null ? 0 : this.getPv().hashCode());
        result = 37 * result + (getIsorder() == null ? 0 : this.getIsorder().hashCode());
        result = 37 * result + (getIspresent() == null ? 0 : this.getIspresent().hashCode());
        result = 37 * result + (getTeamno() == null ? 0 : this.getTeamno().hashCode());
        result = 37 * result + (getOrdertype() == null ? 0 : this.getOrdertype().hashCode());
        result = 37 * result + (getCompanyno() == null ? 0 : this.getCompanyno().hashCode());
        result = 37 * result + (getSaleType() == null ? 0 : this.getSaleType().hashCode());
        result = 37 * result + (getDescr() == null ? 0 : this.getDescr().hashCode());
        result = 37 * result + (getIsactiva() == null ? 0 : this.getIsactiva().hashCode());
        return result;
    }

}
