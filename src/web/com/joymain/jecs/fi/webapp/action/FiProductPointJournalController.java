package com.joymain.jecs.fi.webapp.action;

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
import com.joymain.jecs.fi.service.FiBankbookJournalManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class FiProductPointJournalController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiProductPointJournalController.class);
    private FiBankbookJournalManager fiBankbookJournalManager = null;

    public void setFiBankbookJournalManager(FiBankbookJournalManager fiBankbookJournalManager) {
        this.fiBankbookJournalManager = fiBankbookJournalManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        if("C".equals(loginSysUser.getUserType())){
            RequestUtil.freshSession(request,"listProductPointJournal",10l);
    	}else if("M".equals(loginSysUser.getUserType())){
            RequestUtil.freshSession(request,"listProductPointJournal",1l);
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

		Pager pager = new Pager("fiBankbookJournalListTable", request, 20);
		
		List fiBankbookJournals = null;
		if (request.getParameter("search") != null || SessionLogin.getLoginUser(request).getIsMember() || SessionLogin.getLoginUser(request).getIsAgent()){
			/*if("C".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"listFiBankbookJournalsJJ",10l)!=0){
        			return new ModelAndView("redirect:fiBankbookJournals.html");
        		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"listFiBankbookJournalsJJ",10l)!=0){
        			return new ModelAndView("redirect:fiBankbookJournals.html");
        		}
        	}*/
			fiBankbookJournals = this.fiBankbookJournalManager.getFiProductPointJournalsByCrm(crm, pager);
			
			//Modify By WuCF 20140320 查询结果的总计 
			Map sumMap = this.fiBankbookJournalManager.getSumPPByCrm(crm);
			request.setAttribute("sumMap", sumMap);
		}
				
		

		//根据数据重新设置分页数据
		request.setAttribute("fiBankbookJournalListTable_totalRows", pager.getTotalObjects());
        return new ModelAndView("fi/fiProductPointJournalList", Constants.FIPRODUCTPOINTJOURNAL_LIST, fiBankbookJournals);
    }
}
