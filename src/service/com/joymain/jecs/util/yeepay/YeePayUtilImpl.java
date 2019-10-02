package com.joymain.jecs.util.yeepay;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.fi.model.JfiYeepayLog;
import com.joymain.jecs.fi.service.JfiYeepayLogManager;
import com.joymain.jecs.util.yspay.PayUtils;
import com.joymain.jecs.util.yspay.RemarkBean;

public class YeePayUtilImpl implements YeePayUtil {

	private final Log log = LogFactory.getLog(YeePayUtilImpl.class);
	//public static final String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
	
	private JfiYeepayLogManager jfiYeepayLogManager = null;
	
	public void setJfiYeepayLogManager(JfiYeepayLogManager jfiYeepayLogManager) {
        this.jfiYeepayLogManager = jfiYeepayLogManager;
    }
	
	/**
	 * 易宝支付付款通知处理
	 * @param request
	 * @return
	 */
	@Override
	public JfiYeepayLog getJfiYeepayLog(HttpServletRequest request) {

		log.info("=========易宝支付验签开始=========="+request.getRequestURL().toString() + "?" + request.getQueryString());
		
		boolean check = this.checkJfiYeepayLog(request);
		
		log.info("=========易宝支付2==========");
		
		JfiYeepayLog jfiYeepayLog = setJfiYeepayLog(request);
		log.info("=========易宝支付3==========");
		if(check){
			
			//重复
			if(jfiYeepayLogManager.getJfiYeepayLogsByDealId(jfiYeepayLog.getDetailId()).size()>0){
				
				jfiYeepayLog.setReturnMsg("3");//重发数据
			}else{
				
				jfiYeepayLog.setReturnMsg("10");//验签成功
			}
		}else{
			
			jfiYeepayLog.setReturnMsg("4");//验签失败
		}
		log.info("=========易宝支付4==========");
		jfiYeepayLog.setCreateTime(new Date());
		jfiYeepayLogManager.saveJfiYeepayLog(jfiYeepayLog);
		
		return jfiYeepayLog;
	}
	
	public JfiYeepayLog setJfiYeepayLog(HttpServletRequest request){
		
		JfiYeepayLog jfiYeepayLog = new JfiYeepayLog();
		
		//String[] ext2 = request.getParameter("r8_MP").split(",");
        //String userCode = ext2[0];//获取付款会员身份
		String reserved = request.getParameter("r8_MP");
		RemarkBean bean = PayUtils.getRemarkBean(reserved);
		
		jfiYeepayLog.setUserCode(bean.getUserCode());//会员编号
		jfiYeepayLog.setMerchantId(request.getParameter("p1_MerId"));//商户号
		
		jfiYeepayLog.setOrderId(request.getParameter("r6_Order"));//订单ID
		jfiYeepayLog.setOrderAmount(request.getParameter("r3_Amt"));//商户订单金额，单位为元
	    jfiYeepayLog.setOrderTime(request.getParameter("rp_PayDate"));//商户订单提交时间,格式：yyyy-MM-ddhh:mm:ss		
        
	    jfiYeepayLog.setDetailId(request.getParameter("r2_TrxId"));//易宝平台流水
        jfiYeepayLog.setDetailTime(request.getParameter("rp_PayDate"));//易宝平台交易时间
        jfiYeepayLog.setBankDealId(request.getParameter("ro_BankOrderId"));//银行处理流水
        jfiYeepayLog.setBankId(request.getParameter("rb_BankId"));//银行编号
        //jfiYeepayLog.setErrMsg(reserved);
        jfiYeepayLog.setAmount(request.getParameter("r3_Amt"));//付款金额，单位为元
        if("RMB".equals(request.getParameter("r4_Cur")))
        	jfiYeepayLog.setAmtType("01");//付款币种
        
        if("1".equals(request.getParameter("r1_Code")))//支付结果, 1：为成功；
        	jfiYeepayLog.setPayResult("00");
        else
        	jfiYeepayLog.setPayResult("01");
        
        jfiYeepayLog.setSignMsg(request.getParameter("hmac"));//签名字符串
        
        jfiYeepayLog.setCompanyCode("CN");//
        jfiYeepayLog.setUrl(request.getRequestURL().toString() + "?" + request.getQueryString());//
        jfiYeepayLog.setDataType("1");//数据来源，1：PC，2：手机终端
        jfiYeepayLog.setInc("0");//初始化为未进存折
		
		return jfiYeepayLog;
	}
	
