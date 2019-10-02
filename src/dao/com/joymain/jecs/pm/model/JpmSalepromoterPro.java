package com.joymain.jecs.pm.model;
// Generated 2013-7-2 16:42:32 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;

import com.joymain.jecs.model.BaseObject;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPM_SALEPROMOTER_PRO"
 *     
 */

public class JpmSalepromoterPro extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private Long spno;
    private String prono;
    private Long pttid;
    //private JpmSalePromoter jpmSalePromoter; 
    
    private Long pronum;
    
    // Constructors

    /** default constructor */
    public JpmSalepromoterPro() {
    }


	/** full constructor */
    public JpmSalepromoterPro(long spno, String prono) {
        this.spno = spno;
        this.prono = prono;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             column="ID"
     *         
     */

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    /**       
     *      *            @hibernate.property
     *             column="SPNO"
     *             length="10"
     *             not-null="false"
     *         
     */

    public long getSpno() {
        return this.spno;
    }
    
    public void setSpno(long spno) {
        this.spno = spno;
    }
    /**       
     *      *            @hibernate.property
     *             column="PRONO"
     *             length="20"
     *         
     */

    public String getProno() {
        return this.prono;
    }
    
    public void setProno(String prono) {
        this.prono = prono;
    }
   
   
    /**       
     *  @hibernate.property column="PTTID"
     */
    public Long getPttid() {
		return pttid;
	}


	public void setPttid(Long pttid) {
		this.pttid = pttid;
	}
	
	 /**       
     *  @hibernate.property column="PRO_NUM"
     */
	public Long getPronum() {
		return pronum;
	}


	public void setPronum(Long pronum) {
		this.pronum = pronum;
	}

	/**
	 * hibernate.many-to-one class="com.joymain.jecs.pm.model.JpmSalePromoter" column="spno"
	 * 
	 
	public JpmSalePromoter getJpmSalePromoter() {
		return jpmSalePromoter;
	}


	public void setJpmSalePromoter(JpmSalePromoter jpmSalePromoter) {
		this.jpmSalePromoter = jpmSalePromoter;
	}
*/

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("spno").append("='").append(getSpno()).append("' ");			
      buffer.append("prono").append("='").append(getProno()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JpmSalepromoterPro) ) return false;
		 JpmSalepromoterPro castOther = ( JpmSalepromoterPro ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && (this.getSpno()==castOther.getSpno())
 && ( (this.getProno()==castOther.getProno()) || ( this.getProno()!=null && castOther.getProno()!=null && this.getProno().equals(castOther.getProno()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + (int) this.getSpno();
         result = 37 * result + ( getProno() == null ? 0 : this.getProno().hashCode() );
         return result;
   }






}
