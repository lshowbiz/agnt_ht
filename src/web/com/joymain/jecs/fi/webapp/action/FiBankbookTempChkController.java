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
import com.joymain.jecs.fi.model.FiBankbookTemp;
import com.joymain.jecs.fi.service.FiBankbookJournalManager;
import com.joymain.jecs.fi.service.FiBankbookTempManager;
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
public class FiBankbookTempChkController extends BaseController implements Controller
{
    private final Log log = LogFactory.getLog(FiBankbookTempChkController.class);
    
    private FiBankbookTempManager fiBankbookTempManager = null;
    
    private FiBankbookJournalManager fiBankbookJournalManager = null;
    
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
    
    public void setFiBankbookJournalManager(FiBankbookJournalManager fiBankbookJournalManager)
    {
        this.fiBankbookJournalManager = fiBankbookJournalManager;
    }
    
    public void setFiBankbookTempManager(FiBankbookTempManager fiBankbookTempManager)
    {
        this.fiBankbookTempManager = fiBankbookTempManager;
    }
    
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        if (log.isDebugEnabled())
        {
            log.debug("entering 'handleRequest' method...");
        }
        RequestUtil.freshSession(request, "listFiBankbookTempsChkJJ", 3l);
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        
        if ("doNotDealFiBankbookTempJJ".equals(request.getParameter("strAction")))
        {
            if ("C".equals(loginSysUser.getUserType()))
            {
                if (RequestUtil.saveOperationSession(request, "doNotDealFiBankbookTempJJ", 10l) != 0)
                {
                    return new ModelAndView("redirect:fiBankbookTempsChk.html?strAction=listFiBankbookTempsChkJJ");
                }
            }
            else if ("M".equals(loginSysUser.getUserType()))
            {
                if (RequestUtil.saveOperationSession(request, "doNotDealFiBankbookTempJJ", 10l) != 0)
                {
                    return new ModelAndView("redirect:fiBankbookTempsChk.html?strAction=listFiBankbookTempsChkJJ");
                }
            }
            // 转为不处理
            String[] tempIds = request.getParameter("strTempIds").split(",");
            List<FiBankbookTemp> fiBankbookTemps = new ArrayList<FiBankbookTemp>();
            for (int i = 0; i < tempIds.length; i++)
            {
                if (!StringUtils.isEmpty(tempIds[i]))
                {
                    FiBankbookTemp fiBankbookTemp =
                        this.fiBankbookTempManager.getFiBankbookTemp(tempIds[i]);
                    if (fiBankbookTemp.getStatus() == 1)
                    {
                        fiBankbookTemp.setStatus(3);
                        fiBankbookTemps.add(fiBankbookTemp);
                    }
                }
            }
            this.fiBankbookTempManager.saveFiBankbookTemps(fiBankbookTemps);
            saveMessage(request, LocaleUtil.getLocalText("fiBankbookTemp.doNotDealed"));
            
            ModelAndView mv = new ModelAndView("redirect:fiBankbookTempsChk.html");
            mv.addObject("strAction", "listFiBankbookTempsChkJJ");
            mv.addObject("needReload", "1");
            return mv;
        }
        else if ("notVerifiedFiBankbookTempJJ".equals(request.getParameter("strAction")))
        {
            if ("C".equals(loginSysUser.getUserType()))
            {
                if (RequestUtil.saveOperationSession(request, "notVerifiedFiBankbookTempJJ", 10l) != 0)
                {
                    return new ModelAndView("redirect:fiBankbookTempsChk.html?strAction=listFiBankbookTempsChkJJ");
                }
            }
            else if ("M".equals(loginSysUser.getUserType()))
            {
                if (RequestUtil.saveOperationSession(request, "notVerifiedFiBankbookTempJJ", 10l) != 0)
                {
                    return new ModelAndView("redirect:fiBankbookTempsChk.html?strAction=listFiBankbookTempsChkJJ");
                }
            }
            // 转为未核实
            String[] tempIds = request.getParameter("strTempIds").split(",");
            List<FiBankbookTemp> fiBankbookTemps = new ArrayList<FiBankbookTemp>();
            for (int i = 0; i < tempIds.length; i++)
            {
                if (!StringUtils.isEmpty(tempIds[i]))
                {
                    FiBankbookTemp fiBankbookTemp =
                        this.fiBankbookTempManager.getFiBankbookTemp(tempIds[i]);
                    if (fiBankbookTemp.getStatus() == 3)
                    {
                        fiBankbookTemp.setStatus(1);
                        fiBankbookTemps.add(fiBankbookTemp);
                    }
                }
            }
            this.fiBankbookTempManager.saveFiBankbookTemps(fiBankbookTemps);
            saveMessage(request, LocaleUtil.getLocalText("fiBankbookTemp.notVerified"));
            
            ModelAndView mv = new ModelAndView("redirect:fiBankbookTempsChk.html");
            mv.addObject("strAction", "listFiBankbookTempsChkJJ");
            mv.addObject("needReload", "1");
            return mv;
        }
        else if ("verifyFiBankbookTempJJ".equals(request.getParameter("strAction")))
        {
            if ("C".equals(loginSysUser.getUserType()))
            {
                if (RequestUtil.saveOperationSession(request, "verifyFiBankbookTempJJ", 10l) != 0)
                {
                    ModelAndView mv = new ModelAndView("redirect:fiBankbookTempsChk.html?strAction=listFiBankbookTempsChkJJ");
                    mv.addObject("status", "1");
                    mv.addObject("search", "查询");
                    return mv;
                }
            }
            else if ("M".equals(loginSysUser.getUserType()))
            {
                if (RequestUtil.saveOperationSession(request, "verifyFiBankbookTempJJ", 10l) != 0)
                {
                    ModelAndView mv = new ModelAndView("redirect:fiBankbookTempsChk.html?strAction=listFiBankbookTempsChkJJ");
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
                            this.fiBankbookJournalManager.saveFiBankbookTempCheck(tempIds[i],
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
                    LocaleUtil.getLocalText("error.fiBankbookJournal.balance.not.enough"));
                ModelAndView mv = new ModelAndView("redirect:fiBankbookTempsChk.html");
                mv.addObject("strAction", "listFiBankbookTempsChkJJ");
                mv.addObject("needReload", "1");
                mv.addObject("status", "1");
                mv.addObject("search", "查询");
                return mv;
            }
            
            saveMessage(request, LocaleUtil.getLocalText("fiBankbookTemp.verified"));
            
            ModelAndView mv = new ModelAndView("redirect:fiBankbookTempsChk.html");
            mv.addObject("strAction", "listFiBankbookTempsChkJJ");
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
        
        Pager pager = new Pager("fiBankbookTempListTable", request, 1000);
        
        List fiBankbookTemps = null;
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
                    if (RequestUtil.saveOperationSession(request, "listFiBankbookTempsChkJJ", 3l) != 0)
                    {
                        return new ModelAndView("redirect:fiBankbookTempsChk.html?strAction=listFiBankbookTempsChkJJ");
                    }
                }
                else if ("M".equals(loginSysUser.getUserType()))
                {
                    if (RequestUtil.saveOperationSession(request, "listFiBankbookTempsChkJJ", 3l) != 0)
                    {
                        return new ModelAndView("redirect:fiBankbookTempsChk.html?strAction=listFiBankbookTempsChkJJ");
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
                fiBankbookTemps = this.fiBankbookTempManager.getFiBankbookTempsByCrm(crm, pager);
                Map incExpMap = this.fiBankbookTempManager.getIncExpStatMap(crm);
                request.setAttribute("incExpMap", incExpMap);
            }
            
        }
        // 根据数据重新设置分页数据
        request.setAttribute("fiBankbookTempListTable_totalRows", pager.getTotalObjects());
        ModelAndView mv =
            new ModelAndView("fi/fiBankbookTempChkList", Constants.FIBANKBOOKTEMP_LIST,
                fiBankbookTemps);
        mv.addObject("strAction", "listFiBankbookTempsChkJJ");
        return mv;
    }
}