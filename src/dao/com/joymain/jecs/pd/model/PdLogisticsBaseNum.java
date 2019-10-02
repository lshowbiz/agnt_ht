package com.joymain.jecs.pd.model;
// Generated 2014-11-25 15:27:50 by Hibernate Tools 3.1.0.beta4

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_LOGISTICS_BASE_NUM"
 *     
 */

public class PdLogisticsBaseNum extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long numId;

    //private String baseId;
    private PdLogisticsBase pdLogisticsBase;
    
    private String pdLogisticsBaseNum_no;//PdLogisticsBaseNum_no  
    private String name;//--------------------------------------------------name
    private String status;//------------------------------------------------status
    private Date mailTime;//
    private String otherOne;
    private String otherTwo;

    private List<PdLogisticsBaseDetail> pdLogisticsBaseDetail_items  = new ArrayList();//PdLogisticsBaseDetail_items
    
    // Constructors

    /** default constructor */
    public PdLogisticsBaseNum() {
    }

    
    /** full constructor */
    public PdLogisticsBaseNum(String pdLogisticsBaseNum_no, String name, String status, Date mailTime, String otherOne, String otherTwo,String baseId) {
        //this.baseId = baseId;
        this.pdLogisticsBaseNum_no = pdLogisticsBaseNum_no;
        this.name = name;
        this.status = status;
        this.mailTime = mailTime;
        this.otherOne = otherOne;
        this.otherTwo = otherTwo;
    }
    

    // Property accessors
	///**
	// * * @hibernate.id generator-class="sequence"
   //  *             type="java.lang.Long"
	// * column="NUM_ID"
	// * 
	// * @hibernate.generator-param name="sequence" value="SEQ_PD"
	// */
    /**
	 * * @hibernate.id generator-class="assigned" 
	 * 		type="java.lang.Long"
	 * 
	 * column="NUM_ID"
	 * 
	 */
    public Long getNumId() {
        return this.numId;
    }
    
    public void setNumId(Long numId) {
        this.numId = numId;
    }
    
   /**       
     *      *            @hibernate.property
     *             column="BASE_ID"
     *             length="20"
     *         
     *//*
    public String getBaseId() {
        return this.baseId;
    }
    
    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }*/
    
    
    /**
     * *
     * @hibernate.many-to-one class="com.joymain.jecs.pd.model.PdLogisticsBase"
     * @hibernate.column name="BASE_ID" not-null="true"
     * 
     */
    public PdLogisticsBase getPdLogisticsBase() {
		return pdLogisticsBase;
	}

	public void setPdLogisticsBase(PdLogisticsBase pdLogisticsBase) {
		this.pdLogisticsBase = pdLogisticsBase;
	}

    
    /**       
     *      *            @hibernate.property
     *             column="PDLOGISTICSBASENUM_NO"
     *             length="100"
     *         
     */

    public String getPdLogisticsBaseNum_no() {
        return this.pdLogisticsBaseNum_no;
    }
    
	public void setPdLogisticsBaseNum_no(String pdLogisticsBaseNum_no) {
        this.pdLogisticsBaseNum_no = pdLogisticsBaseNum_no;
    }
    /**       
     *      *            @hibernate.property
     *             column="NAME"
     *             length="200"
     *         
     */

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    /**       
     *      *            @hibernate.property
     *             column="STATUS"
     *             length="100"
     *         
     */

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    /**       
     *      *            @hibernate.property
     *             column="MAIL_TIME"
     *             length="7"
     *         
     */

    public Date getMailTime() {
        return this.mailTime;
    }
    
    public void setMailTime(Date mailTime) {
        this.mailTime = mailTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="OTHER_ONE"
     *             length="200"
     *         
     */

    public String getOtherOne() {
        return this.otherOne;
    }
    
    public void setOtherOne(String otherOne) {
        this.otherOne = otherOne;
    }
    /**       
     *      *            @hibernate.property
     *             column="OTHER_TWO"
     *             length="200"
     *         
     */

    public String getOtherTwo() {
        return this.otherTwo;
    }
    
    public void setOtherTwo(String otherTwo) {
        this.otherTwo = otherTwo;
    }

    /**
	 * *
	 * 
	 * @hibernate.list  inverse="true" cascade="all"
	 * @hibernate.collection-key column="NUM_ID"
	 * @hibernate.collection-index column="positionPdDetail"
	 * @hibernate.collection-one-to-many 
	 * class="com.joymain.jecs.pd.model.PdLogisticsBaseDetail"
	 * 
	 */
    public List<PdLogisticsBaseDetail> getPdLogisticsBaseDetail_items() {
		return pdLogisticsBaseDetail_items;
	}

	public void setPdLogisticsBaseDetail_items(List<PdLogisticsBaseDetail> ppdLogisticsBaseDetail_items) {
		pdLogisticsBaseDetail_items = ppdLogisticsBaseDetail_items;
	}

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
    //  buffer.append("baseId").append("='").append(getBaseId()).append("' ");			
      buffer.append("PdLogisticsBaseNum_no").append("='").append(getPdLogisticsBaseNum_no()).append("' ");			
      buffer.append("name").append("='").append(getName()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("mailTime").append("='").append(getMailTime()).append("' ");			
      buffer.append("otherOne").append("='").append(getOtherOne()).append("' ");			
      buffer.append("otherTwo").append("='").append(getOtherTwo()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdLogisticsBaseNum) ) return false;
		 PdLogisticsBaseNum castOther = ( PdLogisticsBaseNum ) other; 
         
		 return ( (this.getNumId()==castOther.getNumId()) || ( this.getNumId()!=null && castOther.getNumId()!=null && this.getNumId().equals(castOther.getNumId()) ) )
// && ( (this.getBaseId()==castOther.getBaseId()) || ( this.getBaseId()!=null && castOther.getBaseId()!=null && this.getBaseId().equals(castOther.getBaseId()) ) )
 && ( (this.getPdLogisticsBaseNum_no()==castOther.getPdLogisticsBaseNum_no()) || ( this.getPdLogisticsBaseNum_no()!=null && castOther.getPdLogisticsBaseNum_no()!=null && this.getPdLogisticsBaseNum_no().equals(castOther.getPdLogisticsBaseNum_no()) ) )
 && ( (this.getName()==castOther.getName()) || ( this.getName()!=null && castOther.getName()!=null && this.getName().equals(castOther.getName()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getMailTime()==castOther.getMailTime()) || ( this.getMailTime()!=null && castOther.getMailTime()!=null && this.getMailTime().equals(castOther.getMailTime()) ) )
 && ( (this.getOtherOne()==castOther.getOtherOne()) || ( this.getOtherOne()!=null && castOther.getOtherOne()!=null && this.getOtherOne().equals(castOther.getOtherOne()) ) )
 && ( (this.getOtherTwo()==castOther.getOtherTwo()) || ( this.getOtherTwo()!=null && castOther.getOtherTwo()!=null && this.getOtherTwo().equals(castOther.getOtherTwo()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getNumId() == null ? 0 : this.getNumId().hashCode() );
        // result = 37 * result + ( getBaseId() == null ? 0 : this.getBaseId().hashCode() );
         result = 37 * result + ( getPdLogisticsBaseNum_no() == null ? 0 : this.getPdLogisticsBaseNum_no().hashCode() );
         result = 37 * result + ( getName() == null ? 0 : this.getName().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getMailTime() == null ? 0 : this.getMailTime().hashCode() );
         result = 37 * result + ( getOtherOne() == null ? 0 : this.getOtherOne().hashCode() );
         result = 37 * result + ( getOtherTwo() == null ? 0 : this.getOtherTwo().hashCode() );
         return result;
   }   





}
