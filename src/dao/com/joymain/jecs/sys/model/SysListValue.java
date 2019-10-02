package com.joymain.jecs.sys.model;




/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JSYS_LIST_VALUE"
 *     
 */

public class SysListValue extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long valueId;
    private SysListKey sysListKey;
    private String valueCode;
    private String valueTitle;
    private String exCompanyCode;
    private Integer orderNo;


    // Constructors

    /** default constructor */
    public SysListValue() {
    }

	/** minimal constructor */
    public SysListValue(String valueCode, String valueTitle) {
        this.valueCode = valueCode;
        this.valueTitle = valueTitle;
    }
    
    /** full constructor */
    public SysListValue(String valueCode, String valueTitle, String exCompanyCode, Integer orderNo) {
        this.valueCode = valueCode;
        this.valueTitle = valueTitle;
        this.exCompanyCode = exCompanyCode;
        this.orderNo = orderNo;
    }
    

   
    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="VALUE_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_SYS"
     */
    public Long getValueId() {
        return this.valueId;
    }
    
    public void setValueId(Long valueId) {
        this.valueId = valueId;
    }
    
    /**
	 * *
	 * @hibernate.many-to-one not-null="true"
	 * @hibernate.column name="KEY_ID"
	 * 
	 */

	public SysListKey getSysListKey() {
		return this.sysListKey;
	}

	public void setSysListKey(SysListKey sysListKey) {
		this.sysListKey = sysListKey;
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
     *             length="100"
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
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("listValue").append("='").append(getValueCode()).append("' ");			
      buffer.append("valueTitle").append("='").append(getValueTitle()).append("' ");			
      buffer.append("exCompanyCode").append("='").append(getExCompanyCode()).append("' ");			
      buffer.append("orderNo").append("='").append(getOrderNo()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysListValue) ) return false;
		 SysListValue castOther = ( SysListValue ) other; 
         
		 return ( (this.getValueId()==castOther.getValueId()) || ( this.getValueId()!=null && castOther.getValueId()!=null && this.getValueId().equals(castOther.getValueId()) ) )
 && ( (this.getValueCode()==castOther.getValueCode()) || ( this.getValueCode()!=null && castOther.getValueCode()!=null && this.getValueCode().equals(castOther.getValueCode()) ) )
 && ( (this.getValueTitle()==castOther.getValueTitle()) || ( this.getValueTitle()!=null && castOther.getValueTitle()!=null && this.getValueTitle().equals(castOther.getValueTitle()) ) )
 && ( (this.getExCompanyCode()==castOther.getExCompanyCode()) || ( this.getExCompanyCode()!=null && castOther.getExCompanyCode()!=null && this.getExCompanyCode().equals(castOther.getExCompanyCode()) ) )
 && ( (this.getOrderNo()==castOther.getOrderNo()) || ( this.getOrderNo()!=null && castOther.getOrderNo()!=null && this.getOrderNo().equals(castOther.getOrderNo()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getValueId() == null ? 0 : this.getValueId().hashCode() );
         result = 37 * result + ( getValueCode() == null ? 0 : this.getValueCode().hashCode() );
         result = 37 * result + ( getValueTitle() == null ? 0 : this.getValueTitle().hashCode() );
         result = 37 * result + ( getExCompanyCode() == null ? 0 : this.getExCompanyCode().hashCode() );
         result = 37 * result + ( getOrderNo() == null ? 0 : this.getOrderNo().hashCode() );
         return result;
   }   





}
