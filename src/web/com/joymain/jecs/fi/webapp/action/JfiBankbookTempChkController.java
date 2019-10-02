package com.joymain.jecs.fi.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.fi.model.JfiBankbookTemp;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.fi.service.JfiBankbookTempManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 存折条目确认
 * 
 * @author Alvin
 * 
 */
public class JfiBankbookTempChkController extends BaseController implements Controller
{
    private final Log log = LogFactory.getLog(JfiBankbookTempChkController.class);
    
    private JfiBankbookTempManager jfiBankbookTempManager = null;
    
    private JfiBankbookJournalManager jfiBankbookJournalManager = null;
    
    private AlCompanyManager alCompanyManager = null;
    
    private SysUserManager sysUserManager = null;
    
    public void setSysUserManager(SysUserManager sysUserManager)
    {
        this.sysUserManager = sysUserManager;
    }
    
    public void setAlCompanyManager(AlCompanyManager alCompanyManager)
    {
        this.alCompanyManager = alCompanyManager;
    }
    
    public void setJfiBankbookJournalManager(JfiBankbookJournalManager jfiBankbookJournalManager)
    {
        this.jfiBankbookJournalManager = jfiBankbookJournalManager;
    }
    
    public void setJfiBankbookTempManager(JfiBankbookTempManager jfiBankbookTempManager)
    {
        this.jfiBankbookTempManager = jfiBankbookTempManager;
    }
    
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        if (log.isDebugEnabled())
        {
            log.debug("entering 'handleRequest' method...");
        }
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        RequestUtil.freshSession(request, "listFiBankbookTempsChk", 3l);
        
        if ("doNotDealFiBankbookTemp".equals(request.getParameter("strAction")))
        {
            if ("C".equals(loginSysUser.getUserType()))
            {
                if (RequestUtil.saveOperationSession(request, "doNotDealFiBankbookTemp", 10l) != 0)
                {
                    return new ModelAndView("redirect:jfiBankbookTempsChk.html?strAction=listFiBankbookTempsChk");
                }
            }
            else if ("M".equals(loginSysUser.getUserType()))
            {
                if (RequestUtil.saveOperationSession(request, "doNotDealFiBankbookTemp", 10l) != 0)
                {
                    return new ModelAndView("redirect:jfiBankbookTempsChk.html?strAction=listFiBankbookTempsChk");
                }
            }
            // 转为不处理
            String[] tempIds = request.getParameter("strTempIds").split(",");
            List<JfiBankbookTemp> jfiBankbookTemps = new ArrayList<JfiBankbookTemp>();
            for (int i = 0; i < tempIds.length; i++)
            {
                if (!StringUtils.isEmpty(tempIds[i]))
                {
                    JfiBankbookTemp jfiBankbookTemp =
                        this.jfiBankbookTempManager.getJfiBankbookTemp(tempIds[i]);
                    if (jfiBankbookTemp.getStatus() == 1)
                    {
                        jfiBankbookTemp.setStatus(3);
                        jfiBankbookTemps.add(jfiBankbookTemp);
                    }
                }
            }
            this.jfiBankbookTempManager.saveJfiBankbookTemps(jfiBankbookTemps);
            saveMessage(request, LocaleUtil.getLocalText("fiBankbookTemp.doNotDealed"));
            
            ModelAndView mv = new ModelAndView("redirect:jfiBankbookTempsChk.html");
            mv.addObject("strAction", "listFiBankbookTempsChk");
            mv.addObject("needReload", "1");
            return mv;
        }
        else if ("notVerifiedFiBankbookTemp".equals(request.getParameter("strAction")))
        {
            if ("C".equals(loginSysUser.getUserType()))
            {
                if (RequestUtil.saveOperationSession(request, "notVerifiedFiBankbookTemp", 10l) != 0)
                {
                    return new ModelAndView("redirect:jfiBankbookTempsChk.html?strAction=listFiBankbookTempsChk");
                }
            }
            else if ("M".equals(loginSysUser.getUserType()))
            {
                if (RequestUtil.saveOperationSession(request, "notVerifiedFiBankbookTemp", 10l) != 0)
                {
                    return new ModelAndView("redirect:jfiBankbookTempsChk.html?strAction=listFiBankbookTempsChk");
                }
            }
            // 转为未核实
            String[] tempIds = request.getParameter("strTempIds").split(",");
            List<JfiBankbookTemp> jfiBankbookTemps = new ArrayList<JfiBankbookTemp>();
            for (int i = 0; i < tempIds.length; i++)
            {
                if (!StringUtils.isEmpty(tempIds[i]))
                {
                    JfiBankbookTemp jfiBankbookTemp =
                        this.jfiBankbookTempManager.getJfiBankbookTemp(tempIds[i]);
                    if (jfiBankbookTemp.getStatus() == 3)
                    {
                        jfiBankbookTemp.setStatus(1);
                        jfiBankbookTemps.add(jfiBankbookTemp);
                    }
                }
            }
            this.jfiBankbookTempManager.saveJfiBankbookTemps(jfiBankbookTemps);
            saveMessage(request, LocaleUtil.getLocalText("fiBankbookTemp.notVerified"));
            
            ModelAndView mv = new ModelAndView("redirect:jfiBankbookTempsChk.html");
            mv.addObject("strAction", "listFiBankbookTempsChk");
            mv.addObject("needReload", "1");
            return mv;
        }
        else if ("verifyFiBankbookTemp".equals(request.getParameter("strAction")))
        {
            if ("C".equals(loginSysUser.getUserType()))
            {
                if (RequestUtil.saveOperationSession(request, "verifyFiBankbookTemp", 10l) != 0)
                {
                    ModelAndView mv =  new ModelAndView("redirect:jfiBankbookTempsChk.html?strAction=listFiBankbookTempsChk");
                    mv.addObject("status", "1");
                    mv.addObject("search","查询");
                    return mv;
                }
            }
            else if ("M".equals(loginSysUser.getUserType()))
            {
                if (RequestUtil.saveOperationSession(request, "verifyFiBankbookTemp", 10l) != 0)
                {
                    ModelAndView mv = new ModelAndView("redirect:jfiBankbookTempsChk.html?strAction=listFiBankbookTempsChk");
                    mv.addObject("status", "1");
                    mv.addObject("search","查询");
                    return mv;
                }
            }
            // 确认
            String[] tempIds = request.getParameter("strTempIds").split(",");
            try
            {
                SysUser sysUser = SessionLogin.getLoginUser(request);
                for (int i = 0; i < tempIds.length; i++)
                {
                    if (!StringUtils.isEmpty(tempIds[i]))
                    {
                        try
                        {
                            //this.jfiBankbookJournalManager.saveJfiBankbookTempCheck(tempIds[i],sysUser);
                            //Modify By WuCF 20160816 调用统一的过程 
                        	jfiBankbookJournalManager.createJpoBankBookPayList(tempIds[i],loginSysUser.getUserCode());
                        }
                        catch (AppException ae)
                        {
                            log.error("错误:", ae);
                            continue;
                        }
                    }
                }
            }
            catch (AppException e)
            {
                saveMessage(request,
                    LocaleUtil.getLocalText("error.fiBankbookJournal.balance.not.enough"));
                ModelAndView mv = new ModelAndView("redirect:jfiBankbookTempsChk.html");
                mv.addObject("strAction", "listFiBankbookTempsChk");
                mv.addObject("needReload", "1");
                mv.addObject("search","查询");
                return mv;
            }
            
            saveMessage(request, LocaleUtil.getLocalText("fiBankbookTemp.verified"));
            
            ModelAndView mv = new ModelAndView("redirect:jfiBankbookTempsChk.html");
            mv.addObject("strAction", "listFiBankbookTempsChk");
            mv.addObject("needReload", "1");
            mv.addObject("status", "1");
            mv.addObject("search","查询");
            return mv;
        }
        
