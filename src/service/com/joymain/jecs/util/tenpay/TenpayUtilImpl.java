package com.joymain.jecs.util.tenpay;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.fi.model.JfiTenpayLog;
import com.joymain.jecs.fi.service.JfiTenpayLogManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.util.tenpay.Md5Encrypt;


public class TenpayUtilImpl implements TenpayUtil {
    private SysIdManager sysIdManager = null;
	private JfiTenpayLogManager jfiTenpayLogManager = null;
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private JmiMemberManager jmiMemberManager = null;

	public JfiTenpayLog getJfiTenpayLog(HttpServletRequest request, String userCode, String companyCode) {
		JfiTenpayLog jfiTenpayLog = this.setJfiTenpayLog(request, userCode, companyCode);
		jfiTenpayLog = this.checkJfiTenpayLog(jfiTenpayLog);
	    return jfiTenpayLog;
	}

	/**
	 * jfiAlipayLog.getReturnMsg()
	 * 1Pay_Result不为0
	 * 2验签失败
	 * 3交易类型不为TRADE_SUCCESS
	 * 4重复发送
	 * 10成功
	 */
	private JfiTenpayLog checkJfiTenpayLog(JfiTenpayLog jfiTenpayLog){
        if("0".equals(jfiTenpayLog.getPayResult())){
            String signMsgVal = "";
            signMsgVal=appendParam(signMsgVal,"attach",jfiTenpayLog.getAttach());
            signMsgVal=appendParam(signMsgVal,"bargainor_id",jfiTenpayLog.getBargainorId());
            signMsgVal=appendParam(signMsgVal,"bus_args",jfiTenpayLog.getBusArgs());
            signMsgVal=appendParam(signMsgVal,"bus_type",jfiTenpayLog.getBusType());
            signMsgVal=appendParam(signMsgVal,"cmdno",jfiTenpayLog.getCmdno());
            signMsgVal=appendParam(signMsgVal,"date",jfiTenpayLog.getTpDate());
            signMsgVal=appendParam(signMsgVal,"fee_type",jfiTenpayLog.getFeeType());
            signMsgVal=appendParam(signMsgVal,"pay_info",jfiTenpayLog.getPayInfo());
            signMsgVal=appendParam(signMsgVal,"pay_result",jfiTenpayLog.getPayResult());
            signMsgVal=appendParam(signMsgVal,"sp_billno",jfiTenpayLog.getSpBillno());
            signMsgVal=appendParam(signMsgVal,"total_fee",jfiTenpayLog.getTotalFee());
            signMsgVal=appendParam(signMsgVal,"transaction_id",jfiTenpayLog.getTransactionId());
            signMsgVal=appendParam(signMsgVal,"version",jfiTenpayLog.getVersion());
            signMsgVal=appendParam(signMsgVal,"key",TenpayConstants.account.get(jfiTenpayLog.getBargainorId()).get("key"));
            
            String mysign = Md5Encrypt.md5(signMsgVal).toUpperCase();
            
            if (mysign.equals(jfiTenpayLog.getSign())){
            	if (jfiTenpayLog.getPayResult().equals("0")) {
            		JfiTenpayLog jfiTenpayLogTmp = new JfiTenpayLog();
            		jfiTenpayLogTmp.setInc("1");
            		jfiTenpayLogTmp.setTransactionId(jfiTenpayLog.getTransactionId());
            		if(jfiTenpayLogManager.getJfiTenpayLogs(jfiTenpayLogTmp).size()>0){
            			jfiTenpayLog.setReturnMsg("4");
            		}else{
            			jfiTenpayLog.setReturnMsg("10");
            		}
            	}else{
            		jfiTenpayLog.setReturnMsg("3");
            	}
            }else{
            	jfiTenpayLog.setReturnMsg("2");
            }
            jfiTenpayLogManager.saveJfiTenpayLog(jfiTenpayLog);
            return jfiTenpayLog;
        }else{
        	//responseTxt不为true
        	jfiTenpayLog.setReturnMsg("1");
        	jfiTenpayLogManager.saveJfiTenpayLog(jfiTenpayLog);
            return jfiTenpayLog;
        }
	}

