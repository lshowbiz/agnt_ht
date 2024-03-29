package com.joymain.jecs.util.umbpay;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.fi.model.JfiPayLog;
import com.joymain.jecs.fi.service.JfiPayLogManager;
import com.joymain.jecs.util.sypay.IPayUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.util.yspay.PayUtils;
import com.joymain.jecs.util.yspay.RemarkBean;

public class UmbpayUtilImpl2 implements IPayUtil {
	private final Log log = LogFactory.getLog(UmbpayUtilImpl2.class);
	private JfiPayLogManager jfiPayLogManager;

	public void setJfiPayLogManager(JfiPayLogManager jfiPayLogManager) {
		this.jfiPayLogManager = jfiPayLogManager;
	}

	@Override
	public String[] getConfiguration() {
		return new String[] { "宝易互通", "120", "success=true" };
	}

	@Override
	public JfiPayLog getJfiPayLog(HttpServletRequest request, String companyCode) {
		JfiPayLog entity = this.setJfiPayLog(request, companyCode);
		if (StringUtils.isEmpty(entity.getUserCode())) {
			log.info("宝易互通" + entity.getUrl());
			return null;
		}
		return this.checkJfiPayLog(entity);
	}

	private JfiPayLog setJfiPayLog(HttpServletRequest request, String companyCode) {
		JfiPayLog entity = null;
		try {

			;// 商户附加信息中存储【用户编码，支付方式】
			String reserved = request.getParameter("remark");
			if (StringUtils.isNotEmpty(reserved)) {
				RemarkBean bean = PayUtils.getRemarkBean(reserved);
				entity = new JfiPayLog();
				entity.setUserCode(bean.getUserCode());
				entity.setFlag(bean.getPayType());
				entity.setInc("0");
				entity.setCompanyCode(companyCode);
				entity.setUrl(request.getRequestURL().toString() + "?" + RequestUtil.paramStr(request));
				log.info(entity.getUrl());
				entity.setIp(RequestUtil.getIpAddr(request));
				entity.setDataType("1");
				entity.setPayType("4"); // 银盛支付
				entity.setRemark(reserved);
				entity.setUrlRemark("{payorderid:'宝易互通参考号',MD5:' 加密类型',merchantid:' 商户编号',merorderid:' 订单编号',amountsum:' 订单金额',"
						+ "currencytype:' 币种，',subject:' 商品种类',merrecvtime:' 返回到商户时间',banksendtime:' 返回到商户时间'," + "state:' 状态：0→未付款(默认值)；1→成功相符；2→成功不符；3→失败；',"
						+ "mac:'验签码',paybank:'支付银行',interface:' 版本号',remark:'备注【用户编码，支付方式】'}");
				String merchantid = request.getParameter("merchantid");// 商户号
				entity.setMerchantId(merchantid);// 商户号
				entity.setPayResult(request.getParameter("state"));// state状态：0→未付款(默认值)；1→成功相符；2→成功不符；3→失败；
				entity.setOrderAmount(request.getParameter("amountsum"));// Amount[交易金额]
				entity.setOrderId(request.getParameter("merorderid"));// merorderid订单编号
				entity.setAmtType(request.getParameter("currencytype")); // currencytype币种，
				entity.setBankId(request.getParameter("paybank"));// 支付银行
				entity.setMerkey(getMerKey(merchantid));// mac 验签码
				entity.setSign(request.getParameter("mac"));// mac 验签码
				entity.setSignType("MD5"); // 签名方式
				entity.setVersion(request.getParameter("interface")); // interface版本号
				entity.setDealId(request.getParameter("payorderid")); // payorderid宝易互通参考号
				entity.setOrderTime(request.getParameter("merrecvtime"));
				entity.setOrderDate(request.getParameter("banksendtime"));
				entity.setBankDealId(request.getParameter("subject")); // 商品种类
																		// (本身为银行流水)
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return entity;
	}

	private JfiPayLog checkJfiPayLog(JfiPayLog entity) {
		SimpleDateFormat format = new SimpleDateFormat(datFmt);
		int rtnMsg = 0;
		// 验证签名
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("merchantid=").append(entity.getMerchantId());
			sb.append("&merorderid=").append(entity.getOrderId());
			sb.append("&amountsum=").append(entity.getOrderAmount());
			sb.append("&currencytype=").append(entity.getAmtType());
			sb.append("&subject=").append(entity.getBankDealId());
			sb.append("&state=").append(entity.getPayResult());
			sb.append("&paybank=").append(entity.getBankId());
			sb.append("&banksendtime=").append(entity.getOrderDate());
			sb.append("&merrecvtime=").append(entity.getOrderTime());
			sb.append("&interface=").append(entity.getVersion());
			sb.append("&merkey=").append(entity.getMerkey());
			if ("1".equals(entity.getPayResult())) {
				String mac = Crypto.GetMessageDigest(sb.toString());
				if (mac.equals(entity.getSign())) {
					JfiPayLog payLog = new JfiPayLog();
					payLog.setPayType(entity.getPayType());
					payLog.setOrderId(entity.getOrderId());
					payLog.setSign(entity.getSign());
					payLog.setInc("1");
					if (jfiPayLogManager.getJfiPayLogs(payLog).size() > 0) {
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

		} catch (Exception e) {
			e.printStackTrace();
			rtnMsg = 4;// 签名被篡改
		}
		entity.setReturnMsg(String.valueOf(rtnMsg));
		entity.setCreateTime(format.format(new Date()));
		entity.setDataType("1");// 数据来源，1：PC
		jfiPayLogManager.saveJfiPayLog(entity);
		return entity;
	}

	private String getMerKey(String business) {
		String merKey = "";
		if ("1194".equals(business)) {
			merKey = "Dh8S1CF7zfi";
		} else if ("1195".equals(business)) {
			merKey = "HF2Z1fH0Jt";
		} else if ("1143".equals(business)) {
			merKey = "970171e2A45M";
		} else if ("1189".equals(business)) {
			merKey = "3GvonEC50yI23";
		}
		return merKey;
	}

}
