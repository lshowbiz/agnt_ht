package com.joymain.jecs.bd.webapp.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

import com.joymain.jecs.bd.model.BdBounsDeduct;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.model.JbdManualCon;
import com.joymain.jecs.bd.service.BdBounsDeductManager;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.bd.service.JbdManualConManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
public class JbdManualConImportController extends BaseFormController {
	private JmiMemberManager jmiMemberManager;
	private JbdManualConManager jbdManualConManager;
	private BdPeriodManager bdPeriodManager;
	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	public JbdManualConImportController() {
		setCommandName("bdBounsDeduct");
		setCommandClass(BdBounsDeduct.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		BdBounsDeduct bdBounsDeduct = new BdBounsDeduct();

		return bdBounsDeduct;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		BdBounsDeduct bdBounsDeduct = (BdBounsDeduct) command;

		if ("jbdManualConImport".equals(request.getParameter("strAction"))) {
			
			
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("importExcelFile");

				if (file == null || file.getInputStream() == null) {
					//文件读取错误
					errors.reject("bdBounsDeduct.importFile.failed");
					return showForm(request, response, errors);
				}
				// 设置上传路径

		        SysUser defSysUser = SessionLogin.getLoginUser(request);
				//retrieve the file data
				InputStream stream = file.getInputStream();

				ExcelUtil eu = new ExcelUtil();
				//获取可读的工作表对象，定位到要读取的excel文件
				Workbook workbook = eu.getWorkbook(stream);
				//读取此文件的第一个工作表，工作表序号从0开始。
				Sheet sheet = workbook.getSheet(0);

				List<String> messages = new ArrayList<String>();
				List<JbdManualCon> jbdManualCons = new ArrayList<JbdManualCon>();
				Date curDate=new Date();
				int totalCount = sheet.getRows() - 1;
				int errCount = 0;
				//从第2行开始读,第一行为标题
				messages.add(LocaleUtil.getLocalText("bdBounsDeduct.impot.skip.first"));
				for (int i = 1; i < sheet.getRows(); i++) {
					//会员编号,扣补类型,扣补金额,备注
					String userCode = eu.getContents(sheet, 0, i);
					String startWeek = eu.getContents(sheet, 1, i);
					String endWeek = eu.getContents(sheet, 2, i);
					
					String salesStatus = eu.getContents(sheet, 3, i);
					String consumerStatus = eu.getContents(sheet, 4, i);
					
					
					

					String content = " ([ " + userCode + " ] [ " + startWeek + " ] [ " + endWeek + " ] )";
					String message = (i + 1) + ": ";
					if (StringUtils.isEmpty(userCode)) {
						messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "会员编号不能为空" + content+"</font>");
						errCount++;
						continue;
					}
					JmiMember miMember = this.jmiMemberManager.getJmiMember(userCode.trim());
					if (miMember == null) {
						messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "会员不存在" + content+"</font>");
						errCount++;
						continue;
					}
					if(!StringUtil.isInteger(startWeek)){
						messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "开始期别必须为数字" + content+"</font>");
						errCount++;
						continue;
					}else{
						BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(WeekFormatUtil.getFormatWeek("f",startWeek));
						if(bdPeriod==null){
							messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "开始期别不存在" + content+"</font>");
							errCount++;
							continue;
						}
					}
					if(!StringUtil.isInteger(endWeek)){
						messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "结束期别必须为数字" + content+"</font>");
						errCount++;
						continue;
					}else{
						BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(WeekFormatUtil.getFormatWeek("f",endWeek));
						if(bdPeriod==null){
							messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "结束期别不存在" + content+"</font>");
							errCount++;
							continue;
						}
					}
					
					if(!"0".equals(salesStatus) && !"1".equals(salesStatus) ){
						messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "销售奖资格错误" + content+"</font>");
						errCount++;
						continue;
					}

					if(!"0".equals(consumerStatus) && !"1".equals(consumerStatus) ){
						messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "感恩奖资格" + content+"</font>");
						errCount++;
						continue;
					}
					
					
					
					
					
					JbdManualCon jbdManualCon = new JbdManualCon();
					jbdManualCon.setUserCode(userCode);
					jbdManualCon.setStartWeek(StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f",startWeek)));
					jbdManualCon.setEndWeek(StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f",endWeek)));
					jbdManualCon.setSalesStatus(StringUtil.formatInt(salesStatus));
					jbdManualCon.setConsumerStatus(StringUtil.formatInt(consumerStatus));
					jbdManualCon.setCreateNo(defSysUser.getUserCode());
					jbdManualCon.setCreateTime(curDate);
					
					jbdManualCons.add(jbdManualCon);
					

					//messages.add(message + LocaleUtil.getLocalText("bdBounsDeduct.import.OK") + content);
				}

				eu.closeWorkbook(workbook);
				
				if(errCount==0 && !jbdManualCons.isEmpty()){
					jbdManualConManager.saveJbdManualCons(jbdManualCons);
					this.saveMessage(request, "导入成功");
					return new ModelAndView(this.getSuccessView());
				}
				

				request.setAttribute("messages", messages);
				request.setAttribute("isFinished", true);
				request.setAttribute("errCount", errCount);

				return new ModelAndView(this.getFormView());
			} catch (IOException e) {
				this.saveMessage(request, getText("bdBounsDeduct.importFile.failed"));
				log.error(e.getMessage());
			} catch (Exception e) {
				this.saveMessage(request, getText("bdBounsDeduct.import.data.error"));
				log.error(e.getMessage());
            }
		}

		return new ModelAndView(this.getSuccessView());
	}

	public void setJbdManualConManager(JbdManualConManager jbdManualConManager) {
		this.jbdManualConManager = jbdManualConManager;
	}
}
