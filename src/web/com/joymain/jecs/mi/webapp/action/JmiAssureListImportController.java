package com.joymain.jecs.mi.webapp.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.mi.model.JmiAssure;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiAssureManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiAssureListImportController extends BaseFormController {

	private JmiMemberManager jmiMemberManager = null;
	private JmiAssureManager jmiAssureManager = null;

	public void setJmiAssureManager(JmiAssureManager jmiAssureManager) {
		this.jmiAssureManager = jmiAssureManager;
	}

	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	public JmiAssureListImportController() {
		setCommandName("jmiAssure");
		setCommandClass(JmiAssure.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		JmiAssure jmiAssure = new JmiAssure();
		return jmiAssure;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws Exception {

		try {
			SysUser sessionLogin = SessionLogin.getLoginUser(request);
			String companyCode = sessionLogin.getCompanyCode();
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("importExcelFile");
			if (file == null || file.getInputStream() == null) {
				// 文件读取错误
				errors.reject("bdBounsDeduct.importFile.failed");
				return showForm(request, response, errors);
			}
			String uploadDir = getServletContext().getRealPath("/WEB-INF") + "/xls/" + SessionLogin.getLoginUser(request).getUserCode() + "/";
			// 设置上传路径，如果路径不存在则创建
			File dirPath = new File(uploadDir);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}
			
			InputStream stream = file.getInputStream();
			ExcelUtil eu = new ExcelUtil();
			// 获取可读的工作表对象，定位到要读取的excel文件
			Workbook workbook = eu.getWorkbook(stream);
			// 读取此文件的第一个工作表，工作表序号从0开始。
			Sheet sheet = workbook.getSheet(0);

			List<String> messages = new ArrayList<String>();
			int errCount = 0;
			// 从第2行开始读,第一行为标题
			messages.add(LocaleUtil.getLocalText("bdBounsDeduct.impot.skip.first"));

			List<JmiAssure> jmiAssurelList = new ArrayList<JmiAssure>();
			for (int i = 1; i < sheet.getRows(); i++) {
				int lineNum = i+1;
				JmiAssure jmiAssure = new JmiAssure();
				String assureType = eu.getContents(sheet, 0, i);		//担保类型
				String assureContent = eu.getContents(sheet, 1, i);		//担保内容
				String userCode = eu.getContents(sheet, 2, i);			//担保编号
				String assureOrderCode = eu.getContents(sheet, 3, i);	//担保订单编号
				String sstartTime = eu.getContents(sheet, 4, i);		//业务开始时间
				String sendTime = eu.getContents(sheet, 5, i);			//业务结束时间
				String isFlag = eu.getContents(sheet, 6, i);			//是否达成
				String memo = eu.getContents(sheet, 7, i);				//备注
				
				//1、判断担保类型的值
				if(StringUtil.isEmpty(assureType)){
					messages.add("<font color=red>第 "+ lineNum + " 行担保类型不能为空 [ " + assureType + " ] </font>");
					errCount++;
					continue;
				}
				//2、判断担保类型是否存在
				LinkedHashMap<String, String> listOptions = ListUtil.getListOptions(companyCode, "assure_type");
				if(listOptions == null || listOptions.size()<1) {
					messages.add("<font color=red>第 "+ lineNum + " 行系统未配置担保类型列表参数值 [ " + assureType + " ] </font>");
					errCount++;
					continue;
				}
				int isAssureTypeEquals = 0;
				for(String key : listOptions.keySet()) {
					if(assureType.equals(key)) {
						isAssureTypeEquals = 1;
						break;
					}
				}
				//3、判断担保类型的值是否在配置的列表参数列表里
				if(isAssureTypeEquals != 1) {
					messages.add("<font color=red>第 "+ lineNum + " 行担保类型的值不存在 [ " + assureType + " ] </font>");
					errCount++;
					continue;
				}
				//4、判断担保内容是否存在列表里
				if(StringUtil.isEmpty(assureContent)){
					messages.add("<font color=red>第 "+ lineNum + " 行担保内容不能为空 [ " + assureContent + " ] </font>");
					/*messages.add("<font color=red>"+ message
							+ LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
							+ " - " + "担保类型 [ " + assureType + " ] 对应的担保内容不能为空 [ " + assureContent + " ] </font>");*/
					errCount++;
					continue;
				}
				if("1".equals(assureType)) {
					listOptions = ListUtil.getListOptions(companyCode, "assure_content_wjf");	//无纠纷担保内容分组
				} else if("2".equals(assureType)) {
					listOptions = ListUtil.getListOptions(companyCode, "assure_content_yj");	//业绩担保内容分组
				} else if("3".equals(assureType)) {
					listOptions = ListUtil.getListOptions(companyCode, "assure_content_xbwl");	//下不为例担保内容分组
				} else if("4".equals(assureType)) {
					listOptions = ListUtil.getListOptions(companyCode, "assure_content_bth");	//不退货担保内容分组
				}
				int isAssureContentEquals = 0;
				for(String key : listOptions.keySet()) {
					if(assureContent.equals(key)) {
						isAssureContentEquals = 1;
						break;
					}
				}
				if(isAssureContentEquals != 1) {
					messages.add("<font color=red>第 "+ lineNum + 
							" 行担保内容的值 [ " + assureContent + " ] 和担保类型 [ "+assureType+" ] 无法对应</font>");
					/*messages.add("<font color=red>"+ message
							+ LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
							+ " - " + "担保内容的值 [ " + assureContent + " ] 和担保类型 [ "+assureType+" ] 无法对应</font>");*/
					errCount++;
					continue;
				}
				//5、判断担保编号是否存在
				if(StringUtil.isEmpty(userCode)){
					messages.add("<font color=red>第 "+ lineNum + " 行担保编号不能为空 [ " + userCode + " ] </font>");
					/*messages.add("<font color=red>"+ message
							+ LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
							+ " - " + "担保类型 [ " + assureType + " ] 对应的担保编号不能为空 [ " + userCode + " ] </font>");*/
					errCount++;
					continue;
				} 
				JmiMember jmimember = jmiMemberManager.getJmiMember(new String(userCode));
				if(null == jmimember) {
					messages.add("<font color=red>第 "+ lineNum + " 行担保编号不存在 [ " + userCode + " ] </font>");
					/*messages.add("<font color=red>"+ message
							+ LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
							+ " - " + "担保类型 [ " + assureType + " ] 对应的担保编号不存在 [ " + userCode + " ] </font>");*/
					errCount++;
					continue;
				}
				//6、如果担保类型不是 不退货担保，需判断业务开始时间是否为空
				if(!"4".equals(assureType)) {
					//判断是否为合法的日期格式
					Date startTime = validateStartAndEndTime(sstartTime);
					if(null == startTime) {
						messages.add("<font color=red>第 "+ lineNum + " 行业务开始时间格式错误 [ " + sstartTime + " ] </font>");
						/*messages.add("<font color=red>"+ message
								+ LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
								+ " - " + "担保类型 [ " + assureType + " ] 对应的业务开始时间格式错误 [ " + sstartTime + " ] </font>");*/
						errCount++;
						continue;
					}
					Date endTime = null;
					if(!StringUtils.isEmpty(sendTime)) {
						//不等于空的时候要做判断
						endTime = validateStartAndEndTime(sendTime);
						if(null == endTime) {
							messages.add("<font color=red>第 "+ lineNum + " 行业务截止时间格式错误 [ " + sendTime + " ] </font>");
							/*messages.add("<font color=red>"+ message
									+ LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
									+ " - " + "担保类型 [ " + assureType + " ] 对应的业务截止时间格式错误 [ " + sendTime + " ] </font>");*/
							errCount++;
							continue;
						}
					}
					if("2".equals(assureType) && endTime == null) {
						//业绩担保需要判断截止时间是否为空
						messages.add("<font color=red>第 "+ lineNum + " 行业务截止时间格式错误 [ " + sendTime + " ] </font>");
						/*messages.add("<font color=red>"+ message
								+ LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
								+ " - " + "担保类型 [ " + assureType + " ] 对应的业务截止时间格式错误 [ " + sendTime + " ] </font>");*/
						errCount++;
						continue;
					}
					// 业务开始时间和业务结束时间都有值的话，判断大小
					if(startTime != null && endTime != null) {
						if (startTime.getTime() > endTime.getTime()) {
							messages.add("<font color=red>第 "+ lineNum + 
									" 行业务开始时间不能大于业务截止时间,（开始时间="+sstartTime + ", 截止时间="+sendTime + "）</font>");
							/*messages.add("<font color=red>"+ message
									+ LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
									+ " - " + "担保类型 [ " + assureType + " ] 对应的业务开始时间不能大于业务截止时间,（开始时间="+sstartTime + ", 截止时间="+sendTime + "）</font>");*/
							errCount++;
							continue;
				        } 
					}
					jmiAssure.setStartTime(startTime);
					// 1、和 3 担保类型的截止时间是允许为空的，会导致在导出报表的时候漏掉这条数据
					// 所以截止时间默认在开始时间的基础上加1年
					jmiAssure.setEndTime(endTime==null?DateUtil.getDateOffset(startTime, Calendar.YEAR, 1):endTime);
					
				} else {
					//7、如果担保类型是《不退货担保》，需要判断担保订单编号是否存在
					if(StringUtil.isEmpty(assureOrderCode)){
						messages.add("<font color=red>第 "+ lineNum + " 行担保订单编号不能为空 [ " + assureOrderCode + " ] </font>");
						/*messages.add("<font color=red>"+ message
								+ LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
								+ " - " + "担保类型 [ " + assureType + " ] 对应的担保订单编号不能为空 [ " + assureOrderCode + " ] </font>");*/
						errCount++;
						continue;
					}
					if(assureOrderCode.length() > 1000){
						messages.add("<font color=red>第 "+ lineNum + " 行担保订单编号过长 [ " + assureOrderCode + " ] </font>");
						/*messages.add("<font color=red>"+ message
								+ LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
								+ " - " + "担保类型 [ " + assureType + " ] 对应的担保订单编号过长 [ " + assureOrderCode + " ] </font>");*/
						errCount++;
						continue;
					}
					String []orderCodes = assureOrderCode.split(",");
					int orderFlag = 0;//用于记录多条订单中是否有不合法的订单
					w:for(String orderCode : orderCodes) {
						JpoMemberOrder order = jmiAssureManager.getJpoMemberOrderByInterface(orderCode);
						if(null == order) {
							orderFlag = 1;
							break w;
						}
					}
					if(orderFlag == 1) {
						messages.add("<font color=red>第 "+ lineNum + " 行担保订单编号不存在或者订单状态不合法 [ " + assureOrderCode + " ] </font>");
						/*messages.add("<font color=red>"+ message
								+ LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
								+ " - " + "担保类型 [ " + assureType + " ] 对应的担保订单编号不存在或者订单状态不合法 [ " + assureOrderCode + " ] </font>");*/
						errCount++;
						continue;
					}
				}
				//8、判断是否达成担保
				if(StringUtils.isEmpty(isFlag)) {
					messages.add("<font color=red>第 "+ lineNum + " 行是否达成担保不能为空 [ " + isFlag + " ] </font>");
					/*messages.add("<font color=red>"+ message
							+ LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
							+ " - " + "担保类型 [ " + assureType + " ] 对应的是否达成担保不能为空 [ " + isFlag + " ] </font>");*/
					errCount++;
					continue;
				}
				listOptions = ListUtil.getListOptions(companyCode, "assure_is_flag");	//是否达成担保分组
				int isFlagEquals = 0;
				for(String key : listOptions.keySet()) {
					if(isFlag.equals(key)) {
						isFlagEquals = 1;
						break;
					}
				}
				if(isFlagEquals != 1) {
					messages.add("<font color=red>第 "+ lineNum + " 行是否达成担保的值不合法[ " + isFlag + " ] </font>");
					/*messages.add("<font color=red>"+ message
							+ LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
							+ " - " + "担保类型 [ " + assureType + " ] 对应的是否达成担保的值未配置 [ " + isFlag + " ] </font>");*/
					errCount++;
					continue;
				}
				//9、判断备注长度
				if(!StringUtil.isEmpty(memo)){
					int length = MeteorUtil.length(memo);
					if(length > 200) {
						messages.add("<font color=red>第 "+ lineNum + " 行备注长度超过200字符的限制");
						/*messages.add("<font color=red>"+ message
								+ LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
								+ " - " + "担保类型 [ " + assureType + " ] 对应的备注长度超过200字符的限制</font>");*/
						errCount++;
						continue;
					}
				}
				jmiAssure.setAssureType(assureType);
				jmiAssure.setAssureContent(assureContent);
				jmiAssure.setUserCode(userCode);
				jmiAssure.setAssureOrderCode(assureOrderCode);
				jmiAssure.setIsFlag(isFlag);
				jmiAssure.setMemo(memo);
				jmiAssure.setCreateTime(new Date());
				jmiAssure.setUpdateTime(new Date());
				jmiAssure.setCreateUserCode(sessionLogin.getUserCode());
				jmiAssure.setUpdateUserCode(sessionLogin.getUserCode());
				jmiAssurelList.add(jmiAssure);
			}
			eu.closeWorkbook(workbook);

			if (errCount == 0 && !jmiAssurelList.isEmpty()) {
				jmiAssureManager.saveJmiAssureList(jmiAssurelList);
				this.saveMessage(request, "批量导入成功");
				return new ModelAndView(this.getSuccessView());
			}
			request.setAttribute("messages", messages);
			request.setAttribute("isFinished", false);
			request.setAttribute("errCount", errCount);
			String formView = this.getFormView();
			return new ModelAndView(formView);
			
		} catch (IOException e) {
			this.saveMessage(request,getText("bdBounsDeduct.importFile.failed"));
			log.error(e.getMessage());
		} catch (Exception e) {
			this.saveMessage(request,getText("bdBounsDeduct.import.data.error"));
			log.error(e.getMessage());
		}
		return new ModelAndView(this.getSuccessView());
	}
	
	public Date validateStartAndEndTime(String time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		Date result = null;
		try {
			result = format.parse(time);
			return result;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
}
