package com.joymain.jecs.fi.webapp.action;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.FiFundbookJournal;
import com.joymain.jecs.fi.service.FiFundbookJournalManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class FiFundbookJournalController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiFundbookJournalController.class);
    private FiFundbookJournalManager fiFundbookJournalManager = null;

    public void setFiFundbookJournalManager(FiFundbookJournalManager fiFundbookJournalManager) {
        this.fiFundbookJournalManager = fiFundbookJournalManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

       
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        if("C".equals(loginSysUser.getUserType())){
            RequestUtil.freshSession(request,"listFiFundbookJournalsJJ",10l);
    	}else if("M".equals(loginSysUser.getUserType())){
            RequestUtil.freshSession(request,"listFiFundbookJournalsJJ",1l);
    	}
        
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		/** add by hdg 2016-12-23 */
        CustomField[] fields = crm.getFields();
		w:for(CustomField field : fields) {
			String name=field.getName();
			if(!StringUtils.isEmpty(name)) {
				if("enddealdate".equals(name)) {
					String value = (String)field.getValue();
					if(!StringUtils.isEmpty(value)) {
						field.setValue(value+" 23:59:59");
					}
					break w;
				}
			}
		}
		 /** add by hdg 2016-12-23 */
		crm.addField("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());
		if (!SessionLogin.getLoginUser(request).getIsManager() && !SessionLogin.getLoginUser(request).getIsCompany()) {
			crm.addField("sysUser.userCode", SessionLogin.getLoginUser(request).getUserCode());
		}
		String startDealDate = crm.getString("startDealDate", "");
		if (!StringUtils.isEmpty(startDealDate)) {
			crm.addField("startDealDate", startDealDate);
		}

		String endDealDate = crm.getString("endDealDate", "");
		if (!StringUtils.isEmpty(endDealDate)) {
			crm.addField("endDealDate", endDealDate);
		}

		Pager pager = new Pager("fiFundbookJournalListTable", request, 20);
		
		List fiFundbookJournals = null;
		if (request.getParameter("search") != null || SessionLogin.getLoginUser(request).getIsMember() || SessionLogin.getLoginUser(request).getIsAgent()){
			if("C".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"listFiFundbookJournalsJJ",10l)!=0){
        			return new ModelAndView("redirect:fiFundbookJournals.html");
        		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"listFiFundbookJournalsJJ",10l)!=0){
        			return new ModelAndView("redirect:fiFundbookJournals.html");
        		}
        	}
			fiFundbookJournals = this.fiFundbookJournalManager.getFiFundbookJournalsByCrm(crm, pager);
			
			//Modify By WuCF 20140320 查询结果的总计 
			Map sumMap = this.fiFundbookJournalManager.getSumAmountByCrm(crm);
			request.setAttribute("sumMap", sumMap);
		}

        request.setAttribute("fiFundbookJournalListTable_totalRows", pager.getTotalObjects());
        return new ModelAndView("fi/fiFundbookJournalList", "fiFundbookJournalList", fiFundbookJournals);
    }
}
