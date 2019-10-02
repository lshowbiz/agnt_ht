package com.joymain.jecs.pd.webapp.action;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import mobset.smsSDK;
import mobset.str_SendMsg;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.fi.model.JfiPosImport;
import com.joymain.jecs.pd.model.PdSendInfo;
import com.joymain.jecs.pd.model.PdWarehouse;
import com.joymain.jecs.pd.service.PdLogisticsBaseManager;
import com.joymain.jecs.pd.service.PdSendInfoManager;
import com.joymain.jecs.pd.service.PdWarehouseManager;
import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.pm.model.JpmCombinationRelated;
import com.joymain.jecs.pm.service.JpmCombinationRelatedManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.bean.ContextUtil;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.action.FileUpload;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 功能描述:批量修改收货人地址功能
 * @author wucaifeng
 * @date:2013-04-19
 */

public class PdFileUpload2Controller extends BaseFormController {
	PdSendInfoManager pdSendInfoManager = null;
	PdWarehouseManager pdWarehouseManager = null;
	private PdLogisticsBaseManager pdLogisticsBaseManager;   
	private JpmCombinationRelatedManager jpmCombinationRelatedManager;
	private PdWarehouseStockManager pdWarehouseStockManager;
	
	public void setJpmCombinationRelatedManager(
			JpmCombinationRelatedManager jpmCombinationRelatedManager) {
		this.jpmCombinationRelatedManager = jpmCombinationRelatedManager;
	}
	public void setPdWarehouseManager(PdWarehouseManager pdWarehouseManager) {
		this.pdWarehouseManager = pdWarehouseManager;
	}
	public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}
	
	public void setPdWarehouseStockManager(PdWarehouseStockManager pdWarehouseStockManager) {
		this.pdWarehouseStockManager = pdWarehouseStockManager;
	}
	
	public void setPdLogisticsBaseManager(
			PdLogisticsBaseManager pdLogisticsBaseManager) {
		this.pdLogisticsBaseManager = pdLogisticsBaseManager;
	}
	/**
	 * 构造函数
	 */
	public PdFileUpload2Controller() {
		setCommandName("fileUpload");
		setCommandClass(FileUpload.class); 
	}

	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String flagnum = request.getParameter("flagnum");
		String characterCoding=((SysUser) ContextUtil.getResource("sessionLogin")).getDefCharacterCoding();
		//从request获取的值存入到request，以便下传
		request.setAttribute("flagnum", flagnum);
		
		if(!"7".equals(flagnum)){//跳转很慢。。7是套餐商品导入功能  无需初始化这些数据
			super.initPmProductMap(request);
			super.initStateCodeParem(request);
		}
		 
		//测试手机发送短信提醒功能
