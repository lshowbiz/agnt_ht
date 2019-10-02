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

import com.joymain.jecs.fi.model.FiProductPointTemp;
import com.joymain.jecs.fi.service.FiProductPointTempManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
@SuppressWarnings("rawtypes")
public class FiProductPointTempImportController extends BaseFormController {
	private FiProductPointTempManager fiProductPointTempManager = null;
	private SysUserManager sysUserManager = null;
	private SysIdManager sysIdManager = null;

	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public void setFiProductPointTempManager(FiProductPointTempManager fiProductPointTempManager) {
		this.fiProductPointTempManager = fiProductPointTempManager;
	}

	public FiProductPointTempImportController() {
		setCommandName("fiProductPointTemp");
		setCommandClass(FiProductPointTemp.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		Map moneyTypes = ListUtil.getListOptions(SessionLogin.getLoginUser(request).getCompanyCode(), "fibankbooktemp.moneytype");
		request.setAttribute("moneyTypes", moneyTypes);
		
		Map dealTypes=ListUtil.getListOptions(SessionLogin.getLoginUser(request).getCompanyCode(), "fibankbooktemp.dealtype");
		request.setAttribute("dealTypes", dealTypes);
		
		Map bankbookTypes=ListUtil.getListOptions(SessionLogin.getLoginUser(request).getCompanyCode(), "fiproductpointtemp.productpointtype");
		request.setAttribute("bankbookTypes", bankbookTypes);
        RequestUtil.freshSession(request,"importFiProductPointTempJJ",60l);

		return new FiProductPointTemp();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
	        throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
        SysUser loginSysUser = SessionLogin.getLoginUser(request);

		if ("importFiProductPointTempJJ".equals(request.getParameter("strAction"))) {
			if("C".equals(loginSysUser.getUserType())){
	    		if(RequestUtil.saveOperationSession(request,"importFiProductPointTempJJ",60l)!=0){
	    			return new ModelAndView("redirect:importFiProductPointTemp.html");
	    		}
	    	}else if("M".equals(loginSysUser.getUserType())){
	    		if(RequestUtil.saveOperationSession(request,"importFiProductPointTempJJ",60l)!=0){
	    			return new ModelAndView("redirect:importFiProductPointTemp.html");
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

				List<FiProductPointTemp> fiProductPointTemps = new ArrayList<FiProductPointTemp>();
				for (int i = 1; i < sheet.getRows(); i++) {
					//会员编号,操作类型,资金类别,金额,摘要
					String userCode = eu.getContents(sheet, 0, i);
					if (!StringUtils.isEmpty(userCode)) {
						userCode = StringUtils.trim(userCode);
					}

					String ProductPointType = eu.getContents(sheet, 1, i);
					if (!StringUtils.isEmpty(ProductPointType)) {
						ProductPointType = StringUtils.trim(ProductPointType);
					}

					String dealType = eu.getContents(sheet, 2, i);
					if (!StringUtils.isEmpty(dealType)) {
						dealType = StringUtils.trim(dealType);
					}

					String moneyType = eu.getContents(sheet, 3, i);
					if (!StringUtils.isEmpty(moneyType)) {
						moneyType = StringUtils.trim(moneyType);
					}

					String money = eu.getContents(sheet, 4, i);
					if (!StringUtils.isEmpty(money)) {
						money = StringUtils.trim(money);
					}

					String notes = eu.getContents(sheet, 5, i);
					if (!StringUtils.isEmpty(notes)) {
						notes = StringUtils.trim(notes);
					}
					
					String description = eu.getContents(sheet, 6, i);
					if (!StringUtils.isEmpty(description)) {
						description = StringUtils.trim(description);
					}


					FiProductPointTemp fiProductPointTemp = new FiProductPointTemp();

					SysUser sysUser = this.sysUserManager.getSysUser(userCode);
					if (sysUser == null) {
						throw new Exception(getText("miMember.notFound") + ": " + userCode);
					}

					if (SessionLogin.getLoginUser(request).getCompanyCode().equals("AA") || !SessionLogin.getLoginUser(request).getCompanyCode().equals(sysUser.getCompanyCode())) {
						throw new Exception("当前操作人的公司编码(" + SessionLogin.getLoginUser(request).getCompanyCode() + ")和用户对应的公司编码(" + sysUser.getCompanyCode() + ")不同");
					}
					
					try {
						fiProductPointTemp.setSysUser(sysUser);
						fiProductPointTemp.setDealType(dealType);
						fiProductPointTemp.setMoneyType(new Integer(moneyType));
						fiProductPointTemp.setMoney(new BigDecimal(money));
						fiProductPointTemp.setNotes(notes);
						fiProductPointTemp.setDescription(description);
						fiProductPointTemp.setCreateNo(createNo);
						fiProductPointTemp.setProductPointType(ProductPointType);
					} catch (Exception ex) {
						log.error("记录: " + i);
					}

					fiProductPointTemp.setCompanyCode(SessionLogin.getLoginUser(request).getCompanyCode());
					fiProductPointTemp.setStatus(1);
					fiProductPointTemp.setCreaterCode(SessionLogin.getOperatorUser(request).getUserCode());
					fiProductPointTemp.setCreaterName(SessionLogin.getOperatorUser(request).getUserName());
					fiProductPointTemp.setCreateTime(new Date());

					fiProductPointTemps.add(fiProductPointTemp);
				}

				eu.closeWorkbook(workbook);

				if (fiProductPointTemps != null && fiProductPointTemps.size() > 0) {
					if (fiProductPointTemps.size() > limitNum) {
						saveMessage(request, getText("fiProductPointTemp.import.limit.error") + ":" + limitNum.toString());
					} else {
						this.fiProductPointTempManager.saveFiProductPointTemps(fiProductPointTemps);
						saveMessage(request, getText("产品积分导入成功"));
					}
				} else {
					saveMessage(request, getText("产品积分导入内容为空"));
				}
			} catch (Exception ex) {
				saveMessage(request, getText("dataImport.unSuc")+": "+ex.getMessage());
				log.error("xls文件导入失败!", ex);
			}
		}

		ModelAndView mv = new ModelAndView(getSuccessView());
		mv.addObject("strAction", "listfiProductPointTempsJJ");
		mv.addObject("needReload", "1");
		return mv;
	}
}