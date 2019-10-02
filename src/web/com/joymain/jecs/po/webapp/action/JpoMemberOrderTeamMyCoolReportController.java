package com.joymain.jecs.po.webapp.action;

import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.model.JbdMemberLinkCalcHist;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiLinkRefManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;

/**
 * MYCOOL查询单个ID的每天新增业绩、累计业绩
 * @author Alvin
 *
 */
public class JpoMemberOrderTeamMyCoolReportController implements Controller {
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

		if ("post".equalsIgnoreCase(request.getMethod()) && "jpoMemberOrderTeamMyCoolReport".equalsIgnoreCase(request.getParameter("strAction"))) {		

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
				type="link";
				relationType="jmi_link_ref";
			}else if("2".equals(type)){
				type="recommend";
				relationType="jmi_recommend_ref";
				
			}
			
			String treeIndex="";
			if("link".equals(type)){
				treeIndex=jmiMember.getJmiLinkRef().getTreeIndex();
			}else if("recommend".equals(type)){
				treeIndex=jmiMember.getJmiRecommendRef().getTreeIndex();
			}
			
			String endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			String sql="select nvl(Sum(amount),0) amount from jpo_member_order o where o.user_code in " +
					"(select user_code from "+relationType+" t where t.tree_index like '"+treeIndex+"%') " +
					"and  check_date >=To_Date('"+endDate+" 00:00:00', 'yyyy-mm-dd hh24:mi:ss') " +
					"And Check_Date <= To_Date('"+endDate+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss') And Status = '2' ";
			List today = this.jdbcTemplate.queryForList(sql);
			request.setAttribute("today", ((Map)today.get(0)).get("amount"));

			String sql1="select nvl(Sum(amount),0) amount from jpo_member_order o where o.user_code in " +
					"(select user_code from "+relationType+" t where t.tree_index like '"+treeIndex+"%') " +
					"and  check_date >=To_Date('2010-2-6 00:00:00', 'yyyy-mm-dd hh24:mi:ss') " +
					"And Check_Date < To_Date('"+endDate+" 00:00:00', 'yyyy-mm-dd hh24:mi:ss') And Status = '2' ";
			List yesterday = this.jdbcTemplate.queryForList(sql1);
			request.setAttribute("yesterday", ((Map)yesterday.get(0)).get("amount"));
			
		}

		return new ModelAndView("po/jpoMemberOrderTeamMyCoolReport");
	}



}