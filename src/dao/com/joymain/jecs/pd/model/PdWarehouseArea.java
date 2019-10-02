package com.joymain.jecs.pd.model;
// Generated 2009-9-21 11:50:41 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_WAREHOUSE_AREA"
 *     
 */

public class PdWarehouseArea extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long waId;
    private String companyCode;
    private Long areaCode;
    private String warehouseNo;


    // Constructors

    /** default constructor */
    public PdWarehouseArea() {
    }

    
    /** full constructor */
    public PdWarehouseArea(String companyCode, Long areaCode, String warehouseNo) {
        this.companyCode = companyCode;
        this.areaCode = areaCode;
        this.warehouseNo = warehouseNo;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="WA_ID"
     *         @hibernate.generator-param name="sequence" value="SEQ_PD" 
     */

    public Long getWaId() {
        return this.waId;
    }
    
    public void setWaId(Long waId) {
        this.waId = waId;
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
     *             column="AREA_CODE"
     *             length="20"
     *         
     */

    public Long getAreaCode() {
        return this.areaCode;
    }
    
    /**
     * @spring.validator type="required"
     */
    public void setAreaCode(Long areaCode) {
        this.areaCode = areaCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="WAREHOUSE_NO"
     *             length="20"
     *         
     */

    public String getWarehouseNo() {
        return this.warehouseNo;
    }
    
    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("areaCode").append("='").append(getAreaCode()).append("' ");			
      buffer.append("warehouseNo").append("='").append(getWarehouseNo()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdWarehouseArea) ) return false;
		 PdWarehouseArea castOther = ( PdWarehouseArea ) other; 
         
		 return ( (this.getWaId()==castOther.getWaId()) || ( this.getWaId()!=null && castOther.getWaId()!=null && this.getWaId().equals(castOther.getWaId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getAreaCode()==castOther.getAreaCode()) || ( this.getAreaCode()!=null && castOther.getAreaCode()!=null && this.getAreaCode().equals(castOther.getAreaCode()) ) )
 && ( (this.getWarehouseNo()==castOther.getWarehouseNo()) || ( this.getWarehouseNo()!=null && castOther.getWarehouseNo()!=null && this.getWarehouseNo().equals(castOther.getWarehouseNo()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getWaId() == null ? 0 : this.getWaId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getAreaCode() == null ? 0 : this.getAreaCode().hashCode() );
         result = 37 * result + ( getWarehouseNo() == null ? 0 : this.getWarehouseNo().hashCode() );
         return result;
   }   





}
