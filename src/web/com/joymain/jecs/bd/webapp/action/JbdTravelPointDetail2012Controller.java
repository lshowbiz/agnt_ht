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
import com.joymain.jecs.bd.model.JbdTravelPoint;
import com.joymain.jecs.bd.model.JbdTravelPoint2012;
import com.joymain.jecs.bd.model.JbdTravelPointDetail;
import com.joymain.jecs.bd.model.JbdTravelPointDetail2012;
import com.joymain.jecs.bd.service.JbdTravelPoint2012Manager;
import com.joymain.jecs.bd.service.JbdTravelPointDetail2012Manager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdTravelPointDetail2012Controller extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdTravelPointDetail2012Controller.class);
    private JbdTravelPointDetail2012Manager jbdTravelPointDetail2012Manager = null;
    private JbdTravelPoint2012Manager jbdTravelPoint2012Manager;
    public void setJbdTravelPoint2012Manager(
			JbdTravelPoint2012Manager jbdTravelPoint2012Manager) {
		this.jbdTravelPoint2012Manager = jbdTravelPoint2012Manager;
	}

	public void setJbdTravelPointDetail2012Manager(JbdTravelPointDetail2012Manager jbdTravelPointDetail2012Manager) {
        this.jbdTravelPointDetail2012Manager = jbdTravelPointDetail2012Manager;
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
    		JbdTravelPoint2012 jbdTravelPoint2012=jbdTravelPoint2012Manager.getJbdTravelPoint2012(defSysUser.getUserCode());
    		request.setAttribute("jbdTravelPoint2012",jbdTravelPoint2012);
    		crm.addField("userCode", defSysUser.getUserCode());
        }else{
        	
        }

        String strAction=request.getParameter("strAction");

		if("deleteJbdTravelPointDetail2012".equals(strAction)){
			String id = request.getParameter("id");
			JbdTravelPointDetail2012 jbdTravelPointDetail2012=jbdTravelPointDetail2012Manager.getJbdTravelPointDetail2012(id);
			if(jbdTravelPointDetail2012!=null){
				try {
					jbdTravelPointDetail2012Manager.removeJbdTravelPointRecord2012(jbdTravelPointDetail2012.getUserCode(), jbdTravelPointDetail2012.getTravelType(), defSysUser, id);
	        		MessageUtil.saveMessage(request, "删除成功");
				} catch (Exception e) {
					e.printStackTrace();
	        		MessageUtil.saveMessage(request, "删除失败");
					
				}
			}
		}
        
        
        JbdTravelPointDetail2012 jbdTravelPointDetail2012 = new JbdTravelPointDetail2012();
        BeanUtils.populate(jbdTravelPointDetail2012, request.getParameterMap());
        
        
        
        List jbdTravelPointDetail2012s = null;

        String userCode=request.getParameter("userCode");
        String startTime=request.getParameter("startTime");
        String endTime=request.getParameter("endTime");
        Pager pager = new Pager("jbdTravelPointDetail2012ListTable",request, 20);
        
        if("M".equals(defSysUser.getUserType())||!StringUtil.isEmpty(startTime)||!StringUtil.isEmpty(endTime)||!StringUtil.isEmpty(userCode) ){
            jbdTravelPointDetail2012s = jbdTravelPointDetail2012Manager.getJbdTravelPointDetail2012sByCrm(crm,pager);
        }
        
        
        request.setAttribute("jbdTravelPointDetail2012ListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdTravelPointDetail2012List", Constants.JBDTRAVELPOINTDETAIL2012_LIST, jbdTravelPointDetail2012s);
    }
}
