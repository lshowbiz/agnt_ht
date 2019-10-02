package com.joymain.jecs.util.umbpay;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.fi.model.JfiUmbpayLog;
import com.joymain.jecs.fi.service.JfiUmbpayLogManager;

public class UmbpayUtilImpl implements UmbpayUtil {

	JfiUmbpayLogManager umbpayLogManager;

	/**
	 * 0表示数据被篡改 1表示扣款失败 2自定义MD5签名被篡改(签名被破解) 3支付数据重新发送 10表示成功校验
	 * 
	 * @param request
	 * @param userCode
	 * @param companyCode
	 * @return
	 */
	public JfiUmbpayLog getJfiUmbpayLog(HttpServletRequest request, String userCode, String companyCode) {
		JfiUmbpayLog jfiUmbpayLog = this.setJfiUmbpayLog(request, userCode, companyCode);
		jfiUmbpayLog = this.checkJfiUmbpayLog(jfiUmbpayLog);
		return jfiUmbpayLog;
	}

	/**
	 * jfi99billLog.getReturnMsg() 0表示数据被篡改 1表示扣款失败 2自定义MD5签名被篡改(快钱签名被破解)
	 * 3支付数据重新发送 10表示成功校验
	 */
	private JfiUmbpayLog checkJfiUmbpayLog(JfiUmbpayLog entity) {

		// 生成加密串。必须保持如下顺序。
		String merchantSignMsgVal = "";
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "merchantid", entity.getMerchantid());
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "merorderid", entity.getMerorderid());
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "amountsum", entity.getAmountsum());
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "currencytype", entity.getCurrencytype());
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "subject", entity.getSubject());
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "state", entity.getReturnState());
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "paybank", entity.getPaybank());
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "banksendtime", entity.getBanksendtime());
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "merrecvtime", entity.getMerrecvtime());
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "interface", entity.getVersion());

		if ("1194".equals(entity.getMerchantid())) {
			merchantSignMsgVal = appendParam(merchantSignMsgVal, "merkey", "Dh8S1CF7zfi");
		} else if ("1195".equals(entity.getMerchantid())) {
			merchantSignMsgVal = appendParam(merchantSignMsgVal, "merkey", "HF2Z1fH0Jt");
		} else if ("1143".equals(entity.getMerchantid())) {
			merchantSignMsgVal = appendParam(merchantSignMsgVal, "merkey", "970171e2A45M");
		}

		int rtnMsg = 0;
		if ("1".equals(entity.getReturnState())) {
			String mac = Crypto.GetMessageDigest(merchantSignMsgVal);
			if (mac.equals(entity.getMac())) {
				if (umbpayLogManager.getJfiUmbpayLogsByMerId(entity.getMerorderid()).size() > 0) {
					rtnMsg = 3;// 重发信息
				} else {
					rtnMsg = 10;// 成功校验
				}
			} else {
				rtnMsg = 4;// 验签不通过
			}
		} else {
			rtnMsg = 1;// 扣款失败
		}
		entity.setReturnMsg(String.valueOf(rtnMsg));
		entity.setCreateTime(new Date());
		entity.setDataType("1");// 数据来源，1：PC
		umbpayLogManager.saveJfiUmbpayLog(entity);
		return entity;
	}

	/**
	 * 生成初始setJfiUmbpayLog
	 */
	private JfiUmbpayLog setJfiUmbpayLog(HttpServletRequest request, String userCode, String companyCode) {

		JfiUmbpayLog umbpayLog = new JfiUmbpayLog();
		umbpayLog.setInc("0");
		umbpayLog.setCompanyCode(companyCode);
		umbpayLog.setUrl(request.getRequestURL().toString() + "?" + paramStr(request));
		umbpayLog.setUserCode(userCode);

		umbpayLog.setPayorderid(request.getParameter("payorderid")); // payorderid宝易互通参考号
		umbpayLog.setSignType("MD5");// 加密类型
		umbpayLog.setMerchantid(request.getParameter("merchantid")); // merchantid商户编号
		umbpayLog.setMerorderid(request.getParameter("merorderid"));// merorderid订单编号
		umbpayLog.setAmountsum(request.getParameter("amountsum")); // amountsum订单金额
		umbpayLog.setCurrencytype(request.getParameter("currencytype")); // currencytype币种，
		umbpayLog.setSubject(request.getParameter("subject")); // 商品种类
		umbpayLog.setMerrecvtime(request.getParameter("merrecvtime"));// merrecvtime返回到商户时间
		umbpayLog.setBanksendtime(request.getParameter("banksendtime"));// merrecvtime返回到商户时间
		umbpayLog.setReturnState(request.getParameter("state"));// state状态：0→未付款(默认值)；1→成功相符；2→成功不符；3→失败；
		umbpayLog.setMac(request.getParameter("mac"));// mac 验签码
		umbpayLog.setSignType("MD5");// 加密类型
		umbpayLog.setPayType("宝易互通");
		umbpayLog.setPaybank(request.getParameter("paybank"));// paybank 支付银行
		umbpayLog.setVersion(request.getParameter("interface")); // interface版本号
		umbpayLog.setIp(request.getRemoteAddr());
		umbpayLog.setRemark(request.getParameter("remark"));
		return umbpayLog;
	}

	// 功能函数。将变量值不为空的参数组成字符串
	private String appendParam(String returnStr, String paramId, String paramValue) {
		if (!returnStr.equals("")) {
			if (!"".equals(paramValue))
				returnStr = returnStr + "&" + paramId + "=" + paramValue;
		} else {
			if (!"".equals(paramValue))
				returnStr = paramId + "=" + paramValue;
		}
		return returnStr;
	}

	@SuppressWarnings("rawtypes")
	private String paramStr(HttpServletRequest request) {
		String returnStr = "";
		Enumeration rnames = request.getParameterNames();
		for (Enumeration e = rnames; e.hasMoreElements();) {
			String thisName = e.nextElement().toString();
			String thisValue = request.getParameter(thisName);
			returnStr += (thisName + "=" + thisValue + "&");
		}
		return returnStr;
	}

	@Override
	public Umbpay getUmbpay(Umbpay alipay, int flag) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setUmbpayLogManager(JfiUmbpayLogManager umbpayLogManager) {
		this.umbpayLogManager = umbpayLogManager;
	}

}
