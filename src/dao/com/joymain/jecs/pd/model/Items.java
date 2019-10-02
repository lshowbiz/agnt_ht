package com.joymain.jecs.pd.model;
// Generated 2014-11-25 15:41:29 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="ITEMS"
 *     
 */

public class Items extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long itemsId;
    
    //private String statusId;
    private MailStatus mailStatus;
    
    //private Date acceptTime;//acceptTime  acceptTime
	//modify fu 2015-09-07  
    //虽然接口文档中该字段类型是date。但为解决json串转对象（InterfaceJsonUtil类的方法returnnoJsonToModel）时，将时间字段acceptTime转换成了系统当天时间，而不是json串原有的时间，特意将date类型的字段acceptTime修改成string类型
    private String acceptTime;
    private String acceptAddress;//acceptAddress acceptAddress
    private String remark;
    
    //modify by fu 2016-03-02 添加pd_logistics_base_num表的主键
    private String numId;

    // Constructors

    /** default constructor */
    public Items() {
    }

    
    /** full constructor */
    public Items(String acceptTime, String acceptAddress, String remark,String numId) {
        //this.statusId = statusId;
        this.acceptTime = acceptTime;
        this.acceptAddress = acceptAddress;
        this.remark = remark;
        this.numId = numId;
    }

    // Property accessors
	///**
	// * * @hibernate.id generator-class="sequence"
	// *             type="java.lang.Long"
	// * column="ITEMS_ID"
	// * 
	// * @hibernate.generator-param name="sequence" value="SEQ_PD"
	// */

    /**
	 * * @hibernate.id generator-class="assigned" 
	 * 		type="java.lang.Long"
	 * 
	 * column="ITEMS_ID"
	 * 
	 */
    public Long getItemsId() {
        return this.itemsId;
    }
    
    public void setItemsId(Long itemsId) {
        this.itemsId = itemsId;
    }
    
   /**       
     *      *            @hibernate.property
     *             column="STATUS_ID"
     *             length="20"
     *         
     *//*
    public String getStatusId() {
        return this.statusId;
    }
    
    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
    */
    
   /**
    * *
    * @hibernate.many-to-one class="com.joymain.jecs.pd.model.MailStatus" name="mailStatus" column="STATUS_ID"
    * 
    */
    public MailStatus getMailStatus() {
		return mailStatus;
	}

	public void setMailStatus(MailStatus mailStatus) {
		this.mailStatus = mailStatus;
	}


    
    /**       
     *      *            @hibernate.property
     *             column="ACCEPTTIME"
     *             length="200"
     *         
     */

    public String getAcceptTime() {
        return this.acceptTime;
    }
    
   
	public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="ACCEPTADDRESS"
     *             length="200"
     *         
     */

    public String getAcceptAddress() {
        return this.acceptAddress;
    }
    
    public void setAcceptAddress(String acceptAddress) {
        this.acceptAddress = acceptAddress;
    }
    /**       
     *      *            @hibernate.property
     *             column="EVENT"
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
     *      *            @hibernate.property
     *             column="NUM_ID"
     *             length="500"
     *         
     */
    public String getNumId() {
		return numId;
	}


	public void setNumId(String numId) {
		this.numId = numId;
	}


	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      //buffer.append("statusId").append("='").append(getStatusId()).append("' ");			
      buffer.append("acceptTime").append("='").append(getAcceptTime()).append("' ");			
      buffer.append("acceptAddress").append("='").append(getAcceptAddress()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");	
      buffer.append("numId").append("='").append(getNumId()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Items) ) return false;
		 Items castOther = ( Items ) other; 
         
		 return ( (this.getItemsId()==castOther.getItemsId()) || ( this.getItemsId()!=null && castOther.getItemsId()!=null && this.getItemsId().equals(castOther.getItemsId()) ) )
 //&& ( (this.getStatusId()==castOther.getStatusId()) || ( this.getStatusId()!=null && castOther.getStatusId()!=null && this.getStatusId().equals(castOther.getStatusId()) ) )
 && ( (this.getAcceptTime()==castOther.getAcceptTime()) || ( this.getAcceptTime()!=null && castOther.getAcceptTime()!=null && this.getAcceptTime().equals(castOther.getAcceptTime()) ) )
 && ( (this.getAcceptAddress()==castOther.getAcceptAddress()) || ( this.getAcceptAddress()!=null && castOther.getAcceptAddress()!=null && this.getAcceptAddress().equals(castOther.getAcceptAddress()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getNumId()==castOther.getNumId()) || ( this.getNumId()!=null && castOther.getNumId()!=null && this.getNumId().equals(castOther.getNumId()) ) );
  }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getItemsId() == null ? 0 : this.getItemsId().hashCode() );
         //result = 37 * result + ( getStatusId() == null ? 0 : this.getStatusId().hashCode() );
         result = 37 * result + ( getAcceptTime() == null ? 0 : this.getAcceptTime().hashCode() );
         result = 37 * result + ( getAcceptAddress() == null ? 0 : this.getAcceptAddress().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getNumId() == null ? 0 : this.getNumId().hashCode() );

         return result;
   }   





}