//		log.info("===========准备测试！");
//		sendSms(null); 
//		log.info("===========测试完成！");
		return super.formBackingObject(request);
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
	 * 进入到上传页面的函数
	 * 
	 */
	public ModelAndView processFormSubmission(HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors)
	throws Exception {
		String flagnum = String.valueOf(request.getAttribute("flagnum"));
		log.info(flagnum+"!!!!!!!!!!!!!!!!!!!!requestFlagnum:"+request.getParameter("flagnum"));
		if (request.getParameter("cancel") != null) {
			return new ModelAndView(getCancelView());
		}
		if(RequestUtil.freshSession(request, "updateTrackingNo", 60L)>0){
			return new ModelAndView("redirect:pdFileUpload2.html?strAction=updateTrackingNo&flagnum="+flagnum);
		}
		
		return super.processFormSubmission(request, response, command, errors);
	}

	/**
	 * 提交函数
	 */
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command,
			BindException errors)
	throws Exception {
		String flagnum = String.valueOf(request.getAttribute("flagnum"));
		if(RequestUtil.saveOperationSession(request, "updateTrackingNo", 60L)>0){
			return new ModelAndView("redirect:pdFileUpload2.html?strAction=updateTrackingNo&flagnum="+flagnum);
		}

		List ret=new ArrayList();

		log.info("upload...");
		//修改收货人地址信息：具体修改的内容通过file文件流读取到的execl文件
		ret = this.updateTrackingNo(request, response);

		request.setAttribute("errors", ret);
		return new ModelAndView(getSuccessView());
	}

	/**
	 * 操作处理函数
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private List updateTrackingNo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		log.info("upload file......................."); 
		
		String flagnum = String.valueOf(request.getAttribute("flagnum"));
		log.info("###############flagnum:"+flagnum);
		List ret=new ArrayList();
		//获得文件对象，然后获得文件字节流
		MultipartHttpServletRequest multipartRequest =
			(MultipartHttpServletRequest) request;
		CommonsMultipartFile file =
			(CommonsMultipartFile) multipartRequest.getFile("file");

		InputStream stream = file.getInputStream();

		 
		//开始处理表单EXL中的数据
		Workbook wb = Workbook.getWorkbook(stream);
		Sheet sheet1 = wb.getSheet(0);

		Cell cell = null;
		int row = sheet1.getRows();//总行数
		int col = sheet1.getColumns();//总列数
		
		//Modify By WuCF 20140311上传数据量限制
		if(row>=1002){
			ret.add(LocaleUtil.getLocalText("notice.row.number", "一次性上传数据不能超过1000条！"));
			return ret;
		}
		
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		//发货人信息
		Map<String,String> provinceMap = null;
		Map cityMap = null;
		List companyList = null;
		List companyCodeList = null;
		
		//修改货仓库物流状态
		List<String> pdWarehouses = new ArrayList<String>();
		List<String> shNoList = new ArrayList<String>();
		if("1".equals(flagnum)){
			provinceMap = pdSendInfoManager.getAlStateProvincesMapByCountry(sessionLogin.getCompanyCode());
	    	cityMap = pdSendInfoManager.getAlCityMap(sessionLogin.getCompanyCode());
	    	companyList = pdSendInfoManager.getCompanyCode();
			companyCodeList = new ArrayList();
			String s = "  ";
			for(Object str : companyList){
				s = str.toString().split("=")[1];
				companyCodeList.add(s.substring(0,s.length()-1));
			} 
		}else if("2".equals(flagnum)){
			List<PdWarehouse> pdWarehouseList = pdWarehouseManager.getPdWarehouses(new PdWarehouse());
			for(PdWarehouse pw : pdWarehouseList){
				pdWarehouses.add(pw.getWarehouseNo());
			}
			List<Map<String,String>> shList = pdSendInfoManager.jsysListValues("pd.shno", "");
			for(Map<String,String> map : shList){
				shNoList.add(map.get("value_code"));
			}
		}
		
		//modify fu 2016-1-8 批量修改发货信息------------------begin
		else if("6".equals(flagnum)){
			provinceMap = pdSendInfoManager.getAlStateProvincesMapByCountry(sessionLogin.getCompanyCode());
	    	cityMap = pdSendInfoManager.getAlCityMap(sessionLogin.getCompanyCode());
	    	companyList = pdSendInfoManager.getCompanyCode();
			companyCodeList = new ArrayList();
			String s = "  ";
			for(Object str : companyList){
				s = str.toString().split("=")[1];
				companyCodeList.add(s.substring(0,s.length()-1));
			} 
			
			List<PdWarehouse> pdWarehouseList = pdWarehouseManager.getPdWarehouses(new PdWarehouse());
			for(PdWarehouse pw : pdWarehouseList){
				pdWarehouses.add(pw.getWarehouseNo());
			}
			List<Map<String,String>> shList = pdSendInfoManager.jsysListValues("pd.shno", "");
			for(Map<String,String> map : shList){
				shNoList.add(map.get("value_code"));
			}
			
			
		}
		//modify fu 2016-1-8 批量修复发货信息------------------end
		
		List<JpmCombinationRelated> jpmCombinationRelatedList = new ArrayList<JpmCombinationRelated>();
		JpmCombinationRelated ctr = null;
		int errCount = 0;
		for(int i=1;i<row;i++){
			Cell[] column =sheet1.getRow(i);
			try {
				//判断下取到的值，判断如果为空的，就不用处理，可能exl文件中有空行的行（只是内容为空，但是行存在）
				if(column!=null && column[0].getContents()!=null && !"".equals(column[0].getContents())){
					//1:上传批量修改订货单信息    2：上传货仓物流状态
					if("1".equals(flagnum)){
						String returnStr = saveTrackingNo(column,provinceMap,cityMap,companyCodeList);
						if(StringUtils.isNotEmpty(returnStr)){ 
							ret.add(LocaleUtil.getLocalText("notice.row.number", "第"+i+"行："+returnStr));
						} 
					}else if("2".equals(flagnum)){
						//判断是否存在
						String returnStr = saveTrackingNo2(column,sessionLogin.getUserCode(),pdWarehouses,shNoList); 
						if(StringUtils.isNotEmpty(returnStr)){
							ret.add(LocaleUtil.getLocalText("notice.row.number", "第"+i+"行："+returnStr));
						}
					}else if("3".equals(flagnum)){
						//判断是否存在
						String returnStr = updateStorageCordon(column,companyCodeList); 
						if(StringUtils.isNotEmpty(returnStr)){
							ret.add(LocaleUtil.getLocalText("notice.row.number", "第"+i+"行："+returnStr));
						}
					}
					//modify fu 2016-1-8 批量修复发货信息------------------begin
					else if("6".equals(flagnum)){
						String returnStr = savePdSendBatchModify(column,provinceMap,cityMap,companyCodeList,sessionLogin.getUserCode(),pdWarehouses,shNoList);
						if(StringUtils.isNotEmpty(returnStr)){ 
							ret.add(LocaleUtil.getLocalText("notice.row.number", "第"+i+"行："+returnStr));
						} 
					}
					//Modify By WuCF 2016-03-30 订单套餐数据批量上传
					else if("7".equals(flagnum)){
						ctr = new JpmCombinationRelated();
						String returnStr = uploadJpmcomBinationRelated(column,sessionLogin.getUserCode(),ctr);
						if("1".equals(returnStr)){//说明验证通过
							jpmCombinationRelatedList.add(ctr);
						}else{
							ret.add(LocaleUtil.getLocalText("notice.row.number", "第"+i+"行："+returnStr));
							errCount++;
						}
					}
					
					//modify fu 2016-1-8 批量修复发货信息------------------end
					
					
				}					
			} catch (AppException e) {
				// TODO Auto-generated catch block
				log.info("e="+e.getMessage());
				ret.add(LocaleUtil.getLocalText("notice.row.number", new Object[]{i})+LocaleUtil.getLocalText(e.getMessage()));
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				log.info("e="+e.getMessage());
				ret.add(LocaleUtil.getLocalText("notice.row.number", new Object[]{i})+LocaleUtil.getLocalText(e.getMessage()));
			}

		}
		if(errCount==0){
			//验证通过，保存数据
			if(!MeteorUtil.isBlankByList(jpmCombinationRelatedList)){
				for(JpmCombinationRelated c : jpmCombinationRelatedList){
					jpmCombinationRelatedManager.saveJpmCombinationRelated(c);
				}
			}
		}
		//处理完后，需要从session中清楚标示符flagnum
		request.getSession().removeAttribute("flagnum");
			
		return ret;
	}
	
	 
	/**
	 * 修改发货单人订单信息
	 * @param column
	 * @param sessionLogin
	 * @throws Exception
	 */
	private String saveTrackingNo(Cell[] column,Map provinceMap,Map cityMap,List companyList){
		String returnStr = "";
		String siNo = column[0].getContents();//订单号
		String recipientCaNo = column[1].getContents();//省份编号
		String city = column[2].getContents();//城市编号
		String countryCode = column[3].getContents();//国家区号
		String recipientAddr = column[4].getContents();//收货人地址
		String recipientName = column[5].getContents();//收货人姓名
		String recipientPhone = column[6].getContents();//收货人电话
		String recipientMobile = column[7].getContents();//收货人手机
		String remark = "";//备注字段  Modify By WuCF 2013-10-30
		if(column.length>=9){//如果备注列不加数据，则不用读取第9列（column[8]），否则数组越界
			remark = column[8].getContents();
		}
		
		//获得并设置对象的值
		PdSendInfo pdSendInfo =pdSendInfoManager.getPdSendInfo(siNo.trim());//通过siNo获得对象元素

		//订单标示符号： -1，未提交 0,新单 1,初审；2,仓库确认；3,到货确认；4：回单确认。   只有：-1、0、1三种状态才可以修改收货人信息s
//		log.info("1是否应该进入pdSendInfo.getOrderFlag():"+pdSendInfo.getOrderFlag());
//		initStateCodeParem();
		boolean b1 = false;
		boolean b2 = false;
		boolean b3 = false;
		if(pdSendInfo!=null){
			if(pdSendInfo.getOrderFlag()<2){
				//如果为空的数据，默认不修改
				if(!isBlank(recipientCaNo)){
					if(provinceMap.containsKey(recipientCaNo)){
						b1 = true;
					}else{
						returnStr += "第二列：省份编码不存在:"+recipientCaNo.trim()+"； --此行修改失败！";
						return returnStr;
					}				
				}
				if(!isBlank(city)){
					AlCity ac = pdSendInfoManager.getAlCity(Long.parseLong(city.trim())); 
					if(ac==null){
						returnStr += "第三列：城市编码不存在:"+city.trim()+"； --此行修改失败！ ";
						return returnStr;
					}else{
						if(!recipientCaNo.trim().equals(String.valueOf(ac.getAlStateProvince().getStateProvinceId()))){
							returnStr += "第三列：省编码："+recipientCaNo+"与市编码："+city+"不匹配； --此行修改失败！ ";
							return returnStr;
						}else{
							b2 = true;
						}
					}  
				}
				if(!isBlank(countryCode)){ 
					if(companyList.contains(countryCode.trim())){
						b3 = true;
					}else{
						returnStr += "第四列：分区编码不存在:"+countryCode.trim()+"； --此行修改失败！ ";
						return returnStr;
					}
				}
				
				if("".equals(returnStr)){
					if(b1){
						pdSendInfo.setRecipientCaNo(recipientCaNo.trim());
					}
					if(b2){
						pdSendInfo.setCity(city.trim());
					}
					if(b3){
						pdSendInfo.setCountryCode(countryCode.trim());
					}
					if(!isBlank(recipientAddr)){
						pdSendInfo.setRecipientAddr(recipientAddr.trim());
					}
					if(!isBlank(recipientName)){
						pdSendInfo.setRecipientName(recipientName.trim());
					}
					if(!isBlank(recipientPhone)){
						pdSendInfo.setRecipientPhone(recipientPhone.trim());
					}
					if(!isBlank(recipientMobile)){
						pdSendInfo.setRecipientMobile(recipientMobile.trim());
					}
					//备注字段  Modify By WuCF 2013-10-30
					if(!isBlank(remark)){
						pdSendInfo.setRemark((pdSendInfo.getRemark()==null || "".equals(pdSendInfo.getRemark()) 
								|| "null".equals(pdSendInfo.getRemark()) ? "" : pdSendInfo.getRemark())+"|"+remark);
					}
					
					//modify fu 2015-12-09 DO单存在的发货单不让修改，给出正确的提示----begin
					boolean haveDo = pdLogisticsBaseManager.getDoResult(pdSendInfo.getSiNo());
					String orderNo = pdSendInfo.getOrderNo();
					boolean noMo = true;
					 if(!StringUtil.isEmpty(orderNo)){
						 String mo = orderNo.substring(0, 2);
						 if("M0".equals(mo)){
							 noMo = false;
						 }
					 }
					String ShipmentHaveDo = pdSendInfo.getShipmentIdentificationNumber();
					//订单关联的发货单有DO单  或  发货退回单和换货单生成的发货单有发货状态回传---这两种情况不允许对发货单进行编辑
					if(haveDo || (noMo && "haveDo".equals(ShipmentHaveDo))){
						returnStr += "发货单号"+pdSendInfo.getSiNo()+"对应的DO单存在，故不允许修改发货单 --此行修改失败！";
					}
					//modify fu 2015-12-09 DO单存在的发货单不让修改，给出正确的提示----end
					
					pdSendInfoManager.savePdSendInfo(pdSendInfo);
				}else{
					returnStr += "   --此行修改失败！";
				} 
			} 
		}else{
			returnStr = "修改的发货单号不存在！   --此行修改失败！";
		} 
		return returnStr;
	}	 
	
	
	/**
	 * 修改货仓库物流状态
	 * @param column
	 * @param sessionLogin
	 * @throws Exception
	 */
	private String saveTrackingNo2(Cell[] column,String userCode,List<String> pdWarehouses,List<String> shNoList){
		String returnStr = "";
		String siNo = column[0].getContents();//订单号
		String warehouseNo = column[1].getContents();//仓库
		String shNo = column[2].getContents();//物流
		String orderFlag = column[3].getContents();//物流
		
		String shipType = column[4].getContents();//发货方式:空或者0表示正常发货;2暂不发货;3暂停发货 //Modify By fu 20151022
		String remark = "";//备注字段  Modify By WuCF 2013-10-30
		if(column.length>=6){//如果备注列不加数据，则不用读取第5列（column[4]），否则数组越界
			remark = column[5].getContents();
		} 
		  
		//获得并设置对象的值
		PdSendInfo pdSendInfo =pdSendInfoManager.getPdSendInfo(siNo.trim());//通过siNo获得对象VO
		if(pdSendInfo.getOrderFlag()>1){
			returnStr += "发货单状态为未提交(-1)、未审核(0)、已审核(1)的才允许修改 --此行修改失败！";
			return returnStr;
		}
		
		if(pdSendInfo!=null){
			//订单标示符号： -1，未提交 0,新单 1,初审；2,仓库确认；3,到货。   只有：-1、0、1两种状态才能被修改，修改后并把状态修改为1
			//提示：hibernate会自动提交，必须判断审核状态后再设置值；而不是先设置set值，然后再判断状态提交。
			List list = null;
			//if(pdSendInfo.getOrderFlag()<2){
				//修改审核状态 20130531 不修改
				//pdSendInfo.setOrderFlag(1);
				boolean b1 = false;
				boolean b2 = false;
				boolean b3 = false;
				//Modify By fu 20151022
				boolean b4 = false;
				
				//如果为空的数据，默认不修改
				if(!isBlank(warehouseNo)){
					if(pdWarehouses.contains((warehouseNo.trim()))){
						b1 = true;
					}else{
						returnStr += "第二列：仓库编号:"+warehouseNo+"不存在； --此行修改失败！"; 
						return returnStr;
					}
				} 
				if(!isBlank(shNo)){
					if(shNoList.contains(shNo.trim())){
						b2 = true;
					}else{
						returnStr += "第三列：物流公司编号:"+shNo+"不存在； --此行修改失败！";
						return returnStr;
					}
				}
				if(!isBlank(orderFlag)){
					if("-1".equals(orderFlag) || "0".equals(orderFlag) || "1".equals(orderFlag)){//Modify By WuCF 20130530 判断如果是数字，就修改状态
						b3 = true;
					}else{
						returnStr += "第四列：状态只能填写三种状态：-1、0、1 --此行修改失败！";
						return returnStr;
					}
				}
				if(!isBlank(shipType)){
					if("0".equals(shipType.trim()) || "2".equals(shipType.trim()) || "3".equals(shipType.trim())){//Modify By fu 20151022 判断如果是数字，就修改状态
						b4 = true;
					}else{
						returnStr += "第五列：发货方式只能填写三种值：0、2、3 --此行修改失败！";
						return returnStr;
					}
				}
				
				//通过修改标志判断是否可以设置值 
				if("".equals(returnStr)){
					if(b1){
						pdSendInfo.setWarehouseNo(warehouseNo.trim());
					}
					if(b2){
						pdSendInfo.setShNo(shNo.trim());
					}
					if(b3){
						pdSendInfo.setOrderFlag(Integer.parseInt(orderFlag.trim()));
						if("1".equals(orderFlag.trim())){
							pdSendInfo.setCheckTime(new Date());
							pdSendInfo.setCheckUsrCode(userCode);
							pdSendInfo.setCheckRemark("批量上传审核！");
						}
					} 
					
					//Modify By fu 20151022
					if(b4){
						pdSendInfo.setShipType(shipType.trim());
					}
					
					//备注字段  Modify By WuCF 2013-10-30
					if(!isBlank(remark)){
						pdSendInfo.setRemark((pdSendInfo.getRemark()==null || "".equals(pdSendInfo.getRemark()) 
								|| "null".equals(pdSendInfo.getRemark()) ? "" : pdSendInfo.getRemark())+"|"+remark);
					}
					
					//modify fu 2015-12-09 DO单存在的发货单不让修改，给出正确的提示----begin
					boolean haveDo = pdLogisticsBaseManager.getDoResult(pdSendInfo.getSiNo());
					String orderNo = pdSendInfo.getOrderNo();
					boolean noMo = true;
					 if(!StringUtil.isEmpty(orderNo)){
						 String mo = orderNo.substring(0, 2);
						 if("M0".equals(mo)){
							 noMo = false;
						 }
					 }
					String ShipmentHaveDo = pdSendInfo.getShipmentIdentificationNumber();
					//订单关联的发货单有DO单  或  发货退回单和换货单生成的发货单有发货状态回传---这两种情况不允许对发货单进行编辑
					if(haveDo || (noMo && "haveDo".equals(ShipmentHaveDo))){
						returnStr += "发货单号"+pdSendInfo.getSiNo()+"对应的DO单存在，故不允许修改发货单 --此行修改失败！";
					}
					//modify fu 2015-12-09 DO单存在的发货单不让修改，给出正确的提示----end
					
					pdSendInfoManager.savePdSendInfo(pdSendInfo);
					
					
				}
			//}
		}else{
			returnStr = "修改的发货单号不存在！   --此行修改失败！";
		}
		return returnStr;
	}
	
	/**
	 * 批量修改发货信息 
	 * @author modifu fu 2016-1-8 
	 * @param 
	 * @param 
	 * @throws 
	 */
	private String savePdSendBatchModify(Cell[] column,Map provinceMap,Map cityMap,List companyList,String userCode,List<String> pdWarehouses,List<String> shNoList){
		String returnStr = "";
		String siNo = column[0].getContents();//发货单号		
		String recipientCaNo ="";
	    String city = "";
		String countryCode = "";
		String recipientAddr = "";
		String recipientName = "";
		String recipientPhone = "";
		String recipientMobile = "";	
		String warehouseNo = "";
		String shNo = "";
		String orderFlag = "";		
		String shipType = "";	
		String suspendShipment = "";//modify by fu 2016-07-13 暂停发货原因
		if(column.length>=2){
		     recipientCaNo = column[1].getContents();//省份编号
	    }
	    if(column.length>=3){
			 city = column[2].getContents();//城市编号
	    }
	    if(column.length>=4){
			 countryCode = column[3].getContents();//公司,一般默认填写为CN
	    }
	    if(column.length>=5){
		     recipientAddr = column[4].getContents();//收货人地址
	    }
	    if(column.length>=6){
			 recipientName = column[5].getContents();//收货人姓名
	    }
	    if(column.length>=7){
			 recipientPhone = column[6].getContents();//收货人电话
	    }
	    if(column.length>=8){
			 recipientMobile = column[7].getContents();//收货人手机
	    }
	    if(column.length>=9){
			 warehouseNo = column[8].getContents();//仓库
	    }
	    if(column.length>=10){
			 shNo = column[9].getContents();//物流公司
	    }
	    if(column.length>=11){
			 orderFlag = column[10].getContents();//发货状态		
	    }
	    if(column.length>=12){
			 shipType = column[11].getContents();//发货方式:空或者0表示正常发货;2暂不发货;3暂停发货
	    }
	    //modify by fu 2016-07-13 暂停发货原因-----------------begin
	    if(column.length>=13){
	    	suspendShipment = column[12].getContents();//暂停发货原因：1退单；2换货；3定制床垫
	    }
	    //modify by fu 2016-07-13 暂停发货原因-----------------end
		String remark = "";//备注
		if(column.length>=14){//如果备注列不加数据，则不用读取第13列（column[12]），否则数组越界
			remark = column[13].getContents();
		}
		
		PdSendInfo pdSendInfo =pdSendInfoManager.getPdSendInfo(siNo.trim());

		boolean b1 = false;
		boolean b2 = false;
		boolean b3 = false;
		boolean b4 = false;
		boolean b5 = false;
		boolean b6 = false;
		boolean b7 = false;
		boolean b8 = false;//modify by fu 2016-07-13 暂停发货原因

		if(pdSendInfo!=null){
			//modify by fu 2016-1-22 已备货或发货作废的发货单处于锁定状态不允许修改-----begin
			//已备货或发货作废的发货单不允许修改
			String shipTypeInit = pdSendInfo.getShipType();
			if((!StringUtil.isEmpty(shipTypeInit))&& ("4".equals(shipTypeInit))){
				returnStr += "发货作废的发货单"+pdSendInfo.getSiNo()+"处于锁定状态，不允许修改！";
				return returnStr;
			}
			String whetherStockInit = pdSendInfo.getWhetherStock();
			//已备货的发货单处于锁定状态，不允许修改！
			if((!StringUtil.isEmpty(whetherStockInit))&& ("Y".equals(whetherStockInit))){
				returnStr += "已备货的发货单"+pdSendInfo.getSiNo()+"处于锁定状态，不允许修改！";
				return returnStr;
			}
			//modify by fu 2016-1-22-----end
			
			if(pdSendInfo.getOrderFlag()<2){
				//如果为空的数据，默认不修改
				if(!isBlank(recipientCaNo)){
					if(provinceMap.containsKey(recipientCaNo)){
						b1 = true;
					}else{
						returnStr += "第二列：省份编码不存在:"+recipientCaNo.trim()+"； --此行修改失败！";
						return returnStr;
					}				
				}
				if(!isBlank(city)){
					AlCity ac = pdSendInfoManager.getAlCity(Long.parseLong(city.trim())); 
					if(ac==null){
						returnStr += "第三列：城市编码不存在:"+city.trim()+"； --此行修改失败！ ";
						return returnStr;
					}else{
						if(!recipientCaNo.trim().equals(String.valueOf(ac.getAlStateProvince().getStateProvinceId()))){
							returnStr += "第三列：省编码："+recipientCaNo+"与市编码："+city+"不匹配； --此行修改失败！ ";
							return returnStr;
						}else{
							b2 = true;
						}
					}  
				}
				if(!isBlank(countryCode)){ 
					if(companyList.contains(countryCode.trim())){
						b3 = true;
					}else{
						returnStr += "第四列：分区编码不存在:"+countryCode.trim()+"； --此行修改失败！ ";
						return returnStr;
					}
				}

				if(!isBlank(warehouseNo)){
					if(pdWarehouses.contains((warehouseNo.trim()))){
						b4 = true;
					}else{
						returnStr += "第九列：仓库编号:"+warehouseNo+"不存在； --此行修改失败！"; 
						return returnStr;
					}
				} 
				if(!isBlank(shNo)){
					if(shNoList.contains(shNo.trim())){
						b5 = true;
					}else{
						returnStr += "第十列：物流公司编号:"+shNo+"不存在； --此行修改失败！";
						return returnStr;
					}
				}
				if(!isBlank(orderFlag)){
					if("-1".equals(orderFlag) || "0".equals(orderFlag) || "1".equals(orderFlag)){
						b6 = true;
					}else{
						returnStr += "第十一列：发货状态只能填写三种状态：-1、0、1 --此行修改失败！";
						return returnStr;
					}
				}
				
			    //modify by fu 2016-07-13 暂停发货原因-----------------begin
				if(!isBlank(suspendShipment)){
					if("1".equals(suspendShipment.trim()) || "2".equals(suspendShipment.trim()) ||"3".equals(suspendShipment.trim()) ){
						b8 = true;
					}else{
						returnStr += "第十三列：暂停发货原因只能填写三种值：1、2、3 --此行修改失败！";
						return returnStr;
					}
				}
			    //modify by fu 2016-07-13 暂停发货原因-----------------begin

				if(!isBlank(shipType)){
					if("0".equals(shipType.trim()) || "2".equals(shipType.trim()) ||"3".equals(shipType.trim()) ||  "4".equals(shipType.trim())){//Modify By fu 20151022 判断如果是数字，就修改状态
						b7 = true;
						//modify by fu 2016-07-13 恢复发货时暂停原因的校验-------begin
                        if("0".equals(shipType)){
	                        	String suspendShipmentDBA = pdSendInfo.getSuspendShipment();
	                        	if("1".equals(suspendShipmentDBA)){
	                        		returnStr += "第十二列：发货单"+pdSendInfo.getSiNo()+"因退货而暂停发货，故不能进行恢复发货！";
	                        		return returnStr;
	                        	}else if("2".equals(suspendShipmentDBA)){
	                        		returnStr += "第十二列：发货单"+pdSendInfo.getSiNo()+"因换货而暂停发货，故不能进行恢复发货！";
	                        		return returnStr;
	                        	}else if("3".equals(suspendShipmentDBA)){
	                        		returnStr += "第十二列：发货单"+pdSendInfo.getSiNo()+"因定制床垫而暂停发货，故不能进行恢复发货！";
	                        		return returnStr;
	                        	}
                        }
						//modify by fu 2016-07-13 恢复发货添加暂停原因的校验-------end
					}else{
						returnStr += "第十二列：发货方式只能填写三种值：0、2、3、4 --此行修改失败！";
						return returnStr;
					}
				}
				
				
				if("".equals(returnStr)){
					if(b1){
						pdSendInfo.setRecipientCaNo(recipientCaNo.trim());
					}
					if(b2){
						pdSendInfo.setCity(city.trim());
					}
					if(b3){
						pdSendInfo.setCountryCode(countryCode.trim());
					}
					if(!isBlank(recipientAddr)){
						pdSendInfo.setRecipientAddr(recipientAddr.trim());
					}
					if(!isBlank(recipientName)){
						pdSendInfo.setRecipientName(recipientName.trim());
					}
					if(!isBlank(recipientPhone)){
						pdSendInfo.setRecipientPhone(recipientPhone.trim());
					}
					if(!isBlank(recipientMobile)){
						pdSendInfo.setRecipientMobile(recipientMobile.trim());
					}
					
					
					if(b4){
						pdSendInfo.setWarehouseNo(warehouseNo.trim());
					}
					if(b5){
						pdSendInfo.setShNo(shNo.trim());
					}
					if(b6){
						pdSendInfo.setOrderFlag(Integer.parseInt(orderFlag.trim()));
						if("1".equals(orderFlag.trim())){
							pdSendInfo.setCheckTime(new Date());
							pdSendInfo.setCheckUsrCode(userCode);
							pdSendInfo.setCheckRemark("批量上传审核！");
						}
					} 
					if(b7){
						pdSendInfo.setShipType(shipType.trim());
					}
					
					 //modify by fu 2016-07-13 暂停发货原因-----------------begin
					if(b8){
						pdSendInfo.setSuspendShipment(suspendShipment.trim());
					}
					 //modify by fu 2016-07-13 暂停发货原因-----------------end
					
					if(!isBlank(remark)){
						pdSendInfo.setRemark((pdSendInfo.getRemark()==null || "".equals(pdSendInfo.getRemark()) 
								|| "null".equals(pdSendInfo.getRemark()) ? "" : pdSendInfo.getRemark())+"|"+remark);
					}
					
					//modify fu 2015-12-09 DO单存在的发货单不让修改，给出正确的提示----begin
					boolean haveDo = pdLogisticsBaseManager.getDoResult(pdSendInfo.getSiNo());
					String orderNo = pdSendInfo.getOrderNo();
					boolean noMo = true;
					 if(!StringUtil.isEmpty(orderNo)){
						 String mo = orderNo.substring(0, 2);
						 if("M0".equals(mo)){
							 noMo = false;
						 }
					 }
					String ShipmentHaveDo = pdSendInfo.getShipmentIdentificationNumber();
					//订单关联的发货单有DO单  或  发货退回单和换货单生成的发货单有发货状态回传---这两种情况不允许对发货单进行编辑
					if(haveDo || (noMo && "haveDo".equals(ShipmentHaveDo))){
						returnStr += "发货单号"+pdSendInfo.getSiNo()+"对应的DO单存在，故不允许修改发货单 --此行修改失败！";
					}
					//modify fu 2015-12-09 DO单存在的发货单不让修改，给出正确的提示----end
					
					 //在发货单编辑之前去WMS询问发货单能否编辑----begin modify by fu 2016-04-20
					String methodNameWMS = "checkstatus";
					String typeWMS = "11";
					String canUpdate = pdWarehouseStockManager.getPdLogisticsCanModify(pdSendInfo.getSiNo(), typeWMS, methodNameWMS);
					if((!StringUtil.isEmpty(canUpdate))&&(!"allow".equals(canUpdate))){
						if("notAllow".equals(canUpdate)){
							returnStr += "发货单号"+pdSendInfo.getSiNo()+"对应的DO单存在，故不允许修改发货单 --此行修改失败！";
						}else{
							//modify by fu 20160627 发货单作废功能优化（接口询问机制，如果WMS放回XXX发货单已撤单，那么EC就将对应的发货单设置成发货作废状态）--begin
							String cdbz = "发货单已撤单";//WMS撤单标志
							if(!StringUtil.isEmpty(cdbz)){
								int cd = canUpdate.indexOf(cdbz);
								if(cd!=(-1)){
									pdSendInfoManager.reSetPdSendInfoShipTypeFour(pdSendInfo.getSiNo());
									returnStr += "发货单号"+pdSendInfo.getSiNo()+" 因WMS系统发货单已撤单，故EC将发货单设置为发货作废！发货作废设置成功!";
									return returnStr;
								}
							}
							//modify by fu 20160627 发货单作废功能优化（接口询问机制，如果WMS放回XXX发货单已撤单，那么EC就将对应的发货单设置成发货作废状态）--end
							
							returnStr += "发货单号"+pdSendInfo.getSiNo()+" "+canUpdate;
						}
					}
				    //在发货单编辑之前去WMS询问发货单能否编辑----end modify by fu 2016-04-20
					
					
					pdSendInfoManager.savePdSendInfo(pdSendInfo);
				}else{
					returnStr += "   --此行修改失败！";
				} 
			}else{
				returnStr += "发货单状态为未提交(-1)、未审核(0)、已审核(1)的才允许修改 --此行修改失败！";
			}
		}else{
			returnStr = "修改的发货单号不存在！   --此行修改失败！";
		} 
		return returnStr;
	}	 
	
	/**
	 * @throws Exception 
	 * Modify By WuCF 20160330 批量上传订单套餐数据配置关系
	 * @author modifu fu 2016-1-8 
	 * @param 
	 * @param 
	 * @throws 
	 */
	private String uploadJpmcomBinationRelated(Cell[] column,String userCode,JpmCombinationRelated jpmCombinationRelated) throws Exception{
		String returnStr = "1";//每行返回信息   返回1时 表示通过
		
		String productNo = "";//套餐编码
		String productName = "";//套餐名称
	    String productDate = "";//期别
		String subProductNo = "";//子商品编码
		String subProductName = "";//子商品名称
		String qty = "";//数量
		String price = "";//单价
		String pv = "";//单PV
		String totalPrice = "";//总价
		String totalPv = "";//总PV
		String isFree = "";//是否赠品
		
		//获取值对象
		if(column.length>=1){
			productNo = column[0].getContents();
	    }
		
		if(column.length>=2){
			productName = column[1].getContents();
	    }
		
		if(column.length>=3){
			productDate = column[2].getContents();
	    }
		
		if(column.length>=4){
			subProductNo = column[3].getContents();
	    }
		
		if(column.length>=5){
			subProductName = column[4].getContents();
	    }
		
		if(column.length>=6){
			qty = column[5].getContents();
	    }
		
		if(column.length>=7){
			price = column[6].getContents();
	    }
		
		if(column.length>=8){
			pv = column[7].getContents();
	    }
		
		if(column.length>=9){
			totalPrice = column[8].getContents();
	    }
		
		if(column.length>=10){
			totalPv = column[9].getContents();
	    }
		
		if(column.length>=11){
			isFree = column[10].getContents();
	    }
		
		System.out.println(productNo+"\t"+productName+"\t"+productDate+"\t"+subProductName+"\t"+subProductName+"\t"+qty
					+"\t"+price+"\t"+pv+"\t"+totalPrice+"\t"+totalPv+"\t"+isFree);
		
		//数据处理
		if(MeteorUtil.isBlank(isFree)){
			isFree = "0";
		}else{
			isFree = "1";
		}
		
		
		String content = " ([ " + productNo + ","+productName+","+productDate+","+subProductNo+","+subProductName+" ])";
        String tempMessage = "";
		
        if(MeteorUtil.isBlank(productNo)){
        	tempMessage += "套餐代码不能为空,";
		}else if(!MeteorUtil.isStrLen(productNo, 30)){
			tempMessage += "套餐代码超长,";
		}
        
        if(MeteorUtil.isBlank(productName)){
        	tempMessage += "套餐名称不能为空,";
		}else if(!MeteorUtil.isStrLen(productName, 200)){
			tempMessage += "套餐名称超长,";
		}
        
        if(MeteorUtil.isBlank(productDate)){
        	tempMessage += "套餐时段不能为空,";
		}else if(!MeteorUtil.isStrLen(productDate, 20)){
			tempMessage += "套餐时段超长,";
		}
		
        if(MeteorUtil.isBlank(subProductNo)){
        	tempMessage += "子商品编码不能为空,";
		}else if(!MeteorUtil.isStrLen(subProductNo, 20)){
			tempMessage += "子商品编码超长,";
		}
        
        if(MeteorUtil.isBlank(subProductName)){
        	tempMessage += "子商品名称不能为空,";
		}else if(!MeteorUtil.isStrLen(subProductName, 200)){
			tempMessage += "子商品名称超长,";
		}
        
        if(!MeteorUtil.isInteger(qty) || Integer.valueOf(qty)<0){
        	tempMessage += "数量必须为正整数,";
		}
        
        if(!MeteorUtil.is2xiaoshu(price) || Float.valueOf(price)<0){
        	tempMessage += "价格最多两位小数且大于0,";
		}
        
        if(!MeteorUtil.is2xiaoshu(pv) || Float.valueOf(pv)<0){
        	tempMessage += "PV最多两位小数且大于0,";
		}
		
        if(!MeteorUtil.is2xiaoshu(totalPrice) || Float.valueOf(totalPrice)<0){
        	tempMessage += "总金额最多两位小数且大于0,";
		}
        
        if(!MeteorUtil.is2xiaoshu(totalPv) || Float.valueOf(totalPv)<0){
        	tempMessage += "总PV最多两位小数且大于0,";
		}

        //		JpmCombinationRelated jpmCombinationRelated = new JpmCombinationRelated();
		
		if(!MeteorUtil.isBlank(tempMessage)){//说明该条数据验证不通过
			tempMessage = tempMessage.substring(0,tempMessage.length()-1);
			returnStr = "<font color=red>"+ LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + tempMessage + content + "</font>";
		}else{
//			根据套餐代码，子商品代码，期别进行查询，如果已经有此数据，视为修改
			JpmCombinationRelated s = null;
			List<JpmCombinationRelated> jpmCombinationRelatedList = jpmCombinationRelatedManager.getJpmCombinationRelateds(productNo, productDate, subProductNo);
			if(!MeteorUtil.isBlankByList(jpmCombinationRelatedList)){
				s = jpmCombinationRelatedList.get(0);
			}
			if(null!=s){
				BeanUtils.copyProperties(jpmCombinationRelated, s);
			}
			
			jpmCombinationRelated.setProductNo(productNo);
			jpmCombinationRelated.setProductName(productName);
			jpmCombinationRelated.setProductDate(productDate);
			jpmCombinationRelated.setSubProductNo(subProductNo);
			jpmCombinationRelated.setSubProductName(subProductName);
			jpmCombinationRelated.setQty(Integer.parseInt(qty));
			jpmCombinationRelated.setPrice(new BigDecimal(price));
			jpmCombinationRelated.setPv(new BigDecimal(pv));
			jpmCombinationRelated.setTotalPrice(new BigDecimal(totalPrice));
			jpmCombinationRelated.setTotalPv(new BigDecimal(totalPv));
			jpmCombinationRelated.setIsFree(isFree);
			if(null==jpmCombinationRelated.getUniNo()){
				jpmCombinationRelated.setCreateTime(new Date());
				jpmCombinationRelated.setCreateUserCode(userCode);
			}
			
		}
		
		return returnStr;
	}	 
	
	
	/**
	 * 修改货仓库物流状态
	 * @param column
	 * @param sessionLogin
	 * @throws Exception
	 */
	private String updateStorageCordon(Cell[] column,List companyList){
		String returnStr = "";
		String companyCode = column[0].getContents();//地区编码
		String productNo = column[1].getContents();//商品编号
		String storageCordon = column[2].getContents();//库存量
		log.info("======="+companyCode+" "+productNo+" "+storageCordon);
		
		if(!isBlank(companyCode)){
			if(!companyList.contains(companyCode.trim())){
				returnStr += "第一列：地区编号:"+companyCode+"不存在；";
				return returnStr;
			}
		}
		//商品编号不用判断，直接修改即可，如果返回值为0，标示没有修改的编号，提示即可！
		if(!isBlank(productNo)){
//			returnStr += "第三列：商品编号:"+productNo+"不存在；";
		}
		if(!isBlank(storageCordon)){
			if(!StringUtils.isNumeric(storageCordon)){
				returnStr += "第四列：库存量:"+storageCordon+"不是数字；";
				return returnStr;
			}
		} 
		if("".equals(returnStr)){
			int returnNum = pdSendInfoManager.updateStorageCordon(companyCode.trim(),productNo.trim(),storageCordon.trim()); 
			if(returnNum==0){
				returnStr = "   --此行修改失败，可能是对应的地区的商品编号不存在！";
			}
		}else{
			returnStr += "   --此行修改失败！";
		} 
		return returnStr;
	}	
	
	/**
	 * 判断指定字符串是否为空  
	 * @param str
	 * @return：false
	 * D:\workspace\joymain\02 源代码\jecs\src\web\com\joymain\jecs\pd\webapp\action\PdFileUpload2Controller.java:221: 找不到符号
    [javac] 符号： 变量 StringUtils
	 */
	public static boolean isBlank(String str){
		boolean bool = true;
		if(str!=null && !"".equals(str.trim().replace(" ", ""))){
			bool = false;
		}	
		return bool;
	}
	
	/**
	 * 判断是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
}