        CommonRecord crm = RequestUtil.toCommonRecord(request);
        /** add by hdg 2016-12-23 */
        CustomField[] fields = crm.getFields();
		w:for(CustomField field : fields) {
			String name=field.getName();
			if(!StringUtils.isEmpty(name)) {
				if("endcreatetime".equals(name)) {
					String value = (String)field.getValue();
					if(!StringUtils.isEmpty(value)) {
						field.setValue(value+" 23:59:59");
					}
					break w;
				}
			}
		}
		 /** add by hdg 2016-12-23 */
        if (StringUtils.isEmpty(request.getParameter("companyCode")))
        {
            crm.addField("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());
        }
        
        Pager pager = new Pager("jfiBankbookTempListTable", request, 500);
        
        List jfiBankbookTemps = null;
        if (request.getParameter("search") != null)
        {
            if (!StringUtils.isEmpty(crm.getString("sysUser.jmiMember.lastNameKana", ""))
                || !StringUtils.isEmpty(crm.getString("sysUser.jmiMember.firstNameKana", ""))
                || !StringUtils.isEmpty(crm.getString("sysUser.userCode", ""))
                || !StringUtils.isEmpty(crm.getString("moneyType", ""))
                || !StringUtils.isEmpty(crm.getString("status", ""))
                || !StringUtils.isEmpty(crm.getString("startCreateTime", ""))
                || !StringUtils.isEmpty(crm.getString("endCreateTime", ""))
                || !StringUtils.isEmpty(crm.getString("createrName", "")))
            {
                if ("C".equals(loginSysUser.getUserType()))
                {
                    if (RequestUtil.saveOperationSession(request, "listFiBankbookTempsChk", 3l) != 0)
                    {
                        return new ModelAndView("redirect:jfiBankbookTempsChk.html?strAction=listFiBankbookTempsChk");
                    }
                }
                else if ("M".equals(loginSysUser.getUserType()))
                {
                    if (RequestUtil.saveOperationSession(request, "listFiBankbookTempsChk", 3l) != 0)
                    {
                        return new ModelAndView("redirect:jfiBankbookTempsChk.html?strAction=listFiBankbookTempsChk");
                    }
                }
                jfiBankbookTemps = this.jfiBankbookTempManager.getJfiBankbookTempsByCrm(crm, pager);
                Map incExpMap = this.jfiBankbookTempManager.getIncExpStatMap(crm);
                request.setAttribute("incExpMap", incExpMap);
            }
            
        }
        // 根据数据重新设置分页数据
        request.setAttribute("jfiBankbookTempListTable_totalRows", pager.getTotalObjects());
        ModelAndView mv =
            new ModelAndView("fi/jfiBankbookTempChkList", Constants.JFIBANKBOOKTEMP_LIST,
                jfiBankbookTemps);
        mv.addObject("strAction", "listFiBankbookTempsChk");
        return mv;
    }
}