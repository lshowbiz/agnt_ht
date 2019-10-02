package com.joymain.jecs.bd.model;
// Generated 2009-9-25 12:00:42 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_PERIOD"
 *     
 */

public class BdPeriod extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long wid;
    private Integer wyear;
    private Integer wmonth;
    private Integer wweek;
    private Date startTime;
    private Date endTime;
    private Integer lastMark;
    private Integer bonusSend;
    private Integer archivingStatus;
    private Integer monthStatus;
    private Long oldWeek;
    private Integer fyear;
    private Integer fmonth;
    private Integer fweek;
    private Integer aweek;
    private Integer dayStatus;


    // Constructors

    /** default constructor */
    public BdPeriod() {
    }

    
    /** full constructor */
    public BdPeriod(Integer wyear, Integer wmonth, Integer wweek, Date startTime, Date endTime, Integer lastMark, Integer bonusSend, Integer archivingStatus, Integer monthStatus, Long oldWeek) {
        this.wyear = wyear;
        this.wmonth = wmonth;
        this.wweek = wweek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lastMark = lastMark;
        this.bonusSend = bonusSend;
        this.archivingStatus = archivingStatus;
        this.monthStatus = monthStatus;
        this.oldWeek = oldWeek;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="ID"
     *         
     */

    public Long getWid() {
        return this.wid;
    }
    
    public void setWid(Long wid) {
        this.wid = wid;
    }
    /**       
     *      *            @hibernate.property
     *             column="W_YEAR"
     *             length="8"
     *         
     */

    public Integer getWyear() {
        return this.wyear;
    }
    
    public void setWyear(Integer wyear) {
        this.wyear = wyear;
    }
    /**       
     *      *            @hibernate.property
     *             column="W_MONTH"
     *             length="8"
     *         
     */

    public Integer getWmonth() {
        return this.wmonth;
    }
    
    public void setWmonth(Integer wmonth) {
        this.wmonth = wmonth;
    }
    /**       
     *      *            @hibernate.property
     *             column="W_WEEK"
     *             length="8"
     *         
     */

    public Integer getWweek() {
        return this.wweek;
    }
    
    public void setWweek(Integer wweek) {
        this.wweek = wweek;
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
     *             column="LAST_MARK"
     *             length="2"
     *         
     */

    public Integer getLastMark() {
        return this.lastMark;
    }
    
    public void setLastMark(Integer lastMark) {
        this.lastMark = lastMark;
    }
    /**       
     *      *            @hibernate.property
     *             column="BONUS_SEND"
     *             length="2"
     *         
     */

    public Integer getBonusSend() {
        return this.bonusSend;
    }
    
    public void setBonusSend(Integer bonusSend) {
        this.bonusSend = bonusSend;
    }
    /**       
     *      *            @hibernate.property
     *             column="ARCHIVING_STATUS"
     *             length="2"
     *         
     */

    public Integer getArchivingStatus() {
        return this.archivingStatus;
    }
    
    public void setArchivingStatus(Integer archivingStatus) {
        this.archivingStatus = archivingStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="MONTH_STATUS"
     *             length="2"
     *         
     */

    public Integer getMonthStatus() {
        return this.monthStatus;
    }
    
    public void setMonthStatus(Integer monthStatus) {
        this.monthStatus = monthStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="OLD_WEEK"
     *             length="10"
     *         
     */

    public Long getOldWeek() {
        return this.oldWeek;
    }
    
    public void setOldWeek(Long oldWeek) {
        this.oldWeek = oldWeek;
    }

    /**       
     *      *            @hibernate.property
     *             column="F_MONTH"
     *             length="8"
     *         
     */
    public Integer getFmonth() {
    	return fmonth;
    }


    public void setFmonth(Integer fmonth) {
    	this.fmonth = fmonth;
    }


    /**       
     *      *            @hibernate.property
     *             column="F_WEEK"
     *             length="8"
     *         
     */
    public Integer getFweek() {
    	return fweek;
    }


    public void setFweek(Integer fweek) {
    	this.fweek = fweek;
    }


    /**       
     *      *            @hibernate.property
     *             column="F_YEAR"
     *             length="8"
     *         
     */
    public Integer getFyear() {
    	return fyear;
    }


    public void setFyear(Integer fyear) {
    	this.fyear = fyear;
    } 


    /**       
     *      *            @hibernate.property
     *             column="A_WEEK"
     *             length="8"
     *         
     */
    public Integer getAweek() {
    	return aweek;
    }


    public void setAweek(Integer aweek) {
    	this.aweek = aweek;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("wyear").append("='").append(getWyear()).append("' ");			
      buffer.append("wmonth").append("='").append(getWmonth()).append("' ");			
      buffer.append("wweek").append("='").append(getWweek()).append("' ");			
      buffer.append("startTime").append("='").append(getStartTime()).append("' ");			
      buffer.append("endTime").append("='").append(getEndTime()).append("' ");			
      buffer.append("lastMark").append("='").append(getLastMark()).append("' ");			
      buffer.append("bonusSend").append("='").append(getBonusSend()).append("' ");			
      buffer.append("archivingStatus").append("='").append(getArchivingStatus()).append("' ");			
      buffer.append("monthStatus").append("='").append(getMonthStatus()).append("' ");			
      buffer.append("oldWeek").append("='").append(getOldWeek()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BdPeriod) ) return false;
		 BdPeriod castOther = ( BdPeriod ) other; 
         
		 return ( (this.getWid()==castOther.getWid()) || ( this.getWid()!=null && castOther.getWid()!=null && this.getWid().equals(castOther.getWid()) ) )
 && ( (this.getWyear()==castOther.getWyear()) || ( this.getWyear()!=null && castOther.getWyear()!=null && this.getWyear().equals(castOther.getWyear()) ) )
 && ( (this.getWmonth()==castOther.getWmonth()) || ( this.getWmonth()!=null && castOther.getWmonth()!=null && this.getWmonth().equals(castOther.getWmonth()) ) )
 && ( (this.getWweek()==castOther.getWweek()) || ( this.getWweek()!=null && castOther.getWweek()!=null && this.getWweek().equals(castOther.getWweek()) ) )
 && ( (this.getStartTime()==castOther.getStartTime()) || ( this.getStartTime()!=null && castOther.getStartTime()!=null && this.getStartTime().equals(castOther.getStartTime()) ) )
 && ( (this.getEndTime()==castOther.getEndTime()) || ( this.getEndTime()!=null && castOther.getEndTime()!=null && this.getEndTime().equals(castOther.getEndTime()) ) )
 && ( (this.getLastMark()==castOther.getLastMark()) || ( this.getLastMark()!=null && castOther.getLastMark()!=null && this.getLastMark().equals(castOther.getLastMark()) ) )
 && ( (this.getBonusSend()==castOther.getBonusSend()) || ( this.getBonusSend()!=null && castOther.getBonusSend()!=null && this.getBonusSend().equals(castOther.getBonusSend()) ) )
 && ( (this.getArchivingStatus()==castOther.getArchivingStatus()) || ( this.getArchivingStatus()!=null && castOther.getArchivingStatus()!=null && this.getArchivingStatus().equals(castOther.getArchivingStatus()) ) )
 && ( (this.getMonthStatus()==castOther.getMonthStatus()) || ( this.getMonthStatus()!=null && castOther.getMonthStatus()!=null && this.getMonthStatus().equals(castOther.getMonthStatus()) ) )
 && ( (this.getOldWeek()==castOther.getOldWeek()) || ( this.getOldWeek()!=null && castOther.getOldWeek()!=null && this.getOldWeek().equals(castOther.getOldWeek()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getWid() == null ? 0 : this.getWid().hashCode() );
         result = 37 * result + ( getWyear() == null ? 0 : this.getWyear().hashCode() );
         result = 37 * result + ( getWmonth() == null ? 0 : this.getWmonth().hashCode() );
         result = 37 * result + ( getWweek() == null ? 0 : this.getWweek().hashCode() );
         result = 37 * result + ( getStartTime() == null ? 0 : this.getStartTime().hashCode() );
         result = 37 * result + ( getEndTime() == null ? 0 : this.getEndTime().hashCode() );
         result = 37 * result + ( getLastMark() == null ? 0 : this.getLastMark().hashCode() );
         result = 37 * result + ( getBonusSend() == null ? 0 : this.getBonusSend().hashCode() );
         result = 37 * result + ( getArchivingStatus() == null ? 0 : this.getArchivingStatus().hashCode() );
         result = 37 * result + ( getMonthStatus() == null ? 0 : this.getMonthStatus().hashCode() );
         result = 37 * result + ( getOldWeek() == null ? 0 : this.getOldWeek().hashCode() );
         return result;
   }


   /**       
    *      *            @hibernate.property
    *             column="DAY_STATUS"
    *             length="8"
    *         
    */
public Integer getDayStatus() {
	return dayStatus;
}


public void setDayStatus(Integer dayStatus) {
	this.dayStatus = dayStatus;
}

  





}
