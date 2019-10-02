package com.joymain.jecs.am.model;
// Generated 2013-7-19 16:18:08 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PUBLIC_SCHEDULE"
 *     
 */

public class PublicSchedule extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

	private Long psId;
    private String name;
    private Date startTime;
    private Date endTime;
    private String content;
    private String type;



    // Constructors

    /** default constructor */
    public PublicSchedule() {
    }

    
    /** full constructor */
    public PublicSchedule(String name, Date startTime, Date endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="PS_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_SD"
     *         
     */
    public Long getPsId() {
        return this.psId;
    }
    
    public void setPsId(Long psId) {
        this.psId = psId;
    }
    /**       
     *      *            @hibernate.property
     *             column="NAME"
     *             length="50"
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
     *             column="START_TIME"
     *             length="7"
     *         
     */

    public Date getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="END_TIME"
     *             length="7"
     *         
     */

    public Date getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
   

    /**       
     *      *            @hibernate.property
     *             column="CONTENT"
     *             length="1000"
     *         
     */

	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	  /**       
     *      *            @hibernate.property
     *             column="TYPE"
     *             length="100"
     *         
     */

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("name").append("='").append(getName()).append("' ");			
      buffer.append("startTime").append("='").append(getStartTime()).append("' ");			
      buffer.append("endTime").append("='").append(getEndTime()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PublicSchedule) ) return false;
		 PublicSchedule castOther = ( PublicSchedule ) other; 
         
		 return ( (this.getPsId()==castOther.getPsId()) || ( this.getPsId()!=null && castOther.getPsId()!=null && this.getPsId().equals(castOther.getPsId()) ) )
 && ( (this.getName()==castOther.getName()) || ( this.getName()!=null && castOther.getName()!=null && this.getName().equals(castOther.getName()) ) )
 && ( (this.getStartTime()==castOther.getStartTime()) || ( this.getStartTime()!=null && castOther.getStartTime()!=null && this.getStartTime().equals(castOther.getStartTime()) ) )
 && ( (this.getEndTime()==castOther.getEndTime()) || ( this.getEndTime()!=null && castOther.getEndTime()!=null && this.getEndTime().equals(castOther.getEndTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getPsId() == null ? 0 : this.getPsId().hashCode() );
         result = 37 * result + ( getName() == null ? 0 : this.getName().hashCode() );
         result = 37 * result + ( getStartTime() == null ? 0 : this.getStartTime().hashCode() );
         result = 37 * result + ( getEndTime() == null ? 0 : this.getEndTime().hashCode() );
         return result;
   }   





}
