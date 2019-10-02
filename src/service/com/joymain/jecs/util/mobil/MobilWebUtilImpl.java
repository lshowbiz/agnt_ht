package com.joymain.jecs.util.mobil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

import com.joymain.jecs.fi.model.Jfi99billLog;
import com.joymain.jecs.fi.service.Jfi99billLogManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.util.bill99.Bill99Constants;
import com.joymain.jecs.util.bill99.MD5Util;
/**
 * 手机快钱支付验签入账处理实现类
 * @author Administrator
 *
 */
public class MobilWebUtilImpl implements MobilWebUtil {
	
	private Jfi99billLogManager jfi99billLogManager = null;
	private JpoMemberOrderManager jpoMemberOrderManager = null;

	public void setJfi99billLogManager(Jfi99billLogManager jfi99billLogManager) {
		this.jfi99billLogManager = jfi99billLogManager;
	}

	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	final String pubKeyName = "key/app/99bill.cert.rsa.20340630.cer";
	
	/**
	 * 验签 (PKI)
	 * @param request
	 * @return
	 */
	public boolean checkJfi99billLog(HttpServletRequest request){
		
		boolean flag=false;
		
		//人民币网关账号，该账号为11位人民币网关商户编号+01,该值与提交时相同。
		String merchantAcctId = request.getParameter("merchantAcctId");
		//网关版本，固定值：v2.0,该值与提交时相同。
		String version = request.getParameter("version");
		//语言种类，1代表中文显示，2代表英文显示。默认为1,该值与提交时相同。
		String language = request.getParameter("language");
		//签名类型,该值为4，代表PKI加密方式,该值与提交时相同。
		String signType = request.getParameter("signType");
		//支付方式，一般为00，代表所有的支付方式。如果是银行直连商户，该值为10,该值与提交时相同。
		String payType = request.getParameter("payType");
		//银行代码，如果payType为00，该值为空；如果payType为10,该值与提交时相同。
		String bankId = request.getParameter("bankId");
		//商户订单号，该值与提交时相同。
		String orderId = request.getParameter("orderId");
		//订单提交时间，格式：yyyyMMddHHmmss，如：20071117020101,该值与提交时相同。
		String orderTime = request.getParameter("orderTime");
		//订单金额，金额以“分”为单位，商户测试以1分测试即可，切勿以大金额测试,该值与支付时相同。
		String orderAmount = request.getParameter("orderAmount");
		// 快钱交易号，商户每一笔交易都会在快钱生成一个交易号。
		String dealId = request.getParameter("dealId");
		//银行交易号 ，快钱交易在银行支付时对应的交易号，如果不是通过银行卡支付，则为空
		String bankDealId = request.getParameter("bankDealId");
		//快钱交易时间，快钱对交易进行处理的时间,格式：yyyyMMddHHmmss，如：20071117020101
		String dealTime = request.getParameter("dealTime");
		//商户实际支付金额 以分为单位。比方10元，提交时金额应为1000。该金额代表商户快钱账户最终收到的金额。
		String payAmount = request.getParameter("payAmount");
		//费用，快钱收取商户的手续费，单位为分。
		String fee = request.getParameter("fee");
		//扩展字段1，该值与提交时相同。
		String ext1 = request.getParameter("ext1");
		//扩展字段2，该值与提交时相同。
		String ext2 = request.getParameter("ext2");
		//处理结果， 10支付成功，11 支付失败，00订单申请成功，01 订单申请失败
		String payResult = request.getParameter("payResult");
		String key = request.getParameter("key");
		if(key == null)
			key = "7Y43ME4H3Y5D3RG9";
		
		//错误代码 ，请参照《人民币网关接口文档》最后部分的详细解释。
		String errCode = request.getParameter("errCode");
		//签名字符串 
		String signMsg = request.getParameter("signMsg");
		String bindCard = request.getParameter("bindCard");
		String bindMobile = request.getParameter("bindMobile");
		
		
		String merchantSignMsgVal = "";
		merchantSignMsgVal = appendParam(merchantSignMsgVal,"merchantAcctId", merchantAcctId);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "version",version);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "language",language);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "signType",signType);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "payType",payType);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "bankId",bankId);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "orderId",orderId);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "orderTime",orderTime);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "orderAmount",orderAmount);	
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "bindCard",bindCard);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "bindMobile",bindMobile);	
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "dealId",dealId);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "bankDealId",bankDealId);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "dealTime",dealTime);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "payAmount",payAmount);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "fee", fee);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "ext1", ext1);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "ext2", ext2);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "payResult",payResult);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "errCode",errCode);
		try {
			//PKIUtil pkiUtil=new PKIUtil();
			boolean verifyData = verifyData(merchantSignMsgVal, signMsg, pubKeyName);
			flag = verifyData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	
	/**
	 * 验签
	 * @param request
	 * @return
	 */
	public boolean checkMD5Jfi99billLog(HttpServletRequest request){
		
		boolean flag=false;
		
		//人民币网关账号，该账号为11位人民币网关商户编号+01,该值与提交时相同。
		String merchantAcctId = request.getParameter("merchantAcctId");
		//网关版本，固定值：v2.0,该值与提交时相同。
		String version = request.getParameter("version");
		//语言种类，1代表中文显示，2代表英文显示。默认为1,该值与提交时相同。
		String language = request.getParameter("language");
		//签名类型,该值为4，代表PKI加密方式,该值与提交时相同。
		String signType = request.getParameter("signType");
		//支付方式，一般为00，代表所有的支付方式。如果是银行直连商户，该值为10,该值与提交时相同。
		String payType = request.getParameter("payType");
		//银行代码，如果payType为00，该值为空；如果payType为10,该值与提交时相同。
		String bankId = request.getParameter("bankId");
		//商户订单号，该值与提交时相同。
		String orderId = request.getParameter("orderId");
		//订单提交时间，格式：yyyyMMddHHmmss，如：20071117020101,该值与提交时相同。
		String orderTime = request.getParameter("orderTime");
		//订单金额，金额以“分”为单位，商户测试以1分测试即可，切勿以大金额测试,该值与支付时相同。
		String orderAmount = request.getParameter("orderAmount");
		// 快钱交易号，商户每一笔交易都会在快钱生成一个交易号。
		String dealId = request.getParameter("dealId");
		//银行交易号 ，快钱交易在银行支付时对应的交易号，如果不是通过银行卡支付，则为空
		String bankDealId = request.getParameter("bankDealId");
		//快钱交易时间，快钱对交易进行处理的时间,格式：yyyyMMddHHmmss，如：20071117020101
		String dealTime = request.getParameter("dealTime");
		//商户实际支付金额 以分为单位。比方10元，提交时金额应为1000。该金额代表商户快钱账户最终收到的金额。
		String payAmount = request.getParameter("payAmount");
		//费用，快钱收取商户的手续费，单位为分。
		String fee = request.getParameter("fee");
		//扩展字段1，该值与提交时相同。
		String ext1 = request.getParameter("ext1");
		//扩展字段2，该值与提交时相同。
		String ext2 = request.getParameter("ext2");
		
		//处理结果， 10支付成功，11 支付失败，00订单申请成功，01 订单申请失败
		String payResult = request.getParameter("payResult");
		
		String key = request.getParameter("key");
		if(key == null)
			key = "7Y43ME4H3Y5D3RG9";
		
		//错误代码 ，请参照《人民币网关接口文档》最后部分的详细解释。
		String errCode = request.getParameter("errCode");
		//签名字符串 
		String signMsg = request.getParameter("signMsg");
		String bindCard = request.getParameter("bindCard");
		String bindMobile = request.getParameter("bindMobile");
		
		
		String merchantSignMsgVal = "";
		merchantSignMsgVal = appendParam(merchantSignMsgVal,"merchantAcctId", merchantAcctId);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "version",version);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "language",language);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "signType",signType);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "payType",payType);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "bankId",bankId);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "orderId",orderId);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "orderTime",orderTime);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "orderAmount",orderAmount);
		
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "bindCard",bindCard);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "bindMobile",bindMobile);
		
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "dealId",dealId);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "bankDealId",bankDealId);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "dealTime",dealTime);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "payAmount",payAmount);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "fee", fee);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "ext1", ext1);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "ext2", ext2);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "payResult",payResult);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "errCode",errCode);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "key",key);

		//Pkipair pki = new Pkipair();
		//System.out.println(signMsgVal);
		String merchantSignMsg = "";
		
		try {
			merchantSignMsg = MD5Util.md5Hex(merchantSignMsgVal.getBytes("UTF-8")).toUpperCase();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(merchantSignMsg.equals(signMsg)){
			flag = true;
		}
		
		return flag;
	}
	
	public String appendParam(String returns, String paramId, String paramValue) {
		if (!returns.equals("")) {

			if (paramValue!=null && !("").equals(paramValue)) {
				returns += "&" + paramId + "=" + paramValue;
			}

		} else {

			if (paramValue!=null && !("").equals(paramValue)) {
				returns = paramId + "=" + paramValue;
			}
		}

		return returns;
	}
	
	/**
	 * 生成初始Jfi99billLog
	 */
	public Jfi99billLog setJfi99billLog(HttpServletRequest request){
		//人民币网关账号，该账号为11位人民币网关商户编号+01,该值与提交时相同。
		String merchantAcctId = request.getParameter("merchantAcctId");
		//网关版本，固定值：v2.0,该值与提交时相同。
		String version = request.getParameter("version");
		//语言种类，1代表中文显示，2代表英文显示。默认为1,该值与提交时相同。
		String language = request.getParameter("language");
		//签名类型,该值为4，代表PKI加密方式,该值与提交时相同。
		String signType = request.getParameter("signType");
		//支付方式，一般为00，代表所有的支付方式。如果是银行直连商户，该值为10,该值与提交时相同。
		String payType = request.getParameter("payType");
		//银行代码，如果payType为00，该值为空；如果payType为10,该值与提交时相同。
		String bankId = request.getParameter("bankId");
		//商户订单号，该值与提交时相同。
		String orderId = request.getParameter("orderId");
		//订单提交时间，格式：yyyyMMddHHmmss，如：20071117020101,该值与提交时相同。
		String orderTime = request.getParameter("orderTime");
		//订单金额，金额以“分”为单位，商户测试以1分测试即可，切勿以大金额测试,该值与支付时相同。
		String orderAmount = request.getParameter("orderAmount");
		// 快钱交易号，商户每一笔交易都会在快钱生成一个交易号。
		String dealId = request.getParameter("dealId");
		//银行交易号 ，快钱交易在银行支付时对应的交易号，如果不是通过银行卡支付，则为空
		String bankDealId = request.getParameter("bankDealId");
		//快钱交易时间，快钱对交易进行处理的时间,格式：yyyyMMddHHmmss，如：20071117020101
		String dealTime = request.getParameter("dealTime");
		//商户实际支付金额 以分为单位。比方10元，提交时金额应为1000。该金额代表商户快钱账户最终收到的金额。
		String payAmount = request.getParameter("payAmount");
		//费用，快钱收取商户的手续费，单位为分。
		String fee = request.getParameter("fee");
		//扩展字段1，该值与提交时相同。
		String ext1 = request.getParameter("ext1");
		//扩展字段2，该值与提交时相同。
		String ext2 = request.getParameter("ext2");
		
		//处理结果， 10支付成功，11 支付失败，00订单申请成功，01 订单申请失败
		String payResult = request.getParameter("payResult");
		
		//错误代码 ，请参照《人民币网关接口文档》最后部分的详细解释。
		String errCode = request.getParameter("errCode");
		//签名字符串 
		String signMsg = request.getParameter("signMsg");
		String bindCard = request.getParameter("bindCard");
		String bindMobile = request.getParameter("bindMobile");
		
		
		String merchantSignMsgVal = "";
		merchantSignMsgVal = appendParam(merchantSignMsgVal,"merchantAcctId", merchantAcctId);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "version",version);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "language",language);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "signType",signType);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "payType",payType);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "bankId",bankId);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "orderId",orderId);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "orderTime",orderTime);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "orderAmount",orderAmount);
		
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "bindCard",bindCard);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "bindMobile",bindMobile);
		
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "dealId",dealId);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "bankDealId",bankDealId);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "dealTime",dealTime);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "payAmount",payAmount);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "fee", fee);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "ext1", ext1);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "ext2", ext2);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "payResult",payResult);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "errCode",errCode);
		
		//记录快钱支付记录
		Jfi99billLog jfi99billLog = new Jfi99billLog();
		
        jfi99billLog.setInc("0");//1进电子存折 0没进电子存折
        jfi99billLog.setCompanyCode("CN");
        jfi99billLog.setUrl(request.getRequestURL().toString() + "?" + request.getQueryString());//快钱返回的RUL
        
        //获取订单编号、会员编号、flag
       // String externalTraceNo[] = request.getParameter("ext2").split(",");
        String reserved = request.getParameter("ext2");
        String jstr = reserved.substring(reserved.indexOf("[") + 1, reserved.indexOf("]"));
		JSONObject json = JSONObject.fromObject(jstr);;
        String flag = json.getString("payType");//externalTraceNo[1];//标识,1:订单支付,0：充值
        String userCode = json.getString("userCode");//externalTraceNo[0];//会员编号
        
        jfi99billLog.setUserCode(userCode);//会员编号
        jfi99billLog.setOrderId(orderId);//会员订单号
        
        //获取实际支付金额
        //BigDecimal amount = new BigDecimal(payAmount).multiply(new BigDecimal(100));
        jfi99billLog.setPayAmount(payAmount);
        
        if("1".equals(flag)){//订单审核
        	jfi99billLog.setOrderAmount(this.getOrderAmountByOrderId(orderId, payAmount.toString()));//根据订单ID获取订单总金额
        }
        if("0".equals(flag)){//充值
        	jfi99billLog.setOrderAmount(payAmount);
        }
        
        jfi99billLog.setOrderTime(orderTime);
        
        jfi99billLog.setFee(fee);//快钱交易手续费
        
        jfi99billLog.setDealId(dealId);//获取快钱交易号，获取该交易在快钱的交易号,暂时读取授权号
        
        //获取银行代码 参见银行代码列表，暂时读取发卡机构
        jfi99billLog.setBankId(bankId);
        jfi99billLog.setBankDealId(bankDealId);
        //jfi99billLog.setBankDealId(request.getParameter("RRN"));//获取银行交易参考号
        jfi99billLog.setDealTime(dealTime);//获取在快钱交易时间
        jfi99billLog.setMerchantAcctId(merchantAcctId);//获取人民币网关账户号,快钱商户号
        
        jfi99billLog.setErrCode(errCode);//设置错误码，获取交易返回码
        jfi99billLog.setExt2(ext2);//设置扩展字段2,会员编号、标识
        jfi99billLog.setPayType("2");//设置支付方式,代表手机支付
        jfi99billLog.setBillLanguage(language);//设置语言种类,1代表中文显示，2代表英文显示。默认为1,该值与提交时相同。
        jfi99billLog.setSignType(signType);//签名类型.固 1代表MD5签名 当前版本固定为1,该值为4，代表PKI加密方式
        jfi99billLog.setSignMsg(signMsg);//获取加密签名串
        
        //处理结果， 10支付成功，11 支付失败，00订单申请成功，01 订单申请失败
        jfi99billLog.setPayResult(payResult);
        jfi99billLog.setVersion(version);
        jfi99billLog.setExt1(ext1);
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
	
	public boolean verifyData(String orgdata, String sign, String pubKeyNames) {
		BASE64Decoder decoder = new BASE64Decoder();
		boolean flag = false;
		InputStream inputStream = null;
		try {
			if (Bill99Constants.publicKey == null) {
				String file = this.getClass().getClassLoader().getResource(pubKeyNames).getPath();
				file = URLDecoder.decode(file, "utf-8");
				inputStream = new FileInputStream(new File(file));
				CertificateFactory certificateFactory = CertificateFactory.getInstance("X509");// 初始化密钥
				Certificate certificate = certificateFactory.generateCertificate(inputStream);// 加载公钥
				PublicKey publicKey = certificate.getPublicKey();// 获取公钥
				Bill99Constants.publicKey = publicKey;
				byte[] signData = decoder.decodeBuffer(sign);
				Signature s = Signature.getInstance("SHA1withRSA");
				s.initVerify(publicKey);
				s.update(orgdata.getBytes("utf-8"));
				flag = s.verify(signData);// 验签字符串
			} else {
				byte[] signData;
				signData = decoder.decodeBuffer(sign);
				Signature s = Signature.getInstance("SHA1withRSA");
				s.initVerify(Bill99Constants.publicKey);
				s.update(orgdata.getBytes("utf-8"));
				flag = s.verify(signData);// 验签字符串
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	/**
	 * 根据订单ID获取订单总金额,如果查找不到订单，返回支付金额
	 * @param moId
	 * @param amount
	 * @return
	 */
	public String getOrderAmountByOrderId(String moId, String amount){
		
		try{
			
			JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(moId);//查询订单对象
		
			if(jpoMemberOrder!=null){
				
				BigDecimal payAmount = jpoMemberOrder.getAmount().multiply(new BigDecimal(100));
				return payAmount.toString();
			}else{
				
				return amount;
			}
		}catch(Exception e){
			
			return amount;
		}
	}
	
	public static void main(String[] args) { 
		String reserved ="[{userCode:\"CN37466666\",payType:\"1\",payFee:0.00,dataType:2,isFull:false}]";
		
		 String jstr = reserved.substring(reserved.indexOf("[") + 1, reserved.indexOf("]"));
		
		JSONObject json = JSONObject.fromObject(jstr);;
		System.out.println(json.get("userCode")+"=="+json.get("payFee"));
		
	}
}
