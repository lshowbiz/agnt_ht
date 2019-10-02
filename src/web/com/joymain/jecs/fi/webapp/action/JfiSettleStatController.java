package com.joymain.jecs.fi.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.fi.service.JfiBankbookTempManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 结款统计
 * @author Alvin
 *
 */
public class JfiSettleStatController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(JfiSettleStatController.class);
	private JfiBankbookTempManager jfiBankbookTempManager = null;

	public void setJfiBankbookTempManager(JfiBankbookTempManager jfiBankbookTempManager) {
		this.jfiBankbookTempManager = jfiBankbookTempManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

        SysUser loginSysUser = SessionLogin.getLoginUser(request);
		CommonRecord crm = RequestUtil.toCommonRecord(request);
        RequestUtil.freshSession(request,"fiSettleStat",3l);

		if (StringUtils.isEmpty(request.getParameter("search"))) {
			//第一次进来时不显示
			crm.addField("sysUser.userCode", "0");
		}else{
			if("C".equals(loginSysUser.getUserType())){
				if("xls".equals(request.getParameter("jfiBankbookTempListTable_ev"))){
	        		if(RequestUtil.saveOperationSession(request,"fiSettleStatXML",60l)!=0){
	        			return new ModelAndView("redirect:jfiSettleStat.html");
	        		}
	    		}else{
	    			if(RequestUtil.saveOperationSession(request,"fiSettleStat",3l)!=0){
	        			return new ModelAndView("redirect:jfiSettleStat.html");
	        		}
	    		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"fiSettleStat",3l)!=0){
        			return new ModelAndView("redirect:jfiSettleStat.html");
        		}
        	}
		}
		
		if(StringUtils.isEmpty(request.getParameter("companyCode"))){
			crm.addField("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());
		}

		Pager pager = new Pager("jfiBankbookTempListTable", request, 20);
		List jfiBankbookTemps = this.jfiBankbookTempManager.getJfiBankbookTempsByCrm(crm, pager);
		//根据数据重新设置分页数据
		request.setAttribute("jfiBankbookTempListTable_totalRows", pager.getTotalObjects());

		Map incExpMap = this.jfiBankbookTempManager.getIncExpStatMap(crm);
		request.setAttribute("incExpMap", incExpMap);

		return new ModelAndView("fi/jfi_settle_stat", Constants.JFIBANKBOOKTEMP_LIST, jfiBankbookTemps);
	}
}