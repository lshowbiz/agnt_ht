package com.joymain.jecs.fi.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.fi.model.FiCommonAddr;
import com.joymain.jecs.fi.service.FiCommonAddrManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class FiCommonAddrController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiCommonAddrController.class);
    private FiCommonAddrManager fiCommonAddrManager = null;
    private AlCompanyManager alCompanyManager;
	private AlCountryManager alCountryManager = null;

	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}
    public void setFiCommonAddrManager(FiCommonAddrManager fiCommonAddrManager) {
        this.fiCommonAddrManager = fiCommonAddrManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        FiCommonAddr fiCommonAddr = new FiCommonAddr();
        // populate object with request parameters
        BeanUtils.populate(fiCommonAddr, request.getParameterMap());

	//List fiCommonAddrs = fiCommonAddrManager.getFiCommonAddrs(fiCommonAddr);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("fiCommonAddrListTable",request, 20);
        List fiCommonAddrs = fiCommonAddrManager.getFiCommonAddrsByCrm(crm,pager);
        request.setAttribute("fiCommonAddrListTable_totalRows", pager.getTotalObjects());
        /*****/
      //查询省份
		AlCompany alCompany = alCompanyManager.getAlCompanyByCode("CN");
		AlCountry alCountry = new AlCountry();
		alCountry = alCountryManager.getAlCountryByCode(alCompany.getCountryCode());
		alCountry.getAlStateProvinces().iterator();
		request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());

        return new ModelAndView("fi/fiCommonAddrList", "fiCommonAddrList", fiCommonAddrs);
    }
}
