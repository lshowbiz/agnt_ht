package com.joymain.jecs.fi.webapp.action;

import java.util.ArrayList;
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
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.FiFundbookTemp;
import com.joymain.jecs.fi.service.FiFundbookJournalManager;
import com.joymain.jecs.fi.service.FiFundbookTempManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 产业基金批量审核处理控制器
 * @author EC后台
 *
 */
public class FiFundbookTempChkController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiFundbookTempController.class);
    private FiFundbookTempManager fiFundbookTempManager = null;
    private FiFundbookJournalManager fiFundbookJournalManager = null;

    public void setFiFundbookJournalManager(FiFundbookJournalManager fiFundbookJournalManager) {
        this.fiFundbookJournalManager = fiFundbookJournalManager;
    }
    public void setFiFundbookTempManager(FiFundbookTempManager fiFundbookTempManager) {
        this.fiFundbookTempManager = fiFundbookTempManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
        RequestUtil.freshSession(request,"listFiFundbookTempsChkJJ",3l);
        SysUser loginSysUser = SessionLogin.getLoginUser(request);

		if ("doNotDealFiFundbookTempJJ".equals(request.getParameter("strAction"))) {
			if("C".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"doNotDealFiFundbookTempJJ",10l)!=0){
        			return new ModelAndView("redirect:fiFundbookTempsChk.html");
        		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"doNotDealFiFundbookTempJJ",10l)!=0){
        			return new ModelAndView("redirect:fiFundbookTempsChk.html");
        		}
        	}
			//转为不处理
			String[] tempIds = request.getParameter("strTempIds").split(",");
			List<FiFundbookTemp> fiFundbookTemps = new ArrayList<FiFundbookTemp>();
			for (int i = 0; i < tempIds.length; i++) {
				if (!StringUtils.isEmpty(tempIds[i])) {
					FiFundbookTemp fiFundbookTemp = this.fiFundbookTempManager.getFiFundbookTemp(tempIds[i]);
					if(fiFundbookTemp.getStatus()==1){
						fiFundbookTemp.setStatus(3);
						fiFundbookTemps.add(fiFundbookTemp);
					}
				}
			}
			this.fiFundbookTempManager.saveFiFundbookTemps(fiFundbookTemps);
			saveMessage(request, LocaleUtil.getLocalText("fiBankbookTemp.doNotDealed"));
			
			ModelAndView mv=new ModelAndView("redirect:fiFundbookTempsChk.html");
			mv.addObject("strAction", "listFiFundbookTempsChkJJ");
			mv.addObject("needReload", "1");
			return mv;
		} else if ("notVerifiedFiFundbookTempJJ".equals(request.getParameter("strAction"))) {
			if("C".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"notVerifiedFiFundbookTempJJ",10l)!=0){
        			return new ModelAndView("redirect:fiFundbookTempsChk.html");
        		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"notVerifiedFiFundbookTempJJ",10l)!=0){
        			return new ModelAndView("redirect:fiFundbookTempsChk.html");
        		}
        	}
			//转为未核实
			String[] tempIds = request.getParameter("strTempIds").split(",");
			List<FiFundbookTemp> fiFundbookTemps = new ArrayList<FiFundbookTemp>();
			for (int i = 0; i < tempIds.length; i++) {
				if (!StringUtils.isEmpty(tempIds[i])) {
					FiFundbookTemp fiFundbookTemp = this.fiFundbookTempManager.getFiFundbookTemp(tempIds[i]);
					if(fiFundbookTemp.getStatus()==3){
						fiFundbookTemp.setStatus(1);
						fiFundbookTemps.add(fiFundbookTemp);
					}
				}
			}
			this.fiFundbookTempManager.saveFiFundbookTemps(fiFundbookTemps);
			saveMessage(request, LocaleUtil.getLocalText("fiBankbookTemp.notVerified"));
			
			ModelAndView mv=new ModelAndView("redirect:fiFundbookTempsChk.html");
			mv.addObject("strAction", "listFiFundbookTempsChkJJ");
			mv.addObject("needReload", "1");
			return mv;
		}else if ("verifyFiFundbookTempJJ".equals(request.getParameter("strAction"))) {
			if("C".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"verifyFiFundbookTempJJ",10l)!=0){
        			return new ModelAndView("redirect:fiFundbookTempsChk.html");
        		}
        	}else if("M".equals(loginSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"verifyFiFundbookTempJJ",10l)!=0){
        			return new ModelAndView("redirect:fiFundbookTempsChk.html");
        		}
        	}
			
			Integer runResult = 0;//是否全部执行成功标识,0:代表全部执行成功
			StringBuffer errResultMsg = new StringBuffer();//错误记录
			StringBuffer okResultMsg = new StringBuffer();//执行成功记录
			
			//确认、核准
			String[] tempIds = request.getParameter("strTempIds").split(",");
			try{
				SysUser sysUser=SessionLogin.getLoginUser(request);
				for (int i = 0; i < tempIds.length; i++) {
					
					if (!StringUtils.isEmpty(tempIds[i])) {
						try{
							
							//将临时条目插入基金流水表
							this.fiFundbookJournalManager.saveFiFundbookTempCheck(tempIds[i], sysUser);
							okResultMsg.append(i+"行");
						}catch(AppException ae){
							
							runResult = 1;
							errResultMsg.append(i+"行");
							log.error("错误:", ae);
							continue;
						}
					}
				}
			}catch(AppException e){
				
				saveMessage(request, LocaleUtil.getLocalText("error.fiFundbookJournal.balance.not.enough"));
				ModelAndView mv=new ModelAndView("redirect:fiFundbookTempsChk.html");
				mv.addObject("strAction", "listFiFundbookTempsChkJJ");
				mv.addObject("needReload", "1");
				return mv;
			}
			
			if(runResult == 0){
				saveMessage(request, "您选择的所有产业化基金条目都核实成功！");
			}else{
				
				if(!StringUtils.isEmpty(okResultMsg.toString()))
					saveMessage(request, "您选择的第"+errResultMsg.toString()+"记录核实失败;第"+okResultMsg+"记录核实成功");
				else
					saveMessage(request, "您选择的所有记录全部核实失败！请查看余额是否足够！");
			}
			
			ModelAndView mv=new ModelAndView("redirect:fiFundbookTempsChk.html");
			mv.addObject("strAction", "listFiFundbookTempsChkJJ");
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
		if(StringUtils.isEmpty(request.getParameter("companyCode"))){
			crm.addField("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());
		}

		Pager pager = new Pager("fiFundbookTempListTable", request, 20);
		
		List fiFundbookTemps=null;
		if(request.getParameter("search")!=null){
			//if(!StringUtils.isEmpty(crm.getString("sysUser.jmiMember.lastNameKana", "")) || !StringUtils.isEmpty(crm.getString("sysUser.jmiMember.firstNameKana", "")) || !StringUtils.isEmpty(crm.getString("sysUser.userCode", "")) || !StringUtils.isEmpty(crm.getString("moneyType", "")) || !StringUtils.isEmpty(crm.getString("status", "")) || !StringUtils.isEmpty(crm.getString("startCreateTime", "")) || !StringUtils.isEmpty(crm.getString("endCreateTime", "")) || !StringUtils.isEmpty(crm.getString("createrName", "")) ){

				if("C".equals(loginSysUser.getUserType())){
	        		if(RequestUtil.saveOperationSession(request,"listFiFundbookTempsChkJJ",3l)!=0){
	        			return new ModelAndView("redirect:fiFundbookTempsChk.html");
	        		}
	        	}else if("M".equals(loginSysUser.getUserType())){
	        		if(RequestUtil.saveOperationSession(request,"listFiFundbookTempsChkJJ",3l)!=0){
	        			return new ModelAndView("redirect:fiFundbookTempsChk.html");
	        		}
	        	}
				
				fiFundbookTemps = this.fiFundbookTempManager.getFiFundbookTempsByCrm(crm, pager);
				Map incExpMap = this.fiFundbookTempManager.getIncExpStatMap(crm);
				request.setAttribute("incExpMap", incExpMap);
			//}
			
		}
		//根据数据重新设置分页数据
		request.setAttribute("fiFundbookTempListTable_totalRows", pager.getTotalObjects());
		ModelAndView mv=new ModelAndView("fi/fiFundbookTempChkList", "fiFundbookTempChkList", fiFundbookTemps);
		mv.addObject("strAction", "listFiFundbookTempsChkJJ");
		return mv;
	}
}
