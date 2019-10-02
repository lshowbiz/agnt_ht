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
import com.joymain.jecs.fi.service.FiFundbookJournalManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 产业化基金收支统计
 * @author shiyh
 *
 */
public class FiFundbookIncExpStatController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(FiFundbookIncExpStatController.class);
	private FiFundbookJournalManager fiFundbookJournalManager = null;
	private AlCompanyManager alCompanyManager = null;
	private SysUserManager sysUserManager=null;
	
	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public void setFiFundbookJournalManager(FiFundbookJournalManager fiFundbookJournalManager) {
		this.fiFundbookJournalManager = fiFundbookJournalManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        RequestUtil.freshSession(request,"fiIncExpStatJJ",3l);
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		/** add by hdg 2016-12-23 */
        CustomField[] fields = crm.getFields();
		w:for(CustomField field : fields) {
			String name=field.getName();
			if(!StringUtils.isEmpty(name)) {
				if("endcreatedate".equals(name)) {
					String value = (String)field.getValue();
					if(!StringUtils.isEmpty(value)) {
						field.setValue(value+" 23:59:59");
					}
					break w;
				}
			}
		}
		 /** add by hdg 2016-12-23 */
		if (StringUtils.isEmpty(request.getParameter("companyCode"))) {
			crm.addField(new CustomField("companyCode", 1111, SessionLogin.getLoginUser(request).getCompanyCode()));
		}
		if (StringUtils.isEmpty(request.getParameter("search"))) {
			//第一次进来时不显示
			crm.addField(new CustomField("sysUser.userCode", 1111, "0"));
		}else{
			if("C".equals(loginSysUser.getUserType())){
				if("xls".equals(request.getParameter("fiFundbookJournalListTable_ev"))){
	        		if(RequestUtil.saveOperationSession(request,"fiIncExpStatJJXML",60l)!=0){
	        			return new ModelAndView("redirect:fiFunkbookIncExpStat.html");
	        		}
	    		}else{
	    			if(RequestUtil.saveOperationSession(request,"fiIncExpStatJJ",3l)!=0){
	        			return new ModelAndView("redirect:fiFunkbookIncExpStat.html");
	        		}
	    		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"fiIncExpStatJJ",3l)!=0){
        			return new ModelAndView("redirect:fiFunkbookIncExpStat.html");
        		}
        	}
		}

		Pager pager = new Pager("fiFundbookJournalListTable", request, 20);
		List fiFundbookJournals = this.fiFundbookJournalManager.getFiFundbookJournalsByCrm(crm, pager);
		//根据数据重新设置分页数据
		request.setAttribute("fiFundbookJournalListTable_totalRows", pager.getTotalObjects());

		Map incExpMap = this.fiFundbookJournalManager.getIncExpStatMap(crm);
		request.setAttribute("incExpMap", incExpMap);

		List alCompanys = this.alCompanyManager.getAlCompanysExceptAA();
		request.setAttribute("alCompanys", alCompanys);
		
		request.setAttribute("sysUserManager", sysUserManager);

		return new ModelAndView("fi/fi_fundbook_inc_exp_stat", "fiFundbookJournals", fiFundbookJournals);
	}
}
