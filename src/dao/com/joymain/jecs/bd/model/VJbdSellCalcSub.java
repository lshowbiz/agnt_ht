package com.joymain.jecs.bd.model;
// Generated 2009-9-26 10:22:58 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="V_JBD_SELL_CALC_SUB"
 *     
 */

public class VJbdSellCalcSub extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private BigDecimal id;
    private Integer wyear;
    private Integer wmonth;
    private Integer wweek;
    private String userCode;
    private String linkNo;
    private String companyCode;
    private String cardType;
    private BigDecimal groupPv;
    private BigDecimal bounsPoint;
    private BigDecimal bounsPv;
    private BigDecimal keepPv;
    private BigDecimal lastKeepPv;
    private BigDecimal areaConsumption;
    private Integer serialNumber;


    // Constructors

    /** default constructor */
    public VJbdSellCalcSub() {
    }

    
    /** full constructor */
    public VJbdSellCalcSub(Integer wyear, Integer wmonth, Integer wweek, String userCode, String linkNo, String companyCode, String cardType, BigDecimal groupPv, BigDecimal bounsPoint, BigDecimal bounsPv, BigDecimal keepPv, BigDecimal lastKeepPv, Integer serialNumber) {
        this.wyear = wyear;
        this.wmonth = wmonth;
        this.wweek = wweek;
        this.userCode = userCode;
        this.linkNo = linkNo;
        this.companyCode = companyCode;
        this.cardType = cardType;
        this.groupPv = groupPv;
        this.bounsPoint = bounsPoint;
        this.bounsPv = bounsPv;
        this.keepPv = keepPv;
        this.lastKeepPv = lastKeepPv;
        this.serialNumber = serialNumber;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.math.BigDecimal"
     *             column="ID"
     *         
     */

    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    /**       
     *      *            @hibernate.property
     *             column="W_YEAR"
     *             length="8"
     *         
     */

    public Integer getWyear() {
        return this.wyear;
    }
    
    public void setWyear(Integer wyear) {
        this.wyear = wyear;
    }
    /**       
     *      *            @hibernate.property
     *             column="W_MONTH"
     *             length="8"
     *         
     */

    public Integer getWmonth() {
        return this.wmonth;
    }
    
    public void setWmonth(Integer wmonth) {
        this.wmonth = wmonth;
    }
    /**       
     *      *            @hibernate.property
     *             column="W_WEEK"
     *             length="8"
     *         
     */

    public Integer getWweek() {
        return this.wweek;
    }
    
    public void setWweek(Integer wweek) {
        this.wweek = wweek;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_CODE"
     *             length="20"
     *         
     */

    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="LINK_NO"
     *             length="20"
     *         
     */

    public String getLinkNo() {
        return this.linkNo;
    }
    
    public void setLinkNo(String linkNo) {
        this.linkNo = linkNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="COMPANY_CODE"
     *             length="2"
     *         
     */

    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="CARD_TYPE"
     *             length="2"
     *         
     */

    public String getCardType() {
        return this.cardType;
    }
    
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    /**       
     *      *            @hibernate.property
     *             column="GROUP_PV"
     *             length="22"
     *         
     */

    public BigDecimal getGroupPv() {
        return this.groupPv;
    }
    
    public void setGroupPv(BigDecimal groupPv) {
        this.groupPv = groupPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="BOUNS_POINT"
     *             length="22"
     *         
     */

    public BigDecimal getBounsPoint() {
        return this.bounsPoint;
    }
    
    public void setBounsPoint(BigDecimal bounsPoint) {
        this.bounsPoint = bounsPoint;
    }
    /**       
     *      *            @hibernate.property
     *             column="BOUNS_PV"
     *             length="22"
     *         
     */

    public BigDecimal getBounsPv() {
        return this.bounsPv;
    }
    
    public void setBounsPv(BigDecimal bounsPv) {
        this.bounsPv = bounsPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="KEEP_PV"
     *             length="22"
     *         
     */

    public BigDecimal getKeepPv() {
        return this.keepPv;
    }
    
    public void setKeepPv(BigDecimal keepPv) {
        this.keepPv = keepPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="LAST_KEEP_PV"
     *             length="22"
     *         
     */

    public BigDecimal getLastKeepPv() {
        return this.lastKeepPv;
    }
    
    public void setLastKeepPv(BigDecimal lastKeepPv) {
        this.lastKeepPv = lastKeepPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="SERIAL_NUMBER"
     *             length="2"
     *         
     */

    public Integer getSerialNumber() {
        return this.serialNumber;
    }
    
    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("wyear").append("='").append(getWyear()).append("' ");			
      buffer.append("wmonth").append("='").append(getWmonth()).append("' ");			
      buffer.append("wweek").append("='").append(getWweek()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("linkNo").append("='").append(getLinkNo()).append("' ");			
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("cardType").append("='").append(getCardType()).append("' ");			
      buffer.append("groupPv").append("='").append(getGroupPv()).append("' ");			
      buffer.append("bounsPoint").append("='").append(getBounsPoint()).append("' ");			
      buffer.append("bounsPv").append("='").append(getBounsPv()).append("' ");			
      buffer.append("keepPv").append("='").append(getKeepPv()).append("' ");			
      buffer.append("lastKeepPv").append("='").append(getLastKeepPv()).append("' ");			
      buffer.append("serialNumber").append("='").append(getSerialNumber()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof VJbdSellCalcSub) ) return false;
		 VJbdSellCalcSub castOther = ( VJbdSellCalcSub ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getWyear()==castOther.getWyear()) || ( this.getWyear()!=null && castOther.getWyear()!=null && this.getWyear().equals(castOther.getWyear()) ) )
 && ( (this.getWmonth()==castOther.getWmonth()) || ( this.getWmonth()!=null && castOther.getWmonth()!=null && this.getWmonth().equals(castOther.getWmonth()) ) )
 && ( (this.getWweek()==castOther.getWweek()) || ( this.getWweek()!=null && castOther.getWweek()!=null && this.getWweek().equals(castOther.getWweek()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getLinkNo()==castOther.getLinkNo()) || ( this.getLinkNo()!=null && castOther.getLinkNo()!=null && this.getLinkNo().equals(castOther.getLinkNo()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getCardType()==castOther.getCardType()) || ( this.getCardType()!=null && castOther.getCardType()!=null && this.getCardType().equals(castOther.getCardType()) ) )
 && ( (this.getGroupPv()==castOther.getGroupPv()) || ( this.getGroupPv()!=null && castOther.getGroupPv()!=null && this.getGroupPv().equals(castOther.getGroupPv()) ) )
 && ( (this.getBounsPoint()==castOther.getBounsPoint()) || ( this.getBounsPoint()!=null && castOther.getBounsPoint()!=null && this.getBounsPoint().equals(castOther.getBounsPoint()) ) )
 && ( (this.getBounsPv()==castOther.getBounsPv()) || ( this.getBounsPv()!=null && castOther.getBounsPv()!=null && this.getBounsPv().equals(castOther.getBounsPv()) ) )
 && ( (this.getKeepPv()==castOther.getKeepPv()) || ( this.getKeepPv()!=null && castOther.getKeepPv()!=null && this.getKeepPv().equals(castOther.getKeepPv()) ) )
 && ( (this.getLastKeepPv()==castOther.getLastKeepPv()) || ( this.getLastKeepPv()!=null && castOther.getLastKeepPv()!=null && this.getLastKeepPv().equals(castOther.getLastKeepPv()) ) )
 && ( (this.getSerialNumber()==castOther.getSerialNumber()) || ( this.getSerialNumber()!=null && castOther.getSerialNumber()!=null && this.getSerialNumber().equals(castOther.getSerialNumber()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getWyear() == null ? 0 : this.getWyear().hashCode() );
         result = 37 * result + ( getWmonth() == null ? 0 : this.getWmonth().hashCode() );
         result = 37 * result + ( getWweek() == null ? 0 : this.getWweek().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getLinkNo() == null ? 0 : this.getLinkNo().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getCardType() == null ? 0 : this.getCardType().hashCode() );
         result = 37 * result + ( getGroupPv() == null ? 0 : this.getGroupPv().hashCode() );
         result = 37 * result + ( getBounsPoint() == null ? 0 : this.getBounsPoint().hashCode() );
         result = 37 * result + ( getBounsPv() == null ? 0 : this.getBounsPv().hashCode() );
         result = 37 * result + ( getKeepPv() == null ? 0 : this.getKeepPv().hashCode() );
         result = 37 * result + ( getLastKeepPv() == null ? 0 : this.getLastKeepPv().hashCode() );
         result = 37 * result + ( getSerialNumber() == null ? 0 : this.getSerialNumber().hashCode() );
         return result;
   }


   /**       
    *      *            @hibernate.property
    *             column="AREA_CONSUMPTION"
    *         
    */
public BigDecimal getAreaConsumption() {
	return areaConsumption;
}


public void setAreaConsumption(BigDecimal areaConsumption) {
	this.areaConsumption = areaConsumption;
}   





}
