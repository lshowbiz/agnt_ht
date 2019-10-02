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
import com.joymain.jecs.fi.model.JfiPosImport;
import com.joymain.jecs.fi.service.JfiPayBankManager;
import com.joymain.jecs.fi.service.JfiPayDataManager;
import com.joymain.jecs.fi.service.JfiPosImportManager;
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
 * 批量导入POS机刷卡记录
 * @author Alvin
 * 一个参考号只能有一条记录，进来了就不能删。
 * 实在错了就用存折条目补款
 */
public class JfiPosImportExcelController extends BaseFormController {
	private SysUserManager sysUserManager = null;
	private SysIdManager sysIdManager = null;
    private JfiPosImportManager jfiPosImportManager = null;

    public void setJfiPosImportManager(JfiPosImportManager jfiPosImportManager) {
        this.jfiPosImportManager = jfiPosImportManager;
    }
	

	public SysIdManager getSysIdManager() {
		return sysIdManager;
	}

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}

	public JfiPosImportExcelController() {
		setCommandName("jfiPosImport");
		setCommandClass(JfiPosImport.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		JfiPosImport jfiPosImport = new JfiPosImport();

		return jfiPosImport;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		if ("jfiPosImportExcel".equals(request.getParameter("strAction"))) {
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
				
				List<JfiPosImport> jfiPosImports=new ArrayList<JfiPosImport>();
				Date createTime = new Date();
				for (int i = 1; i < sheet.getRows(); i++) {
					//参考号	金额   流水号  码证码    
					String posNo = eu.getContents(sheet, 0, i);
					if(!StringUtils.isEmpty(posNo)){
						posNo=StringUtils.trim(posNo);
					}
					String amount = eu.getContents(sheet, 1, i);
					if(!StringUtils.isEmpty(amount)){
						amount=StringUtils.trim(amount);
					}
					String pid = eu.getContents(sheet, 2, i);
					if(!StringUtils.isEmpty(pid)){
						pid=StringUtils.trim(pid);
					}
					String tel = eu.getContents(sheet, 3, i);
					if(!StringUtils.isEmpty(tel)){
						tel=StringUtils.trim(tel);
					}
					
					JfiPosImport jfiPosImport = new JfiPosImport();
					
					try{
						jfiPosImport.setPid(pid);
						jfiPosImport.setTel(tel);
						jfiPosImport.setAmount(new BigDecimal(amount));
						jfiPosImport.setPosNo(posNo);
						if("0".equals(posNo)){
							saveMessage(request, getText("参考号不能为0:")+i);
							jfiPosImports = null;
							break;
						}
						JfiPosImport jfiPosImportTmp = new JfiPosImport();
						jfiPosImportTmp.setPosNo(posNo);
						List jfiPosImportListTmp = jfiPosImportManager.getJfiPosImports(jfiPosImportTmp);
						if(jfiPosImportListTmp!=null&&jfiPosImportListTmp.size()!=0){
							saveMessage(request, getText("已存在相同的参考号")+i);
							jfiPosImports = null;
							break;
						}
/*						jfiPosImportTmp = new JfiPosImport();
						jfiPosImportTmp.setPid(pid);
						jfiPosImportListTmp = jfiPosImportManager.getJfiPosImports(jfiPosImportTmp);
						if(jfiPosImportListTmp!=null&&jfiPosImportListTmp.size()!=0){
							saveMessage(request, getText("已存在相同的流水号")+i);
							jfiPosImports = null;
							break;
						}*/
					}catch(Exception ex){
						ex.printStackTrace();
						log.error("记录: "+i);
						saveMessage(request, getText("fiPayData.import.failed")+i);
						jfiPosImports = null;
						break;
					}
					jfiPosImport.setCreateTime(createTime);
					jfiPosImport.setCreateUser(SessionLogin.getLoginUser(request).getUserCode());
					jfiPosImport.setInc("0");
					jfiPosImport.setStatus("1");
					jfiPosImport.setMoneyType(89);//POS现场刷卡支付 
					
					jfiPosImports.add(jfiPosImport);
				}

				eu.closeWorkbook(workbook);
			
				if(jfiPosImports!=null && jfiPosImports.size()>0){
					jfiPosImportManager.saveJfiPosImports(jfiPosImports);
					saveMessage(request, getText("fiPayData.imported"));
				}else{
					if(jfiPosImports==null){
						
					}else{
						saveMessage(request, getText("fiPayData.imported.empty"));
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				saveMessage(request, getText("fiPayData.import.failed"));
				log.error("xls文件导入失败!",ex);
			}
		}

		ModelAndView mv=new ModelAndView(getSuccessView());
		mv.addObject("needReload", "1");
		return mv;
	}
}