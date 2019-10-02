package com.joymain.jecs.pd.model;
// Generated 2009-9-21 11:49:59 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_WAREHOUSE_USER"
 *     
 */

public class PdWarehouseUser extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long wuId;
    private String warehouseNo;
    private String userCode;


    // Constructors

    /** default constructor */
    public PdWarehouseUser() {
    }

    
    /** full constructor */
    public PdWarehouseUser(String warehouseNo, String userCode) {
        this.warehouseNo = warehouseNo;
        this.userCode = userCode;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="WU_ID"
     *         @hibernate.generator-param name="sequence" value="SEQ_PD" 
     */

    public Long getWuId() {
        return this.wuId;
    }
    
    public void setWuId(Long wuId) {
        this.wuId = wuId;
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
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("warehouseNo").append("='").append(getWarehouseNo()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdWarehouseUser) ) return false;
		 PdWarehouseUser castOther = ( PdWarehouseUser ) other; 
         
		 return ( (this.getWuId()==castOther.getWuId()) || ( this.getWuId()!=null && castOther.getWuId()!=null && this.getWuId().equals(castOther.getWuId()) ) )
 && ( (this.getWarehouseNo()==castOther.getWarehouseNo()) || ( this.getWarehouseNo()!=null && castOther.getWarehouseNo()!=null && this.getWarehouseNo().equals(castOther.getWarehouseNo()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getWuId() == null ? 0 : this.getWuId().hashCode() );
         result = 37 * result + ( getWarehouseNo() == null ? 0 : this.getWarehouseNo().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         return result;
   }   





}
