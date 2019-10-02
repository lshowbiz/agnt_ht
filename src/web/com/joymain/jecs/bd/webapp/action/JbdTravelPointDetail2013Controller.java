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
import com.joymain.jecs.bd.model.JbdTravelPoint2013;
import com.joymain.jecs.bd.model.JbdTravelPointDetail2013;
import com.joymain.jecs.bd.service.JbdTravelPoint2013Manager;
import com.joymain.jecs.bd.service.JbdTravelPointDetail2013Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdTravelPointDetail2013Controller extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdTravelPointDetail2013Controller.class);
    private JbdTravelPointDetail2013Manager jbdTravelPointDetail2013Manager = null;
    private JbdTravelPoint2013Manager jbdTravelPoint2013Manager;
    public void setJbdTravelPoint2013Manager(
			JbdTravelPoint2013Manager jbdTravelPoint2013Manager) {
		this.jbdTravelPoint2013Manager = jbdTravelPoint2013Manager;
	}

	public void setJbdTravelPointDetail2013Manager(JbdTravelPointDetail2013Manager jbdTravelPointDetail2013Manager) {
        this.jbdTravelPointDetail2013Manager = jbdTravelPointDetail2013Manager;
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
    		JbdTravelPoint2013 jbdTravelPoint2013=jbdTravelPoint2013Manager.getJbdTravelPoint2013(defSysUser.getUserCode());
    		request.setAttribute("jbdTravelPoint2013",jbdTravelPoint2013);
    		crm.addField("userCode", defSysUser.getUserCode());
        }else{
        	
        }

        String strAction=request.getParameter("strAction");

		if("deleteJbdTravelPointDetail2013".equals(strAction)){
			String id = request.getParameter("id");
			JbdTravelPointDetail2013 jbdTravelPointDetail2013=jbdTravelPointDetail2013Manager.getJbdTravelPointDetail2013(id);
			if(jbdTravelPointDetail2013!=null){
				try {
					jbdTravelPointDetail2013Manager.removeJbdTravelPointRecord2013(jbdTravelPointDetail2013.getUserCode(), jbdTravelPointDetail2013.getTravelType(), defSysUser, id);
	        		MessageUtil.saveMessage(request, "删除成功");
				} catch (Exception e) {
					e.printStackTrace();
	        		MessageUtil.saveMessage(request, "删除失败");
					
				}
			}
		}
        
        
        JbdTravelPointDetail2013 jbdTravelPointDetail2013 = new JbdTravelPointDetail2013();
        BeanUtils.populate(jbdTravelPointDetail2013, request.getParameterMap());
        
        
        
        List jbdTravelPointDetail2013s = null;
        List jbdMemberLinkCalcHist2013s = null;

        String userCode=request.getParameter("userCode");
        String startTime=request.getParameter("startTime");
        String endTime=request.getParameter("endTime");
        Pager pager = new Pager("jbdTravelPointDetail2013ListTable",request, 20);
        
        if("M".equals(defSysUser.getUserType())||!StringUtil.isEmpty(startTime)||!StringUtil.isEmpty(endTime)||!StringUtil.isEmpty(userCode) ){
            jbdTravelPointDetail2013s = jbdTravelPointDetail2013Manager.getJbdTravelPointDetail2013sByCrm(crm,pager);
            jbdMemberLinkCalcHist2013s = jbdTravelPoint2013Manager.getJbdTravelPoint2013sByUserCode(defSysUser.getUserCode());
            request.setAttribute("jbdMemberLinkCalcHist2013s", jbdMemberLinkCalcHist2013s);
        }
        
        
        request.setAttribute("jbdTravelPointDetail2013ListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdTravelPointDetail2013List", Constants.JBDTRAVELPOINTDETAIL2013_LIST, jbdTravelPointDetail2013s);
    }
}
