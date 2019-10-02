package com.joymain.jecs.bd.model;

import java.util.Date;

// Generated 2009-9-26 10:23:41 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_MEMBER_STAR_REWARDS"
 *     
 */

public class JbdMemberStarRewards extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {

    private Long id;
    private Integer fyear;
    private String userCode;
    private Integer rewards;
    private String isReach;
    private String remark;//备注
    private String memberRemark;//会员备注
    private String createUser;
    private Date createTime;
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="SEQ_BD"
     */
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    /**       
     *      *            @hibernate.property
     *             column="F_YEAR"
     *             length="8"
     *         
     */
    public Integer getFyear()
    {
        return fyear;
    }
    public void setFyear(Integer fyear)
    {
        this.fyear = fyear;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_CODE"
     *             length="20"
     *         
     */
    public String getUserCode()
    {
        return userCode;
    }
    public void setUserCode(String userCode)
    {
        this.userCode = userCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="REWARDS"
     *             length="2"
     *         
     */
    public Integer getRewards()
    {
        return rewards;
    }
    public void setRewards(Integer rewards)
    {
        this.rewards = rewards;
    }
    /**       
     *      *            @hibernate.property
     *             column="IS_REACH"
     *             length="1"
     *         
     */
    public String getIsReach()
    {
        return isReach;
    }
    public void setIsReach(String isReach)
    {
        this.isReach = isReach;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMARK"
     *             length="4000"
     *         
     */
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    /**       
     *      *            @hibernate.property
     *             column="MEMBER_REMARK"
     *             length="4000"
     *         
     */
    public String getMemberRemark()
    {
        return memberRemark;
    }
    public void setMemberRemark(String memberRemark)
    {
        this.memberRemark = memberRemark;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_USER"
     *             length="20"
     *         
     */
    public String getCreateUser()
    {
        return createUser;
    }
    public void setCreateUser(String createUser)
    {
        this.createUser = createUser;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_TIME"
     *         
     */
    public Date getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((createTime == null) ? 0 : createTime.hashCode());
        result = prime * result
                + ((createUser == null) ? 0 : createUser.hashCode());
        result = prime * result + ((fyear == null) ? 0 : fyear.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((isReach == null) ? 0 : isReach.hashCode());
        result = prime * result + ((remark == null) ? 0 : remark.hashCode());
        result = prime * result + ((rewards == null) ? 0 : rewards.hashCode());
        result = prime * result
                + ((userCode == null) ? 0 : userCode.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JbdMemberStarRewards other = (JbdMemberStarRewards) obj;
        if (createTime == null)
        {
            if (other.createTime != null)
                return false;
        }
        else if (!createTime.equals(other.createTime))
            return false;
        if (createUser == null)
        {
            if (other.createUser != null)
                return false;
        }
        else if (!createUser.equals(other.createUser))
            return false;
        if (fyear == null)
        {
            if (other.fyear != null)
                return false;
        }
        else if (!fyear.equals(other.fyear))
            return false;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (isReach == null)
        {
            if (other.isReach != null)
                return false;
        }
        else if (!isReach.equals(other.isReach))
            return false;
        if (remark == null)
        {
            if (other.remark != null)
                return false;
        }
        else if (!remark.equals(other.remark))
            return false;
        if (rewards == null)
        {
            if (other.rewards != null)
                return false;
        }
        else if (!rewards.equals(other.rewards))
            return false;
        if (userCode == null)
        {
            if (other.userCode != null)
                return false;
        }
        else if (!userCode.equals(other.userCode))
            return false;
        return true;
    }
    @Override
    public String toString()
    {
        return "JbdMemberStarRewards [id=" + id + ", fyear=" + fyear
                + ", userCode=" + userCode + ", rewards=" + rewards
                + ", isReach=" + isReach + ", remark=" + remark
                + ", createUser=" + createUser + ", createTime=" + createTime
                + "]";
    }
   
}
