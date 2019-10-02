package com.joymain.jecs.mi.model;

import java.math.BigDecimal;
import java.util.Date;



import com.joymain.jecs.fi.model.FiCoinLog;
 
/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="Ws_Tp_Member"
 *     
 */
public class JmiRemitSale extends com.joymain.jecs.model.BaseObject implements java.io.Serializable{
 

	private static final long serialVersionUID = 1L;
	private BigDecimal id;
	private String userCode;
    private BigDecimal startWeek;
    private BigDecimal endWeek;
    private String createTime;
    private String remark;
    private String createUser;
	 

	@Override
	public String toString() {
		return "JmiRemitSale [id=" + id + ", userCode=" + userCode + ", startWeek=" + startWeek + ", endWeek=" + endWeek
				+ ", createTime=" + createTime + ", remark=" + remark + ", createUser=" + createUser +"]";
	}

	@Override
	public boolean equals(Object o) {
	     if(this.getId().compareTo(((JmiRemitSale)o).getId())==0){
	    	 return true;
	     }
		return false;
	}

	@Override
	public int hashCode() {
         int result = 71;      
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getCreateUser() == null ? 0 : this.getCreateUser().hashCode() );
       
         return result;
   }   
	
	
    /**       
     *    @hibernate.id
     *             generator-class="sequence"
     *             type="java.math.BigDecimal"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="SEQ_JBD"
     *         
     */
	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}
	
    /**       
     *      *            @hibernate.property
     *             column="USER_CODE"
     *       
     *     
     *         
     */
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}


    /**       
     *      *            @hibernate.property
     *             column="START_WEEK"
     *       
     *     
     *         
     */
	public BigDecimal getStartWeek() {
		return startWeek;
	}

	public void setStartWeek(BigDecimal startWeek) {
		this.startWeek = startWeek;
	}
    /**       
     *      *            @hibernate.property
     *             column="END_WEEK"
     *       
     *     
     *         
     */
	public BigDecimal getEndWeek() {
		return endWeek;
	}

	public void setEndWeek(BigDecimal endWeek) {
		this.endWeek = endWeek;
	}

 
    /**       
     *      *            @hibernate.property
     *             column="CREATE_TIME"
     *       
     *     
     *         
     */
	
	public String getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


    /**       
     *      *            @hibernate.property
     *             column="REMARK"
     *       
     *     
     *         
     */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    /**       
     *      *            @hibernate.property
     *             column="CREATE_USER"
     *       
     *     
     *         
     */
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}


}
