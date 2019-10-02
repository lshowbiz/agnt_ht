package com.joymain.jecs.fi.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.JfiBankbookTemp;
import com.joymain.jecs.fi.model.JfiPosImport;
import com.joymain.jecs.fi.service.JfiPosImportManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiPosImportController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiPosImportController.class);
    private JfiPosImportManager jfiPosImportManager = null;

    public void setJfiPosImportManager(JfiPosImportManager jfiPosImportManager) {
        this.jfiPosImportManager = jfiPosImportManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        SysUser loginSysUser = SessionLogin.getLoginUser(request);

		if ("checkPosImport".equals(request.getParameter("strAction"))) {
			//转为待审
			Date date = new Date();
			String[] tempIds = request.getParameter("strTempIds").split(",");
			for (int i = 0; i < tempIds.length; i++) {
				if (!StringUtils.isEmpty(tempIds[i])) {
					JfiPosImport jfiPosImport = this.jfiPosImportManager.getJfiPosImport(tempIds[i]);
					if(!"1".equals(jfiPosImport.getInc())){
						jfiPosImport.setStatus("1");
						jfiPosImport.setCheckTime(date);
						jfiPosImport.setCheckUser(loginSysUser.getUserCode());
						this.jfiPosImportManager.saveJfiPosImport(jfiPosImport);
					}
				}
			}
			saveMessage(request, LocaleUtil.getLocalText("jfiPosImport.checkPosImport"));
			ModelAndView mv=new ModelAndView("redirect:jfiPosImports.html");
			mv.addObject("strAction", "jfiPosImports");
			mv.addObject("needReload", "1");
			return mv;
		}else if ("doCheckPosImport".equals(request.getParameter("strAction"))) {
			//转为已审
			Date date = new Date();
			String[] tempIds = request.getParameter("strTempIds").split(",");
			for (int i = 0; i < tempIds.length; i++) {
				if (!StringUtils.isEmpty(tempIds[i])) {
					JfiPosImport jfiPosImport = this.jfiPosImportManager.getJfiPosImport(tempIds[i]);
					if(!"1".equals(jfiPosImport.getInc())){
						jfiPosImport.setStatus("2");
						jfiPosImport.setCheckTime(date);
						jfiPosImport.setCheckUser(loginSysUser.getUserCode());
						this.jfiPosImportManager.saveJfiPosImport(jfiPosImport);
					}
				}
			}
			saveMessage(request, LocaleUtil.getLocalText("jfiPosImport.doCheckPosImport"));
			ModelAndView mv=new ModelAndView("redirect:jfiPosImports.html");
			mv.addObject("strAction", "jfiPosImports");
			mv.addObject("needReload", "1");
			return mv;
		}if ("doNotCheckPosImport".equals(request.getParameter("strAction"))) {
			//转为取消
			Date date = new Date();
			String[] tempIds = request.getParameter("strTempIds").split(",");
			for (int i = 0; i < tempIds.length; i++) {
				if (!StringUtils.isEmpty(tempIds[i])) {
					JfiPosImport jfiPosImport = this.jfiPosImportManager.getJfiPosImport(tempIds[i]);
					if(!"1".equals(jfiPosImport.getInc())){
						jfiPosImport.setStatus("3");
						jfiPosImport.setCheckTime(date);
						jfiPosImport.setCheckUser(loginSysUser.getUserCode());
						this.jfiPosImportManager.saveJfiPosImport(jfiPosImport);
					}
				}
			}
			saveMessage(request, LocaleUtil.getLocalText("jfiPosImport.doCheckPosImport"));
			ModelAndView mv=new ModelAndView("redirect:jfiPosImports.html");
			mv.addObject("strAction", "jfiPosImports");
			mv.addObject("needReload", "1");
			return mv;
		}if ("deletePosImport".equals(request.getParameter("strAction"))) {
			//删除
			String[] tempIds = request.getParameter("strTempIds").split(",");
			for (int i = 0; i < tempIds.length; i++) {
				if (!StringUtils.isEmpty(tempIds[i])) {
					JfiPosImport jfiPosImport = this.jfiPosImportManager.getJfiPosImport(tempIds[i]);
					if(jfiPosImport.getStatus().equals("1") && !"1".equals(jfiPosImport.getInc())){
						this.jfiPosImportManager.removeJfiPosImport(tempIds[i]);
					}
				}
			}
			saveMessage(request, LocaleUtil.getLocalText("bdOutWardBank.deleteSuccess"));
			ModelAndView mv=new ModelAndView("redirect:jfiPosImports.html");
			mv.addObject("strAction", "jfiPosImports");
			mv.addObject("needReload", "1");
			return mv;
		}
        
        List jfiPosImports = null;
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jfiPosImportListTable",request, 20);
		if(request.getParameter("search")!=null){
	        jfiPosImports = jfiPosImportManager.getJfiPosImportsByCrm(crm,pager);
	        String sumAmount = jfiPosImportManager.getSumJfiPosImportsByCrm(crm);
	        request.setAttribute("sumAmount", sumAmount);
		}
        request.setAttribute("jfiPosImportListTable_totalRows", pager.getTotalObjects());
        return new ModelAndView("fi/jfiPosImportList", Constants.JFIPOSIMPORT_LIST, jfiPosImports);
    }
}
