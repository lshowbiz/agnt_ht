package com.joymain.jecs.bd.webapp.action;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.BdBounsDeduct;
import com.joymain.jecs.bd.service.BdBounsDeductManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class BdBounsDeductCreateFormController extends BaseFormController {
    private BdBounsDeductManager bdBounsDeductManager = null;
    private JmiMemberManager jmiMemberManager;
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
    public void setBdBounsDeductManager(BdBounsDeductManager bdBounsDeductManager) {
        this.bdBounsDeductManager = bdBounsDeductManager;
    }
    public BdBounsDeductCreateFormController() {
        setCommandName("bdBounsDeduct");
        setCommandClass(BdBounsDeduct.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        BdBounsDeduct bdBounsDeduct = null;

        if (!StringUtils.isEmpty(id)) {
            bdBounsDeduct = bdBounsDeductManager.getBdBounsDeduct(id);
        } else {
            bdBounsDeduct = new BdBounsDeduct();
            bdBounsDeduct.setJmiMember(new JmiMember());
            bdBounsDeduct.getJmiMember().setSysUser(new SysUser());
        }

        return bdBounsDeduct;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        String companyCode="";
        SysUser defSysUser = SessionLogin.getLoginUser(request);
    
        companyCode=defSysUser.getCompanyCode();  
    	
        BdBounsDeduct bdBounsDeduct = (BdBounsDeduct) command;
       
        if ("editDeduct".equals(request.getParameter("strAction"))) {
        	try {
        		bdBounsDeductManager.saveBdBounsDeduct(bdBounsDeduct);
        		saveMessage(request,LocaleUtil.getLocalText("sys.message.updateSuccess"));
			} catch (Exception e) {
				saveMessage(request,LocaleUtil.getLocalText("sys.message.updateFail"));
			}
        	
        }

        if ("addDeduct".equals(request.getParameter("strAction"))) {
        	JmiMember resMiMember= jmiMemberManager.getJmiMember(bdBounsDeduct.getJmiMember().getUserCode());
        	if(resMiMember==null){
        		saveMessage(request, LocaleUtil.getLocalText("operation.notice.js.orderNo.miMember.memberNoError"));
        		return showForm(request, response, errors);
        	}else{
        	
	        	bdBounsDeduct.setStatus("1");
	        	BigDecimal money=bdBounsDeduct.getMoney();
	        	bdBounsDeduct.setRemainMoney(money);
	        	bdBounsDeduct.setDeductMoney(new BigDecimal(0));
	        	bdBounsDeduct.setCompanyCode(companyCode);
	        	bdBounsDeduct.setCreateTime(new Date());
	        	bdBounsDeduct.setCreateName(defSysUser.getUserName());
	        	bdBounsDeduct.setCreateCode(defSysUser.getUserCode());
	        	try {
	        		bdBounsDeductManager.saveBdBounsDeduct(bdBounsDeduct);
	        		saveMessage(request,LocaleUtil.getLocalText("sys.message.updateSuccess"));
				} catch (Exception e) {
					e.printStackTrace();
					saveMessage(request,LocaleUtil.getLocalText("sys.message.updateFail"));
				}
        	}
        	
        }

        return new ModelAndView("redirect:bdBounsDeductsCreate.html?strAction=bdBounsDeductsCreate&needReload=1");
    }
}
