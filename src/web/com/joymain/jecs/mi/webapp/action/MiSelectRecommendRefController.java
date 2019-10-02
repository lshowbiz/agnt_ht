package com.joymain.jecs.mi.webapp.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.bd.service.JbdMemberLinkCalcHistManager;
import com.joymain.jecs.mi.model.JmiLinkRef;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiRecommendRef;
import com.joymain.jecs.mi.service.JmiLinkRefManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiRecommendRefManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.util.SessionLogin;


public class MiSelectRecommendRefController implements Controller {
    private final Log log = LogFactory.getLog(MiSelectRecommendRefController.class);
    private JmiMemberManager jmiMemberManager = null;
    private JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager;
    private JmiRecommendRefManager jmiRecommendRefManager;
    private JmiLinkRefManager jmiLinkRefManager;
    private BdPeriodManager bdPeriodManager;
    
	public void setJbdMemberLinkCalcHistManager(
			JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager) {
		this.jbdMemberLinkCalcHistManager = jbdMemberLinkCalcHistManager;
	}

	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public void setJmiLinkRefManager(JmiLinkRefManager jmiLinkRefManager) {
		this.jmiLinkRefManager = jmiLinkRefManager;
	}

	public void setJmiRecommendRefManager(
			JmiRecommendRefManager jmiRecommendRefManager) {
		this.jmiRecommendRefManager = jmiRecommendRefManager;
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

    	//================AiAgent LOGIN IMFORMATION
    	SysUser defSysUser = SessionLogin.getLoginUser(request);
    	//=========================================
    	
    	String memberNo = request.getParameter("memberNo");
    	
    	if (StringUtil.isEmpty(memberNo)){//会员员为空
	    	return new ModelAndView("mi/miSelectRecommendRef");
    	}
    	
    	JmiMember jmiMember = jmiMemberManager.getJmiMember(memberNo);
    	if (jmiMember ==null){//会员不存在
    		return new ModelAndView("mi/miSelectRecommendRef");
    	}

        JmiRecommendRef miRecommendRef = jmiRecommendRefManager.getJmiRecommendRef(jmiMember.getJmiRecommendRef().getUserCode());
        
        
		JmiRecommendRef defRecommendRef = jmiRecommendRefManager.getJmiRecommendRef(defSysUser.getUserCode());

		if("M".equals(defSysUser.getUserType())){

			//判断填的推荐人是否在当前会员下
			String rdefIndex=defRecommendRef.getTreeIndex();
			String rIndex=miRecommendRef.getTreeIndex();
			if(rIndex.length()<rdefIndex.length()){
				return new ModelAndView("mi/miSelectRecommendRef");
			}
			String rmemberIndexTmp = StringUtil.getLeft(rIndex, rdefIndex.length());
			if(!rdefIndex.equals(rmemberIndexTmp)){//不为会员的下级组织
				return new ModelAndView("mi/miSelectRecommendRef");
			}
		}
		
		
		
    	
    	JmiLinkRef miLinkRef = jmiLinkRefManager.getJmiLinkRef(memberNo);
    	Map miLinkRefMap =null;
		if("TW".equals(defSysUser.getCompanyCode())||"PH".equals(defSysUser.getCompanyCode())){
	    	miLinkRefMap = fillInMapTw(miLinkRef);
		}else{
	    	miLinkRefMap = fillInMap(miLinkRef);
		}
    	
    	CommonRecord crm=new CommonRecord();
    	crm.addField("linkNo", miLinkRef.getUserCode());
    	crm.addField("m_no", request.getParameter("m_no"));
    	if("TW".equals(defSysUser.getCompanyCode())||"PH".equals(defSysUser.getCompanyCode())){
        	crm.addField("departmentPv", "departmentPv");
		}
		List miLinkRefs = jmiLinkRefManager.getMiLinkRefManagersByTree(crm);
		List miLinkRefMaps = new ArrayList();
		for (int i = 0; i < miLinkRefs.size(); i++) {
			JmiLinkRef miLinkRefTmp = (JmiLinkRef)miLinkRefs.get(i);

			if("TW".equals(defSysUser.getCompanyCode())||"PH".equals(defSysUser.getCompanyCode())){
				miLinkRefMaps.add(fillInMapTw(miLinkRefTmp));
			}else{
				miLinkRefMaps.add(fillInMap(miLinkRefTmp));
			}
			
			
		}
		
		if("post".equalsIgnoreCase(request.getMethod())){
			
			if("TW".equals(defSysUser.getCompanyCode())||"PH".equals(defSysUser.getCompanyCode())||"US".equals(defSysUser.getCompanyCode())){
				String curWeek=bdPeriodManager.getLastDayBonus();
				miLinkRefMaps = fillInMapbyPostTw(miLinkRefMaps,crm.getString("m_no", ""),curWeek);
			}else{
				miLinkRefMaps = fillInMapbyPost(miLinkRefMaps,crm.getString("m_no", ""));
			}
		}
		request.setAttribute("miLinkRefForm", miLinkRefMap);
    	return new ModelAndView("mi/miSelectRecommendRef","miLinkRefList",miLinkRefMaps);
    }
    private List fillInMapbyPost(List miLinkRefs,String memberNo){
    	
    	for(int i=0;i<miLinkRefs.size();i++){
    		Map mapTmp = (Map)miLinkRefs.get(i);
    		JmiLinkRef miLinkRefTmp = (JmiLinkRef)mapTmp.get("miLinkRef");
    		if(memberNo.equals(miLinkRefTmp.getUserCode())){
    			List tmpList = null;

    				tmpList = jmiLinkRefManager.getLinkRefbyLinkNoOrderByCreateTime(memberNo);
    				
    			if(tmpList.size()==0){
    				mapTmp.put("memberNo", miLinkRefTmp);
    			}else{
    				JmiLinkRef miLinkRefTmp1 = (JmiLinkRef)tmpList.get(0);

        				tmpList = jmiLinkRefManager.getLinkRefbyLinkNoOrderByCreateTime(miLinkRefTmp1.getUserCode());
        			while(tmpList.size()>0){
        				miLinkRefTmp1 = (JmiLinkRef)tmpList.get(0);
            			tmpList = jmiLinkRefManager.getLinkRefbyLinkNoOrderByCreateTime(miLinkRefTmp1.getUserCode());
        			}
            		mapTmp.put("memberNo", miLinkRefTmp1);
    			}
    		}
    	}
    	return miLinkRefs;
    }
    

