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
import com.joymain.jecs.bd.model.JbdSendRecordHist;
import com.joymain.jecs.bd.service.BdBounsDeductManager;
import com.joymain.jecs.bd.service.JbdSendRecordHistManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
public class JbdSendRecordFreezeImportController extends BaseFormController {
	private BdBounsDeductManager bdBounsDeductManager = null;
	private JmiMemberManager jmiMemberManager;
	private JbdSendRecordHistManager jbdSendRecordHistManager;

	public void setJbdSendRecordHistManager(
			JbdSendRecordHistManager jbdSendRecordHistManager) {
		this.jbdSendRecordHistManager = jbdSendRecordHistManager;
	}

	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	public void setBdBounsDeductManager(BdBounsDeductManager bdBounsDeductManager) {
		this.bdBounsDeductManager = bdBounsDeductManager;
	}

	public JbdSendRecordFreezeImportController() {
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


		if ("jbdSendRecordFreezeImport".equals(request.getParameter("strAction"))) {
			
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("importExcelFile");

				if (file == null || file.getInputStream() == null) {
					//文件读取错误
					errors.reject("bdBounsDeduct.importFile.failed");
					return showForm(request, response, errors);
				}
				// 设置上传路径
				String uploadDir = getServletContext().getRealPath("/WEB-INF") + "/xls/" + SessionLogin.getLoginUser(request).getUserCode() + "/";
				// 如果路径不存在则创建
				File dirPath = new File(uploadDir);
				if (!dirPath.exists()) {
					dirPath.mkdirs();
				}
				//retrieve the file data
				InputStream stream = file.getInputStream();

				ExcelUtil eu = new ExcelUtil();
				//获取可读的工作表对象，定位到要读取的excel文件
				Workbook workbook = eu.getWorkbook(stream);
				//读取此文件的第一个工作表，工作表序号从0开始。
				Sheet sheet = workbook.getSheet(0);

				List<String> messages = new ArrayList<String>();
				List<JbdSendRecordHist> jbdSendRecordHists = new ArrayList<JbdSendRecordHist>();

				int totalCount = sheet.getRows() - 1;
				int okCount = 0;
				int errCount = 0;
				//从第2行开始读,第一行为标题
				messages.add(LocaleUtil.getLocalText("bdBounsDeduct.impot.skip.first"));
				for (int i = 1; i < sheet.getRows(); i++) {
					//会员编号,扣补类型,扣补金额,备注
					String wweek = eu.getContents(sheet, 0, i);
					wweek=WeekFormatUtil.getFormatWeek("f", wweek);
					String userCode = eu.getContents(sheet, 1, i);
					String type = eu.getContents(sheet, 2, i);

					String content = " ([ " + userCode + " ] [ " + wweek + " ] [ " + type + " ])";
					String message = (i + 1) + ": ";
					
					if (!"1".equals(type) &&  !"0".equals(type)) {
						messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "冻结标识不明确" + content+"</font>");
						errCount++;
						continue;
					}
					
					if (StringUtils.isEmpty(userCode)) {
						messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + LocaleUtil.getLocalText("bdBounsDeduct.error.memberNo.empty") + content+"</font>");
						errCount++;
						continue;
					}
					JmiMember miMember = this.jmiMemberManager.getJmiMember(userCode.trim());
					if (miMember == null) {
						messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "会员编号不存在" + content+"</font>");
						errCount++;
						continue;
					}
					
					
					JbdSendRecordHist jbdSendRecordHist=jbdSendRecordHistManager.getJbdSendRecordHistByUserCodeAndWeek(userCode, wweek);
					if(jbdSendRecordHist==null){
						messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "奖金不存在" + content+"</font>");
						errCount++;
						continue;
					}
					if(jbdSendRecordHist.getRemittanceMoney().compareTo(new BigDecimal(0))<=0){
						messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "奖金为0" + content+"</font>");
						errCount++;
						continue;
					}
/*					if(jbdSendRecordHist.getCardType().equals("0")){
						messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "待审会员" + content+"</font>");
						errCount++;
						continue;
					}*/
					if(!jbdSendRecordHist.getRegisterStatus().equals("1")){
						messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "奖金已发放" + content+"</font>");
						errCount++;
						continue;
					}
					if(jbdSendRecordHist.getFreezeStatus().toString().equals(type)){
						messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "冻结状态相同" + content+"</font>");
						errCount++;
						continue;
					}

					jbdSendRecordHists.add(jbdSendRecordHist);
					okCount++;

					messages.add(message + LocaleUtil.getLocalText("bdBounsDeduct.import.OK") + content);
				}

				eu.closeWorkbook(workbook);
				this.jbdSendRecordHistManager.freezeJbdSendRecord(jbdSendRecordHists);

				messages.add(LocaleUtil.getLocalText("bdBounsDeduct.imported.finished") + ": " + LocaleUtil.getLocalText("bdBounsDeduct.import.totalCount") + ":" + totalCount + " " + LocaleUtil.getLocalText("bdBounsDeduct.import.OK") + ":" + okCount + " " + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + ":" + errCount);
				request.setAttribute("messages", messages);
				request.setAttribute("isFinished", true);
				request.setAttribute("totalCount", totalCount);
				request.setAttribute("okCount", okCount);
				request.setAttribute("errCount", errCount);

				return new ModelAndView(this.getFormView());
			} catch (IOException e) {
				e.printStackTrace();
				this.saveMessage(request, getText("bdBounsDeduct.importFile.failed"));
				log.error(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				this.saveMessage(request, getText("bdBounsDeduct.import.data.error"));
				log.error(e.getMessage());
            }
		}

		return new ModelAndView(this.getSuccessView());
	}
}
