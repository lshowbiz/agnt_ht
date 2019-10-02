package com.joymain.jecs.am.webapp.action;

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
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.am.model.JamMsnDetail;
import com.joymain.jecs.am.model.JamMsnType;
import com.joymain.jecs.am.service.JamMsnDetailManager;
import com.joymain.jecs.am.service.JamMsnTypeManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JamMsnDetailController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JamMsnDetailController.class);
    private JamMsnDetailManager jamMsnDetailManager = null;
    private JamMsnTypeManager jamMsnTypeManager;
    public void setJamMsnTypeManager(JamMsnTypeManager jamMsnTypeManager) {
		this.jamMsnTypeManager = jamMsnTypeManager;
	}

	public void setJamMsnDetailManager(JamMsnDetailManager jamMsnDetailManager) {
        this.jamMsnDetailManager = jamMsnDetailManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

		SysUser defSysUser = SessionLogin.getLoginUser(request);
		
//        CommonRecord crm=RequestUtil.toCommonRecord(request);
//        Pager pager = new Pager("jamMsnDetailListTable",request, 0);
        
//        List jamMsnDetails = jamMsnDetailManager.getJamMsnDetailsByCrm(crm,pager);
//        request.setAttribute("jamMsnDetailListTable_totalRows", pager.getTotalObjects());
		
		if("memberMsnStatus".equals(request.getParameter("strAction"))){
			JamMsnDetail jamMsnDetail=new JamMsnDetail();
			String mdId=request.getParameter("mdId");
			String mtId=request.getParameter("mtId");
			String status=request.getParameter("status");
			JamMsnType jamMsnType=jamMsnTypeManager.getJamMsnType(mtId);
			if (!StringUtils.isEmpty(mdId)) {
				jamMsnDetail=jamMsnDetailManager.getJamMsnDetail(mdId);
			}else{
				jamMsnDetail.setJamMsnType(jamMsnType);
				jamMsnDetail.setUserCode(defSysUser.getUserCode());
				
			}
			jamMsnDetail.setStatus(status);
			try {
				jamMsnDetailManager.saveJamMsnDetail(jamMsnDetail);
				MessageUtil.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
			} catch (Exception e) {
				MessageUtil.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateFail"));
			}
	        return new ModelAndView("redirect:jamMsnDetails.html?strAction=memberMsnList&needReload=1");
			
		}
		
		
		List jamMsnDetails=null;
		if("M".equals(defSysUser.getUserType())){
			jamMsnDetails = jamMsnDetailManager.getJamMsnDetailsBySql(defSysUser.getUserCode());
		}

        return new ModelAndView("am/jamMsnDetailList", Constants.JAMMSNDETAIL_LIST, jamMsnDetails);
    }
}
