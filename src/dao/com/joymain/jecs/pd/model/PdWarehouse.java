package com.joymain.jecs.pd.model;
// Generated 2009-9-21 11:01:14 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_WAREHOUSE"
 *     
 */

public class PdWarehouse extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String warehouseNo;
    private String companyCode;
    private String warehouseName;
    private String stateCode;
    private String city;
    private String warehouseAddr;
    private String warehouseZip;
    private String warehouseLevel;
    private String remark;
    
    private String shNo;//物流公司
    private String lockFlag;


    // Constructors

    
	/** default constructor */
    public PdWarehouse() {
    }

	/** minimal constructor */
    public PdWarehouse(String warehouseName, String warehouseAddr, String warehouseLevel) {
        this.warehouseName = warehouseName;
        this.warehouseAddr = warehouseAddr;
        this.warehouseLevel = warehouseLevel;
    }
    
    /** full constructor */
    public PdWarehouse(String companyCode, String warehouseName, String stateCode, String city, String warehouseAddr, String warehouseZip, String warehouseLevel, String remark) {
        this.companyCode = companyCode;
        this.warehouseName = warehouseName;
        this.stateCode = stateCode;
        this.city = city;
        this.warehouseAddr = warehouseAddr;
        this.warehouseZip = warehouseZip;
        this.warehouseLevel = warehouseLevel;
        this.remark = remark;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="WAREHOUSE_NO"
     *         
     */

    public String getWarehouseNo() {
        return this.warehouseNo;
    }
    /**
     * @spring.validator type="required"
     */
    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }
    
    
    /**       
     *      *            @hibernate.property
     *             column="SH_NO"
     *             not-null="true"
     *         
     */

    public String getShNo() {
		return shNo;
	}

    /**
     * @spring.validator type="required"
     */
	public void setShNo(String shNo) {
		this.shNo = shNo;
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
    /**
     * @spring.validator type="required"
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="WAREHOUSE_NAME"
     *             length="2"
     *             not-null="true"
     *         
     */

    public String getWarehouseName() {
        return this.warehouseName;
    }
    /**
     * @spring.validator type="required"
     */
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
    /**       
     *      *            @hibernate.property
     *             column="STATE_CODE"
     *             length="50"
     *         
     */

    public String getStateCode() {
        return this.stateCode;
    }
    
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="CITY"
     *             length="200"
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
     *             column="WAREHOUSE_ADDR"
     *             length="2"
     *             not-null="true"
     *         
     */

    public String getWarehouseAddr() {
        return this.warehouseAddr;
    }
    
    /**
     * @spring.validator type="required"
     */
    public void setWarehouseAddr(String warehouseAddr) {
        this.warehouseAddr = warehouseAddr;
    }
    /**       
     *      *            @hibernate.property
     *             column="WAREHOUSE_ZIP"
     *             length="2"
     *         
     */

    public String getWarehouseZip() {
        return this.warehouseZip;
    }
    
    public void setWarehouseZip(String warehouseZip) {
        this.warehouseZip = warehouseZip;
    }
    /**       
     *      *            @hibernate.property
     *             column="WAREHOUSE_LEVEL"
     *             length="1"
     *             not-null="true"
     *         
     */

    public String getWarehouseLevel() {
        return this.warehouseLevel;
    }
    /**
     * @spring.validator type="required"
     */
    public void setWarehouseLevel(String warehouseLevel) {
        this.warehouseLevel = warehouseLevel;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMARK"
     *             length="2"
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
     *             column="LOCK_FLAG"
     *             
     *         
     */
    public String getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("warehouseName").append("='").append(getWarehouseName()).append("' ");			
      buffer.append("stateCode").append("='").append(getStateCode()).append("' ");			
      buffer.append("city").append("='").append(getCity()).append("' ");			
      buffer.append("warehouseAddr").append("='").append(getWarehouseAddr()).append("' ");			
      buffer.append("warehouseZip").append("='").append(getWarehouseZip()).append("' ");			
      buffer.append("warehouseLevel").append("='").append(getWarehouseLevel()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdWarehouse) ) return false;
		 PdWarehouse castOther = ( PdWarehouse ) other; 
         
		 return ( (this.getWarehouseNo()==castOther.getWarehouseNo()) || ( this.getWarehouseNo()!=null && castOther.getWarehouseNo()!=null && this.getWarehouseNo().equals(castOther.getWarehouseNo()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getWarehouseName()==castOther.getWarehouseName()) || ( this.getWarehouseName()!=null && castOther.getWarehouseName()!=null && this.getWarehouseName().equals(castOther.getWarehouseName()) ) )
 && ( (this.getStateCode()==castOther.getStateCode()) || ( this.getStateCode()!=null && castOther.getStateCode()!=null && this.getStateCode().equals(castOther.getStateCode()) ) )
 && ( (this.getCity()==castOther.getCity()) || ( this.getCity()!=null && castOther.getCity()!=null && this.getCity().equals(castOther.getCity()) ) )
 && ( (this.getWarehouseAddr()==castOther.getWarehouseAddr()) || ( this.getWarehouseAddr()!=null && castOther.getWarehouseAddr()!=null && this.getWarehouseAddr().equals(castOther.getWarehouseAddr()) ) )
 && ( (this.getWarehouseZip()==castOther.getWarehouseZip()) || ( this.getWarehouseZip()!=null && castOther.getWarehouseZip()!=null && this.getWarehouseZip().equals(castOther.getWarehouseZip()) ) )
 && ( (this.getWarehouseLevel()==castOther.getWarehouseLevel()) || ( this.getWarehouseLevel()!=null && castOther.getWarehouseLevel()!=null && this.getWarehouseLevel().equals(castOther.getWarehouseLevel()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getWarehouseNo() == null ? 0 : this.getWarehouseNo().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getWarehouseName() == null ? 0 : this.getWarehouseName().hashCode() );
         result = 37 * result + ( getStateCode() == null ? 0 : this.getStateCode().hashCode() );
         result = 37 * result + ( getCity() == null ? 0 : this.getCity().hashCode() );
         result = 37 * result + ( getWarehouseAddr() == null ? 0 : this.getWarehouseAddr().hashCode() );
         result = 37 * result + ( getWarehouseZip() == null ? 0 : this.getWarehouseZip().hashCode() );
         result = 37 * result + ( getWarehouseLevel() == null ? 0 : this.getWarehouseLevel().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         return result;
   }   





}
