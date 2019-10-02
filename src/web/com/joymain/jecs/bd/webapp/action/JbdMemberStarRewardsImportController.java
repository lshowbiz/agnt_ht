package com.joymain.jecs.bd.webapp.action;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

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
import com.joymain.jecs.bd.model.JbdMemberStarRewards;
import com.joymain.jecs.bd.service.JbdMemberStarRewardsManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdMemberStarRewardsImportController extends BaseFormController
{
    private JmiMemberManager jmiMemberManager;
    
    private JbdMemberStarRewardsManager jbdMemberStarRewardsManager;
    
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy");
    
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager)
    {
        this.jmiMemberManager = jmiMemberManager;
    }
    
    public void setJbdMemberStarRewardsManager(
            JbdMemberStarRewardsManager jbdMemberStarRewardsManager)
    {
        this.jbdMemberStarRewardsManager = jbdMemberStarRewardsManager;
    }
    
    
    public JbdMemberStarRewardsImportController()
    {
        setCommandName("jbdMemberStarRewards");
        setCommandClass(JbdMemberStarRewards.class);
    }
    
    protected Object formBackingObject(HttpServletRequest request)
            throws Exception
    {
        JbdMemberStarRewards jbdMemberStarRewards = new JbdMemberStarRewards();
        String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
        String currentYear = SDF.format(new Date());
        try {
            LinkedHashMap<String, String> rewardsMap = ListUtil.getListOptions(companyCode, "star.rewards.repolicy."+currentYear);
            request.setAttribute("currentYear", currentYear);
            request.setAttribute("rewardsMap", rewardsMap);
        }catch(Exception e){
            e.printStackTrace();
        }
        return jbdMemberStarRewards;
    }
    
    public ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception
    {
        if (log.isDebugEnabled())
        {
            log.debug("entering 'onSubmit' method...");
        }
        
        if ("jbdMemberStarRewardsImport".equals(request.getParameter("strAction")))
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
                List<JbdMemberStarRewards> rewardLists = new ArrayList<JbdMemberStarRewards>();
                int errCount = 0;
                //从第2行开始读,第一行为标题
                messages.add(LocaleUtil.getLocalText("bdBounsDeduct.impot.skip.first"));
                for (int i = 1; i < sheet.getRows(); i++)
                {
                    //会员编号,扣补类型,扣补金额,备注
                    String userCode = eu.getContents(sheet, 0, i);
                    String fyear = eu.getContents(sheet, 1, i);
                    
                    String rewards = eu.getContents(sheet, 2, i);
                    String isReach = eu.getContents(sheet, 3, i);
                    String remark = eu.getContents(sheet, 4, i);
                    String memberRemark = eu.getContents(sheet, 5, i);
                    
                    String content = " ([ " + userCode + " ] [ " + fyear + "] ["+rewards+"]"
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
                    if (!StringUtil.isInteger(fyear))
                    {
                        messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "财年必须为数字" + content + "</font>");
                        errCount++;
                        continue;
                    }
                    else
                    {
                        if (fyear.length()!=4)
                        {
                            messages.add("<font color=red>"
                                    + message
                                    + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                    + " - " + "财年必须为4位的数字" + content + "</font>");
                            errCount++;
                            continue;
                        }
                    }
                    try {
                        if (!Constants.sysListMap.get("star.rewards.repolicy."+fyear).containsKey(rewards))
                        {
                            messages.add("<font color=red>"
                                    + message
                                    + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                    + " - " + "奖励政策必须为"+Constants.sysListMap.get("star.rewards.repolicy."+fyear).keySet()+"的数字" + content + "</font>");
                            errCount++;
                            continue;
                        }
                    }catch(Exception e){
                        messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "请先配置奖衔奖励政策参数列表" + content + "</font>");
                        errCount++;
                        continue;
                    }
                    
                    if (!"0".equals(isReach)&&!"1".equals(isReach))
                    {
                        messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "是否达成必须为0或1" + content + "</font>");
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
                    if (memberRemark != null && memberRemark.length() > 2000)
                    {
                        messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "会员备注输入过长" + content + "</font>");
                        errCount++;
                        continue;
                    }
                    
                    JbdMemberStarRewards jbdMemberStarRewards = new JbdMemberStarRewards();
                    jbdMemberStarRewards.setUserCode(userCode);
                    jbdMemberStarRewards.setFyear(Integer.valueOf(fyear));
                    jbdMemberStarRewards.setRewards(Integer.valueOf(rewards));
                    jbdMemberStarRewards.setIsReach(isReach);
                    jbdMemberStarRewards.setRemark(remark);
                    jbdMemberStarRewards.setMemberRemark(memberRemark);
                    SysUser defSysUser = SessionLogin.getLoginUser(request);
                    jbdMemberStarRewards.setCreateUser(defSysUser.getUserCode());
                    jbdMemberStarRewards.setCreateTime(new Date());
                    
                    rewardLists.add(jbdMemberStarRewards);
                    
                    //messages.add(message + LocaleUtil.getLocalText("bdBounsDeduct.import.OK") + content);
                }
                
                eu.closeWorkbook(workbook);
                
                if (errCount == 0 && !rewardLists.isEmpty())
                {
                    jbdMemberStarRewardsManager.saveImportJbdMemberStarRewards(rewardLists);
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
