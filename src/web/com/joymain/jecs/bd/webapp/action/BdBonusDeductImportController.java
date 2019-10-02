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
import com.joymain.jecs.bd.service.BdBounsDeductManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
public class BdBonusDeductImportController extends BaseFormController {
	private BdBounsDeductManager bdBounsDeductManager = null;
	private JmiMemberManager jmiMemberManager;
	

	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	public void setBdBounsDeductManager(BdBounsDeductManager bdBounsDeductManager) {
		this.bdBounsDeductManager = bdBounsDeductManager;
	}

	public BdBonusDeductImportController() {
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

		if ("importBdBonusDeduct".equals(request.getParameter("strAction"))) {
			long deductedCount = this.bdBounsDeductManager.getDeductedCountByTreatedNo(bdBounsDeduct.getTreatedNo());
			if (deductedCount > 0) {
				//如果此批号已经存在并且被使用
				errors.rejectValue("treatedNo", "bdBounsDeduct.treatedNo.deducted");
				return showForm(request, response, errors);
			}
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
				List<BdBounsDeduct> bdBounsDeducts = new ArrayList<BdBounsDeduct>();
//				int deletedCount = this.bdBounsDeductManager.removeBdBounsDeductsByTreatedNo(bdBounsDeduct.getTreatedNo());
//				messages.add(LocaleUtil.getLocalText("bdBounsDeduct.import.deleted", new Object[] { bdBounsDeduct.getTreatedNo(), deletedCount }));

				int totalCount = sheet.getRows() - 1;
				int okCount = 0;
				int errCount = 0;
				//从第2行开始读,第一行为标题
				messages.add(LocaleUtil.getLocalText("bdBounsDeduct.impot.skip.first"));
				for (int i = 1; i < sheet.getRows(); i++) {
					//会员编号,扣补类型,扣补金额,备注
					String memberNo = eu.getContents(sheet, 0, i);
					String treatedType = eu.getContents(sheet, 1, i);
					String money = eu.getContents(sheet, 2, i);
					String summary = eu.getContents(sheet, 3, i);

					String content = " ([ " + memberNo + " ] [ " + treatedType + " ] [ " + money + " ] [ " + summary + " ])";
					String message = (i + 1) + ": ";
					if (StringUtils.isEmpty(memberNo)) {
						messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + LocaleUtil.getLocalText("bdBounsDeduct.error.memberNo.empty") + content+"</font>");
						errCount++;
						continue;
					}
					JmiMember miMember = this.jmiMemberManager.getJmiMember(memberNo.trim());
					if (miMember == null) {
						messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + LocaleUtil.getLocalText("bdBounsDeduct.error.memberNo.notExists") + content+"</font>");
						errCount++;
						continue;
					}
					if (StringUtils.isEmpty(miMember.getCompanyCode())) {
						messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + LocaleUtil.getLocalText("bdBounsDeduct.error.company.empty") + content+"</font>");
						errCount++;
						continue;
					}
					
					if(!StringUtil.isDouble(money)){
						throw new AppException("money.error");
					}
					
					BdBounsDeduct newBdBounsDeduct = new BdBounsDeduct();
					newBdBounsDeduct.setCompanyCode(miMember.getCompanyCode());
					newBdBounsDeduct.setJmiMember(miMember);
					newBdBounsDeduct.setSummary(summary);
					newBdBounsDeduct.setMoney(new BigDecimal(money));
					newBdBounsDeduct.setRemainMoney(new BigDecimal(money));
					newBdBounsDeduct.setDeductMoney(new BigDecimal(0));
					newBdBounsDeduct.setStatus("2");
					newBdBounsDeduct.setCheckerCode(SessionLogin.getLoginUser(multipartRequest).getUserCode());
					newBdBounsDeduct.setCheckerName(SessionLogin.getLoginUser(multipartRequest).getUserName());
					newBdBounsDeduct.setCheckTime(new Date());
					newBdBounsDeduct.setCreateCode(SessionLogin.getLoginUser(multipartRequest).getUserCode());
					newBdBounsDeduct.setCreateName(SessionLogin.getLoginUser(multipartRequest).getUserName());
					newBdBounsDeduct.setCreateTime(new Date());
					newBdBounsDeduct.setType(treatedType);
					newBdBounsDeduct.setTreatedNo(bdBounsDeduct.getTreatedNo());

					bdBounsDeducts.add(newBdBounsDeduct);
					okCount++;

					messages.add(message + LocaleUtil.getLocalText("bdBounsDeduct.import.OK") + content);
				}

				eu.closeWorkbook(workbook);
				this.bdBounsDeductManager.saveBdBounsDeducts(bdBounsDeducts);

				messages.add(LocaleUtil.getLocalText("bdBounsDeduct.imported.finished") + ": " + LocaleUtil.getLocalText("bdBounsDeduct.import.totalCount") + ":" + totalCount + " " + LocaleUtil.getLocalText("bdBounsDeduct.import.OK") + ":" + okCount + " " + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + ":" + errCount);
				request.setAttribute("messages", messages);
				request.setAttribute("isFinished", true);
				request.setAttribute("totalCount", totalCount);
				request.setAttribute("okCount", okCount);
				request.setAttribute("errCount", errCount);

				return new ModelAndView(this.getFormView());
			} catch (AppException e) {
				this.saveMessage(request, "金额存在不合法数字");
			}catch (IOException e) {
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
