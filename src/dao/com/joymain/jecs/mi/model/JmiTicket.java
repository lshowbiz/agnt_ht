package com.joymain.jecs.mi.model;
// Generated 2014-6-4 14:10:13 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JMI_TICKET"
 *     
 */

public class JmiTicket extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String userCode;
    private String ticketType;
    private String applyUserCode;
    private String userName;
    private String papernumber;
    private String censusProvince;
    private String censusCity;
    private String censusDistrict;
    private String censusAddress;
    private String province;
    private String city;
    private String district;
    private String address;
    private String mobiletele;
    private String remark;
    private Date createTime;


    // Constructors

    /**       
     *      *            @hibernate.property
     *             column="CREATE_TIME"
     *         
     */

    public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	/** default constructor */
    public JmiTicket() {
    }

    
    /** full constructor */
    public JmiTicket(String userCode, String ticketType, String applyUserCode, String userName, String papernumber, String censusProvince, String censusCity, String censusDistrict, String censusAddress, String province, String city, String district, String address, String mobiletele, String remark) {
        this.userCode = userCode;
        this.ticketType = ticketType;
        this.applyUserCode = applyUserCode;
        this.userName = userName;
        this.papernumber = papernumber;
        this.censusProvince = censusProvince;
        this.censusCity = censusCity;
        this.censusDistrict = censusDistrict;
        this.censusAddress = censusAddress;
        this.province = province;
        this.city = city;
        this.district = district;
        this.address = address;
        this.mobiletele = mobiletele;
        this.remark = remark;
    }
    

   
    // Property accessors

    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID" 
     *@hibernate.generator-param name="sequence" value="SEQ_MI"
     *         
     */

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
     *             column="TICKET_TYPE"
     *             length="2"
     *         
     */

    public String getTicketType() {
        return this.ticketType;
    }
    
    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }
    /**       
     *      *            @hibernate.property
     *             column="APPLY_USER_CODE"
     *             length="20"
     *         
     */

    public String getApplyUserCode() {
        return this.applyUserCode;
    }
    
    public void setApplyUserCode(String applyUserCode) {
        this.applyUserCode = applyUserCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_NAME"
     *             length="300"
     *         
     */

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**       
     *      *            @hibernate.property
     *             column="PAPERNUMBER"
     *             length="70"
     *         
     */

    public String getPapernumber() {
        return this.papernumber;
    }
    
    public void setPapernumber(String papernumber) {
        this.papernumber = papernumber;
    }
    /**       
     *      *            @hibernate.property
     *             column="CENSUS_PROVINCE"
     *             length="20"
     *         
     */

    public String getCensusProvince() {
        return this.censusProvince;
    }
    
    public void setCensusProvince(String censusProvince) {
        this.censusProvince = censusProvince;
    }
    /**       
     *      *            @hibernate.property
     *             column="CENSUS_CITY"
     *             length="20"
     *         
     */

    public String getCensusCity() {
        return this.censusCity;
    }
    
    public void setCensusCity(String censusCity) {
        this.censusCity = censusCity;
    }
    /**       
     *      *            @hibernate.property
     *             column="CENSUS_DISTRICT"
     *             length="20"
     *         
     */

    public String getCensusDistrict() {
        return this.censusDistrict;
    }
    
    public void setCensusDistrict(String censusDistrict) {
        this.censusDistrict = censusDistrict;
    }
    /**       
     *      *            @hibernate.property
     *             column="CENSUS_ADDRESS"
     *             length="500"
     *         
     */

    public String getCensusAddress() {
        return this.censusAddress;
    }
    
    public void setCensusAddress(String censusAddress) {
        this.censusAddress = censusAddress;
    }
    /**       
     *      *            @hibernate.property
     *             column="PROVINCE"
     *             length="20"
     *         
     */

    public String getProvince() {
        return this.province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    /**       
     *      *            @hibernate.property
     *             column="CITY"
     *             length="20"
     *         
     */

    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    /**       
     *      *            @hibernate.property
     *             column="DISTRICT"
     *             length="20"
     *         
     */

    public String getDistrict() {
        return this.district;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }
    /**       
     *      *            @hibernate.property
     *             column="ADDRESS"
     *             length="500"
     *         
     */

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    /**       
     *      *            @hibernate.property
     *             column="MOBILETELE"
     *             length="30"
     *         
     */

    public String getMobiletele() {
        return this.mobiletele;
    }
    
    public void setMobiletele(String mobiletele) {
        this.mobiletele = mobiletele;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMARK"
     *             length="500"
     *         
     */

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("ticketType").append("='").append(getTicketType()).append("' ");			
      buffer.append("applyUserCode").append("='").append(getApplyUserCode()).append("' ");			
      buffer.append("userName").append("='").append(getUserName()).append("' ");			
      buffer.append("papernumber").append("='").append(getPapernumber()).append("' ");			
      buffer.append("censusProvince").append("='").append(getCensusProvince()).append("' ");			
      buffer.append("censusCity").append("='").append(getCensusCity()).append("' ");			
      buffer.append("censusDistrict").append("='").append(getCensusDistrict()).append("' ");			
      buffer.append("censusAddress").append("='").append(getCensusAddress()).append("' ");			
      buffer.append("province").append("='").append(getProvince()).append("' ");			
      buffer.append("city").append("='").append(getCity()).append("' ");			
      buffer.append("district").append("='").append(getDistrict()).append("' ");			
      buffer.append("address").append("='").append(getAddress()).append("' ");			
      buffer.append("mobiletele").append("='").append(getMobiletele()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JmiTicket) ) return false;
		 JmiTicket castOther = ( JmiTicket ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getTicketType()==castOther.getTicketType()) || ( this.getTicketType()!=null && castOther.getTicketType()!=null && this.getTicketType().equals(castOther.getTicketType()) ) )
 && ( (this.getApplyUserCode()==castOther.getApplyUserCode()) || ( this.getApplyUserCode()!=null && castOther.getApplyUserCode()!=null && this.getApplyUserCode().equals(castOther.getApplyUserCode()) ) )
 && ( (this.getUserName()==castOther.getUserName()) || ( this.getUserName()!=null && castOther.getUserName()!=null && this.getUserName().equals(castOther.getUserName()) ) )
 && ( (this.getPapernumber()==castOther.getPapernumber()) || ( this.getPapernumber()!=null && castOther.getPapernumber()!=null && this.getPapernumber().equals(castOther.getPapernumber()) ) )
 && ( (this.getCensusProvince()==castOther.getCensusProvince()) || ( this.getCensusProvince()!=null && castOther.getCensusProvince()!=null && this.getCensusProvince().equals(castOther.getCensusProvince()) ) )
 && ( (this.getCensusCity()==castOther.getCensusCity()) || ( this.getCensusCity()!=null && castOther.getCensusCity()!=null && this.getCensusCity().equals(castOther.getCensusCity()) ) )
 && ( (this.getCensusDistrict()==castOther.getCensusDistrict()) || ( this.getCensusDistrict()!=null && castOther.getCensusDistrict()!=null && this.getCensusDistrict().equals(castOther.getCensusDistrict()) ) )
 && ( (this.getCensusAddress()==castOther.getCensusAddress()) || ( this.getCensusAddress()!=null && castOther.getCensusAddress()!=null && this.getCensusAddress().equals(castOther.getCensusAddress()) ) )
 && ( (this.getProvince()==castOther.getProvince()) || ( this.getProvince()!=null && castOther.getProvince()!=null && this.getProvince().equals(castOther.getProvince()) ) )
 && ( (this.getCity()==castOther.getCity()) || ( this.getCity()!=null && castOther.getCity()!=null && this.getCity().equals(castOther.getCity()) ) )
 && ( (this.getDistrict()==castOther.getDistrict()) || ( this.getDistrict()!=null && castOther.getDistrict()!=null && this.getDistrict().equals(castOther.getDistrict()) ) )
 && ( (this.getAddress()==castOther.getAddress()) || ( this.getAddress()!=null && castOther.getAddress()!=null && this.getAddress().equals(castOther.getAddress()) ) )
 && ( (this.getMobiletele()==castOther.getMobiletele()) || ( this.getMobiletele()!=null && castOther.getMobiletele()!=null && this.getMobiletele().equals(castOther.getMobiletele()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getTicketType() == null ? 0 : this.getTicketType().hashCode() );
         result = 37 * result + ( getApplyUserCode() == null ? 0 : this.getApplyUserCode().hashCode() );
         result = 37 * result + ( getUserName() == null ? 0 : this.getUserName().hashCode() );
         result = 37 * result + ( getPapernumber() == null ? 0 : this.getPapernumber().hashCode() );
         result = 37 * result + ( getCensusProvince() == null ? 0 : this.getCensusProvince().hashCode() );
         result = 37 * result + ( getCensusCity() == null ? 0 : this.getCensusCity().hashCode() );
         result = 37 * result + ( getCensusDistrict() == null ? 0 : this.getCensusDistrict().hashCode() );
         result = 37 * result + ( getCensusAddress() == null ? 0 : this.getCensusAddress().hashCode() );
         result = 37 * result + ( getProvince() == null ? 0 : this.getProvince().hashCode() );
         result = 37 * result + ( getCity() == null ? 0 : this.getCity().hashCode() );
         result = 37 * result + ( getDistrict() == null ? 0 : this.getDistrict().hashCode() );
         result = 37 * result + ( getAddress() == null ? 0 : this.getAddress().hashCode() );
         result = 37 * result + ( getMobiletele() == null ? 0 : this.getMobiletele().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         return result;
   }   





}
