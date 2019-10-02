package com.joymain.jecs.fi.model;
// Generated 2010-1-14 14:33:59 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;

import com.joymain.jecs.po.model.JpoMemberOrder;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_SUN_MEMBER_ORDER_FEE"
 *     
 */

public class JfiSunMemberOrderFee extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long mofId;
    private JfiSunMemberOrder jfiSunMemberOrder;
	private BigDecimal fee;
    private String feeType;
    private String detailType;


    // Constructors

    /** default constructor */
    public JfiSunMemberOrderFee() {
    }

    
    /** full constructor */
    public JfiSunMemberOrderFee(long moId, BigDecimal fee, String feeType, String detailType) {
        this.fee = fee;
        this.feeType = feeType;
        this.detailType = detailType;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="MOF_ID"
     *         
     */

    public Long getMofId() {
        return this.mofId;
    }
    
    public void setMofId(Long mofId) {
        this.mofId = mofId;
    }

    /**
     * *
     * @hibernate.many-to-one not-null="true"
     * @hibernate.column name="MO_ID"
     * 
     */
    public JfiSunMemberOrder getJfiSunMemberOrder() {
		return jfiSunMemberOrder;
	}


	public void setJfiSunMemberOrder(JfiSunMemberOrder jfiSunMemberOrder) {
		this.jfiSunMemberOrder = jfiSunMemberOrder;
	}

    /**       
     *      *            @hibernate.property
     *             column="FEE"
     *             length="18"
     *             not-null="true"
     *         
     */

    public BigDecimal getFee() {
        return this.fee;
    }
    
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
    /**       
     *      *            @hibernate.property
     *             column="FEE_TYPE"
     *             length="2"
     *             not-null="true"
     *         
     */

    public String getFeeType() {
        return this.feeType;
    }
    
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }
    /**       
     *      *            @hibernate.property
     *             column="DETAIL_TYPE"
     *             length="10"
     *             not-null="true"
     *         
     */

    public String getDetailType() {
        return this.detailType;
    }
    
    public void setDetailType(String detailType) {
        this.detailType = detailType;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("fee").append("='").append(getFee()).append("' ");			
      buffer.append("feeType").append("='").append(getFeeType()).append("' ");			
      buffer.append("detailType").append("='").append(getDetailType()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiSunMemberOrderFee) ) return false;
		 JfiSunMemberOrderFee castOther = ( JfiSunMemberOrderFee ) other; 
         
		 return ( (this.getMofId()==castOther.getMofId()) || ( this.getMofId()!=null && castOther.getMofId()!=null && this.getMofId().equals(castOther.getMofId()) ) )
 && ( (this.getFee()==castOther.getFee()) || ( this.getFee()!=null && castOther.getFee()!=null && this.getFee().equals(castOther.getFee()) ) )
 && ( (this.getFeeType()==castOther.getFeeType()) || ( this.getFeeType()!=null && castOther.getFeeType()!=null && this.getFeeType().equals(castOther.getFeeType()) ) )
 && ( (this.getDetailType()==castOther.getDetailType()) || ( this.getDetailType()!=null && castOther.getDetailType()!=null && this.getDetailType().equals(castOther.getDetailType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getMofId() == null ? 0 : this.getMofId().hashCode() );
         result = 37 * result + ( getFee() == null ? 0 : this.getFee().hashCode() );
         result = 37 * result + ( getFeeType() == null ? 0 : this.getFeeType().hashCode() );
         result = 37 * result + ( getDetailType() == null ? 0 : this.getDetailType().hashCode() );
         return result;
   }   





}
