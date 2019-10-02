package com.joymain.jecs.fi.model;
// Generated 2014-11-26 10:00:32 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="FI_BILL_ACCOUNT"
 *     
 */

public class FiBillAccount extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long accountId;
    private String billAccountCode;//商户号
    private String billAccountPassword; //证书密钥
    private String userCode;//经销商编号
    private String createUserCode;
    private String createUserName;
    private Date createTime;
    private String status;//状态：0，启用；1，停用
    private String remark;
    private String isDefault;
    private String accountName;
    private String registerEmail;
    private String linkNum;
    private String accountType;//终端类型：1，PC；2，移动终端
    
    private String providerType;//平台：1.快钱、2.畅捷通
    
    private String key;//密钥
    private String province; //所属省份 
    private Long maxMoney;//默认最大存储金额
    private String merchantType;//加密类型1.密钥，2.证书
    private String password;//前端验签的密钥，或者证书存储路径
    private String password2;//后端验签的密钥，或者证书存储路径
    private String busicode;//业务代码
    private String businessType="2";//商户类型，1.非全额，2全额
    // ConstructorsBUSINESS_TYPE

    /** default constructor */
    public FiBillAccount() {
    }

	/** minimal constructor */
    public FiBillAccount(String billAccountCode, String status) {
        this.billAccountCode = billAccountCode;
        this.status = status;
    }
   
    public FiBillAccount(String billAccountCode,
			String userCode, String createUserCode, String createUserName,
			Date createTime, String status, String remark, String isDefault,
			String accountName, String registerEmail, String linkNum,
			String accountType, String providerType) {

		this.billAccountCode = billAccountCode;
		this.userCode = userCode;
		this.createUserCode = createUserCode;
		this.createUserName = createUserName;
		this.createTime = createTime;
		this.status = status;
		this.remark = remark;
		this.isDefault = isDefault;
		this.accountName = accountName;
		this.registerEmail = registerEmail;
		this.linkNum = linkNum;
		this.accountType = accountType;
		this.providerType = providerType;
	}

	// Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="ACCOUNT_ID"
     *         
     */

    public Long getAccountId() {
        return this.accountId;
    }
    
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    
    
    
    
    
    
    /**       
     *      *      @hibernate.property
     *      	   type="java.lang.Long"
     *             column="MAX_MONEY"
     *         
     */
    public Long getMaxMoney() {
		return maxMoney;
	}

    /**
     * @spring.validator type="required"
     */
	public void setMaxMoney(Long maxMoney) {
		this.maxMoney = maxMoney;
	}
	
	/**       
     *      *      @hibernate.property
     *             column="PROVINCE"
     *             length="20"
     *         
     */
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	/**       
     *      *      @hibernate.property
     *             column="MERCHANT_TYPE"
     *             length="1"
     *         
     */
	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}
	/**       
     *      *      @hibernate.property
     *             column="PASSWORD"
     *             length="1024"
     *         
     */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	/**       
     *      *      @hibernate.property
     *             column="PASSWORD2"
     *             length="1024"
     *         
     */
	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	/**       
     *      *      @hibernate.property
     *             column="BUSICODE"
     *             length="256"
     *         
     */
	public String getBusicode() {
		return busicode;
	}

	public void setBusicode(String busicode) {
		this.busicode = busicode;
	}

	/**       
     *      *      @hibernate.property
     *             column="BUSINESS_TYPE"
     *             length="1"
     *         
     */
    public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	
	/**       
     *      *            @hibernate.property
     *             column="BILL_ACCOUNT_CODE"
     *             length="50"
     *             not-null="true"
     *         
     */
    public String getBillAccountCode() {
        return this.billAccountCode;
    }
    
    public void setBillAccountCode(String billAccountCode) {
        this.billAccountCode = billAccountCode;
    }
    
    
    /**       
     *      *            @hibernate.property
     *             column="BILL_ACCOUNT_PASSWORD"
     *             length="50"
     *         
     */
    public String getBillAccountPassword() {
		return billAccountPassword;
	}

	public void setBillAccountPassword(String billAccountPassword) {
		this.billAccountPassword = billAccountPassword;
	}

	/**       
     *      *            @hibernate.property
     *             column="USER_CODE"
     *             length="50"
     *         
     */

    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_USER_CODE"
     *             length="30"
     *         
     */

    public String getCreateUserCode() {
        return this.createUserCode;
    }
    
    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_USER_NAME"
     *             length="30"
     *         
     */

    public String getCreateUserName() {
        return this.createUserName;
    }
    
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
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
    /**       
     *      *            @hibernate.property
     *             column="STATUS"
     *             length="1"
     *             not-null="true"
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
     *             column="REMARK"
     *             length="100"
     *         
     */

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**       
     *      *            @hibernate.property
     *             column="IS_DEFAULT"
     *             length="1"
     *         
     */

    public String getIsDefault() {
        return this.isDefault;
    }
    
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }
    /**       
     *      *            @hibernate.property
     *             column="ACCOUNT_NAME"
     *             length="50"
     *         
     */

    public String getAccountName() {
        return this.accountName;
    }
    
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    /**       
     *      *            @hibernate.property
     *             column="REGISTER_EMAIL"
     *             length="50"
     *         
     */

    public String getRegisterEmail() {
        return this.registerEmail;
    }
    
    public void setRegisterEmail(String registerEmail) {
        this.registerEmail = registerEmail;
    }
    /**       
     *      *            @hibernate.property
     *             column="LINK_NUM"
     *             length="50"
     *         
     */

    public String getLinkNum() {
        return this.linkNum;
    }
    
    public void setLinkNum(String linkNum) {
        this.linkNum = linkNum;
    }
   
    /**       
     *      *            @hibernate.property
     *             column="ACCOUNT_TYPE"
     *             length="1"
     *         
     */
    public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/**       
     *      *            @hibernate.property
     *             column="PROVIDER_TYPE"
     *             length="1"
     *         
     */
	public String getProviderType() {
		return providerType;
	}

	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("billAccountCode").append("='").append(getBillAccountCode()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("createUserCode").append("='").append(getCreateUserCode()).append("' ");			
      buffer.append("createUserName").append("='").append(getCreateUserName()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("isDefault").append("='").append(getIsDefault()).append("' ");			
      buffer.append("accountName").append("='").append(getAccountName()).append("' ");			
      buffer.append("registerEmail").append("='").append(getRegisterEmail()).append("' ");			
      buffer.append("linkNum").append("='").append(getLinkNum()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FiBillAccount) ) return false;
		 FiBillAccount castOther = ( FiBillAccount ) other; 
         
		 return ( (this.getAccountId()==castOther.getAccountId()) || ( this.getAccountId()!=null && castOther.getAccountId()!=null && this.getAccountId().equals(castOther.getAccountId()) ) )
 && ( (this.getBillAccountCode()==castOther.getBillAccountCode()) || ( this.getBillAccountCode()!=null && castOther.getBillAccountCode()!=null && this.getBillAccountCode().equals(castOther.getBillAccountCode()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getCreateUserCode()==castOther.getCreateUserCode()) || ( this.getCreateUserCode()!=null && castOther.getCreateUserCode()!=null && this.getCreateUserCode().equals(castOther.getCreateUserCode()) ) )
 && ( (this.getCreateUserName()==castOther.getCreateUserName()) || ( this.getCreateUserName()!=null && castOther.getCreateUserName()!=null && this.getCreateUserName().equals(castOther.getCreateUserName()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getIsDefault()==castOther.getIsDefault()) || ( this.getIsDefault()!=null && castOther.getIsDefault()!=null && this.getIsDefault().equals(castOther.getIsDefault()) ) )
 && ( (this.getAccountName()==castOther.getAccountName()) || ( this.getAccountName()!=null && castOther.getAccountName()!=null && this.getAccountName().equals(castOther.getAccountName()) ) )
 && ( (this.getRegisterEmail()==castOther.getRegisterEmail()) || ( this.getRegisterEmail()!=null && castOther.getRegisterEmail()!=null && this.getRegisterEmail().equals(castOther.getRegisterEmail()) ) )
 && ( (this.getLinkNum()==castOther.getLinkNum()) || ( this.getLinkNum()!=null && castOther.getLinkNum()!=null && this.getLinkNum().equals(castOther.getLinkNum()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getAccountId() == null ? 0 : this.getAccountId().hashCode() );
         result = 37 * result + ( getBillAccountCode() == null ? 0 : this.getBillAccountCode().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getCreateUserCode() == null ? 0 : this.getCreateUserCode().hashCode() );
         result = 37 * result + ( getCreateUserName() == null ? 0 : this.getCreateUserName().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getIsDefault() == null ? 0 : this.getIsDefault().hashCode() );
         result = 37 * result + ( getAccountName() == null ? 0 : this.getAccountName().hashCode() );
         result = 37 * result + ( getRegisterEmail() == null ? 0 : this.getRegisterEmail().hashCode() );
         result = 37 * result + ( getLinkNum() == null ? 0 : this.getLinkNum().hashCode() );
         return result;
   }   





}
