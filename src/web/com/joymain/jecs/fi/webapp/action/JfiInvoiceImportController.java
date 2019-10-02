package com.joymain.jecs.fi.webapp.action;

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
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.fi.model.JfiInvoice;
import com.joymain.jecs.fi.service.JfiInvoiceManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JfiInvoiceImportController extends BaseFormController {
    private JfiInvoiceManager jfiInvoiceManager = null;

    private JmiMemberManager jmiMemberManager;
    
    private BdPeriodManager bdPeriodManager;
    
    public BdPeriodManager getBdPeriodManager() {
		return bdPeriodManager;
	}
	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
	public JmiMemberManager getJmiMemberManager() {
		return jmiMemberManager;
	}
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	public void setJfiInvoiceManager(JfiInvoiceManager jfiInvoiceManager) {
        this.jfiInvoiceManager = jfiInvoiceManager;
    }
    public JfiInvoiceImportController() {
        setCommandName("jfiInvoice");
        setCommandClass(JfiInvoice.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JfiInvoice jfiInvoice = null;

        if (!StringUtils.isEmpty(id)) {
            jfiInvoice = jfiInvoiceManager.getJfiInvoice(id);
        } else {
            jfiInvoice = new JfiInvoice();
        }

        return jfiInvoice;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
	
	SysUser loginSysUser = SessionLogin.getLoginUser(request);
	
    if("importJfiInvoice".equals(request.getParameter("strAction"))){
    	
    	
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
    	CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("importExcelFile");
    	
    	 if (file == null || file.getInputStream() == null) {
             //文件读取错误
             errors.reject("bdBounsDeduct.importFile.failed");
             return showForm(request, response, errors);
         }
    	 
          // 设置上传路径
    	  InputStream stream = file.getInputStream();
    	  
    	  ExcelUtil eu = new ExcelUtil();
    	  Workbook workbook = eu.getWorkbook(stream);
    	  Sheet sheet = workbook.getSheet(0);

          List<String> messages = new ArrayList<String>();
          List<JfiInvoice> invoices = new ArrayList<JfiInvoice>();
          int errCount = 0;
          
          messages.add(LocaleUtil.getLocalText("bdBounsDeduct.impot.skip.first"));
          
          for (int i = 1; i < sheet.getRows(); i++) {
        	  
        	  String userCode = eu.getContents(sheet, 0, i);
              String userName = eu.getContents(sheet, 1, i);
              
              
              String wyear = eu.getContents(sheet, 2, i);
              String wweek = eu.getContents(sheet, 3, i);
              String invoiceMoney = eu.getContents(sheet, 4, i);
              String company = eu.getContents(sheet, 5, i);
              String remark = eu.getContents(sheet, 6, i);
              
              
              String content = " ([ " + userCode + " ] [ " + userName+ " ][ " + wyear+ " ][ " + wweek+ " ][ " + invoiceMoney+ " ][ " + company+ " ][ " + remark+ " ] )";
              String message = (i + 1) + ": ";
              if (StringUtils.isEmpty(userCode)) {
                  messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "会员编号不能为空" + content+"</font>");
                  errCount++;
                  continue;
              }
              if (StringUtils.isEmpty(userName))
              {
                  messages.add("<font color=red>" + message+ LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "会员姓名不能为空" + content + "</font>");
                  errCount++;
                  continue;
              }
              if (StringUtils.isEmpty(wyear))
              {
                  messages.add("<font color=red>" + message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "财年不能为空" + content + "</font>");
                  errCount++;
                  continue;
              }
              if (StringUtils.isEmpty(wweek))
              {
                  messages.add("<font color=red>" + message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "期别不能为空" + content + "</font>");
                  errCount++;
                  continue;
              }
              JmiMember miMember = this.jmiMemberManager.getJmiMember(userCode.trim());
              if (miMember == null) {
                  messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "会员不存在" + content+"</font>");
                  errCount++;
                  continue;
              }
              if(!StringUtil.isInteger(wweek)){
                  messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "期别必须为数字" + content+"</font>");
                  errCount++;
                  continue;
              }else{
                  BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(WeekFormatUtil.getFormatWeek("f",wweek.trim()));
                  if(bdPeriod==null){
                      messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "期别不存在" + content+"</font>");
                      errCount++;
                      continue;
                  }
              }
              if (StringUtils.isEmpty(invoiceMoney) || !(isInteger(invoiceMoney) || isDouble(invoiceMoney)))
              {
                  messages.add("<font color=red>" + message+ LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "发票金额填写错误" + content + "</font>");
                  errCount++;
                  continue;
              }
              if (StringUtils.isEmpty(company))
              {
                  messages.add("<font color=red>" + message+ LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "发票所属公司不能为空" + content + "</font>");
                  errCount++;
                  continue;
              }
            
              
              JfiInvoice invoice = new JfiInvoice();
              invoice.setUserCode(userCode);
              invoice.setUserName(userName);
              invoice.setWyear(StringUtil.formatInt(wyear));
              invoice.setWweek(StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f", wweek)));
              invoice.setInvoiceMoney(new BigDecimal(invoiceMoney));
              invoice.setCompany(company);
              invoice.setRemark(remark);
              invoice.setCreateTime(new Date());
              invoice.setCreateNo(loginSysUser.getUserCode());
              
              invoices.add(invoice);
              
          }
    	  
          eu.closeWorkbook(workbook);
          
          if(errCount==0 && !invoices.isEmpty()){
        	  for (JfiInvoice jfiInvoice2 : invoices) {
        		  this.jfiInvoiceManager.saveJfiInvoice(jfiInvoice2);
        	  }
        	  
              this.saveMessage(request, "导入成功");
              return new ModelAndView(this.getSuccessView());
          }
          
          if(errCount==0 && invoices.isEmpty()){
        	  messages.add("<font color=red>"+message + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR") + " - " + "无内容" + "</font>");
          }
          request.setAttribute("messages", messages);
          request.setAttribute("isFinished", true);
          request.setAttribute("errCount", errCount);
    	
    }

        return new ModelAndView("fi/jfiInvoiceImport");
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
    
    /**
    * 判断字符串是否是整数
    */
    public static boolean isInteger(String value) {
    try {
    Integer.parseInt(value);
    return true;
    } catch (NumberFormatException e) {
    return false;
    }
    }

    /**
    * 判断字符串是否是浮点数
    */
    public static boolean isDouble(String value) {
    try {
    Double.parseDouble(value);
    if (value.contains("."))
    return true;
    return false;
    } catch (NumberFormatException e) {
    return false;
    }
    }

    /**
    * 判断字符串是否是数字
    */
    public static boolean isNumber(String value) {
    return isInteger(value) || isDouble(value);
    }
    
    
}
