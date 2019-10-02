package com.joymain.jecs.fi.model;

import java.util.Date;
// Generated 2016-2-17 18:44:41 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_AMOUNT_DETAIL"
 *     
 */

public class JfiAmountDetail extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private Long quotaId;
    private JfiQuota jfiQuota = new JfiQuota();
    private String memberOrderNo;
    private String money;
    private String userCode;
    private Date createTime;

    // Constructors

    /** default constructor */
    public JfiAmountDetail() {
    }

    
    /** full constructor */
    public JfiAmountDetail(Long quotaId, String memberOrderNo, String money, String userCode) {
        this.quotaId = quotaId;
        this.memberOrderNo = memberOrderNo;
        this.money = money;
        this.userCode = userCode;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="SEQ_FI"
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
     *             column="QUOTA_ID"
     *             length="12"
     *         
     */

    public Long getQuotaId() {
        return this.quotaId;
    }
    
    public void setQuotaId(Long quotaId) {
        this.quotaId = quotaId;
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
     *             column="MONEY"
     *             length="256"
     *         
     */

    public String getMoney() {
        return this.money;
    }
    
    public void setMoney(String money) {
        this.money = money;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_CODE"
     *             length="10"
     *         
     */

    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("quotaId").append("='").append(getQuotaId()).append("' ");			
      buffer.append("memberOrderNo").append("='").append(getMemberOrderNo()).append("' ");			
      buffer.append("money").append("='").append(getMoney()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiAmountDetail) ) return false;
		 JfiAmountDetail castOther = ( JfiAmountDetail ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getQuotaId()==castOther.getQuotaId()) || ( this.getQuotaId()!=null && castOther.getQuotaId()!=null && this.getQuotaId().equals(castOther.getQuotaId()) ) )
 && ( (this.getMemberOrderNo()==castOther.getMemberOrderNo()) || ( this.getMemberOrderNo()!=null && castOther.getMemberOrderNo()!=null && this.getMemberOrderNo().equals(castOther.getMemberOrderNo()) ) )
 && ( (this.getMoney()==castOther.getMoney()) || ( this.getMoney()!=null && castOther.getMoney()!=null && this.getMoney().equals(castOther.getMoney()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getQuotaId() == null ? 0 : this.getQuotaId().hashCode() );
         result = 37 * result + ( getMemberOrderNo() == null ? 0 : this.getMemberOrderNo().hashCode() );
         result = 37 * result + ( getMoney() == null ? 0 : this.getMoney().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         return result;
   }

   /**
	 * *
	 * @hibernate.many-to-one not-null="true" inverse="true" insert="false" update="false" lazy="false"
	 * @hibernate.column name="QUOTA_ID"
	 * 
	 */
	public JfiQuota getJfiQuota() {
		return jfiQuota;
	}
	
	
	public void setJfiQuota(JfiQuota jfiQuota) {
		this.jfiQuota = jfiQuota;
	}


	/**       
	 *     @hibernate.property
	 *      column="CREATE_TIME"
	 *         
	 */
	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}   
}
