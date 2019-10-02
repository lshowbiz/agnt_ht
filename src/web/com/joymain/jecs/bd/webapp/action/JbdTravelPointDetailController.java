package com.joymain.jecs.bd.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.JbdTravelPoint;
import com.joymain.jecs.bd.model.JbdTravelPointDetail;
import com.joymain.jecs.bd.service.JbdTravelPointDetailManager;
import com.joymain.jecs.bd.service.JbdTravelPointManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdTravelPointDetailController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdTravelPointDetailController.class);
    private JbdTravelPointDetailManager jbdTravelPointDetailManager = null;

    public void setJbdTravelPointDetailManager(JbdTravelPointDetailManager jbdTravelPointDetailManager) {
        this.jbdTravelPointDetailManager = jbdTravelPointDetailManager;
    }

    private JbdTravelPointManager jbdTravelPointManager = null;

    public void setJbdTravelPointManager(JbdTravelPointManager jbdTravelPointManager) {
        this.jbdTravelPointManager = jbdTravelPointManager;
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
    		JbdTravelPoint jbdTravelPoint=jbdTravelPointManager.getJbdTravelPoint(defSysUser.getUserCode());
    		List recommendVips=jbdTravelPointManager.getRecommendVip(defSysUser.getUserCode(), "2010-10-02", "2011-9-30");
    		request.setAttribute("recommendVips", recommendVips.size());
    		request.setAttribute("jbdTravelPoint",jbdTravelPoint);
    		crm.addField("userCode", defSysUser.getUserCode());
        }else{
        	
        }

        String strAction=request.getParameter("strAction");

		if("deleteJbdTravelPointDetail".equals(strAction)){
			String id = request.getParameter("id");
			JbdTravelPointDetail jbdTravelPointDetail=jbdTravelPointDetailManager.getJbdTravelPointDetail(id);
			if(jbdTravelPointDetail!=null){
				try {
					jbdTravelPointDetailManager.removeJbdTravelPointRecord(jbdTravelPointDetail.getUserCode(), jbdTravelPointDetail.getTravelType(), defSysUser, id);
	        		MessageUtil.saveMessage(request, "删除成功");
				} catch (Exception e) {
					e.printStackTrace();
	        		MessageUtil.saveMessage(request, "删除失败");
					
				}
			}
		}
        
        
        JbdTravelPointDetail jbdTravelPointDetail = new JbdTravelPointDetail();
        BeanUtils.populate(jbdTravelPointDetail, request.getParameterMap());
        Pager pager = new Pager("jbdTravelPointDetailListTable",request, 20);
        List jbdTravelPointDetails = null;

        String userCode=request.getParameter("userCode");
        String startTime=request.getParameter("startTime");
        String endTime=request.getParameter("endTime");
        
        if("M".equals(defSysUser.getUserType())||!StringUtil.isEmpty(startTime)||!StringUtil.isEmpty(endTime)||!StringUtil.isEmpty(userCode) ){
            jbdTravelPointDetails = jbdTravelPointDetailManager.getJbdTravelPointDetailsByCrm(crm,pager);
        }
        request.setAttribute("jbdTravelPointDetailListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("bd/jbdTravelPointDetailList", Constants.JBDTRAVELPOINTDETAIL_LIST, jbdTravelPointDetails);
    }
    
}
