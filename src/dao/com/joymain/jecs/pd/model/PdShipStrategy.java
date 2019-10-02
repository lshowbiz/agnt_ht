package com.joymain.jecs.pd.model;
// Generated 2011-5-26 12:08:38 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_SHIP_STRATEGY"
 *     
 */

public class PdShipStrategy extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long ssId;
    private String compayCode;
    private String ssName;
    private String ssDesc;


    // Constructors

    /** default constructor */
    public PdShipStrategy() {
    }

    
    /** full constructor */
    public PdShipStrategy(String compayCode, String ssName, String ssDesc) {
        this.compayCode = compayCode;
        this.ssName = ssName;
        this.ssDesc = ssDesc;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="SS_ID"
     *         
     */

    public Long getSsId() {
        return this.ssId;
    }
    
    public void setSsId(Long ssId) {
        this.ssId = ssId;
    }
    /**       
     *      *            @hibernate.property
     *             column="COMPAY_CODE"
     *             length="2"
     *         
     */

    public String getCompayCode() {
        return this.compayCode;
    }
    
    public void setCompayCode(String compayCode) {
        this.compayCode = compayCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="SS_NAME"
     *             length="200"
     *         
     */

    public String getSsName() {
        return this.ssName;
    }
    
    public void setSsName(String ssName) {
        this.ssName = ssName;
    }
    /**       
     *      *            @hibernate.property
     *             column="SS_DESC"
     *             length="500"
     *         
     */

    public String getSsDesc() {
        return this.ssDesc;
    }
    
    public void setSsDesc(String ssDesc) {
        this.ssDesc = ssDesc;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("compayCode").append("='").append(getCompayCode()).append("' ");			
      buffer.append("ssName").append("='").append(getSsName()).append("' ");			
      buffer.append("ssDesc").append("='").append(getSsDesc()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdShipStrategy) ) return false;
		 PdShipStrategy castOther = ( PdShipStrategy ) other; 
         
		 return ( (this.getSsId()==castOther.getSsId()) || ( this.getSsId()!=null && castOther.getSsId()!=null && this.getSsId().equals(castOther.getSsId()) ) )
 && ( (this.getCompayCode()==castOther.getCompayCode()) || ( this.getCompayCode()!=null && castOther.getCompayCode()!=null && this.getCompayCode().equals(castOther.getCompayCode()) ) )
 && ( (this.getSsName()==castOther.getSsName()) || ( this.getSsName()!=null && castOther.getSsName()!=null && this.getSsName().equals(castOther.getSsName()) ) )
 && ( (this.getSsDesc()==castOther.getSsDesc()) || ( this.getSsDesc()!=null && castOther.getSsDesc()!=null && this.getSsDesc().equals(castOther.getSsDesc()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getSsId() == null ? 0 : this.getSsId().hashCode() );
         result = 37 * result + ( getCompayCode() == null ? 0 : this.getCompayCode().hashCode() );
         result = 37 * result + ( getSsName() == null ? 0 : this.getSsName().hashCode() );
         result = 37 * result + ( getSsDesc() == null ? 0 : this.getSsDesc().hashCode() );
         return result;
   }   





}
