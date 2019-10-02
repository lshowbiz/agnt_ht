package com.joymain.jecs.mi.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.mi.model.JmiRecommendRef;
import com.joymain.jecs.mi.service.JmiRecommendRefManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.util.MiUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class MiRecommendRefReverseController implements Controller {
	private final Log log = LogFactory.getLog(MiRecommendRefReverseController.class);
	private JmiRecommendRefManager jmiRecommendRefManagerReport;

	private JdbcTemplate jdbcTemplate = null;
	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setJmiRecommendRefManagerReport(
			JmiRecommendRefManager jmiRecommendRefManagerReport) {
		this.jmiRecommendRefManagerReport = jmiRecommendRefManagerReport;
	}



	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("GET".equals(request.getMethod())) {
			return new ModelAndView("mi/miRecommendRefReverseList");
		}

        SysUser defSysUser = SessionLogin.getLoginUser(request);

        RequestUtil.freshSession(request,"miRecommendRefReverse", 10L);
        CommonRecord crmMemberNo = RequestUtil.toCommonRecord(request);

		JmiRecommendRef miRecommendRef = jmiRecommendRefManagerReport.getJmiRecommendRef(crmMemberNo.getString("memberNo", ""));
		if (miRecommendRef == null) {
			return new ModelAndView("mi/miRecommendRefReverseList");
		} else {
			if(!"AA".equals(defSysUser.getCompanyCode())){
				if(!miRecommendRef.getJmiMember().getCompanyCode().equals(defSysUser.getCompanyCode())){
					return new ModelAndView("mi/miLinkRefReverseList");
				}
			}
			request.setAttribute("miRecommendRefForm", miRecommendRef);
			 
			  if(RequestUtil.saveOperationSession(request, "miRecommendRefReverse", 10L)!=0){
	    		  return new ModelAndView("redirect:miRecommendRefReverse.html");
	    	  }
			  
			List<JmiRecommendRef> miRecommendRefListTmp = new ArrayList<JmiRecommendRef>();
			miRecommendRefListTmp.add(miRecommendRef);
			String memberNo = miRecommendRef.getUserCode();
			String linkNo = miRecommendRef.getRecommendJmiMember().getUserCode();
			while(!"8888888888".equals(memberNo)){
				JmiRecommendRef miRecommendRefTmp = jmiRecommendRefManagerReport.getJmiRecommendRef(linkNo);
				if(miRecommendRefTmp!=null){
					miRecommendRefListTmp.add(miRecommendRefTmp);
					memberNo = miRecommendRefTmp.getUserCode();
					linkNo = miRecommendRefTmp.getRecommendJmiMember().getUserCode();
				}else{
					break;
				}
			}
			List<JmiRecommendRef>miRecommendRefList = new ArrayList<JmiRecommendRef>();
			for (int i=miRecommendRefListTmp.size()-1 ; i >=0 ; i--){
				MiUtil.setCardType(miRecommendRefListTmp.get(i).getJmiMember());
				miRecommendRefList.add(miRecommendRefListTmp.get(i));
			}
			miRecommendRefList = this.getSytToRe(miRecommendRefList);
			
			return new ModelAndView("mi/miRecommendRefReverseList", "miRecommendRefList", miRecommendRefList);
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
}
