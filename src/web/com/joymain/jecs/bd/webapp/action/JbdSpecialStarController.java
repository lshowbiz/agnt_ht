package com.joymain.jecs.bd.webapp.action;

import java.util.List;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.JbdSpecialStar;
import com.joymain.jecs.bd.service.JbdSpecialStarManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdSpecialStarController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdSpecialStarController.class);
    private JbdSpecialStarManager jbdSpecialStarManager = null;

    public void setJbdSpecialStarManager(JbdSpecialStarManager jbdSpecialStarManager) {
        this.jbdSpecialStarManager = jbdSpecialStarManager;
    }
    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        String companyCode="";
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        if("C".equals(defSysUser.getUserType())){
        	companyCode=defSysUser.getCompanyCode();  
    		if("AA".equals(defSysUser.getCompanyCode())){
    			companyCode = request.getParameter("company");
    		}
    	}

        CommonRecord crm=RequestUtil.toCommonRecord(request);

        crm.addField("companyCode", companyCode);
        
//        if("xls".equals(request.getParameter("jbdSpecialStarListTable_ev"))){
//        	crm.addField("passStar", "passStar");
//        }
        String type=request.getParameter("type");
        String team_code=request.getParameter("team_code");
        String s_fyear=request.getParameter("s_fyear");
        List jbdSpecialStars = null;

        Pager pager = new Pager("jbdSpecialStarListTable",request, 20);
        if("detail".equals(type)){
        	List jbdCubMemberList=jdbcTemplate.queryForList("Select a.*,b.mi_user_name From Jbd_Cub_Member_List a,jmi_member b  Where a.user_code=b.user_code and  a.team_code = '"+team_code+"' And a.w_month Like '"+s_fyear+"%'");
        	request.setAttribute("jbdCubMemberList", jbdCubMemberList);
        }else{
            jbdSpecialStars = jbdSpecialStarManager.getJbdSpecialStarsByCrm(crm,pager);
            request.setAttribute("jbdSpecialStarListTable_totalRows", pager.getTotalObjects());
        	
        }
        
        /*****/

        return new ModelAndView("bd/jbdSpecialStarList", "jbdSpecialStarList", jbdSpecialStars);
    }
}
