package com.joymain.jecs.util.alipay;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.fi.model.JfiAlipayLog;
import com.joymain.jecs.fi.service.JfiAlipayLogManager;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.util.string.StringUtil;

public class AlipayUtilImpl implements AlipayUtil {
    private SysIdManager sysIdManager = null;
	private JfiAlipayLogManager jfiAlipayLogManager = null;
	
	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}

	public void setJfiAlipayLogManager(JfiAlipayLogManager jfiAlipayLogManager) {
		this.jfiAlipayLogManager = jfiAlipayLogManager;
	}

	/**
	 * 生成Alipay对像
	 * flag(0:为电子存折  1:订单)
	 */
	public Alipay getAlipay(Alipay alipay, int flag) {
		alipay.set_input_charset(AlipayConstants._input_charset);
		alipay.setBody(AlipayConstants.body);
		alipay.setNotify_url(AlipayConstants.notify_url);
		alipay.setPartner(AlipayConstants.account.get("abc").get("partnerID"));
		alipay.setPayment_type(AlipayConstants.payment_type);
		alipay.setPaymethod(AlipayConstants.paymethod);
		alipay.setReturn_url(AlipayConstants.return_url);
		alipay.setSeller_email(AlipayConstants.account.get("abc").get("sellerEmail"));
		alipay.setService(AlipayConstants.service);
		alipay.setShow_url(AlipayConstants.show_url);
		alipay.setSign_type(AlipayConstants.sign_type);
		alipay.setSubject(AlipayConstants.subject);
		//alipay.setTotal_fee(total_fee);
		//alipay.setDefaultbank(defaultbank);
		BigDecimal orderFee = new BigDecimal(alipay.getTotal_fee());
		alipay.setExtra_common_param(alipay.getExtra_common_param() + "," + String.valueOf(flag) + "," + orderFee);
		//alipay.setOut_trade_no(out_trade_no);
		if(flag==0){
			alipay.setOut_trade_no(sysIdManager.buildIdStr("advicecode_cn"));
		}
		alipay.setSign(getSignMsg(alipay));
		
		try {
			String str = "";
			str += "_input_charset=" + URLEncoder.encode(alipay.get_input_charset(),AlipayConstants._input_charset);
			if(!StringUtil.isEmpty(alipay.getBody())){
				str += "&body=" + URLEncoder.encode(alipay.getBody(),AlipayConstants._input_charset);
			}
			str += "&defaultbank=" + URLEncoder.encode(alipay.getDefaultbank(),AlipayConstants._input_charset);
			str += "&extra_common_param=" + alipay.getExtra_common_param();
			str += "&notify_url=" + URLEncoder.encode(alipay.getNotify_url(),AlipayConstants._input_charset);
			str += "&out_trade_no=" + URLEncoder.encode(alipay.getOut_trade_no(),AlipayConstants._input_charset);
			str += "&partner=" + URLEncoder.encode(alipay.getPartner(),AlipayConstants._input_charset);
			str += "&payment_type=" + URLEncoder.encode(alipay.getPayment_type(),AlipayConstants._input_charset);
			str += "&paymethod=" + URLEncoder.encode(alipay.getPaymethod(),AlipayConstants._input_charset);
			str += "&return_url=" + URLEncoder.encode(alipay.getReturn_url(),AlipayConstants._input_charset);
			str += "&seller_email=" + URLEncoder.encode(alipay.getSeller_email(),AlipayConstants._input_charset);
			str += "&service=" + URLEncoder.encode(alipay.getService(),AlipayConstants._input_charset);
			if(!StringUtil.isEmpty(alipay.getShow_url())){
				str += "&show_url=" + URLEncoder.encode(alipay.getShow_url(),AlipayConstants._input_charset);
			}
			str += "&subject=" + URLEncoder.encode(alipay.getSubject(),AlipayConstants._input_charset);
			str += "&total_fee=" + URLEncoder.encode(alipay.getTotal_fee(),AlipayConstants._input_charset);
			alipay.setPostUrl(AlipayConstants.postUrl + "?" + str + "&sign_type=MD5&sign=" + alipay.getSign());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return alipay;
	}
	
	/**
	 * 生成签名
	 * @param alipay
	 * @return
	 */
	private String getSignMsg(Alipay alipay){
		String str = "";
		str += "_input_charset=" + alipay.get_input_charset();
		if(!StringUtil.isEmpty(alipay.getBody())){
			str += "&body=" + alipay.getBody();
		}
		str += "&defaultbank=" + alipay.getDefaultbank();
		str += "&extra_common_param=" + alipay.getExtra_common_param();
		str += "&notify_url=" + alipay.getNotify_url();
		str += "&out_trade_no=" + alipay.getOut_trade_no();
		str += "&partner=" + alipay.getPartner();
		str += "&payment_type=" + alipay.getPayment_type();
		str += "&paymethod=" + alipay.getPaymethod();
		str += "&return_url=" + alipay.getReturn_url();
		str += "&seller_email=" + alipay.getSeller_email();
		str += "&service=" + alipay.getService();
		if(!StringUtil.isEmpty(alipay.getShow_url())){
			str += "&show_url=" + alipay.getShow_url();
		}
		str += "&subject=" + alipay.getSubject();
		str += "&total_fee=" + alipay.getTotal_fee();
		str += AlipayConstants.account.get("abc").get("key");
		//_input_charset=GBK&body=商品描述&defaultbank=ICBCB2C&notify_url=http://localhost:8080/jsp_direct_gbk/alipay_notify.jsp&out_trade_no=20100804171256&partner=2088101568345155&payment_type=1&paymethod=bankPay&return_url=http://localhost:8080/jsp_direct_gbk/alipay_return.jsp&seller_email=gwl25@126.com&service=create_direct_pay_by_user&show_url=商品展示地址&subject=商品名称*商品订单号：20100804171256&total_fee=1000xu6xamwvgk5b51ahco9sgpbxy1e49ve9
		return Md5Encrypt.md5(str);
	}

	public static void main(String[] args) {
		AlipayUtilImpl alipayUtilImpl = new AlipayUtilImpl();
		Alipay alipay = new Alipay();
		alipay.setTotal_fee("600");
		alipay.setOut_trade_no("120743");
		alipay.setDefaultbank("ICBCB2C");
		alipay.setExtra_common_param("CN99909438");
		alipayUtilImpl.getAlipay(alipay,1);
	}

	public JfiAlipayLog getJfiAlipayLog(HttpServletRequest request, String userCode, String companyCode) {
		if(!request.getRequestURL().toString().contains("jfiPayAlipayReceiveNotify.html")){
			JfiAlipayLog jfiAlipayLog = this.setJfiAlipayLog(request, userCode, companyCode);
			jfiAlipayLog = this.checkJfiAlipayLog(jfiAlipayLog);
	        return jfiAlipayLog;
		}else{
			JfiAlipayLog jfiAlipayLog = this.setJfiAlipayLogNotify(request, userCode, companyCode);
			jfiAlipayLog = this.checkJfiAlipayLogNotify(jfiAlipayLog);
	        return jfiAlipayLog;
		}
	}

	/**
	 * jfiAlipayLog.getReturnMsg()
	 * 1responseTxt不为true
	 * 2验签失败
	 * 3交易类型不为TRADE_SUCCESS
	 * 4重复发送
	 * 10成功
	 */
	private JfiAlipayLog checkJfiAlipayLogNotify(JfiAlipayLog jfiAlipayLog){
        AlipayCheckURL alipayCheckURL = new AlipayCheckURL();
        String urlvalue = AlipayConstants.alipayNotifyURL + "partner=" + AlipayConstants.accountEmail.get(jfiAlipayLog.getSellerEmail()) + "&notify_id=" + jfiAlipayLog.getNotifyId();
        String responseTxt = alipayCheckURL.check(urlvalue);
        if("true".equals(responseTxt)){
            String signMsgVal = "";
            signMsgVal=appendParam(signMsgVal,"body",jfiAlipayLog.getBody());
            signMsgVal=appendParam(signMsgVal,"buyer_email",jfiAlipayLog.getBuyerEmail());
            signMsgVal=appendParam(signMsgVal,"buyer_id",jfiAlipayLog.getBuyerId());
            signMsgVal=appendParam(signMsgVal,"discount",jfiAlipayLog.getDiscount());
            //signMsgVal=appendParam(signMsgVal,"exterface",jfiAlipayLog.getExterface());
            signMsgVal=appendParam(signMsgVal,"extra_common_param",jfiAlipayLog.getExtraCommonParam());
            signMsgVal=appendParam(signMsgVal,"gmt_create",jfiAlipayLog.getGmtCreate());
            signMsgVal=appendParam(signMsgVal,"gmt_payment",jfiAlipayLog.getGmtPayment());
            //signMsgVal=appendParam(signMsgVal,"is_success",jfiAlipayLog.getIsSuccess());
            signMsgVal=appendParam(signMsgVal,"is_total_fee_adjust",jfiAlipayLog.getIsTotalFeeAdjust());
            signMsgVal=appendParam(signMsgVal,"notify_id",jfiAlipayLog.getNotifyId());
            signMsgVal=appendParam(signMsgVal,"notify_time",jfiAlipayLog.getNotifyTime());
            signMsgVal=appendParam(signMsgVal,"notify_type",jfiAlipayLog.getNotifyType());
            signMsgVal=appendParam(signMsgVal,"out_trade_no",jfiAlipayLog.getOutTradeNo());
            signMsgVal=appendParam(signMsgVal,"payment_type",jfiAlipayLog.getPaymentType());
            signMsgVal=appendParam(signMsgVal,"price",jfiAlipayLog.getPrice());
            signMsgVal=appendParam(signMsgVal,"quantity",jfiAlipayLog.getQuantity());
            signMsgVal=appendParam(signMsgVal,"seller_email",jfiAlipayLog.getSellerEmail());
            signMsgVal=appendParam(signMsgVal,"seller_id",jfiAlipayLog.getSellerId());
            //signMsgVal=appendParam(signMsgVal,"sign",jfiAlipayLog.getSign());
            //signMsgVal=appendParam(signMsgVal,"sign_type",jfiAlipayLog.getSignType());
            signMsgVal=appendParam(signMsgVal,"subject",jfiAlipayLog.getSubject());
            signMsgVal=appendParam(signMsgVal,"total_fee",jfiAlipayLog.getTotalFee());
            signMsgVal=appendParam(signMsgVal,"trade_no",jfiAlipayLog.getTradeNo());
            signMsgVal=appendParam(signMsgVal,"trade_status",jfiAlipayLog.getTradeStatus());
            signMsgVal=appendParam(signMsgVal,"use_coupon",jfiAlipayLog.getUseCoupon());
            signMsgVal += AlipayConstants.account.get("abc").get("key");
            String mysign = Md5Encrypt.md5(signMsgVal);
            
            if (mysign.equals(jfiAlipayLog.getSign())){
            	if (jfiAlipayLog.getTradeStatus().equals("TRADE_SUCCESS")) {
            		JfiAlipayLog jfiAlipayLogTmp = new JfiAlipayLog();
            		jfiAlipayLogTmp.setInc("1");
            		jfiAlipayLogTmp.setTradeNo(jfiAlipayLog.getTradeNo());
            		if(jfiAlipayLogManager.getJfiAlipayLogs(jfiAlipayLogTmp).size()>0){
            			jfiAlipayLog.setReturnMsg("4");
            		}else{
            			jfiAlipayLog.setReturnMsg("10");
            		}
            	}else{
            		if (jfiAlipayLog.getTradeStatus().equals("WAIT_BUYER_PAY")) {
            			jfiAlipayLog.setReturnMsg("9");
            		}else{
            			jfiAlipayLog.setReturnMsg("3");
            		}
            	}
            }else{
            	jfiAlipayLog.setReturnMsg("2");
            }
            jfiAlipayLogManager.saveJfiAlipayLog(jfiAlipayLog);
            return jfiAlipayLog;
        }else{
        	//responseTxt不为true
            jfiAlipayLog.setReturnMsg("1");
            jfiAlipayLogManager.saveJfiAlipayLog(jfiAlipayLog);
            return jfiAlipayLog;
        }
	}

	/**
	 * 生成初始Jfi99billLog
	 */
	private JfiAlipayLog setJfiAlipayLogNotify(HttpServletRequest request,String userCode,String companyCode){

		JfiAlipayLog jfiAlipayLog = new JfiAlipayLog();
        jfiAlipayLog.setInc("0");
        jfiAlipayLog.setCompanyCode(companyCode);
        jfiAlipayLog.setUrl(request.getRequestURL().toString() + "?" + request.getQueryString());
        jfiAlipayLog.setUserCode(userCode);
        jfiAlipayLog.setReferer(request.getHeader("referer"));
        jfiAlipayLog.setIp(request.getRemoteAddr());
        jfiAlipayLog.setCreateTime(new Date());        
            jfiAlipayLog.setNotifyType(request.getParameter("notify_type"));
            jfiAlipayLog.setNotifyId(request.getParameter("notify_id"));
            jfiAlipayLog.setNotifyTime(request.getParameter("notify_time"));
            jfiAlipayLog.setSign(request.getParameter("sign"));
            jfiAlipayLog.setSignType(request.getParameter("sign_type"));
            if(!StringUtil.isEmpty(request.getParameter("trade_no"))){
                jfiAlipayLog.setTradeNo(request.getParameter("trade_no"));
            }
            if(!StringUtil.isEmpty(request.getParameter("out_trade_no"))){
            	jfiAlipayLog.setOutTradeNo(request.getParameter("out_trade_no"));
            }
            if(!StringUtil.isEmpty(request.getParameter("discount"))){
            	jfiAlipayLog.setDiscount(request.getParameter("discount"));
            }
            if(!StringUtil.isEmpty(request.getParameter("payment_type"))){
            	jfiAlipayLog.setPaymentType(request.getParameter("payment_type"));
            }
            if(!StringUtil.isEmpty(request.getParameter("subject"))){
            	jfiAlipayLog.setSubject(request.getParameter("subject"));
            }
            if(!StringUtil.isEmpty(request.getParameter("price"))){
            	jfiAlipayLog.setPrice(request.getParameter("price"));
            }
            if(!StringUtil.isEmpty(request.getParameter("quantity"))){
            	jfiAlipayLog.setQuantity(request.getParameter("quantity"));
            }
            if(!StringUtil.isEmpty(request.getParameter("total_fee"))){
            	jfiAlipayLog.setTotalFee(request.getParameter("total_fee"));
            }
            if(!StringUtil.isEmpty(request.getParameter("is_total_fee_adjust"))){
            	jfiAlipayLog.setIsTotalFeeAdjust(request.getParameter("is_total_fee_adjust"));
            }
            if(!StringUtil.isEmpty(request.getParameter("gmt_create"))){
            	jfiAlipayLog.setGmtCreate(request.getParameter("gmt_create"));
            }
    		if(!StringUtil.isEmpty(request.getParameter("body"))){
    			jfiAlipayLog.setBody(request.getParameter("body"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("gmt_payment"))){
                jfiAlipayLog.setGmtPayment(request.getParameter("gmt_payment"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("gmt_close"))){
                jfiAlipayLog.setGmtClose(request.getParameter("gmt_close"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("gmt_refund"))){
                jfiAlipayLog.setGmtRefund(request.getParameter("gmt_refund"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("extra_common_param"))){
                jfiAlipayLog.setExtraCommonParam(request.getParameter("extra_common_param"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("trade_status"))){
                jfiAlipayLog.setTradeStatus(request.getParameter("trade_status"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("refund_status"))){
                jfiAlipayLog.setRefundTrade(request.getParameter("refund_status"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("seller_email"))){
                jfiAlipayLog.setSellerEmail(request.getParameter("seller_email"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("seller_id"))){
                jfiAlipayLog.setSellerId(request.getParameter("seller_id"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("buyer_email"))){
                jfiAlipayLog.setBuyerEmail(request.getParameter("buyer_email"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("buyer_id"))){
                jfiAlipayLog.setBuyerId(request.getParameter("buyer_id"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("use_coupon"))){
                jfiAlipayLog.setUseCoupon(request.getParameter("use_coupon"));
    		}
        jfiAlipayLogManager.saveJfiAlipayLog(jfiAlipayLog);
        return jfiAlipayLog;
	}

	/**
	 * 生成初始Jfi99billLog
	 */
	private JfiAlipayLog setJfiAlipayLog(HttpServletRequest request,String userCode,String companyCode){

		JfiAlipayLog jfiAlipayLog = new JfiAlipayLog();
        jfiAlipayLog.setInc("0");
        jfiAlipayLog.setCompanyCode(companyCode);
        jfiAlipayLog.setUrl(request.getRequestURL().toString() + "?" + request.getQueryString());
        jfiAlipayLog.setUserCode(userCode);
        jfiAlipayLog.setReferer(request.getHeader("referer"));
        jfiAlipayLog.setIp(request.getRemoteAddr());
        jfiAlipayLog.setCreateTime(new Date());
        try {
            jfiAlipayLog.setIsSuccess(new String(request.getParameter("is_success").getBytes("ISO-8859-1"), "UTF-8"));
            jfiAlipayLog.setSign(new String(request.getParameter("sign").getBytes("ISO-8859-1"), "UTF-8"));
            jfiAlipayLog.setSignType(new String(request.getParameter("sign_type").getBytes("ISO-8859-1"), "UTF-8"));
    		if(!StringUtil.isEmpty(request.getParameter("bank_seq_no"))){
    			jfiAlipayLog.setBankSeqNo(new String(request.getParameter("bank_seq_no").getBytes("ISO-8859-1"), "UTF-8"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("body"))){
    			jfiAlipayLog.setBody(new String(request.getParameter("body").getBytes("ISO-8859-1"), "UTF-8"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("buyer_email"))){
                jfiAlipayLog.setBuyerEmail(new String(request.getParameter("buyer_email").getBytes("ISO-8859-1"), "UTF-8"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("buyer_id"))){
                jfiAlipayLog.setBuyerId(new String(request.getParameter("buyer_id").getBytes("ISO-8859-1"), "UTF-8"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("exterface"))){
                jfiAlipayLog.setExterface(new String(request.getParameter("exterface").getBytes("ISO-8859-1"), "UTF-8"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("extra_common_param"))){
                jfiAlipayLog.setExtraCommonParam(new String(request.getParameter("extra_common_param").getBytes("ISO-8859-1"), "UTF-8"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("notify_id"))){
                jfiAlipayLog.setNotifyId(new String(request.getParameter("notify_id").getBytes("ISO-8859-1"), "UTF-8"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("notify_time"))){
                jfiAlipayLog.setNotifyTime(new String(request.getParameter("notify_time").getBytes("ISO-8859-1"), "UTF-8"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("notify_type"))){
                jfiAlipayLog.setNotifyType(new String(request.getParameter("notify_type").getBytes("ISO-8859-1"), "UTF-8"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("out_trade_no"))){
                jfiAlipayLog.setOutTradeNo(new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("payment_type"))){
                jfiAlipayLog.setPaymentType(new String(request.getParameter("payment_type").getBytes("ISO-8859-1"), "UTF-8"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("seller_email"))){
                jfiAlipayLog.setSellerEmail(new String(request.getParameter("seller_email").getBytes("ISO-8859-1"), "UTF-8"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("seller_id"))){
                jfiAlipayLog.setSellerId(new String(request.getParameter("seller_id").getBytes("ISO-8859-1"), "UTF-8"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("subject"))){
            	jfiAlipayLog.setSubject(new String(request.getParameter("subject").getBytes("ISO-8859-1"), "UTF-8"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("total_fee"))){
                jfiAlipayLog.setTotalFee(new String(request.getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("trade_no"))){
                jfiAlipayLog.setTradeNo(new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8"));
    		}
    		if(!StringUtil.isEmpty(request.getParameter("trade_status"))){
                jfiAlipayLog.setTradeStatus(new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8"));
    		}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        jfiAlipayLogManager.saveJfiAlipayLog(jfiAlipayLog);
        return jfiAlipayLog;
	}

	/**
	 * jfiAlipayLog.getReturnMsg()
	 * 1responseTxt不为true
	 * 2验签失败
	 * 3交易类型不为TRADE_SUCCESS
	 * 4重复发送
	 * 10成功
	 */
	private JfiAlipayLog checkJfiAlipayLog(JfiAlipayLog jfiAlipayLog){
        AlipayCheckURL alipayCheckURL = new AlipayCheckURL();
        String urlvalue = AlipayConstants.alipayNotifyURL + "partner=" + AlipayConstants.accountEmail.get(jfiAlipayLog.getSellerEmail()) + "&notify_id=" + jfiAlipayLog.getNotifyId();
        String responseTxt = alipayCheckURL.check(urlvalue);
        if("true".equals(responseTxt)){
            String signMsgVal = "";
            signMsgVal=appendParam(signMsgVal,"bank_seq_no",jfiAlipayLog.getBankSeqNo());
            if(!StringUtil.isEmpty(jfiAlipayLog.getBody())){
                signMsgVal=appendParam(signMsgVal,"body",jfiAlipayLog.getBody());
            }
            signMsgVal=appendParam(signMsgVal,"buyer_email",jfiAlipayLog.getBuyerEmail());
            signMsgVal=appendParam(signMsgVal,"buyer_id",jfiAlipayLog.getBuyerId());
            signMsgVal=appendParam(signMsgVal,"exterface",jfiAlipayLog.getExterface());
            signMsgVal=appendParam(signMsgVal,"extra_common_param",jfiAlipayLog.getExtraCommonParam());
            signMsgVal=appendParam(signMsgVal,"is_success",jfiAlipayLog.getIsSuccess());
            signMsgVal=appendParam(signMsgVal,"notify_id",jfiAlipayLog.getNotifyId());
            signMsgVal=appendParam(signMsgVal,"notify_time",jfiAlipayLog.getNotifyTime());
            signMsgVal=appendParam(signMsgVal,"notify_type",jfiAlipayLog.getNotifyType());
            signMsgVal=appendParam(signMsgVal,"out_trade_no",jfiAlipayLog.getOutTradeNo());
            signMsgVal=appendParam(signMsgVal,"payment_type",jfiAlipayLog.getPaymentType());
            signMsgVal=appendParam(signMsgVal,"seller_email",jfiAlipayLog.getSellerEmail());
            signMsgVal=appendParam(signMsgVal,"seller_id",jfiAlipayLog.getSellerId());
            //signMsgVal=appendParam(signMsgVal,"sign",jfiAlipayLog.getSign());
            //signMsgVal=appendParam(signMsgVal,"sign_type",jfiAlipayLog.getSignType());
            signMsgVal=appendParam(signMsgVal,"subject",jfiAlipayLog.getSubject());
            signMsgVal=appendParam(signMsgVal,"total_fee",jfiAlipayLog.getTotalFee());
            signMsgVal=appendParam(signMsgVal,"trade_no",jfiAlipayLog.getTradeNo());
            signMsgVal=appendParam(signMsgVal,"trade_status",jfiAlipayLog.getTradeStatus());
            signMsgVal += AlipayConstants.account.get("abc").get("key");
            String mysign = Md5Encrypt.md5(signMsgVal);
            
            if (mysign.equals(jfiAlipayLog.getSign())){
            	if (jfiAlipayLog.getTradeStatus().equals("TRADE_SUCCESS")) {
            		JfiAlipayLog jfiAlipayLogTmp = new JfiAlipayLog();
            		jfiAlipayLogTmp.setInc("1");
            		jfiAlipayLogTmp.setTradeNo(jfiAlipayLog.getTradeNo());
            		if(jfiAlipayLogManager.getJfiAlipayLogs(jfiAlipayLogTmp).size()>0){
            			jfiAlipayLog.setReturnMsg("4");
            		}else{
            			jfiAlipayLog.setReturnMsg("10");
            		}
            	}else{
            		jfiAlipayLog.setReturnMsg("3");
            	}
            }else{
            	jfiAlipayLog.setReturnMsg("2");
            }
            jfiAlipayLogManager.saveJfiAlipayLog(jfiAlipayLog);
            return jfiAlipayLog;
        }else{
        	//responseTxt不为true
            jfiAlipayLog.setReturnMsg("1");
            jfiAlipayLogManager.saveJfiAlipayLog(jfiAlipayLog);
            return jfiAlipayLog;
        }
	}
	
	//功能函数。将变量值不为空的参数组成字符串
	private static String appendParam(String returnStr,String paramId,String paramValue)
	{
		if(!StringUtil.isEmpty(returnStr))
		{
			if(!StringUtil.isEmpty(paramValue))
			{
				returnStr=returnStr+"&"+paramId+"="+paramValue;
			}
		}
		else
		{
			if(!StringUtil.isEmpty(paramValue))
			{
			returnStr=paramId+"="+paramValue;
			}
		}	
		return returnStr;
	}
}
