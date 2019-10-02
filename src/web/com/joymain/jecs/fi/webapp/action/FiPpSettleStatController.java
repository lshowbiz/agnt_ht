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
import com.joymain.jecs.fi.service.FiBankbookTempManager;
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
public class FiPpSettleStatController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(FiPpSettleStatController.class);
	private FiBankbookTempManager fiBankbookTempManager = null;

	public void setFiBankbookTempManager(FiBankbookTempManager fiBankbookTempManager) {
		this.fiBankbookTempManager = fiBankbookTempManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		/** add by hdg 2016-12-23 */
        CustomField[] fields = crm.getFields();
		w:for(CustomField field : fields) {
			String name=field.getName();
			if(!StringUtils.isEmpty(name)) {
				if("endcreatetime".equals(name)) {
					String value = (String)field.getValue();
					if(!StringUtils.isEmpty(value)) {
						field.setValue(value+" 23:59:59");
					}
					break w;
				}
			}
		}
		 /** add by hdg 2016-12-23 */
		if (StringUtils.isEmpty(request.getParameter("search"))) {
			//第一次进来时不显示
			crm.addField("sysUser.userCode", "0");
		}
/*		else{
			if("C".equals(loginSysUser.getUserType())){
				if("xls".equals(request.getParameter("fiBankbookTempListTable_ev"))){
	        		if(RequestUtil.saveOperationSession(request,"fiSettleStatJJXML",60l)!=0){
	        			return new ModelAndView("redirect:fiSettleStat.html");
	        		}
	    		}else{
	    			if(RequestUtil.saveOperationSession(request,"fiSettleStatJJ",3l)!=0){
	        			return new ModelAndView("redirect:fiSettleStat.html");
	        		}
	    		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"fiSettleStatJJ",3l)!=0){
        			return new ModelAndView("redirect:fiSettleStat.html");
        		}
        	}
		}*/
		
		if(StringUtils.isEmpty(request.getParameter("companyCode"))){
			crm.addField("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());
		}

		Pager pager = new Pager("fiBankbookTempListTable", request, 20);
		List fiBankbookTemps = this.fiBankbookTempManager.getFiProductPointTempsByCrm(crm, pager);
		//根据数据重新设置分页数据
		request.setAttribute("fiBankbookTempListTable_totalRows", pager.getTotalObjects());

		Map incExpMap = this.fiBankbookTempManager.getPpIncExpStatMap(crm);
		request.setAttribute("incExpMap", incExpMap);

		return new ModelAndView("fi/fi_pp_settle_stat", Constants.FIBANKBOOKTEMP_LIST, fiBankbookTemps);
	}
}