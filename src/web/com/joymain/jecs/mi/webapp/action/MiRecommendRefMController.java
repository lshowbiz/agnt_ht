package com.joymain.jecs.mi.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.mi.model.JmiLinkRef;
import com.joymain.jecs.mi.model.JmiRecommendRef;
import com.joymain.jecs.mi.service.JmiRecommendRefManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.SessionLogin;


public class MiRecommendRefMController implements Controller {
	private final Log log = LogFactory.getLog(MiRecommendRefMController.class);

	private JmiRecommendRefManager jmiRecommendRefManager = null;



	public void setJmiRecommendRefManager(
			JmiRecommendRefManager jmiRecommendRefManager) {
		this.jmiRecommendRefManager = jmiRecommendRefManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		// ================MiMember LOGIN IMFORMATION
		SysUser defSysUser = SessionLogin.getLoginUser(request);
		String userCode = "";
		if ("M".equals(defSysUser.getUserType())) {
			userCode = defSysUser.getUserCode();
		}
		// =========================================

		long layerId = 0;
		String defLayerID = ConfigUtil.getConfigValue(defSysUser.getCompanyCode().toUpperCase(), "select.recommend.ref.m");

		List layerList = new ArrayList();
		for (int i =1 ; i <= Integer.parseInt(defLayerID) && i <= 50  ; i++ ){
			layerList.add(i);
		}
		CommonRecord crmMemberNo = RequestUtil.toCommonRecord(request);
		request.setAttribute("layerList", layerList);
		String layerIdStr = crmMemberNo.getString("layerId","");
		try{
			if (StringUtil.isInteger(layerIdStr)) {
				layerId = Long.parseLong(layerIdStr);
				
				if (layerId > Long.parseLong(defLayerID)) {
					layerId = Long.parseLong(defLayerID);
				}
				if (layerId > 50) {// 无论如何，代数不能超过50
					layerId = 50;
				}
			}
		}catch(NumberFormatException e){
        	layerIdStr = "0";
        	crmMemberNo.setValue("layerId", 0);
		}

		if (StringUtils.isEmpty(crmMemberNo.getString("memberNo", ""))) {
        	if("M".equals(defSysUser.getUserType())){
        		crmMemberNo.addField("memberNo", defSysUser.getUserCode());
        		layerId = 1;
        		request.setAttribute("memberNo", userCode);
        	}else{
        		request.setAttribute("layerId", 1);
        		return new ModelAndView("mi/miRecommendRefMList").addObject("memberNo", userCode);
        	}
			
		}
		
		String isSubmit = crmMemberNo.getString("memberNo", "");
		//判断是否在CN18303490这个会员下
		JmiRecommendRef topMiRecommendRef =jmiRecommendRefManager.getJmiRecommendRef("CN18303490");
		JmiRecommendRef curMiRecommendRef =jmiRecommendRefManager.getJmiRecommendRef(isSubmit);
		String topIndex=topMiRecommendRef.getTreeIndex();
		String curIndex=curMiRecommendRef.getTreeIndex();
		String miRecommendRefIndexTmp = StringUtil.getLeft(curIndex, topIndex.length());
		//
		
		if (isSubmit == null||curIndex.length()<topIndex.length()||!topIndex.equals(miRecommendRefIndexTmp)) {
			return new ModelAndView("mi/miRecommendRefMList").addObject("memberNo", userCode);
		} else {
			CommonRecord crm = new CommonRecord();
			// 分页获取数据
			JmiRecommendRef miRecommendRef = jmiRecommendRefManager.getJmiRecommendRef(crmMemberNo.getString("memberNo", ""));
			if (miRecommendRef != null) {

				if(!"C".equals(defSysUser.getUserType())){
					String treeIndex = miRecommendRef.getTreeIndex();

					/* 当用户类型为管理中心时，不需判断目标查询会员是否为当前用户下线，否则需进行判断 */
					if (!"C".equals(defSysUser.getUserType())) {
						JmiRecommendRef miRecommendRefTree = jmiRecommendRefManager.getJmiRecommendRef(userCode);
						String memberIndex = miRecommendRefTree.getTreeIndex();
						if (treeIndex.length() < memberIndex.length()) {
							return new ModelAndView("mi/miRecommendRefMList", "miRecommendRefList", new JmiRecommendRef());
						}
						String memberIndexTmp = StringUtil.getLeft(treeIndex, memberIndex.length());
						if (!memberIndex.equals(memberIndexTmp)) {// 不为会员的下级组织
							return new ModelAndView("mi/miRecommendRefMList", "miRecommendRefList", new JmiRecommendRef());
						}
					}else if(!"AA".equals(defSysUser.getCompanyCode())){
						if(!miRecommendRef.getJmiMember().getCompanyCode().equals(defSysUser.getCompanyCode())){//与当前管理员不为同一分公司
							return new ModelAndView("mi/miRecommendRefMList", "miRecommendRefList", new JmiRecommendRef());
						}
					}
					crm.addField("treeIndexs", treeIndex);
					crm.addField("layerIds", Long.toString(layerId));
					
					List miRecommendRefs = new ArrayList();
					miRecommendRefs.add(miRecommendRef);
					if(crm.getString("layerIds","").equals("1")){
						miRecommendRefs = jmiRecommendRefManager.getRecommendRefbyRecommendNo(miRecommendRef.getUserCode());
						miRecommendRefs.add(0,miRecommendRef);
					}else{
						getMiRecommendRefsTree(miRecommendRefs,miRecommendRef,Integer.parseInt(crm.getString("layerIds","")),0,false,defSysUser.getCompanyCode());
					}
					
					request.setAttribute("miRecommendRefForm", miRecommendRef);
					
					request.setAttribute("miMemberCount", miRecommendRefs.size());
					return new ModelAndView("mi/miRecommendRefMList", "miRecommendRefList", miRecommendRefs);
				}else{
					boolean isCheck = false;
					if(!"AA".equals(defSysUser.getCompanyCode())){
						if(!miRecommendRef.getJmiMember().getCompanyCode().equals(defSysUser.getCompanyCode())){
							return new ModelAndView("mi/miRecommendRefMList", "miRecommendRefList", new JmiRecommendRef());
						}
						isCheck = true;
					}
					List miRecommendRefs = new ArrayList();
					miRecommendRefs.add(miRecommendRef);
					try{
						getMiRecommendRefsTree(miRecommendRefs,miRecommendRef,Integer.parseInt(crmMemberNo.getString("layerId","")),0,isCheck,defSysUser.getCompanyCode());
					}catch(NumberFormatException e){
						
					}
					request.setAttribute("miRecommendRefForm", miRecommendRef);
//					request.setAttribute("poMemberOrderManager", poMemberOrderManager);
					request.setAttribute("miMemberCount", miRecommendRefs.size());
					return new ModelAndView("mi/miRecommendRefMList", "miRecommendRefList", miRecommendRefs);
				}
			} else {
				return new ModelAndView("mi/miRecommendRefMList").addObject("memberNo", userCode);
			}
		}
	}

