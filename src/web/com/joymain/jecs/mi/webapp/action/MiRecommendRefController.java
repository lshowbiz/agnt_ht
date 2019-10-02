package com.joymain.jecs.mi.webapp.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.mi.model.JmiRecommendRef;
import com.joymain.jecs.mi.service.JmiRecommendRefManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.MiUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;


public class MiRecommendRefController implements Controller {
	private final Log log = LogFactory.getLog(MiRecommendRefController.class);

	private JmiRecommendRefManager jmiRecommendRefManagerReport = null;
	private JdbcTemplate jdbcTemplate = null;
	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public void setJmiRecommendRefManagerReport(
			JmiRecommendRefManager jmiRecommendRefManagerReport) {
		this.jmiRecommendRefManagerReport = jmiRecommendRefManagerReport;
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
		Long time=0L;
    	if("M".equals(defSysUser.getUserType())){
    		time=Constants.MEMBER_TIME;
	  	}else if("C".equals(defSysUser.getUserType())){
    		time=10L;
	  	}
		RequestUtil.freshSession(request,"miRecommendRefs", time);
		
		long layerId = 0;
		String defLayerID = ConfigUtil.getConfigValue(defSysUser.getCompanyCode().toUpperCase(), "select.recommend.ref");
		String limitNum = ConfigUtil.getConfigValue(defSysUser.getCompanyCode().toUpperCase(), "select.recommend.ref.limit");
		//String userCodeStr=ConfigUtil.getConfigValue(defSysUser.getCompanyCode().toUpperCase(), "memberno.unlimit.user");
    	boolean unlimitUserFlag=false;
    	Set valueSets = Constants.sysListMap.get("memberno.unlimit.user").entrySet();
    	if (valueSets != null) {
			Iterator iter = valueSets.iterator();
			while (iter.hasNext()) {
				Map.Entry entry=(Map.Entry)iter.next();
				String curUserCode=(String) entry.getKey();
				String loginUserCode=defSysUser.getUserCode();
				if(curUserCode.equals(loginUserCode)){
					unlimitUserFlag=true;
				}
				
			}
		}
		boolean flag=false;
		if("M".equals(defSysUser.getUserType()) && unlimitUserFlag){
			flag=true;
			defLayerID="5";
    	}
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
                if(RequestUtil.isMobileRequest(request)){
                	return new ModelAndView("mobile/mi/miRecommendRefList").addObject("memberNo", userCode);
                }else{
                	return new ModelAndView("mi/miRecommendRefList").addObject("memberNo", userCode);
                }
        		
        	}
			
		}
		String layerIdNum=request.getParameter("layerId");
		if("C".equals(defSysUser.getUserType()) && StringUtil.isInteger(layerIdNum) && StringUtil.formatLong(layerIdNum)>StringUtil.formatLong(limitNum)){
			MessageUtil.saveLocaleMessage(request, "查询代数过多，只能查询"+limitNum+"代");
			 return new ModelAndView("redirect:miRecommendRefs.html");
		}
		
		
		String isSubmit = crmMemberNo.getString("memberNo", "");
		if (isSubmit == null) {
            if(RequestUtil.isMobileRequest(request)){
    			return new ModelAndView("mobile/mi/miRecommendRefList").addObject("memberNo", userCode);
            }else{
    			return new ModelAndView("mi/miRecommendRefList").addObject("memberNo", userCode);
            }
		} else {
			
	    	if("M".equals(defSysUser.getUserType())){
	        	  if(RequestUtil.saveOperationSession(request,"miRecommendRefs", Constants.MEMBER_TIME)!=0){
	        		  return new ModelAndView("redirect:miRecommendRefs.html"); 
	        	  }
	    	}else if("C".equals(defSysUser.getUserType())){
	    		  if(RequestUtil.saveOperationSession(request, "miRecommendRefs", 10L)!=0){
	        		  return new ModelAndView("redirect:miRecommendRefs.html");
	        	  }
	    	}
			
			
			CommonRecord crm = new CommonRecord();
			// 分页获取数据
			JmiRecommendRef miRecommendRef = jmiRecommendRefManagerReport.getJmiRecommendRef(crmMemberNo.getString("memberNo", ""));

			MiUtil.setCardType(miRecommendRef.getJmiMember());
			if (miRecommendRef != null) {

				if(!"C".equals(defSysUser.getUserType())){
					String treeIndex = miRecommendRef.getTreeIndex();

					/* 当用户类型为管理中心时，不需判断目标查询会员是否为当前用户下线，否则需进行判断 */
					if (!"C".equals(defSysUser.getUserType())) {
						JmiRecommendRef miRecommendRefTree = jmiRecommendRefManagerReport.getJmiRecommendRef(userCode);
						String memberIndex = miRecommendRefTree.getTreeIndex();
						if (treeIndex.length() < memberIndex.length()) {
				            if(RequestUtil.isMobileRequest(request)){
								return new ModelAndView("mobile/mi/miRecommendRefList", "miRecommendRefList", new JmiRecommendRef());
				            }else{
								return new ModelAndView("mi/miRecommendRefList", "miRecommendRefList", new JmiRecommendRef());
				            }
						}
						String memberIndexTmp = StringUtil.getLeft(treeIndex, memberIndex.length());
						if (!memberIndex.equals(memberIndexTmp)) {// 不为会员的下级组织
				            if(RequestUtil.isMobileRequest(request)){
								return new ModelAndView("mobile/mi/miRecommendRefList", "miRecommendRefList", new JmiRecommendRef());
				            }else{
								return new ModelAndView("mi/miRecommendRefList", "miRecommendRefList", new JmiRecommendRef());
				            }
						}	
						//TODO:viewSomeData
						if(!flag&&!defSysUser.getUserCode().equals(crmMemberNo.getString("memberNo", ""))){
				            if(RequestUtil.isMobileRequest(request)){
								return new ModelAndView("mobile/mi/miRecommendRefList", "miRecommendRefList", new JmiRecommendRef());
				            }else{
								return new ModelAndView("mi/miRecommendRefList", "miRecommendRefList", new JmiRecommendRef());
				            }
						}
					}else if(!"AA".equals(defSysUser.getCompanyCode())){
						if(!miRecommendRef.getJmiMember().getCompanyCode().equals(defSysUser.getCompanyCode())){//与当前管理员不为同一分公司
				            if(RequestUtil.isMobileRequest(request)){
								return new ModelAndView("mobile/mi/miRecommendRefList", "miRecommendRefList", new JmiRecommendRef());
				            }else{
								return new ModelAndView("mi/miRecommendRefList", "miRecommendRefList", new JmiRecommendRef());
				            }
						}
					}
					crm.addField("treeIndexs", treeIndex);
					crm.addField("layerIds", Long.toString(layerId));
					
					List miRecommendRefs = new ArrayList();
					miRecommendRefs.add(miRecommendRef);
					if(crm.getString("layerIds","").equals("1")){
						miRecommendRefs = jmiRecommendRefManagerReport.getRecommendRefbyRecommendNo(miRecommendRef.getUserCode());
						miRecommendRefs.add(0,miRecommendRef);
					}else{
						getMiRecommendRefsTree(miRecommendRefs,miRecommendRef,Integer.parseInt(crm.getString("layerIds","")),0,false,defSysUser.getCompanyCode());
					}
					
					miRecommendRefs = this.getSytToRe(miRecommendRefs);
					
					request.setAttribute("miRecommendRefForm", miRecommendRef);
					
					request.setAttribute("miMemberCount", miRecommendRefs.size());
		            if(RequestUtil.isMobileRequest(request)){
						return new ModelAndView("mobile/mi/miRecommendRefList", "miRecommendRefList", miRecommendRefs);
		            }else{
						return new ModelAndView("mi/miRecommendRefList", "miRecommendRefList", miRecommendRefs);
		            }
				}else{
					boolean isCheck = false;
					if(!"AA".equals(defSysUser.getCompanyCode())){
						if(!miRecommendRef.getJmiMember().getCompanyCode().equals(defSysUser.getCompanyCode())){
				            if(RequestUtil.isMobileRequest(request)){
								return new ModelAndView("mobile/mi/miRecommendRefList", "miRecommendRefList", new JmiRecommendRef());
				            }else{
								return new ModelAndView("mi/miRecommendRefList", "miRecommendRefList", new JmiRecommendRef());
				            }
						}
						isCheck = true;
					}
					List miRecommendRefs = new ArrayList();
					miRecommendRefs.add(miRecommendRef);
					try{
						getMiRecommendRefsTree(miRecommendRefs,miRecommendRef,Integer.parseInt(crmMemberNo.getString("layerId","")),0,isCheck,defSysUser.getCompanyCode());
					}catch(NumberFormatException e){
						
					}
					miRecommendRefs = this.getSytToRe(miRecommendRefs);
					
					request.setAttribute("miRecommendRefForm", miRecommendRef);
//					request.setAttribute("poMemberOrderManager", poMemberOrderManager);
					request.setAttribute("miMemberCount", miRecommendRefs.size());
		            if(RequestUtil.isMobileRequest(request)){
						return new ModelAndView("mobile/mi/miRecommendRefList", "miRecommendRefList", miRecommendRefs);
		            }else{
						return new ModelAndView("mi/miRecommendRefList", "miRecommendRefList", miRecommendRefs);
		            }
				}
			} else {
	            if(RequestUtil.isMobileRequest(request)){
					return new ModelAndView("mobile/mi/miRecommendRefList").addObject("memberNo", userCode);
	            }else{
					return new ModelAndView("mi/miRecommendRefList").addObject("memberNo", userCode);	
	            }
			}
		}
	}
	
	public List getSytToRe(List miRecommendRefs){
		List tempMiRecommendRefs = new ArrayList();
		JmiRecommendRef tempMiRecommendRef = null;
		String uame = "";
		String rname = "";
		String sname = "";
		List list = null;
		if(!MeteorUtil.isBlankByList(miRecommendRefs)){
			for (int i=0;i<miRecommendRefs.size();i++){
				tempMiRecommendRef = (JmiRecommendRef)miRecommendRefs.get(i);
				String sql = "select b.user_name,b.rank_name,b.syt_name from JMI_MEMBER_SYT_LIST b where user_code ='"+tempMiRecommendRef.getUserCode()+"' ";
				log.info("sql === "+sql);
				list = this.jdbcTemplate.queryForList(sql);
				if(!MeteorUtil.isBlankByList(list)){
					for (int z=0;z<list.size();z++) {
						Map map = (Map)list.get(z);
						uame = (String)map.get("user_name");
						rname = (String)map.get("rank_name");
						sname = (String)map.get("syt_name");
						
						tempMiRecommendRef.setReUserName(uame);
						tempMiRecommendRef.setRankName(rname);
						tempMiRecommendRef.setSytName(sname);
					}
				}
				tempMiRecommendRefs.add(tempMiRecommendRef);
			}
		}
		return tempMiRecommendRefs;
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
    	List miRecommendRefTmps = jmiRecommendRefManagerReport.getRecommendRefbyRecommendNo(miRecommendRef.getJmiMember().getUserCode());
    	if(maxLevel==0){
    		level = -1;
    	}else{
        	level++;
    	}
    	if(miRecommendRefTmps!=null && level <= maxLevel){
    		for(int i=0;i<miRecommendRefTmps.size();i++){
    			JmiRecommendRef miRecommendRefTmp = (JmiRecommendRef) miRecommendRefTmps.get(i);
    			MiUtil.setCardType(miRecommendRefTmp.getJmiMember());
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
