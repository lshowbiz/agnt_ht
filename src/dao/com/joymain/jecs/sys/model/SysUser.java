package com.joymain.jecs.sys.model;

import java.util.Date;

import com.joymain.jecs.Constants;
import com.joymain.jecs.mi.model.JmiMember;

// Generated 2008-3-8 15:17:35 by Hibernate Tools 3.1.0.beta4

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="JSYS_USER"
 */

public class SysUser extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {

	// Fields
	private String userCode;

	protected String userName;

	private String password;

	private String password2;

	private String state;

	private String companyCode;

	private String userType;

	private String defCharacterCoding;
	private String userArea;
	private Integer ipCheck;
	private String firstName;
	private String lastName;
	private String honorStar;
	
	private Date lastLoginErrorTime;
	private Integer failureTimes=0;
	private Integer errNum=0;
	


	//登录信息,与数据库不作关联
	private boolean isAgent;
	private boolean isMember;
	private boolean isCompany;
	private boolean isManager;
	private boolean isAdmin;
	private boolean authorized;
	private Long operationLogId;
	private String dataMonth;
	private SysUser operatorSysUser;
	private String upGrade;
	private JmiMember jmiMember;
	private String sendStatus;
	private String remark;
	private String token;
	
	/**
	 * *
	 * 
	 * @hibernate.property column="ERR_NUM" 
	 * 
	 */
	public Integer getErrNum() {
		return errNum;
	}

	public void setErrNum(Integer errNum) {
		this.errNum = errNum;
	}
	
