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
import com.joymain.jecs.mi.service.JmiLinkRefManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class MiLinkRefMController implements Controller {
    private final Log log = LogFactory.getLog(MiLinkRefMController.class);
    private JmiLinkRefManager jmiLinkRefManager = null;


	public void setJmiLinkRefManager(JmiLinkRefManager jmiLinkRefManager) {
		this.jmiLinkRefManager = jmiLinkRefManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
    	//================AiAgent LOGIN IMFORMATION
    	SysUser defSysUser = SessionLogin.getLoginUser(request);
    	String userCode = "";
    	if("M".equals(defSysUser.getUserType())){
    		userCode = defSysUser.getUserCode();
    	}
    	//=========================================
        
        long layerId = 0;
		String defLayerID = ConfigUtil.getConfigValue(defSysUser.getCompanyCode().toUpperCase(), "select.link.ref.m");

		List layerList = new ArrayList();
		for (int i =1 ; i <= Integer.parseInt(defLayerID) && i <= 50 ; i++ ){
			layerList.add(i);
		}
		request.setAttribute("layerList", layerList);
        CommonRecord crmMemberNo = RequestUtil.toCommonRecord(request);
        String layerIdStr = crmMemberNo.getString("layerId","");
        try{
	        if(StringUtil.isInteger(layerIdStr)){
	        	layerId = Long.parseLong(layerIdStr);
	        	if(layerId>Long.parseLong(defLayerID)){
	        		layerId = Long.parseLong(defLayerID);
	        	}
	        	if(layerId>50){//无论如何，代数不能超过50
	        		layerId = 50;
	        	}
	        }
        }catch(NumberFormatException e){
        	layerIdStr = "0";
        	crmMemberNo.setValue("layerId", 0);
        }
		
        if(StringUtils.isEmpty(crmMemberNo.getString("memberNo", ""))){
        	if("M".equals(defSysUser.getUserType())){
        		crmMemberNo.addField("memberNo", defSysUser.getUserCode());
        		layerId = 1;
        		request.setAttribute("memberNo", userCode);
        	}else{
        		request.setAttribute("layerId", 1);
         		return new ModelAndView("mi/miLinkRefMList").addObject("memberNo", userCode);
        	}
        }
        
		String isSubmit = crmMemberNo.getString("memberNo", "");
		//判断是否在CN18303490这个会员下
		JmiLinkRef topMiLinkRef =jmiLinkRefManager.getJmiLinkRef("CN18303490");
		JmiLinkRef curMiLinkRef =jmiLinkRefManager.getJmiLinkRef(isSubmit);
		String topIndex=topMiLinkRef.getTreeIndex();
		String curIndex=curMiLinkRef.getTreeIndex();
		String miLinkRefIndexTmp = StringUtil.getLeft(curIndex, topIndex.length());
		//
		if (isSubmit==null||curIndex.length()<topIndex.length()||!topIndex.equals(miLinkRefIndexTmp)){
			return new ModelAndView("mi/miLinkRefMList").addObject("memberNo", userCode);
		}else{
			CommonRecord crm=new CommonRecord();
			//分页获取数据
			JmiLinkRef miLinkRef = jmiLinkRefManager.getJmiLinkRef(crmMemberNo.getString("memberNo", ""));
			if(miLinkRef != null){
				
				if(!"C".equals(defSysUser.getUserType())){
					String treeIndex = miLinkRef.getTreeIndex();
					/* 当用户类型为管理中心时，不需判断目标查询会员是否为当前用户下线，否则需进行判断 */
					if (!"C".equals(defSysUser.getUserType())) {					
						JmiLinkRef miLinkRefTree = jmiLinkRefManager.getJmiLinkRef(userCode);
						String agentIndex = miLinkRefTree.getTreeIndex();
						if (treeIndex.length() < agentIndex.length()) {
							return new ModelAndView("mi/miLinkRefMList", "miLinkRefList", new JmiLinkRef());
						}
						String memberIndexTmp = StringUtil.getLeft(treeIndex, agentIndex.length());
						if (!agentIndex.equals(memberIndexTmp)) {//不为代理商的下级组织
							return new ModelAndView("mi/miLinkRefMList", "miLinkRefList", new JmiLinkRef());
						}
					}else{
						if(!"AA".equals(defSysUser.getCompanyCode())){
							if(!miLinkRef.getJmiMember().getCompanyCode().equals(defSysUser.getCompanyCode())){
								return new ModelAndView("mi/miLinkRefMList", "miLinkRefList", new JmiLinkRef());
							}
						}
					}
					crm.addField("treeIndexs",treeIndex);
					crm.addField("layerIds",Long.toString(layerId));
					List miLinkRefs = new ArrayList();
					miLinkRefs.add(miLinkRef);
					if(crm.getString("layerIds","").equals("1")){
						miLinkRefs = jmiLinkRefManager.getLinkRefbyLinkNo(miLinkRef.getUserCode());
						miLinkRefs.add(0,miLinkRef);
					}else{
						getMiLinkRefsTree(miLinkRefs,miLinkRef,Integer.parseInt(crm.getString("layerIds","")),0,false,defSysUser.getCompanyCode());
					}
					request.setAttribute("miLinkRefForm", miLinkRef);
					request.setAttribute("miMemberCount", miLinkRefs.size());
					return new ModelAndView("mi/miLinkRefMList", "miLinkRefList", miLinkRefs);
				}else{
					boolean isCheck = false;
					if(!"AA".equals(defSysUser.getCompanyCode())){
						if(!miLinkRef.getJmiMember().getCompanyCode().equals(defSysUser.getCompanyCode())){
							return new ModelAndView("mi/miLinkRefMList", "miLinkRefList", new JmiLinkRef());
						}
						isCheck = true;
					}
					List miLinkRefs = new ArrayList();
					miLinkRefs.add(miLinkRef);

			        try{
			        	getMiLinkRefsTree(miLinkRefs,miLinkRef,Integer.parseInt(crmMemberNo.getString("layerId","")),0,isCheck,defSysUser.getCompanyCode());
			        }catch(NumberFormatException e){
			        	
			        }
					request.setAttribute("miLinkRefForm", miLinkRef);
//					request.setAttribute("poMemberOrderManager", poMemberOrderManager);
					request.setAttribute("miMemberCount", miLinkRefs.size());
					return new ModelAndView("mi/miLinkRefMList", "miLinkRefList", miLinkRefs);
				}
				
				
			}else{
				return new ModelAndView("mi/miLinkRefMList").addObject("memberNo", userCode);
			}
		}
    }

    /**
     * 递归查询会员接点组织
     * @param miLinkRefs
     * @param miLinkRef
     * @param maxLevel
     * @param level
     * @param isCheck
     * @param companyCode
     */
    
    private void getMiLinkRefsTree(final List miLinkRefs,final JmiLinkRef miLinkRef, final int maxLevel, int level, boolean isCheck, String companyCode){
    	List miLinkRefTmps = jmiLinkRefManager.getLinkRefbyLinkNo(miLinkRef.getJmiMember().getUserCode());
    	if(maxLevel==0){
    		level = -1;
    	}else{
        	level++;
    	}
    	if(miLinkRefTmps!=null && level <= maxLevel){
    		for(int i=0;i<miLinkRefTmps.size();i++){
    			JmiLinkRef miLinkRefTmp = (JmiLinkRef) miLinkRefTmps.get(i);
    			if(isCheck){
        			if(companyCode.equals(miLinkRefTmp.getJmiMember().getCompanyCode())){
            			miLinkRefs.add(miLinkRefTmp);
            			getMiLinkRefsTree(miLinkRefs,miLinkRefTmp, maxLevel, level, isCheck , companyCode);
            			
        			}else{
        				
        				miLinkRefTmp.setUserCode("haiwaixian");
            			miLinkRefs.add(miLinkRefTmp);
            			getMiLinkRefsTree(miLinkRefs,miLinkRefTmp, maxLevel, level, isCheck, companyCode);
        			}
    			}else{
        			miLinkRefs.add(miLinkRefTmp);
        			getMiLinkRefsTree(miLinkRefs,miLinkRefTmp, maxLevel, level, isCheck,companyCode);
    			}
    		}
    	}
    }

}
