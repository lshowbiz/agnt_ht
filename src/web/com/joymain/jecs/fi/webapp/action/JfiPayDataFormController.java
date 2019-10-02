package com.joymain.jecs.fi.webapp.action;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.fi.model.JfiPayData;
import com.joymain.jecs.fi.service.JfiPayBankManager;
import com.joymain.jecs.fi.service.JfiPayDataManager;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 银行到款条目表单
 * @author Alvin
 *
 */
public class JfiPayDataFormController extends BaseFormController {
    private JfiPayDataManager jfiPayDataManager = null;
	private JfiPayBankManager jfiPayBankManager = null;
	private SysIdManager sysIdManager = null;
	
	public void setJfiPayBankManager(JfiPayBankManager jfiPayBankManager) {
		this.jfiPayBankManager = jfiPayBankManager;
	}
	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}

    public void setJfiPayDataManager(JfiPayDataManager jfiPayDataManager) {
        this.jfiPayDataManager = jfiPayDataManager;
    }
    public JfiPayDataFormController() {
        setCommandName("jfiPayData");
        setCommandClass(JfiPayData.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
		String dataId = request.getParameter("dataId");
		JfiPayData jfiPayData = null;

		if (!StringUtils.isEmpty(dataId)) {
			jfiPayData = jfiPayDataManager.getJfiPayData(dataId);
			if(jfiPayData.getStatus()==2){
				//如果已确认,则不能修改
				throw new AppException(LocaleUtil.getLocalText("error.fiPayData.has.verified"));
			}
		} else {
			jfiPayData = new JfiPayData();
			jfiPayData.setDealDate(new Date());
		}
		
		List jfiPayBanks=null;
		if("AA".equals(SessionLogin.getLoginUser(request).getCompanyCode())){
			jfiPayBanks = this.jfiPayBankManager.getJfiPayBanks(null);
		}else{
			jfiPayBanks=this.jfiPayBankManager.getJfiPayBanksByCompany(SessionLogin.getLoginUser(request).getCompanyCode());
		}
		request.setAttribute("jfiPayBanks", jfiPayBanks);

		return jfiPayData;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

		JfiPayData jfiPayData = (JfiPayData) command;
		boolean isNew = (jfiPayData.getDataId() == null);

		if ("deleteFiPayData".equals(request.getParameter("strAction"))) {
			jfiPayDataManager.removeJfiPayData(jfiPayData.getDataId().toString());

			saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), "fiPayData.deleted"));
		} else {
			if("addFiPayData".equals(request.getParameter("strAction"))){
				String createNo=this.sysIdManager.buildIdStr("create_no");
				if(StringUtils.isEmpty(createNo)){
					throw new AppException(LocaleUtil.getLocalText("sysId.build.error")+":create_no");
				}
				jfiPayData.setCompanyCode(SessionLogin.getLoginUser(request).getCompanyCode());
				jfiPayData.setStatus(1);
				jfiPayData.setCreateNo(createNo);
				jfiPayData.setCreaterCode(SessionLogin.getOperatorUser(request).getUserCode());
				jfiPayData.setCreaterName(SessionLogin.getOperatorUser(request).getUserName());
				jfiPayData.setCreateTime(new Date());
			}
			jfiPayDataManager.saveJfiPayData(jfiPayData);

			String key = (isNew) ? "fiPayData.added" : "fiPayData.updated";
			saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), key));
		}

		ModelAndView mv=new ModelAndView(getSuccessView());
		mv.addObject("strAction", "listFiPayDatas");
		mv.addObject("needReload", "1");
		return mv;
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
    	String[] allowedFields = {"accountCode","dealDate","incomeMoney","excerpt"};
		binder.setAllowedFields(allowedFields);
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
