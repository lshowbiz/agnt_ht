package com.joymain.jecs.pm.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.JocsInterfaceLog;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.ReportLogUtilService;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pm.model.JpmCouponInfo;
import com.joymain.jecs.pm.service.JpmCouponInfoManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import service.MsgSendService;

public class JpmCouponInfoController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpmCouponInfoController.class);
    private JpmCouponInfoManager jpmCouponInfoManager = null;

    public void setJpmCouponInfoManager(JpmCouponInfoManager jpmCouponInfoManager) {
        this.jpmCouponInfoManager = jpmCouponInfoManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JpmCouponInfo jpmCouponInfo = new JpmCouponInfo();
        BeanUtils.populate(jpmCouponInfo, request.getParameterMap());
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        String key ="";
        String view = "pm/jpmCouponInfoList";
		//获得登录账号对象(主要获得当前登录账号的所属国别地区) 
		SysUser loginSysUser = SessionLogin.getLoginUser(request);
		String userCode= loginSysUser.getUserCode();
		String cpId = request.getParameter("cpId");
		String status2 = request.getParameter("status2"); 
		String strAction = request.getParameter("strAction");
		
		 crm=initCommonRecord(request);//存放request中的值例如：strAction
	    
		//confirmJpmCouponInfo
		if(StringUtils.isNotEmpty(strAction)&&strAction.equals("confirmJpmCouponInfo")){
		if(StringUtils.isNotEmpty(cpId) && StringUtils.isNotEmpty(status2)){
			int i = jpmCouponInfoManager.batchAuditCouponInfo(cpId, status2,userCode); 
			if(i>0){
			key="代金券审核成功";
			saveMessage(request, key);
			}else{
			key="代金券审核失败";
			saveMessage(request, key);
			}
		}
		}
        
		//选择代金券，商品绑定代金券
		if("selectJpmCouponInfo".equals(strAction)){
			 crm = RequestUtil.toCommonRecord(request);
	         view = "pm/jpmCouponInfoSelect";
	         

	         //如果不是全球，则设置区域
	         SysUser loginUser = SessionLogin.getLoginUser(request);
	         if (!"AA".equalsIgnoreCase(loginUser.getCompanyCode())) {
	             crm.setValue("companyCode", loginUser.getCompanyCode());

	        }
//	        String uniNo = request.getParameter("uniNo");
	        String cpName = request.getParameter("cpName");
	        crm.setValue("cpName", cpName);
	         
		}
		
		
		String status = request.getParameter("status");
		String startCreateTime = request.getParameter("startCreateTime");
		String endCreateTime = request.getParameter("endCreateTime");
		String startUpdateTime = request.getParameter("startUpdateTime");
		String endUpdateTime = request.getParameter("endUpdateTime");
		
        crm.setValue("status", status);
        crm.setValue("startCreateTime", startCreateTime);
        crm.setValue("endCreateTime", endCreateTime);
        crm.setValue("startUpdateTime", startUpdateTime);
        crm.setValue("endUpdateTime", endUpdateTime);
        
        Pager pager = new Pager("jpmCouponInfoListTable",request, 20);
        List jpmCouponInfos = jpmCouponInfoManager.getJpmCouponInfosByCrm(crm,pager);
        request.setAttribute("jpmCouponInfoListTable_totalRows", pager.getTotalObjects());
        
        return new ModelAndView(view, "jpmCouponInfoList", jpmCouponInfos);
    }
}
