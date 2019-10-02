package com.joymain.jecs.util.chinapnr;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import chinapnr.SecureLink;

import com.joymain.jecs.fi.model.JfiChinapnrLog;
import com.joymain.jecs.fi.service.JfiChinapnrLogManager;
import com.joymain.jecs.util.web.RequestUtil;

public class ChinapnrUtilImpl implements ChinapnrUtil {
		
	private final Log log = LogFactory.getLog(ChinapnrUtilImpl.class);
	
	public static final String KEY_NAME = "key/PgPubk.key";//
	public static final String RS_SUCCESS = "000000";// 成功标识

	JfiChinapnrLogManager chinapnrLogManager;

	@Override
	public JfiChinapnrLog getJfiChinapnrLog(HttpServletRequest request, String userCode, String companyCode) {
		JfiChinapnrLog entity = this.setJfiChinapnrLog(request, userCode, companyCode);
		entity = this.checkJfiChinapnrLog(entity);
		return entity;
	}

	private JfiChinapnrLog setJfiChinapnrLog(HttpServletRequest request, String userCode, String companyCode) {
		JfiChinapnrLog entity = new JfiChinapnrLog();
		try {
			request.setCharacterEncoding("gbk");
			entity.setInc("0");
			entity.setCompanyCode(companyCode);
			entity.setUrl(request.getRequestURL().toString() + "?" + RequestUtil.paramStr(request));
			entity.setUserCode(userCode);
			entity.setMerchantid(request.getParameter("MerId")); // MerId商户编号
			entity.setPayorderid(request.getParameter("TrxId")); // payorderid
			entity.setMerorderid(request.getParameter("OrdId"));// merorderid订单编号
			entity.setAmountsum(request.getParameter("OrdAmt")); // amountsum订单金额
			entity.setCurrencytype(request.getParameter("CurCode")); // currencytype币种，
			entity.setSubject(request.getParameter("Pid")); // 商品种类
			entity.setMerrecvtime(request.getParameter("merrecvtime"));// merrecvtime返回到商户时间
			entity.setBanksendtime(request.getParameter("banksendtime"));// merrecvtime返回到商户时间
			entity.setReturnState(request.getParameter("RespCode"));// state状态：000000→成功相符；其它→失败；
			entity.setMac(request.getParameter("ChkValue"));// 签名信息
			entity.setSignType("KEY_NAME");// 加密类型
			entity.setPayType("汇付天下" + request.getParameter("CmdId"));
			entity.setPaybank(request.getParameter("GateId"));// paybank 支付银行
			entity.setVersion("10"); // interface版本号
			entity.setIp(request.getRemoteAddr());
			entity.setRemark(request.getParameter("MerPriv") + request.getParameter("RetType") + request.getParameter("DivDetails")); // 分账明细
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	private JfiChinapnrLog checkJfiChinapnrLog(JfiChinapnrLog entity) {
		StringBuffer sb = new StringBuffer();
		sb.append(entity.getPayType().substring(4)); // CmdId
		sb.append(entity.getMerchantid());// MerId
		sb.append(entity.getReturnState());// RespCode
		sb.append(entity.getPayorderid());// TrxId
		sb.append(entity.getAmountsum());// OrdAmt
		sb.append(entity.getCurrencytype());// CurCode
		sb.append(entity.getSubject());// Pid
		sb.append(entity.getMerorderid());// OrdId
		sb.append(entity.getRemark());// MerPriv + RetType + DivDetails
		sb.append(entity.getPaybank());// GateId
		// CmdId + MerId + RespCode + TrxId + OrdAmt + CurCode + Pid + OrdId +
		// MerPriv + RetType + DivDetails + GateId;
		int rtnMsg = 0;
		try {

			if (RS_SUCCESS.equals(entity.getReturnState())) {
				// String MerKeyFile =
				// this.getClass().getResource(KEY_NAME).getPath(); // 商户私钥文件路径
				String path = this.getClass().getClassLoader().getResource(KEY_NAME).getPath();
				log.info("================================"+path);
				SecureLink sl = new SecureLink();
				path = URLDecoder.decode(path, "utf-8");
				int ret = sl.VeriSignMsg(path, sb.toString(), entity.getMac());

				if (ret != 0) {
					entity.setSignType("KEY_NAME"+ret);
					rtnMsg = 4;// 签名被篡改
				} else {
					if (chinapnrLogManager.getChinapnrLogsByMerId(entity.getMerorderid()).size() > 0) {
						rtnMsg = 3;// 重发信息
					} else {
						rtnMsg = 10;// 验签成功
					}
				}
			} else {
				rtnMsg = 1;// 交易失败
			}
		} catch (Exception e) {
			log.info("==============交易失败================="+e);
			
			rtnMsg = 1;// 交易失败
			e.printStackTrace();
		}
		entity.setReturnMsg(String.valueOf(rtnMsg));
		entity.setCreateTime(new Date());
		entity.setDataType("1");// 数据来源，1：PC
		entity.setPaybank(entity.getPaybank() + "==" + getBankChain(entity.getPaybank()));
		chinapnrLogManager.saveJfiChinapnrLog(entity);
		return entity;
	}

	public void setChinapnrLogManager(JfiChinapnrLogManager chinapnrLogManager) {
		this.chinapnrLogManager = chinapnrLogManager;
	}

	String getBankChain(String code) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("09", "兴业");
		map.put("12", "民生");
		map.put("13", "华夏");
		map.put("15", "北京");
		map.put("16", "浦发");
		map.put("19", "广发");
		map.put("21", "交行");
		map.put("23", "招行借贷");
		map.put("25", "工商行");
		map.put("27", "建设行");
		map.put("29", "农业行");
		map.put("33", "中信");
		map.put("36", "光大");
		map.put("40", "北农商");
		map.put("45", "中行");
		map.put("46", "邮储");
		map.put("49", "南京银行");
		map.put("50", "平安银行");
		map.put("51", "杭州银行");
		map.put("53", "浙商");
		map.put("54", "上海银行");
		map.put("55", "渤海银行");
		map.put("61", "PNR钱管家");
		map.put("69", "上农商B2C");
		map.put("70", "工行B2B");
		map.put("71", "农行B2B");
		map.put("74", "光大B2B");
		map.put("75", "北农商B2B");
		map.put("76", "浦发B2B");
		map.put("78", "招行B2B");
		map.put("81", "深发B2B");
		map.put("82", "建行B2B新");
		return map.get(code);
	}
}
