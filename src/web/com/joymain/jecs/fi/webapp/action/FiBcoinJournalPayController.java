package com.joymain.jecs.fi.webapp.action;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.fi.model.FiBankbookTemp;
import com.joymain.jecs.fi.service.FiBcoinJournalManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.math.MathUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
@SuppressWarnings("unused")
public class FiBcoinJournalPayController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiBcoinJournalPayController.class);
    private FiBcoinJournalManager fiBcoinJournalManager;
    private SysUserManager sysUserManager;

    public void setFiBcoinJournalManager(FiBcoinJournalManager fiBcoinJournalManager) {
        this.fiBcoinJournalManager = fiBcoinJournalManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
    	SysUser loginSysUser = SessionLogin.getLoginUser(request);
    	SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
        if("post".equals(request.getMethod().toLowerCase())){
        	String userCode = request.getParameter("userCode");
        	if(StringUtil.isEmpty(userCode)){
        		postByBatchUserCode(request,response);
        	}else{
        		postByUserCode(request,response);
        	}
        }
        return new ModelAndView("fi/fiBcoinJournalPayForm");
    }
    
	private void postByBatchUserCode(HttpServletRequest request,HttpServletResponse response){
    	SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
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

			List<FiBankbookTemp> fiBankbookTemps = new ArrayList<FiBankbookTemp>();
			for (int i = 1; i < sheet.getRows(); i++) {
				//会员编号,金额,摘要
				String userCode = eu.getContents(sheet, 0, i);
				if (!StringUtils.isEmpty(userCode)) {
					userCode = StringUtils.trim(userCode);
				}else{
//					MessageUtil.saveLocaleMessage(request, "会员编号为空，请检查第"+i+"行");
					continue;
				}
				SysUser sysUser = sysUserManager.getSysUser(userCode);
	        	if(sysUser==null || !"M".equals(sysUser.getUserType())){
//	        		MessageUtil.saveLocaleMessage(request, "会员不存在，请检查第"+i+"行");
					continue;
	        	}

				String money = eu.getContents(sheet, 1, i);
				if (!StringUtils.isEmpty(money)) {
					money = StringUtils.trim(money);
				}else if (!MathUtil.isDecimal(money)) {
//					MessageUtil.saveLocaleMessage(request, "积分输入错误");
				} else{
//					MessageUtil.saveLocaleMessage(request, "金额为空，请检查第"+i+"行");
					continue;
				}

				String remark = eu.getContents(sheet, 2, i);
				if (!StringUtils.isEmpty(remark)) {
					remark = StringUtils.trim(remark);
				}else{
//					MessageUtil.saveLocaleMessage(request, "摘要为空，请检查第"+i+"行");
					continue;
				}
				
				String description = eu.getContents(sheet, 3, i);
				if (!StringUtils.isEmpty(description)) {
					description = StringUtils.trim(description);
				}else{
//					MessageUtil.saveLocaleMessage(request, "描述为空，请检查第"+i+"行");
					continue;
				}
				
				Integer[] moneyType = new Integer[1];
    			moneyType[0] = 5;
    			Long[] appId = new Long[1];
    			appId[0] = 2l;
    			String[] notes = new String[1];
    			notes[0] = request.getParameter("description")+"手工修改积分=====" + request.getParameter("remark");
    			//notes[0] = "手工修改积分=====" + remark;
    			BigDecimal[] moneyArray = new BigDecimal[1];
				BigDecimal amount = new BigDecimal(money);
				if(amount.compareTo(new BigDecimal("0"))==-1){
					moneyArray[0] = amount.multiply(new BigDecimal("-1"));
					fiBcoinJournalManager.saveJfiBankbookDeduct("CN", sysUser, moneyType, moneyArray, operatorSysUser.getUserCode(), operatorSysUser.getUserName(), "0", notes, appId,"1");
//					MessageUtil.saveLocaleMessage(request, "导入成功!");
				}else if(amount.compareTo(new BigDecimal("0"))==1){
					moneyArray[0] = amount;
					fiBcoinJournalManager.saveFiPayDataVerify("CN", sysUser, moneyType, moneyArray, operatorSysUser.getUserCode(), operatorSysUser.getUserName(), "0", notes, appId, null,"1");
//					MessageUtil.saveLocaleMessage(request, "导入成功!");
				}else{
//					MessageUtil.saveLocaleMessage(request, "积分不能为零"+i);
				}
			}
			MessageUtil.saveLocaleMessage(request, "导入成功!");
			eu.closeWorkbook(workbook);
		} catch (Exception ex) {
			ex.printStackTrace();
			MessageUtil.saveLocaleMessage(request, "导入失败!");
			log.error("xls文件导入失败!", ex);
		}
    }
    
    private void postByUserCode(HttpServletRequest request,HttpServletResponse response){
    	SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
    	String userCode = request.getParameter("userCode");
    	BigDecimal amount = null;
    	try{
    		amount = new BigDecimal(request.getParameter("amount"));
        	SysUser sysUser = sysUserManager.getSysUser(userCode);
        	if(!MathUtil.isDecimal(amount.toString())){
        		MessageUtil.saveLocaleMessage(request, "积分输入错误");
        	}
        	if(sysUser!=null && "M".equals(sysUser.getUserType())){
    			Integer[] moneyType = new Integer[1];
    			moneyType[0] = 5;
    			Long[] appId = new Long[1];
    			appId[0] = 2l;
    			String[] notes = new String[1];
    			//notes[0] = "手工修改积分=====" + request.getParameter("remark");
    			notes[0] = request.getParameter("description")+"手工修改积分=====" + request.getParameter("remark");
    			BigDecimal[] moneyArray = new BigDecimal[1];
            	if(amount.compareTo(new BigDecimal("0"))==-1){
        			moneyArray[0] = amount.multiply(new BigDecimal("-1"));
        			try{
        				fiBcoinJournalManager.saveJfiBankbookDeduct("CN", sysUser, moneyType, moneyArray, operatorSysUser.getUserCode(), operatorSysUser.getUserName(), "0", notes, appId,"1");
                		MessageUtil.saveLocaleMessage(request, "积分扣除成功");
        			}catch(Exception e){
                		e.printStackTrace();
                		MessageUtil.saveLocaleMessage(request, "积分不足");
                	}
            	}else if(amount.compareTo(new BigDecimal("0"))==1){
            		moneyArray[0] = amount;
            		fiBcoinJournalManager.saveFiPayDataVerify("CN", sysUser, moneyType, moneyArray, operatorSysUser.getUserCode(), operatorSysUser.getUserName(), "0", notes, appId, null,"1");
            		MessageUtil.saveLocaleMessage(request, "积分增加成功");
            	}else{
    				MessageUtil.saveLocaleMessage(request, "积分不能为零");
            	}
        	}else{
				MessageUtil.saveLocaleMessage(request, "会员不存在");
        	}
    	}catch(Exception e){
    		e.printStackTrace();
    		MessageUtil.saveLocaleMessage(request, "积分不为数字");
    	}
    }

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}
}
