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
import com.joymain.jecs.util.MeteorUtil;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.util.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 会员重销订购列表
 * @author Alvin
 *
 */
public class JpoMemberROrderController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoMemberROrderController.class);
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
        SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
        if("C".equals(loginSysUser.getUserType())){
            RequestUtil.freshSession(request,"poMemberROrders",3l);
    	}else if("M".equals(loginSysUser.getUserType())){
            RequestUtil.freshSession(request,"poMemberROrders",3l);
    	}
        String orderType = getParValue(request, "orderType");//订单类型
        if(MeteorUtil.isBlank(orderType)){
        	orderType = "4";
        }
        request.setAttribute("orderType", orderType);
        
        CommonRecord crm = RequestUtil.toCommonRecord(request);
    	crm.addField("orderType", orderType);
    	crm.addField("isSpecial", "0");
        
        if(loginSysUser.getIsMember()){
        	crm.addField("sysUser.userCode", loginSysUser.getUserCode());
        }else{
        	crm.addField("companyCode", loginSysUser.getCompanyCode());
        }
        
        Pager pager = new Pager("jpoMemberOrderListTable",request, 20);
        List jpoMemberOrders = null;
        if(!StringUtils.isEmpty(crm.getString("search","")) || !StringUtils.isEmpty(crm.getString("sysUser.userCode",""))){
        	if("C".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"poMemberROrders",3l)!=0){
        			return new ModelAndView("redirect:jpoMemberROrders.html");
        		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"poMemberROrders",3l)!=0){
        			return new ModelAndView("redirect:jpoMemberROrders.html");
        		}
        	}
        	/*
        	 * TODO 折扣促销
        	 */
            jpoMemberOrders = jpoMemberOrderManager.getJpoMemberOrdersByCrm(crm,pager);
        }
        request.setAttribute("jpoMemberOrderListTable_totalRows", pager.getTotalObjects());
        
        if(RequestUtil.isMobileRequest(request)){
        	return new ModelAndView("mobile/po/jpoMemberROrderList", Constants.JPOMEMBERORDER_LIST, jpoMemberOrders);
        }

        return new ModelAndView("po/jpoMemberROrderList", Constants.JPOMEMBERORDER_LIST, jpoMemberOrders);
    }

	    
    /**
	 * @Description 获取请求对象值
	 * @author houxyu
	 * @date 2016-4-10
	 * @param request
	 * @param param
	 * @return
	 */
	private static String getParValue(HttpServletRequest request, String param){
		String s = "";
		
		try {
			s = request.getParameter(param);
			if(MeteorUtil.isBlank(s)){
				s = (String)request.getAttribute(param);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return s;
	}

}
