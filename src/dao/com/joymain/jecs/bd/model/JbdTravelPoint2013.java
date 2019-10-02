package com.joymain.jecs.bd.model;
// Generated 2012-11-6 16:24:34 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_TRAVEL_POINT2013"
 *     
 */

public class JbdTravelPoint2013 extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String userCode;
    private Integer passStar;
    private BigDecimal total;


    // Constructors

    /** default constructor */
    public JbdTravelPoint2013() {
    }

    
    /** full constructor */
    public JbdTravelPoint2013(Integer passStar, BigDecimal total) {
        this.passStar = passStar;
        this.total = total;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             type="java.lang.String"
     *             column="USER_CODE"
     *         
     */
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="PASS_STAR"
     *             length="2"
     *         
     */

    public Integer getPassStar() {
        return this.passStar;
    }
    
    public void setPassStar(Integer passStar) {
        this.passStar = passStar;
    }
    /**       
     *      *            @hibernate.property
     *             column="TOTAL"
     *             length="22"
     *         
     */

    public BigDecimal getTotal() {
        return this.total;
    }
    
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("passStar").append("='").append(getPassStar()).append("' ");			
      buffer.append("total").append("='").append(getTotal()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdTravelPoint2013) ) return false;
		 JbdTravelPoint2013 castOther = ( JbdTravelPoint2013 ) other; 
         
		 return ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getPassStar()==castOther.getPassStar()) || ( this.getPassStar()!=null && castOther.getPassStar()!=null && this.getPassStar().equals(castOther.getPassStar()) ) )
 && ( (this.getTotal()==castOther.getTotal()) || ( this.getTotal()!=null && castOther.getTotal()!=null && this.getTotal().equals(castOther.getTotal()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getPassStar() == null ? 0 : this.getPassStar().hashCode() );
         result = 37 * result + ( getTotal() == null ? 0 : this.getTotal().hashCode() );
         return result;
   }   





}
