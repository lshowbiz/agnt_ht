package com.joymain.jecs.po.model;

import com.joymain.jecs.sys.model.SysUser;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="JPO_CHECKED_FAILED"
 * 
 */
public class JpoCheckedFailed extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long FId;
   
    private JpoMemberOrder jpoMemberOrder;
    
    private SysUser operatorSysuser; 
    
    private String dataType;
    
    /**
	 * @hibernate.id generator-class="sequence" type="java.lang.Long" column="F_ID"
	 * @hibernate.generator-param name="sequence" value="SEQ_PO"
	 * 
	 */
    public Long getFId() {
        return this.FId;
    }
    
    public void setFId(Long FId) {
        this.FId = FId;
    }

 	/**
	 * *
	 * @hibernate.class lazy="false"
	 * @hibernate.many-to-one class="com.joymain.jecs.po.model.JpoMemberOrder" column="MO_ID"
	 */
     public JpoMemberOrder getJpoMemberOrder() {
		return jpoMemberOrder;
	}

	public void setJpoMemberOrder(JpoMemberOrder jpoMemberOrder) {
		this.jpoMemberOrder = jpoMemberOrder;
	}

	/**
	 * *
	 * @hibernate.class lazy="false"
	 * @hibernate.many-to-one class="com.joymain.jecs.sys.model.SysUser" column="OPERATOR_SYSUSER"
	 */
	public SysUser getOperatorSysuser() {
		return operatorSysuser;
	}

	public void setOperatorSysuser(SysUser operatorSysuser) {
		this.operatorSysuser = operatorSysuser;
	}
	
    /**
	 * * @hibernate.property column="DATA_TYPE"
	 */
    public String getDataType() {
        return this.dataType;
    }
    
	public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpoCheckedFailed pojo = (JpoCheckedFailed) o;

        if (FId != null ? !FId.equals(pojo.FId) : pojo.FId != null) return false;
        if (operatorSysuser != null ? !operatorSysuser.equals(pojo.operatorSysuser) : pojo.operatorSysuser != null) return false;
        if (dataType != null ? !dataType.equals(pojo.dataType) : pojo.dataType != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = 31 * result + (FId != null ? FId.hashCode() : 0);
        result = 31 * result + (operatorSysuser != null ? operatorSysuser.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("FId").append("='").append(getFId()).append("', ");
        sb.append("operatorSysuser").append("='").append(getOperatorSysuser()).append("', ");
        sb.append("dataType").append("='").append(getDataType()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}