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
import com.joymain.jecs.fi.model.FiProductPointTemp;
import com.joymain.jecs.fi.service.FiProductPointJournalManager;
import com.joymain.jecs.fi.service.FiProductPointTempManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 存折条目确认
 * 
 * @author Alvin
 * 
 */
public class FiProductPointTempChkController extends BaseController implements Controller
{
    private final Log log = LogFactory.getLog(FiProductPointTempChkController.class);
    
    private FiProductPointTempManager fiProductPointTempManager = null;
    
    private FiProductPointJournalManager fiProductPointJournalManager = null;
    
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
    
    public void setFiProductPointJournalManager(FiProductPointJournalManager fiProductPointJournalManager)
    {
        this.fiProductPointJournalManager = fiProductPointJournalManager;
    }
    
    public void setFiProductPointTempManager(FiProductPointTempManager fiProductPointTempManager)
    {
        this.fiProductPointTempManager = fiProductPointTempManager;
    }
    
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        if (log.isDebugEnabled())
        {
            log.debug("entering 'handleRequest' method...");
        }
        RequestUtil.freshSession(request, "listFiProductPointTempsChkJJ", 3l);
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        
        if ("doNotDealFiProductPointTemp".equals(request.getParameter("strAction")))
        {
            if ("C".equals(loginSysUser.getUserType()))
            {
                if (RequestUtil.saveOperationSession(request, "doNotDealFiProductPointTemp", 10l) != 0)
                {
                    return new ModelAndView("redirect:fiProductPointTempsChk.html?strAction=listFiProductPointTempsChkJJ");
                }
            }
            else if ("M".equals(loginSysUser.getUserType()))
            {
                if (RequestUtil.saveOperationSession(request, "doNotDealFiProductPointTemp", 10l) != 0)
                {
                    return new ModelAndView("redirect:fiProductPointTempsChk.html?strAction=listFiProductPointTempsChkJJ");
                }
            }
            // 转为不处理
            String[] tempIds = request.getParameter("strTempIds").split(",");
            List<FiProductPointTemp> fiProductPointTemps = new ArrayList<FiProductPointTemp>();
            for (int i = 0; i < tempIds.length; i++)
            {
                if (!StringUtils.isEmpty(tempIds[i]))
                {
                    FiProductPointTemp fiProductPointTemp =
                        this.fiProductPointTempManager.getFiProductPointTemp(tempIds[i]);
                    if (fiProductPointTemp.getStatus() == 1)
                    {
                        fiProductPointTemp.setStatus(3);
                        fiProductPointTemps.add(fiProductPointTemp);
                    }
                }
            }
            this.fiProductPointTempManager.saveFiProductPointTemps(fiProductPointTemps);
            saveMessage(request, LocaleUtil.getLocalText("fiProductPointTemp.doNotDealed"));
            
            ModelAndView mv = new ModelAndView("redirect:fiProductPointTempsChk.html");
            mv.addObject("strAction", "listFiProductPointTempsChkJJ");
            mv.addObject("needReload", "1");
            return mv;
        }
        else if ("notVerifiedFiProductPointTemp".equals(request.getParameter("strAction")))
        {
            if ("C".equals(loginSysUser.getUserType()))
            {
                if (RequestUtil.saveOperationSession(request, "notVerifiedFiProductPointTemp", 10l) != 0)
                {
                    return new ModelAndView("redirect:fiProductPointTempsChk.html?strAction=listFiProductPointTempsChkJJ");
                }
            }
            else if ("M".equals(loginSysUser.getUserType()))
            {
                if (RequestUtil.saveOperationSession(request, "notVerifiedFiProductPointTemp", 10l) != 0)
                {
                    return new ModelAndView("redirect:fiProductPointTempsChk.html?strAction=listFiProductPointTempsChkJJ");
                }
            }
            // 转为未核实
            String[] tempIds = request.getParameter("strTempIds").split(",");
            List<FiProductPointTemp> fiProductPointTemps = new ArrayList<FiProductPointTemp>();
            for (int i = 0; i < tempIds.length; i++)
            {
                if (!StringUtils.isEmpty(tempIds[i]))
                {
                    FiProductPointTemp fiProductPointTemp =
                        this.fiProductPointTempManager.getFiProductPointTemp(tempIds[i]);
                    if (fiProductPointTemp.getStatus() == 3)
                    {
                        fiProductPointTemp.setStatus(1);
                        fiProductPointTemps.add(fiProductPointTemp);
                    }
                }
            }
            this.fiProductPointTempManager.saveFiProductPointTemps(fiProductPointTemps);
            saveMessage(request, LocaleUtil.getLocalText("fiProductPointTemp.notVerified"));
            
            ModelAndView mv = new ModelAndView("redirect:fiProductPointTempsChk.html");
            mv.addObject("strAction", "listFiProductPointTempsChkJJ");
            mv.addObject("needReload", "1");
            return mv;
        }
        else if ("verifyFiProductPointTemp".equals(request.getParameter("strAction")))
        {
            if ("C".equals(loginSysUser.getUserType()))
            {
                if (RequestUtil.saveOperationSession(request, "verifyFiProductPointTemp", 10l) != 0)
                {
                    ModelAndView mv = new ModelAndView("redirect:fiProductPointTempsChk.html?strAction=listFiProductPointTempsChkJJ");
                    mv.addObject("status", "1");
                    mv.addObject("search", "查询");
                    return mv;
                }
            }
            else if ("M".equals(loginSysUser.getUserType()))
            {
                if (RequestUtil.saveOperationSession(request, "verifyFiProductPointTemp", 10l) != 0)
                {
                    ModelAndView mv = new ModelAndView("redirect:fiProductPointTempsChk.html?strAction=listFiProductPointTempsChkJJ");
                    mv.addObject("status", "1");
                    mv.addObject("search", "查询");
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
                            this.fiProductPointJournalManager.saveFiProductPointTempCheck(tempIds[i],
                                sysUser);
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
                    LocaleUtil.getLocalText("error.fiProductPointJournal.balance.not.enough"));
                ModelAndView mv = new ModelAndView("redirect:fiProductPointTempsChk.html");
                mv.addObject("strAction", "listFiProductPointTempsChkJJ");
                mv.addObject("needReload", "1");
                mv.addObject("status", "1");
                mv.addObject("search", "查询");
                return mv;
            }
            
            saveMessage(request, LocaleUtil.getLocalText("fiProductPointTemp.verified"));
            
            ModelAndView mv = new ModelAndView("redirect:fiProductPointTempsChk.html");
            mv.addObject("strAction", "listFiProductPointTempsChkJJ");
            mv.addObject("needReload", "1");
            mv.addObject("status", "1");
            mv.addObject("search", "查询");
            return mv;
        }
        
