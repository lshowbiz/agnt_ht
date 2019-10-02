package com.joymain.jecs.pd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.pd.model.Items;
import com.joymain.jecs.pd.service.ItemsManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class ItemsFormController extends BaseFormController {
    private ItemsManager itemsManager = null;

    public void setitemsManager(ItemsManager itemsManager) {
        this.itemsManager = itemsManager;
    }
    public ItemsFormController() {
        setCommandName("items");
        setCommandClass(Items.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String itemsId = request.getParameter("itemsId");
        Items items = null;

        if (!StringUtils.isEmpty(itemsId)) {
            items = itemsManager.getItems(itemsId);
        } else {
            items = new Items();
        }

        return items;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        Items items = (Items) command;
        boolean isNew = (items.getItemsId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteitems".equals(strAction)  ) {
		itemsManager.removeItems(items.getItemsId());
		key="items.delete";
	}else{
		itemsManager.saveItems(items);
		key="items.update";
	}

        return new ModelAndView(getSuccessView());
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
