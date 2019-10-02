package com.joymain.jecs.util.bill99ms;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.fi.model.Jfi99billLog;
import com.joymain.jecs.fi.model.Jfi99billmsLog;
import com.joymain.jecs.fi.service.Jfi99billLogManager;
import com.joymain.jecs.fi.service.Jfi99billmsLogManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.util.StringUtil;

public class Bill99msUtilImpl implements Bill99msUtil {
	private Jfi99billmsLogManager jfi99billmsLogManager = null;
    private SysIdManager sysIdManager = null;
    private JmiMemberManager jmiMemberManager = null;
    private JpoMemberOrderManager jpoMemberOrderManager = null;
	
	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}

	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	
	private String getMemberCodeByMoIdOrUserCode(String str,int flag){
		String province = "";
		if(flag==0){
			JmiMember jmiMember = jmiMemberManager.getJmiMember(str);
			if(jmiMember.getProvince()==null){
				province = "null";
			}else{
				province = jmiMember.getProvince();
			}
		}else{
			JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(str);
			province = jpoMemberOrder.getProvince();
		}
		String memberCode = province;
		if(Bill99msConstants.account.get(province)==null){
			memberCode = "null";
		}
		return memberCode;
	}

	/**
	 * 生成初始Jfi99billLog
	 */
	private Jfi99billmsLog setJfi99billmsLog(HttpServletRequest request,String userCode,String companyCode){
		Jfi99billmsLog jfi99billmsLog = new Jfi99billmsLog();
        jfi99billmsLog.setInc("0");
        jfi99billmsLog.setCompanyCode(companyCode);
        jfi99billmsLog.setUrl(request.getRequestURL().toString() + "?" + request.getQueryString());
        jfi99billmsLog.setUserCode(userCode);
        jfi99billmsLog.setReferer(request.getHeader("referer"));
        jfi99billmsLog.setPid(request.getParameter("pid"));
        
        jfi99billmsLog.setBankDealId(request.getParameter("bankDealId"));
        jfi99billmsLog.setBankId(request.getParameter("bankId"));
        jfi99billmsLog.setBillLanguage(request.getParameter("language"));
        jfi99billmsLog.setDealId(request.getParameter("dealId"));
        jfi99billmsLog.setDealTime(request.getParameter("dealTime"));
        jfi99billmsLog.setErrCode(request.getParameter("errCode"));
        jfi99billmsLog.setExt1(request.getParameter("ext1"));
        jfi99billmsLog.setExt2(request.getParameter("ext2"));
        jfi99billmsLog.setFee(request.getParameter("fee"));
        jfi99billmsLog.setPayeeContactType(request.getParameter("payeeContactType"));
        jfi99billmsLog.setPayeeContact(request.getParameter("payeeContact"));
        jfi99billmsLog.setPayeeAmount(request.getParameter("payeeAmount"));
        jfi99billmsLog.setOrderAmount(request.getParameter("orderAmount"));
        jfi99billmsLog.setOrderId(request.getParameter("orderId"));
        jfi99billmsLog.setOrderTime(request.getParameter("orderTime"));
        jfi99billmsLog.setPayAmount(request.getParameter("payAmount"));
        jfi99billmsLog.setPayResult(request.getParameter("payResult"));
        jfi99billmsLog.setPayType(request.getParameter("payType"));
        jfi99billmsLog.setSignMsg(request.getParameter("signMsg"));
        jfi99billmsLog.setSignType(request.getParameter("signType"));
        jfi99billmsLog.setVerifySignResult(request.getParameter("verifySignResult"));
        jfi99billmsLog.setVersion(request.getParameter("version"));
        jfi99billmsLog.setSharingResult(request.getParameter("sharingResult"));
        jfi99billmsLog.setIp(request.getRemoteAddr());
        return jfi99billmsLog;
	}
	
	/**
	 * 0表示数据被篡改
	 * 1表示扣款失败
	 * 2自定义MD5签名被篡改(快钱签名被破解)
	 * 3支付数据重新发送
	 * 10表示成功校验
	 * @param request
	 * @param userCode
	 * @param companyCode
	 * @return
	 */
	public Jfi99billmsLog getJfi99billmsLog(HttpServletRequest request,String userCode,String companyCode){
		Jfi99billmsLog jfi99billmsLog = this.setJfi99billmsLog(request, userCode, companyCode);
		jfi99billmsLog = this.checkJfi99billmsLog(jfi99billmsLog);
		return jfi99billmsLog;
	}

	/**
	 * jfi99billLog.getReturnMsg()
	 * 0表示数据被篡改
	 * 1表示扣款失败
	 * 2自定义MD5签名被篡改(快钱签名被破解)
	 * 3支付数据重新发送
	 * 10表示成功校验
	 */
	private Jfi99billmsLog checkJfi99billmsLog(Jfi99billmsLog jfi99billmsLog){
		//生成加密串。必须保持如下顺序。
		String merchantSignMsgVal="";
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"version",jfi99billmsLog.getVersion());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"language",jfi99billmsLog.getBillLanguage());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"signType",jfi99billmsLog.getSignType());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"payType",jfi99billmsLog.getPayType());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"bankId",jfi99billmsLog.getBankId());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"pid",jfi99billmsLog.getPid());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"orderId",jfi99billmsLog.getOrderId());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"orderTime",jfi99billmsLog.getOrderTime());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"orderAmount",jfi99billmsLog.getOrderAmount());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"dealId",jfi99billmsLog.getDealId());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"bankDealId",jfi99billmsLog.getBankDealId());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"dealTime",jfi99billmsLog.getDealTime());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"payAmount",jfi99billmsLog.getPayAmount());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"fee",jfi99billmsLog.getFee());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"payeeContactType",jfi99billmsLog.getPayeeContactType());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"payeeContact",jfi99billmsLog.getPayeeContact());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"payeeAmount",jfi99billmsLog.getPayeeAmount());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"ext1",jfi99billmsLog.getExt1());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"ext2",jfi99billmsLog.getExt2());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"payResult",jfi99billmsLog.getPayResult());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"sharingResult",jfi99billmsLog.getSharingResult());
		merchantSignMsgVal=appendParam(merchantSignMsgVal,"errCode",jfi99billmsLog.getErrCode());

		int rtnMsg = 0;
		jfi99billmsLog.setVerifySignResult("false");
		
		if(Bill99msConstants.signType4.equals(jfi99billmsLog.getSignType())){
			//证书校验签名
			PKIUtil pkiUtil=new PKIUtil();
			boolean verifyData = pkiUtil.verifyData(merchantSignMsgVal+"&",jfi99billmsLog.getSignMsg());
			jfi99billmsLog.setVerifySignResult(String.valueOf(verifyData));
		}
		
		if("true".equals(jfi99billmsLog.getVerifySignResult().toLowerCase())){
			///接着进行支付结果判断
			if("10".equals(jfi99billmsLog.getPayResult())){
				// 商户网站逻辑处理，比方更新订单支付状态为成功
				// 特别注意：只有signMsg.toUpperCase().equals(merchantSignMsg.toUpperCase())，且payResult=10，才表示支付成功！同时将订单金额与提交订单前的订单金额进行对比校验。
				//*
				//报告给快钱处理结果，并提供将要重定向的地址。
				Jfi99billmsLog jfi99billmsLogTmp = new Jfi99billmsLog();
				jfi99billmsLogTmp.setDealId(jfi99billmsLog.getDealId());
				jfi99billmsLogTmp.setInc("1");
				if(jfi99billmsLogManager.getJfi99billmsLogs(jfi99billmsLogTmp).size()>0){
					rtnMsg = 3;//重发信息
				}else{
					rtnMsg = 10;//成功校验
				}
			}else{
				rtnMsg = 2;//扣款失败
			}
		}else{
			// 商户网站逻辑处理，
			rtnMsg = 0;//被篡改
		}
		jfi99billmsLog.setReturnMsg(String.valueOf(rtnMsg));
		jfi99billmsLog.setCreateTime(new Date());
		jfi99billmsLogManager.saveJfi99billmsLog(jfi99billmsLog);
		return jfi99billmsLog;//被篡改
	}
	
	/**
	 * 生成签名
	 * @param bill99ms
	 * @return
	 */
	private String getSignMsg(Bill99ms bill99ms){
		//生成加密签名串
		///请务必按照如下顺序和规则组成加密串！
		String signMsgVal="";
		signMsgVal=appendParam(signMsgVal,"inputCharset",Bill99msConstants.inputCharset);
		signMsgVal=appendParam(signMsgVal,"pageUrl",Bill99msConstants.pageUrl);
		signMsgVal=appendParam(signMsgVal,"bgUrl",Bill99msConstants.bgUrl);
		signMsgVal=appendParam(signMsgVal,"version",Bill99msConstants.version);
		signMsgVal=appendParam(signMsgVal,"language",Bill99msConstants.language);
		
		signMsgVal=appendParam(signMsgVal,"signType",bill99ms.getSignType());
		
		signMsgVal=appendParam(signMsgVal,"payeeContactType",bill99ms.getPayeeContactType());
		signMsgVal=appendParam(signMsgVal,"payeeContact",bill99ms.getPayeeContact());
		signMsgVal=appendParam(signMsgVal,"payerName",bill99ms.getPayerName());
		signMsgVal=appendParam(signMsgVal,"payerContactType","");
		signMsgVal=appendParam(signMsgVal,"payerContact","");
 		signMsgVal=appendParam(signMsgVal,"orderId",bill99ms.getOrderId());
		signMsgVal=appendParam(signMsgVal,"orderAmount",bill99ms.getOrderAmount());
		signMsgVal=appendParam(signMsgVal,"payeeAmount",bill99ms.getPayeeAmount());
		signMsgVal=appendParam(signMsgVal,"orderTime",bill99ms.getOrderTime());
		signMsgVal=appendParam(signMsgVal,"productName",bill99ms.getProductName());
		signMsgVal=appendParam(signMsgVal,"productNum",bill99ms.getProductNum());
		signMsgVal=appendParam(signMsgVal,"productDesc",bill99ms.getProductDesc());
		signMsgVal=appendParam(signMsgVal,"ext1",bill99ms.getExt1());
		signMsgVal=appendParam(signMsgVal,"ext2",bill99ms.getExt2());
		if(StringUtils.isEmpty(bill99ms.getBankId())){
			signMsgVal=appendParam(signMsgVal,"payType",Bill99msConstants.payType13);
		}else{
			signMsgVal=appendParam(signMsgVal,"payType",Bill99msConstants.payType10);
		}
		signMsgVal=appendParam(signMsgVal,"bankId",bill99ms.getBankId());
		signMsgVal=appendParam(signMsgVal,"pid",bill99ms.getPid());
		signMsgVal=appendParam(signMsgVal,"sharingData",bill99ms.getSharingData());
		signMsgVal=appendParam(signMsgVal,"sharingPayFlag",bill99ms.getSharingPayFlag());
		String signMsg = "";
		
		String[] ext2 = bill99ms.getExt2().split(",");
        String userCode = ext2[0];
		
		if(Bill99msConstants.signType4.equals(bill99ms.getSignType())){
			//证书签名
			PKIUtil pkiUtil=new PKIUtil();
			//signMsg=pkiUtil.generateSign(signMsgVal,this.getMemberCodeByUserCode(userCode));
			
	        int flag = Integer.parseInt(ext2[1]);
			if(flag==0){
				signMsg=pkiUtil.generateSign(signMsgVal,this.getMemberCodeByMoIdOrUserCode(userCode,flag));//账户编号
			}else{
				signMsg=pkiUtil.generateSign(signMsgVal,this.getMemberCodeByMoIdOrUserCode(bill99ms.getOrderId(),flag));//账户编号
			}
		}
		return signMsg;
	}

	/**
	 * 生成Bill99对像
	 * flag(0:为电子存折  1:订单)
	 */
	public Bill99ms getBill99ms(Bill99ms bill99ms,int flag ){
		bill99ms.setInputCharset(Bill99msConstants.inputCharset);
		bill99ms.setPageUrl(Bill99msConstants.pageUrl);
		bill99ms.setBgUrl(Bill99msConstants.bgUrl);
		bill99ms.setVersion(Bill99msConstants.version);
		bill99ms.setLanguage(Bill99msConstants.language);
		
		String[] ext2 = bill99ms.getExt2().split(",");
		String userCode = ext2[0];
		bill99ms.setPayeeContactType(Bill99msConstants.payeeContactType);
		if(flag==0){
			bill99ms.setPayeeContact(Bill99msConstants.account.get(this.getMemberCodeByMoIdOrUserCode(userCode,flag)).get("memberCode"));//账户编号
			bill99ms.setSharingData(Bill99msConstants.account.get(this.getMemberCodeByMoIdOrUserCode(userCode,flag)).get("sharingData"));
			bill99ms.setPid(Bill99msConstants.account.get(this.getMemberCodeByMoIdOrUserCode(userCode,flag)).get("pid"));
		}else{
			bill99ms.setPayeeContact(Bill99msConstants.account.get(this.getMemberCodeByMoIdOrUserCode(bill99ms.getOrderId(),flag)).get("memberCode"));//账户编号
			bill99ms.setSharingData(Bill99msConstants.account.get(this.getMemberCodeByMoIdOrUserCode(bill99ms.getOrderId(),flag)).get("sharingData"));
			bill99ms.setPid(Bill99msConstants.account.get(this.getMemberCodeByMoIdOrUserCode(bill99ms.getOrderId(),flag)).get("pid"));
		}
		
		bill99ms.setPayerContactType("");
		if(StringUtils.isEmpty(bill99ms.getBankId())){
			bill99ms.setPayType(Bill99msConstants.payType13);
		}else{
			bill99ms.setPayType(Bill99msConstants.payType10);
		}
		if(flag==0){
			bill99ms.setOrderId(sysIdManager.buildIdStr("advicecode_cn"));
		}
		BigDecimal orderFee = new BigDecimal(bill99ms.getOrderAmount()).multiply(Bill99msConstants.feeP).setScale(2, BigDecimal.ROUND_UP);
		BigDecimal OrderAmount = (new BigDecimal(bill99ms.getOrderAmount()).add(orderFee)).multiply(new BigDecimal(100));
		DecimalFormat decimalFormat = new DecimalFormat("#0");
		bill99ms.setOrderAmount(decimalFormat.format(OrderAmount));
		BigDecimal payeeAmount = new BigDecimal(decimalFormat.format(OrderAmount.multiply(new BigDecimal("0.65"))));
		bill99ms.setPayeeAmount(payeeAmount.toString());
		bill99ms.setSharingData(bill99ms.getSharingData().replaceAll("\\^@\\^", "^"+decimalFormat.format(OrderAmount.subtract(payeeAmount)) + "^"));
		bill99ms.setSharingPayFlag(Bill99msConstants.sharingPayFlag);
		bill99ms.setOrderTime(new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));
		bill99ms.setProductName("");
		bill99ms.setProductDesc("");
		bill99ms.setProductNum("");
		bill99ms.setExt2(bill99ms.getExt2() + "," + String.valueOf(flag) + "," + orderFee);
		
		//证书
		bill99ms.setSignType(Bill99msConstants.signType4);
		
		bill99ms.setExt1("");
		bill99ms.setPostUrl(Bill99msConstants.postUrl);
		bill99ms.setSignMsg(getSignMsg(bill99ms));
		return bill99ms;
	}
	
	//功能函数。将变量值不为空的参数组成字符串
	private String appendParam(String returnStr,String paramId,String paramValue)
	{
		if(!returnStr.equals(""))
		{
			if(!paramValue.equals(""))
			{
				returnStr=returnStr+"&"+paramId+"="+paramValue;
			}
		}
		else
		{
			if(!paramValue.equals(""))
			{
			returnStr=paramId+"="+paramValue;
			}
		}	
		return returnStr;
	}

	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public void setJfi99billmsLogManager(Jfi99billmsLogManager jfi99billmsLogManager) {
		this.jfi99billmsLogManager = jfi99billmsLogManager;
	}
}
