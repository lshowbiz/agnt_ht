package com.joymain.jecs.mi.webapp.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;

public class BatchUpdateStarRemarkController extends BaseFormController
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
    
    public BatchUpdateStarRemarkController()
    {
        setCommandName("batchUpdateStarRemark");
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
            List<String> sqls = new ArrayList<String>();
            int errCount = 0;
            //从第2行开始读,第一行为标题
            messages.add(LocaleUtil.getLocalText("bdBounsDeduct.impot.skip.first"));
            for (int i = 1; i < sheet.getRows(); i++)
            {
                //会员编号,扣补类型,扣补金额,备注
                String userCode = eu.getContents(sheet, 0, i);
                String spouseName = eu.getContents(sheet, 1, i);
                String spouseIdno = eu.getContents(sheet, 2, i);
                String titleRemark = eu.getContents(sheet, 3, i);
                
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
                
                if (StringUtils.isEmpty(userCode))
                {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "会员编号不能为空" + content + "</font>");
                    errCount++;
                    continue;
                }
                
                if (StringUtils.isNotEmpty(titleRemark))
                {
                    String orgTitleRemark = jmiMember.getTitleRemark();
                    jmiMember.setTitleRemark((orgTitleRemark==null?"":orgTitleRemark)+" "+titleRemark);
                }
                
                if (StringUtils.isEmpty(spouseName))
                {
                    messages.add("<font color=red>"
                            + message
                            + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                            + " - " + "配偶姓名不能为空" + content + "</font>");
                    errCount++;
                    continue;
                } else {
                    jmiMember.setSpouseName(spouseName);
                }
                
                if(StringUtils.isNotEmpty(spouseIdno)) {
                    if(!this.jmiMemberManager.getIdCardCheck(spouseIdno)&& !this.jmiMemberManager.getIdCardCheckTw(spouseIdno)){
                        messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "身份证格式错误" + content + "</font>");
                        errCount++;
                        continue;
                    } else {
                        jmiMember.setSpouseIdno(spouseIdno);
                    }
                }
                
                String tspouseName = StringUtils.isNotEmpty(jmiMember.getSpouseName())?jmiMember.getSpouseName():"";
                String tspouseIdno = StringUtils.isNotEmpty(jmiMember.getSpouseIdno())?jmiMember.getSpouseIdno():"";
                String ttitleRemark = StringUtils.isNotEmpty(jmiMember.getTitleRemark())?jmiMember.getTitleRemark():"";
                String tuserCode = jmiMember.getUserCode();
                String sql = "update jmi_member m set m.spouse_name='"+tspouseName+"' , m.spouse_idno='"+tspouseIdno+"' , m.title_remark='"+ttitleRemark+"' where m.user_code='"+tuserCode+"'";
                sqls.add(sql);
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
