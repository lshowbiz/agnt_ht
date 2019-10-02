package com.joymain.jecs.fi.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.FiBillAccountRelation;
import com.joymain.jecs.fi.service.FiBillAccountRelationManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class FiBillAccountRelationController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiBillAccountRelationController.class);
    private FiBillAccountRelationManager fiBillAccountRelationManager = null;

    public void setFiBillAccountRelationManager(FiBillAccountRelationManager fiBillAccountRelationManager) {
        this.fiBillAccountRelationManager = fiBillAccountRelationManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        String strAction = request.getParameter("strAction");
        //删除所选择的区域
		if ("deleteFiBillAccountRelation".equals(strAction)) {
			
			String relationId = request.getParameter("relationId");
			String billAccountCode = request.getParameter("billAccountCode");
			
			fiBillAccountRelationManager.removeFiBillAccountRelation(relationId);
			
			return new ModelAndView("redirect:editFiBillAccount.html?strAction=editFiBillAccount&billAccountCode="+billAccountCode);
		}
		
        FiBillAccountRelation fiBillAccountRelation = new FiBillAccountRelation();
        // populate object with request parameters
        BeanUtils.populate(fiBillAccountRelation, request.getParameterMap());

        //List fiBillAccountRelations = fiBillAccountRelationManager.getFiBillAccountRelations(fiBillAccountRelation);
        /**** auto pagination ***/
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("fiBillAccountRelationListTable",request, 20);
        List fiBillAccountRelations = fiBillAccountRelationManager.getFiBillAccountRelationsByCrm(crm,pager);
        request.setAttribute("fiBillAccountRelationListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/fiBillAccountRelationList", "fiBillAccountRelationList", fiBillAccountRelations);
    }
}
