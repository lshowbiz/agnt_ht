package com.joymain.jecs.mi.webapp.action;

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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiSpecialList;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiSpecialListManager;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiSpecialListImportController extends BaseFormController
{
    
    private JmiMemberManager jmiMemberManager = null;
    private JdbcTemplate jdbcTemplate;
    private JmiSpecialListManager jmiSpecialListManager = null;

    public void setJmiSpecialListManager(JmiSpecialListManager jmiSpecialListManager) {
        this.jmiSpecialListManager = jmiSpecialListManager;
    }
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
        this.jmiMemberManager = jmiMemberManager;
    }
    
    public JmiSpecialListImportController()
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
            //从第2行开始读,第一行为标题
            messages.add(LocaleUtil.getLocalText("bdBounsDeduct.impot.skip.first"));

			

			String scType=request.getParameter("scType");
			
			
            List<JmiSpecialList> jmiSpecialList=new ArrayList<JmiSpecialList>();
            for (int i = 1; i < sheet.getRows(); i++)
            {
                String userCode = eu.getContents(sheet, 0, i);
                
                String content = " ([ " + userCode + " ])";
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

                JmiSpecialList jmiSpecialListObj=new JmiSpecialList();
                jmiSpecialListObj.setUserCode(userCode);
                jmiSpecialListObj.setScType(scType);
                jmiSpecialList.add(jmiSpecialListObj);
            }
            
            eu.closeWorkbook(workbook);
            
           
            
            
            if (errCount == 0 && !jmiSpecialList.isEmpty())
            {
            	jmiSpecialListManager.saveJmiSpecialList(jmiSpecialList);
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
