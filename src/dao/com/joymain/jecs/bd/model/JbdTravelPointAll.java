package com.joymain.jecs.bd.model;
// Generated 2016-1-15 15:25:46 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_TRAVEL_POINT_ALL"
 *     
 */

public class JbdTravelPointAll extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private JbdTravelPointAllPK comp_id;
    private Integer passStar;
    private BigDecimal total;


    // Constructors

    /** default constructor */
    public JbdTravelPointAll() {
    }

	/** minimal constructor */
    public JbdTravelPointAll(JbdTravelPointAllPK comp_id) {
        this.comp_id = comp_id;
    }
    
    /** full constructor */
    public JbdTravelPointAll(JbdTravelPointAllPK comp_id, Integer passStar, BigDecimal total) {
        this.comp_id = comp_id;
        this.passStar = passStar;
        this.total = total;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     */

    public JbdTravelPointAllPK getComp_id() {
        return this.comp_id;
    }
    
    public void setComp_id(JbdTravelPointAllPK comp_id) {
        this.comp_id = comp_id;
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
		 if ( !(other instanceof JbdTravelPointAll) ) return false;
		 JbdTravelPointAll castOther = ( JbdTravelPointAll ) other; 
         
		 return ( (this.getComp_id()==castOther.getComp_id()) || ( this.getComp_id()!=null && castOther.getComp_id()!=null && this.getComp_id().equals(castOther.getComp_id()) ) )
 && ( (this.getPassStar()==castOther.getPassStar()) || ( this.getPassStar()!=null && castOther.getPassStar()!=null && this.getPassStar().equals(castOther.getPassStar()) ) )
 && ( (this.getTotal()==castOther.getTotal()) || ( this.getTotal()!=null && castOther.getTotal()!=null && this.getTotal().equals(castOther.getTotal()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getComp_id() == null ? 0 : this.getComp_id().hashCode() );
         result = 37 * result + ( getPassStar() == null ? 0 : this.getPassStar().hashCode() );
         result = 37 * result + ( getTotal() == null ? 0 : this.getTotal().hashCode() );
         return result;
   }   





}
