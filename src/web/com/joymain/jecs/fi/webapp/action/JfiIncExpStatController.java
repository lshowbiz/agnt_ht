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
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 收支统计
 * @author Alvin
 *
 */
public class JfiIncExpStatController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(JfiIncExpStatController.class);
	private JfiBankbookJournalManager jfiBankbookJournalManager = null;
	private AlCompanyManager alCompanyManager = null;
	private SysUserManager sysUserManager=null;
	
	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public void setJfiBankbookJournalManager(JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

        SysUser loginSysUser = SessionLogin.getLoginUser(request);
		CommonRecord crm = RequestUtil.toCommonRecord(request);
        RequestUtil.freshSession(request,"fiIncExpStat",3l);

		if (StringUtils.isEmpty(request.getParameter("companyCode"))) {
			crm.addField("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());
		}
		if (request.getParameter("search")==null) {
			//第一次进来时不显示
			crm.addField("sysUser.userCode", "0");
		}else{
			if("C".equals(loginSysUser.getUserType())){
				if("xls".equals(request.getParameter("jfiBankbookJournalListTable_ev"))){
	        		if(RequestUtil.saveOperationSession(request,"fiIncExpStatXML",60l)!=0){
	        			return new ModelAndView("redirect:jfiIncExpStat.html");
	        		}
	    		}else{
	    			if(RequestUtil.saveOperationSession(request,"fiIncExpStat",3l)!=0){
	        			return new ModelAndView("redirect:jfiIncExpStat.html");
	        		}
	    		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"fiIncExpStat",3l)!=0){
        			return new ModelAndView("redirect:jfiIncExpStat.html");
        		}
        	}
		}

		Pager pager = new Pager("jfiBankbookJournalListTable", request, 20);
		List jfiBankbookJournals = this.jfiBankbookJournalManager.getJfiBankbookJournalsByCrm(crm, pager);
		//根据数据重新设置分页数据
		request.setAttribute("jfiBankbookJournalListTable_totalRows", pager.getTotalObjects());

		Map incExpMap = this.jfiBankbookJournalManager.getIncExpStatMap(crm);
		request.setAttribute("incExpMap", incExpMap);

		List alCompanys = this.alCompanyManager.getAlCompanysExceptAA();
		request.setAttribute("alCompanys", alCompanys);
		
		request.setAttribute("sysUserManager", sysUserManager);

		return new ModelAndView("fi/jfi_inc_exp_stat", Constants.JFIBANKBOOKJOURNAL_LIST, jfiBankbookJournals);
	}
}
