package com.joymain.jecs.sys.model;
// Generated 2008-3-25 15:29:16 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JOCS_INTERFACE_LOG"
 *     
 */

public class JocsInterfaceLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long logId;//主键，使用序列SEQ_LOG
	private String logType;//日志收发类型，例如以JOCS为基准：0 ：发送   1：接收 对应列表：jocsinterfacelog.flag
	private String flag;//访问来源标识(JOCS/WMS) 对应列表：jocsinterfacelog.logtype
	private String method;//接口方法,创建订单：order.add
	private String type;//返回格式，默认json
	private String charset;//返回编码，默认utf-8，还支持gbk
	private String ver;//版本号，默认1
	private String content;//接口内容，json字符串
	private String sign;//签名，详见签名算法
	private String returnResult;//返回结果标识
	private String returnDesc;//返回结果详细
	private Date sendTime;//发送时间(发送方)，传递字符串
	private Date reciverTime;//接收时间(接收方插入数据库时间) insert时传递空值即可，默认插入sysdate
	private Date editTime;//修改时间
	private String remark;//备注字段

	private String sendTimeStr;//发送时间字符串
	private String editTimeStr;//修改时间字符串
	
	private String sender;//发送到的目标
	
	/**
	 * 构造方法：
	 * @param logType：日志收发类型，例如以JOCS为基准：0 ：发送   1：接收 对应列表：jocsinterfacelog.flag
	 * @param flag：访问来源标识(JOCS/WMS) 例如：0:JOCS  1：OMS  2：WMS  对应列表：jocsinterfacelog.logtype
	 * @param method：接口方法,创建订单：order.add
	 * @param type：返回格式，默认json
	 * @param charset：返回编码，默认utf-8，还支持gbk
	 * @param ver：版本号，默认1
	 * @param content：接口内容，json字符串
	 * @param sign：签名，详见签名算法
	 * @param returnResult：返回结果标识
	 * @param returnDesc：返回结果详细
	 * @param sendTimeStr：发送时间字符串
	 * @param editTimeStr：修改时间字符串
	 * @param remark：备注字段
	 */
	public JocsInterfaceLog(String logType, String flag, String method,
			String type, String charset, String ver, String content,
			String sign, String returnResult, String returnDesc, String sendTimeStr,
			String editTimeStr, String remark) {
		super();
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
		this.sendTimeStr = sendTimeStr;
		this.editTimeStr = editTimeStr;
		this.remark = remark;
	}

	/** 
	//调用写入日志方法实例：
    //创建对象
	JocsInterfaceLog jocsInterfaceLog = new JocsInterfaceLog("0","1","ORDERUPDATE","json","UTF-8","1","KEY1:VALUE1...","SUANFA...","0","成功!","2014-11-11 11:11:11","","备注");
	//调用方法写入到文本中
	String refId =ReportLogUtil.addJocsInterfaceTxt(jocsInterfaceLog);
	**/

	/**
     * @hibernate.id
     *  generator-class="sequence"
     *  type="java.lang.Long"
     *  column="LOG_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_LOG"
     */
	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	/**       
     * @hibernate.property
     *  column="LOG_TYPE"
     *  length="2"
     */
	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	/**       
     * @hibernate.property
     *  column="FLAG"
     *  length="10"
     */
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**       
     * @hibernate.property
     *  column="METHOD"
     *  length="100"
     */
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	/**       
     * @hibernate.property
     *  column="TYPE"
     *  length="10"
     */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**       
     * @hibernate.property
     *  column="CHARSET"
     *  length="10"
     */
	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	/**       
     * @hibernate.property
     *  column="VER"
     *  length="10"
     */
	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	/**       
     * @hibernate.property
     *  column="CONTENT"
     *  length="4000"
     */
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**       
     * @hibernate.property
     *  column="SIGN"
     *  length="4000"
     */
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	/**       
     * @hibernate.property
     *  column="RETURN_RESULT"
     *  length="10"
     */
	public String getReturnResult() {
		return returnResult;
	}

	public void setReturnResult(String returnResult) {
		this.returnResult = returnResult;
	}

	/**       
     * @hibernate.property
     *  column="RETURN_DESC"
     *  length="100"
     */
	public String getReturnDesc() {
		return returnDesc;
	}

	public void setReturnDesc(String returnDesc) {
		this.returnDesc = returnDesc;
	}

	/**       
	 *      @hibernate.property
	 *       column="SEND_TIME"
	 *       length="7"
	 *         
	 */
	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	/**       
	 *      @hibernate.property
	 *       column="RECIVER_TIME"
	 *       length="7"
	 *         
	 */
	public Date getReciverTime() {
		return reciverTime;
	}

	public void setReciverTime(Date reciverTime) {
		this.reciverTime = reciverTime;
	}

	/**       
	 *      @hibernate.property
	 *       column="EDIT_TIME"
	 *       length="7"
	 *         
	 */
	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	/**       
     * @hibernate.property
     *  column="REMARK"
     *  length="1000"
     */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
	/** default constructor */
    public JocsInterfaceLog() {
    }

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSendTimeStr() {
		return sendTimeStr;
	}

	public void setSendTimeStr(String sendTimeStr) {
		this.sendTimeStr = sendTimeStr;
	}

	public String getEditTimeStr() {
		return editTimeStr;
	}

	public void setEditTimeStr(String editTimeStr) {
		this.editTimeStr = editTimeStr;
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
