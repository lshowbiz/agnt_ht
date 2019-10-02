package com.joymain.jecs.pd.model;
// Generated 2011-11-4 14:32:20 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_SHIP_FEE"
 *     
 */

public class PdShipFee extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String psfId;
    private String provinceName;
    private BigDecimal fee;


    // Constructors

    /** default constructor */
    public PdShipFee() {
    }

    
    /** full constructor */
    public PdShipFee(String provinceName, BigDecimal fee) {
        this.provinceName = provinceName;
        this.fee = fee;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.String"
     *             column="PSF_ID"
     *             @hibernate.generator-param name="sequence" value="SEQ_PD"
     *         
     */
    public String getPsfId() {
        return this.psfId;
    }
    
    public void setPsfId(String psfId) {
        this.psfId = psfId;
    }
    /**       
     *            @hibernate.property
     *             column="PROVINCE_NAME"
     *             length="20"
     *             not-null="true"
     *         
     */

    public String getProvinceName() {
        return this.provinceName;
    }
    
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    /**       
     *      *            @hibernate.property
     *             column="FEE"
     *             length="5"
     *             not-null="true"
     *         
     */

    public BigDecimal getFee() {
        return this.fee;
    }
    
    /**
	 * @spring.validator type="required"
	 */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("provinceName").append("='").append(getProvinceName()).append("' ");			
      buffer.append("fee").append("='").append(getFee()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdShipFee) ) return false;
		 PdShipFee castOther = ( PdShipFee ) other; 
         
		 return ( (this.getPsfId()==castOther.getPsfId()) || ( this.getPsfId()!=null && castOther.getPsfId()!=null && this.getPsfId().equals(castOther.getPsfId()) ) )
 && ( (this.getProvinceName()==castOther.getProvinceName()) || ( this.getProvinceName()!=null && castOther.getProvinceName()!=null && this.getProvinceName().equals(castOther.getProvinceName()) ) )
 && ( (this.getFee()==castOther.getFee()) || ( this.getFee()!=null && castOther.getFee()!=null && this.getFee().equals(castOther.getFee()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getPsfId() == null ? 0 : this.getPsfId().hashCode() );
         result = 37 * result + ( getProvinceName() == null ? 0 : this.getProvinceName().hashCode() );
         result = 37 * result + ( getFee() == null ? 0 : this.getFee().hashCode() );
         return result;
   }   





}
