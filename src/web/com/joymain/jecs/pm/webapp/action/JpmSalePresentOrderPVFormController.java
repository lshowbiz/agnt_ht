package com.joymain.jecs.pm.webapp.action;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.util.GlobalVar;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.pm.model.JmiMemberTeam;
import com.joymain.jecs.pm.model.JpmSalePromoter;
import com.joymain.jecs.pm.service.JmiMemberTeamManager;
import com.joymain.jecs.pm.service.JpmSalePromoterManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JpmSalePresentOrderPVFormController extends BaseFormController {
    private JpmSalePromoterManager jpmSalePromoterManager = null;
    private JmiMemberTeamManager jmiMemberTeamManager;
    
   
    public JpmSalePresentOrderPVFormController() {
        setCommandName("jpmSalePromoter");
        setCommandClass(JpmSalePromoter.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String spno = request.getParameter("spno");
        JpmSalePromoter jpmSalePromoter = null;

        if (!StringUtils.isEmpty(spno)) {
            jpmSalePromoter = jpmSalePromoterManager.getJpmSalePromoter(spno);
            String teams = jpmSalePromoter.getTeamno();
            String orderTypes = jpmSalePromoter.getOrdertype();
            String companys = jpmSalePromoter.getCompanyno();
            String userLever = jpmSalePromoter.getUserLevel();
            
            log.info("teams is:["+teams+"] and orderTypes is:["+orderTypes+"] and companys is:"+companys);
            if(StringUtils.isNotBlank(teams)){
            	String[] teamArr = teams.split(",");
            	request.setAttribute("teamArr", teamArr);
            	request.setAttribute("teams", teams);
            }
            if(StringUtils.isNotBlank(orderTypes)){
            	String[] orderTypeArr = orderTypes.split(",");
            	request.setAttribute("orderTypes", orderTypes);
                request.setAttribute("orderTypeArr", orderTypeArr);
            }
            if(StringUtils.isNotBlank(companys)){
            	String[] compArr = companys.split(",");
            	 request.setAttribute("companys", companys);
                 request.setAttribute("compArr", compArr);
            }
            if(StringUtils.isNotBlank(userLever)){
            	String[] leverArr = userLever.split(",");
            	 request.setAttribute("userLever", userLever);
                 request.setAttribute("leverArr", leverArr);
            }
            request.setAttribute("spno", spno);
            request.setAttribute("jpmSalePro", jpmSalePromoter);
        } else {
            jpmSalePromoter = new JpmSalePromoter();
        }
        log.info("formBack object is:"+jpmSalePromoter);
        
        List<JmiMemberTeam> teamList = jmiMemberTeamManager.
    			getJmiMemberTeams(new JmiMemberTeam());
        request.setAttribute("orderMap", GlobalVar.orderMap);
        request.setAttribute("teamList", teamList);
        request.setAttribute("comSet", GlobalVar.comSet);
        return jpmSalePromoter;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)throws Exception {
        
    	if (log.isDebugEnabled()) {
            log.debug("presentOrderPVForm onSubmit method....");
        }
        
        JpmSalePromoter jpmSalePromoter = (JpmSalePromoter) command;
        log.info("jpmSalePromoter:"+jpmSalePromoter);
		/*
		 * 策略类型:
		 * 1折价促销, 2赠品促销,3积分促销
		 * 4根据订单总金额或PV送赠品
		 */
		String saleType = request.getParameter("saleType");
		String teams = request.getParameter("teams");
		String orderTypes = request.getParameter("orderTypes");
		String companys = request.getParameter("companys");
		String startime = request.getParameter("startime");
		String endtime = request.getParameter("endtime");
		String userLever =  request.getParameter("userLever");
		
		log.info("teams=["+teams+"] and order type=["+orderTypes+"] " +
				"and comp=["+companys+"] and userLever is:"+userLever+"]" );
		
		jpmSalePromoter.setStartime(
				DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", startime+" 00:00:00"));
		jpmSalePromoter.setEndtime(
				DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", endtime+" 23:59:59"));
		jpmSalePromoter.setSaleType(saleType);
		jpmSalePromoter.setTeamno(teams);
		jpmSalePromoter.setOrdertype(orderTypes);
		jpmSalePromoter.setCompanyno(companys);
		jpmSalePromoter.setUserLevel(userLever);	
		
		if(! validateForm(request,response,errors,jpmSalePromoter)){
			return showForm(request, response, errors);
		}
		jpmSalePromoterManager.saveJpmSalePromoter(jpmSalePromoter);
		
        return new ModelAndView(getSuccessView());
    }
    
    private boolean validateForm(HttpServletRequest request,
    		HttpServletResponse response,BindException errors,
    		JpmSalePromoter jpmSalePromoter) throws Exception {
    	if(jpmSalePromoter.getSpname()==null){
			errors.rejectValue("spname","errors.required",
					new Object[]{LocaleUtil.getLocalText("jpmSalePromoter.promoterName")}, "");
			return false;
		}
    	if(jpmSalePromoter.getStartime()==null){
			errors.rejectValue("startime","errors.required",
					new Object[]{LocaleUtil.getLocalText("jpmSalePromoter.startime")}, "");
			return false;
		}
		if(jpmSalePromoter.getEndtime()==null){
			errors.rejectValue("endtime","errors.required",
					new Object[]{LocaleUtil.getLocalText("jpmSalePromoter.endtime")}, "");
			return false;
		}
	
		if(jpmSalePromoter.getAmount() ==null || 
				jpmSalePromoter.getAmount().compareTo(new BigDecimal(0))<0){
			errors.rejectValue("amount","errors.required",
					new Object[]{LocaleUtil.getLocalText("jpmSalePromoter.amount")}, "");
			return false;
		}
		if(jpmSalePromoter.getPv() ==null || jpmSalePromoter.getPv()<0L){
			errors.rejectValue("PV","errors.required",
					new Object[]{LocaleUtil.getLocalText("jpmSalePromoter.pv")}, "");
			return false;
		}
		/*
		if(jpmSalePromoter.getTeamno()==null || "".equals(jpmSalePromoter.getTeamno())){
			errors.rejectValue("teamno","errors.required",
					new Object[]{LocaleUtil.getLocalText("jpmProductSaleTeamType.teamCode")}, "");
			return showForm(request, response, errors);
		}
		*/
		if(jpmSalePromoter.getOrdertype()==null || "".equals(jpmSalePromoter.getOrdertype())){
			errors.rejectValue("ordertype","errors.required",
					new Object[]{LocaleUtil.getLocalText("jpmSalePromoter.ordertype")}, "");
			return false;
		}
		if(jpmSalePromoter.getCompanyno()==null || "".equals(jpmSalePromoter.getCompanyno())){
			errors.rejectValue("companyno","errors.required",
					new Object[]{LocaleUtil.getLocalText("jpmSalePromoter.companyno")}, "");
			return false;
		}
		/*if(jpmSalePromoter.getUserLevel()==null || "".equals(jpmSalePromoter.getUserLevel())){
			errors.rejectValue("userLevel","errors.required",
					new Object[]{LocaleUtil.getLocalText("miMember.cardType")}, "");
			return false;
		}*/
		if(jpmSalePromoter.getIsactiva()==null){
			errors.rejectValue("isactiva","errors.required",
					new Object[]{LocaleUtil.getLocalText("jpmSalePromoter.isactiva")}, "");
			return false;
		}
		return true;
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
    
    public JmiMemberTeamManager getJmiMemberTeamManager() {
		return jmiMemberTeamManager;
	}
	public void setJmiMemberTeamManager(JmiMemberTeamManager jmiMemberTeamManager) {
		this.jmiMemberTeamManager = jmiMemberTeamManager;
	}
	public void setJpmSalePromoterManager(JpmSalePromoterManager jpmSalePromoterManager) {
        this.jpmSalePromoterManager = jpmSalePromoterManager;
    }
}
