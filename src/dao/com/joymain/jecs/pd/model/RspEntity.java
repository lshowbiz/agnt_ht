package com.joymain.jecs.pd.model;

/**
 * JOCS与其他系统接口交换数据时返回值的类
 * @author gw 2014-12-05
 *
 */
public class RspEntity extends com.joymain.jecs.model.BaseObject implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//状态码：code为e000006，表明：应用参数错误
	private String code;
	
    private String msg;
    //错误描述，错误原因
    private String sub_msg;
    
    private String rspCode;
    
    private String rsp;
    
    private String content;
    
    //应用实例：失败返回：{"error_response":{"code":"e000006","msg":"application params error","sub_msg":"order_bn[订单号] not null"}}
	
	public String getMsg()
    {
        return msg;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getRsp() {
		return rsp;
	}

	public void setRsp(String rsp) {
		this.rsp = rsp;
	}

	public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public String getSub_msg()
    {
        return sub_msg;
    }

    public void setSub_msg(String subMsg)
    {
        sub_msg = subMsg;
    }

    public String getRspCode()
    {
        return rspCode;
    }

    public void setRspCode(String rspCode)
    {
        this.rspCode = rspCode;
    }

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public boolean equals(Object o) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public String toString() {
		  StringBuffer buffer = new StringBuffer();
	      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
	      buffer.append("code").append("='").append(getCode()).append("' ");			
	      buffer.append("]");
	      return buffer.toString();
	}

}
