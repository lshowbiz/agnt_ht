package com.joymain.jecs.po.webapp.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.webapp.util.ConfigUtil;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 更新审核日期對賬統計
 * @author Alvin
 *
 */
public class JpoMemberOrderChangeDateStatisticController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoMemberOrderChangeDateStatisticController.class);
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private BdPeriodManager bdPeriodManager = null;

    public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

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
        RequestUtil.freshSession(request,"jpoMemberOrderChangeDateStatistic",3l);
        CommonRecord crm = RequestUtil.toCommonRecord(request);
    	crm.addField("companyCode", loginSysUser.getCompanyCode());
    	crm.addField("status", "2");
        Pager pager1 = new Pager("jpoMemberOrderListTable1",request, 0);
        List jpoMemberOrders1 = null;
        Pager pager2 = new Pager("jpoMemberOrderListTable2",request, 0);
        List jpoMemberOrders2 = null;
        if(!StringUtils.isEmpty(crm.getString("formatedWeek","")) ||(!StringUtils.isEmpty(request.getParameter("createBTime"))&& !StringUtils.isEmpty(request.getParameter("createETime")))){
        	if("C".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"jpoMemberOrderChangeDateStatistic",3l)!=0){
        			return new ModelAndView("redirect:jpoMemberOrderChangeDateStatistic.html");
        		}
        	}
        	String wweek = WeekFormatUtil.getFormatWeek("f",crm.getString("formatedWeek",""));
        	crm.addField("formatedWeek", wweek);
	        BdPeriod bdPeriod = bdPeriodManager.getBdPeriodByFormatedWeek(crm.getString("formatedWeek",""));
	        //根据时间进行查询
	        String  createBTime = request.getParameter("createBTime");
	        String	createETime = request.getParameter("createETime");
	        if(bdPeriod!=null){
	        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        crm.addField("inPeriodStartTime", dateFormat.format(bdPeriod.getStartTime()));
		        crm.addField("inPeriodEndTime", dateFormat.format(bdPeriod.getEndTime()));
		        crm.addField("inPeriod", "A");
		        jpoMemberOrders1 = jpoMemberOrderManager.getJpoMemberOrdersByCrm(crm,pager1);
		        crm.addField("inPeriod", "D");
		        jpoMemberOrders2 = jpoMemberOrderManager.getJpoMemberOrdersByCrm(crm,pager2);
	        }else 
	        {
	        	   if(!StringUtil.isEmpty(createBTime) &&!StringUtil.isEmpty(createETime))
				    {   
				    	    crm.addField("inPeriodStartTime",createBTime+" 00:00:00");
					        crm.addField("inPeriodEndTime",createETime+" 23:59:59");
					        crm.addField("inPeriod", "A");
					        jpoMemberOrders1 = jpoMemberOrderManager.getJpoMemberOrdersByCrm(crm,pager1);
					        crm.addField("inPeriod", "D");
					        jpoMemberOrders2 = jpoMemberOrderManager.getJpoMemberOrdersByCrm(crm,pager2);
				    }
	        }
        }
        request.setAttribute("jpoMemberOrderListTable1_totalRows", pager1.getTotalObjects());
        request.setAttribute("jpoMemberOrderListTable2_totalRows", pager2.getTotalObjects());
        
        return new ModelAndView("po/jpoMemberOrdersChangeDateStatistic","jpoMemberOrderList1",jpoMemberOrders1).addObject("jpoMemberOrderList2",jpoMemberOrders2);
    }
}
