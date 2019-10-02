package com.joymain.jecs.mi.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.model.JbdMemberLinkCalcHist;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiLinkRefManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.webapp.util.MessageUtil;

/**
 * MYCOOL查询ID分布情况：银卡人数、金卡人数、白金卡人数、钻石卡人数
 * @author Alvin
 *
 */
public class JmiMemberCheckMyCoolReportController implements Controller {
	private transient final Log log = LogFactory.getLog(JbdMemberLinkCalcHist.class);
    private JmiMemberManager jmiMemberManager;
    private JmiLinkRefManager jmiLinkRefManager;
    
	public void setJmiLinkRefManager(JmiLinkRefManager jmiLinkRefManager) {
		this.jmiLinkRefManager = jmiLinkRefManager;
	}


	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	private JdbcTemplate jdbcTemplate = null;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		if ("post".equalsIgnoreCase(request.getMethod()) && "jmiMemberCheckMyCoolReport".equalsIgnoreCase(request.getParameter("strAction"))) {		

			String userCode = request.getParameter("userCode");
			JmiMember jmiMember=jmiMemberManager.getJmiMember(userCode);
			if(jmiMember==null){
				MessageUtil.saveLocaleMessage(request, "miMember.notFound");
				return new ModelAndView("po/jpoMemberOrderTeamMyCoolReport");
			}
			
			boolean isMyCool = jmiLinkRefManager.getJmiLinkRefIsM(userCode);
			if(isMyCool==false){
				MessageUtil.saveLocaleMessage(request, "miMember.notFound");
				return new ModelAndView("po/jpoMemberOrderTeamMyCoolReport");
			}
			
			String type = request.getParameter("type");
			String relationType="";
			if("1".equals(type)){
				relationType="jmi_link_ref";
			}else if("2".equals(type)){
				relationType="jmi_recommend_ref";
			}
			
			String treeIndex="";
			if("1".equals(type)){
				treeIndex=jmiMember.getJmiLinkRef().getTreeIndex();
			}else if("2".equals(type)){
				treeIndex=jmiMember.getJmiRecommendRef().getTreeIndex();
			}
			
			String sql="Select card_type,Count(card_type)counts From jmi_member jm," + relationType + " jlr Where jm.user_code = jlr.user_code  And jlr.tree_index Like '"+treeIndex+"%' Group By card_type Order By Card_Type Asc";
			List memberCardType = this.jdbcTemplate.queryForList(sql);
			request.setAttribute("memberCardType", memberCardType);
		}

		return new ModelAndView("mi/jmiMemberCheckMyCoolReport");
	}



}