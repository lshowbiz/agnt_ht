package com.joymain.jecs.util.sypay;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibm.icu.text.SimpleDateFormat;
import com.joymain.jecs.fi.model.JfiPayLog;
import com.joymain.jecs.fi.service.JfiPayLogManager;
import com.joymain.jecs.util.web.RequestUtil;

/**
 * 顺手付支付
 * 
 * @author lizg date:2015-10-23
 */
public class SypayUtilImpl implements IPayUtil {
	private final Log log = LogFactory.getLog(SypayUtilImpl.class);
	
	private JfiPayLogManager jfiPayLogManager;

    public void setJfiPayLogManager(JfiPayLogManager jfiPayLogManager) {
        this.jfiPayLogManager = jfiPayLogManager;
    }

	@Override
	public String[] getConfiguration() {
		// 支付平台中文名称,金额类型,打印控制台的表示关闭的
		return new String[] { "顺手付", "160", "success" };
	}

	@Override
	public JfiPayLog getJfiPayLog(HttpServletRequest request, String companyCode) {
		JfiPayLog entity = this.setJfiPayLog(request, companyCode);
		if (StringUtils.isEmpty(entity.getUserCode())) {
			return null;
		}
		return this.checkJfiPayLog(entity);
	}

	private JfiPayLog setJfiPayLog(HttpServletRequest request, String companyCode) {
		JfiPayLog entity = new JfiPayLog();
		try {
			// request.setCharacterEncoding("gbk");
			String reserved = request.getParameter("reserved");// 商户附加信息中存储【用户编码，支付方式】
			String[] ext = new String[2];
			if (StringUtils.isNotEmpty(reserved)) {
				ext = reserved.substring(reserved.indexOf("[") + 1, reserved.indexOf("]")).split(",");
			}
			entity.setInc("0");
			entity.setCompanyCode(companyCode);
			entity.setUrl(request.getRequestURL().toString() + "?" + RequestUtil.paramStr(request));
			entity.setUrlRemark("{serviceVersion:'接口版本',charset:'字符编码',signType:'签名方式',sign:'签名',"
					+ "merchantId:'商户代码',orderId:'商户订单号',amount:'订单金额',ccy:'金额币种',orderBeginTime:'订单发起时间',"
					+ "orderExpireTime:'订单失效时间',goodsName:'商品名称',goodsUrl:'商品URL',goodsDesc:'商品描述',"
					+ "reserved:'商户附加信息',merBusinessType:'商户业务类型',returnUrl:'返回URL',notifyUrl:'通知URL',"
					+ "connBankCode:'直连银行编码',channelType:'渠道类型',sfpOrderId:'顺银订单号',bankOrderId:'银行流水号',"
					+ "status:'交易状态',merFee:'商家手续费',notifyType:'通知类型',returnCode:'返回码',returnMsg:'返回信息'}");
			entity.setIp(RequestUtil.getIpAddr(request));
			entity.setDataType("1");
			entity.setRemark(request.getParameter("goodsDesc"));
			entity.setPayResult(request.getParameter("status"));
			entity.setErrCode(request.getParameter("returnCode"));
			entity.setErrMsg(request.getParameter("returnMsg"));
			entity.setExpireTime(request.getParameter("orderExpireTime"));
			entity.setOrderTime(request.getParameter("orderBeginTime"));
			BigDecimal payAmount = new BigDecimal(request.getParameter("amount"));
			entity.setOrderAmount(payAmount.divide(new BigDecimal(100))+"");
			entity.setOrderDate(request.getParameter("orderBeginTime"));
			entity.setOrderId(request.getParameter("orderId"));
			entity.setAmtType(request.getParameter("ccy"));
			entity.setBankDealId(request.getParameter("bankOrderId"));
			entity.setBankId(request.getParameter("connBankCode"));
			entity.setMerchantId(request.getParameter("merchantId"));// 商户代码
			entity.setMerkey(getMerKey(request.getParameter("merchantId")));// 秘药
			entity.setSign(request.getParameter("sign"));
			entity.setSignType(request.getParameter("signType"));
			entity.setVersion(request.getParameter("serviceVersion"));
			entity.setUserCode(ext[0]);
			entity.setFlag(ext[1]);
			entity.setDealId(request.getParameter("sfpOrderId"));
			entity.setPayType("7");
			entity.setCompanyCode(companyCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	private JfiPayLog checkJfiPayLog(JfiPayLog entity) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		StringBuffer meta = new StringBuffer();// 签名串
		meta.append("merchantId=").append(entity.getMerchantId()).append("&").append("orderId=").append(entity.getOrderId()).append("&").append("amount=")
				.append(entity.getOrderAmount()).append("&").append("ccy=").append(entity.getAmtType()).append("&").append("orderBeginTime=").append(entity.getOrderTime())
				.append("&").append("sfpOrderId=").append(entity.getDealId()).append("&").append("status=").append(entity.getPayResult());
		System.out.println("---------" + meta);

		int rtnMsg = 0;
		try {
			if ("SUCCESS".equals(entity.getPayResult())) {
				String signStr = SignatureUtil.sign(meta, entity.getMerkey()); // 加密
				if (!signStr.equals(entity.getSign())) {
					rtnMsg = 4;// 签名被篡改
				} else {
					JfiPayLog payLog = new JfiPayLog();
					payLog.setDealId(entity.getDealId());
					payLog.setOrderId(entity.getOrderId());
					payLog.setInc("1");
					if (jfiPayLogManager.getJfiPayLogs(payLog).size() > 0) {
						rtnMsg = 3;// 重发信息
					} else {
						rtnMsg = 10;// 验签成功
					}
				}
			} else {
				rtnMsg = 1;// 交易失败
			}
		} catch (Exception e) {
			log.info("==============交易失败=================" + e);
			rtnMsg = 1;// 交易失败
			e.printStackTrace();
		}
		entity.setReturnMsg(String.valueOf(rtnMsg));
		entity.setCreateTime(format.format(new Date()));
		entity.setDataType("1");// 数据来源，1：PC
		jfiPayLogManager.saveJfiPayLog(entity);
		return entity;
	}

	private String getMerKey(String merchantId) {
		if ("2000120162".equals(merchantId)) {
			return "a0db0b0f0e0c43ad1cd99231ffc3c860";
		}
		if ("2000144539".equals(merchantId)) {
			return "6e0b41f596a5bb8672c6427643cc1dda";//成都真合谛商贸有限公司
		}
		return "";
	}

	public static void main(String[] args) {
		
		/*String path = "G:\\pay.txt";
		InputStreamReader read;
		try {
			read = new InputStreamReader(new FileInputStream(new File(path)), "GBK");
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = bufferedReader.readLine();
			// Pattern p = Pattern.compile("\"\"");
			Pattern pattern = Pattern.compile("(?<=\\\")(.+?)(?=\\\")");
			while ((lineTxt = bufferedReader.readLine()) != null) {
				Matcher matcher = pattern.matcher(lineTxt);
				while (matcher.find()) {
					System.out.print(matcher.group() + ":'" + lineTxt.substring(lineTxt.indexOf("//") + 2) + "',");
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
*/
	}
}
