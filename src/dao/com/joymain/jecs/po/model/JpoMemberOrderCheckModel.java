package com.joymain.jecs.po.model;

import java.io.Serializable;

import com.joymain.jecs.sys.model.SysUser;


/**
 * 内部消息对象。
 * @author 
 *
 */
public class JpoMemberOrderCheckModel implements Serializable
{
    /**
     * 订单
     */
    private JpoMemberOrder jpoMemberOrder;
    
    /**
     * 操作人
     */
    private SysUser jsysUser;
    
    //来源
    private String dataType;

	public JpoMemberOrder getJpoMemberOrder() {
		return jpoMemberOrder;
	}

	public void setJpoMemberOrder(JpoMemberOrder jpoMemberOrder) {
		this.jpoMemberOrder = jpoMemberOrder;
	}

	public SysUser getJsysUser() {
		return jsysUser;
	}

	public void setJsysUser(SysUser jsysUser) {
		this.jsysUser = jsysUser;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
    
    

    
}
