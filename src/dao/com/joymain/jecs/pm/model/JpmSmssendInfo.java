package com.joymain.jecs.pm.model;

import java.util.Date;




/**
 * Add By
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPM_SMSSEND_INFO"
 *     
 */

public class JpmSmssendInfo extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {

	private Long smsId;
	private String smsType;//短信类型  例如：1：仓库发货确认    2：找回密码   3：电影票  目前只有三种，在列表中配置
	private String smsRecipient;//短信接收人
	private String smsMessage;//短信内容
	private Date smsTime;//发送时间
	private String smsOperator;//操作人
	private String smsStatus;//保留字段，是否发送成功！ 默认为1：成功！ 现在短信还不能知道是否发送成功，没有返回值！
	private String remark;//备注
	private String phoneNum;//接收短信的手机号码
	private String remark2;//备注2
	
    /** default constructor */
    public JpmSmssendInfo() {
    }

    
    public JpmSmssendInfo(Long smsId, String smsType, String smsRecipient,
			String smsMessage, Date smsTime, String smsOperator,
			String smsStatus, String remark) {
		super();
		this.smsId = smsId;
		this.smsType = smsType;
		this.smsRecipient = smsRecipient;
		this.smsMessage = smsMessage;
		this.smsTime = smsTime;
		this.smsOperator = smsOperator;
		this.smsStatus = smsStatus;
		this.remark = remark;
	}

    /**       
	 *      @hibernate.id
	 *       generator-class="sequence"
	 *       type="java.lang.Long"
	 *       column="SMS_ID"
	 *      @hibernate.generator-param name="sequence" value="SEQ_SMS" 
	 */
	public Long getSmsId() {
		return smsId;
	}


	public void setSmsId(Long smsId) {
		this.smsId = smsId;
	}


	/**       
     *     @hibernate.property
     *      column="SMS_TYPE"
     *      length="2"
     *         
     */
	public String getSmsType() {
		return smsType;
	}


	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	/**       
     *     @hibernate.property
     *      column="SMS_RECIPIENT"
     *      length="15"
     *         
     */
	public String getSmsRecipient() {
		return smsRecipient;
	}


	public void setSmsRecipient(String smsRecipient) {
		this.smsRecipient = smsRecipient;
	}

	/**       
     *     @hibernate.property
     *      column="SMS_MESSAGE"
     *      length="500"
     *         
     */
	public String getSmsMessage() {
		return smsMessage;
	}


	public void setSmsMessage(String smsMessage) {
		this.smsMessage = smsMessage;
	}

	/**       
	 *      @hibernate.property
	 *       column="SMS_TIME"
	 *       length="7"
	 *         
	 */
	public Date getSmsTime() {
		return smsTime;
	}


	public void setSmsTime(Date smsTime) {
		this.smsTime = smsTime;
	}

	/**       
     *     @hibernate.property
     *      column="SMS_OPERATOR"
     *      length="20"
     *         
     */
	public String getSmsOperator() {
		return smsOperator;
	}


	public void setSmsOperator(String smsOperator) {
		this.smsOperator = smsOperator;
	}

	/**       
     *     @hibernate.property
     *      column="SMS_STATUS"
     *      length="1"
     *         
     */
	public String getSmsStatus() {
		return smsStatus;
	}


	public void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}

	/**       
     *     @hibernate.property
     *      column="REMARK"
     *      length="500"
     *         
     */
	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**       
     *     @hibernate.property
     *      column="REMARK2"
     *      length="100"
     *         
     */
	public String getRemark2() {
		return remark2;
	}


	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}


	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}


	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}


	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**       
     *     @hibernate.property
     *      column="PHONE_NUM"
     *      length="100"
     *         
     */
	public String getPhoneNum() {
		return phoneNum;
	}


	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	
}
