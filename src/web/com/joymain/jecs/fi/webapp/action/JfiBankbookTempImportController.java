package com.joymain.jecs.fi.webapp.action;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

import com.joymain.jecs.fi.model.JfiBankbookTemp;
import com.joymain.jecs.fi.service.JfiBankbookTempManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 导入存折条目
 * @author lenovo
 *
 */
@SuppressWarnings("rawtypes")
public class JfiBankbookTempImportController extends BaseFormController {
	private JfiBankbookTempManager jfiBankbookTempManager = null;
	private SysUserManager sysUserManager = null;
	private SysIdManager sysIdManager = null;

	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public void setJfiBankbookTempManager(JfiBankbookTempManager jfiBankbookTempManager) {
		this.jfiBankbookTempManager = jfiBankbookTempManager;
	}

	public JfiBankbookTempImportController() {
		setCommandName("jfiBankbookTemp");
		setCommandClass(JfiBankbookTemp.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		Map moneyTypes = ListUtil.getListOptions(SessionLogin.getLoginUser(request).getCompanyCode(), "fibankbooktemp.moneytype");
		request.setAttribute("moneyTypes", moneyTypes);
		
		Map dealTypes=ListUtil.getListOptions(SessionLogin.getLoginUser(request).getCompanyCode(), "fibankbooktemp.dealtype");
		request.setAttribute("dealTypes", dealTypes);
        RequestUtil.freshSession(request,"importFiBankbookTemp",60l);

		return new JfiBankbookTemp();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
	        throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
        SysUser loginSysUser = SessionLogin.getLoginUser(request);

		if ("importFiBankbookTemp".equals(request.getParameter("strAction"))) {
			
			if("C".equals(loginSysUser.getUserType())){
	    		if(RequestUtil.saveOperationSession(request,"importFiBankbookTemp",60l)!=0){
	    			return new ModelAndView("redirect:importJfiBankbookTemp.html");
	    		}
	    	}else if("M".equals(loginSysUser.getUserType())){
	    		if(RequestUtil.saveOperationSession(request,"importFiBankbookTemp",60l)!=0){
	    			return new ModelAndView("redirect:importJfiBankbookTemp.html");
	    		}
	    	}
			
			Long limitNum = new Long(ConfigUtil.getConfigValue(SessionLogin.getLoginUser(request).getCompanyCode(), "bankbook_temp_import_limit"));
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("xlsFile");
				//retrieve the file data
				InputStream stream = file.getInputStream();

				ExcelUtil eu = new ExcelUtil();
				//获取可读的工作表对象，定位到要读取的excel文件
				Workbook workbook = eu.getWorkbook(stream);
				//读取此文件的第一个工作表，工作表序号从0开始。
				Sheet sheet = workbook.getSheet(0);
				
				String createNo = this.sysIdManager.buildIdStr("create_no");
				if (StringUtils.isEmpty(createNo)) {
					throw new AppException(LocaleUtil.getLocalText("sysId.build.error") + ":create_no");
				}

				List<JfiBankbookTemp> jfiBankbookTemps = new ArrayList<JfiBankbookTemp>();
				for (int i = 1; i < sheet.getRows(); i++) {
					//会员编号,操作类型,资金类别,金额,摘要,POS
					String userCode = eu.getContents(sheet, 0, i);
					if (!StringUtils.isEmpty(userCode)) {
						userCode = StringUtils.trim(userCode);
					}
					System.out.println("==========="+userCode);
					String dealType = eu.getContents(sheet, 1, i);
					if (!StringUtils.isEmpty(dealType)) {
						dealType = StringUtils.trim(dealType);
					}

					String moneyType = eu.getContents(sheet, 2, i);
					if (!StringUtils.isEmpty(moneyType)) {
						moneyType = StringUtils.trim(moneyType);
					}

					String money = eu.getContents(sheet, 3, i);
					if (!StringUtils.isEmpty(money)) {
						money = StringUtils.trim(money);
					}

					String notes = eu.getContents(sheet, 4, i);
					if (!StringUtils.isEmpty(notes)) {
						notes = StringUtils.trim(notes);
					}
					
					String billpospayNum = eu.getContents(sheet, 5, i);
					if (!StringUtils.isEmpty(billpospayNum)) {
						billpospayNum = StringUtils.trim(billpospayNum);
					}
					
					String description = eu.getContents(sheet, 6, i);
					if (!StringUtils.isEmpty(description)) {
						description = StringUtils.trim(description);
					}
					
					System.out.println("==========="+billpospayNum);
					JfiBankbookTemp jfiBankbookTemp = new JfiBankbookTemp();

					SysUser sysUser = this.sysUserManager.getSysUser(userCode);
					if (sysUser == null) {
						throw new Exception(getText("miMember.notFound") + ": " + userCode);
					}

					if (SessionLogin.getLoginUser(request).getCompanyCode().equals("AA") || !SessionLogin.getLoginUser(request).getCompanyCode().equals(sysUser.getCompanyCode())) {
						throw new Exception("当前操作人的公司编码(" + SessionLogin.getLoginUser(request).getCompanyCode() + ")和用户对应的公司编码(" + sysUser.getCompanyCode() + ")不同");
					}
					
					try {
						jfiBankbookTemp.setSysUser(sysUser);
						jfiBankbookTemp.setDealType(dealType);
						jfiBankbookTemp.setMoneyType(new Integer(moneyType));
						jfiBankbookTemp.setMoney(new BigDecimal(money));
						jfiBankbookTemp.setNotes(notes);
						jfiBankbookTemp.setCreateNo(createNo);
						jfiBankbookTemp.setBillpospayNum(billpospayNum);
						jfiBankbookTemp.setDescription(description);
					} catch (Exception ex) {
						log.error("记录: " + i);
					}

					jfiBankbookTemp.setCompanyCode(SessionLogin.getLoginUser(request).getCompanyCode());
					jfiBankbookTemp.setStatus(1);
					jfiBankbookTemp.setCreaterCode(SessionLogin.getOperatorUser(request).getUserCode());
					jfiBankbookTemp.setCreaterName(SessionLogin.getOperatorUser(request).getUserName());
					jfiBankbookTemp.setCreateTime(new Date());

					jfiBankbookTemps.add(jfiBankbookTemp);
				}

				eu.closeWorkbook(workbook);

				if (jfiBankbookTemps != null && jfiBankbookTemps.size() > 0) {
					if (jfiBankbookTemps.size() > limitNum) {
						saveMessage(request, getText("fiBankbookTemp.import.limit.error") + ":" + limitNum.toString());
					} else {
						this.jfiBankbookTempManager.saveJfiBankbookTemps(jfiBankbookTemps);
						saveMessage(request, getText("fiBankbookTemp.imported"));
					}
				} else {
					saveMessage(request, getText("fiBankbookTemp.imported.empty"));
				}
			} catch (Exception ex) {
				saveMessage(request, getText("dataImport.unSuc")+": "+ex.getMessage());
				log.error("xls文件导入失败!", ex);
			}
		}

		ModelAndView mv = new ModelAndView(getSuccessView());
		mv.addObject("strAction", "listfiBankbookTemps");
		mv.addObject("needReload", "1");
		return mv;
	}
}