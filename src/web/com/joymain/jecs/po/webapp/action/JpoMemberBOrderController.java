package com.joymain.jecs.po.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 奖衔补单
 * @author xiaoman001
 *
 */
public class JpoMemberBOrderController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(JpoMemberBOrderController.class);
    private JpoMemberOrderManager jpoMemberOrderManager = null;

    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
        this.jpoMemberOrderManager = jpoMemberOrderManager;
    }

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
        if("C".equals(loginSysUser.getUserType())){
            RequestUtil.freshSession(request,"poMemberFOrders",3l);
    	}else if("M".equals(loginSysUser.getUserType())){
            RequestUtil.freshSession(request,"poMemberFOrders",3l);
    	}

        CommonRecord crm = RequestUtil.toCommonRecord(request);
    	crm.addField("companyCode", loginSysUser.getCompanyCode());
    	crm.addField("orderType", "32");
    	crm.addField("isSpecial", "0");
        
        if(loginSysUser.getIsMember()){
        	crm.addField("sysUser.userCode", loginSysUser.getUserCode());
        }
        
        Pager pager = new Pager("jpoMemberOrderListTable",request, 20);
        List jpoMemberOrders = null;
        if(!StringUtils.isEmpty(crm.getString("search","")) || !StringUtils.isEmpty(crm.getString("sysUser.userCode",""))){
        	if("C".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"poMemberBOrders",3l)!=0){
        			return new ModelAndView("redirect:jpoMemberBOrders.html");
        		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"poMemberBOrders",3l)!=0){
        			return new ModelAndView("redirect:jpoMemberBOrders.html");
        		}
        	}
            jpoMemberOrders = jpoMemberOrderManager.getJpoMemberOrdersByCrm(crm,pager);
        }
        request.setAttribute("jpoMemberOrderListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("po/jpoMemberBOrderList", Constants.JPOMEMBERORDER_LIST, jpoMemberOrders);
    }

}
