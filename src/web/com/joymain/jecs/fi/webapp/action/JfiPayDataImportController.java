package com.joymain.jecs.fi.webapp.action;

import java.io.File;
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

import com.joymain.jecs.fi.model.JfiPayData;
import com.joymain.jecs.fi.service.JfiPayBankManager;
import com.joymain.jecs.fi.service.JfiPayDataManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 批量导入存折条目
 * @author Alvin
 *
 */
public class JfiPayDataImportController extends BaseFormController {
	private JfiPayDataManager jfiPayDataManager = null;
	private JfiPayBankManager jfiPayBankManager = null;
	private SysUserManager sysUserManager = null;
	private SysIdManager sysIdManager = null;

	public SysIdManager getSysIdManager() {
		return sysIdManager;
	}

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}

	public void setJfiPayBankManager(JfiPayBankManager jfiPayBankManager) {
		this.jfiPayBankManager = jfiPayBankManager;
	}

	public void setJfiPayDataManager(JfiPayDataManager jfiPayDataManager) {
		this.jfiPayDataManager = jfiPayDataManager;
	}

	public JfiPayDataImportController() {
		setCommandName("jfiPayData");
		setCommandClass(JfiPayData.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String dataId = request.getParameter("dataId");
		JfiPayData jfiPayData = null;

		if (!StringUtils.isEmpty(dataId)) {
			jfiPayData = jfiPayDataManager.getJfiPayData(dataId);
			if (jfiPayData.getStatus() == 2) {
				//如果已确认,则不能修改
				throw new AppException(LocaleUtil.getLocalText("error.fiPayData.has.verified"));
			}
		} else {
			jfiPayData = new JfiPayData();
		}

		List jfiPayBanks = this.jfiPayBankManager.getJfiPayBanks(null);
		request.setAttribute("jfiPayBanks", jfiPayBanks);

		return jfiPayData;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		if ("importFiPayData".equals(request.getParameter("strAction"))) {
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("xlsFile");
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
				
				String createNo = this.sysIdManager.buildIdStr("create_no");
				if (StringUtils.isEmpty(createNo)) {
					throw new AppException(LocaleUtil.getLocalText("sysId.build.error") + ":create_no");
				}
				List<JfiPayData> jfiPayDatas=new ArrayList<JfiPayData>();
				for (int i = 1; i < sheet.getRows(); i++) {
					//会员编号,扣补类型,扣补金额,备注
					String accountCode = eu.getContents(sheet, 0, i);
					if(!StringUtils.isEmpty(accountCode)){
						accountCode=StringUtils.trim(accountCode);
					}
					//String dealDate = eu.getContents(sheet, 1, i);
					Date dealDate = new Date();
					String incomeMoney = eu.getContents(sheet, 2, i);
					if(!StringUtils.isEmpty(incomeMoney)){
						incomeMoney=StringUtils.trim(incomeMoney);
					}
					String userCode = eu.getContents(sheet, 3, i);
					SysUser sysUser = null;
					if(!StringUtils.isEmpty(userCode)){
						userCode=StringUtils.trim(userCode);
						sysUser = sysUserManager.getSysUser(userCode);
					}
					String excerpt = eu.getContents(sheet, 4, i);
					if(!StringUtils.isEmpty(excerpt)){
						excerpt=StringUtils.trim(excerpt);
					}
					
					JfiPayData jfiPayData = new JfiPayData();
					
					try{
						jfiPayData.setAccountCode(accountCode);
						jfiPayData.setDealDate(dealDate);
						jfiPayData.setIncomeMoney(new BigDecimal(incomeMoney));
						jfiPayData.setSysUser(sysUser);
						jfiPayData.setExcerpt(excerpt);
					}catch(Exception ex){
						log.error("记录: "+i);
					}
					
					jfiPayData.setCompanyCode(SessionLogin.getLoginUser(request).getCompanyCode());
					jfiPayData.setStatus(1);
					jfiPayData.setCreateNo(createNo);
					jfiPayData.setCreaterCode(SessionLogin.getOperatorUser(request).getUserCode());
					jfiPayData.setCreaterName(SessionLogin.getOperatorUser(request).getUserName());
					jfiPayData.setCreateTime(new Date());
					
					jfiPayDatas.add(jfiPayData);
				}

				eu.closeWorkbook(workbook);
			
				if(jfiPayDatas!=null && jfiPayDatas.size()>0){
					this.jfiPayDataManager.saveJfiPayDatas(jfiPayDatas);
					saveMessage(request, getText("fiPayData.imported"));
				}else{
					saveMessage(request, getText("fiPayData.imported.empty"));
				}
			} catch (Exception ex) {
				saveMessage(request, getText("fiPayData.import.failed"));
				log.error("xls文件导入失败!",ex);
			}
		}

		ModelAndView mv=new ModelAndView(getSuccessView());
		mv.addObject("strAction","listFiPayDatas");
		mv.addObject("needReload", "1");
		return mv;
	}
}