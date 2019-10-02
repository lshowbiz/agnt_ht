package com.joymain.jecs.util.reapal;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.fi.model.JfiReapalLog;
import com.joymain.jecs.fi.service.JfiReapalLogManager;
import com.joymain.jecs.util.yeepay.YeePayUtilImpl;

/**
 * 融宝支付
 * @author WuCF
 * date:2015-09-23
 */
public class ReapalUtilImpl implements ReapalUtil {
	private final Log log = LogFactory.getLog(ReapalUtilImpl.class);
	
	String formatString(String text) {
		return (text == null) ? "" : text.trim();
	}
	
	private JfiReapalLogManager jfiReapalLogManager;
	
	public void setJfiReapalLogManager(JfiReapalLogManager jfiReapalLogManager) {
		this.jfiReapalLogManager = jfiReapalLogManager;
	}

	/**
	 * 
	 */
	@Override
	public JfiReapalLog getJfiReapalLog(HttpServletRequest request) {
		log.info("-----融宝支付返回链接："+request.getRequestURL().toString() + "?" + request.getQueryString());
		
		//检查数据安全性
		boolean check = this.checkJfiReapalLog(request);
		
		log.info("-----check:"+check);
		
		JfiReapalLog jfiReapalLog = this.setJfiReapalLog(request);
		log.info("-----jfiReapalLog："+jfiReapalLog);
		if(check){
			//判断流水号是否重复
			if(jfiReapalLogManager.getJfiReapalLogsByDealId(jfiReapalLog.getDetailId()).size()>0){
				jfiReapalLog.setReturnMsg("3");//重发数据
			}else{
				jfiReapalLog.setReturnMsg("10");//验签成功
			}
		}else{
			jfiReapalLog.setReturnMsg("4");//验签失败
		}
		log.info("=========jfiReapalLog.returnMsg:"+jfiReapalLog.getReturnMsg());
		jfiReapalLog.setCreateTime(new Date());
		jfiReapalLogManager.saveJfiReapalLog(jfiReapalLog);
		
		return jfiReapalLog;
		
	}
	
	/**
	 * 验证融宝传递过来的数据安全性，并返回结果
	 * @param request
	 * @return
	 */
	public boolean checkJfiReapalLog(HttpServletRequest request){
		boolean checkResult = false; 
		try{
			String key = RongpayConfig.key;
			request.setCharacterEncoding("GBK");
			System.out.println("收到信息==============="+request.getParameter("order_no"));
	
			//获取融宝支付POST过来反馈信息
			Map params = RongpayFunction.transformRequestMap(request.getParameterMap());
	
			//判断responsetTxt是否为ture，生成的签名结果mysign与获得的签名结果sign是否一致
			//responsetTxt的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
			//mysign与sign不等，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
			String mysign = RongpayFunction.BuildMysign(params,key);
	
			String responseTxt = RongpayFunction.Verify(request.getParameter("notify_id"));
			String sign = request.getParameter("sign");
	
			//获取融宝支付的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)
			String trade_no = request.getParameter("trade_no");				//融宝支付交易号
			String order_no = request.getParameter("order_no");	        //获取订单号
			String total_fee = request.getParameter("total_fee");	        	//获取总金额
			String title=request.getParameter("title");					//商品名称、订单名称
			if(title!=null && !"".equals(title)){
				title = new String(title.getBytes("ISO-8859-1"),"GBK");
			}
	
			String body = request.getParameter("body");//商品描述、订单备注、描述
			if(body != null && !"".equals(body)){
				body = new String(body.getBytes("ISO-8859-1"), "GBK");
			}
	
			String trade_status = request.getParameter("trade_status");		//交易状态
	
	
			//建议校验responseTxt，判读该返回结果是否由融宝发出
			if(mysign.equals(sign) && responseTxt.equals("true")){
	
				//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				if(trade_status.equals("TRADE_FINISHED")){
	
					//支付成功，如果没有做过处理，根据订单号（out_trade_no）及金额（total_fee）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//一定要做金额判断，防止恶意窜改金额，只支付了小金额的订单。
					//如果有做过处理，不执行商户的业务程序
					//out.print("success");	//融宝支付后台程序获取此标记后，不再发送通知，请不要修改或删除
					checkResult = true;
				}
				else {
					//支付失败处理相关订单
					//out.print("success");	//融宝支付后台程序获取此标记后，不再发送通知，请不要修改或删除
					checkResult = true; 
				}
				//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
	
			}else{//验证失败
				//out.print("fail");
				checkResult = false;
			}
		}catch(Exception e){
			checkResult = false;
			e.printStackTrace();
		}
		
		return checkResult;
	}

	
	/**
	 * 记录数据导入流水表
	 * @param request
	 * @return
	 */
	public JfiReapalLog setJfiReapalLog(HttpServletRequest request){
		JfiReapalLog jfiReapalLog = new JfiReapalLog();
	
		try{		
			String key = RongpayConfig.key;
			jfiReapalLog.setCompanyCode("CN");//1.中国区
			request.setCharacterEncoding("GBK");
			System.out.println("收到信息==============="+request.getParameter("order_no"));
			log.info("收到信息==============="+request.getParameter("order_no"));
			//建议校验responseTxt，判读该返回结果是否由融宝发出
			String sign = request.getParameter("sign");
			jfiReapalLog.setSignMsg(sign);//2.签名字符串
			
			//获取融宝支付的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)
			String trade_no = request.getParameter("trade_no");//融宝支付交易号
			String order_no = request.getParameter("order_no");//获取订单号
			String total_fee = request.getParameter("total_fee");//获取总金额
			String title=request.getParameter("title");//商品名称、订单名称
			jfiReapalLog.setDetailId(trade_no);//3.融宝支付交易号
			jfiReapalLog.setOrderId(order_no);//4.订单号
			jfiReapalLog.setAmount(total_fee);//5.获取总金额
			if(title!=null && !"".equals(title)){
				title = new String(title.getBytes("ISO-8859-1"),"GBK");
			}
	
			String body = request.getParameter("body");//商品描述、订单备注、描述
			if(body != null && !"".equals(body)){
				body = new String(body.getBytes("ISO-8859-1"), "GBK");
			}
			jfiReapalLog.setUserCode(body);//会员编号
			String trade_status = request.getParameter("trade_status");		//交易状态
			jfiReapalLog.setPayResult("00");//==成功
		}catch(Exception e){
			e.printStackTrace();
		}
		return jfiReapalLog;
	}
}
