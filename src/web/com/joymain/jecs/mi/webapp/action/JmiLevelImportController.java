package com.joymain.jecs.mi.webapp.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiValidLevelList;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiValidLevelListManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiLevelImportController extends BaseFormController
{
    private JmiMemberManager jmiMemberManager;
    private BdPeriodManager bdPeriodManager;
    private JmiValidLevelListManager jmiValidLevelListManager;
    public void setJmiValidLevelListManager(
			JmiValidLevelListManager jmiValidLevelListManager) {
		this.jmiValidLevelListManager = jmiValidLevelListManager;
	}

	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public void setJmiMemberManager(JmiMemberManager jmiMemberManager)
    {
        this.jmiMemberManager = jmiMemberManager;
    }
    

    public JmiLevelImportController()
    {
        setCommandName("jmiValidLevelList");
        setCommandClass(JmiValidLevelList.class);
    }
    
    protected Object formBackingObject(HttpServletRequest request)
            throws Exception
    {
    	JmiValidLevelList jmiValidLevelList = new JmiValidLevelList();
        String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();

        return jmiValidLevelList;
    }
    
    public ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception
    {
        if (log.isDebugEnabled())
        {
            log.debug("entering 'onSubmit' method...");
        }
        
        if ("jmiLevelImportImport".equals(request.getParameter("strAction")))
        {

        	SysUser defSysUser=SessionLogin.getLoginUser(request);
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
                List<JmiValidLevelList> list = new ArrayList<JmiValidLevelList>();
                int errCount = 0;
                
                if(sheet.getRows()>1001){
                    //文件读取错误
                    errors.reject("数据过多","数据过多");
                    return showForm(request, response, errors);
                }
                
                
                //从第2行开始读,第一行为标题
                messages.add(LocaleUtil.getLocalText("bdBounsDeduct.impot.skip.first"));
                for (int i = 1; i < sheet.getRows(); i++)
                {
                	
                    String userCode = eu.getContents(sheet, 0, i);
                    String wweek = eu.getContents(sheet, 1, i);
                    
                    String memberLevel = eu.getContents(sheet, 2, i);
                    
                    String content = " ([ " + userCode + " ] [ " + wweek + "] ["+memberLevel+"]"
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
                    if (!StringUtil.isInteger(wweek)) {
                        messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "期别必须为数字" + content + "</font>");
                        errCount++;
                        continue;
                    } else if(wweek.length()!=6) {
                            messages.add("<font color=red>"
                                    + message
                                    + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                    + " - " + "期别必须为6位的数字" + content + "</font>");
                            errCount++;
                            continue;
                    }else{

                    	BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(WeekFormatUtil.getFormatWeek("f",wweek.toString()));
                    	if(bdPeriod==null || (bdPeriod.getBonusSend()!=null && bdPeriod.getBonusSend()==1) ){
                    		messages.add("<font color=red>"
                                    + message
                                    + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                    + " - " + "请输入未结算期别" + content + "</font>");
                            errCount++;
                            continue;
                    	}
                    	
                    }
                    
                    
                    
                    
                    if (!Constants.sysListMap.get("member.level.no").containsKey(memberLevel))
                    {
                        messages.add("<font color=red>"
                                + message
                                + LocaleUtil.getLocalText("bdBounsDeduct.import.ERR")
                                + " - " + "级别不存在" + content + "</font>");
                        errCount++;
                        continue;
                    }
               
                    JmiValidLevelList jmiValidLevelList=new JmiValidLevelList();
                    
                    
                    jmiValidLevelList.setUserCode(userCode);;
                    jmiValidLevelList.setNewMemberLevel(StringUtil.formatInt(memberLevel));
                    jmiValidLevelList.setWweek(StringUtil.formatInt(wweek));
                    jmiValidLevelList.setOldMemberLevel(miMember.getMemberLevel());
                    
                    
                    list.add(jmiValidLevelList);
                    
                    //messages.add(message + LocaleUtil.getLocalText("bdBounsDeduct.import.OK") + content);
                }
                
                eu.closeWorkbook(workbook);
                
                if (errCount == 0 && !list.isEmpty())
                {
                	jmiValidLevelListManager.saveJmiValidLevelListImport(list, defSysUser);
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