        CommonRecord crm = RequestUtil.toCommonRecord(request);
        if (StringUtils.isEmpty(request.getParameter("companyCode")))
        {
            crm.addField("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());
        }
        
        Pager pager = new Pager("fiProductPointTempListTable", request, 1000);
        
        List fiProductPointTemps = null;
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
                    if (RequestUtil.saveOperationSession(request, "listFiProductPointTempsChkJJ", 3l) != 0)
                    {
                        return new ModelAndView("redirect:fiProductPointTempsChk.html?strAction=listFiProductPointTempsChkJJ");
                    }
                }
                else if ("M".equals(loginSysUser.getUserType()))
                {
                    if (RequestUtil.saveOperationSession(request, "listFiProductPointTempsChkJJ", 3l) != 0)
                    {
                        return new ModelAndView("redirect:fiProductPointTempsChk.html?strAction=listFiProductPointTempsChkJJ");
                    }
                }
                /** add by hdg 2016-12-22 */
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
        		 /** add by hdg 2016-12-22 */
                fiProductPointTemps = this.fiProductPointTempManager.getFiProductPointTempsByCrm(crm, pager);
                Map incExpMap = this.fiProductPointTempManager.getIncExpStatMap(crm);
                request.setAttribute("incExpMap", incExpMap);
            }
            
        }
        // 根据数据重新设置分页数据
        request.setAttribute("fiProductPointTempListTable_totalRows", pager.getTotalObjects());
        ModelAndView mv =
            new ModelAndView("fi/fiProductPointTempChkList", Constants.FIPRODUCTPOINTTEMP_LIST,
                fiProductPointTemps);
        mv.addObject("strAction", "listFiProductPointTempsChkJJ");
        return mv;
    }
}