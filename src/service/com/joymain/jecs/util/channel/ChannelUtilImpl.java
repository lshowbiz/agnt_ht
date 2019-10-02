package com.joymain.jecs.util.channel;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.fi.model.FiChannelLog;
import com.joymain.jecs.fi.service.FiChannelLogManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.bill99.MD5Util;
import com.joymain.jecs.util.bill99ms.Bill99msConstants;
import com.joymain.jecs.util.string.StringUtil;
/**
 * 盛付通实现类
 * @author Administrator
 *
 */
public class ChannelUtilImpl implements ChannelUtil {
	
    private SysIdManager sysIdManager = null;
    private SysUserManager sysUserManager = null;
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private FiChannelLogManager fiChannelLogManager;
    
	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}
	
	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}	

	
	public void setFiChannelLogManager(FiChannelLogManager fiChannelLogManager) {
		this.fiChannelLogManager = fiChannelLogManager;
	}

	/**
	 * 生成Bill99对像
	 * flag(0:为电子存折  1:订单,2:公益基金 )
	 */
	public ChannelBean getChannelBean(ChannelBean channel, int flag){
		
		channel.setPostUrl("https://mas.sdo.com/web-acquire-channel/cashier.htm");
		channel.setName("B2CPayment");
		channel.setVersion("V4.1.1.1.1");
		channel.setCharset("UTF-8");
		channel.setMsgSender("100894");//测试商户号
		
		if(flag==0){
			channel.setOrderNo(sysIdManager.buildIdStr("advicecode_cn"));//充值订单号
			channel.setProductName("充值单");
		}
		channel.setOrderTime(new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));
		channel.setPayType("PT001");
		channel.setPageUrl("http://test.joylifeglobal.net/tdec/index.html");
		channel.setNotifyUrl("http://test.joylifeglobal.net/tdec/fiPayChannelReceive.html");//服务端通知地址
		
		try {
			InetAddress addr = InetAddress.getLocalHost();
			String ip=addr.getHostAddress().toString();//获得本机IP
			
			channel.setBuyerIp(ip);//防钓鱼用,买家的ip地址
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		SysUser sysUser = sysUserManager.getSysUser(channel.getExt1());
		channel.setBuyerContact(sysUser.getUserName());//联系方式
		channel.setExt1(channel.getExt1() + "," + String.valueOf(flag));
		channel.setSignType("MD5");
		channel.setSignMsg(getSignMsg(channel));
		
		
		return channel;
	}
	
	public String getSignMsg(ChannelBean channel){
		
		String md5Key = "shengfutongSHENGFUTONGtest";//测试MD5密钥：shengfutongSHENGFUTONGtest
		
		String origin = channel.getName()+channel.getVersion()+channel.getCharset()+channel.getMsgSender();
			
		if(!StringUtil.isEmpty(channel.getSendTime())){
			origin += channel.getSendTime();
		}
		
		origin += channel.getOrderNo()+channel.getOrderAmount()+channel.getOrderTime()+channel.getPayType();
		
		if(!StringUtil.isEmpty(channel.getPayChannel())){
			origin += channel.getPayChannel();
		}
		
		origin += channel.getInstCode()+channel.getPageUrl();
		
		if(!StringUtil.isEmpty(channel.getBackUrl())){
			origin += channel.getBackUrl();
		}
		
		origin += channel.getNotifyUrl();
		
		if(!StringUtil.isEmpty(channel.getProductName())){
			origin += channel.getProductName();
		}
		
		if(!StringUtil.isEmpty(channel.getBuyerContact())){
			origin += channel.getBuyerContact();
		}
		
		origin += channel.getBuyerIp()+channel.getExt1()+channel.getSignType();
		
		
		return Md5.Md5(origin+md5Key).toUpperCase();
	}
	
	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	/**
	 * 支付验签校验
	 * @param request
	 * @return
	 */
	@Override
	public FiChannelLog getFiChannelLog(HttpServletRequest request,String userCode) {

		//校验
		int rtnMsg = 0;//校验结果
		
		String Name = request.getParameter("Name");
		String Version = request.getParameter("Version");
		String Charset = request.getParameter("Charset");
		String TraceNo = request.getParameter("TraceNo");
		String MsgSender = request.getParameter("MsgSender");
		String SendTime = request.getParameter("SendTime");
		String InstCode = request.getParameter("InstCode");
		String OrderNo = request.getParameter("OrderNo");
		String OrderAmount = request.getParameter("OrderAmount");
		String TransNo = request.getParameter("TransNo");
		String TransAmount = request.getParameter("TransAmount");
		String TransStatus = request.getParameter("TransStatus");
		String TransType = request.getParameter("TransType");
		String TransTime = request.getParameter("TransTime");
		String MerchantNo = request.getParameter("MerchantNo");
		String ErrorCode = request.getParameter("ErrorCode");
		String ErrorMsg = request.getParameter("ErrorMsg");
		String Ext1 = request.getParameter("Ext1");
		String SignType = request.getParameter("SignType");
		
		//验签信息
		String SignMsg = request.getParameter("SignMsg");//签名串
		
		String origin = Name + Version + Charset + TraceNo + MsgSender + SendTime + InstCode 
		+ OrderNo + OrderAmount + TransNo + TransAmount + TransStatus + TransType + TransTime + MerchantNo + ErrorCode + ErrorMsg + Ext1 + SignType;
		
		String md5Key = "shengfutongSHENGFUTONGtest";//测试MD5密钥：shengfutongSHENGFUTONGtest
		String resultMsg = Md5.Md5(origin+md5Key).toUpperCase();
		
		//校验
		if(SignMsg.equals(resultMsg)){
			
			//支付成功
			if(("01").equals(TransStatus)){
				//校验是否重发数据
				FiChannelLog fiChannelLog = new FiChannelLog();
				fiChannelLog.setDealId(TransNo);
				fiChannelLog.setInc("1");
				if(fiChannelLogManager.getFiChannelLogs(fiChannelLog).size()>0){
					
					rtnMsg = 3;//重发信息
				}else{
					rtnMsg = 10;//成功校验
				}
			}else{
				rtnMsg = 2;//支付失败
			}
		}else{
			rtnMsg = 0;//被篡改
		}
		
		//保存盛付通支付记录
		FiChannelLog fiChannelLog = new FiChannelLog();
		
		fiChannelLog.setInc("0");
		fiChannelLog.setCompanyCode("CN");
		fiChannelLog.setUserCode(userCode);
		fiChannelLog.setUrl(request.getRequestURL().toString() + "?" + request.getQueryString());
		
		fiChannelLog.setBankDealId(request.getParameter("BankSerialNo"));
		fiChannelLog.setBankId(InstCode);
		fiChannelLog.setDealId(TransNo);
		fiChannelLog.setDealTime(TransTime);
		
		fiChannelLog.setMerchantAcctId(MerchantNo);
		fiChannelLog.setPayAmount(TransAmount);
		fiChannelLog.setOrderAmount(OrderAmount);
		fiChannelLog.setOrderId(request.getParameter("OriginalOrderNo"));
		
		fiChannelLog.setPayResult(TransStatus);//支付状态:01付款成功; 02付款失败
		fiChannelLog.setErrCode(ErrorCode);
		fiChannelLog.setExt2(ErrorMsg);
		fiChannelLog.setExt1(Ext1);
		fiChannelLog.setSignType(SignType);
		fiChannelLog.setSignMsg(SignMsg);
		
		fiChannelLog.setReturnMsg(String.valueOf(rtnMsg));
		fiChannelLog.setCreateTime(new Date());
		fiChannelLogManager.saveFiChannelLog(fiChannelLog);
		
		return fiChannelLog;
	}

	

}
