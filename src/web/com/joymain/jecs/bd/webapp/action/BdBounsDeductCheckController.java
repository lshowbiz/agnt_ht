package com.joymain.jecs.bd.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.model.BdBounsDeduct;
import com.joymain.jecs.bd.service.BdBounsDeductManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class BdBounsDeductCheckController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(BdBounsDeductCheckController.class);
    private BdBounsDeductManager bdBounsDeductManager = null;

    public void setBdBounsDeductManager(BdBounsDeductManager bdBounsDeductManager) {
        this.bdBounsDeductManager = bdBounsDeductManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        String companyCode="";
        SysUser defSysUser = SessionLogin.getLoginUser(request);
      
        	companyCode=defSysUser.getCompanyCode();  
  
        
        //确认
        if ("get".equalsIgnoreCase(request.getMethod()) && "checkSelected".equals(request.getParameter("strActionOperation"))) {
        	String[] strAdvicesCodes = request.getParameter("strAdvicesCodes").split(",");
        	List<BdBounsDeduct> bdBounsDeductCheckList =new ArrayList<BdBounsDeduct>();
			for (int i = 0; i < strAdvicesCodes.length; i++) {
				if (!StringUtils.isEmpty(strAdvicesCodes[i])) {
					BdBounsDeduct bdBounsDeduct= bdBounsDeductManager.getBdBounsDeduct(strAdvicesCodes[i]);
					if(!"2".equals(bdBounsDeduct.getStatus())){
						bdBounsDeduct.setStatus("2");
						bdBounsDeduct.setRemainMoney(bdBounsDeduct.getMoney());
						bdBounsDeduct.setCheckTime(new Date());
						bdBounsDeduct.setCheckerCode(defSysUser.getUserCode());
						bdBounsDeduct.setCheckerName(defSysUser.getUserName());
						bdBounsDeductCheckList.add(bdBounsDeduct);
					}
				}
			}
			try {
				if(bdBounsDeductCheckList.size()!=0){
					bdBounsDeductManager.saveBdBounsDeducts(bdBounsDeductCheckList);
					saveMessage(request, LocaleUtil.getLocalText("bdBounsDeduct.checkSuccess"));
				}
				
			} catch (Exception e) {
				saveMessage(request, LocaleUtil.getLocalText("bdBounsDeduct.checkFail"));
			}
        }
        
        //不确认
        if ("get".equalsIgnoreCase(request.getMethod()) && "unCheckSelected".equals(request.getParameter("strActionOperation"))) {
        	String[] strAdvicesCodes = request.getParameter("strAdvicesCodes").split(",");
        	List<BdBounsDeduct> bdBounsDeductUnCheckList =new ArrayList<BdBounsDeduct>();
			for (int i = 0; i < strAdvicesCodes.length; i++) {
				if (!StringUtils.isEmpty(strAdvicesCodes[i])) {
					BdBounsDeduct bdBounsDeduct= bdBounsDeductManager.getBdBounsDeduct(strAdvicesCodes[i]);
					if(!"3".equals(bdBounsDeduct.getStatus())){
					bdBounsDeduct.setStatus("3");
					bdBounsDeduct.setCheckTime(new Date());
					bdBounsDeduct.setCheckerCode(defSysUser.getUserCode());
					bdBounsDeduct.setCheckerName(defSysUser.getUserName());
					bdBounsDeductUnCheckList.add(bdBounsDeduct);
					}
				}
			}
			try {
				if(bdBounsDeductUnCheckList.size()!=0){
					bdBounsDeductManager.saveBdBounsDeducts(bdBounsDeductUnCheckList);
					saveMessage(request, LocaleUtil.getLocalText("bdBounsDeduct.unCheckSuccess"));
				}

			} catch (Exception e) {
				saveMessage(request, LocaleUtil.getLocalText("bdBounsDeduct.unCheckFail"));
			}
        }
        
        //转未确认
        if ("get".equalsIgnoreCase(request.getMethod()) && "bdNotCheckedSelected".equals(request.getParameter("strActionOperation"))) {
        	String[] strAdvicesCodes = request.getParameter("strAdvicesCodes").split(",");
        	List<BdBounsDeduct> bdBounsDeductUnCheckList =new ArrayList<BdBounsDeduct>();
			for (int i = 0; i < strAdvicesCodes.length; i++) {
				if (!StringUtils.isEmpty(strAdvicesCodes[i])) {
					BdBounsDeduct bdBounsDeduct= bdBounsDeductManager.getBdBounsDeduct(strAdvicesCodes[i]);
					if(null!=bdBounsDeduct.getDeductTime()){
						continue;
					}
					if(!"1".equals(bdBounsDeduct.getStatus())){
					bdBounsDeduct.setStatus("1");
					bdBounsDeduct.setCheckTime(null);					
					bdBounsDeduct.setCheckerCode(null);
					bdBounsDeduct.setCheckerName(null);
					bdBounsDeductUnCheckList.add(bdBounsDeduct);
					}
				}
			}
			try {
				if(bdBounsDeductUnCheckList.size()!=0){
				bdBounsDeductManager.saveBdBounsDeducts(bdBounsDeductUnCheckList);
				saveMessage(request, LocaleUtil.getLocalText("bdBounsDeduct.notCheckedSuccess"));
				}
			} catch (Exception e) {
				saveMessage(request, LocaleUtil.getLocalText("bdBounsDeduct.notCheckedFail"));
			}
        }
        
        

        if("C".equals(defSysUser.getUserType())){
        	companyCode=defSysUser.getCompanyCode();  
    		if("AA".equals(defSysUser.getCompanyCode())){
    			companyCode = null;
    		}
    	}
        
        
        CommonRecord crm=RequestUtil.toCommonRecord(request);
		crm.addField("companyCode", companyCode);

		
		String userCode=request.getParameter("userCode");
		String name=request.getParameter("name");
		String createName=request.getParameter("createName");
		String startCreateTime = request.getParameter("startCreateTime");
		String endCreateTime =request.getParameter("endCreateTime");
		String status =request.getParameter("status");
		
		
		
		
		
		
        Pager pager = new Pager("bdBounsDeductCheckListTable",request, 20);
        

        if(!"1".equals(status)&&StringUtil.isEmpty(userCode)&&StringUtil.isEmpty(name)&&StringUtil.isEmpty(createName)&&!StringUtil.isDate(startCreateTime)&&!StringUtil.isDate(endCreateTime)){
        	 request.setAttribute("bdBounsDeductCheckListTable_totalRows", pager.getTotalObjects());
        	 return new ModelAndView("bd/bdBounsDeductCheckList");
        }
        
        List bdBounsDeductsCheck = bdBounsDeductManager.getBdBounsDeductsByCrm(crm,pager);
        request.setAttribute("bdBounsDeductCheckListTable_totalRows", pager.getTotalObjects());


        return new ModelAndView("bd/bdBounsDeductCheckList", "bdBounsDeductCheckList", bdBounsDeductsCheck);
		
    }
}
