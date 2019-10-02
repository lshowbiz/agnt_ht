package com.joymain.jecs.fi.model;
// Generated 2014-3-31 15:59:00 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="FI_BCOIN_PAYCONFIG_DETAIL"
 *     
 */

public class FiBcoinPayconfigDetail extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long detailId;
    private String productNo;//产品
    private Long configId;//所属换购活动ID
    private Long sumQuantity;//限购总数量
    private Long nowQuantity;//当前剩余数量

    // Constructors

    /** default constructor */
    public FiBcoinPayconfigDetail() {
    }

    
    /** full constructor */
    public FiBcoinPayconfigDetail(String productNo, long configId) {
        this.productNo = productNo;
        this.configId = configId;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="DETAIL_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_BANKBOOK"
     *         
     */

    public Long getDetailId() {
        return this.detailId;
    }
    
    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }
    /**       
     *      *            @hibernate.property
     *             column="PRODUCT_NO"
     *             length="20"
     *             not-null="true"
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
     *             column="CONFIG_ID"
     *             length="12"
     *             not-null="true"
     *         
     */

    public Long getConfigId() {
        return this.configId;
    }
    
    public void setConfigId(Long configId) {
        this.configId = configId;
    }
   
    
    /**       
     *      *            @hibernate.property
     *             column="SUM_QUANTITY"
     *             length="10"
     *         
     */
    public Long getSumQuantity() {
		return sumQuantity;
	}


	public void setSumQuantity(Long sumQuantity) {
		this.sumQuantity = sumQuantity;
	}

	/**       
     *      *            @hibernate.property
     *             column="NOW_QUANTITY"
     *             length="10"
     *         
     */
	public Long getNowQuantity() {
		return nowQuantity;
	}


	public void setNowQuantity(Long nowQuantity) {
		this.nowQuantity = nowQuantity;
	}


	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("productNo").append("='").append(getProductNo()).append("' ");			
      buffer.append("configId").append("='").append(getConfigId()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FiBcoinPayconfigDetail) ) return false;
		 FiBcoinPayconfigDetail castOther = ( FiBcoinPayconfigDetail ) other; 
         
		 return ( (this.getDetailId()==castOther.getDetailId()) || ( this.getDetailId()!=null && castOther.getDetailId()!=null && this.getDetailId().equals(castOther.getDetailId()) ) )
 && ( (this.getProductNo()==castOther.getProductNo()) || ( this.getProductNo()!=null && castOther.getProductNo()!=null && this.getProductNo().equals(castOther.getProductNo()) ) )
 && (this.getConfigId()==castOther.getConfigId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDetailId() == null ? 0 : this.getDetailId().hashCode() );
         result = 37 * result + ( getProductNo() == null ? 0 : this.getProductNo().hashCode() );
         result = 37 * result + ( getConfigId() == null ? 0 : this.getConfigId().hashCode() );
         return result;
   }   





}
