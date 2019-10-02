package com.joymain.jecs.pm.model;
// Generated 2009-11-6 17:25:20 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPM_PRODUCT_COMBINATION"
 *     
 */

public class JpmProductCombination extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long jpcId;
    private String productNo;
    private String subProductNo;
    private Long qty;

    // Constructors

    /** default constructor */
    public JpmProductCombination() {
    }

    
    /** full constructor */
    public JpmProductCombination(String productNo, String subProductNo) {
        this.productNo = productNo;
        this.subProductNo = subProductNo;
    }
    

    /**       
     *      *            @hibernate.property
     *             column="QTY"
     *             
     *         
     */
    public Long getQty() {
		return qty;
	}

    /**
     * @spring.validator type="required"
     */

	public void setQty(Long qty) {
		this.qty = qty;
	}


	// Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="JPC_ID"
     *         
     */

    public Long getJpcId() {
        return this.jpcId;
    }
    
    public void setJpcId(Long jpcId) {
        this.jpcId = jpcId;
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
    /**
     * @spring.validator type="required"
     */

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="SUB_PRODUCT_NO"
     *             length="20"
     *         
     */

    public String getSubProductNo() {
        return this.subProductNo;
    }
    /**
     * @spring.validator type="required"
     */

    public void setSubProductNo(String subProductNo) {
        this.subProductNo = subProductNo;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("productNo").append("='").append(getProductNo()).append("' ");			
      buffer.append("subProductNo").append("='").append(getSubProductNo()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JpmProductCombination) ) return false;
		 JpmProductCombination castOther = ( JpmProductCombination ) other; 
         
		 return ( (this.getJpcId()==castOther.getJpcId()) || ( this.getJpcId()!=null && castOther.getJpcId()!=null && this.getJpcId().equals(castOther.getJpcId()) ) )
 && ( (this.getProductNo()==castOther.getProductNo()) || ( this.getProductNo()!=null && castOther.getProductNo()!=null && this.getProductNo().equals(castOther.getProductNo()) ) )
 && ( (this.getSubProductNo()==castOther.getSubProductNo()) || ( this.getSubProductNo()!=null && castOther.getSubProductNo()!=null && this.getSubProductNo().equals(castOther.getSubProductNo()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getJpcId() == null ? 0 : this.getJpcId().hashCode() );
         result = 37 * result + ( getProductNo() == null ? 0 : this.getProductNo().hashCode() );
         result = 37 * result + ( getSubProductNo() == null ? 0 : this.getSubProductNo().hashCode() );
         return result;
   }   





}
