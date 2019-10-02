package com.joymain.jecs.pd.webapp.action;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.pd.model.PdSendInfo;
import com.joymain.jecs.pd.service.PdLogisticsBaseManager;
import com.joymain.jecs.pd.service.PdSendInfoManager;
import com.joymain.jecs.pm.model.JpmSmssendInfo;
import com.joymain.jecs.pm.service.JpmProductSaleNewManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.action.FileUpload;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.webapp.util.SmsSend;



public class PdFileUploadController extends BaseFormController {
	PdSendInfoManager pdSendInfoManager = null;
	private JpmProductSaleNewManager jpmProductSaleNewManager = null;
	private PdLogisticsBaseManager pdLogisticsBaseManager;    

	 public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}

	 public void setJpmProductSaleNewManager(
			 JpmProductSaleNewManager jpmProductSaleNewManager) {
		 this.jpmProductSaleNewManager = jpmProductSaleNewManager;
	 }
	 
	 public void setPdLogisticsBaseManager(
				PdLogisticsBaseManager pdLogisticsBaseManager) {
			this.pdLogisticsBaseManager = pdLogisticsBaseManager;
		}
	 
	public PdFileUploadController() {
	        setCommandName("fileUpload");
	        setCommandClass(FileUpload.class);
	    }
	    
	    public ModelAndView processFormSubmission(HttpServletRequest request,
	                                              HttpServletResponse response,
	                                              Object command,
	                                              BindException errors)
	    throws Exception {
	        if (request.getParameter("cancel") != null) {
	            return new ModelAndView(getCancelView());
	        }
	        if(RequestUtil.freshSession(request, "updateTrackingNo", 60L)>0){
	        	return new ModelAndView("redirect:pdFileUpload.html?strAction=updateTrackingNo");
	        }

	        return super.processFormSubmission(request, response, command, errors);
	    }

	    public ModelAndView onSubmit(HttpServletRequest request,
	                                 HttpServletResponse response, Object command,
	                                 BindException errors)
	    throws Exception {
	    	if(RequestUtil.saveOperationSession(request, "updateTrackingNo", 60L)>0){
	        	return new ModelAndView("redirect:pdFileUpload.html?strAction=updateTrackingNo");
			}
	    	
	    	List ret=new ArrayList();
	        /*FileUpload fileUpload = (FileUpload) command;
	        SysUser sessionLogin = SessionLogin.getLoginUser(request);
	        String strAction = request.getParameter("strAction");
	        if("updateTrackingNo".equals(strAction)){
	        	updateTrackingNo(request,response);
			}
	        // validate a file was entered
	        if (fileUpload.getFile().length == 0) {
	            Object[] args = 
	                new Object[] { getText("uploadForm.file","uploadForm.file", request.getLocale()) };
	            errors.rejectValue("file", "errors.required", args, "File");
	            
	            return showForm(request, response, errors);
	        }

	        MultipartHttpServletRequest multipartRequest =
	            (MultipartHttpServletRequest) request;
	        CommonsMultipartFile file =
	            (CommonsMultipartFile) multipartRequest.getFile("file");
	        
	        InputStream stream = file.getInputStream();
	        String confirmFlag = ConfigUtil.getConfigValue(sessionLogin.getCompanyCode(), "pd.auto.confirm"); 
	        
	        Workbook wb = Workbook.getWorkbook(stream);
	        
	        Sheet sheet1 = wb.getSheet(0); 
	        
//	        Cell cell = null;
	        int row = sheet1.getRows();//总行数
	        int col = sheet1.getColumns();//总列数
	        for(int i=1;i<row;i++){
	        	Cell[] column =sheet1.getRow(i);
	        	try {
					saveTrackingNo(column);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					log.info("e="+e.getMessage());
					ret.add(i+1);
				}

	        }*/
	    	log.info("upload...");
	        ret = this.updateTrackingNo(request, response);
//	        JMSContextUtil.getJMSSender("jmsTimeSender").sendObjecsMessage("time");
	        log.info("upload end...");
	        request.setAttribute("errors", ret);
	        return new ModelAndView(getSuccessView());
	    }

		private List updateTrackingNo(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			// TODO Auto-generated method stub
			log.info("upload file.......................");
			
			List ret=new ArrayList();
			SysUser sessionLogin = SessionLogin.getLoginUser(request);
			MultipartHttpServletRequest multipartRequest =
	            (MultipartHttpServletRequest) request;
	        CommonsMultipartFile file =
	            (CommonsMultipartFile) multipartRequest.getFile("file");
	        
	        InputStream stream = file.getInputStream();
	        String confirmFlag = ConfigUtil.getConfigValue(sessionLogin.getCompanyCode(), "pd.auto.confirm"); 
	        
	        //write the file to the file specified
	        DateFormat df = DateFormat.getDateInstance();
//	        
//	        OutputStream bos =
//	            new FileOutputStream(getServletContext().getRealPath("/traceno")+"/"+df.format(Calendar.getInstance().getTime())+"/" + file.getOriginalFilename());
//	        int bytesRead = 0;
//	        byte[] buffer = new byte[8192];
//
//	        while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
//	            bos.write(buffer, 0, bytesRead);
//	        }
//
//	        bos.close();
	        
	        Workbook wb = Workbook.getWorkbook(stream);
	        Sheet sheet1 = wb.getSheet(0); 
	        
	        Cell cell = null;
	        int row = sheet1.getRows();//总行数
	        log.info("row="+row);
	        
	        Integer listNum = Integer.parseInt(ConfigUtil.getConfigValue(sessionLogin.getCompanyCode(), "import.maxnum"));
	        
	        if(row>=listNum+2){
	        	ret.add(LocaleUtil.getLocalText("notice.row.number", "一次性上传数据不能超过"+listNum+"条！"));
	        	return ret;
	        }
	        
	        int col = sheet1.getColumns();//总列数
	        LinkedHashMap<String,String> shMap = ListUtil.getListOptions(sessionLogin.getCompanyCode().toUpperCase(), "pd.shno");
	        
	        String returnStr = "";
	        if("1".equals(confirmFlag)){
	        	for(int i=1;i<row;i++){
	        	   Cell[] column =sheet1.getRow(i);
	        	   //modify fu 2015-12-21 
	        	   if(column!=null && column[0].getContents()!=null && !"".equals(column[0].getContents())){
			        	
			        	try {
			        		returnStr = confirmPdSendInfo(column,sessionLogin,shMap);
			        		if(StringUtils.isNotEmpty(returnStr)){ 
								ret.add(LocaleUtil.getLocalText("notice.row.number", "第"+i+"行"+column[0]+"："+returnStr));
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
//		        	
		        }
	        }else{//未知做什么用的？？？？？
	        	for(int i=1;i<row;i++){
	 	        	Cell[] column =sheet1.getRow(i);
 	        		returnStr = saveTrackingNo(column,sessionLogin); 
					if(StringUtils.isNotEmpty(returnStr)){
						ret.add(LocaleUtil.getLocalText("notice.row.number", "第"+i+"行："+returnStr));
					}
	 	        }
	        }
	       
	        return ret;
			
		}
		
		/**
		 * Add By WuCF 20150424
		 * 判断是否包含字母，数字
		 * @param number
		 * @return 
		 */
		public String isNumberOrWord(String str){
		     Pattern pattern =Pattern.compile("[a-zA-Z]|\\d");
		     Matcher matcher = pattern.matcher(str);
		     while (matcher.find()) {
		    	 // 如匹配成功即走到这里
		    	 return matcher.group();
		     }
		     return "";
		}
		
		private String confirmPdSendInfo(Cell[] column,SysUser sessionLogin,LinkedHashMap<String,String> shMap) throws Exception{
			String returnStr = "";
			String siNo = column[0].getContents();//发货单号
			String traceNo = column[1].getContents();//跟踪单号
			String shNo = "";//物流公司编号
			String barCode = "";//条形码
			String okRemark = "";//备注
			if(column.length==3){//如果备注列不加数据，则不用读取第5列（column[4]），否则数组越界
				shNo = column[2].getContents();
			}else if(column.length==4){//物流公司编码、条形码
				shNo = column[2].getContents();
				barCode = column[3].getContents();
			}else if(column.length==5){//物流公司编码、条形码、备注
				shNo = column[2].getContents();
				barCode = column[3].getContents();
				okRemark = column[4].getContents();
			}
			// modify fu 2015-12-21 
			else if(column.length>=6){
				shNo = column[2].getContents();
				barCode = column[3].getContents();
				okRemark = column[4].getContents();
			}
			
			PdSendInfo pdSendInfo =pdSendInfoManager.getPdSendInfo(siNo.trim());
			boolean b1 = false; 
			boolean b2 = false; 
			if(pdSendInfo!=null){
				List list = null;
				if(!isBlank(traceNo)){
					b1 = true;
				}
				//如果为空的数据，默认不修改
				if(!isBlank(shNo)){
					if(shMap.containsKey(shNo.trim())){
						b2 = true;
					}else{
						returnStr += "第三列：物流公司编号:"+shNo+"不存在； --此行修改失败！"; 
						return returnStr;
					}
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
				boolean isOrNotHaveDo = (haveDo || (noMo && "haveDo".equals(ShipmentHaveDo)));
				//modify fu 2015-12-09 DO单存在的发货单不让修改，给出正确的提示----end
				
				if("".equals(returnStr)){
					if(b1){//修改跟踪号
						pdSendInfo.setTrackingNo(traceNo.trim());
					}
					if(b2){//修改物流公司
						
						if(pdSendInfo.getOrderFlag()<1 && isOrNotHaveDo){
							//未提交，未审核的发货单有DO单存在的情况下不让修改物流公司 modify fu 2015-12-23
						}else{
							pdSendInfo.setShNo(shNo.trim());
						}
					}
					if(!StringUtils.isEmpty(barCode) && !"null".equals(barCode)){//条形码
						pdSendInfo.setBarCode(barCode);
					}
					if(!StringUtils.isEmpty(okRemark) && !"null".equals(okRemark)){//备注
						pdSendInfo.setOkRemark(okRemark);
					}
//					pdSendInfoManager.savePdSendInfo(pdSendInfo);
					//获得修改之前的发货单状态
					Integer orderFlag = pdSendInfo.getOrderFlag();
					log.info("orderFlag:"+orderFlag);
					//发货确认
					pdSendInfoManager.confirmSendInfo(pdSendInfo, sessionLogin);
					
					try{
						//添加短信功能：Modify By WuCF 20140113				
						if (orderFlag ==1) {
							log.info("pdFileUploadController1:"+orderFlag);
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
								jpmSmssendInfo.setRemark("批量发货确认！仓库："+pdSendInfo.getWarehouseNo());//备注
								jpmSmssendInfo.setPhoneNum(mobilePhone);//手机号码
								jpmProductSaleNewManager.saveJpmSmssendInfo(jpmSmssendInfo);
							} 
						}else{
							log.info("pdFileUploadController2:"+orderFlag);
							pdSendInfoManager.savePdSendInfo(pdSendInfo);
							
							//modify fu 2015-12-09 DO单存在的发货单不让修改，给出正确的提示----begin
							
							//订单关联的发货单有DO单  或  发货退回单和换货单生成的发货单有发货状态回传---这两种情况不允许对发货单进行编辑
							if(haveDo || (noMo && "haveDo".equals(ShipmentHaveDo))){
								returnStr += "发货单号"+pdSendInfo.getSiNo()+"对应的DO单存在，故不允许修改发货单 --此行修改失败！";
							}
							//modify fu 2015-12-09 DO单存在的发货单不让修改，给出正确的提示----end
							
							
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}else{
				returnStr = "修改的发货单号不存在！   --此行修改失败！";
			}
			
			
		

			return returnStr;
		}
		 
		private String saveTrackingNo(Cell[] column,SysUser sessionLogin){
			String returnStr = "";
			String siNo = column[0].getContents();
			String traceNo = column[1].getContents();
			String shNo = "";
			if(column.length>=3){//如果备注列不加数据，则不用读取第5列（column[4]），否则数组越界
				shNo = column[2].getContents();;
			}
			
			PdSendInfo pdSendInfo =pdSendInfoManager.getPdSendInfo(siNo);
			boolean b1 = false; 
			boolean b2 = false; 
			if(pdSendInfo!=null){
				List list = null;
				if(!isBlank(traceNo)){
					b1 = true;
				}
				//如果为空的数据，默认不修改
				if(!isBlank(shNo)){
					if(checkShNoIsExist(shNo)){
						b2 = true;
					}else{
						returnStr += "第三列：物流公司编号:"+shNo+"不存在； --此行修改失败！"; 
						return returnStr;
					}
				} 
				
				if("".equals(returnStr)){
					if(b1){//修改跟踪号
//						String trackingTemp = pdSendInfo.getTrackingNo();
//						if(StringUtil.isEmpty(trackingTemp)){//直接覆盖
//							pdSendInfo.setTrackingNo(traceNo.trim());
//						}else{
//							pdSendInfo.setTrackingNo(trackingTemp+"</br>"+traceNo.trim());
//						}
						pdSendInfo.setTrackingNo(traceNo.trim());
					}
					if(b2){//修改物流公司
						pdSendInfo.setShNo(shNo.trim());
					}
//					SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy年MM月dd日");   
//					String dateStr = dateformat2.format(new Date());
//					String remark = pdSendInfo.getRemark()+"|批量修改物流公司和跟踪号("+dateStr+"#"+sessionLogin.getUserCode()+")。";
//					pdSendInfo.setRemark(remark);
					pdSendInfoManager.savePdSendInfo(pdSendInfo);
				}
			}else{
				returnStr = "修改的发货单号不存在！   --此行修改失败！";
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
		 * 物流公司编码是否存在
		 * @param shNo
		 * @return
		 */
		public boolean checkShNoIsExist(String shNo){
			boolean returnB = false;
			if( "UPS".equals(shNo) || 
				"0000".equals(shNo) || 
				"DTW".equals(shNo) || 
				"ZJS".equals(shNo) || 
				"STARS".equals(shNo) || 
				"ZTO".equals(shNo) || 
				"GUOTONG".equals(shNo) || 
				"XINJIE".equals(shNo) || 
				"YUNDA".equals(shNo) || 
				"ZHONGTIE".equals(shNo) || 
				"KEJIE".equals(shNo) || 
				"BEIZHI".equals(shNo) ||
				"EMS".equals(shNo) ||//2014-2-24
				"SHUNFENG".equals(shNo) ||
				"SHENTONG".equals(shNo) ||
				"HUITONG".equals(shNo) ||//2014-02-25
				"DEBANG".equals(shNo) ||
				"YUANTONG".equals(shNo) ||//2014-03-31 圆通物流
				"BAISHI".equals(shNo) ||//百世物流
				"BSHT".equals(shNo) ||//百世汇通
				"XINYITAI".equals(shNo)//新易泰20140610
				){
				returnB = true;
			}
			return returnB;
		}
}
