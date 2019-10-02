package com.joymain.jecs.fi.webapp.action;

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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.fi.model.JfiDepositMoney;
import com.joymain.jecs.fi.model.JfiDepositSend;
import com.joymain.jecs.fi.service.JfiDepositSendManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JfiDepositImportController extends BaseFormController
{
    
    private JmiMemberManager jmiMemberManager = null;
    private JdbcTemplate jdbcTemplate;
    private JfiDepositSendManager jfiDepositSendManager;
    public void setJfiDepositSendManager(JfiDepositSendManager jfiDepositSendManager) {
		this.jfiDepositSendManager = jfiDepositSendManager;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
        this.jmiMemberManager = jmiMemberManager;
    }
    
    public JfiDepositImportController()
    {
        setCommandName("jmiMember");
        setCommandClass(JmiMember.class);
    }
    
    protected Object formBackingObject(HttpServletRequest request)
            throws Exception
    {
        JmiMember jmiMember = new JmiMember();
        return jmiMember;
    }
    
    public ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception
    {
            
        try
        {
            SysUser defSysUser = SessionLogin.getLoginUser(request);
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
            
            if(sheet.getRows()>5001){

                this.saveMessage(request, "导入数据过多，一次只能导入5000条记录");
                return showForm(request, response, errors);
            }
            
            
            List<String> messages = new ArrayList<String>();
            int errCount = 0;
            //从第2行开始读,第一行为标题
            messages.add(LocaleUtil.getLocalText("bdBounsDeduct.impot.skip.first"));

			Date curDate=new Date();
            List<JfiDepositSend> jfiDepositSends=new ArrayList<JfiDepositSend>();
            for (int i = 1; i < sheet.getRows(); i++)
            {
                String userCode = eu.getContents(sheet, 0, i);
                String userName = eu.getContents(sheet, 1, i);
                String wyear = eu.getContents(sheet, 2, i);
                String wweek = eu.getContents(sheet, 3, i);
                String depositMoney = eu.getContents(sheet, 4, i);
                String remark = eu.getContents(sheet, 5, i);
                
                String content = " ([ " + userCode + " ] [ " + userName + " ] [ " + wyear + " ] [ " + wweek + " ] [ " + depositMoney + " ]  [ " + remark + " ])";
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
                if (StringUtils.isEmpty(userName))
                {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "会员姓名不能为空" + content + "</font>");
                    errCount++;
                    continue;
                }

                if (StringUtils.isEmpty(wyear))
                {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "财年不能为空" + content + "</font>");
                    errCount++;
                    continue;
                }
                if (StringUtils.isEmpty(wweek))
                {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "期别不能为空" + content + "</font>");
                    errCount++;
                    continue;
                }
                if (StringUtils.isEmpty(depositMoney))
                {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "金额不能为空" + content + "</font>");
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
                if(WeekFormatUtil.findWweekMap.get(wweek)==null){
                	   messages.add("<font color=red>"
                               + message
                               + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                               + " - " + "期别不存在" + content + "</font>");
                       errCount++;
                       continue;
                }

                if(!StringUtil.isDouble(depositMoney)){
                	   messages.add("<font color=red>"
                               + message
                               + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                               + " - " + "保证金金额必须只能是数字" + content + "</font>");
                       errCount++;
                       continue;
                }
                JfiDepositSend jfiDepositSend=new JfiDepositSend();
                jfiDepositSend.setCreateNo(defSysUser.getUserCode());
                jfiDepositSend.setCreateTime(curDate);
                jfiDepositSend.setDepositMoney(new BigDecimal(depositMoney));
                jfiDepositSend.setRemark(remark);
                jfiDepositSend.setStatus("1");
                jfiDepositSend.setUserCode(userCode);
                jfiDepositSend.setUserName(userName);
                jfiDepositSend.setWweek(StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f", wweek)));
                jfiDepositSend.setWyear(StringUtil.formatInt(wyear));
                jfiDepositSend.setCompanyCode(jmiMember.getCompanyCode());
                jfiDepositSends.add(jfiDepositSend);
                //System.out.println(i);
            }
            
            eu.closeWorkbook(workbook);
            
            
            
            
            if (errCount == 0 && !jfiDepositSends.isEmpty())
            {
            	jfiDepositSendManager.saveJfiDepositSend(jfiDepositSends);
                this.saveMessage(request, "导入成功");
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
