package com.joymain.jecs.pm.model;
// Generated 2014-12-9 11:17:47 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JOCS_INTERFACE_RETRANSMISSION"
 *     
 */

public class JocsInterfaceRetransmission extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal logId;//主键，使用序列SEQ_LOG
    private String logType;//日志收发类型，例如以JOCS为基准：0 ：发送   1：接收  对应列表：jocsinterfacelog.logtype
    private String flag;//访问来源标识(JOCS/WMS) 对应列表：jocsinterfacelog.flag
    private String method;//接口方法,创建订单：order.add
    private String type;//返回格式，默认json
    private String charset;//返回编码，默认utf-8，还支持gbk
    private String ver;//版本号，默认1
    private String content;//接口内容，json字符串
    private String sign;//签名，详见签名算法
    private String returnResult;//返回结果标识
    private String returnDesc;//返回结果详细
    private Date sendTime;//发送时间(发送方)
    private Date reciverTime;//接收时间(接收方插入数据库时间)insert时传递空值即可，默认插入sysdate
    private Date editTime;//修改时间
    private String remark;//备注字段
    private String retranType;//重发类型     0：自动重发   1：手动重发
    private String retranStatus;//重发状态     重发状态     0：未发送    1：已发送
    private Date retranTime;//重发时间
    private String retranCode;//重发失败编码类型
    private String retranReason;//重发失败原因
    private String remark1;//备用字段1
    private String remark2;//备用字段2

    private String sender;//发送到的目标
    
    // Constructors

    /** default constructor */
    public JocsInterfaceRetransmission() {
    }

	/** minimal constructor */
    public JocsInterfaceRetransmission(String logType, String flag, String method, String type, String charset, String ver, String content, Date sendTime, Date reciverTime) {
        this.logType = logType;
        this.flag = flag;
        this.method = method;
        this.type = type;
        this.charset = charset;
        this.ver = ver;
        this.content = content;
        this.sendTime = sendTime;
        this.reciverTime = reciverTime;
    }
    
    /** full constructor */
    public JocsInterfaceRetransmission(String logType, String flag, String method, String type, String charset, String ver, String content, String sign, String returnResult, String returnDesc, Date sendTime, Date reciverTime, Date editTime, String remark, String retranType, String retranStatus, Date retranTime, String retranCode, String retranReason, String remark1, String remark2) {
        this.logType = logType;
        this.flag = flag;
        this.method = method;
        this.type = type;
        this.charset = charset;
        this.ver = ver;
        this.content = content;
        this.sign = sign;
        this.returnResult = returnResult;
        this.returnDesc = returnDesc;
        this.sendTime = sendTime;
        this.reciverTime = reciverTime;
        this.editTime = editTime;
        this.remark = remark;
        this.retranType = retranType;
        this.retranStatus = retranStatus;
        this.retranTime = retranTime;
        this.retranCode = retranCode;
        this.retranReason = retranReason;
        this.remark1 = remark1;
        this.remark2 = remark2;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.math.BigDecimal"
     *             column="LOG_ID"
     *         
     */

    public BigDecimal getLogId() {
        return this.logId;
    }
    
    public void setLogId(BigDecimal logId) {
        this.logId = logId;
    }
    /**       
     *      *            @hibernate.property
     *             column="LOG_TYPE"
     *             length="2"
     *             not-null="true"
     *         
     */

    public String getLogType() {
        return this.logType;
    }
    
    public void setLogType(String logType) {
        this.logType = logType;
    }
    /**       
     *      *            @hibernate.property
     *             column="FLAG"
     *             length="10"
     *             not-null="true"
     *         
     */

    public String getFlag() {
        return this.flag;
    }
    
    public void setFlag(String flag) {
        this.flag = flag;
    }
    /**       
     *      *            @hibernate.property
     *             column="METHOD"
     *             length="100"
     *             not-null="true"
     *         
     */

    public String getMethod() {
        return this.method;
    }
    
    public void setMethod(String method) {
        this.method = method;
    }
    /**       
     *      *            @hibernate.property
     *             column="TYPE"
     *             length="10"
     *             not-null="true"
     *         
     */

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    /**       
     *      *            @hibernate.property
     *             column="CHARSET"
     *             length="10"
     *             not-null="true"
     *         
     */

    public String getCharset() {
        return this.charset;
    }
    
    public void setCharset(String charset) {
        this.charset = charset;
    }
    /**       
     *      *            @hibernate.property
     *             column="VER"
     *             length="10"
     *             not-null="true"
     *         
     */

    public String getVer() {
        return this.ver;
    }
    
    public void setVer(String ver) {
        this.ver = ver;
    }
    /**       
     *      *            @hibernate.property
     *             column="CONTENT"
     *             length="4000"
     *             not-null="true"
     *         
     */

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    /**       
     *      *            @hibernate.property
     *             column="SIGN"
     *             length="4000"
     *         
     */

    public String getSign() {
        return this.sign;
    }
    
    public void setSign(String sign) {
        this.sign = sign;
    }
    /**       
     *      *            @hibernate.property
     *             column="RETURN_RESULT"
     *             length="20"
     *         
     */

    public String getReturnResult() {
        return this.returnResult;
    }
    
    public void setReturnResult(String returnResult) {
        this.returnResult = returnResult;
    }
    /**       
     *      *            @hibernate.property
     *             column="RETURN_DESC"
     *             length="500"
     *         
     */

    public String getReturnDesc() {
        return this.returnDesc;
    }
    
    public void setReturnDesc(String returnDesc) {
        this.returnDesc = returnDesc;
    }
    /**       
     *      *            @hibernate.property
     *             column="SEND_TIME"
     *             length="7"
     *             not-null="true"
     *         
     */

    public Date getSendTime() {
        return this.sendTime;
    }
    
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="RECIVER_TIME"
     *             length="7"
     *             not-null="true"
     *         
     */

    public Date getReciverTime() {
        return this.reciverTime;
    }
    
    public void setReciverTime(Date reciverTime) {
        this.reciverTime = reciverTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="EDIT_TIME"
     *             length="7"
     *         
     */

    public Date getEditTime() {
        return this.editTime;
    }
    
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMARK"
     *             length="1000"
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
     *             column="RETRAN_TYPE"
     *             length="2"
     *         
     */

    public String getRetranType() {
        return this.retranType;
    }
    
    public void setRetranType(String retranType) {
        this.retranType = retranType;
    }
    /**       
     *      *            @hibernate.property
     *             column="RETRAN_STATUS"
     *             length="2"
     *         
     */

    public String getRetranStatus() {
        return this.retranStatus;
    }
    
    public void setRetranStatus(String retranStatus) {
        this.retranStatus = retranStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="RETRAN_TIME"
     *             length="7"
     *         
     */

    public Date getRetranTime() {
        return this.retranTime;
    }
    
    public void setRetranTime(Date retranTime) {
        this.retranTime = retranTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="RETRAN_CODE"
     *             length="50"
     *         
     */

    public String getRetranCode() {
        return this.retranCode;
    }
    
    public void setRetranCode(String retranCode) {
        this.retranCode = retranCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="RETRAN_REASON"
     *             length="500"
     *         
     */

    public String getRetranReason() {
        return this.retranReason;
    }
    
    public void setRetranReason(String retranReason) {
        this.retranReason = retranReason;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMARK1"
     *             length="500"
     *         
     */

    public String getRemark1() {
        return this.remark1;
    }
    
    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMARK2"
     *             length="500"
     *         
     */

    public String getRemark2() {
        return this.remark2;
    }
    
    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("logType").append("='").append(getLogType()).append("' ");			
      buffer.append("flag").append("='").append(getFlag()).append("' ");			
      buffer.append("method").append("='").append(getMethod()).append("' ");			
      buffer.append("type").append("='").append(getType()).append("' ");			
      buffer.append("charset").append("='").append(getCharset()).append("' ");			
      buffer.append("ver").append("='").append(getVer()).append("' ");			
      buffer.append("content").append("='").append(getContent()).append("' ");			
      buffer.append("sign").append("='").append(getSign()).append("' ");			
      buffer.append("returnResult").append("='").append(getReturnResult()).append("' ");			
      buffer.append("returnDesc").append("='").append(getReturnDesc()).append("' ");			
      buffer.append("sendTime").append("='").append(getSendTime()).append("' ");			
      buffer.append("reciverTime").append("='").append(getReciverTime()).append("' ");			
      buffer.append("editTime").append("='").append(getEditTime()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("retranType").append("='").append(getRetranType()).append("' ");			
      buffer.append("retranStatus").append("='").append(getRetranStatus()).append("' ");			
      buffer.append("retranTime").append("='").append(getRetranTime()).append("' ");			
      buffer.append("retranCode").append("='").append(getRetranCode()).append("' ");			
      buffer.append("retranReason").append("='").append(getRetranReason()).append("' ");			
      buffer.append("remark1").append("='").append(getRemark1()).append("' ");			
      buffer.append("remark2").append("='").append(getRemark2()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JocsInterfaceRetransmission) ) return false;
		 JocsInterfaceRetransmission castOther = ( JocsInterfaceRetransmission ) other; 
         
		 return ( (this.getLogId()==castOther.getLogId()) || ( this.getLogId()!=null && castOther.getLogId()!=null && this.getLogId().equals(castOther.getLogId()) ) )
 && ( (this.getLogType()==castOther.getLogType()) || ( this.getLogType()!=null && castOther.getLogType()!=null && this.getLogType().equals(castOther.getLogType()) ) )
 && ( (this.getFlag()==castOther.getFlag()) || ( this.getFlag()!=null && castOther.getFlag()!=null && this.getFlag().equals(castOther.getFlag()) ) )
 && ( (this.getMethod()==castOther.getMethod()) || ( this.getMethod()!=null && castOther.getMethod()!=null && this.getMethod().equals(castOther.getMethod()) ) )
 && ( (this.getType()==castOther.getType()) || ( this.getType()!=null && castOther.getType()!=null && this.getType().equals(castOther.getType()) ) )
 && ( (this.getCharset()==castOther.getCharset()) || ( this.getCharset()!=null && castOther.getCharset()!=null && this.getCharset().equals(castOther.getCharset()) ) )
 && ( (this.getVer()==castOther.getVer()) || ( this.getVer()!=null && castOther.getVer()!=null && this.getVer().equals(castOther.getVer()) ) )
 && ( (this.getContent()==castOther.getContent()) || ( this.getContent()!=null && castOther.getContent()!=null && this.getContent().equals(castOther.getContent()) ) )
 && ( (this.getSign()==castOther.getSign()) || ( this.getSign()!=null && castOther.getSign()!=null && this.getSign().equals(castOther.getSign()) ) )
 && ( (this.getReturnResult()==castOther.getReturnResult()) || ( this.getReturnResult()!=null && castOther.getReturnResult()!=null && this.getReturnResult().equals(castOther.getReturnResult()) ) )
 && ( (this.getReturnDesc()==castOther.getReturnDesc()) || ( this.getReturnDesc()!=null && castOther.getReturnDesc()!=null && this.getReturnDesc().equals(castOther.getReturnDesc()) ) )
 && ( (this.getSendTime()==castOther.getSendTime()) || ( this.getSendTime()!=null && castOther.getSendTime()!=null && this.getSendTime().equals(castOther.getSendTime()) ) )
 && ( (this.getReciverTime()==castOther.getReciverTime()) || ( this.getReciverTime()!=null && castOther.getReciverTime()!=null && this.getReciverTime().equals(castOther.getReciverTime()) ) )
 && ( (this.getEditTime()==castOther.getEditTime()) || ( this.getEditTime()!=null && castOther.getEditTime()!=null && this.getEditTime().equals(castOther.getEditTime()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getRetranType()==castOther.getRetranType()) || ( this.getRetranType()!=null && castOther.getRetranType()!=null && this.getRetranType().equals(castOther.getRetranType()) ) )
 && ( (this.getRetranStatus()==castOther.getRetranStatus()) || ( this.getRetranStatus()!=null && castOther.getRetranStatus()!=null && this.getRetranStatus().equals(castOther.getRetranStatus()) ) )
 && ( (this.getRetranTime()==castOther.getRetranTime()) || ( this.getRetranTime()!=null && castOther.getRetranTime()!=null && this.getRetranTime().equals(castOther.getRetranTime()) ) )
 && ( (this.getRetranCode()==castOther.getRetranCode()) || ( this.getRetranCode()!=null && castOther.getRetranCode()!=null && this.getRetranCode().equals(castOther.getRetranCode()) ) )
 && ( (this.getRetranReason()==castOther.getRetranReason()) || ( this.getRetranReason()!=null && castOther.getRetranReason()!=null && this.getRetranReason().equals(castOther.getRetranReason()) ) )
 && ( (this.getRemark1()==castOther.getRemark1()) || ( this.getRemark1()!=null && castOther.getRemark1()!=null && this.getRemark1().equals(castOther.getRemark1()) ) )
 && ( (this.getRemark2()==castOther.getRemark2()) || ( this.getRemark2()!=null && castOther.getRemark2()!=null && this.getRemark2().equals(castOther.getRemark2()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLogId() == null ? 0 : this.getLogId().hashCode() );
         result = 37 * result + ( getLogType() == null ? 0 : this.getLogType().hashCode() );
         result = 37 * result + ( getFlag() == null ? 0 : this.getFlag().hashCode() );
         result = 37 * result + ( getMethod() == null ? 0 : this.getMethod().hashCode() );
         result = 37 * result + ( getType() == null ? 0 : this.getType().hashCode() );
         result = 37 * result + ( getCharset() == null ? 0 : this.getCharset().hashCode() );
         result = 37 * result + ( getVer() == null ? 0 : this.getVer().hashCode() );
         result = 37 * result + ( getContent() == null ? 0 : this.getContent().hashCode() );
         result = 37 * result + ( getSign() == null ? 0 : this.getSign().hashCode() );
         result = 37 * result + ( getReturnResult() == null ? 0 : this.getReturnResult().hashCode() );
         result = 37 * result + ( getReturnDesc() == null ? 0 : this.getReturnDesc().hashCode() );
         result = 37 * result + ( getSendTime() == null ? 0 : this.getSendTime().hashCode() );
         result = 37 * result + ( getReciverTime() == null ? 0 : this.getReciverTime().hashCode() );
         result = 37 * result + ( getEditTime() == null ? 0 : this.getEditTime().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getRetranType() == null ? 0 : this.getRetranType().hashCode() );
         result = 37 * result + ( getRetranStatus() == null ? 0 : this.getRetranStatus().hashCode() );
         result = 37 * result + ( getRetranTime() == null ? 0 : this.getRetranTime().hashCode() );
         result = 37 * result + ( getRetranCode() == null ? 0 : this.getRetranCode().hashCode() );
         result = 37 * result + ( getRetranReason() == null ? 0 : this.getRetranReason().hashCode() );
         result = 37 * result + ( getRemark1() == null ? 0 : this.getRemark1().hashCode() );
         result = 37 * result + ( getRemark2() == null ? 0 : this.getRemark2().hashCode() );
         return result;
   }

   /**       
    * @hibernate.property
    *  column="SENDER"
    *  length="20"
    */
	public String getSender() {
		return sender;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}   
}
