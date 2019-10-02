package com.joymain.jecs.pd.model;
// Generated 2009-9-21 11:09:36 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_WAREHOUSE_STOCK"
 *           	dynamic-update="true"
 *		dynamic-insert="true"
 *		optimistic-lock="version"
 */

public class PdWarehouseStock extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long uniNo;
    private String companyCode;
    PdWarehouse pdWarehouse = new PdWarehouse();
   

	private String productNo;
    private Long normalQty=new Long(0);
    private Long damageQty=new Long(0);
    private Long unknownQty=new Long(0);
    private Integer version=new Integer(0);
    //Modify By WuCF 20150706 警戒库存
    //private Long warnQty=new Long(0);

    
    
   
	
    // Constructors

    /** default constructor */
    public PdWarehouseStock() {
    }

    
    /** full constructor */
    public PdWarehouseStock(String companyCode, String productNo, Long normalQty, Long damageQty, Long unknownQty, Integer version) {
        this.companyCode = companyCode;
        this.productNo = productNo;
        this.normalQty = normalQty;
        this.damageQty = damageQty;
        this.unknownQty = unknownQty;
        this.version = version;
    }
    

    /**
     * *
     * @hibernate.many-to-one not-null="true" inverse="true"  
     * @hibernate.column name="WAREHOUSE_NO"
     * 
     */
    public PdWarehouse getPdWarehouse() {
		return pdWarehouse;
	}


	public void setPdWarehouse(PdWarehouse pdWarehouse) {
		this.pdWarehouse = pdWarehouse;
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
     *      *            @hibernate.version
     *             column="VERSION"
     *             length="5"
     *         
     */
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("productNo").append("='").append(getProductNo()).append("' ");			
      buffer.append("normalQty").append("='").append(getNormalQty()).append("' ");			
      buffer.append("damageQty").append("='").append(getDamageQty()).append("' ");			
      buffer.append("unknownQty").append("='").append(getUnknownQty()).append("' ");			
      buffer.append("version").append("='").append(getVersion()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdWarehouseStock) ) return false;
		 PdWarehouseStock castOther = ( PdWarehouseStock ) other; 
         
		 return ( (this.getUniNo()==castOther.getUniNo()) || ( this.getUniNo()!=null && castOther.getUniNo()!=null && this.getUniNo().equals(castOther.getUniNo()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getProductNo()==castOther.getProductNo()) || ( this.getProductNo()!=null && castOther.getProductNo()!=null && this.getProductNo().equals(castOther.getProductNo()) ) )
 && ( (this.getNormalQty()==castOther.getNormalQty()) || ( this.getNormalQty()!=null && castOther.getNormalQty()!=null && this.getNormalQty().equals(castOther.getNormalQty()) ) )
 && ( (this.getDamageQty()==castOther.getDamageQty()) || ( this.getDamageQty()!=null && castOther.getDamageQty()!=null && this.getDamageQty().equals(castOther.getDamageQty()) ) )
 && ( (this.getUnknownQty()==castOther.getUnknownQty()) || ( this.getUnknownQty()!=null && castOther.getUnknownQty()!=null && this.getUnknownQty().equals(castOther.getUnknownQty()) ) )
 && ( (this.getVersion()==castOther.getVersion()) || ( this.getVersion()!=null && castOther.getVersion()!=null && this.getVersion().equals(castOther.getVersion()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUniNo() == null ? 0 : this.getUniNo().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getProductNo() == null ? 0 : this.getProductNo().hashCode() );
         result = 37 * result + ( getNormalQty() == null ? 0 : this.getNormalQty().hashCode() );
         result = 37 * result + ( getDamageQty() == null ? 0 : this.getDamageQty().hashCode() );
         result = 37 * result + ( getUnknownQty() == null ? 0 : this.getUnknownQty().hashCode() );
         result = 37 * result + ( getVersion() == null ? 0 : this.getVersion().hashCode() );
         return result;
   }

  

 

   


}
