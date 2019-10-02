package com.joymain.jecs.po.model;
// Generated 2017-5-18 17:24:36 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPO_INVITE_LIST"
 *     
 */

public class JpoInviteList extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String userCode;
    private String inviteCode;
    private String memberOrderNo;
    private Date createTime;
    private String status;
    private String useUserCode;
    private Date useTime;
    private Long version=new Long(0);

    private String createUserCode;
    private String editUserCode;
    private Date editTime;
    private String inviteType;
    private String remark;
    
    
    // Constructors
    /**       
     *      *            @hibernate.property
     *             column="REMARK"
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
     *             column="INVITE_TYPE"
     *         
     */
    public String getInviteType() {
		return inviteType;
	}


	public void setInviteType(String inviteType) {
		this.inviteType = inviteType;
	}


	/** default constructor */
    public JpoInviteList() {
    }

    
    /** full constructor */
    public JpoInviteList(String userCode, String inviteCode, String memberOrderNo, Date createTime, String status, String useUserCode, Date useTime, Long version) {
        this.userCode = userCode;
        this.inviteCode = inviteCode;
        this.memberOrderNo = memberOrderNo;
        this.createTime = createTime;
        this.status = status;
        this.useUserCode = useUserCode;
        this.useTime = useTime;
        this.version = version;
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
     *      *            @hibernate.property
     *             column="USER_CODE"
     *             length="20"
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
     *             column="INVITE_CODE"
     *             unique="true"
     *             length="20"
     *         
     */

    public String getInviteCode() {
        return this.inviteCode;
    }
    
    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="MEMBER_ORDER_NO"
     *             length="20"
     *         
     */

    public String getMemberOrderNo() {
        return this.memberOrderNo;
    }
    
    public void setMemberOrderNo(String memberOrderNo) {
        this.memberOrderNo = memberOrderNo;
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
     *             column="USE_USER_CODE"
     *             length="20"
     *         
     */

    public String getUseUserCode() {
        return this.useUserCode;
    }
    
    public void setUseUserCode(String useUserCode) {
        this.useUserCode = useUserCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="USE_TIME"
     *             length="7"
     *         
     */

    public Date getUseTime() {
        return this.useTime;
    }
    
    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }
    
    
    /**       
     *      *            @hibernate.property
     *             column="CREATE_USER_CODE"
     *             length="20"
     *         
     */
    public String getCreateUserCode() {
		return createUserCode;
	}


	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}

	 /**       
     *      *            @hibernate.property
     *             column="EDIT_USER_CODE"
     *             length="20"
     *         
     */
	public String getEditUserCode() {
		return editUserCode;
	}


	public void setEditUserCode(String editUserCode) {
		this.editUserCode = editUserCode;
	}

	 /**       
     *      *            @hibernate.property
     *             column="EDIT_TIME"
     *             length="7"
     *         
     */
	public Date getEditTime() {
		return editTime;
	}


	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}


	/**       
     *      *            @hibernate.property
     *             column="VERSION"
     *             length="10"
     *         
     */

    public Long getVersion() {
        return this.version;
    }
    
    public void setVersion(Long version) {
        this.version = version;
    }
   

    /**
     * toString
     * @return String
     */
     @Override
	public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("inviteCode").append("='").append(getInviteCode()).append("' ");			
      buffer.append("memberOrderNo").append("='").append(getMemberOrderNo()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("useUserCode").append("='").append(getUseUserCode()).append("' ");			
      buffer.append("useTime").append("='").append(getUseTime()).append("' ");			
      buffer.append("version").append("='").append(getVersion()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   @Override
public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JpoInviteList) ) return false;
		 JpoInviteList castOther = ( JpoInviteList ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getInviteCode()==castOther.getInviteCode()) || ( this.getInviteCode()!=null && castOther.getInviteCode()!=null && this.getInviteCode().equals(castOther.getInviteCode()) ) )
 && ( (this.getMemberOrderNo()==castOther.getMemberOrderNo()) || ( this.getMemberOrderNo()!=null && castOther.getMemberOrderNo()!=null && this.getMemberOrderNo().equals(castOther.getMemberOrderNo()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getUseUserCode()==castOther.getUseUserCode()) || ( this.getUseUserCode()!=null && castOther.getUseUserCode()!=null && this.getUseUserCode().equals(castOther.getUseUserCode()) ) )
 && ( (this.getUseTime()==castOther.getUseTime()) || ( this.getUseTime()!=null && castOther.getUseTime()!=null && this.getUseTime().equals(castOther.getUseTime()) ) )
 && ( (this.getVersion()==castOther.getVersion()) || ( this.getVersion()!=null && castOther.getVersion()!=null && this.getVersion().equals(castOther.getVersion()) ) );
   }
   
   @Override
public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getInviteCode() == null ? 0 : this.getInviteCode().hashCode() );
         result = 37 * result + ( getMemberOrderNo() == null ? 0 : this.getMemberOrderNo().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getUseUserCode() == null ? 0 : this.getUseUserCode().hashCode() );
         result = 37 * result + ( getUseTime() == null ? 0 : this.getUseTime().hashCode() );
         result = 37 * result + ( getVersion() == null ? 0 : this.getVersion().hashCode() );
         return result;
   }   





}
