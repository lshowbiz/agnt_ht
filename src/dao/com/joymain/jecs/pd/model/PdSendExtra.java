package com.joymain.jecs.pd.model;
// Generated 2010-11-1 17:27:44 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_SEND_EXTRA"
 *     
 */

public class PdSendExtra extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long uniId;
    private String siNo;
    private String productNo;
    private Long qty;
    private String exType;
    private Date createTime;
    private String remark;
    private String operaterCode;

    // Constructors

    /** default constructor */
    public PdSendExtra() {
    }

    
    /** full constructor */
    public PdSendExtra(String siNo, String productNo, Long qty, String exType, Date createTime, String remark) {
        this.siNo = siNo;
        this.productNo = productNo;
        this.qty = qty;
        this.exType = exType;
        this.createTime = createTime;
        this.remark = remark;
    }
    

   
    // Property accessors
   
    /**
	 * * @hibernate.id generator-class="sequence"
	 * 
	 * column="UNI_ID"
	 * 
	 * @hibernate.generator-param name="sequence" value="SEQ_PD"
	 */
    public Long getUniId() {
        return this.uniId;
    }
    
    public void setUniId(Long uniId) {
        this.uniId = uniId;
    }
    /**       
     *      *            @hibernate.property
     *             column="SI_NO"
     *             length="17"
     *         
     */

    public String getSiNo() {
        return this.siNo;
    }
    
    public void setSiNo(String siNo) {
        this.siNo = siNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="PRODUCT_NO"
     *             length="20"
     *         
     */

    public String getProductNo() {
        return this.productNo;
    }
    
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="QTY"
     *             length="10"
     *         
     */

    public Long getQty() {
        return this.qty;
    }
    
    public void setQty(Long qty) {
        this.qty = qty;
    }
    /**       
     *      *            @hibernate.property
     *             column="OPERATER_CODE"
     *             length="20"
     *         
     */
    public String getOperaterCode() {
		return operaterCode;
	}


	public void setOperaterCode(String operaterCode) {
		this.operaterCode = operaterCode;
	}
	
    /**       
     *      *            @hibernate.property
     *             column="EX_TYPE"
     *             length="5"
     *         
     */

    
    public String getExType() {
        return this.exType;
    }
    
   


	public void setExType(String exType) {
        this.exType = exType;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_TIME"
     *             length="7"
     *         
     */

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMARK"
     *             length="1024"
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
      buffer.append("siNo").append("='").append(getSiNo()).append("' ");			
      buffer.append("productNo").append("='").append(getProductNo()).append("' ");			
      buffer.append("qty").append("='").append(getQty()).append("' ");			
      buffer.append("exType").append("='").append(getExType()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdSendExtra) ) return false;
		 PdSendExtra castOther = ( PdSendExtra ) other; 
         
		 return ( (this.getUniId()==castOther.getUniId()) || ( this.getUniId()!=null && castOther.getUniId()!=null && this.getUniId().equals(castOther.getUniId()) ) )
 && ( (this.getSiNo()==castOther.getSiNo()) || ( this.getSiNo()!=null && castOther.getSiNo()!=null && this.getSiNo().equals(castOther.getSiNo()) ) )
 && ( (this.getProductNo()==castOther.getProductNo()) || ( this.getProductNo()!=null && castOther.getProductNo()!=null && this.getProductNo().equals(castOther.getProductNo()) ) )
 && ( (this.getQty()==castOther.getQty()) || ( this.getQty()!=null && castOther.getQty()!=null && this.getQty().equals(castOther.getQty()) ) )
 && ( (this.getExType()==castOther.getExType()) || ( this.getExType()!=null && castOther.getExType()!=null && this.getExType().equals(castOther.getExType()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUniId() == null ? 0 : this.getUniId().hashCode() );
         result = 37 * result + ( getSiNo() == null ? 0 : this.getSiNo().hashCode() );
         result = 37 * result + ( getProductNo() == null ? 0 : this.getProductNo().hashCode() );
         result = 37 * result + ( getQty() == null ? 0 : this.getQty().hashCode() );
         result = 37 * result + ( getExType() == null ? 0 : this.getExType().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         return result;
   }   





}