	public SysUser(String userCode, String userName) {
		super();
		this.userCode = userCode;
		this.userName = userName;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="TOKEN" 
	 * 
	 */
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="REMARK" 
	 * 
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="SEND_STATUS" 
	 * 
	 */
	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	//	private JmiMember jmiMember = new JmiMember();
//	// Constructors
//
//	public JmiMember getJmiMember() {
//		return jmiMember;
//	}
//
//	public void setJmiMember(JmiMember jmiMember) {
//		this.jmiMember = jmiMember;
//	}
	/** default constructor */
	public SysUser() {
	}

	// Property accessors
	/**
	 * *
	 * 
	 * @hibernate.id generator-class="assigned" type="java.lang.String"
	 *               column="USER_CODE" length="20"
	 * 
	 */
	public String getUserCode() {
		return userCode;
	}
	/**
	 * @hibernate.one-to-one cascade="none" constrained="false"
	 */
	public JmiMember getJmiMember() {
		return jmiMember;
	}

	public void setJmiMember(JmiMember jmiMember) {
		this.jmiMember = jmiMember;
	}


	/**
	 * *
	 * 
	 * @hibernate.property column="HONOR_STAR" 
	 * 
	 */
	public String getHonorStar() {
		return honorStar;
	}

	public void setHonorStar(String honorStar) {
		this.honorStar = honorStar;
	}
	
	/**
	 * *
	 * 
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-var name="maxlength" value="20"
	 * @spring.validator-args arg1value="20"
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="PASSWORD2" length="32"
	 * 
	 */
	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="STATE" length="1" not-null="true"
	 * 
	 */
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="USER_NAME" length="16" not-null="false"
	 * 
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * *
	 * 
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-var name="maxlength" value="8"
	 * @spring.validator-args arg1value="8"
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="PASSWORD" length="32" not-null="true"
	 * 
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * *
	 * 
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-var name="maxlength" value="32"
	 * @spring.validator-args arg1value="32"
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="COMPANY_CODE" length="2" not-null="true"
	 * 
	 */
	public String getCompanyCode() {
		return this.companyCode;
	}

	/**
	 * @spring.validator type="required"
	 * @spring.validator-args arg0resource="sys.common.companyCode"
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="USER_TYPE" length="2" not-null="true"
	 * 
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @spring.validator type="required"
	 * @param userType
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="DEF_CHARACTER_CODING" length="10"
	 *                     not-null="true"
	 * 
	 */
	public String getDefCharacterCoding() {
		return defCharacterCoding;
	}

	/**
	 * @spring.validator type="required"
	 */
	public void setDefCharacterCoding(String defCharacterCoding) {
		this.defCharacterCoding = defCharacterCoding;
	}

	/**
	 * toString
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		buffer.append("userCode").append("='").append(getUserCode()).append("' ");
		buffer.append("userName").append("='").append(getUserName()).append("' ");
		buffer.append("state").append("='").append(getState()).append("' ");
		buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");
		buffer.append("userType").append("='").append(getUserType()).append("' ");
		buffer.append("defCharacterCoding").append("='").append(getDefCharacterCoding()).append("' ");
		buffer.append("]");

		return buffer.toString();
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysUser))
			return false;
		SysUser castOther = (SysUser) other;

		return ((this.getUserCode() == castOther.getUserCode()) || (this.getUserCode() != null && castOther.getUserCode() != null && this
				.getUserCode().equals(castOther.getUserCode())))
				&& ((this.getUserName() == castOther.getUserName()) || (this.getUserName() != null && castOther.getUserName() != null && this
						.getUserName().equals(castOther.getUserName())))
				&& ((this.getUserType() == castOther.getUserType()) || (this.getUserType() != null && castOther.getUserType() != null && this
						.getUserType().equals(castOther.getUserType())))
				&& ((this.getCompanyCode() == castOther.getCompanyCode()) || (this.getCompanyCode() != null && castOther.getCompanyCode() != null && this
						.getCompanyCode().equals(castOther.getCompanyCode())));
	}

	public int hashCode() {
		int result = 17;
		result = 37 * result + (getUserCode() == null ? 0 : this.getUserCode().hashCode());
		result = 37 * result + (getUserName() == null ? 0 : this.getUserName().hashCode());
		result = 37 * result + (getState() == null ? 0 : this.getState().hashCode());
		result = 37 * result + (getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode());
		return result;
	}
	/**
	 * *
	 * 
	 * @hibernate.property column="USER_AREA" 
	 * 
	 */
	public String getUserArea() {
		return userArea;
	}

	public void setUserArea(String userArea) {
		this.userArea = userArea;
	}

	/**
	 * @hibernate.property column="IP_CHECK" length="2"
	 */
	public Integer getIpCheck() {
    	return ipCheck;
    }

	public void setIpCheck(Integer ipCheck) {
    	this.ipCheck = ipCheck;
    }
	/**
	 * *
	 * 
	 * @hibernate.property column="FIRST_NAME" length="100"
	 * 
	 */
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * *
	 * 
	 * @hibernate.property column="LAST_NAME" length="100"
	 * 
	 */
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="LAST_LOGIN_ERROR_TIME" 
	 * 
	 */
	public Date getLastLoginErrorTime() {
		return lastLoginErrorTime;
	}

	public void setLastLoginErrorTime(Date lastLoginErrorTime) {
		this.lastLoginErrorTime = lastLoginErrorTime;
	}
	
	/**
	 * *
	 * 
	 * @hibernate.property column="FAILURE_TIMES" 
	 * 
	 */
	public Integer getFailureTimes() {
		return failureTimes;
	}

	public void setFailureTimes(Integer failureTimes) {
		this.failureTimes = failureTimes;
	}

	
	//登录信息,与数据库不做关联
	public boolean getIsAdmin() {
		this.isAdmin= Constants.ROOT_ACCOUNT.equals(this.getUserCode());
		return this.isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	/**
	 * 是否代理商
	 * 
	 * @return
	 */
	public boolean getIsAgent() {
		this.isAgent = ("P".equals(this.getUserType()) || "Q".equals(this.getUserType()));
		return this.isAgent;
	}

	/**
	 * 是否会员
	 * 
	 * @return
	 */
	public boolean getIsMember() {
		this.isMember = "M".equals(this.getUserType());
		return this.isMember;
	}

	/**
	 * 是否管理中心
	 * 
	 * @return
	 */
	public boolean getIsManager() {
		this.isManager = "AA".equals(this.getCompanyCode());
		return this.isManager;
	}

	/**
	 * 是否公司管理人员
	 * 
	 * @return
	 */
	public boolean getIsCompany() {
		this.isCompany = "C".equals(this.getUserType()) && !"AA".equals(this.getCompanyCode());
		return this.isCompany;
	}

	public void setIsAgent(boolean isAgent) {
		this.isAgent = isAgent;
	}

	public void setIsMember(boolean isMember) {
		this.isMember = isMember;
	}

	public void setIsCompany(boolean isCompany) {
		this.isCompany = isCompany;
	}

	public void setIsManager(boolean isManager) {
		this.isManager = isManager;
	}

	/**
	 * 已登录
	 * @return
	 */
	public boolean isAuthorized() {
    	return authorized;
    }

	public void setAuthorized(boolean authorized) {
    	this.authorized = authorized;
    }

	public String getUpGrade() {
		return upGrade;
	}

	public void setUpGrade(String upGrade) {
		this.upGrade = upGrade;
	}

	 public SysUser getOperatorSysUser() {
		return operatorSysUser;
	}

	public void setOperatorSysUser(SysUser operatorSysUser) {
		this.operatorSysUser = operatorSysUser;
	}

	public Long getOperationLogId() {
    	return operationLogId;
    }

	public void setOperationLogId(Long operationLogId) {
    	this.operationLogId = operationLogId;
    }

	public String getDataMonth() {
    	return dataMonth;
    }

	public void setDataMonth(String dataMonth) {
    	this.dataMonth = dataMonth;
    }
}