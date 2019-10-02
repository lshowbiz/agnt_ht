package com.joymain.jecs.bd.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.service.BdBounsDeductManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class BdBounsDeductController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(BdBounsDeductController.class);
	private BdBounsDeductManager bdBounsDeductManager = null;

	public void setBdBounsDeductManager(BdBounsDeductManager bdBounsDeductManager) {
		this.bdBounsDeductManager = bdBounsDeductManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		String companyCode = "";
		SysUser defSysUser = SessionLogin.getLoginUser(request);
		if ("C".equals(defSysUser.getUserType())) {
			companyCode = defSysUser.getCompanyCode();
			if ("AA".equals(defSysUser.getCompanyCode())) {
				companyCode = null;
			}
		}else if("M".equals(defSysUser.getUserType())){
    		crm.addField("userCode", defSysUser.getUserCode());
    	}
		String strAction=request.getParameter("strAction");
		if("bdBounsDeductDetail".equals(strAction)){
			List bdBounsDeducts = bdBounsDeductManager.getBdBounsDeductsByCrmBySql(crm,  new Pager("bdBounsDeductListTable", request, 0));
			if(bdBounsDeducts.size()==1){
				request.setAttribute("bdBounsDeduct", bdBounsDeducts.get(0));
				return new ModelAndView("bd/bdBounsDeductDetail");
			}else{
				
			}
		}
	
		crm.addField("companyCode", companyCode);

		Pager pager = new Pager("bdBounsDeductListTable", request, 20);
		String userCode=request.getParameter("userCode");
		 if("M".equals(defSysUser.getUserType())){
			 userCode=defSysUser.getUserCode();
	    }
		String name=request.getParameter("name");
		String createName=request.getParameter("createName");
		
		String startCreateTime=request.getParameter("startCreateTime");
		String endCreateTime=request.getParameter("endCreateTime");
		
		String status=request.getParameter("status");
		if(StringUtil.isEmpty(status)&&StringUtil.isEmpty(userCode)&&StringUtil.isEmpty(name)&&StringUtil.isEmpty(createName)&&!StringUtil.isDate(startCreateTime)&&!StringUtil.isDate(endCreateTime)){
			request.setAttribute("bdBounsDeductListTable_totalRows", pager.getTotalObjects());

			return new ModelAndView("bd/bdBounsDeductList", "bdBounsDeductList", null);
		}else{
			List bdBounsDeducts = bdBounsDeductManager.getBdBounsDeductsByCrmBySql(crm, pager);
			request.setAttribute("bdBounsDeductListTable_totalRows", pager.getTotalObjects());

			return new ModelAndView("bd/bdBounsDeductList", "bdBounsDeductList", bdBounsDeducts);
		}
		
	}
}
