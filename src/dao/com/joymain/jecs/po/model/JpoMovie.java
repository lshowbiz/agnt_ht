package com.joymain.jecs.po.model;

import java.io.Serializable;
import java.util.Date;

import com.joymain.jecs.model.BaseObject;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="jpo_movie"
 *     
 */
public class JpoMovie extends BaseObject implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long mId;
	private String mname;
	private String maccount;
	private String mpwd;
	private Date museTime;
	private String orderNo;
	/**
	 * 0已使用, 未使用
	 */
	private char status;
	
	/**       
     *  @hibernate.id
     * 	generator-class="sequence"
     *  type="java.lang.Long"
     *  column="M_ID"
     *	@hibernate.generator-param name="sequence" value="SEQ_PO"
     *         
     */
	public Long getmId() {
		return mId;
	}

	public void setmId(Long mId) {
		this.mId = mId;
	}
	
	/**       
     * @hibernate.property column="M_NAME"
     */
	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	/**       
     * @hibernate.property column="M_ACCOUNT"
     */
	public String getMaccount() {
		return maccount;
	}

	public void setMaccount(String maccount) {
		this.maccount = maccount;
	}
	/**       
     * @hibernate.property column="M_PWD"
     */
	public String getMpwd() {
		return mpwd;
	}

	public void setMpwd(String mpwd) {
		this.mpwd = mpwd;
	}
	
	/**       
     * @hibernate.property column="M_USETIME"
     */
	public Date getMuseTime() {
		return museTime;
	}

	public void setMuseTime(Date museTime) {
		this.museTime = museTime;
	}
	/**       
     * @hibernate.property column="M_STATUS"
     */
	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
	/**       
     * @hibernate.property column="M_ORDERNO"
     */
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("mId").append("='").append(getmId()).append("', ");
        sb.append("mAccount").append("='").append(getMaccount()).append("', ");
        sb.append("mName").append("='").append(getMname()).append("', ");
        sb.append("mPwd").append("='").append(getMpwd()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("useTime").append("='").append(getMuseTime()).append("', ");
        sb.append("orderNo").append("='").append(getOrderNo());
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpoMovie pojo = (JpoMovie) o;

        if (maccount != null ? !maccount.equals(pojo.maccount) : pojo.maccount != null) return false;
        if (mname != null ? !mname.equals(pojo.mname) : pojo.mname != null) return false;
        if (mId != null ? !mId.equals(pojo.mId) : pojo.mId != null) return false;
        if (mpwd != null ? !mpwd.equals(pojo.mpwd) : pojo.mpwd != null) return false;
        if (orderNo != null ? !orderNo.equals(pojo.orderNo) : pojo.orderNo != null) return false;
        return true;
	}

	@Override
	public int hashCode() {
		 int result = 0;
	        result = (maccount != null ? maccount.hashCode() : 0);
	        result = 31 * result + (mname != null ? mname.hashCode() : 0);
	        result = 31 * result + (mpwd != null ? mpwd.hashCode() : 0);
	        result = 31 * result + (orderNo != null ? orderNo.hashCode() : 0);
	        return result;
	}

}
