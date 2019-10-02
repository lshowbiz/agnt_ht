package com.joymain.jecs.am.model;
// Generated 2012-3-20 11:09:35 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="AM_NEW"
 *     
 */

public class AmNew extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String newId;
    private String category;
    private String subject;
    private String url;
    private BigDecimal newOrder;
    private Date createTime;


    // Constructors

    /** default constructor */
    public AmNew() {
    }

    
    /** full constructor */
    public AmNew(String category, String subject, String url, BigDecimal newOrder) {
        this.category = category;
        this.subject = subject;
        this.url = url;
        this.newOrder = newOrder;
    }
    
    
    /**       
     *      *            @hibernate.property
     *             column="CREATE_TIME"
     *             length="7"
     *         
     */
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.String"
     *             column="NEW_ID"
     *         
     */

    public String getNewId() {
        return this.newId;
    }
    
    public void setNewId(String newId) {
        this.newId = newId;
    }
    /**       
     *      *            @hibernate.property
     *             column="CATEGORY"
     *             length="50"
     *         
     */

    public String getCategory() {
        return this.category;
    }
    
    /**
   	 * @spring.validator type="required"
   	 */
    public void setCategory(String category) {
        this.category = category;
    }
    /**       
     *      *            @hibernate.property
     *             column="SUBJECT"
     *             length="200"
     *         
     */

    public String getSubject() {
        return this.subject;
    }
    
    /**
   	 * @spring.validator type="required"
   	 */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    /**       
     *      *            @hibernate.property
     *             column="URL"
     *             length="500"
     *         
     */

    public String getUrl() {
        return this.url;
    }
    
    /**
   	 * @spring.validator type="required"
   	 */
    public void setUrl(String url) {
        this.url = url;
    }
    /**       
     *      *            @hibernate.property
     *             column="NEW_ORDER"
     *             length="22"
     *         
     */

    public BigDecimal getNewOrder() {
        return this.newOrder;
    }
    
    /**
   	 * @spring.validator type="required, long, minlength, maxlength"
   	 * @spring.validator-var name="minlength" value="8"
   	 * @spring.validator-args arg1value="8"
   	 * @spring.validator-var name="maxlength" value="11"
   	 * @spring.validator-args arg1value="11"
   	 */
    public void setNewOrder(BigDecimal newOrder) {
        this.newOrder = newOrder;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("category").append("='").append(getCategory()).append("' ");			
      buffer.append("subject").append("='").append(getSubject()).append("' ");			
      buffer.append("url").append("='").append(getUrl()).append("' ");			
      buffer.append("newOrder").append("='").append(getNewOrder()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AmNew) ) return false;
		 AmNew castOther = ( AmNew ) other; 
         
		 return ( (this.getNewId()==castOther.getNewId()) || ( this.getNewId()!=null && castOther.getNewId()!=null && this.getNewId().equals(castOther.getNewId()) ) )
 && ( (this.getCategory()==castOther.getCategory()) || ( this.getCategory()!=null && castOther.getCategory()!=null && this.getCategory().equals(castOther.getCategory()) ) )
 && ( (this.getSubject()==castOther.getSubject()) || ( this.getSubject()!=null && castOther.getSubject()!=null && this.getSubject().equals(castOther.getSubject()) ) )
 && ( (this.getUrl()==castOther.getUrl()) || ( this.getUrl()!=null && castOther.getUrl()!=null && this.getUrl().equals(castOther.getUrl()) ) )
 && ( (this.getNewOrder()==castOther.getNewOrder()) || ( this.getNewOrder()!=null && castOther.getNewOrder()!=null && this.getNewOrder().equals(castOther.getNewOrder()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getNewId() == null ? 0 : this.getNewId().hashCode() );
         result = 37 * result + ( getCategory() == null ? 0 : this.getCategory().hashCode() );
         result = 37 * result + ( getSubject() == null ? 0 : this.getSubject().hashCode() );
         result = 37 * result + ( getUrl() == null ? 0 : this.getUrl().hashCode() );
         result = 37 * result + ( getNewOrder() == null ? 0 : this.getNewOrder().hashCode() );
         return result;
   }   





}
