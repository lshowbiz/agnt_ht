package com.joymain.jecs.mi.webapp.action;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.mi.model.JmiLinkRef;
import com.joymain.jecs.mi.service.JmiLinkRefManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.MiUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class MiLinkRefController implements Controller {
    private final Log log = LogFactory.getLog(MiLinkRefController.class);
    private JmiLinkRefManager jmiLinkRefManagerReport = null;

    private JdbcTemplate jdbcTemplate = null;
	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public void setJmiLinkRefManagerReport(JmiLinkRefManager jmiLinkRefManagerReport) {
		this.jmiLinkRefManagerReport = jmiLinkRefManagerReport;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
    	
    	//================AiAgent LOGIN IMFORMATION
    	SysUser defSysUser = SessionLogin.getLoginUser(request);

    	if("M".equals(defSysUser.getUserType())){
    		RequestUtil.freshSession(request,"miLinkRefs", Constants.MEMBER_TIME);
	  	}else if("C".equals(defSysUser.getUserType())){
    		RequestUtil.freshSession(request,"miLinkRefs", 10L);
	  	}
	    	
    	String userCode = "";
    	if("M".equals(defSysUser.getUserType())){
    		userCode = defSysUser.getUserCode();
    	}
    	//=========================================
        long layerId = 0;
		String defLayerID = ConfigUtil.getConfigValue(defSysUser.getCompanyCode().toUpperCase(), "select.link.ref");
		String limitNum = ConfigUtil.getConfigValue(defSysUser.getCompanyCode().toUpperCase(), "select.link.ref.limit");
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
        		if(RequestUtil.isMobileRequest(request)){
            		return new ModelAndView("mobile/mi/miLinkRefList").addObject("memberNo", userCode);
                }else{
            		return new ModelAndView("mi/miLinkRefList").addObject("memberNo", userCode);
                }
        	}
        }

		String layerIdNum=request.getParameter("layerId");
		if("C".equals(defSysUser.getUserType()) && StringUtil.isInteger(layerIdNum) && StringUtil.formatLong(layerIdNum)>StringUtil.formatLong(limitNum)){
			MessageUtil.saveLocaleMessage(request, "查询代数过多，只能查询"+limitNum+"代");
			 return new ModelAndView("redirect:miLinkRefs.html");
		}
		
		String isSubmit = crmMemberNo.getString("memberNo", "");
		if (isSubmit==null){
    		if(RequestUtil.isMobileRequest(request)){
        		return new ModelAndView("mobile/mi/miLinkRefList").addObject("memberNo", userCode);
            }else{
    			return new ModelAndView("mi/miLinkRefList").addObject("memberNo", userCode);
            }
		}else{


	    	if("M".equals(defSysUser.getUserType())){
	        	  if(RequestUtil.saveOperationSession(request,"miLinkRefs", Constants.MEMBER_TIME)!=0){
	        		  return new ModelAndView("redirect:miLinkRefs.html"); 
	        	  }
	    	}else if("C".equals(defSysUser.getUserType())){
	    		  if(RequestUtil.saveOperationSession(request, "miLinkRefs", 10L)!=0){
	        		  return new ModelAndView("redirect:miLinkRefs.html");
	        	  }
	    	}
	    	
			 
			CommonRecord crm=new CommonRecord();
			//分页获取数据
			JmiLinkRef miLinkRef = jmiLinkRefManagerReport.getJmiLinkRef(crmMemberNo.getString("memberNo", ""));
			MiUtil.setCardType(miLinkRef.getJmiMember());
			if(miLinkRef != null){
				
				if(!"C".equals(defSysUser.getUserType())){
					String treeIndex = miLinkRef.getTreeIndex();
					/* 当用户类型为管理中心时，不需判断目标查询会员是否为当前用户下线，否则需进行判断 */
					if (!"C".equals(defSysUser.getUserType())) {					
						JmiLinkRef miLinkRefTree = jmiLinkRefManagerReport.getJmiLinkRef(userCode);
						String agentIndex = miLinkRefTree.getTreeIndex();
						if (treeIndex.length() < agentIndex.length()) {
				    		if(RequestUtil.isMobileRequest(request)){
								return new ModelAndView("mobile/mi/miLinkRefList", "miLinkRefList", new JmiLinkRef());
				            }else{
								return new ModelAndView("mi/miLinkRefList", "miLinkRefList", new JmiLinkRef());
				            }
						}
						String memberIndexTmp = StringUtil.getLeft(treeIndex, agentIndex.length());
						if (!agentIndex.equals(memberIndexTmp)) {//不为代理商的下级组织
				    		if(RequestUtil.isMobileRequest(request)){
								return new ModelAndView("mobile/mi/miLinkRefList", "miLinkRefList", new JmiLinkRef());
				            }else{
								return new ModelAndView("mi/miLinkRefList", "miLinkRefList", new JmiLinkRef());
				            }
						}
						//TODO:viewSomeData
						if(!flag&&!defSysUser.getUserCode().equals(crmMemberNo.getString("memberNo", ""))){
				    		if(RequestUtil.isMobileRequest(request)){
								return new ModelAndView("mobile/mi/miLinkRefList", "miLinkRefList", new JmiLinkRef());
				            }else{
								return new ModelAndView("mi/miLinkRefList", "miLinkRefList", new JmiLinkRef());
				            }
						}
					}else{
						if(!"AA".equals(defSysUser.getCompanyCode())){
							if(!miLinkRef.getJmiMember().getCompanyCode().equals(defSysUser.getCompanyCode())){
					    		if(RequestUtil.isMobileRequest(request)){
									return new ModelAndView("mobile/mi/miLinkRefList", "miLinkRefList", new JmiLinkRef());
					            }else{
									return new ModelAndView("mi/miLinkRefList", "miLinkRefList", new JmiLinkRef());
					            }
							}
						}
					}
					crm.addField("treeIndexs",treeIndex);
					crm.addField("layerIds",Long.toString(layerId));
					List miLinkRefs = new ArrayList();
					miLinkRefs.add(miLinkRef);
					if(crm.getString("layerIds","").equals("1")){
						miLinkRefs = jmiLinkRefManagerReport.getLinkRefbyLinkNo(miLinkRef.getUserCode());
						miLinkRefs.add(0,miLinkRef);
					}else{
						getMiLinkRefsTree(miLinkRefs,miLinkRef,Integer.parseInt(crm.getString("layerIds","")),0,false,defSysUser.getCompanyCode());
					}
					miLinkRefs = this.getSytToRe(miLinkRefs);
					request.setAttribute("miLinkRefForm", miLinkRef);
					request.setAttribute("miMemberCount", miLinkRefs.size());
		    		if(RequestUtil.isMobileRequest(request)){
						return new ModelAndView("mobile/mi/miLinkRefList", "miLinkRefList", miLinkRefs);
		            }else{
						return new ModelAndView("mi/miLinkRefList", "miLinkRefList", miLinkRefs);
		            }
				}else{
					boolean isCheck = false;
					if(!"AA".equals(defSysUser.getCompanyCode())){
						if(!miLinkRef.getJmiMember().getCompanyCode().equals(defSysUser.getCompanyCode())){
				    		if(RequestUtil.isMobileRequest(request)){
								return new ModelAndView("mobile/mi/miLinkRefList", "miLinkRefList", new JmiLinkRef());
				            }else{
								return new ModelAndView("mi/miLinkRefList", "miLinkRefList", new JmiLinkRef());
				            }
						}
						isCheck = true;
					}
					List miLinkRefs = new ArrayList();
					miLinkRefs.add(miLinkRef);

			        try{
			        	getMiLinkRefsTree(miLinkRefs,miLinkRef,Integer.parseInt(crmMemberNo.getString("layerId","")),0,isCheck,defSysUser.getCompanyCode());
			        }catch(NumberFormatException e){
			        	
			        }
			        miLinkRefs = this.getSytToRe(miLinkRefs);
					request.setAttribute("miLinkRefForm", miLinkRef);
//					request.setAttribute("poMemberOrderManager", poMemberOrderManager);
					request.setAttribute("miMemberCount", miLinkRefs.size());
		    		if(RequestUtil.isMobileRequest(request)){
						return new ModelAndView("mobile/mi/miLinkRefList", "miLinkRefList", miLinkRefs);
		            }else{
						return new ModelAndView("mi/miLinkRefList", "miLinkRefList", miLinkRefs);
		            }
				}
				
				
			}else{
	    		if(RequestUtil.isMobileRequest(request)){
					return new ModelAndView("mobile/mi/miLinkRefList").addObject("memberNo", userCode);
	            }else{
					return new ModelAndView("mi/miLinkRefList").addObject("memberNo", userCode);
	            }
			}
		}
    }
	
	public List getSytToRe(List miLinkRefs){
		List tempMiLinkRefs = new ArrayList();
		JmiLinkRef miLinkRef = null;
		String uame = "";
		String rname = "";
		String sname = "";
		List list = null;
		if(!MeteorUtil.isBlankByList(miLinkRefs)){
			for (int i=0;i<miLinkRefs.size();i++){
				miLinkRef = (JmiLinkRef)miLinkRefs.get(i);
				String sql = "select b.user_name,b.rank_name,b.syt_name from JMI_MEMBER_SYT_LIST b where user_code ='"+miLinkRef.getUserCode()+"' ";
				log.info("sql === "+sql);
				list = this.jdbcTemplate.queryForList(sql);
				if(!MeteorUtil.isBlankByList(list)){
					for (int z=0;z<list.size();z++) {
						Map map = (Map)list.get(z);
						uame = (String)map.get("user_name");
						rname = (String)map.get("rank_name");
						sname = (String)map.get("syt_name");
						
						miLinkRef.setReUserName(uame);
						miLinkRef.setRankName(rname);
						miLinkRef.setSytName(sname);
					}
				}
				tempMiLinkRefs.add(miLinkRef);
			}
		}
		return tempMiLinkRefs;
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
    	List miLinkRefTmps = jmiLinkRefManagerReport.getLinkRefbyLinkNo(miLinkRef.getJmiMember().getUserCode());
    	if(maxLevel==0){
    		level = -1;
    	}else{
        	level++;
    	}
    	if(miLinkRefTmps!=null && level <= maxLevel){
    		for(int i=0;i<miLinkRefTmps.size();i++){
    			JmiLinkRef miLinkRefTmp = (JmiLinkRef) miLinkRefTmps.get(i);
    			MiUtil.setCardType(miLinkRefTmp.getJmiMember());
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
