package com.joymain.jecs.pd.model;
// Generated 2016-1-19 14:16:04 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_SHIP_STRATEGY_MAIN"
 *     
 */

public class PdShipStrategyMain extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long valueId;
    private Long keyId;
    private String valueCode;
    private String valueTitle;
    private String exCompanyCode;
    private Integer orderNo;
    private String priority;
    private String importance;


    // Constructors

    /** default constructor */
    public PdShipStrategyMain() {
    }

	/** minimal constructor */
    public PdShipStrategyMain(String valueCode, String valueTitle) {
        this.valueCode = valueCode;
        this.valueTitle = valueTitle;
    }
    
    /** full constructor */
    public PdShipStrategyMain(Long keyId, String valueCode, String valueTitle, String exCompanyCode, Integer orderNo, String priority, String importance) {
        this.keyId = keyId;
        this.valueCode = valueCode;
        this.valueTitle = valueTitle;
        this.exCompanyCode = exCompanyCode;
        this.orderNo = orderNo;
        this.priority = priority;
        this.importance = importance;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="VALUE_ID"
     *         
     */

    public Long getValueId() {
        return this.valueId;
    }
    
    public void setValueId(Long valueId) {
        this.valueId = valueId;
    }
    /**       
     *      *            @hibernate.property
     *             column="KEY_ID"
     *             length="12"
     *         
     */

    public Long getKeyId() {
        return this.keyId;
    }
    
    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }
    /**       
     *      *            @hibernate.property
     *             column="VALUE_CODE"
     *             length="50"
     *             not-null="true"
     *         
     */

    public String getValueCode() {
        return this.valueCode;
    }
    
    public void setValueCode(String valueCode) {
        this.valueCode = valueCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="VALUE_TITLE"
     *             length="150"
     *             not-null="true"
     *         
     */

    public String getValueTitle() {
        return this.valueTitle;
    }
    
    public void setValueTitle(String valueTitle) {
        this.valueTitle = valueTitle;
    }
    /**       
     *      *            @hibernate.property
     *             column="EX_COMPANY_CODE"
     *             length="200"
     *         
     */

    public String getExCompanyCode() {
        return this.exCompanyCode;
    }
    
    public void setExCompanyCode(String exCompanyCode) {
        this.exCompanyCode = exCompanyCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="ORDER_NO"
     *             length="8"
     *         
     */

    public Integer getOrderNo() {
        return this.orderNo;
    }
    
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="PRIORITY"
     *             length="3"
     *         
     */

    public String getPriority() {
        return this.priority;
    }
    
    public void setPriority(String priority) {
        this.priority = priority;
    }
    /**       
     *      *            @hibernate.property
     *             column="IMPORTANCE"
     *             length="3"
     *         
     */

    public String getImportance() {
        return this.importance;
    }
    
    public void setImportance(String importance) {
        this.importance = importance;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("keyId").append("='").append(getKeyId()).append("' ");			
      buffer.append("valueCode").append("='").append(getValueCode()).append("' ");			
      buffer.append("valueTitle").append("='").append(getValueTitle()).append("' ");			
      buffer.append("exCompanyCode").append("='").append(getExCompanyCode()).append("' ");			
      buffer.append("orderNo").append("='").append(getOrderNo()).append("' ");			
      buffer.append("priority").append("='").append(getPriority()).append("' ");			
      buffer.append("importance").append("='").append(getImportance()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdShipStrategyMain) ) return false;
		 PdShipStrategyMain castOther = ( PdShipStrategyMain ) other; 
         
		 return ( (this.getValueId()==castOther.getValueId()) || ( this.getValueId()!=null && castOther.getValueId()!=null && this.getValueId().equals(castOther.getValueId()) ) )
 && ( (this.getKeyId()==castOther.getKeyId()) || ( this.getKeyId()!=null && castOther.getKeyId()!=null && this.getKeyId().equals(castOther.getKeyId()) ) )
 && ( (this.getValueCode()==castOther.getValueCode()) || ( this.getValueCode()!=null && castOther.getValueCode()!=null && this.getValueCode().equals(castOther.getValueCode()) ) )
 && ( (this.getValueTitle()==castOther.getValueTitle()) || ( this.getValueTitle()!=null && castOther.getValueTitle()!=null && this.getValueTitle().equals(castOther.getValueTitle()) ) )
 && ( (this.getExCompanyCode()==castOther.getExCompanyCode()) || ( this.getExCompanyCode()!=null && castOther.getExCompanyCode()!=null && this.getExCompanyCode().equals(castOther.getExCompanyCode()) ) )
 && ( (this.getOrderNo()==castOther.getOrderNo()) || ( this.getOrderNo()!=null && castOther.getOrderNo()!=null && this.getOrderNo().equals(castOther.getOrderNo()) ) )
 && ( (this.getPriority()==castOther.getPriority()) || ( this.getPriority()!=null && castOther.getPriority()!=null && this.getPriority().equals(castOther.getPriority()) ) )
 && ( (this.getImportance()==castOther.getImportance()) || ( this.getImportance()!=null && castOther.getImportance()!=null && this.getImportance().equals(castOther.getImportance()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getValueId() == null ? 0 : this.getValueId().hashCode() );
         result = 37 * result + ( getKeyId() == null ? 0 : this.getKeyId().hashCode() );
         result = 37 * result + ( getValueCode() == null ? 0 : this.getValueCode().hashCode() );
         result = 37 * result + ( getValueTitle() == null ? 0 : this.getValueTitle().hashCode() );
         result = 37 * result + ( getExCompanyCode() == null ? 0 : this.getExCompanyCode().hashCode() );
         result = 37 * result + ( getOrderNo() == null ? 0 : this.getOrderNo().hashCode() );
         result = 37 * result + ( getPriority() == null ? 0 : this.getPriority().hashCode() );
         result = 37 * result + ( getImportance() == null ? 0 : this.getImportance().hashCode() );
         return result;
   }   





}
