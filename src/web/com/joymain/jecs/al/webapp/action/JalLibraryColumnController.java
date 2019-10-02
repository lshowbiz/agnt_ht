package com.joymain.jecs.al.webapp.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.JalLibraryColumn;
import com.joymain.jecs.al.service.JalLibraryColumnManager;
import com.joymain.jecs.webapp.action.BaseController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JalLibraryColumnController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JalLibraryColumnController.class);
    private JalLibraryColumnManager jalLibraryColumnManager = null;

    public void setJalLibraryColumnManager(JalLibraryColumnManager jalLibraryColumnManager) {
        this.jalLibraryColumnManager = jalLibraryColumnManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JalLibraryColumn jalLibraryColumn = new JalLibraryColumn();
        // populate object with request parameters
        BeanUtils.populate(jalLibraryColumn, request.getParameterMap());

	//List jalLibraryColumns = jalLibraryColumnManager.getJalLibraryColumns(jalLibraryColumn);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jalLibraryColumnListTable",request, 20);
        List jalLibraryColumns = jalLibraryColumnManager.getJalLibraryColumnsByCrm(crm,pager);
        request.setAttribute("jalLibraryColumnListTable_totalRows", pager.getTotalObjects());
        /*****/
        
        

        return new ModelAndView("al/jalLibraryColumnList", Constants.JALLIBRARYCOLUMN_LIST, jalLibraryColumns);
    }
}