    private List fillInMapbyPostTw(List miLinkRefs,String memberNo,String curWeek){
    	
    	for(int i=0;i<miLinkRefs.size();i++){
    		Map mapTmp = (Map)miLinkRefs.get(i);
    		JmiLinkRef miLinkRefTmp = (JmiLinkRef)mapTmp.get("miLinkRef");
    		if(memberNo.equals(miLinkRefTmp.getUserCode())){
    			List tmpList = null;

    			JmiLinkRef miLinkRefTmp1=null;

        				tmpList = jmiLinkRefManager.getLinkRefbyLinkNoByWeekGroupPv(memberNo,curWeek);
        			if(tmpList.size()==0){
        				mapTmp.put("memberNo", miLinkRefTmp);
        			}else{
            			while(tmpList.size()>0){
               				miLinkRefTmp1 = (JmiLinkRef)tmpList.get(0);
            				if(tmpList.size()>=2){
                				miLinkRefTmp1 = (JmiLinkRef)tmpList.get(1);
                    			tmpList = jmiLinkRefManager.getLinkRefbyLinkNoByWeekGroupPv(miLinkRefTmp1.getUserCode(),curWeek);
            				}else{
            					break;
            				}
            			}
                		mapTmp.put("memberNo", miLinkRefTmp1);
        			}
    			
    		}
    	}
    	return miLinkRefs;
    }
    
    
    private Map fillInMap(JmiLinkRef miLinkRef){
    	BdPeriod bdPeriod = bdPeriodManager.getLatestSendBonus();
		String bdWeek= bdPeriod.getWyear()+""+ (bdPeriod.getWweek()<10?"0" + bdPeriod.getWweek():bdPeriod.getWweek());
    	Map newMap = new HashMap();
    	Map memberGPV = jbdMemberLinkCalcHistManager.getBonusRecordbyUserCode(miLinkRef.getUserCode(),bdWeek);
    	newMap.put("miLinkRef", miLinkRef);
    	newMap.put("gpv", memberGPV.get("pv"));
    	return newMap;
    }
    private Map fillInMapTw(JmiLinkRef miLinkRef){
    	Map newMap = new HashMap();
    	newMap.put("miLinkRef", miLinkRef);
    	newMap.put("gpv", miLinkRef.getDepartmentPv());
    	return newMap;
    }
}
