package com.joymain.jecs.pd.model;
// Generated 2012-2-6 10:19:54 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_WAREHOUSE_FROZEN_DETAIL"
 *     
 */

public class PdWarehouseFrozenDetail extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long uniNo;
    private Long batchId;
    private String companyCode;
    private String warehouseNo;
    private String productNo;
    private Long normalQty;
    private Long damageQty;
    private Long unknownQty;


    // Constructors

    /** default constructor */
    public PdWarehouseFrozenDetail() {
    }

    
    /** full constructor */
    public PdWarehouseFrozenDetail(Long batchId, String companyCode, String warehouseNo, String productNo, Long normalQty, Long damageQty, Long unknownQty) {
        this.batchId = batchId;
        this.companyCode = companyCode;
        this.warehouseNo = warehouseNo;
        this.productNo = productNo;
        this.normalQty = normalQty;
        this.damageQty = damageQty;
        this.unknownQty = unknownQty;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="UNI_NO"
     *      @hibernate.generator-param name="sequence" value="SEQ_PD"    
     */

    public Long getUniNo() {
        return this.uniNo;
    }
    
    public void setUniNo(Long uniNo) {
        this.uniNo = uniNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="BATCH_ID"
     *             length="10"
     *         
     */

    public Long getBatchId() {
        return this.batchId;
    }
    
    public void setBatchId(Long batchId) {
        this.batchId = batchId;
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
     *             column="WAREHOUSE_NO"
     *             length="10"
     *         
     */

    public String getWarehouseNo() {
        return this.warehouseNo;
    }
    
    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
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
     *             column="NORMAL_QTY"
     *             length="10"
     *         
     */

    public Long getNormalQty() {
        return this.normalQty;
    }
    
    public void setNormalQty(Long normalQty) {
        this.normalQty = normalQty;
    }
    /**       
     *      *            @hibernate.property
     *             column="DAMAGE_QTY"
     *             length="10"
     *         
     */

    public Long getDamageQty() {
        return this.damageQty;
    }
    
    public void setDamageQty(Long damageQty) {
        this.damageQty = damageQty;
    }
    /**       
     *      *            @hibernate.property
     *             column="UNKNOWN_QTY"
     *             length="10"
     *         
     */

    public Long getUnknownQty() {
        return this.unknownQty;
    }
    
    public void setUnknownQty(Long unknownQty) {
        this.unknownQty = unknownQty;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("batchId").append("='").append(getBatchId()).append("' ");			
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("warehouseNo").append("='").append(getWarehouseNo()).append("' ");			
      buffer.append("productNo").append("='").append(getProductNo()).append("' ");			
      buffer.append("normalQty").append("='").append(getNormalQty()).append("' ");			
      buffer.append("damageQty").append("='").append(getDamageQty()).append("' ");			
      buffer.append("unknownQty").append("='").append(getUnknownQty()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdWarehouseFrozenDetail) ) return false;
		 PdWarehouseFrozenDetail castOther = ( PdWarehouseFrozenDetail ) other; 
         
		 return ( (this.getUniNo()==castOther.getUniNo()) || ( this.getUniNo()!=null && castOther.getUniNo()!=null && this.getUniNo().equals(castOther.getUniNo()) ) )
 && ( (this.getBatchId()==castOther.getBatchId()) || ( this.getBatchId()!=null && castOther.getBatchId()!=null && this.getBatchId().equals(castOther.getBatchId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getWarehouseNo()==castOther.getWarehouseNo()) || ( this.getWarehouseNo()!=null && castOther.getWarehouseNo()!=null && this.getWarehouseNo().equals(castOther.getWarehouseNo()) ) )
 && ( (this.getProductNo()==castOther.getProductNo()) || ( this.getProductNo()!=null && castOther.getProductNo()!=null && this.getProductNo().equals(castOther.getProductNo()) ) )
 && ( (this.getNormalQty()==castOther.getNormalQty()) || ( this.getNormalQty()!=null && castOther.getNormalQty()!=null && this.getNormalQty().equals(castOther.getNormalQty()) ) )
 && ( (this.getDamageQty()==castOther.getDamageQty()) || ( this.getDamageQty()!=null && castOther.getDamageQty()!=null && this.getDamageQty().equals(castOther.getDamageQty()) ) )
 && ( (this.getUnknownQty()==castOther.getUnknownQty()) || ( this.getUnknownQty()!=null && castOther.getUnknownQty()!=null && this.getUnknownQty().equals(castOther.getUnknownQty()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUniNo() == null ? 0 : this.getUniNo().hashCode() );
         result = 37 * result + ( getBatchId() == null ? 0 : this.getBatchId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getWarehouseNo() == null ? 0 : this.getWarehouseNo().hashCode() );
         result = 37 * result + ( getProductNo() == null ? 0 : this.getProductNo().hashCode() );
         result = 37 * result + ( getNormalQty() == null ? 0 : this.getNormalQty().hashCode() );
         result = 37 * result + ( getDamageQty() == null ? 0 : this.getDamageQty().hashCode() );
         result = 37 * result + ( getUnknownQty() == null ? 0 : this.getUnknownQty().hashCode() );
         return result;
   }   





}
