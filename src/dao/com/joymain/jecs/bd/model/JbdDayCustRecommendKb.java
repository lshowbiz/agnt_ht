package com.joymain.jecs.bd.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_DAY_CUST_RECOMMEND_KB"
 *     
 */

public class JbdDayCustRecommendKb {

	private Long id;
	private Integer wweek;
	private String userCode;
	private String userName;
	private BigDecimal kb_money;
	private String kb_reason;
	private String operation_no;
	private Date operation_date;
	private Integer status;
	
	
	
	public JbdDayCustRecommendKb() {
		super();
	}
	public JbdDayCustRecommendKb(Long id, Integer wweek, String userCode, String userName, BigDecimal kb_money,
			String kb_reason, String operation_no, Date operation_date, Integer status) {
		super();
		this.id = id;
		this.wweek = wweek;
		this.userCode = userCode;
		this.userName = userName;
		this.kb_money = kb_money;
		this.kb_reason = kb_reason;
		this.operation_no = operation_no;
		this.operation_date = operation_date;
		this.status = status;
	}
	
	
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="ID"
     *         
     */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="W_WEEK"
	 *         
	 */
	public Integer getWweek() {
		return wweek;
	}
	public void setWweek(Integer wweek) {
		this.wweek = wweek;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="USER_CODE"
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
	 *             column="USER_NAME"
	 *         
	 */
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="KB_MONEY"
	 *         
	 */
	public BigDecimal getKb_money() {
		return kb_money;
	}
	public void setKb_money(BigDecimal kb_money) {
		this.kb_money = kb_money;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="KB_REASON"
	 *         
	 */
	public String getKb_reason() {
		return kb_reason;
	}
	public void setKb_reason(String kb_reason) {
		this.kb_reason = kb_reason;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="OPERATION_NO"
	 *         
	 */
	public String getOperation_no() {
		return operation_no;
	}
	public void setOperation_no(String operation_no) {
		this.operation_no = operation_no;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="OPERATION_DATE"
	 *         
	 */
	public Date getOperation_date() {
		return operation_date;
	}
	public void setOperation_date(Date operation_date) {
		this.operation_date = operation_date;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="STATUS"
	 *         
	 */
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((kb_money == null) ? 0 : kb_money.hashCode());
		result = prime * result + ((kb_reason == null) ? 0 : kb_reason.hashCode());
		result = prime * result + ((operation_date == null) ? 0 : operation_date.hashCode());
		result = prime * result + ((operation_no == null) ? 0 : operation_no.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((userCode == null) ? 0 : userCode.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((wweek == null) ? 0 : wweek.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JbdDayCustRecommendKb other = (JbdDayCustRecommendKb) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (kb_money == null) {
			if (other.kb_money != null)
				return false;
		} else if (!kb_money.equals(other.kb_money))
			return false;
		if (kb_reason == null) {
			if (other.kb_reason != null)
				return false;
		} else if (!kb_reason.equals(other.kb_reason))
			return false;
		if (operation_date == null) {
			if (other.operation_date != null)
				return false;
		} else if (!operation_date.equals(other.operation_date))
			return false;
		if (operation_no == null) {
			if (other.operation_no != null)
				return false;
		} else if (!operation_no.equals(other.operation_no))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (userCode == null) {
			if (other.userCode != null)
				return false;
		} else if (!userCode.equals(other.userCode))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (wweek == null) {
			if (other.wweek != null)
				return false;
		} else if (!wweek.equals(other.wweek))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "JbdDayCustRecommendKb [id=" + id + ", wweek=" + wweek + ", userCode=" + userCode + ", userName="
				+ userName + ", kb_money=" + kb_money + ", kb_reason=" + kb_reason + ", operation_no=" + operation_no
				+ ", operation_date=" + operation_date + ", status=" + status + "]";
	}
	
	

    
}
