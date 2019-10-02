package com.joymain.jecs.mi.webapp.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;



public class MiMemberChangeCardTypeController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(MiMemberChangeCardTypeController.class);
    private JmiMemberManager jmiMemberManager;
    private BdPeriodManager bdPeriodManager;
	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}


	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}


	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        SysUser defSysUser = SessionLogin.getLoginUser(request);

        CommonRecord crm = RequestUtil.toCommonRecord(request);
        
    	String userCode=crm.getString("userCode", "");
    	String newCardType=crm.getString("cardType", "");
    	String wweek=crm.getString("wweek", "");

    	//输入财政周转为工作周
    	wweek=WeekFormatUtil.getFormatWeek("f",wweek);
    	//
    	
    	BdPeriod curBdPeriod=bdPeriodManager.getBdPeriodByTime(new Date(), null);
		String bdWeek= curBdPeriod.getWyear()+""+ (curBdPeriod.getWweek()<10?"0" + curBdPeriod.getWweek():curBdPeriod.getWweek());
    	request.setAttribute("wweek", bdWeek);
    	
    	if(StringUtil.isEmpty(userCode) || StringUtil.isEmpty(newCardType)){
    		return new ModelAndView("mi/miMemberChangeCardType","userCode",userCode);
    	}else{
    		if(!"0".equals(newCardType) && !"1".equals(newCardType) && !"2".equals(newCardType) && !"3".equals(newCardType) && !"4".equals(newCardType) && !"5".equals(newCardType) && !"6".equals(newCardType)){
    			return new ModelAndView("mi/miMemberChangeCardType");
    		}
    	}
    	
    	JmiMember miMember = jmiMemberManager.getJmiMember(userCode);

//    	if(1==miMember.getBeforeFreezeStatus()){//会员冻结，不能转移
//    		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("bdsendrecord.sendlateremark.17"));
//    		return new ModelAndView("mi/miMemberChangeCardType","userCode",userCode);
//    	}
    	
    	if(miMember==null || (!"AA".equals(defSysUser.getCompanyCode()) && !miMember.getCompanyCode().equals(defSysUser.getCompanyCode())) ){
    		saveMessage(request,LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(),"miMember.notFound"));
    	}else{
    		BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(wweek);
    		if(bdPeriod==null||bdPeriod.getArchivingStatus()==1){
        		saveMessage(request,LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(),"bd.send.Archiving"));
        		return new ModelAndView("mi/miMemberChangeCardType","userCode",userCode);
    		}
			//添加会员备注 --20110503
			String miRemark=request.getParameter("miRemark");
			if(!StringUtil.isEmpty(miRemark)){
				String enBr="";
				if(!StringUtil.isEmpty(miMember.getRemark())){
					enBr="\n";
				}
				miMember.setRemark((miMember.getRemark()==null?"":miMember.getRemark())+enBr+miRemark);
			}
			//
			
			
    		jmiMemberManager.changeMiMemberCardType(newCardType,miMember,StringUtil.formatInt(wweek),defSysUser);
    		saveMessage(request,LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(),"miMember.updated"));
    	}
    	return new ModelAndView("mi/miMemberChangeCardType","userCode",userCode);
    }
}
