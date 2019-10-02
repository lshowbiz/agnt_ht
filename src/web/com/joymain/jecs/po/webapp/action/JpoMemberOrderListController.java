package com.joymain.jecs.po.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.service.JpoMemberOrderListManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpoMemberOrderListController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoMemberOrderListController.class);
    private JpoMemberOrderListManager jpoMemberOrderListManager = null;

    public void setJpoMemberOrderListManager(JpoMemberOrderListManager jpoMemberOrderListManager) {
        this.jpoMemberOrderListManager = jpoMemberOrderListManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        SysUser loginSysUser = SessionLogin.getLoginUser(request);

        CommonRecord crm = RequestUtil.toCommonRecord(request);
        
        if(loginSysUser.getIsCompany()){
        	crm.addField("companyCode", loginSysUser.getCompanyCode());
        }else if(loginSysUser.getIsMember()){
        	crm.addField("companyCode", loginSysUser.getCompanyCode());
        	crm.addField("sysUser.userCode", loginSysUser.getUserCode());
        }
        
        Pager pager = new Pager("jpoMemberOrderListListTable",request, 20);
        List jpoMemberOrderLists = jpoMemberOrderListManager.getJpoMemberOrderListsByCrm(crm,pager);
        request.setAttribute("jpoMemberOrderListListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("po/jpoMemberOrderListList", Constants.JPOMEMBERORDERLIST_LIST, jpoMemberOrderLists);
    }
}
