package com.joymain.jecs.mi.webapp.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

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

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.mi.model.JmiGradeLock;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiGradeLockManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class BatchUpdateJmiGradeLockController extends BaseFormController
{
    
    private JmiMemberManager jmiMemberManager = null;
    private JdbcTemplate jdbcTemplate;
    private BdPeriodManager bdPeriodManager;
    private JmiGradeLockManager jmiGradeLockManager = null;
    public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
        this.jmiMemberManager = jmiMemberManager;
    }
    public void setJmiGradeLockManager(JmiGradeLockManager jmiGradeLockManager) {
        this.jmiGradeLockManager = jmiGradeLockManager;
    }
    
    public BatchUpdateJmiGradeLockController()
    {
        setCommandName("batchUpdateJmiGradeLock");
        setCommandClass(JmiGradeLock.class);
    }
    
    protected Object formBackingObject(HttpServletRequest request)
            throws Exception
    {
    	JmiGradeLock jmiGradeLock = new JmiGradeLock();
        
//        List sysBankList=sysBankManager.getSysBankByCompanyCode("CN");
//        request.setAttribute("sysBankList", sysBankList);
        
        return jmiGradeLock;
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
            int errCount = 0;
            int suCount = 0;
            //从第2行开始读,第一行为标题
            messages.add(LocaleUtil.getLocalText("bdBounsDeduct.impot.skip.first"));
            
            String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
            LinkedHashMap<String, String> gradeTypeMap = ListUtil.getListOptionsByLocal(companyCode, "grade.type");
            
            JmiGradeLock jmiGradeLock = null;
            List<JmiGradeLock> jmiGradeLockList = new ArrayList<JmiGradeLock>();
            for (int i = 1; i < sheet.getRows(); i++)
            {
                //会员编号,期别,身份
                String userCode = eu.getContents(sheet, 0, i);
                String week = eu.getContents(sheet, 1, i);
                String ident = eu.getContents(sheet, 2, i);
                
                String content = " ([ " + userCode + ","+week+","+ident+" ])";
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
                
                if (StringUtils.isEmpty(week))
                {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "期别不能为空" + content + "</font>");
                    errCount++;
                    continue;
                } else {
                	BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(WeekFormatUtil.getFormatWeek("f",week.trim()));
                	if(bdPeriod==null){
                		messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "期别不存在" + content + "</font>");
                        errCount++;
                        continue;
                	}
                	//已结算的期别不能增加
                	if(bdPeriod.getBonusSend()!=null&&bdPeriod.getBonusSend()==1){
                		messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "已结算的期别不能修改身份" + content + "</font>");
                        errCount++;
                        continue;
                	}
                }
                
                if (StringUtils.isEmpty(ident))
                {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "身份不能为空" + content + "</font>");
                    errCount++;
                    continue;
                } else if(!gradeTypeMap.containsValue(ident.trim())) {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "身份不正确" + content + "</font>");
                    errCount++;
                    continue;
                }else {
                	for(String s:gradeTypeMap.keySet()){
                		if(gradeTypeMap.get(s).equals(ident)){
                			ident = s ;
                			break;
                		}
                	}
                }
                
                jmiGradeLock = new JmiGradeLock();
                jmiGradeLock.setUserCode(userCode);
                jmiGradeLock.setGradeType(Integer.valueOf(ident));
                
                JmiGradeLock resJmiGradeLock=jmiGradeLockManager.getJmiGradeLockByUserCode(userCode.trim(), new Integer(WeekFormatUtil.getFormatWeek("f",week.trim())));
    	    	
            	
            	if(resJmiGradeLock!=null){
            		resJmiGradeLock.setGradeType(Integer.valueOf(ident));
            		jmiGradeLock=resJmiGradeLock;
            	}

        		jmiGradeLock.setCreateNo(defSysUser.getUserCode());
        		jmiGradeLock.setCreateTime(new Date());
        		

            	if(resJmiGradeLock==null){
            		jmiGradeLock.setValidWeek(new Integer(WeekFormatUtil.getFormatWeek("f",week.toString())));
            	}
            	
            	jmiGradeLockList.add(jmiGradeLock);
            }
            
            eu.closeWorkbook(workbook);
            
            if (errCount == 0)
            {
        		jmiGradeLockManager.saveJmiGradeLockList(jmiGradeLockList ,defSysUser);
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
