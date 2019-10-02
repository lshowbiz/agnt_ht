package com.joymain.jecs.po.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import com.joymain.jecs.Constants;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 店铺重销列表
 * @author Alvin
 *
 */
public class JpoMemberSROrderController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoMemberSROrderController.class);
    private JpoMemberOrderManager jpoMemberOrderManager = null;

    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
        this.jpoMemberOrderManager = jpoMemberOrderManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        if("C".equals(loginSysUser.getUserType())){
            RequestUtil.freshSession(request,"poMemberSROrders",3l);
    	}else if("M".equals(loginSysUser.getUserType())){
            RequestUtil.freshSession(request,"poMemberSROrders",3l);
    	}

        CommonRecord crm = RequestUtil.toCommonRecord(request);
    	crm.addField("companyCode", loginSysUser.getCompanyCode());
    	crm.addField("orderType", "9");
    	crm.addField("isSpecial", "0");
        
        if(loginSysUser.getIsMember()){
        	crm.addField("sysUser.userCode", loginSysUser.getUserCode());
        }
        
        Pager pager = new Pager("jpoMemberOrderListTable",request, 20);
        List jpoMemberOrders = null;
        if(!StringUtils.isEmpty(crm.getString("search","")) || !StringUtils.isEmpty(crm.getString("sysUser.userCode",""))){
        	if("C".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"poMemberSROrders",3l)!=0){
        			return new ModelAndView("redirect:jpoMemberSROrders.html");
        		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"poMemberSROrders",3l)!=0){
        			return new ModelAndView("redirect:jpoMemberSROrders.html");
        		}
        	}
            jpoMemberOrders = jpoMemberOrderManager.getJpoMemberOrdersByCrm(crm,pager);
        }
        request.setAttribute("jpoMemberOrderListTable_totalRows", pager.getTotalObjects());

		if (RequestUtil.isMobileRequest(request)) {
			return new ModelAndView("mobile/po/jpoMemberSROrderList", Constants.JPOMEMBERORDER_LIST, jpoMemberOrders);
		}
        return new ModelAndView("po/jpoMemberSROrderList", Constants.JPOMEMBERORDER_LIST, jpoMemberOrders);
    }
}
