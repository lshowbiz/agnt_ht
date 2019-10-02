package com.joymain.jecs.fi.webapp.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.FiProductPointTemp;
import com.joymain.jecs.fi.service.FiProductPointTempManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class FiProductPointTempController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiProductPointTempController.class);
    private FiProductPointTempManager fiProductPointTempManager = null;

    public void setFiProductPointTempManager(FiProductPointTempManager fiProductPointTempManager) {
        this.fiProductPointTempManager = fiProductPointTempManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        RequestUtil.freshSession(request,"listfiProductPointTempsJJ",3l);

        SysUser loginSysUser = SessionLogin.getLoginUser(request);
		if ("multiDeleteFiProductPointTempJJ".equals(request.getParameter("strAction"))) {
			if("C".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"multiDeleteFiProductPointTempJJ",3l)!=0){
        			return new ModelAndView("redirect:fiProductPointTemps.html");
        		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"multiDeleteFiProductPointTempJJ",3l)!=0){
        			return new ModelAndView("redirect:fiProductPointTemps.html");
        		}
        	}
			//转为删除
			String[] tempIds = request.getParameter("strTempIds").split(",");
			for (int i = 0; i < tempIds.length; i++) {
				if (!StringUtils.isEmpty(tempIds[i])) {
					FiProductPointTemp fiProductPointTemp = this.fiProductPointTempManager.getFiProductPointTemp(tempIds[i]);
					if(fiProductPointTemp!=null && fiProductPointTemp.getStatus()!=2){
						this.fiProductPointTempManager.removeFiProductPointTemp(tempIds[i]);
					}
				}
			}
			saveMessage(request, LocaleUtil.getLocalText("jfiBankbookTemp.deleted"));
			ModelAndView mv=new ModelAndView("redirect:fiProductPointTemps.html");
			mv.addObject("strAction", "listfiProductPointTempsJJ");
			mv.addObject("needReload", "1");
			return mv;
		}
        
        CommonRecord crm = RequestUtil.toCommonRecord(request);
		crm.addField("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());
		crm.addField("createrCode", SessionLogin.getLoginUser(request).getUserCode());

		Pager pager = new Pager("fiProductPointTempListTable", request, 20);
		
		List fiProductPointTemps=null;
		Map incExpMap = new HashMap();
		if(request.getParameter("search")!=null){
			if("C".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"listfiProductPointTempsJJ",10l)!=0){
        			return new ModelAndView("redirect:fiProductPointTemps.html");
        		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"listfiProductPointTempsJJ",1l)!=0){
        			return new ModelAndView("redirect:fiProductPointTemps.html");
        		}
        	}
			/** add by hdg 2016-12-22 */
            /*CustomField[] fields = crm.getFields();
    		w:for(CustomField field : fields) {
    			String name=field.getName();
    			if(!StringUtils.isEmpty(name)) {
    				if("endcreatetime".equals(name)) {
    					String value = (String)field.getValue();
    					if(!StringUtils.isEmpty(value)) {
    						field.setValue(value+" 23:59:59");
    					}
    					break w;
    				}
    			}
    		}*/
    		 /** add by hdg 2016-12-22 */
			fiProductPointTemps = this.fiProductPointTempManager.getFiProductPointTempsByCrm(crm, pager);
			incExpMap = this.fiProductPointTempManager.getIncExpStatMap(crm);
		}else{
			incExpMap.put("incTotal", new BigDecimal("0"));
			incExpMap.put("expTotal", new BigDecimal("0"));
		}
		//根据数据重新设置分页数据
		request.setAttribute("fiProductPointTempListTable_totalRows", pager.getTotalObjects());
		request.setAttribute("incExpMap", incExpMap);
        return new ModelAndView("fi/fiProductPointTempList", Constants.FIPRODUCTPOINTTEMP_LIST, fiProductPointTemps);
    }
}
