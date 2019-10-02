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
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.JfiBankbookTemp;
import com.joymain.jecs.fi.service.JfiBankbookTempManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.fi.model.JfiBankbookTemp;
import com.joymain.jecs.webapp.util.LocaleUtil;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 存折条目管理
 * @author Alvin
 *
 */
public class JfiBankbookTempController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiBankbookTempController.class);
    private JfiBankbookTempManager jfiBankbookTempManager = null;

    public void setJfiBankbookTempManager(JfiBankbookTempManager jfiBankbookTempManager) {
        this.jfiBankbookTempManager = jfiBankbookTempManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        RequestUtil.freshSession(request,"listfiBankbookTemps",3l);
        
		if ("multiDeleteFiBankbookTemp".equals(request.getParameter("strAction"))) {
			if("C".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"multiDeleteFiBankbookTemp",3l)!=0){
        			return new ModelAndView("redirect:jfiBankbookTemps.html");
        		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"multiDeleteFiBankbookTemp",3l)!=0){
        			return new ModelAndView("redirect:jfiBankbookTemps.html");
        		}
        	}
			//转为删除
			String[] tempIds = request.getParameter("strTempIds").split(",");
			for (int i = 0; i < tempIds.length; i++) {
				if (!StringUtils.isEmpty(tempIds[i])) {
					JfiBankbookTemp jfiBankbookTemp = this.jfiBankbookTempManager.getJfiBankbookTemp(tempIds[i]);
					if(jfiBankbookTemp!=null && jfiBankbookTemp.getStatus()!=2){
						this.jfiBankbookTempManager.removeJfiBankbookTemp(tempIds[i]);
					}
				}
			}
			saveMessage(request, LocaleUtil.getLocalText("jfiBankbookTemp.deleted"));
			ModelAndView mv=new ModelAndView("redirect:jfiBankbookTemps.html");
			mv.addObject("strAction", "listfiBankbookTemps");
			mv.addObject("needReload", "1");
			return mv;
		}
        
        CommonRecord crm = RequestUtil.toCommonRecord(request);
		crm.addField("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());
		crm.addField("createrCode", SessionLogin.getLoginUser(request).getUserCode());
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
		Pager pager = new Pager("jfiBankbookTempListTable", request, 20);
		
		List jfiBankbookTemps=null;
		Map incExpMap = new HashMap();
		if(request.getParameter("search")!=null){
			if("C".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"listfiBankbookTemps",10l)!=0){
        			return new ModelAndView("redirect:jfiBankbookTemps.html");
        		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"listfiBankbookTemps",1l)!=0){
        			return new ModelAndView("redirect:jfiBankbookTemps.html");
        		}
        	}
			jfiBankbookTemps = this.jfiBankbookTempManager.getJfiBankbookTempsByCrm(crm, pager);
			incExpMap = this.jfiBankbookTempManager.getIncExpStatMap(crm);
		}else{
			incExpMap.put("incTotal", new BigDecimal("0"));
			incExpMap.put("expTotal", new BigDecimal("0"));
		}
		//根据数据重新设置分页数据
		request.setAttribute("jfiBankbookTempListTable_totalRows", pager.getTotalObjects());
		request.setAttribute("incExpMap", incExpMap);
        return new ModelAndView("fi/jfiBankbookTempList", Constants.JFIBANKBOOKTEMP_LIST, jfiBankbookTemps);
    }
}
