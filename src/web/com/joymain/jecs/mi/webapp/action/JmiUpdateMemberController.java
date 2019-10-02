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
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiUpdateMemberController extends BaseFormController
{
    
    private JmiMemberManager jmiMemberManager = null;
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
        this.jmiMemberManager = jmiMemberManager;
    }
    
    public JmiUpdateMemberController()
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

			Map paperTypeMap=ListUtil.getListOptions(SessionLogin.getLoginUser(request).getCompanyCode(), "papertype");
			
            List<Map> list=new ArrayList<Map>();
            for (int i = 1; i < sheet.getRows(); i++)
            {
                String userCode = eu.getContents(sheet, 0, i);
                String firstName = eu.getContents(sheet, 1, i);
                String lastName = eu.getContents(sheet, 2, i);
                String mobiletele = eu.getContents(sheet, 3, i);
                String papertype = eu.getContents(sheet, 4, i);
                String papernumber = eu.getContents(sheet, 5, i);
                
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

                if(!StringUtil.isEmpty(papertype)&&paperTypeMap.get(papertype)==null){

                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "证件类型不存在" + content + "</font>");
                    errCount++;
                    continue;
                }
                
                Map map=new HashMap();
                map.put("userCode", userCode);
                map.put("firstName", StringUtil.isEmpty(firstName)?"":firstName);
                map.put("lastName", StringUtil.isEmpty(lastName)?"":lastName);
                map.put("mobiletele", StringUtil.isEmpty(mobiletele)?"":mobiletele);
                map.put("papertype", StringUtil.isEmpty(papertype)?"":papertype);
                map.put("papernumber", StringUtil.isEmpty(papernumber)?"":papernumber);
                list.add(map);
            }
            
            eu.closeWorkbook(workbook);
            
            if(list.size()>1000){
				this.saveMessage(request, "数据过多，一次只能处理1000条数据");
				return new ModelAndView(getSuccessView());
            }
            
            
            if (errCount == 0 && !list.isEmpty())
            {
            	jmiMemberManager.saveInfoMembers(list);
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
