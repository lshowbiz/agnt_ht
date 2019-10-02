package com.joymain.jecs.mi.webapp.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
public class JmiChangeCardTypeImportController extends BaseFormController {
	private JmiMemberManager jmiMemberManager;
	private BdPeriodManager bdPeriodManager;

	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}


	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}


	public JmiChangeCardTypeImportController() {
		setCommandName("bdPeriod");
		setCommandClass(BdPeriod.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		BdPeriod bdPeriod = new BdPeriod();

		return bdPeriod;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		if ("importJmiChangeCardType".equals(request.getParameter("strAction"))) {

	        SysUser defSysUser = SessionLogin.getLoginUser(request);
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
				
//				int deletedCount = this.bdBounsDeductManager.removeBdBounsDeductsByTreatedNo(bdBounsDeduct.getTreatedNo());
//				messages.add(LocaleUtil.getLocalText("bdBounsDeduct.import.deleted", new Object[] { bdBounsDeduct.getTreatedNo(), deletedCount }));


				Map cardTypeMap=ListUtil.getListOptions(defSysUser.getCompanyCode(), "bd.cardtype");
				
				int totalCount = sheet.getRows() - 1;
				int okCount = 0;
				int errCount = 0;
				//从第2行开始读,第一行为标题
				messages.add(LocaleUtil.getLocalText("bdBounsDeduct.impot.skip.first"));
				
				List<Map> jmiMembers=new ArrayList<Map>();
				
				for (int i = 1; i < sheet.getRows(); i++) {
					//会员编号,扣补类型,扣补金额,备注
					String userCode = eu.getContents(sheet, 0, i);
					String wweek = eu.getContents(sheet, 1, i);
					wweek=WeekFormatUtil.getFormatWeek("f",wweek);
					String newCard = eu.getContents(sheet, 2, i);
					String remark = eu.getContents(sheet, 3, i);

					String content = " ([ " + userCode + " ] [ " + WeekFormatUtil.getFormatWeek("w",wweek) + " ] [ " + newCard + " ])";
					String message = (i + 1) + ": ";
					if (StringUtils.isEmpty(userCode)) {
						messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + LocaleUtil.getLocalText("bdBounsDeduct.error.memberNo.empty") + content+"</font>");
						errCount++;
						continue;
					}
					JmiMember miMember = this.jmiMemberManager.getJmiMember(userCode.trim());
					if (miMember == null) {
						messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + LocaleUtil.getLocalText("bdBounsDeduct.error.memberNo.notExists") + content+"</font>");
						errCount++;
						continue;
					}

		    		BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(wweek);
		    		if(!StringUtil.isInteger(wweek)||bdPeriod==null||bdPeriod.getArchivingStatus()==1){
		    			messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + LocaleUtil.getLocalText("bd.send.Archiving") + content+"</font>");
		    			errCount++;
		    			continue;
					}
					if(cardTypeMap.get(newCard)==null){
		    			messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - "  + LocaleUtil.getLocalText("null.grade") + content+"</font>");
		    			errCount++;
		    			continue;
					}
					if(miMember.getCardType().equals(newCard)){
		    			messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + LocaleUtil.getLocalText("jmiMember.cardType.same") + content+"</font>");
		    			errCount++;
		    			continue;
					}
					
					Map map=new HashMap();
					map.put("newCard", newCard);
					map.put("jmiMember", miMember);
					map.put("wweek", StringUtil.formatInt(wweek));
					map.put("remark", remark);
					jmiMembers.add(map);
					
					okCount++;

				}

				if(errCount==0){
					jmiMemberManager.changeMiMemberCardTypeAll(jmiMembers,defSysUser);
					//messages.add(message + LocaleUtil.getLocalText("bdBounsDeduct.import.OK") + content);

					messages.add(LocaleUtil.getLocalText("bdBounsDeduct.imported.finished") + ": " + LocaleUtil.getLocalText("bdBounsDeduct.import.totalCount") + ":" + totalCount + " " + LocaleUtil.getLocalText("bdBounsDeduct.import.OK") + ":" + okCount + " " + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + ":" + errCount);
				}else{
					messages.add( LocaleUtil.getLocalText("bdBounsDeduct.import.totalCount") + ":" + totalCount + " " + LocaleUtil.getLocalText("bdBounsDeduct.import.OK") + ":" + okCount + " " + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + ":" + errCount);
				}
				
				
				
				eu.closeWorkbook(workbook);
				

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
