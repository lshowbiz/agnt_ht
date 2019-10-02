package com.joymain.jecs.mi.model;

import java.util.Date;
// Generated 2009-9-14 16:09:19 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JMI_MEMBER_UPGRADE_NOTE"
 *     
 */

public class JmiMemberUpgradeNote extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long munId;
    private JmiMember jmiMember;
    private String newCard;
    private String oldCard;
    private String companyCode;
    private String memberOrderNo;
    private Date updateDate;
    private String updateType;
    private String remark;


    // Constructors

    /** default constructor */
    public JmiMemberUpgradeNote() {
    }

	/** minimal constructor */
    public JmiMemberUpgradeNote(String userCode, String newCard, String oldCard, String companyCode) {
        this.newCard = newCard;
        this.oldCard = oldCard;
        this.companyCode = companyCode;
    }
    
    /** full constructor */
    public JmiMemberUpgradeNote(String userCode, String newCard, String oldCard, String companyCode, String memberOrderNo, Date updateDate, String updateType, String remark) {
        this.newCard = newCard;
        this.oldCard = oldCard;
        this.companyCode = companyCode;
        this.memberOrderNo = memberOrderNo;
        this.updateDate = updateDate;
        this.updateType = updateType;
        this.remark = remark;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="MUN_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_MI"
     *         
     */

    public Long getMunId() {
        return this.munId;
    }
    
    public void setMunId(Long munId) {
        this.munId = munId;
    }

    /**       
     *      *            @hibernate.property
     *             column="NEW_CARD"
     *             length="1"
     *             not-null="true"
     *         
     */

    public String getNewCard() {
        return this.newCard;
    }
    
    public void setNewCard(String newCard) {
        this.newCard = newCard;
    }
    /**       
     *      *            @hibernate.property
     *             column="OLD_CARD"
     *             length="1"
     *             not-null="true"
     *         
     */

    public String getOldCard() {
        return this.oldCard;
    }
    
    public void setOldCard(String oldCard) {
        this.oldCard = oldCard;
    }
    /**       
     *      *            @hibernate.property
     *             column="COMPANY_CODE"
     *             length="2"
     *             not-null="true"
     *         
     */

    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
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
     *             column="UPDATE_DATE"
     *             length="7"
     *         
     */

    public Date getUpdateDate() {
        return this.updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    /**       
     *      *            @hibernate.property
     *             column="UPDATE_TYPE"
     *             length="1"
     *         
     */

    public String getUpdateType() {
        return this.updateType;
    }
    
    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMARK"
     *             length="200"
     *         
     */

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   
    /**
     * *
     * @hibernate.many-to-one not-null="true"
     * @hibernate.column name="USER_CODE"
     * 
     */
    public JmiMember getJmiMember() {
		return jmiMember;
	}

	public void setJmiMember(JmiMember jmiMember) {
		this.jmiMember = jmiMember;
	}

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");		
      buffer.append("newCard").append("='").append(getNewCard()).append("' ");			
      buffer.append("oldCard").append("='").append(getOldCard()).append("' ");			
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("memberOrderNo").append("='").append(getMemberOrderNo()).append("' ");			
      buffer.append("updateDate").append("='").append(getUpdateDate()).append("' ");			
      buffer.append("updateType").append("='").append(getUpdateType()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JmiMemberUpgradeNote) ) return false;
		 JmiMemberUpgradeNote castOther = ( JmiMemberUpgradeNote ) other; 
         
		 return ( (this.getMunId()==castOther.getMunId()) || ( this.getMunId()!=null && castOther.getMunId()!=null && this.getMunId().equals(castOther.getMunId()) ) )
 && ( (this.getNewCard()==castOther.getNewCard()) || ( this.getNewCard()!=null && castOther.getNewCard()!=null && this.getNewCard().equals(castOther.getNewCard()) ) )
 && ( (this.getOldCard()==castOther.getOldCard()) || ( this.getOldCard()!=null && castOther.getOldCard()!=null && this.getOldCard().equals(castOther.getOldCard()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getMemberOrderNo()==castOther.getMemberOrderNo()) || ( this.getMemberOrderNo()!=null && castOther.getMemberOrderNo()!=null && this.getMemberOrderNo().equals(castOther.getMemberOrderNo()) ) )
 && ( (this.getUpdateDate()==castOther.getUpdateDate()) || ( this.getUpdateDate()!=null && castOther.getUpdateDate()!=null && this.getUpdateDate().equals(castOther.getUpdateDate()) ) )
 && ( (this.getUpdateType()==castOther.getUpdateType()) || ( this.getUpdateType()!=null && castOther.getUpdateType()!=null && this.getUpdateType().equals(castOther.getUpdateType()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getMunId() == null ? 0 : this.getMunId().hashCode() );
         result = 37 * result + ( getNewCard() == null ? 0 : this.getNewCard().hashCode() );
         result = 37 * result + ( getOldCard() == null ? 0 : this.getOldCard().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getMemberOrderNo() == null ? 0 : this.getMemberOrderNo().hashCode() );
         result = 37 * result + ( getUpdateDate() == null ? 0 : this.getUpdateDate().hashCode() );
         result = 37 * result + ( getUpdateType() == null ? 0 : this.getUpdateType().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         return result;
   }   





}
