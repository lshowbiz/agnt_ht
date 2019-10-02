package com.joymain.jecs.pd.webapp.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.pd.model.PdShipFee;
import com.joymain.jecs.pd.service.PdShipFeeManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class PdShipFeeFormController extends BaseFormController {
    private PdShipFeeManager pdShipFeeManager = null;
    private AlStateProvinceManager alStateProvinceManager;
    private AlCountryManager alCountryManager;

    public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}
    public void setAlStateProvinceManager(
			AlStateProvinceManager alStateProvinceManager) {
		this.alStateProvinceManager = alStateProvinceManager;
	}
	public void setPdShipFeeManager(PdShipFeeManager pdShipFeeManager) {
        this.pdShipFeeManager = pdShipFeeManager;
    }
    public PdShipFeeFormController() {
        setCommandName("pdShipFee");
        setCommandClass(PdShipFee.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String psfId = request.getParameter("psfId");
        PdShipFee pdShipFee = null;

        if (!StringUtils.isEmpty(psfId)) {
            pdShipFee = pdShipFeeManager.getPdShipFee(psfId);
        } else {
            pdShipFee = new PdShipFee();
        }
        //获取地区
    	String companyCode="CN";
        AlCountry alCountry = new AlCountry();
    	alCountry = alCountryManager.getAlCountryByCode(companyCode);
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
        return pdShipFee;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        String view = "redirect:pdShipFees.html?strAction=pdShipFeeList";
        PdShipFee pdShipFee = (PdShipFee) command;
        boolean isNew = (pdShipFee.getPsfId() == null);
        Locale locale = request.getLocale();
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        String key=null;
	    String strAction = request.getParameter("strAction");
	    if ("deletePdShipFee".equals(strAction)  ) {
			pdShipFeeManager.removePdShipFee(pdShipFee.getPsfId().toString());
			key="bdOutWardBank.deleteSuccess";
		}else{
			//保存之前先做同一个地区的唯一性校验
			if(pdShipFeeManager.getUniqueResult(pdShipFee)){
				pdShipFeeManager.savePdShipFee(pdShipFee);
				key="saveOrUpdate.success";
			}else{
				key="pdShipFee.exist";
				saveMessage(request, getText(sessionLogin.getDefCharacterCoding(), key));
				view = "redirect:editPdShipFee.html?strAction="+strAction;
				return new ModelAndView(view);
			}
			
		}
		saveMessage(request, getText(sessionLogin.getDefCharacterCoding(), key));
        return new ModelAndView(view);
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
