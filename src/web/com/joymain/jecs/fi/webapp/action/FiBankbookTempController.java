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
import com.joymain.jecs.fi.model.FiBankbookTemp;
import com.joymain.jecs.fi.service.FiBankbookTempManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class FiBankbookTempController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiBankbookTempController.class);
    private FiBankbookTempManager fiBankbookTempManager = null;

    public void setFiBankbookTempManager(FiBankbookTempManager fiBankbookTempManager) {
        this.fiBankbookTempManager = fiBankbookTempManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        RequestUtil.freshSession(request,"listfiBankbookTempsJJ",3l);

        SysUser loginSysUser = SessionLogin.getLoginUser(request);
		if ("multiDeleteFiBankbookTempJJ".equals(request.getParameter("strAction"))) {
			if("C".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"multiDeleteFiBankbookTempJJ",3l)!=0){
        			return new ModelAndView("redirect:fiBankbookTemps.html");
        		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"multiDeleteFiBankbookTempJJ",3l)!=0){
        			return new ModelAndView("redirect:fiBankbookTemps.html");
        		}
        	}
			//转为删除
			String[] tempIds = request.getParameter("strTempIds").split(",");
			for (int i = 0; i < tempIds.length; i++) {
				if (!StringUtils.isEmpty(tempIds[i])) {
					FiBankbookTemp fiBankbookTemp = this.fiBankbookTempManager.getFiBankbookTemp(tempIds[i]);
					if(fiBankbookTemp!=null && fiBankbookTemp.getStatus()!=2){
						this.fiBankbookTempManager.removeFiBankbookTemp(tempIds[i]);
					}
				}
			}
			saveMessage(request, LocaleUtil.getLocalText("fiBankbookTemp.deleted"));
			ModelAndView mv=new ModelAndView("redirect:fiBankbookTemps.html");
			mv.addObject("strAction", "listfiBankbookTempsJJ");
			mv.addObject("needReload", "1");
			return mv;
		}
        
        CommonRecord crm = RequestUtil.toCommonRecord(request);
		crm.addField("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());
		crm.addField("createrCode", SessionLogin.getLoginUser(request).getUserCode());

		Pager pager = new Pager("fiBankbookTempListTable", request, 20);
		
		List fiBankbookTemps=null;
		Map incExpMap = new HashMap();
		if(request.getParameter("search")!=null){
			if("C".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"listfiBankbookTempsJJ",10l)!=0){
        			return new ModelAndView("redirect:fiBankbookTemps.html");
        		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"listfiBankbookTempsJJ",1l)!=0){
        			return new ModelAndView("redirect:fiBankbookTemps.html");
        		}
        	}
			/** add by hdg 2016-12-22 */
            CustomField[] fields = crm.getFields();
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
    		}
    		 /** add by hdg 2016-12-22 */
			fiBankbookTemps = this.fiBankbookTempManager.getFiBankbookTempsByCrm(crm, pager);
			incExpMap = this.fiBankbookTempManager.getIncExpStatMap(crm);
		}else{
			incExpMap.put("incTotal", new BigDecimal("0"));
			incExpMap.put("expTotal", new BigDecimal("0"));
		}
		//根据数据重新设置分页数据
		request.setAttribute("fiBankbookTempListTable_totalRows", pager.getTotalObjects());
		request.setAttribute("incExpMap", incExpMap);
        return new ModelAndView("fi/fiBankbookTempList", Constants.FIBANKBOOKTEMP_LIST, fiBankbookTemps);
    }
}
