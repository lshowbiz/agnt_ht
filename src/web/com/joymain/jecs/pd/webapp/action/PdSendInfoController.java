package com.joymain.jecs.pd.webapp.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mobset.smsSDK;
import mobset.str_SendMsg;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.JfiPosImport;
import com.joymain.jecs.pd.model.PdSendInfo;
import com.joymain.jecs.pd.service.PdSendInfoDetailManager;
import com.joymain.jecs.pd.service.PdSendInfoManager;
import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.pd.service.PdWarehouseUserManager;
import com.joymain.jecs.pm.model.JpmSmssendInfo;
import com.joymain.jecs.pm.service.JpmProductSaleNewManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.webapp.util.SmsSend;

public class PdSendInfoController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(PdSendInfoController.class);
	private PdSendInfoManager pdSendInfoManager = null;
	private PdWarehouseUserManager pdWarehouseUserManager = null;
	private PdSendInfoDetailManager pdSendInfoDetailManager =null;
	private PdWarehouseStockManager pdWarehouseStockManager = null;
	private JpmProductSaleNewManager jpmProductSaleNewManager = null;
	
	public void setJpmProductSaleNewManager(
			JpmProductSaleNewManager jpmProductSaleNewManager) {
		this.jpmProductSaleNewManager = jpmProductSaleNewManager;
	}
	
	public void setPdSendInfoDetailManager(
			PdSendInfoDetailManager pdSendInfoDetailManager) {
		this.pdSendInfoDetailManager = pdSendInfoDetailManager;
	}

	public void setPdWarehouseStockManager(
			PdWarehouseStockManager pdWarehouseStockManager) {
		this.pdWarehouseStockManager = pdWarehouseStockManager;
	}

	public void setPdWarehouseUserManager(
			PdWarehouseUserManager pdWarehouseUserManager) {
		this.pdWarehouseUserManager = pdWarehouseUserManager;
	}

	public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}

	 
	
	/**
	 * 测试手机发送短信提醒功能 
	 * @param jfiPosImport
	 */
	private void sendSms(JfiPosImport jfiPosImport){
    	if(jfiPosImport==null){
    		jfiPosImport = new JfiPosImport();
    		jfiPosImport.setTel("13650710137");
    		jfiPosImport.setMessageCode("手验短信验证测试:您的货已经发出,请留意! ");
    	} 
		smsSDK sdk = new smsSDK();
		try {
			int iRet = sdk.Sms_Connect("www.mobset.com", 111043, "test", "281512", 30); // 测试时请更改企业ID,用户名,密码
			if (iRet == 0){// 登录成功
				iRet = sdk.Sms_KYSms();
				if (iRet >= 0) {
					str_SendMsg[] sendMsg = new str_SendMsg[1];
					sendMsg[0] = new str_SendMsg();
					sendMsg[0].strExNum = ""; // 扩展号码，不用扩展请留空。
					sendMsg[0].strMobile = jfiPosImport.getTel(); // 目标手机号码，测试时请更改号码。
					log.info("tel.code:"+LocaleUtil.getLocalText("tel.code"));
					log.info("zh_CN_tel.code:"+LocaleUtil.getLocalText("zh_CN","tel.code","tel.code"));
					sendMsg[0].strMsg = LocaleUtil.getLocalText("tel.code").replace("{code}", jfiPosImport.getMessageCode()); // 短信内容
					iRet = sdk.Sms_Send(sendMsg, 1);
					if (iRet > 0) {
						log.info("发送短消息成功");
					}else{
						log.info("发送短消息失败:"+iRet);
					}
				}else{
					log.info("获取短消息失败:"+iRet);
				}
			}else{
				log.info("登录失败:"+iRet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sdk.Sms_DisConnect(); // 断开与服务器的连接
			sdk = null;
		}
    }
	
	/**
	 * Add By WuCF 20150424
	 * 判断是否包含字母，数字
	 * @param number
	 * @return
	 */
	public static String isNumberOrWord(String str){
	     Pattern pattern =Pattern.compile("[a-zA-Z]|\\d");
	     Matcher matcher = pattern.matcher(str);
	     while (matcher.find()) {
	    	 // 如匹配成功即走到这里
	    	 return matcher.group();
	     }
	     return "";
	}
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/*System.out.println("===================");
		System.out.println("=="+isNumberOrWord("1"));
		System.out.println("=="+isNumberOrWord("a"));
		System.out.println("=="+isNumberOrWord("123abc"));
		System.out.println("=="+isNumberOrWord("跟踪单号123"));
		System.out.println("=="+isNumberOrWord("跟踪单号abc"));
		System.out.println("=="+isNumberOrWord("跟踪单号"));*/
//		System.out.println("=1111");
		String str = pdSendInfoManager.getPdSendInfoJsons();
//		sendSms(null);
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		CommonRecord crm = initCommonRecord(request);
		//点击第一次菜单，默认不查询，如果是N：不查询 
		String cxFlag = request.getParameter("cxFlag");
		crm.setValue("cxFlag", cxFlag);
		if(!"n".equals(cxFlag)){
			super.initPmProductMap(request);
		}
		super.initStateCodeParem(request);
		
		String mvc = "welcome";
		String strAction = request.getParameter("strAction");
		
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		String listFlag = "-1";
		Pager pager = new Pager("pdSendInfoListTable", request, 20);

		//Modify By WuCF 20140603 对于root账号不用限制仓库权限
		String warehouseNoStr = "";
		if(!"root".equals(sessionLogin.getUserCode())){
			warehouseNoStr = pdWarehouseUserManager.getStringWarehouseByUser(sessionLogin.getUserCode());
		}
		
		//新增仓库管理权限控制
    	crm.setValue("defaultWarehouse", warehouseNoStr);

		// 当不是公司用户 时 只能看到自己的单,管理中心可以看到所有公司的单
		if (sessionLogin.getIsCompany()) {
			crm.setValue("companyCode", sessionLogin.getCompanyCode());
		} else if (sessionLogin.getIsManager()) {

		} else {
			crm.setValue("customCode", sessionLogin.getUserCode());
		}
		//Modify By WuCF 20131114 仓库权限管理修改，查询时过滤
		crm.setValue("userCodeT", sessionLogin.getUserCode());
		
		//批量打印字符串的编号
		String siNoTextarea = request.getParameter("siNoTextarea");
		crm.setValue("siNoTextarea", siNoTextarea);
		
		//Modify By WUCF20150906 是否有审核人
		String hasCheckUser = request.getParameter("hasCheckUser");
		crm.setValue("hasCheckUser", hasCheckUser);
		
		if("editPdSendInfo".equals(strAction)){//编辑发货单
        	listFlag = "1";

        	mvc="pd/pdSendInfoList";
         } else if("checkPdSendInfo".equals(strAction)){//初审
	        	listFlag = "0";
//	        	crm.setValue("checkUsrCode", sessionLogin.getUserCode());
	        	crm.setValue("hasCheckUsrCodeBlank", "1");
	        	crm.setValue("orderFlagDefault","0,1,2,3,4");

	        	mvc="pd/pdSendInfoList";

	        }else if("sendPdSendInfo".equals(strAction)){//发货确认
	        	//新增仓库管理权限控制

	        	listFlag = "0";
	        	crm.setValue("orderFlagDefault","1,2,3,4"); 
	        	
	        	crm.setValue("okUsrCode", sessionLogin.getUserCode());
	        	crm.setValue("hasCheckUsrCodeBlank", "1");

	        	mvc="pd/pdSendInfoList";
	        }else if("acceptPdSendInfo".equals(strAction)){//代理商收货确认
	        	crm.setValue("orderFlagDefault","-1,0,1,2,3,4");
	        	mvc="pd/pdSendInfoList";
	        }else if("receiptPdSendInfo".equals(strAction)){//回单确认
	        	crm.setValue("orderFlagDefault","2,3,4");
	        	mvc="pd/pdSendInfoList";
	        }else if("searchPdSendInfo".equals(strAction)){//发货单查询
	        	listFlag = "0";
	        	
	        	mvc="pd/pdSendInfoList";
	        }else if("statisticPdSendInfo".equals(strAction)){
	        	if(!"n".equals(cxFlag)){	
		        	List pdSendInfoTotal = pdSendInfoDetailManager.getTotalPdSendInfoDetails(crm);
		        	request.setAttribute("pdSendInfoTotal", pdSendInfoTotal);
	        	}
	        	mvc="pd/pdSendInfoList";
	        }else if("batchConfirmShipOrder".equals(strAction)){
	        	//Modify By WuCF 20140304 发货单批量确认注释！查询数据量太大，而且没有实际用处！
//	        	crm.setValue("isPickup", "0");
//	        	crm.setValue("withoutAgent", "1");
	        	
	        	crm.setValue("orderFlag", "1");
	        	
//	        	crm.setValue("orderFlagDefault","1,2,3,4"); 
	        	
//	        	crm.setValue("okUsrCode", sessionLogin.getUserCode());
//	        	crm.setValue("hasCheckUsrCodeBlank", "1");

	        	
	        	List pdSendInfoList = new ArrayList();
	        	String confirmFlag = request.getParameter("confirmFlag");
	        	if("search".equals(confirmFlag)){
	        		pdSendInfoList = pdSendInfoManager.getPdSendInfosByCrm(crm, null);
	        	}
	        	 
	        	String siNoList = this.getSiNoByAll(pdSendInfoList);
	        		
	        		
	        	
	        	
	        	request.setAttribute("pdSendInfoList", pdSendInfoList);
	        	
	        	if("batchConfirm".equals(confirmFlag)){
	        		log.info("batchConfirm");
	        		siNoList=request.getParameter("siNoList");
	        		batchConfirmShip(siNoList,request,response);
	        		siNoList="";	
	        	}else if("batchHurry".equals(confirmFlag)){
	        		siNoList=request.getParameter("siNoList");
	        		batchHurry(siNoList,request,response);
	        		siNoList="";	
	        	}
	        	request.setAttribute("siNoList", siNoList);
	        	mvc="pd/pdBatchConfirmOrder";
	        	return new ModelAndView(mvc);
	        }else if("reupdatePdWarehouseStock".equals(strAction)){
	        	reupdatePdWarehouseStock(request,response);
	        }
		
		
		// PdSendInfo pdSendInfo = new PdSendInfo();
		// // populate object with request parameters
		// BeanUtils.populate(pdSendInfo, request.getParameterMap());

		/**** auto pagination ***/

		List pdSendInfos = pdSendInfoManager.getPdSendInfosByCrm(crm, pager);
		request.setAttribute("pdSendInfoListTable_totalRows", pager
				.getTotalObjects());
		/*****/
		
		request.setAttribute("siNoTextarea", siNoTextarea);
		return new ModelAndView(mvc, Constants.PDSENDINFO_LIST,
				pdSendInfos);
	}
	
	
	private void batchHurry(String siNoList, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
    	List retList = new ArrayList();
		if(siNoList != null){
			String[] siNos = siNoList.split(",");
			for(int i=0;i<siNos.length;i++){
				PdSendInfo pdSendInfo = pdSendInfoManager.getPdSendInfo(siNos[i]);
				try {
					
					pdSendInfo.setEditTime(new Date());
					pdSendInfo.setEditUsrCode(sessionLogin.getUserCode());
					pdSendInfo.setHurryFlag("1");
					pdSendInfoManager.savePdSendInfo(pdSendInfo);
					retList.add("<font color='green'>success:" + pdSendInfo.getSiNo()+","+pdSendInfo.getCustomer().getUserCode()+","+pdSendInfo.getOrderNo()+"</font>");
					
				} catch (Exception e) {
					// TODO: handle exception
					retList.add("<font color='red'>error:" + pdSendInfo.getSiNo()+","+pdSendInfo.getCustomer().getUserCode()+","+pdSendInfo.getOrderNo()+"</font>");
				}
			}
 		}
		request.setAttribute("errors", retList);
	}

	private void reupdatePdWarehouseStock(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String siNos = request.getParameter("siNos");
		String[] siNo = siNos.split(",");
		for(int i=0;i<siNo.length;i++){
			PdSendInfo pdSendInfo = pdSendInfoManager.getPdSendInfo(siNo[i]);
			pdWarehouseStockManager.reupdatePdWarehouseStock(pdSendInfo);
		}
		
	}

	private void batchConfirmShip(String siNoList, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
    	SysUser sessionLogin = SessionLogin.getLoginUser(request);
    	List retList = new ArrayList();
		if(siNoList != null){
			String[] siNos = siNoList.split(",");
			for(int i=0;i<siNos.length;i++){
				PdSendInfo pdSendInfo = pdSendInfoManager.getPdSendInfo(siNos[i]);
				try {
					
					pdSendInfo.setOkTime(new Date());
					pdSendInfo.setOkUsrCode(sessionLogin.getUserCode());
					pdSendInfo.setOrderFlag(2);
					pdSendInfoManager.confirmSendInfo(pdSendInfo, sessionLogin);
					retList.add("<font color='green'>success:" + pdSendInfo.getSiNo()+","+pdSendInfo.getCustomer().getUserCode()+","+pdSendInfo.getOrderNo()+"</font>");
				
				
					/*try{
						//添加短信功能：Modify By WuCF 20140113				
						if (pdSendInfo.getOrderFlag() ==1) {
							//Modify By WuCF 20140310 添加短信开关控制
							String smsSendOpenStatus = ConfigUtil.getConfigValue(sessionLogin.getCompanyCode().toUpperCase(), "smssend.open.status");
							
							//哪些仓库发货可以发送短信，在参数中配置
							String smsSendWarehouseNos = ConfigUtil.getConfigValue(sessionLogin.getCompanyCode().toUpperCase(), "smssend.warehouseno");
		//					if(smsSendWarehouseNos.contains(pdSendInfo.getWarehouseNo()) && "Y".equals(smsSendOpenStatus)){
								//Modify By WuCF 20140117 中脉系统短信发送
								String mobilePhone = pdSendInfo.getRecipientMobile();
								短信内容：亲爱的中脉家人，您的会员号CN********，发货单号LO01*************,已于*月*日发出。
								运单号：*****（系统上传的物流跟踪号），物流公司：*****（系统上传的物流公司）。
								请您查收，并注意开箱验货。 
								SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy年MM月dd日");   
								String dateStr = dateformat2.format(new Date());
								
								StringBuffer message = new StringBuffer("亲爱的中脉家人，您的会员号");
								message.append(pdSendInfo.getCustomer().getUserCode());
								message.append("，发货单号");
								message.append(pdSendInfo.getSiNo());
								message.append("，已于"+dateStr+"发出。");
								if(StringUtils.isNotEmpty(pdSendInfo.getTrackingNo())){
									message.append("运单号：");
									message.append(pdSendInfo.getTrackingNo());
									message.append("，");
								}
								message.append("物流公司：");
								message.append(ListUtil.getListValue(sessionLogin.getCompanyCode().toUpperCase(), "pd.shno", pdSendInfo.getShNo()));
								message.append("。请您查收，并注意开箱验货。");
								log.info("发货短信message:"+message);
								String url1 = ListUtil.getListValue(sessionLogin.getCompanyCode().toUpperCase(), "smssend.url", "1");
								String url2 = ListUtil.getListValue(sessionLogin.getCompanyCode().toUpperCase(), "smssend.url", "2");
			//					String message = "尊敬的家人：龚伟，恭喜您！您中得“中脉道和”幕后抽奖活动，奖品是：中脉床垫！，请带着您的身份证到中脉国际20楼前台领取！";
								SmsSend.sendSms(url1,url2,mobilePhone, message.toString());
		//					}
							
							//将短信写入到数据库表
							JpmSmssendInfo jpmSmssendInfo = new JpmSmssendInfo();
							jpmSmssendInfo.setSmsType("1");////短信类型  例如：1：仓库发货确认    2：找回密码   3：电影票  目前只有三种，在列表中配置
							jpmSmssendInfo.setSmsRecipient(pdSendInfo.getCustomer().getUserCode());//短信接收人:用户会员编号
							jpmSmssendInfo.setSmsMessage(message.toString());//短信内容
							jpmSmssendInfo.setSmsTime(new Date());//发送时间
							jpmSmssendInfo.setSmsOperator(sessionLogin.getUserCode());//操作人
							jpmSmssendInfo.setSmsStatus("1");//保留字段，是否发送成功！ 默认为1：成功！ 现在短信还不能知道是否发送成功，没有返回值！
							jpmSmssendInfo.setRemark("");//备注
							jpmSmssendInfo.setPhoneNum(mobilePhone);//手机号码
							jpmProductSaleNewManager.saveJpmSmssendInfo(jpmSmssendInfo);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
					
					//发货确认
					pdSendInfoManager.confirmSendInfo(pdSendInfo, sessionLogin);*/
				} catch (Exception e) {
					// TODO: handle exception
					retList.add("<font color='red'>error:" + pdSendInfo.getSiNo()+","+pdSendInfo.getCustomer().getUserCode()+","+pdSendInfo.getOrderNo()+"</font>");
				}
			}
 		}
		request.setAttribute("errors", retList);
	}

	private String  getSiNoByAll(List pdSendInfoList){
    	StringBuffer sb = new StringBuffer();
    	if(pdSendInfoList != null){
    		for(int i=0;i<pdSendInfoList.size();i++){
        		if(i==0){
        			sb.append(((PdSendInfo)pdSendInfoList.get(i)).getSiNo());
        		}else{
        			sb.append(","+((PdSendInfo)pdSendInfoList.get(i)).getSiNo());
        		}
        		
        	}
    	}
    	
    	return sb.toString();
    }
}
