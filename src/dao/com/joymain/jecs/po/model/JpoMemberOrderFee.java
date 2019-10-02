package com.joymain.jecs.po.model;
// Generated 2009-9-22 10:19:12 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPO_MEMBER_ORDER_FEE"
 *     
 */

public class JpoMemberOrderFee extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long mofId;
    private JpoMemberOrder jpoMemberOrder;
	private BigDecimal fee;
	//1,物流 2,手续费 3,税
    private String feeType;
    private String detailType;


    // Constructors


	/** default constructor */
    public JpoMemberOrderFee() {
    }

    
    /** full constructor */
    public JpoMemberOrderFee(long moId, BigDecimal fee, String feeType) {
        this.fee = fee;
        this.feeType = feeType;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="MOF_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_PO"
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
    public JpoMemberOrder getJpoMemberOrder() {
		return jpoMemberOrder;
	}


	public void setJpoMemberOrder(JpoMemberOrder jpoMemberOrder) {
		this.jpoMemberOrder = jpoMemberOrder;
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
		return detailType;
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
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JpoMemberOrderFee) ) return false;
		 JpoMemberOrderFee castOther = ( JpoMemberOrderFee ) other; 
         
		 return ( (this.getMofId()==castOther.getMofId()) || ( this.getMofId()!=null && castOther.getMofId()!=null && this.getMofId().equals(castOther.getMofId()) ) )
 && ( (this.getFee()==castOther.getFee()) || ( this.getFee()!=null && castOther.getFee()!=null && this.getFee().equals(castOther.getFee()) ) )
 && ( (this.getFeeType()==castOther.getFeeType()) || ( this.getFeeType()!=null && castOther.getFeeType()!=null && this.getFeeType().equals(castOther.getFeeType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getMofId() == null ? 0 : this.getMofId().hashCode() );
         result = 37 * result + ( getFee() == null ? 0 : this.getFee().hashCode() );
         result = 37 * result + ( getDetailType() == null ? 0 : this.getDetailType().hashCode() );
         result = 37 * result + ( getFeeType() == null ? 0 : this.getFeeType().hashCode() );
         return result;
   }   





}
