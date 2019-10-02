package com.joymain.jecs.fi.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.JfiPayData;
import com.joymain.jecs.fi.service.JfiPayBankManager;
import com.joymain.jecs.fi.service.JfiPayDataManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 银行到款条目列表
 * @author Alvin
 *
 */
public class JfiPayDataController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiPayDataController.class);
    private JfiPayDataManager jfiPayDataManager = null;
	private JfiPayBankManager jfiPayBankManager = null;
	private SysUserManager sysUserManager = null;

    public void setJfiPayBankManager(JfiPayBankManager jfiPayBankManager) {
		this.jfiPayBankManager = jfiPayBankManager;
	}

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public void setJfiPayDataManager(JfiPayDataManager jfiPayDataManager) {
        this.jfiPayDataManager = jfiPayDataManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

		if ("multiDeleteFiPayData".equals(request.getParameter("strAction"))) {
			//转为不处理
			String[] tempIds = request.getParameter("strTempIds").split(",");
			for (int i = 0; i < tempIds.length; i++) {
				if (!StringUtils.isEmpty(tempIds[i])) {
					JfiPayData jfiPayData = this.jfiPayDataManager.getJfiPayData(tempIds[i]);
					if(jfiPayData!=null && jfiPayData.getStatus()!=2){
						this.jfiPayDataManager.removeJfiPayData(tempIds[i]);
					}
				}
			}
			saveMessage(request, LocaleUtil.getLocalText("fiPayData.deleted"));
			
			ModelAndView mv=new ModelAndView("redirect:jfiPayDatas.html");
			mv.addObject("strAction", "listFiPayDatas");
			mv.addObject("needReload", "1");
			return mv;
		}
		
		List jfiPayBanks=null;
		if("AA".equals(SessionLogin.getLoginUser(request).getCompanyCode())){
			jfiPayBanks = this.jfiPayBankManager.getJfiPayBanks(null);
		}else{
			jfiPayBanks=this.jfiPayBankManager.getJfiPayBanksByCompany(SessionLogin.getLoginUser(request).getCompanyCode());
		}
		request.setAttribute("jfiPayBanks", jfiPayBanks);
		
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		crm.addField("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());

		Pager pager = new Pager("jfiPayDataListTable", request, 20);
		List jfiPayDatas = this.jfiPayDataManager.getJfiPayDatasByCrm(crm, pager);
		//根据数据重新设置分页数据
		request.setAttribute("jfiPayDataListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("fi/jfiPayDataList", Constants.JFIPAYDATA_LIST, jfiPayDatas);
    }
}
