package com.joymain.jecs.bd.webapp.action;

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
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.JbdTaiwanTravelPoint;
import com.joymain.jecs.bd.service.JbdTaiwanTravelPointManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdTaiwanTravelPointController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdTaiwanTravelPointController.class);
    private JbdTaiwanTravelPointManager jbdTaiwanTravelPointManager = null;

    public void setJbdTaiwanTravelPointManager(JbdTaiwanTravelPointManager jbdTaiwanTravelPointManager) {
        this.jbdTaiwanTravelPointManager = jbdTaiwanTravelPointManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

    	SysUser defSysUser=SessionLogin.getLoginUser(request);

        CommonRecord crm=RequestUtil.toCommonRecord(request);
    	
	   if("M".equals(defSysUser.getUserType())){
           crm.addField("userCode", defSysUser.getUserCode());
       }else{
//	       	if(StringUtil.isEmpty(request.getParameter("userCode"))){
//	
//	               
//	       	}
	       	crm.addField("companyCode", defSysUser.getCompanyCode());
       }

        Pager pager = new Pager("jbdTaiwanTravelPointListTable",request, 20);
        List jbdTaiwanTravelPoints=null;
        
        if(!StringUtil.isEmpty(crm.getString("userCode", ""))){

            jbdTaiwanTravelPoints = jbdTaiwanTravelPointManager.getJbdTaiwanTravelPointsByCrm(crm,pager);
        }

        request.setAttribute("jbdTaiwanTravelPointListTable_totalRows", pager.getTotalObjects());


        return new ModelAndView("bd/jbdTaiwanTravelPointList", Constants.JBDTAIWANTRAVELPOINT_LIST, jbdTaiwanTravelPoints);
    }
}
