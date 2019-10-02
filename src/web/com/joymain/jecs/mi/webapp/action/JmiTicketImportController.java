package com.joymain.jecs.mi.webapp.action;

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
import com.joymain.jecs.mi.model.JmiTicket;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiTicketManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
public class JmiTicketImportController extends BaseFormController {
	private JmiMemberManager jmiMemberManager;
	
	private BdPeriodManager bdPeriodManager;
	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
	private JmiTicketManager jmiTicketManager;
	public void setJmiTicketManager(JmiTicketManager jmiTicketManager) {
		this.jmiTicketManager = jmiTicketManager;
	}

	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	public JmiTicketImportController() {
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

		if ("jmiTicketImport".equals(request.getParameter("strAction"))) {
			
			
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
				List<JmiTicket> jmiTickets = new ArrayList<JmiTicket>();
				Date curDate=new Date();
				int totalCount = sheet.getRows() - 1;
				int errCount = 0;
				//从第2行开始读,第一行为标题
				messages.add(LocaleUtil.getLocalText("bdBounsDeduct.impot.skip.first"));
				for (int i = 1; i < sheet.getRows(); i++) {

					
					String userCode = eu.getContents(sheet, 0, i);
					String ticketType = eu.getContents(sheet, 1, i);
					
					
					

					String content = " ([ " + userCode + " ] [ " + ticketType + " ]  )";
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
					if (!"1".equals(ticketType) && !"2".equals(ticketType)) {
						messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "门票类型错误" + content+"</font>");
						errCount++;
						continue;
					}
					
				
					JmiTicket jmiTicket=new JmiTicket();
					jmiTicket.setUserCode(userCode);
					
					jmiTicket.setTicketType(ticketType);
					
				
					
					jmiTickets.add(jmiTicket);
					

					//messages.add(message + LocaleUtil.getLocalText("bdBounsDeduct.import.OK") + content);
				}

				eu.closeWorkbook(workbook);
				
				if(errCount==0 && !jmiTickets.isEmpty()){
					
					jmiTicketManager.saveJmiTickets(jmiTickets);
					
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


}
