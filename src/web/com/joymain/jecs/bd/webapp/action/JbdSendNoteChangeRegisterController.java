package com.joymain.jecs.bd.webapp.action;

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
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.BdOutwardBank;
import com.joymain.jecs.bd.model.JbdSendNote;
import com.joymain.jecs.bd.service.BdOutwardBankManager;
import com.joymain.jecs.bd.service.JbdSendNoteManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdSendNoteChangeRegisterController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdSendNoteChangeRegisterController.class);
    private JbdSendNoteManager jbdSendNoteManager = null;

    private BdOutwardBankManager bdOutwardBankManager = null;
    public void setBdOutwardBankManager(BdOutwardBankManager bdOutwardBankManager) {
		this.bdOutwardBankManager = bdOutwardBankManager;
	}
    public void setJbdSendNoteManager(JbdSendNoteManager jbdSendNoteManager) {
        this.jbdSendNoteManager = jbdSendNoteManager;
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
        
        //取消登记
        if ("get".equalsIgnoreCase(request.getMethod()) && "cancalRegisterNote".equals(request.getParameter("strAction"))) {
        	String[] strAdvicesCodes = request.getParameter("strAdvicesCodes").split(",");
        	
			
        	
			try {		
				jbdSendNoteManager.cancalJbdSendNote(strAdvicesCodes, defSysUser);
				saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.operaterSuccess"));
			} catch (Exception e) {
				e.printStackTrace();
				saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.operaterFail"));
			}
        }

        
    	String startOperaterTime=request.getParameter("startOperaterTime");
    	String endOperaterTime=request.getParameter("endOperaterTime");
    	String wweek=request.getParameter("wweek");
    	String userCode=request.getParameter("userCode");
    	String name=request.getParameter("name");
    	String remittanceBNum=request.getParameter("remittanceBNum");
    	String userName=request.getParameter("userName");


    	CommonRecord crm=RequestUtil.toCommonRecord(request);
    	/** add by hdg 2016-12-23 */
        CustomField[] fields = crm.getFields();
		w:for(CustomField field : fields) {
			String fieldname=field.getName();
			if(!StringUtils.isEmpty(fieldname)) {
				if("endoperatertime".equals(fieldname)) {
					String value = (String)field.getValue();
					if(!StringUtils.isEmpty(value)) {
						field.setValue(value+" 23:59:59");
					}
					break w;
				}
			}
		}
		 /** add by hdg 2016-12-23 */
        crm.addField("companyCode", companyCode);

        Pager pager = new Pager("jbdSendNoteListTable",request, 20);
        
        
        BdOutwardBank searchBdOutwardBank=new BdOutwardBank();
		if(null!=companyCode){
		searchBdOutwardBank.setCompanyCode(companyCode);
		}
		List bdOutwardBanks= bdOutwardBankManager.getBdOutwardBanks(searchBdOutwardBank);
		request.setAttribute("bdOutwardBanks", bdOutwardBanks);
        

    	if(StringUtil.isEmpty(userName)&&StringUtil.isEmpty(userCode)&&StringUtil.isEmpty(name)&&StringUtil.isEmpty(startOperaterTime)&&StringUtil.isEmpty(endOperaterTime)){

            request.setAttribute("jbdSendNoteListTable_totalRows", pager.getTotalObjects());
    		return new ModelAndView("bd/jbdSendNoteChangeRegisterList");
    	}else{
            List jbdSendNotes = jbdSendNoteManager.getJbdSendNotesByCrm(crm,pager);
            request.setAttribute("jbdSendNoteListTable_totalRows", pager.getTotalObjects());
            return new ModelAndView("bd/jbdSendNoteChangeRegisterList", Constants.JBDSENDNOTE_LIST, jbdSendNotes);
    	}
    }
}
