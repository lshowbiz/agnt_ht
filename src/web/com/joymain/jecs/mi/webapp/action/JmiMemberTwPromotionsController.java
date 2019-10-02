package com.joymain.jecs.mi.webapp.action;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysLoginLog;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysLoginLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiMemberTwPromotionsController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiMemberTwPromotionsController.class);
    private JmiMemberManager jmiMemberManager = null;
	private SysLoginLogManager sysLoginLogManager;
	private BdPeriodManager bdPeriodManager;
	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public void setSysLoginLogManager(SysLoginLogManager sysLoginLogManager) {
		this.sysLoginLogManager = sysLoginLogManager;
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
        
        String strAction=request.getParameter("strAction");
        
        
        

		List<String> messages = new ArrayList<String>();

    	BdPeriod bdPeriod =bdPeriodManager.getNextWeek(bdPeriodManager.getLatestSendBonus());
        if("jmiMemberTwPromotionUpGrade".equals(strAction)){
        	
        	String bdWeek= bdPeriod.getWyear()+""+ (bdPeriod.getWweek()<10?"0" + bdPeriod.getWweek():bdPeriod.getWweek());
        	

			//获得会员级别列表
			Map cardTypeMap=ListUtil.getListOptions("TW", "bd.cardtype");
        	
        	List list=jmiMemberManager.getTWPromotions(bdPeriod.getEndTime());
        	for (int i = 0; i < list.size(); i++) {
				Map map=(Map) list.get(i);
        		String user_code=(String) map.get("user_code");
        		String nCardType=(String) map.get("nCardType");
        		
        		JmiMember miMember=jmiMemberManager.getJmiMember(user_code);
        		
        		String curCardType=miMember.getCardType();
        		
        		
        		if( StringUtil.formatInt(nCardType) > StringUtil.formatInt(miMember.getCardType()) ){
        			
        			jmiMemberManager.changeMiMemberCardType(nCardType,miMember,StringUtil.formatInt(bdWeek),defSysUser);

        			
        			String messageStr=MessageFormat.format(LocaleUtil.getLocalText("JmiMemberTwPromotions.update"), 
        					new String[]{user_code,LocaleUtil.getLocalText(cardTypeMap.get(curCardType).toString()),LocaleUtil.getLocalText(cardTypeMap.get(nCardType).toString())});
        			messages.add(messageStr);
        		}
        		
        		
        		
        		
			}
        }
        
        

		request.setAttribute("messages", messages);
        

        return new ModelAndView("mi/jmiMemberTwPromotions", "jmiMemberTwPromotions", jmiMemberManager.getTWPromotions(bdPeriod.getEndTime()));
    }

}
