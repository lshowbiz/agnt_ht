package com.joymain.jecs.am.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.am.model.AmRegularMsg;
import com.joymain.jecs.am.service.AmRegularMsgManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;


public class AmRegularMsgController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(AmRegularMsgController.class);
    private AmRegularMsgManager amRegularMsgManager = null;

    public void setAmRegularMsgManager(AmRegularMsgManager amRegularMsgManager) {
        this.amRegularMsgManager = amRegularMsgManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        //SysUser sessionLogin = SessionLogin.getLoginUser(request);
        SysUser login = SessionLogin.getLoginUser(request);
        
        AmRegularMsg amRegularMsg = new AmRegularMsg();
        // populate object with request parameters
        BeanUtils.populate(amRegularMsg, request.getParameterMap());

		//List amRegularMsgs = amRegularMsgManager.getAmRegularMsgs(amRegularMsg);
		/**** auto pagination ***/
		CommonRecord crm=RequestUtil.toCommonRecord(request);
		
		Map map = RequestUtil.populateMap(request);
		
		if(! login.getIsManager()){
        	crm.setValue("companyCode", login.getCompanyCode());
        }
		
        Pager pager = new Pager("amRegularMsgListTable",request, 20);
        
        List amRegularMsgs = amRegularMsgManager.getAmRegularMsgsByCrm(crm,pager);
        
        
        request.setAttribute("amRegularMsgListTable_totalRows", pager.getTotalObjects());
        request.setAttribute("amRegularMsgList", amRegularMsgs);
        request.setAttribute("requestMap", map);
        
        /*****/

        return new ModelAndView("am/amRegularMsgList", "amRegularMsgList", amRegularMsgs);
    }
}
