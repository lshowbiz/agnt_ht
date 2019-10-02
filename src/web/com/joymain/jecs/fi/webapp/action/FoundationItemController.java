package com.joymain.jecs.fi.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.FoundationItem;
import com.joymain.jecs.fi.service.FoundationItemManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class FoundationItemController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FoundationItemController.class);
    private FoundationItemManager foundationItemManager = null;

    public void setFoundationItemManager(FoundationItemManager foundationItemManager) {
        this.foundationItemManager = foundationItemManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        FoundationItem foundationItem = new FoundationItem();
        // populate object with request parameters
        BeanUtils.populate(foundationItem, request.getParameterMap());

	//List foundationItems = foundationItemManager.getFoundationItems(foundationItem);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        
        /*****/
        
        String strAction = request.getParameter("strAction");
        String type = request.getParameter("type");
        if(StringUtils.isNotEmpty(type)){
        	
        	crm.setValue("type", type);
        }
        
		String url = "";
		if("selectFoundationItemList".equals(strAction)){ 
			url = "fi/foundationItemList";
		}else if("selectFoundationItemList2".equals(strAction)){
			
			url = "fi/foundationItemList2";
			crm.setValue("STATUS", "0");
		} 
		
		Pager pager = new Pager("foundationItemListTable",request, 20);
        List foundationItems = foundationItemManager.getFoundationItemsByCrm(crm,pager);
        request.setAttribute("foundationItemListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView(url, Constants.FOUNDATIONITEM_LIST, foundationItems);
    }
}
