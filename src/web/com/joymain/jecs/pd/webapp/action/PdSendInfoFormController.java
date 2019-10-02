 package com.joymain.jecs.pd.webapp.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mobset.smsSDK;
import mobset.str_SendMsg;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.JfiPosImport;
import com.joymain.jecs.pd.model.PdSendInfo;
import com.joymain.jecs.pd.model.PdSendInfoDetail;
import com.joymain.jecs.pd.service.PdSendInfoDetailManager;
import com.joymain.jecs.pd.service.PdSendInfoManager;
import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.pm.model.JpmSmssendInfo;
import com.joymain.jecs.pm.service.JpmProductSaleNewManager;
import com.joymain.jecs.service.PdLogisticsService;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.webapp.util.SmsSend;

public class PdSendInfoFormController extends BaseFormController {
	private PdSendInfoManager pdSendInfoManager = null;
	private PdSendInfoDetailManager pdSendInfoDetailManager = null;
	private SysUserManager sysUserManager = null;
	private PdWarehouseStockManager pdWarehouseStockManager = null;
	private SysIdManager sysIdManager = null;
	private PdLogisticsService starsExpressService = null;
	private JpmProductSaleNewManager jpmProductSaleNewManager = null;
	
	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}

	public void setStarsExpressService(PdLogisticsService starsExpressService) {
		this.starsExpressService = starsExpressService;
	}

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public void setPdSendInfoDetailManager(
			PdSendInfoDetailManager pdSendInfoDetailManager) {
		this.pdSendInfoDetailManager = pdSendInfoDetailManager;
	}

	public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}

	public void setJpmProductSaleNewManager(
			JpmProductSaleNewManager jpmProductSaleNewManager) {
		this.jpmProductSaleNewManager = jpmProductSaleNewManager;
	}

	public PdSendInfoFormController() {
		setCommandName("pdSendInfo");
		setCommandClass(PdSendInfo.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		String defultWarehouse = Constants.sysConfigMap.get(
				sessionLogin.getCompanyCode()).get("pd.autoship.warehouseno");
		String siNo = request.getParameter("siNo");
		PdSendInfo pdSendInfo = null;
		// 控制
		String strAction = request.getParameter("strAction");
		String customerCode = request.getParameter("customerCode");


		Set pdSendInfoListDetails = null;
		// CommonRecord crm = initCommonRecord(request);//����ѯ���д��session
		// crm.setValue("siNo", siNo);
		// Pager pager = new Pager("pdSendInfoListTable",request, 20);
		// List pdSendInfoListDetails = new ArrayList();

		if (!StringUtils.isEmpty(siNo)) {
			pdSendInfo = pdSendInfoManager.getPdSendInfo(siNo);
			pdSendInfoListDetails = pdSendInfo.getPdSendInfoDetails();
			//modify fu 20150825------发货单的发货方式记录，用来判别发货方式是否发生变化，如果发货方式发生变化，那么向后续系统推送接口数据
			request.setAttribute("interfaceShipType", pdSendInfo.getShipType());
		} else {
			pdSendInfo = new PdSendInfo();

		}

		boolean disabled = true;// 可编辑部分是否有权限编辑
		String checkButtonKey = "button.submit";// 按钮显示的文字
		String checkFlag = "-1";
		if ("checkPdSendInfo".equals(strAction)) {
			checkFlag = "1";// 审核
			checkButtonKey = "button.checked";
			// disabled = true;
		} else if ("sendPdSendInfo".equals(strAction)) {
			checkFlag = "2";// 发货确认
			checkButtonKey = "pdSendInfo.sendPdSendInfo";
			// disabled = true;
		} else if ("editPdSendInfo".equals(strAction)) {
			checkFlag = "0";// 修改
			checkButtonKey = "button.submit";

			if (pdSendInfo.getOrderFlag() < 0) {// 已经初审的入库单不能编辑
				disabled = false;
			}
		} else if ("addPdSendInfo".equals(strAction)) {// 支持会员发货 暂时不支持手动制单，此功能不用
			checkFlag = "-1";// 新增
			checkButtonKey = "button.submit";

			if (StringUtils.isNotEmpty(customerCode)) {
				SysUser customer = sysUserManager.getSysUser(customerCode);
				pdSendInfo.setCustomer(customer);

			}
			disabled = false;
		} else if ("acceptPdSendInfo".equals(strAction)) {// 收货确认
			checkFlag = "3";//
			checkButtonKey = "pdSendInfo.acceptPdSendInfo";
			// disabled = true;
		} else if ("receiptPdSendInfo".equals(strAction)) {// 回单确认
			checkFlag = "4";//
			checkButtonKey = "pdSendInfo.receiptPdSendInfo";
			// disabled = true;
		}

		request.setAttribute("siNo", siNo);
		request.setAttribute("disabledFlag", disabled);
		request.setAttribute("checkButtonKey", checkButtonKey);
		request.setAttribute("checkFlag", checkFlag);
		log.debug("disabledFlag=" + disabled);
		request.setAttribute(Constants.PDSENDINFODETAIL_LIST,
				pdSendInfoListDetails);
		
		
		super.initPmProductMap(request);
		super.initStateCodeParem(request);

		return pdSendInfo;

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
	public String isNumberOrWord(String str){
	     Pattern pattern =Pattern.compile("[a-zA-Z]|\\d");
	     if(!StringUtil.isEmpty(str)){
		     Matcher matcher = pattern.matcher(str);
		     while (matcher.find()) {
		    	 // 如匹配成功即走到这里
		    	 return matcher.group();
		     }
	     }
	     return "";
	}
	
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		String strAction = request.getParameter("strAction");
		
		//modify fu 20150824
		//发货单的发货方式记录，用来判别发货方式是否发生变化，如果发货方式发生变化，那么向后续系统推送接口数据
		String view = getSuccessView();
		view = "redirect:pdSendInfos.html";
		String key = "pdSendInfo.added";
		PdSendInfo pdSendInfo = (PdSendInfo) command;
		//订单关联的发货单,初始化接口所用字段：空值表示可以生成DO单 modify fu 2015-09-22
		String orderNo = pdSendInfo.getOrderNo();
		 if(!StringUtil.isEmpty(orderNo)){
			 String mo = orderNo.substring(0, 2);
			 if("M0".equals(mo)){
					pdSendInfo.setCanDo("");//modify fu 20150916   初始化接口所用字段：空值表示可以生成DO单
			 }
		 }
		boolean isNew = StringUtils.isEmpty(pdSendInfo.getSiNo());
		Locale locale = request.getLocale();

		if ("checkPdSendInfo".equals(strAction)) {// 初审
		

			
			
			if (pdSendInfo.getPdSendInfoDetails().size() > 0) {
				
				/*if("STARS".equalsIgnoreCase(pdSendInfo.getShNo())){//星晨
					if(this.sendShipInfo(pdSendInfo)){
						checkSendInfo(request, response, pdSendInfo);
					}else{
						errors.reject("pd.stars.erro", new Object[] {},
								LocaleUtil.getLocalText("pd.stars.erro"));
						return showForm(request, response, errors);
					}
				}else{
					checkSendInfo(request, response, pdSendInfo);
				}*/
				checkSendInfo(request, response, pdSendInfo);
				
				key = "pdSendInfo.check";
			} else {
				errors.reject("pd.qtyMustBeMoreThanZero", new Object[] {},
						LocaleUtil.getLocalText("pd.qtyMustBeMoreThanZero"));
				//return showForm(request, response, errors);
				request.setAttribute("errors", errors);
			}
			//modify fu 2015-09-18 在点击保存之前已经进行了库存的校验，这里是做了重复校验,功能保留--begin
			//为避免影响接口业务，重复的校验放到保存发货单之后，不然会影响到接口业务的
			List erroList = validateStock(pdSendInfo);
			//modify fu 2016-1-7 去掉发货单库存的校验
			/*if (erroList.size() > 0) {
				for (int i = 0; i < erroList.size(); i++) {
					errors.reject(null, new Object[] {}, (String) erroList
							.get(i)
							+ LocaleUtil.getLocalText("pd.notEnoughStock"));
				}
				request.setAttribute("errors", errors);
				//return showForm(request, response, errors);
			}*/
			//modify fu 2015-09-18 在点击保存之前已经进行了库存的校验，这里是做了重复校验--end

			view = "redirect:pdSendInfos.html?strAction=checkPdSendInfo";
		} else if ("sendPdSendInfo".equals(strAction)) {// 发货确认

			List erroList = validateStock(pdSendInfo, "x");
//			List erroList = new ArrayList();
			if (erroList.size() == 0) {
				key = "pdSendInfo.send";
				try {
					try{
						//发货确认
						sendPdSendInfo(request, response, pdSendInfo);
						
						//添加短信功能：Modify By WuCF 20140113				
						//Modify By WuCF 20140310 添加短信开关控制
						String smsSendOpenStatus = ConfigUtil.getConfigValue(sessionLogin.getCompanyCode().toUpperCase(), "smssend.open.status");
						
						//哪些仓库发货可以发送短信，在参数中配置
						String smsSendWarehouseNos = ConfigUtil.getConfigValue(sessionLogin.getCompanyCode().toUpperCase(), "smssend.warehouseno");
						
						//Modify By WuCF 20140117 中脉系统短信发送
						String mobilePhone = pdSendInfo.getRecipientMobile();
						
						//Modify By WuCF 20150424 是否包含字母或数字
						String isContain = this.isNumberOrWord(pdSendInfo.getTrackingNo()); 
						
						if(smsSendWarehouseNos.contains(","+pdSendInfo.getWarehouseNo()+",") && "Y".equals(smsSendOpenStatus)){// && isContain!=null && !"".equals(isContain)
							
							/*短信内容：亲爱的中脉家人，您的会员号CN********，发货单号LO01*************,已于*月*日发出。
							运单号：*****（系统上传的物流跟踪号），物流公司：*****（系统上传的物流公司）。
							请您查收，并注意开箱验货。 */
							
							/*SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy年MM月dd日");   
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
							SmsSend.sendSms(url1,url2,mobilePhone, message.toString());*/
							
                             //modify by fu 2016-1-20 变更短信内容---------------------------------------begin
							
							/**亲爱的中脉家人，您CN********下LO01************** 已于*月*日发出，**物流 ********* 请您注意查收货物。
							 * 中脉有效查货期30天，超过此期限及签收后反馈少货公司将不予受理，敬请知悉。
							 */
							SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy年MM月dd日");   
							String dateStr = dateformat2.format(new Date());
							
							StringBuffer message = new StringBuffer("亲爱的中脉家人，您");
							message.append(pdSendInfo.getCustomer().getUserCode());
							message.append("下");
							message.append(pdSendInfo.getSiNo());
							message.append("已于"+dateStr+"发出，");
							message.append(ListUtil.getListValue(sessionLogin.getCompanyCode().toUpperCase(), "pd.shno", pdSendInfo.getShNo()));
							message.append("物流");
							if(StringUtils.isNotEmpty(pdSendInfo.getTrackingNo())){
								message.append(pdSendInfo.getTrackingNo());
							}
							message.append("请您注意查收货物。中脉有效查货期30天，超过此期限及签收后反馈少货公司将不予受理，敬请知悉。");
							log.info("发货短信message:"+message);
							String url1 = ListUtil.getListValue(sessionLogin.getCompanyCode().toUpperCase(), "smssend.url", "1");
							String url2 = ListUtil.getListValue(sessionLogin.getCompanyCode().toUpperCase(), "smssend.url", "2");
							System.out.println( message.toString());
							SmsSend.sendSms(url1,url2,mobilePhone, message.toString());
							
                            //modify by fu 2016-1-20 变更短信内容---------------------------------------end

						
							//将短信写入到数据库表
							JpmSmssendInfo jpmSmssendInfo = new JpmSmssendInfo();
							jpmSmssendInfo.setSmsType("1");////短信类型  例如：1：仓库发货确认    2：找回密码   3：电影票  目前只有三种，在列表中配置
							jpmSmssendInfo.setSmsRecipient(pdSendInfo.getCustomer().getUserCode());//短信接收人:用户会员编号
							jpmSmssendInfo.setSmsMessage(message.toString());//短信内容
							jpmSmssendInfo.setSmsTime(new Date());//发送时间
							jpmSmssendInfo.setSmsOperator(sessionLogin.getUserCode());//操作人
							jpmSmssendInfo.setSmsStatus("1");//保留字段，是否发送成功！ 默认为1：成功！ 现在短信还不能知道是否发送成功，没有返回值！
							jpmSmssendInfo.setRemark("单个发货确认！仓库："+pdSendInfo.getWarehouseNo());//备注
							jpmSmssendInfo.setPhoneNum(mobilePhone);//手机号码
							jpmProductSaleNewManager.saveJpmSmssendInfo(jpmSmssendInfo);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
					
				} catch (AppException e) {// 捕获代理商库存不足
					// TODO Auto-generated catch block
					errors.reject(null, new Object[] {}, LocaleUtil
							.getLocalText(e.getErrMsg()));
					key = "pd.agent.notEnoughStock";
//					return showForm(request, response, errors);
					request.setAttribute("errors", errors);
				}

			} else {
				for (int i = 0; i < erroList.size(); i++) {
					errors.reject(null, new Object[] {}, (String) erroList
							.get(i)
							+ LocaleUtil.getLocalText("pd.notEnoughStock"));
				}
				key = "pd.notEnoughStock";
				request.setAttribute("errors", errors);
//				return showForm(request, response, errors);
				pdSendInfo.setShipmentIdentificationNumber("");
			}

			view = "redirect:pdSendInfos.html?strAction=sendPdSendInfo";
		} else if ("acceptPdSendInfo".equals(strAction)) {// 收货确认
			acceptPdSendInfo(request, response, pdSendInfo);
			key = "pdSendInfo.accept";
			view = "redirect:pdSendInfos.html?strAction=acceptPdSendInfo";
		} else if ("receiptPdSendInfo".equals(strAction)) {// 返单确认
			receiptPdSendInfo(request, response, pdSendInfo);
			key = "pdSendInfo.receipt";
			view = "redirect:pdSendInfos.html?strAction=receiptPdSendInfo";
		}

		else if ("addPdSendInfo".equals(strAction)) {// 新建 手动制单
		// List unsendList = pdSendInfoManager
		// .getUnSendOrderByUserAndSiNo(pdSendInfo.getSysUser()
		// .getUserCode(), pdSendInfo.getSiNo());
		// // List erroList = validateStock(pdSendInfo);//现前台判断库存
		//
		// if (unsendList != null && unsendList.size() > 0) {
		// for (int i = 0; i < unsendList.size(); i++) {
		// PdSendInfo sendInfo = (PdSendInfo) unsendList.get(i);
		// key = "pd.hasUnsendOrder";
		// errors.reject(null, new Object[] {}, LocaleUtil
		// .getLocalText("pd.hasUnsendOrder")
		// + LocaleUtil.getLocalText("pdSendInfo.siNo")
		// + sendInfo.getSiNo());
		//
		// }
		// addPdSendInfo(request, response, pdSendInfo);
		// // return showForm(request, response, errors);
		// } else {
		// key = "menu.pd.addPdSendInfo";
		// addPdSendInfo(request, response, pdSendInfo);
		// }

			view = "redirect:pdSendInfos.html?strAction=editPdSendInfo";
		} else if ("submitPdSendInfo".equals(strAction)) {// 提交
			/**
			 * 现前台判断库存 List erroList = validateStock(pdSendInfo);
			 * if(erroList.size()==0){
			 * editPdSendInfo(request,response,pdSendInfo); key =
			 * "pdSendInfo.submit"; }else{ for(int i=0;i<erroList.size();i++){
			 * errors.reject((String)erroList.get(i)+"pd.notEnoughStock", new
			 * Object[] {},(String)erroList.get(i)+LocaleUtil.getLocalText(
			 * "pd.notEnoughStock")); }
			 * 
			 * return showForm(request, response, errors); }
			 */
			key = "pd.hasSubmited";
			if(pdSendInfo.getOrderFlag()==-1){
//				pdSendInfo.setOrderFlag(0);
				submitPdSendInfo(request, response, pdSendInfo);
				key = "pdSendInfo.submit";
			}
			

			view = "redirect:pdSendInfos.html?strAction=editPdSendInfo";
		}

		else if ("editPdSendInfo".equals(strAction)) {// 编辑
			key = "pd.hasSubmited";
			if(pdSendInfo.getOrderFlag()==-1){
				editPdSendInfo(request, response, pdSendInfo);
				key = "pdSendInfo.update";
			}
			

			// editPdSendInfo(request, response, pdSendInfo);
			// key = "pdSendInfo.submit";

			view = "redirect:pdSendInfos.html?strAction=editPdSendInfo";
		}

		else if ("deletePdSendInfo".equals(strAction)) {// 删除
			key = "pd.hasSubmited";

			pdSendInfoManager.removePdSendInfo(pdSendInfo.getSiNo().toString());
			key = "pdSendInfo.delete";

			// saveMessage(request, getText("pdSendInfo.deleted", locale));
			view = "redirect:pdSendInfos.html?strAction=editPdSendInfo";
		} else if ("toNewPdSendInfo".equals(strAction)) {// 不支持
			
		    //在发货单编辑之前去WMS询问发货单能否编辑----begin modify by fu 2016-03-21
			String methodNameWMS = "checkstatus";
			String typeWMS = "11";
			String canUpdate = pdWarehouseStockManager.getPdLogisticsCanModify(pdSendInfo.getSiNo(), typeWMS, methodNameWMS);
			if((!StringUtil.isEmpty(canUpdate))&&(!"allow".equals(canUpdate))){
				if("notAllow".equals(canUpdate)){
					MessageUtil.saveLocaleMessage(request, "因发货单后续系统已经操作，故发货单不能编辑！");
				}else{
					//modify by fu 20160627 发货单作废功能优化（接口询问机制，如果WMS放回XXX发货单已撤单，那么EC就将对应的发货单设置成发货作废状态）--begin
					String cdbz = "发货单已撤单";//WMS撤单标志
					if(!StringUtil.isEmpty(cdbz)){
						int cd = canUpdate.indexOf(cdbz);
						if(cd!=(-1)){
							pdSendInfoManager.reSetPdSendInfoShipTypeFour(pdSendInfo.getSiNo());
							MessageUtil.saveLocaleMessage(request, "因WMS系统发货单已撤单，故EC将发货单设置为发货作废！");
							view = "redirect:pdSendInfos.html?strAction=checkPdSendInfo";
							return new ModelAndView(view);
						}
					}
					//modify by fu 20160627 发货单作废功能优化（接口询问机制，如果WMS放回XXX发货单已撤单，那么EC就将对应的发货单设置成发货作废状态）--end

					MessageUtil.saveLocaleMessage(request,canUpdate);
				}
				view = "redirect:pdSendInfos.html?strAction=checkPdSendInfo";
				return new ModelAndView(view);
			}
		    //在发货单编辑之前去WMS询问发货单能否编辑----end modify by fu 2016-03-21
			
			key = "error.cannotreturn";
			
			if(pdSendInfo.getOrderFlag()==0 ){
				pdSendInfo.setOrderFlag(-1);
				pdSendInfo.setEditTime(new Date());
				pdSendInfo.setEditUsrCode(sessionLogin.getUserCode());
				key = "operation.notice.tonew";
				 pdSendInfoManager.savePdSendInfo(pdSendInfo);
			}else if(pdSendInfo.getOrderFlag()==1){
				pdSendInfo.setOrderFlag(-1);
				pdSendInfo.setEditTime(new Date());
				pdSendInfo.setEditUsrCode(sessionLogin.getUserCode());
				pdSendInfo.setCanDo("N");//modify fu 20150916 发货单由已审核的状态变成新单状态的时候：OMS\WMS不能生成新的DO单，N表示不可以生成DO单
				key = "operation.notice.tonew";
				 pdSendInfoManager.savePdSendInfo(pdSendInfo);
				/*if("STARS".equalsIgnoreCase(pdSendInfo.getShNo())){
					if(sendVoidShipOrder(pdSendInfo)){
						pdSendInfo.setOrderFlag(-1);
						pdSendInfo.setEditTime(new Date());
						pdSendInfo.setEditUsrCode(sessionLogin.getUserCode());
						key = "operation.notice.tonew";
						 pdSendInfoManager.savePdSendInfo(pdSendInfo);
					}
				}else{
					pdSendInfo.setOrderFlag(-1);
					pdSendInfo.setEditTime(new Date());
					pdSendInfo.setEditUsrCode(sessionLogin.getUserCode());
					key = "operation.notice.tonew";
					 pdSendInfoManager.savePdSendInfo(pdSendInfo);
				}*/
				
			}
			
			view = "redirect:pdSendInfos.html?strAction=checkPdSendInfo";

		}else if("updateHarryFlag".equals(strAction)){
			if(pdSendInfo.getOrderFlag()<2){
				pdSendInfo.setEditTime(new Date());
				pdSendInfo.setEditUsrCode(sessionLogin.getUserCode());
				pdSendInfoManager.savePdSendInfo(pdSendInfo);
			}else{
				key = "pd.hasSubmited";
			}
			view = "redirect:pdSendInfos.html?strAction=editPdSendInfo";
		}
//		

		// }else if("saveTrackingNo".equals(strAction)){
		// saveTrackingNo(pdSendInfo,request,response);
		// key = "pdSendInfo.update";
		// view = "redirect:pdSendInfos.html?strAction=searchPdSendInfo";
		// }
		Integer orderFlag = pdSendInfo.getOrderFlag();
		if((null!=orderFlag)&& (orderFlag>=2)){//发货确认之后的发货单状态，就不需要向后续系统推送接口数据，同时也不需要判断有没有DO单过来。
			saveMessage(request, getText(sessionLogin.getDefCharacterCoding(), key));
		}else{
			String haveDo = pdSendInfo.getShipmentIdentificationNumber();
		    if((!StringUtil.isEmpty(haveDo))&&"haveDo".equals(haveDo)){
				MessageUtil.saveLocaleMessage(request, "因发货单关联的DO单存在，故发货单不可以修改！");
		    }else{
				saveMessage(request, getText(sessionLogin.getDefCharacterCoding(), key));
		    }
		}
		return new ModelAndView(view);
	}

	private boolean sendVoidShipOrder(PdSendInfo pdSendInfo){
		boolean ret = false;
		try {
			ret=starsExpressService.sendVoidShipOrder(pdSendInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("退回失败");
		}
		return ret;
	}
	
	private boolean sendShipInfo(PdSendInfo pdSendInfo){
		log.info("sendShipInfo=>stars开始");
		boolean ret = false;
		try {
			ret=starsExpressService.sendShipInfo(pdSendInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("stars失败=>"+e.getMessage());
			e.printStackTrace();
		}
		return ret;
	}
//	private boolean 
	private void saveTrackingNo(PdSendInfo pdSendInfo,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Set sets = pdSendInfo.getPdSendInfoDetails();
		Iterator iterator = sets.iterator();
		while (iterator.hasNext()) {
			PdSendInfoDetail pdSendInfoDetail = (PdSendInfoDetail) iterator
					.next();
			String trackingNo = request.getParameter(pdSendInfoDetail
					.getUniNo()
					+ "-trackingNo");
			// pdSendInfoDetail.setTrackingNo(trackingNo);
			pdSendInfoDetailManager.savePdSendInfoDetail(pdSendInfoDetail);
		}
	}

	/**
	 * 返单确认
	 * 
	 * @param request
	 * @param response
	 * @param pdSendInfo
	 */
	private void receiptPdSendInfo(HttpServletRequest request,
			HttpServletResponse response, PdSendInfo pdSendInfo) {
		// TODO Auto-generated method stub

		/*************
		 * List pdSendInfoDetailList = pdSendInfoDetailManager
		 * .getPdSendInfoDetailsBySiNo(pdSendInfo.getSiNo()); if
		 * (pdSendInfoDetailList != null) { for (int i = 0; i <
		 * pdSendInfoDetailList.size(); i++) { PdSendInfoDetail pdSendInfoDetail
		 * = new PdSendInfoDetail(); pdSendInfoDetail = (PdSendInfoDetail)
		 * pdSendInfoDetailList .get(i); String qty =
		 * request.getParameter(pdSendInfoDetail .getPmProduct().getProductNo()
		 * + "-acceptQty"); if (StringUtils.isNotEmpty(qty)) {
		 * pdSendInfoDetail.setAcceptQty(new Integer(qty)); }
		 * pdSendInfoDetailManager.savePdSendInfoDetail(pdSendInfoDetail); } }
		 * if (pdSendInfo.getRecheckTime() == null) {
		 * pdSendInfo.setRecheckTime(new Date()); }
		 *************/
		pdSendInfo.setOrderFlag(4);
		pdSendInfoManager.savePdSendInfo(pdSendInfo);
	}

	/**
	 * 收货确认
	 * 
	 * @param request
	 * @param response
	 * @param pdSendInfo
	 */
	private void acceptPdSendInfo(HttpServletRequest request,
			HttpServletResponse response, PdSendInfo pdSendInfo) {
		// TODO Auto-generated method stub
		pdSendInfo.setOrderFlag(3);

		pdSendInfoManager.savePdSendInfo(pdSendInfo);
	}

	/**
	 * 提交发货单
	 * 
	 * @param request
	 * @param response
	 * @param pdSendInfo
	 */
	private void submitPdSendInfo(HttpServletRequest request,
			HttpServletResponse response, PdSendInfo pdSendInfo) {
		// TODO Auto-generated method stub
//		SysUser sessionLogin = SessionLogin.getLoginUser(request);

		pdSendInfo.setOrderFlag(0);
		pdSendInfoManager.savePdSendInfo(pdSendInfo);
	}

	/**
	 * 编辑发货单
	 * 
	 * @param request
	 * @param response
	 * @param pdSendInfo
	 */
	private void editPdSendInfo(HttpServletRequest request,
			HttpServletResponse response, PdSendInfo pdSendInfo) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdSendInfo.setEditTime(new Date());

		pdSendInfo.setEditUsrCode(sessionLogin.getUserCode());
//		String agentNo = pdSendInfo.getSysUser().getUserCode();

		// Set pdSendInfoDetails = pdSendInfo.getPdSendInfoDetails();
		// Set sets = new HashSet();
		// Iterator iterator =pdSendInfoDetails.iterator();
		// while(iterator.hasNext()){
		// PdSendInfoDetail pdSendInfoDetail = new PdSendInfoDetail();
		// pdSendInfoDetail = (PdSendInfoDetail) iterator.next();
		// String qty =
		// request.getParameter(pdSendInfoDetail.getPiProduct().getProductNo()+"-qty");
		// if(StringUtils.isNotEmpty(qty)){
		// pdSendInfoDetail.setQty(new Long(qty));
		// }
		// String price =
		// request.getParameter(pdSendInfoDetail.getPiProduct().getProductNo()+"-price");
		// if(StringUtils.isNotEmpty(price)){
		// pdSendInfoDetail.setPrice(new BigDecimal(price));
		// }
		// pdSendInfoDetailManager.savePdSendInfoDetail(pdSendInfoDetail);
		// }

		/*Set pdSendInfoDetails = pdSendInfo.getPdSendInfoDetails();
		Iterator iterator = pdSendInfoDetails.iterator();
		while (iterator.hasNext()) {
			PdSendInfoDetail pdSendInfoDetail = (PdSendInfoDetail) iterator
					.next();

			String qty = request.getParameter(pdSendInfoDetail.getProductNo()

			+ "-qty");
			if (StringUtils.isNotEmpty(qty)) {
				pdSendInfoDetail.setQty(new Long(qty));

				// pdSendInfoDetail.setAcceptQty(new Integer(qty));
			}
//			价格直接取订单
//			String price = request.getParameter(pdSendInfoDetail.getProductNo()
//					+ "-price");
//			if (StringUtils.isNotEmpty(price)) {
//				pdSendInfoDetail.setPrice(new BigDecimal(price));
//			}
			pdSendInfoDetailManager.savePdSendInfoDetail(pdSendInfoDetail);

		}*/
		/**************
		 * List pdSendInfoDetailList = pdSendInfoDetailManager
		 * .getPdSendInfoDetailsBySiNo(pdSendInfo.getSiNo()); // // Set sets =
		 * new HashSet(); if (pdSendInfoDetailList != null) { for (int i = 0; i
		 * < pdSendInfoDetailList.size(); i++) { PdSendInfoDetail
		 * pdSendInfoDetail = new PdSendInfoDetail(); pdSendInfoDetail =
		 * (PdSendInfoDetail) pdSendInfoDetailList .get(i); String qty =
		 * request.getParameter(pdSendInfoDetail .getPmProduct().getProductNo()
		 * + "-qty"); if (StringUtils.isNotEmpty(qty)) {
		 * pdSendInfoDetail.setQty(new Integer(qty));
		 * pdSendInfoDetail.setCountQty(new Integer(qty));
		 * pdSendInfoDetail.setAcceptQty(new Integer(qty)); } String price =
		 * request.getParameter(pdSendInfoDetail .getPmProduct().getProductNo()
		 * + "-price"); if (StringUtils.isNotEmpty(price)) {
		 * pdSendInfoDetail.setPrice(new BigDecimal(price)); }
		 * pdSendInfoDetailManager.savePdSendInfoDetail(pdSendInfoDetail); //
		 * pdSendInfoDetail.setSiNo(pdSendInfo.getSiNo()); //
		 * sets.add(pdSendInfoDetail); } }
		 ***/
		// pdSendInfo.setPdSendInfoDetails(sets);
		pdSendInfoManager.savePdSendInfo(pdSendInfo);
	}

	/**
	 * 发货确认
	 * 
	 * @param request
	 * @param response
	 * @param pdSendInfo
	 * @throws Exception 
	 */
	private void sendPdSendInfo(HttpServletRequest request,
			HttpServletResponse response, PdSendInfo pdSendInfo) throws Exception {

		// TODO Auto-generated method stub
		
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		// pdSendInfo.setOkStatus("2");
		/*pdSendInfo.setOkTime(new Date());
		pdSendInfo.setOkUsrCode(sessionLogin.getUserCode());
		pdSendInfo.setOrderFlag(2);
		pdSendInfo.getPdSendInfoDetails();*/
		pdSendInfoManager.confirmSendInfo(pdSendInfo, sessionLogin);
//		pdSendInfoManager.confirmSendInfo(pdSendInfo, sessionLogin,request);
		/*if("CN".equals(pdSendInfo.getCompanyCode())){
			JMSContextUtil.getJMSSender("jmsTimeSender").sendObjecsMessage("time");
		}*/
		
		// JmsSendInfo jmsSendInfo = new JmsSendInfo();
		// jmsSendInfo.setSendInfo(pdSendInfo);
		// jmsSendInfo.setSysUser(sessionLogin.getSysUser());
		// sendInfoProducter.send(jmsSendInfo);
		// pdEnterWarehouseManager.savePdEnterWarehouse(pdEnterWarehouse);
		log.warn(">>>>>shipping--time-end>>>>" + new Date());
	}

	/**
	 * 初审发货单
	 * 
	 * @param request
	 * @param response
	 * @param pdSendInfo
	 * @throws Exception 
	 */
	private void checkSendInfo(HttpServletRequest request,
			HttpServletResponse response, PdSendInfo pdSendInfo) throws Exception {
		// TODO Auto-generated method stub
		// pdSendInfo.setCheckStatus("2");
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdSendInfo.setCheckUsrCode(sessionLogin.getUserCode());
		pdSendInfo.setCheckTime(new Date());
		pdSendInfo.setOrderFlag(1);
		pdSendInfo.setCanDo("");
//		pdSendInfoManager.savePdSendInfo(pdSendInfo);
		pdSendInfoManager.checkPdSendInfo(pdSendInfo);
	}
	
//	private boolean sendStartsExpress(PdSendInfo pdSendInfo){
//		boolean ret = false;
//		
//		return ret;
//		
//	}

	/**
	 * 新增发货单
	 * 
	 * @param request
	 * @param response
	 * @param pdSendInfo
	 */
	private void addPdSendInfo(HttpServletRequest request,
			HttpServletResponse response, PdSendInfo pdSendInfo) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdSendInfo.setSiNo(sysIdManager.buildIdStr("sino"));

		/*pdSendInfo.setCreateUsrNo(sessionLogin.getUserCode());
		pdSendInfo.setAmount(new BigDecimal(0));
		pdSendInfo.setCreateTime(new Date());
		pdSendInfo.setCheckStatus("1");

		pdSendInfo.setRecheckStatus("1");
		pdSendInfo.setOkStatus("1");
		pdSendInfo.setStockFlag("0");
		pdSendInfo.setSiDate(new Date());
		// pdSendInfo.setCompanyNo(pdSendInfo.getSysUser().getCompanyCode());
		String agentNo = pdSendInfo.getSysUser().getUserCode();
		pdSendInfo.setCompanyNo(sysUserManager.getSysUser(agentNo)
				.getCompanyCode());

		log.debug(">>,agentNo=" + agentNo);
		List pdSendInfoDetailList = pdSendInfoDetailManager
				.getPdReadySendInfoDetails(agentNo);

		Set sets = new HashSet();
		// log.debug("pdSendInfoDetailList>>"+pdSendInfoDetailList.size());
		if (pdSendInfoDetailList != null) {

			for (int i = 0; i < pdSendInfoDetailList.size(); i++) {
				PdSendInfoDetail pdSendInfoDetail = new PdSendInfoDetail();
				pdSendInfoDetail = (PdSendInfoDetail) pdSendInfoDetailList
						.get(i);
				pdSendInfoDetail.setCompanyNo(pdSendInfo.getCompanyNo());
				pdSendInfoDetail.setSiNo(pdSendInfo.getSiNo());
				String qty = request.getParameter(pdSendInfoDetail
						.getPmProduct().getProductNo()
						+ "-qty");
				if (StringUtils.isNotEmpty(qty)) {
					pdSendInfoDetail.setQty(new Integer(qty));
					pdSendInfoDetail.setCountQty(new Integer(qty));
					pdSendInfoDetail.setAcceptQty(new Integer(qty));
				}
				// log.debug("savePdSendInfoDetail="+pdSendInfoDetail.getQty());
				pdSendInfoDetailManager.savePdSendInfoDetail(pdSendInfoDetail);
				String price = request.getParameter(pdSendInfoDetail
						.getPmProduct().getProductNo()
						+ "-price");
				// log.info("price="+price);
				if (StringUtils.isNotEmpty(price)) {
					pdSendInfoDetail.setPrice(new BigDecimal(price));
				} else {
					pdSendInfoDetail.setPrice(new BigDecimal(0));
				}
				// sets.add(pdSendInfoDetail);
			}
		}
		// pdSendInfo.setPdSendInfoDetails(sets);
		pdSendInfoManager.savePdSendInfo(pdSendInfo, true);*/

	}

	/**
	 * 判断仓库库存
	 * 
	 * @param pdSendInfo
	 * @return
	 */
	private List validateStock(PdSendInfo pdSendInfo, String flag) {
		List list = new ArrayList();
		// String[] ret =null;
		// int i=0;
		Set sets = pdSendInfo.getPdSendInfoDetails();
		Iterator iterator = sets.iterator();
		while (iterator.hasNext()) {
			PdSendInfoDetail pdSendInfoDetail = (PdSendInfoDetail) iterator
					.next();

			if ((pdSendInfoDetail.getQty() > 0)
					&& (!pdWarehouseStockManager.enoughStock(pdSendInfo
							.getWarehouseNo(), pdSendInfoDetail.getProductNo(),
							pdSendInfoDetail.getQty()))) {
				list.add(pdSendInfoDetail.getProductNo());
				// ret[i++] =
				// pdSendInfoDetail.getPmProduct().getProductNo()+"-"+pdSendInfoDetail.getPmProduct().getProductName();
			}
		}
		return list;
	}

	public void setPdWarehouseStockManager(
			PdWarehouseStockManager pdWarehouseStockManager) {
		this.pdWarehouseStockManager = pdWarehouseStockManager;
	}

	/**
	 * 判断仓库库存
	 * 
	 * @param pdSendInfo
	 * @return
	 */
	private List validateStock(PdSendInfo pdSendInfo) {
		List list = new ArrayList();
		// String[] ret =null;
		// int i=0;
		Set sets = pdSendInfo.getPdSendInfoDetails();
		Iterator iterator = sets.iterator();
		Long l = new Long(0);
		while (iterator.hasNext()) {
			PdSendInfoDetail pdSendInfoDetail = (PdSendInfoDetail) iterator
					.next();
			l = pdWarehouseStockManager.enoughStockSend(pdSendInfo
					.getWarehouseNo(), pdSendInfoDetail.getProductNo(),
					0);
			if ((pdSendInfoDetail.getQty() > 0) && (l < 0)) {
				list.add(pdSendInfoDetail.getProductNo()

				+ "[<font color='red'>" + l + "</font>]");
				// ret[i++] =
				// pdSendInfoDetail.getPmProduct().getProductNo()+"-"+pdSendInfoDetail.getPmProduct().getProductName();
			}
		}
		return list;
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		String[] disallowedFields = new String[] { "orderFlag", "stockFlag" };
		// binder.setAllowedFields(allowedFields);
		binder.setDisallowedFields(disallowedFields);
		// binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
