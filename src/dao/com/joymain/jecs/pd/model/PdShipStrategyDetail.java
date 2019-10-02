package com.joymain.jecs.pd.model;
// Generated 2011-5-26 12:09:09 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_SHIP_STRATEGY_DETAIL"
 *     
 */

public class PdShipStrategyDetail extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long ssdId;
    private String ssId;
    private Long shipArea;
    private String warehouseNo;
    private String shNo;
    
    private String status;//Modify By WuCF 20140225状态 

    // Constructors

    /** default constructor */
    public PdShipStrategyDetail() {
    }

    
    /** full constructor */
    public PdShipStrategyDetail(String ssId, Long shipArea, String warehouseNo, String shNo) {
        this.ssId = ssId;
        this.shipArea = shipArea;
        this.warehouseNo = warehouseNo;
        this.shNo = shNo;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="SSD_ID"
     *         
     */

    public Long getSsdId() {
        return this.ssdId;
    }
    
    public void setSsdId(Long ssdId) {
        this.ssdId = ssdId;
    }
    /**       
     *      *            @hibernate.property
     *             column="SS_ID"
     *             length="10"
     *         
     */

    public String getSsId() {
        return this.ssId;
    }
    
    public void setSsId(String ssId) {
        this.ssId = ssId;
    }
    /**       
     *      *            @hibernate.property
     *             column="SHIP_AREA"
     *             length="12"
     *         
     */

    public Long getShipArea() {
        return this.shipArea;
    }
    
    public void setShipArea(Long shipArea) {
        this.shipArea = shipArea;
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
    /**
     * @spring.validator type="required"
     */
    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="SH_NO"
     *             length="20"
     *         
     */

    public String getShNo() {
        return this.shNo;
    }
    /**
     * @spring.validator type="required"
     */
    public void setShNo(String shNo) {
        this.shNo = shNo;
    }
   
    /**       
	 *      *            @hibernate.property
	 *             column="STATUS"
	 *             length="1"
	 *         
	 */
    public String getStatus() {
		return status;
	}

    /**
     * @spring.validator type="required"
     */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("ssId").append("='").append(getSsId()).append("' ");			
      buffer.append("shipArea").append("='").append(getShipArea()).append("' ");			
      buffer.append("warehouseNo").append("='").append(getWarehouseNo()).append("' ");			
      buffer.append("shNo").append("='").append(getShNo()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdShipStrategyDetail) ) return false;
		 PdShipStrategyDetail castOther = ( PdShipStrategyDetail ) other; 
         
		 return ( (this.getSsdId()==castOther.getSsdId()) || ( this.getSsdId()!=null && castOther.getSsdId()!=null && this.getSsdId().equals(castOther.getSsdId()) ) )
 && ( (this.getSsId()==castOther.getSsId()) || ( this.getSsId()!=null && castOther.getSsId()!=null && this.getSsId().equals(castOther.getSsId()) ) )
 && ( (this.getShipArea()==castOther.getShipArea()) || ( this.getShipArea()!=null && castOther.getShipArea()!=null && this.getShipArea().equals(castOther.getShipArea()) ) )
 && ( (this.getWarehouseNo()==castOther.getWarehouseNo()) || ( this.getWarehouseNo()!=null && castOther.getWarehouseNo()!=null && this.getWarehouseNo().equals(castOther.getWarehouseNo()) ) )
 && ( (this.getShNo()==castOther.getShNo()) || ( this.getShNo()!=null && castOther.getShNo()!=null && this.getShNo().equals(castOther.getShNo()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getSsdId() == null ? 0 : this.getSsdId().hashCode() );
         result = 37 * result + ( getSsId() == null ? 0 : this.getSsId().hashCode() );
         result = 37 * result + ( getShipArea() == null ? 0 : this.getShipArea().hashCode() );
         result = 37 * result + ( getWarehouseNo() == null ? 0 : this.getWarehouseNo().hashCode() );
         result = 37 * result + ( getShNo() == null ? 0 : this.getShNo().hashCode() );
         return result;
   }   





}
