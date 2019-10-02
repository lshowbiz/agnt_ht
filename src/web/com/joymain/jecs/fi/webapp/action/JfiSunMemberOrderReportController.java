package com.joymain.jecs.fi.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.service.JfiSunMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JfiSunMemberOrderReportController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiSunMemberOrderReportController.class);
    private JfiSunMemberOrderManager jfiSunMemberOrderManager = null;
	private JdbcTemplate jdbcTemplate = null;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setJfiSunMemberOrderManager(JfiSunMemberOrderManager jfiSunMemberOrderManager) {
        this.jfiSunMemberOrderManager = jfiSunMemberOrderManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
    	SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
        
        String strAction = request.getParameter("strAction");
        
        if(request.getMethod().toLowerCase().equals("post")&&"jfiSunMemberOrderReportEdit".equals(strAction)){
        	String agentNo = request.getParameter("agentNoPost");
        	String startLogTimePost = request.getParameter("startLogTimePost");
        	String endLogTimePost = request.getParameter("endLogTimePost");
        	String[] productId = request.getParameterValues("productId");
        	for(int i_no = 0 ; i_no < productId.length ; i_no++){
        		String productPrice = request.getParameter("productPrice"+productId[i_no]);
        		String price = request.getParameter("price"+productId[i_no]);
        		if(!StringUtil.isEmpty(productPrice) && !StringUtil.isEmpty(price)){
        			if(!productPrice.equals(price)){
        	        	String sql = "Select Mol_Id ";
        	        	sql += "From Jfi_Sun_Member_Order Jsmo, Jfi_Sun_Member_Order_List Jsmol ";
        	        	sql += "Where Jsmo.Mo_Id = Jsmol.Mo_Id ";
        	        	sql += "And Jsmol.Product_Id = " + productId[i_no] + " ";
        	        	sql += "and jsmol.price = " + productPrice + " ";
        	        	sql += "And Jsmo.Agent_No = '" + agentNo + "' ";
        	        	if(!StringUtil.isEmpty(startLogTimePost))
        	        	sql += "And Jsmo.Check_Time >= To_Date('" + startLogTimePost + " 00:00:00', 'yyyy-mm-dd hh24:mi:ss')";
        	        	if(!StringUtil.isEmpty(endLogTimePost))
        	        	sql += "And Jsmo.Check_Time <= To_Date('" + endLogTimePost + " 23:59:59', 'yyyy-mm-dd hh24:mi:ss')";
        				jdbcTemplate.update("update Jfi_Sun_Member_Order_List set price=" + price + " where mol_id in (" + sql + ")");
        			}
        		}
        	}
        	saveMessage(request, LocaleUtil.getLocalText(operatorSysUser.getDefCharacterCoding(),"BdSendRecordallot.allotSuccess"));
        }

        CommonRecord crm=RequestUtil.toCommonRecord(request);
        List jfiSunMemberOrders = new ArrayList();
        if(!StringUtil.isEmpty(crm.getString("agentNo", "")) || !StringUtil.isEmpty(request.getParameter("startLogTimePost")) || !StringUtil.isEmpty(request.getParameter("endLogTimePost"))){
            jfiSunMemberOrders = jfiSunMemberOrderManager.getJfiSunMemberOrderReportByCrm(crm);
        }

        return new ModelAndView("fi/jfiSunMemberOrderReport", Constants.JFISUNMEMBERORDER_LIST, jfiSunMemberOrders);
    }
}
