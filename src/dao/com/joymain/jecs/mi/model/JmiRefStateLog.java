package com.joymain.jecs.mi.model;

import java.util.Date;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JMI_REF_STATE_LOG"
 *     
 */

public class JmiRefStateLog extends com.joymain.jecs.model.BaseObject implements
        java.io.Serializable
{
    
    private Long id;
    
    private String userCode;
    
    private String netType;
    
    private String operateType;
    
    private Date createTime;
    
    private String treeIndex;
    
    public String getTreeIndex()
    {
        return treeIndex;
    }

    public void setTreeIndex(String treeIndex)
    {
        this.treeIndex = treeIndex;
    }

    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID" 
     *@hibernate.generator-param name="sequence" value="SEQ_MI"
     *         
     */
    
    public Long getId()
    {
        return this.id;
    }
    
    public void setId(Long id)
    {
        this.id = id;
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
     *             column="NET_TYPE"
     *             length="20"
     *         
     */
    public String getNetType()
    {
        return netType;
    }
    
    public void setNetType(String netType)
    {
        this.netType = netType;
    }
    
    /**       
     *      *            @hibernate.property
     *             column="OPERATE_TYPE"
     *             length="1"
     *         
     */
    public String getOperateType()
    {
        return operateType;
    }
    
    public void setOperateType(String operateType)
    {
        this.operateType = operateType;
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
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((netType == null) ? 0 : netType.hashCode());
        result = prime * result
                + ((operateType == null) ? 0 : operateType.hashCode());
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
        JmiRefStateLog other = (JmiRefStateLog) obj;
        if (createTime == null)
        {
            if (other.createTime != null)
                return false;
        }
        else if (!createTime.equals(other.createTime))
            return false;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (netType == null)
        {
            if (other.netType != null)
                return false;
        }
        else if (!netType.equals(other.netType))
            return false;
        if (operateType == null)
        {
            if (other.operateType != null)
                return false;
        }
        else if (!operateType.equals(other.operateType))
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
        return "JmiRefStateLog [id=" + id + ", userCode=" + userCode
                + ", netType=" + netType + ", operateType=" + operateType
                + ", createTime=" + createTime + "]";
    }
    
}
