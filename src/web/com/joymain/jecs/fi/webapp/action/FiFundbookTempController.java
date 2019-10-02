package com.joymain.jecs.fi.webapp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.FiFundbookTemp;
import com.joymain.jecs.fi.service.FiFundbookTempManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class FiFundbookTempController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiFundbookTempController.class);
    private FiFundbookTempManager fiFundbookTempManager = null;

    public void setFiFundbookTempManager(FiFundbookTempManager fiFundbookTempManager) {
        this.fiFundbookTempManager = fiFundbookTempManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
//
//        FiFundbookTemp fiFundbookTemp = new FiFundbookTemp();
//        // populate object with request parameters
//        BeanUtils.populate(fiFundbookTemp, request.getParameterMap());
//
//	//List fiFundbookTemps = fiFundbookTempManager.getFiFundbookTemps(fiFundbookTemp);
//	/**** auto pagination ***/
//	CommonRecord crm=RequestUtil.toCommonRecord(request);
//        Pager pager = new Pager("fiFundbookTempListTable",request, 20);
//        List fiFundbookTemps = fiFundbookTempManager.getFiFundbookTempsByCrm(crm,pager);
//        request.setAttribute("fiFundbookTempListTable_totalRows", pager.getTotalObjects());
//        /*****/
        
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
		if ("multiDeleteFiFundbookTempJJ".equals(request.getParameter("strAction"))) {
			if("C".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"multiDeleteFiFundbookTempJJ",3l)!=0){
        			return new ModelAndView("redirect:fiFundbookTemps.html");
        		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"multiDeleteFiFundbookTempJJ",3l)!=0){
        			return new ModelAndView("redirect:fiFundbookTemps.html");
        		}
        	}
			//转为删除
			String[] tempIds = request.getParameter("strTempIds").split(",");
			for (int i = 0; i < tempIds.length; i++) {
				if (!StringUtils.isEmpty(tempIds[i])) {
					FiFundbookTemp fiFundbookTemp = this.fiFundbookTempManager.getFiFundbookTemp(tempIds[i]);
					if(fiFundbookTemp!=null && fiFundbookTemp.getStatus()!=2){
						this.fiFundbookTempManager.removeFiFundbookTemp(tempIds[i]);
					}
				}
			}
			saveMessage(request, LocaleUtil.getLocalText("fiFundbookTemp.deleted"));
			ModelAndView mv=new ModelAndView("redirect:fiFundbookTemps.html");
			mv.addObject("strAction", "listfiFundbookTempsJJ");
			mv.addObject("needReload", "1");
			return mv;
		}
        
        CommonRecord crm = RequestUtil.toCommonRecord(request);
        /** add by hdg 2016-12-23 */
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
		 /** add by hdg 2016-12-23 */
		crm.addField("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());
		crm.addField("createrCode", SessionLogin.getLoginUser(request).getUserCode());

		Pager pager = new Pager("fiFundbookTempListTable", request, 20);
		
		List fiFundbookTemps=null;
		Map incExpMap = new HashMap();
		if(request.getParameter("search")!=null){
			if("C".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"listfiFundbookTempsJJ",10l)!=0){
        			return new ModelAndView("redirect:fiFundbookTemps.html");
        		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"listfiFundbookTempsJJ",1l)!=0){
        			return new ModelAndView("redirect:fiFundbookTemps.html");
        		}
        	}
			fiFundbookTemps = this.fiFundbookTempManager.getFiFundbookTempsByCrm(crm, pager);
			incExpMap = this.fiFundbookTempManager.getIncExpStatMap(crm);
		}else{
			incExpMap.put("incTotal", new BigDecimal("0"));
			incExpMap.put("expTotal", new BigDecimal("0"));
		}
		//根据数据重新设置分页数据
		request.setAttribute("fiFundbookTempListTable_totalRows", pager.getTotalObjects());
		request.setAttribute("incExpMap", incExpMap);

        return new ModelAndView("fi/fiFundbookTempList", "fiFundbookTempList", fiFundbookTemps);
    }
}
