package com.joymain.jecs.pd.webapp.action;

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
import com.joymain.jecs.pd.model.Items;
import com.joymain.jecs.pd.service.ItemsManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ItemsController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(ItemsController.class);
    private ItemsManager itemsManager = null;

    public void setItemsManager(ItemsManager itemsManager) {
        this.itemsManager = itemsManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        Items items = new Items();
        // populate object with request parameters
        BeanUtils.populate(items, request.getParameterMap());

	//List items = itemsManager.getItems(items);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("itemsListTable",request, 20);
        List itemslist = itemsManager.getItemsByCrm(crm,pager);
        request.setAttribute("itemsListTable_totalRows", pager.getTotalObjects());
        /*****/
        
        //Constants.ITEMS_LIST itemsList
        return new ModelAndView("pd/itemsList", "itemsList", itemslist);
    }
}