	/**
	 * 生成初始Jfi99billLog
	 */
	private JfiTenpayLog setJfiTenpayLog(HttpServletRequest request,String userCode,String companyCode){
		JfiTenpayLog jfiTenpayLog = new JfiTenpayLog();
		jfiTenpayLog.setAttach(request.getParameter("attach"));
		jfiTenpayLog.setBargainorId(request.getParameter("bargainor_id"));
		jfiTenpayLog.setBusArgs(request.getParameter("bus_args"));
		jfiTenpayLog.setBusType(request.getParameter("bus_type"));
		jfiTenpayLog.setCmdno(request.getParameter("cmdno"));
		jfiTenpayLog.setTpDate(request.getParameter("date"));
		jfiTenpayLog.setFeeType(request.getParameter("fee_type"));
		jfiTenpayLog.setPayInfo(request.getParameter("pay_info"));
		jfiTenpayLog.setPayResult(request.getParameter("pay_result"));
		jfiTenpayLog.setSpBillno(request.getParameter("sp_billno"));
		jfiTenpayLog.setTotalFee(request.getParameter("total_fee"));
		jfiTenpayLog.setTransactionId(request.getParameter("transaction_id"));
		jfiTenpayLog.setVersion(request.getParameter("version"));
		jfiTenpayLog.setSign(request.getParameter("sign"));
		jfiTenpayLog.setCompanyCode(companyCode);
		jfiTenpayLog.setCreateTime(new Date());
		jfiTenpayLog.setInc("0");
		jfiTenpayLog.setUserCode(userCode);
		jfiTenpayLog.setReferer(request.getHeader("referer"));
		jfiTenpayLog.setUrl(request.getRequestURL().toString() + "?" + request.getQueryString());
		jfiTenpayLog.setIp(request.getRemoteAddr());
		jfiTenpayLog.setPurchaserId(request.getParameter("purchaser_id"));
		jfiTenpayLogManager.saveJfiTenpayLog(jfiTenpayLog);
        return jfiTenpayLog;
	}

	/**
	 * 生成Tenpay对像
	 * flag(0:为电子存折  1:订单)
	 */
	public Tenpay getTenpay(Tenpay tenpay, int flag) {
		tenpay.setBus_type(TenpayConstants.bus_type);
		tenpay.setCmdno(TenpayConstants.cmdno);
		tenpay.setDate(this.getCurrTime().substring(0, 8));
		tenpay.setDesc(TenpayConstants.desc);
		tenpay.setFee_type(TenpayConstants.fee_type);
		//tenpay.setPurchaser_id(purchaser_id);
		tenpay.setReturn_url(TenpayConstants.return_url);
		tenpay.setVersion(TenpayConstants.version);
		BigDecimal total_fee = new BigDecimal(tenpay.getTotal_fee()).multiply(new BigDecimal(100));
		DecimalFormat decimalFormat = new DecimalFormat("#0");
		tenpay.setTotal_fee(decimalFormat.format(total_fee));
		String transaction_id = "";
		String province = "";
		if(flag==0){
			String sp_billno = sysIdManager.buildIdStr("advicecode_cn");
			tenpay.setSp_billno(sp_billno);
			transaction_id = sp_billno.substring(2, 10) + "0000" + sp_billno.substring(10, 16);
			JmiMember jmiMember = jmiMemberManager.getJmiMember(tenpay.getAttach());
			if(jmiMember.getProvince()==null){
				province = "null";
			}else{
				province = jmiMember.getProvince();
			}
		}else{
			JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(tenpay.getSp_billno());
			transaction_id = jpoMemberOrder.getMemberOrderNo().substring(2, 10) + "1000" + jpoMemberOrder.getMemberOrderNo().substring(10, 16);
			province = jpoMemberOrder.getProvince();
		}
		tenpay.setProvince(province);
		Map account = TenpayConstants.account.get(province);
		String bargainor_id = account.get("bargainor_id").toString();
		tenpay.setBargainor_id(bargainor_id);
		String bus_args = account.get("bus_args").toString();
		//分账实现
		bus_args = bus_args.replaceAll("M1", decimalFormat.format(total_fee.multiply(new BigDecimal("0.35"))));
		bus_args = bus_args.replaceAll("R1", "1");
		bus_args = bus_args.replaceAll("M2", decimalFormat.format(total_fee.subtract(new BigDecimal(decimalFormat.format(total_fee.multiply(new BigDecimal("0.35")))))));
		bus_args = bus_args.replaceAll("R2", "4");
		//================
		tenpay.setBus_args(bus_args);
		tenpay.setBus_desc(account.get("bus_desc").toString());
		tenpay.setTransaction_id(bargainor_id + transaction_id);
		String urlMsg = "";
		urlMsg = getSignMsg(tenpay,false);
		String posUrl = "";
		posUrl = TenpayConstants.postUrl + "?" + getSignMsg(tenpay,true);
		tenpay.setSign(Md5Encrypt.md5(appendParam(urlMsg,"key",TenpayConstants.account.get(tenpay.getProvince()).get("key"))).toUpperCase());
		tenpay.setPostUrl(posUrl+"&sign="+tenpay.getSign());
		return tenpay;
	}

