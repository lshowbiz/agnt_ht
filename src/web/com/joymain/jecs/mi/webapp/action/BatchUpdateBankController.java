package com.joymain.jecs.mi.webapp.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysBank;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysBankManager;
import com.joymain.jecs.util.GuidHelper;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class BatchUpdateBankController extends BaseFormController
{
    
    private JmiMemberManager jmiMemberManager = null;
    private SysBankManager sysBankManager;
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
        this.jmiMemberManager = jmiMemberManager;
    }
    public void setSysBankManager(SysBankManager sysBankManager) {
        this.sysBankManager = sysBankManager;
    }
    
    public BatchUpdateBankController()
    {
        setCommandName("batchUpdateBank");
        setCommandClass(JmiMember.class);
    }
    
    protected Object formBackingObject(HttpServletRequest request)
            throws Exception
    {
        JmiMember jmiMember = new JmiMember();
        
        List sysBankList=sysBankManager.getSysBankByCompanyCode("CN");
        request.setAttribute("sysBankList", sysBankList);
        
        return jmiMember;
    }
    
    public ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception
    {
        SysUser defSysUser = SessionLogin.getLoginUser(request);    
        try
        {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("importExcelFile");
            if (file == null || file.getInputStream() == null)
            {
                //文件读取错误
                errors.reject("bdBounsDeduct.importFile.failed");
                return showForm(request, response, errors);
            }
            // 设置上传路径
            
            //retrieve the file data
            InputStream stream = file.getInputStream();
            
            ExcelUtil eu = new ExcelUtil();
            //获取可读的工作表对象，定位到要读取的excel文件
            Workbook workbook = eu.getWorkbook(stream);
            //读取此文件的第一个工作表，工作表序号从0开始。
            Sheet sheet = workbook.getSheet(0);
            
            List<String> messages = new ArrayList<String>();
            List<String> sqls = new ArrayList<String>();
            int errCount = 0;
            //从第2行开始读,第一行为标题
            messages.add(LocaleUtil.getLocalText("bdBounsDeduct.impot.skip.first"));
            
            List<SysBank> sysBankList=sysBankManager.getSysBankByCompanyCode("CN");
            Map<String,String> bankMap = new HashMap<String, String>();
            for(SysBank b:sysBankList){
                bankMap.put(b.getBankKey(), b.getBankValue());
            }
            
            for (int i = 1; i < sheet.getRows(); i++)
            {
                //会员编号,扣补类型,扣补金额,备注
                String userCode = eu.getContents(sheet, 0, i);
                String bank = eu.getContents(sheet, 1, i);
                String bankaddress = eu.getContents(sheet, 2, i);
                String bankcard = eu.getContents(sheet, 3, i);
                String remark = eu.getContents(sheet, 4, i);
                
                String content = " ([ " + userCode + ","+bank+","+bankaddress+","+bankcard+","+remark+" ])";
                String message = (i + 1) + ": ";
                if (StringUtils.isEmpty(userCode))
                {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "会员编号不能为空" + content + "</font>");
                    errCount++;
                    continue;
                }
                JmiMember jmiMember = this.jmiMemberManager.getJmiMember(userCode);
                if (jmiMember == null)
                {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "会员不存在" + content + "</font>");
                    errCount++;
                    continue;
                }
                
                if (StringUtils.isEmpty(bank))
                {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "开户银行不能为空" + content + "</font>");
                    errCount++;
                    continue;
                } else if(!bankMap.containsKey(bank.trim())) {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "开户银行不正确" + content + "</font>");
                    errCount++;
                    continue;
                } else {
                    jmiMember.setBank(bank.trim());
                }
                
                if (StringUtils.isEmpty(bankaddress))
                {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "开户支行不能为空" + content + "</font>");
                    errCount++;
                    continue;
                } else {
                    jmiMember.setBankaddress(bankaddress);
                }
                
                if (StringUtils.isEmpty(bankcard))
                {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "银行账号不能为空" + content + "</font>");
                    errCount++;
                    continue;
                } else {
                    jmiMember.setBankcard(bankcard);
                } 
                
                if (StringUtils.isEmpty(remark))
                {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "备注不能为空" + content + "</font>");
                    errCount++;
                    continue;
                }  else {
                    String orgRemark = jmiMember.getRemark();
                    jmiMember.setRemark((orgRemark==null?"":orgRemark)+" "+remark);
                }
                
                String tbank = StringUtils.isNotEmpty(jmiMember.getBank())?jmiMember.getBank():"";
                String tbankaddress = StringUtils.isNotEmpty(jmiMember.getBankaddress())?jmiMember.getBankaddress():"";
                String tbankcard = StringUtils.isNotEmpty(jmiMember.getBankcard())?jmiMember.getBankcard():"";
                String tremark = StringUtils.isNotEmpty(jmiMember.getRemark())?jmiMember.getRemark():"";
                String tuserCode = jmiMember.getUserCode();
                String sql = "update jmi_member m set m.bank='"+tbank+"' , m.bankaddress='"+tbankaddress+"' , m.bankcard='"+tbankcard+"' , m.remark='"+tremark+"' where m.user_code='"+tuserCode+"'";
                sqls.add(sql);
				if(!StringUtil.isEmpty(userCode)){
    	        	List list = jmiMemberManager.findJmiMemberById(userCode);
    	        	
    	        	JmiMember oldJmiMember = (JmiMember)list.get(0);
    	        	if(null != oldJmiMember){
    	        		 StringBuffer memberLogSql = new StringBuffer(" INSERT INTO JMI_MEMBER_LOG (LOG_ID, USER_CODE, USER_NAME, BEFORE_BANK, BEFORE_BANKADDRESS, BEFORE_BANKBOOK, BEFORE_BANKCARD, ");
    	                 memberLogSql.append(" BEFORE_BANKPROVINCE, BEFORE_BANKCITY, LOG_TIME, LOG_TYPE, LOG_USER_CODE, AFTER_BANK, AFTER_BANKADDRESS, AFTER_BANKBOOK, AFTER_BANKCARD, AFTER_BANKPROVINCE, AFTER_BANKCITY) ");
    	                 memberLogSql.append(" VALUES ");
    	                 memberLogSql.append(" (SEQ_MI.nextval, '"+tuserCode+"', '"+jmiMember.getFirstName()+jmiMember.getLastName()+"', '"+oldJmiMember.getBank()+"', '"+oldJmiMember.getBankaddress()+"', '"+oldJmiMember.getBankbook()+"', '"+oldJmiMember.getBankcard()+"', '"+oldJmiMember.getBankProvince()+"', '"+oldJmiMember.getBankCity()+"', ");
    	                 memberLogSql.append("  TO_DATE('"+MeteorUtil.doDateToConvert(new Date())+"', 'YYYY-MM-DD HH24:MI:SS'), '2', '"+defSysUser.getUserCode()+"',  ");
    	                 memberLogSql.append(" '"+tbank+"', '"+tbankaddress+"', '"+oldJmiMember.getBankbook()+"', '"+tbankcard+"', '"+oldJmiMember.getBankProvince()+"','"+oldJmiMember.getBankCity()+"') ");
    	                 sqls.add(memberLogSql.toString());
    	        	}
    	        	
    	    	}
            }
            
            eu.closeWorkbook(workbook);
            
            if (errCount == 0 && !sqls.isEmpty())
            {
                String[] sqlArr = new String[sqls.size()];
                this.jdbcTemplate.batchUpdate(sqls.toArray(sqlArr));
                this.saveMessage(request, "批量修改成功");
                return new ModelAndView(this.getSuccessView());
            }
            
            request.setAttribute("messages", messages);
            request.setAttribute("isFinished", true);
            request.setAttribute("errCount", errCount);
            
            return new ModelAndView(this.getFormView());
        }
        catch (IOException e)
        {
            this.saveMessage(request,
                    getText("bdBounsDeduct.importFile.failed"));
            log.error(e.getMessage());
        }
        catch (Exception e)
        {
            this.saveMessage(request,
                    getText("bdBounsDeduct.import.data.error"));
            log.error(e.getMessage());
        }
        
        return new ModelAndView(this.getSuccessView());
    }
}
