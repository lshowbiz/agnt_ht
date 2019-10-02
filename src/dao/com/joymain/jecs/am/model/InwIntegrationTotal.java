package com.joymain.jecs.am.model;
// Generated Jun 4, 2014 5:47:37 PM by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="INW_INTEGRATION_TOTAL"
 *     
 */

public class InwIntegrationTotal extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String userCode;
    private String totalPoints;
    private String remark;
    
    // Constructors

    /** default constructor */
    public InwIntegrationTotal() {
    }

    
    /** full constructor */
    public InwIntegrationTotal(String userCode, String totalPoints, String remark) {
        this.userCode = userCode;
        this.totalPoints = totalPoints;
        this.remark = remark;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *         @hibernate.generator-param name="sequence" value="SEQ_AM" 
     */
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
     *      *            @hibernate.property
     *             column="TOTAL_POINTS"
     *             length="10"
     *         
     */

    public String getTotalPoints() {
        return this.totalPoints;
    }
    
    public void setTotalPoints(String totalPoints) {
        this.totalPoints = totalPoints;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMARK"
     *             length="200"
     *         
     */

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("totalPoints").append("='").append(getTotalPoints()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof InwIntegrationTotal) ) return false;
		 InwIntegrationTotal castOther = ( InwIntegrationTotal ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getTotalPoints()==castOther.getTotalPoints()) || ( this.getTotalPoints()!=null && castOther.getTotalPoints()!=null && this.getTotalPoints().equals(castOther.getTotalPoints()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getTotalPoints() == null ? 0 : this.getTotalPoints().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         return result;
   }   





}
