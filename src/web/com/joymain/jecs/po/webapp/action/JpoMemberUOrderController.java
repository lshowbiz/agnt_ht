package com.joymain.jecs.po.webapp.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysListValue;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysListValueManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.date.DateUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 会员升级列表
 * @author Alvin
 *
 */
public class JpoMemberUOrderController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoMemberUOrderController.class);
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
            RequestUtil.freshSession(request,"poMemberUOrders",3l);
    	}else if("M".equals(loginSysUser.getUserType())){
            RequestUtil.freshSession(request,"poMemberUOrders",3l);
    	}
        
        //判断是否能升级
        if("M".equals(loginSysUser.getUserType())){
        	if("6".equals(loginSysUser.getJmiMember().getCardType())){//vip
        		loginSysUser.setUpGrade("0");//1可以下升级单，0表示不能下升级单
        	}else{
                if(StringUtils.isEmpty(loginSysUser.getUpGrade())){
                	loginSysUser.setUpGrade(this.setUpGrade(loginSysUser));
                }
        	}
        }else{
        	loginSysUser.setUpGrade("1");//可以下升级单公司后台
        }

        CommonRecord crm = RequestUtil.toCommonRecord(request);
    	crm.addField("companyCode", loginSysUser.getCompanyCode());
    	crm.addField("orderType", "2");
    	crm.addField("isSpecial", "0");//是否为特殊订单0表示否，1表示是
        
        if(loginSysUser.getIsMember()){
        	crm.addField("sysUser.userCode", loginSysUser.getUserCode());
        }
        
        Pager pager = new Pager("jpoMemberOrderListTable",request, 20);
        List jpoMemberOrders = null;
        if(!StringUtils.isEmpty(crm.getString("search","")) || !StringUtils.isEmpty(crm.getString("sysUser.userCode",""))){
        	if("C".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"poMemberUOrders",3l)!=0){
        			return new ModelAndView("redirect:jpoMemberUOrders.html");
        		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"poMemberUOrders",3l)!=0){
        			return new ModelAndView("redirect:jpoMemberUOrders.html");
        		}
        	}
            jpoMemberOrders = jpoMemberOrderManager.getJpoMemberOrdersByCrm(crm,pager);
        }
        request.setAttribute("jpoMemberOrderListTable_totalRows", pager.getTotalObjects());

        if(RequestUtil.isMobileRequest(request)){
        	return new ModelAndView("/mobile/po/jpoMemberUOrderList", Constants.JPOMEMBERORDER_LIST, jpoMemberOrders);
        }
        return new ModelAndView("po/jpoMemberUOrderList", Constants.JPOMEMBERORDER_LIST, jpoMemberOrders);
    }
    
    /**
     * 判断是否满足升级条件
     * @param sysUser
     * @return
     */
    private String setUpGrade(SysUser sysUser){
    	
    	int member_upgrade_time = Integer.parseInt(ConfigUtil.getConfigValue(sysUser.getCompanyCode().toUpperCase(),"member_upgrade_time"));//会员在规定时间里只能升几次
        boolean isUpGrade1 = checkMiMemberIsUpGraded(sysUser,member_upgrade_time);
        if(!isUpGrade1){
    		return "0";
        }
        
        int days = Integer.parseInt(ConfigUtil.getConfigValue(sysUser.getCompanyCode().toUpperCase(),"member_upgrade_day"));//会员的是否满级和是否超过60天
    	boolean isUpGrade2 = checkMiMemberIsGrade(sysUser,days);
    	if(!isUpGrade2){
    		return "0";//不允许下升级单
    	}
    	return "1";//允许下升级单
    }
    
    /**
     * 会员在MEMBER_UPGRADE_DAY时间内可几次
     * @param sysUser
     * @param member_upgrade_time
     * @return
     */
    private boolean checkMiMemberIsUpGraded(SysUser sysUser, int member_upgrade_time){
        JpoMemberOrder jpoMemberOrder = new JpoMemberOrder();
        jpoMemberOrder.setSysUser(sysUser);
        jpoMemberOrder.setOrderType("2");//升级单
        jpoMemberOrder.setStatus("1");//待审单
        List poMemberOrderList1 = jpoMemberOrderManager.getJpoMemberOrdersByMiMember(jpoMemberOrder);
        if(poMemberOrderList1.size() > 0){//不允许在下升级单
        	return false;
        }
        jpoMemberOrder.setSysUser(sysUser);
        jpoMemberOrder.setOrderType("2");
        jpoMemberOrder.setStatus("2");
        List poMemberOrderList2 = jpoMemberOrderManager.getJpoMemberOrdersByMiMember(jpoMemberOrder);
    	if(poMemberOrderList2.size() < member_upgrade_time){//审核过的订单总数
    		return true;
    	}
    	return false;
    }
    
    /**
     * 检查会员的是否满级和是否超过28天
     * @param sysUser
     * @param days
     * @return
     */
    private boolean checkMiMemberIsGrade(SysUser sysUser,int days){
    	String checkTime = jpoMemberOrderManager.getMemberFirstOrderStatusTime(sysUser.getUserCode());
    	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = null;
    	try {
			date = format1.parse(checkTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	if(DateUtil.daysBetweenDates(new Date(),date)<days){
    		return true;
    	}
    	return false;
    }
}
