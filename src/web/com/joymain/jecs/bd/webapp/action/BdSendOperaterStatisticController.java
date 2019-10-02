package com.joymain.jecs.bd.webapp.action;



import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.model.BdOutwardBank;
import com.joymain.jecs.bd.service.BdOutwardBankManager;
import com.joymain.jecs.bd.service.JbdSendRecordHistManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;



public class BdSendOperaterStatisticController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(BdSendOperaterStatisticController.class);

    private JbdSendRecordHistManager jbdSendRecordHistManager;
    private BdOutwardBankManager bdOutwardBankManager;

    public void setBdOutwardBankManager(BdOutwardBankManager bdOutwardBankManager) {
		this.bdOutwardBankManager = bdOutwardBankManager;
	}

	public void setJbdSendRecordHistManager(
			JbdSendRecordHistManager jbdSendRecordHistManager) {
		this.jbdSendRecordHistManager = jbdSendRecordHistManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        String companyCode="";
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        if("C".equals(defSysUser.getUserType())){
        	companyCode=defSysUser.getCompanyCode();  
    		if("AA".equals(defSysUser.getCompanyCode())){
    			companyCode = null;
    		}
    	}

        
		String registerStatus=request.getParameter("registerStatus");
		String wweek=request.getParameter("wweek");
		String operaterCode=request.getParameter("operaterCode");	
        String startOperaterTime=request.getParameter("startOperaterTime");
        String endOperaterTime=request.getParameter("endOperaterTime");
        String remittanceBNum=request.getParameter("remittanceBNum");	
    	String sendLateCause=request.getParameter("sendLateCause");
    	String userCode=request.getParameter("userCode");
    	
    	CommonRecord crm=RequestUtil.toCommonRecord(request);
        crm.addField("companyCode", companyCode);
        crm.addField("remittanceMoneyGreater","0");
        crm.addField("tableName","VJbdSendRecord");

        crm.addField("notEqualCardType","0");

        WeekFormatUtil.setSearchFweek(crm);
        Pager pager = new Pager("bdSendOperaterStatisticListTable",request, 20);
        
        if("1".equals(registerStatus)){//待登记
        	crm.addField("reissueStatus", "1");
        	crm.addField("reissueStatus", "1");
        }
        else if("2".equals(registerStatus)){//登记成功
        	crm.addField("registerStatus", "2");
        	crm.addField("reissueStatus", "1");
        }
        else if("3".equals(registerStatus)){//待补发
        	crm.addField("registerStatus", "");
        	crm.addField("reissueStatus", "2");
        }
        else if("5".equals(registerStatus)){//转补发
        	crm.addField("registerStatus", "");
        	crm.addField("toReissue", "toReissue");
        }else if("6".equals(registerStatus)){//补发成功
        	crm.addField("registerStatus", "");
        	crm.addField("reissueStatus", "3");
        }
        
        
        BdOutwardBank searchBdOutwardBank=new BdOutwardBank();
		if(null!=companyCode){
		searchBdOutwardBank.setCompanyCode(companyCode);
		}
		List bdOutwardBanks= bdOutwardBankManager.getBdOutwardBanks(searchBdOutwardBank);
		request.setAttribute("bdOutwardBanks", bdOutwardBanks);

    	if(StringUtil.isEmpty(userCode)&&!StringUtil.isInteger(wweek)&&StringUtil.isEmpty(operaterCode)&&StringUtil.isEmpty(remittanceBNum)&&!StringUtil.isDate(startOperaterTime)&&!StringUtil.isDate(endOperaterTime)){
    		request.setAttribute("bdSendOperaterStatisticListTable_totalRows", pager.getTotalObjects());
    		return new ModelAndView("bd/bdSendOperaterStatisticList");
    	}else{
//    		计数总金额
    		
    		List bdSendOperaterStatistics=jbdSendRecordHistManager.getJbdSendRecordHistsByCrm(crm, pager);
    		request.setAttribute("bdSendOperaterStatisticListTable_totalRows", pager.getTotalObjects());
    		BigDecimal totalRemittanceMoney =jbdSendRecordHistManager.getSumRemittanceMoney(crm);
    		request.setAttribute("totalRemittanceMoney", totalRemittanceMoney);
    		
    		return new ModelAndView("bd/bdSendOperaterStatisticList", "bdSendOperaterStatisticList", bdSendOperaterStatistics);
    	}
    		
    	

    
    }
}
