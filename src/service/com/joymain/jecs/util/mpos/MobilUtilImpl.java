package com.joymain.jecs.util.mpos;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import com.joymain.jecs.fi.model.Jfi99billLog;
import com.joymain.jecs.fi.service.Jfi99billLogManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
/**
 * 手机快钱支付验签入账处理实现类
 * @author Administrator
 *
 */
public class MobilUtilImpl implements MobilUtil {
	
	private Jfi99billLogManager jfi99billLogManager = null;
	private JpoMemberOrderManager jpoMemberOrderManager = null;

	public void setJfi99billLogManager(Jfi99billLogManager jfi99billLogManager) {
		this.jfi99billLogManager = jfi99billLogManager;
	}

	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	/**
	 * 验签
	 * @param request
	 * @return
	 */
	public boolean checkJfi99billLog(HttpServletRequest request){
		
		boolean flag=false;
		
		String signature = request.getParameter("signature");
		StringBuffer sb = new StringBuffer();
		sb.append(request.getParameter("processFlag")).append(
				request.getParameter("txnType")).append(
				request.getParameter("orgTxnType"));
		sb.append(request.getParameter("amt")).append(
				request.getParameter("externalTraceNo")).append(
				request.getParameter("orgExternalTraceNo")).append(
				request.getParameter("terminalOperId"));
		sb.append(request.getParameter("authCode")).append(
				request.getParameter("RRN")).append(
				request.getParameter("txnTime")).append(
				request.getParameter("shortPAN")).append(
				request.getParameter("responseCode"));
		sb.append(request.getParameter("cardType")).append(
				request.getParameter("issuerId"));
		CerEncode ce = new CerEncode();
		
		// 验签
		flag = ce.enCodeByCer(sb.toString(), signature);
		
		return flag;
	}
	
	/**
	 * 生成初始Jfi99billLog
	 */
	public Jfi99billLog setJfi99billLog(HttpServletRequest request){
		
		Jfi99billLog jfi99billLog = new Jfi99billLog();
		
        jfi99billLog.setInc("0");//1进电子存折 0没进电子存折
        jfi99billLog.setCompanyCode("CN");
        jfi99billLog.setUrl(request.getRequestURL().toString() + "?" + request.getQueryString());//快钱返回的RUL
        
        //获取订单编号、会员编号、flag
        String externalTraceNo[] = request.getParameter("externalTraceNo").split(",");
        String orderId = externalTraceNo[0];//订单编号
        String userCode = externalTraceNo[1];//会员编号
        
        jfi99billLog.setUserCode(userCode);//会员编号
        jfi99billLog.setOrderId(orderId);//会员订单号
        
        //获取实际支付金额
        BigDecimal payAmount = new BigDecimal(request.getParameter("amt")).multiply(new BigDecimal(100));
        jfi99billLog.setPayAmount(payAmount.toString());
        
        String flag = externalTraceNo[2];//标识,1:订单支付,0：充值
        if("1".equals(flag)){//订单审核
        	jfi99billLog.setOrderAmount(this.getOrderAmountByOrderId(orderId));//根据订单ID获取订单总金额
        }
        if("0".equals(flag)){//充值
        	jfi99billLog.setOrderAmount(payAmount.toString());
        }
        
        jfi99billLog.setOrderTime(new Date().toString());
        
        jfi99billLog.setFee("0");//快钱交易手续费
        
        jfi99billLog.setDealId(request.getParameter("RRN"));//获取快钱交易号，获取该交易在快钱的交易号,暂时读取授权号
        
        //获取银行代码 参见银行代码列表，暂时读取发卡机构
        jfi99billLog.setBankId(request.getParameter("issuerId"));
        
        //jfi99billLog.setBankDealId(request.getParameter("RRN"));//获取银行交易参考号
        jfi99billLog.setDealTime(request.getParameter("txnTime"));//获取在快钱交易时间
        jfi99billLog.setMerchantAcctId(request.getParameter("merchantId"));//获取人民币网关账户号,快钱商户号
        
        jfi99billLog.setErrCode(request.getParameter("responseCode"));//设置错误码，获取交易返回码
        jfi99billLog.setExt2(request.getParameter("externalTraceNo"));//设置扩展字段2,存入订单、会员编号、标识
        jfi99billLog.setPayType("2");//设置支付方式,代表手机支付
        jfi99billLog.setBillLanguage("1");//设置语言种类,1代表中文
        jfi99billLog.setSignType("1");//签名类型.固 1代表MD5签名 当前版本固定为1
        jfi99billLog.setSignMsg(request.getParameter("signature"));//获取加密签名串
        
        //获取处理结果,10代表 成功;11代表 失败
        if(("0").equals(request.getParameter("processFlag")))//获取处理结果  0代表 成功; 1代表 失败
        	jfi99billLog.setPayResult("10");
        if(("1").equals(request.getParameter("processFlag")))
        	jfi99billLog.setPayResult("11");
        
        //jfi99billLog.setReferer("");//来源页
        jfi99billLog.setCreateTime(new Date());
        
        return jfi99billLog;
	}

	/**
	 * 获取快钱返回信息，验签，保存快钱支付记录
	 */
	@Override
	public Jfi99billLog getJfi99billLog(HttpServletRequest request) {
		
		int rtnMsg = 0;//验签状态，0表示被篡改,2表示扣款失败,2自定义MD5签名被篡改(快钱签名被篡改),3支付数据重新发送,10表示成功校验
		
		/*
		 *  第1步：获取快钱发送的通知参数,设置到Jfi99billLog对象
		 */
		Jfi99billLog jfi99billLog = this.setJfi99billLog(request);
		
		/*
		 *  第2步：验签
		 */
		boolean flag = this.checkJfi99billLog(request);
		
		//验签通过
		if (flag) {
			
			//扣款成功
			if("10".equals(jfi99billLog.getPayResult())){
				
				//签名字符串验证结果
				jfi99billLog.setVerifySignResult(String.valueOf(flag));	
				
				/**
				 * 第3步：查询历史数据，查询数据是否重发
				 */
				if(jfi99billLogManager.getSuccessJfi99billLogsByConditions(jfi99billLog.getDealId()).size()>0){
					
					rtnMsg = 3;//重发信息
				}else{
					
					rtnMsg = 10;//成功校验
				}
			}else{
				rtnMsg = 20;//手机扣款失败
			}
		} else {

			rtnMsg = 4;//验签不通过
		}

		//验签状态: 10:代表成功校验，3：重发信息,0:表示被篡改,2:扣款失败
		jfi99billLog.setReturnMsg(String.valueOf(rtnMsg));
		jfi99billLog.setDataType("2");//数据来源，2：手机移动终端
		
		/*
		 * 第4步：保存快钱返回数据
		 */
		this.jfi99billLogManager.saveJfi99billLog(jfi99billLog);
		
		return jfi99billLog;
	}
	
	//根据订单ID获取订单总金额
	public String getOrderAmountByOrderId(String moId){
		
		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(moId);//查询订单对象
		
		BigDecimal payAmount = jpoMemberOrder.getAmount().multiply(new BigDecimal(100));
		return payAmount.toString();
	}
}