    /**
     * 递归查询会员接点组织
     * @param miRecommendRefs
     * @param miRecommendRef
     * @param maxLevel
     * @param level
     * @param isCheck
     * @param companyCode
     */
    
    private void getMiRecommendRefsTree(final List miRecommendRefs,final JmiRecommendRef miRecommendRef, final int maxLevel, int level, boolean isCheck, String companyCode){
    	List miRecommendRefTmps = jmiRecommendRefManager.getRecommendRefbyRecommendNo(miRecommendRef.getJmiMember().getUserCode());
    	if(maxLevel==0){
    		level = -1;
    	}else{
        	level++;
    	}
    	if(miRecommendRefTmps!=null && level <= maxLevel){
    		for(int i=0;i<miRecommendRefTmps.size();i++){
    			JmiRecommendRef miRecommendRefTmp = (JmiRecommendRef) miRecommendRefTmps.get(i);
    			if(isCheck){
        			if(companyCode.equals(miRecommendRefTmp.getJmiMember().getCompanyCode())){
            			miRecommendRefs.add(miRecommendRefTmp);
            			getMiRecommendRefsTree(miRecommendRefs,miRecommendRefTmp, maxLevel, level, isCheck,companyCode);
        			}else{
        				JmiRecommendRef miRecommendRefNo = new JmiRecommendRef();
        				miRecommendRefNo.setJmiMember(miRecommendRefTmp.getJmiMember());
        				miRecommendRefNo.getJmiMember().setSysUser(miRecommendRefTmp.getJmiMember().getSysUser());
        				miRecommendRefNo.setTreeIndex(miRecommendRefTmp.getTreeIndex());
        				miRecommendRefNo.setUserCode("haiwaixian");
        				miRecommendRefs.add(miRecommendRefNo);
        			}
    			}else{
        			miRecommendRefs.add(miRecommendRefTmp);
        			getMiRecommendRefsTree(miRecommendRefs,miRecommendRefTmp, maxLevel, level, isCheck,companyCode);
    			}
    		}
    	}
    }

}
