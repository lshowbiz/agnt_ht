package com.joymain.jecs.bd.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.JbdTravelPoint2014;
import com.joymain.jecs.bd.model.JbdTravelPointDetail2014;
import com.joymain.jecs.bd.service.JbdTravelPoint2014Manager;
import com.joymain.jecs.bd.service.JbdTravelPointDetail2014Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdTravelPointDetail2014Controller extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdTravelPointDetail2014Controller.class);
    private JbdTravelPointDetail2014Manager jbdTravelPointDetail2014Manager = null;
    private JbdTravelPoint2014Manager jbdTravelPoint2014Manager;
    public void setJbdTravelPoint2014Manager(
			JbdTravelPoint2014Manager jbdTravelPoint2014Manager) {
		this.jbdTravelPoint2014Manager = jbdTravelPoint2014Manager;
	}

	public void setJbdTravelPointDetail2014Manager(JbdTravelPointDetail2014Manager jbdTravelPointDetail2014Manager) {
        this.jbdTravelPointDetail2014Manager = jbdTravelPointDetail2014Manager;
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
    		JbdTravelPoint2014 jbdTravelPoint2014=jbdTravelPoint2014Manager.getJbdTravelPoint2014(defSysUser.getUserCode());
    		request.setAttribute("jbdTravelPoint2014",jbdTravelPoint2014);
    		crm.addField("userCode", defSysUser.getUserCode());
        }else{
        	
        }

        String strAction=request.getParameter("strAction");

		if("deleteJbdTravelPointDetail2014".equals(strAction)){
			String id = request.getParameter("id");
			JbdTravelPointDetail2014 jbdTravelPointDetail2014=jbdTravelPointDetail2014Manager.getJbdTravelPointDetail2014(id);
			if(jbdTravelPointDetail2014!=null){
				try {
					jbdTravelPointDetail2014Manager.removeJbdTravelPointRecord2014(jbdTravelPointDetail2014.getUserCode(), jbdTravelPointDetail2014.getTravelType(), defSysUser, id);
	        		MessageUtil.saveMessage(request, "删除成功");
				} catch (Exception e) {
					e.printStackTrace();
	        		MessageUtil.saveMessage(request, "删除失败");
					
				}
			}
		}
        
        
        JbdTravelPointDetail2014 jbdTravelPointDetail2014 = new JbdTravelPointDetail2014();
        BeanUtils.populate(jbdTravelPointDetail2014, request.getParameterMap());
        
        
        
        List jbdTravelPointDetail2014s = null;
        List jbdMemberLinkCalcHist2014s = null;

        String userCode=request.getParameter("userCode");
        String startTime=request.getParameter("startTime");
        String endTime=request.getParameter("endTime");
        Pager pager = new Pager("jbdTravelPointDetail2014ListTable",request, 20);
        
        if("M".equals(defSysUser.getUserType())||!StringUtil.isEmpty(startTime)||!StringUtil.isEmpty(endTime)||!StringUtil.isEmpty(userCode) ){
            jbdTravelPointDetail2014s = jbdTravelPointDetail2014Manager.getJbdTravelPointDetail2014sByCrm(crm,pager);
            jbdMemberLinkCalcHist2014s = jbdTravelPoint2014Manager.getJbdTravelPoint2014sByUserCode(defSysUser.getUserCode());
            request.setAttribute("jbdMemberLinkCalcHist2014s", jbdMemberLinkCalcHist2014s);
        }
        
        
        request.setAttribute("jbdTravelPointDetail2014ListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdTravelPointDetail2014List", Constants.JBDTRAVELPOINTDETAIL2014_LIST, jbdTravelPointDetail2014s);
    }
}