	public void setJfiTenpayLogManager(JfiTenpayLogManager jfiTenpayLogManager) {
		this.jfiTenpayLogManager = jfiTenpayLogManager;
	}

	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}
	
	/**
	 * 生成签名
	 * @param bill99
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private String getSignMsg(Tenpay tenpay,boolean isUrlEncode){
		String signMsgVal="";
		if(isUrlEncode){
			try {
				signMsgVal=appendParam(signMsgVal,"attach",URLEncoder.encode(tenpay.getAttach(),"gbk"));
				signMsgVal=appendParam(signMsgVal,"bank_type",URLEncoder.encode(tenpay.getBank_type(),"gbk"));
				signMsgVal=appendParam(signMsgVal,"bargainor_id",URLEncoder.encode(tenpay.getBargainor_id(),"gbk"));
				signMsgVal=appendParam(signMsgVal,"bus_args",URLEncoder.encode(tenpay.getBus_args(),"gbk"));
				signMsgVal=appendParam(signMsgVal,"bus_desc",URLEncoder.encode(tenpay.getBus_desc(),"gbk"));
				signMsgVal=appendParam(signMsgVal,"bus_type",URLEncoder.encode(TenpayConstants.bus_type,"gbk"));
				signMsgVal=appendParam(signMsgVal,"cmdno",URLEncoder.encode(TenpayConstants.cmdno,"gbk"));
				signMsgVal=appendParam(signMsgVal,"date",URLEncoder.encode(tenpay.getDate(),"gbk"));
				signMsgVal=appendParam(signMsgVal,"desc",URLEncoder.encode(tenpay.getDesc(),"gbk"));
				signMsgVal=appendParam(signMsgVal,"fee_type",URLEncoder.encode(TenpayConstants.fee_type,"gbk"));
				//signMsgVal=appendParam(signMsgVal,"purchaser_id",URLEncoder.encode(tenpay.getPurchaser_id(),"gbk"));
				signMsgVal=appendParam(signMsgVal,"return_url",URLEncoder.encode(TenpayConstants.return_url,"gbk"));
				signMsgVal=appendParam(signMsgVal,"sp_billno",URLEncoder.encode(tenpay.getSp_billno(),"gbk"));
				signMsgVal=appendParam(signMsgVal,"spbill_create_ip",URLEncoder.encode(tenpay.getSpbill_create_ip(),"gbk"));
				signMsgVal=appendParam(signMsgVal,"total_fee",URLEncoder.encode(tenpay.getTotal_fee(),"gbk"));
				signMsgVal=appendParam(signMsgVal,"transaction_id",URLEncoder.encode(tenpay.getTransaction_id(),"gbk"));
				signMsgVal=appendParam(signMsgVal,"version",URLEncoder.encode(TenpayConstants.version,"gbk"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			signMsgVal=appendParam(signMsgVal,"attach",tenpay.getAttach());
			signMsgVal=appendParam(signMsgVal,"bank_type",tenpay.getBank_type());
			signMsgVal=appendParam(signMsgVal,"bargainor_id",tenpay.getBargainor_id());
			signMsgVal=appendParam(signMsgVal,"bus_args",tenpay.getBus_args());
			signMsgVal=appendParam(signMsgVal,"bus_desc",tenpay.getBus_desc());
			signMsgVal=appendParam(signMsgVal,"bus_type",TenpayConstants.bus_type);
			signMsgVal=appendParam(signMsgVal,"cmdno",TenpayConstants.cmdno);
			signMsgVal=appendParam(signMsgVal,"date",tenpay.getDate());
			signMsgVal=appendParam(signMsgVal,"desc",tenpay.getDesc());
			signMsgVal=appendParam(signMsgVal,"fee_type",TenpayConstants.fee_type);
			//signMsgVal=appendParam(signMsgVal,"purchaser_id",tenpay.getPurchaser_id());
			signMsgVal=appendParam(signMsgVal,"return_url",TenpayConstants.return_url);
			signMsgVal=appendParam(signMsgVal,"sp_billno",tenpay.getSp_billno());
			signMsgVal=appendParam(signMsgVal,"spbill_create_ip",tenpay.getSpbill_create_ip());
			signMsgVal=appendParam(signMsgVal,"total_fee",tenpay.getTotal_fee());
			signMsgVal=appendParam(signMsgVal,"transaction_id",tenpay.getTransaction_id());
			signMsgVal=appendParam(signMsgVal,"version",TenpayConstants.version);
			
			
			
/*			try {
				signMsgVal=appendParam(signMsgVal,"attach",URLEncoder.encode(tenpay.getAttach(),"gbk"));
				signMsgVal=appendParam(signMsgVal,"bank_type",URLEncoder.encode(tenpay.getBank_type(),"gbk"));
				signMsgVal=appendParam(signMsgVal,"bargainor_id",URLEncoder.encode(tenpay.getBargainor_id(),"gbk"));
				signMsgVal=appendParam(signMsgVal,"bus_args",URLEncoder.encode(tenpay.getBus_args(),"gbk"));
				signMsgVal=appendParam(signMsgVal,"bus_desc",URLEncoder.encode(tenpay.getBus_desc(),"gbk"));
				signMsgVal=appendParam(signMsgVal,"bus_type",URLEncoder.encode(TenpayConstants.bus_type,"gbk"));
				signMsgVal=appendParam(signMsgVal,"cmdno",URLEncoder.encode(TenpayConstants.cmdno,"gbk"));
				signMsgVal=appendParam(signMsgVal,"date",URLEncoder.encode(tenpay.getDate(),"gbk"));
				signMsgVal=appendParam(signMsgVal,"desc",URLEncoder.encode(tenpay.getDesc(),"gbk"));
				signMsgVal=appendParam(signMsgVal,"fee_type",URLEncoder.encode(TenpayConstants.fee_type,"gbk"));
				//signMsgVal=appendParam(signMsgVal,"purchaser_id",URLEncoder.encode(tenpay.getPurchaser_id(),"gbk"));
				signMsgVal=appendParam(signMsgVal,"return_url",URLEncoder.encode(TenpayConstants.return_url,"gbk"));
				signMsgVal=appendParam(signMsgVal,"sp_billno",URLEncoder.encode(tenpay.getSp_billno(),"gbk"));
				signMsgVal=appendParam(signMsgVal,"spbill_create_ip",URLEncoder.encode(tenpay.getSpbill_create_ip(),"gbk"));
				signMsgVal=appendParam(signMsgVal,"total_fee",URLEncoder.encode(tenpay.getTotal_fee(),"gbk"));
				signMsgVal=appendParam(signMsgVal,"transaction_id",URLEncoder.encode(tenpay.getTransaction_id(),"gbk"));
				signMsgVal=appendParam(signMsgVal,"version",URLEncoder.encode(TenpayConstants.version,"gbk"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		return signMsgVal;
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
	
	/**
	 * 获取当前时间 yyyyMMddHHmmss
	 * @return String
	 */ 
	public String getCurrTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}

	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

}