	public boolean checkJfiYeepayLog(HttpServletRequest request) {
		
		String p1_MerId				= formatString(request.getParameter("p1_MerId"));
		String r0_Cmd               = formatString(request.getParameter("r0_Cmd"));
		String r1_Code              = formatString(request.getParameter("r1_Code"));//支付结果，1成功
		String r2_TrxId             = formatString(request.getParameter("r2_TrxId"));//流水
		String r3_Amt               = formatString(request.getParameter("r3_Amt"));
		String r4_Cur               = formatString(request.getParameter("r4_Cur"));
		String r5_Pid               = formatString(request.getParameter("r5_Pid"));
		String r6_Order             = formatString(request.getParameter("r6_Order"));//订单号
		String r7_Uid               = formatString(request.getParameter("r7_Uid"));
		String r8_MP                = formatString(request.getParameter("r8_MP"));
		String r9_BType             = formatString(request.getParameter("r9_BType"));
		//String rb_BankId            = formatString(request.getParameter("rb_BankId"));
	//	String ro_BankOrderId       = formatString(request.getParameter("ro_BankOrderId"));
		//String rp_PayDate           = formatString(request.getParameter("rp_PayDate"));
		//String rq_CardNo            = formatString(request.getParameter("rq_CardNo"));
		//String ru_Trxtime           = formatString(request.getParameter("ru_Trxtime"));
		//String rq_SourceFee         = formatString(request.getParameter("rq_SourceFee"));
		//String rq_TargetFee         = formatString(request.getParameter("rq_TargetFee"));
		String hmac		            = formatString(request.getParameter("hmac"));
		
		String[] strArr	= {p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType };

		boolean hmacIsCorrect = verifyCallbackHmac(strArr, hmac, getKeyByMerId(p1_MerId));
		
		if(hmacIsCorrect) {
			if(r9_BType.equals("2")) {
				return true;
			}
		} else {
			return false;
		}
		
		return false;
	}
	
	String formatString(String text) {
		return (text == null) ? "" : text.trim();
	}
	
	/**
	 * verifyCallbackHmac() : 验证回调参数是否有效
	 */
	public static boolean verifyCallbackHmac(String[] stringValue, String hmacFromYeepay, String keyValue) {

		StringBuffer sourceData	= new StringBuffer();
		for(int i = 0; i < stringValue.length; i++) {
			sourceData.append(stringValue[i]);
		}

		String localHmac = DigestUtil.getHmac(stringValue, keyValue);

		StringBuffer hmacSource	= new StringBuffer();
		for(int i = 0; i < stringValue.length; i++) {
			hmacSource.append(stringValue[i]);
		}

		return (localHmac.equals(hmacFromYeepay) ? true : false);
	}
	
	/**
	 * 根据商户号获取key
	 * @param merId
	 * @return
	 */
	public String getKeyByMerId(String merId){
		
		if("10012475322".equals(merId)){//河南		
			return "XLrJ33M177YX2B857Jv3XwKyNi9C9AmhpboG0p7391U7Mzao6X1pH7g0YKo1";
		}
		if ("10012474429".equals(merId)){ // 徐州佰兰德商贸有限公司  广东
			return "6133ZSjo43BojuQydX60966RkgJT5X2TFETs92n6641u865T46245729R20c";
		}
		
		if ("10012552798".equals(merId)){// 重庆美森商贸有限责任公司
			return "W2jW2B31d1212444e45sqSb4H5r78o9Ro8p66680v5i82oFF2Rgr629898h1";
		}
		if ("10012553418".equals(merId)){// 哈密市小本商贸有限责任公司
			return "IVMPb75eS6f0q7DT9YhD50koKUdA53q5hzn158Dh6917VV4P2ow4B4085Cxd";
		}
		if ("10012553444".equals(merId)){// 成武县圣和商贸有限公司
			return "3a4aFRv82K3KjJ7A562q47zs2p2R852Ds7YbN51Wf3G62wXb09oN91591tYq";
		}
		if ("10012553386".equals(merId)){// 潍坊乌拉商贸有限公司
			return "iyd8Jec5EL81uI80v5G5p753iF5E4GNvHG36WJ721D0B98PL8924056760vY";
		}
		
		if("10012563264".equals(merId)){	
			return "438mv0Lo38n05Ghjr9B57644F91dr62PW5Ah56G67x6pe48694U77pX8RTt0";
		}
		if("10012562338".equals(merId)){	
			return "92j716Vu3cX62b2b79z1PS787Asr392Y81B97V5Y7jw2f6690y433F9xy92U";
		}
		if("10012562222".equals(merId)){	
			return "4Z49WX140x30t5q01T0vT8Vol72qIM1y1Q1JFH05m8978r73Z5urtfsnVq10";
		}
		if("10012562279".equals(merId)){	
			return "2VI46B90e1Esf103ZEy8p5hK3G9Z5728476U6sV226n8T8j98kj0z4C0i2w4";
		}
		if("10012562324".equals(merId)){	
			return "JJ0r270IVF9Je58pP245tY66JL8B8lP9av555604c0MZ3s90NQ7Thn42u477";
		}
		if("10012562193".equals(merId)){	
			return "2vBuu845Rq84Wl33Yt8939G3oz4H087QoEl5Fv5G0B221lh001v3N49214i5";
		}
		if("10012562366".equals(merId)){	
			return "24S0kbu8jm7o50yJ8N1726pqUjjPJ6046358802lUf1g554136742p03W9o6";
		}
		if("10012592595".equals(merId)){	
			return "l5Qk3N39Bf71l2X3FW1uK5oR98D0Y885319gL325x2vW9HaRl474k8pm2Xco";
		}
		return null;
	}
}
