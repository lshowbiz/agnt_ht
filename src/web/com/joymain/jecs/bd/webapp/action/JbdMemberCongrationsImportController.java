package com.joymain.jecs.bd.webapp.action;

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
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.JbdMemberCongrations;
import com.joymain.jecs.bd.service.JbdMemberCongrationsManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdMemberCongrationsImportController extends BaseFormController
{
    private JmiMemberManager jmiMemberManager;
    
    private JbdMemberCongrationsManager jbdMemberCongrationsManager;
    
    public void setJbdMemberCongrationsManager(
            JbdMemberCongrationsManager jbdMemberCongrationsManager)
    {
        this.jbdMemberCongrationsManager = jbdMemberCongrationsManager;
    }
    
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager)
    {
        this.jmiMemberManager = jmiMemberManager;
    }
    
    public JbdMemberCongrationsImportController()
    {
        setCommandName("jbdMemberCongrations");
        setCommandClass(JbdMemberCongrations.class);
    }
    
    protected Object formBackingObject(HttpServletRequest request)
            throws Exception
    {
        JbdMemberCongrations jbdMemberCongrations = new JbdMemberCongrations();
        String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
        LinkedHashMap<String, String> starsMap = ListUtil.getListOptions(companyCode, "qualify.star.zero");
        request.setAttribute("starsMap", starsMap);
        return jbdMemberCongrations;
    }
    
    public ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception
    {
        if (log.isDebugEnabled())
        {
            log.debug("entering 'onSubmit' method...");
        }
        
        if ("jbdMemberCongrationsImport".equals(request.getParameter("strAction")))
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
                List<JbdMemberCongrations> lists = new ArrayList<JbdMemberCongrations>();
                int errCount = 0;
                //从第2行开始读,第一行为标题
                messages.add(LocaleUtil.getLocalText("bdBounsDeduct.impot.skip.first"));
                for (int i = 1; i < sheet.getRows(); i++)
                {
                    //会员编号,扣补类型,扣补金额,备注
                    String userCode = eu.getContents(sheet, 0, i);
                    String yearMonth = eu.getContents(sheet, 1, i);
                    
                    String starLevel = eu.getContents(sheet, 2, i);
                    String chineseName = eu.getContents(sheet, 3, i);
                    String englishName = eu.getContents(sheet, 4, i);
                    String remark = eu.getContents(sheet, 5, i);
                    
                    String content = " ([ " + userCode + " ] [ " + yearMonth + "] ["+starLevel+"]"
                            + ")";
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
                    JmiMember miMember = this.jmiMemberManager.getJmiMember(userCode.trim());
                    if (miMember == null)
                    {
                        messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "会员不存在" + content + "</font>");
                        errCount++;
                        continue;
                    }
                    if (!StringUtil.isInteger(yearMonth))
                    {
                        messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "财月必须为数字" + content + "</font>");
                        errCount++;
                        continue;
                    }
                    else
                    {
                        if(yearMonth.length()!=6) {
                            messages.add("<font color=red>"
                                    + message
                                    + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                    + " - " + "财月必须为6位的数字,如201401" + content + "</font>");
                            errCount++;
                            continue;
                        }
                        if(!WeekFormatUtil.findWmonthMap.containsKey(yearMonth)) {
                            messages.add("<font color=red>"
                                    + message
                                    + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                    + " - " + "财月不存在系统中" + content + "</font>");
                            errCount++;
                            continue;
                        }
                    } 
                    
                    if (!Constants.sysListMap.get("qualify.star.zero").containsKey(starLevel))
                    {
                        messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "奖衔级别必须为0-9的数字" + content + "</font>");
                        errCount++;
                        continue;
                    }
                    if (StringUtils.isEmpty(chineseName))
                    {
                        messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "中文名不能为空" + content + "</font>");
                        errCount++;
                        continue;
                    }
                    if (StringUtils.isEmpty(englishName))
                    {
                        messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "英文名不能为空" + content + "</font>");
                        errCount++;
                        continue;
                    }
                    if (remark != null && remark.length() > 2000)
                    {
                        messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "备注输入过长" + content + "</font>");
                        errCount++;
                        continue;
                    }
                    
                    JbdMemberCongrations jbdMemberCongrations = new JbdMemberCongrations();
                    jbdMemberCongrations.setUserCode(userCode);
                    jbdMemberCongrations.setYearMonth(Integer.valueOf(yearMonth));
                    jbdMemberCongrations.setStarLevel(Integer.valueOf(starLevel));
                    jbdMemberCongrations.setChineseName(chineseName);
                    jbdMemberCongrations.setEnglishName(englishName);
                    jbdMemberCongrations.setRemark(remark);
                    SysUser defSysUser = SessionLogin.getLoginUser(request);
                    jbdMemberCongrations.setCreateUser(defSysUser.getUserCode());
                    jbdMemberCongrations.setCreateTime(new Date());
                    
                    lists.add(jbdMemberCongrations);
                    
                    //messages.add(message + LocaleUtil.getLocalText("bdBounsDeduct.import.OK") + content);
                }
                
                eu.closeWorkbook(workbook);
                
                if (errCount == 0 && !lists.isEmpty())
                {
                    this.jbdMemberCongrationsManager.saveImportJbdMemberCongrations(lists);
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
        }
        
        return new ModelAndView(this.getSuccessView());
    }
}
