package com.joymain.jecs.po.model;
// Generated 2015-5-21 14:18:02 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPO_PRODUCT_NUM_LIMIT"
 *     
 */

public class JpoProductNumLimit extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String productNo;
    private String productName;
    private Long num;
    private Date startime;
    private Date endtime;


    // Constructors
    public JpoProductNumLimit() {
	}

	public JpoProductNumLimit(Long id, String productNo, String productName,
			Long num, Date startime, Date endtime) {
		this.id = id;
		this.productNo = productNo;
		this.productName = productName;
		this.num = num;
		this.startime = startime;
		this.endtime = endtime;
	}

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="SEQ_PO"
     *         
     */

    public Long getId() {
        return this.id;
    }
    
	public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * * @hibernate.property
     * column="STARTIME"
     * length="11"
     * not-null="true"
     * 
     */

    public Date getStartime() {
        return this.startime;
    }

    /**
     * @spring.validator type="required"
     */
    public void setStartime(Date startime) {
        this.startime = startime;
    }

    /**
     * * @hibernate.property
     * column="ENDTIME"
     * length="11"
     * 
     */
    public Date getEndtime() {
		return endtime;
	}


	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	

    /**       
     *      *            @hibernate.property
     *             column="PRODUCT_NO"
     *             length="20"
     *         
     */

    public String getProductNo() {
        return this.productNo;
    }
    
 


	public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="PRODUCT_NAME"
     *             length="30"
     *         
     */

    public String getProductName() {
        return this.productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    /**       
     *      *            @hibernate.property
     *             column="NUM"
     *         
     */

    public Long getNum() {
        return this.num;
    }
    
    public void setNum(Long num) {
        this.num = num;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("productNo").append("='").append(getProductNo()).append("' ");			
      buffer.append("productName").append("='").append(getProductName()).append("' ");			
      buffer.append("num").append("='").append(getNum()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JpoProductNumLimit) ) return false;
		 JpoProductNumLimit castOther = ( JpoProductNumLimit ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getProductNo()==castOther.getProductNo()) || ( this.getProductNo()!=null && castOther.getProductNo()!=null && this.getProductNo().equals(castOther.getProductNo()) ) )
 && ( (this.getProductName()==castOther.getProductName()) || ( this.getProductName()!=null && castOther.getProductName()!=null && this.getProductName().equals(castOther.getProductName()) ) )
 && ( (this.getNum()==castOther.getNum()) || ( this.getNum()!=null && castOther.getNum()!=null && this.getNum().equals(castOther.getNum()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getProductNo() == null ? 0 : this.getProductNo().hashCode() );
         result = 37 * result + ( getProductName() == null ? 0 : this.getProductName().hashCode() );
         result = 37 * result + ( getNum() == null ? 0 : this.getNum().hashCode() );
         return result;
   }   





}
