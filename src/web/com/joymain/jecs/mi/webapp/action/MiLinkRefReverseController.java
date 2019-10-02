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

import com.joymain.jecs.mi.model.JmiLinkRef;
import com.joymain.jecs.mi.service.JmiLinkRefManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.MiUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class MiLinkRefReverseController implements Controller {
	private final Log log = LogFactory.getLog(MiLinkRefReverseController.class);
	private JmiLinkRefManager jmiLinkRefManagerReport;

	private JdbcTemplate jdbcTemplate = null;
	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public void setJmiLinkRefManagerReport(JmiLinkRefManager jmiLinkRefManagerReport) {
		this.jmiLinkRefManagerReport = jmiLinkRefManagerReport;
	}


	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("GET".equals(request.getMethod())) {
			return new ModelAndView("mi/miLinkRefReverseList");
		}

        SysUser defSysUser = SessionLogin.getLoginUser(request);
        RequestUtil.freshSession(request,"reverse", 10L);
		CommonRecord crmMemberNo = RequestUtil.toCommonRecord(request);

		/* 会员编号为空或会员不存在返回输入页面 */
		if (StringUtil.isEmpty(crmMemberNo.getString("memberNo", ""))) {
			MessageUtil.saveLocaleMessage(request, "operation.notice.js.orderNo.miMember.memberNoError");
			return new ModelAndView("mi/miLinkRefReverseList");
		}

		JmiLinkRef miLinkRef = jmiLinkRefManagerReport.getJmiLinkRef(crmMemberNo.getString("memberNo", ""));
		
		if (miLinkRef == null) {
			return new ModelAndView("mi/miLinkRefReverseList");
		} else {
			if(!"AA".equals(defSysUser.getCompanyCode())){
				if(!miLinkRef.getJmiMember().getCompanyCode().equals(defSysUser.getCompanyCode())){
					return new ModelAndView("mi/miLinkRefReverseList");
				}
			}
			request.setAttribute("miLinkRefForm", miLinkRef);

	 
			  if(RequestUtil.saveOperationSession(request, "reverse", 10L)!=0){
	    		  return new ModelAndView("redirect:miLinkRefReverse.html?strAction=reverse");
	    	  }
			
			List<JmiLinkRef>miLinkRefListTmp = new ArrayList<JmiLinkRef>();
			miLinkRefListTmp.add(miLinkRef);
			String userCode = miLinkRef.getUserCode();
			String linkNo = miLinkRef.getLinkJmiMember().getUserCode();
			while(!"8888888888".equals(userCode)){
				JmiLinkRef miLinkRefTmp = jmiLinkRefManagerReport.getJmiLinkRef(linkNo);
				if(miLinkRefTmp!=null){
					miLinkRefListTmp.add(miLinkRefTmp);
					userCode = miLinkRefTmp.getUserCode();
					linkNo = miLinkRefTmp.getLinkJmiMember().getUserCode();
				}else{
					break;
				}
			}
			List<JmiLinkRef> miLinkRefList = new ArrayList<JmiLinkRef>();
			for (int i=miLinkRefListTmp.size()-1 ; i >=0 ; i--){
				MiUtil.setCardType(miLinkRefListTmp.get(i).getJmiMember());
				miLinkRefList.add(miLinkRefListTmp.get(i));
			}
			miLinkRefList = this.getSytToRe(miLinkRefList);
			return new ModelAndView("mi/miLinkRefReverseList", "miLinkRefList", miLinkRefList);
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
}
