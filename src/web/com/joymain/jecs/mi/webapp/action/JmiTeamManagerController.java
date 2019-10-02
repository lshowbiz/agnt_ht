package com.joymain.jecs.mi.webapp.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.io.OutputStream;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiStore;
import com.joymain.jecs.mi.model.JmiSubStore;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiStoreManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JmiTeamManagerController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiTeamManagerController.class);

    private JdbcTemplate jdbcTemplate;
    private JdbcTemplate jdbcTemplateReport;
    private JmiMemberManager jmiMemberManager;
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplateReport) {
		this.jdbcTemplateReport = jdbcTemplateReport;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

    	SysUser defSysUser=SessionLogin.getLoginUser(request);
    	
        CommonRecord crm=RequestUtil.toCommonRecord(request);

        String userCodeStr=request.getParameter("userCodeStr");

        String executeType=request.getParameter("executeType");
       
        if(userCodeStr==null){
        	 return new ModelAndView("mi/jmiTeamManager");
        }
       
        if("addTeam".equals(executeType)){

            String leaderCode=request.getParameter("leaderCode");
            String memberTypeStr=request.getParameter("memberTypeStr");
            String memberTypeView=request.getParameter("memberTypeView");
            this.addTeamType(leaderCode, memberTypeStr, memberTypeView);
            this.saveMessage(request, "添加成功");
            return new ModelAndView("mi/jmiTeamManager");
        	
        }
        List list=jdbcTemplateReport.queryForList("select * from jmi_team_type");
        Map teamMap=new HashMap();
        for (int i = 0; i < list.size(); i++) {
			Map map=(Map) list.get(i);
			
			teamMap.put(map.get("user_code"), map.get("member_type"));
		}
        if(!StringUtil.isEmpty(userCodeStr)){
        	String str[]=userCodeStr.split("\r\n");
        	 for (int i = 0; i < str.length; i++) {
     			Map map=new HashMap();
     			map.put("user_code", str[i]);
     			map.put("member_type", "");
     			list.add(map);
     			teamMap.put(str[i], "");
     		}
        }
        
       
        for (int i = 0; i < list.size(); i++) {
        	Map map=(Map) list.get(i);
        	String userCode=map.get("user_code").toString();
        	

    		Map teamLeaderMap=jdbcTemplateReport.queryForMap("select * from jmi_member where user_code='"+userCode+"'");
        	
    		String recommendNo=teamLeaderMap.get("recommend_no").toString();
    		if(teamLeaderMap!=null && teamMap.get(recommendNo)==null){
    			while (!"8888888888".equals(recommendNo) ) {
           		 List reslist=jdbcTemplateReport.queryForList("select * from jmi_member where user_code='"+recommendNo+"'");
           		 if(!reslist.isEmpty()){
           			 Map resMap=(Map) reslist.get(0);
           			 recommendNo=resMap.get("recommend_no").toString();
           			 //System.out.println(recommendNo);
           			 if(teamMap.get(recommendNo)!=null){
           				 break;
           			 }
           		 }
    			}
    		}
    		
        	
        	
        	teamMap.put(userCode, recommendNo);
        	//System.out.println(userCode+":"+recommendNo);

            //teamList.add(teamMap);
        	
		}
        

        List teamList=new ArrayList();
        Iterator it=teamMap.entrySet().iterator();
        while (it.hasNext()) {
			Entry entry=(Entry) it.next();
			String key=entry.getKey().toString();
			String val=entry.getValue().toString();
			
			Map teamLeaderMap=jdbcTemplateReport.queryForMap("select * from jmi_member where user_code='"+key+"'");
			
			
			Map map=new HashMap();
			map.put("key", key);
			map.put("parent", val);
			map.put("member_type", ListUtil.getListValue(defSysUser.getDefCharacterCoding(), "membertype", teamLeaderMap.get("member_type")==null?"0":teamLeaderMap.get("member_type").toString()));
			map.put("mi_user_name", teamLeaderMap.get("mi_user_name"));
			teamList.add(map);
		}

        Map topMap=new HashMap();
        topMap.put("key", "8888888888");
        topMap.put("member_type", "");
        topMap.put("mi_user_name", "8888888888");
        teamList.add(topMap);
        
        JSONObject jsonObject=new JSONObject();
        jsonObject.element("class", "go.TreeModel");
        
        JSONArray jsonArray=JSONArray.fromObject(teamList);
        
        jsonObject.element("nodeDataArray", jsonArray);
        request.setAttribute("teamList", jsonObject.toString());
        return new ModelAndView("mi/jmiTeamManager");
    }
    
	private void addTeamType(String userCode,String memberType,String teamTitle){
		//查询是否包含团队

		Map jmi_recommend_ref_map=jdbcTemplateReport.queryForMap("select * from jmi_recommend_ref where user_code='"+userCode+"'");
		String tree_index=(String) jmi_recommend_ref_map.get("tree_index");

  		List containList=jdbcTemplateReport.queryForList("select user_code from  jmi_team_type where user_code in (select user_code from jmi_recommend_ref  where tree_index like '"+tree_index+"%')");
		
  		String updatesql="update jmi_member t set t.member_type = '"+memberType+"' where t.user_code in (select user_code from jmi_recommend_ref r "
  				+ "where r.tree_index like '"+tree_index+"%') ";
		
		for (int i = 0; i < containList.size(); i++) {
			Map map=(Map) containList.get(i);
			String cur_user_code=(String) map.get("user_code");
			updatesql+=" and  t.user_code not in (select user_code from jmi_recommend_ref r "
					+ "where r.tree_index like  (select tree_index from jmi_recommend_ref where user_code='"+cur_user_code+"') ||   '%') ";
		}
		jdbcTemplate.update("insert into  jmi_team_type values ('"+userCode+"','"+memberType+"')");
		jdbcTemplate.update(updatesql);
		
		if(!StringUtil.isEmpty(teamTitle)){
			jdbcTemplate.update("insert into am_permit values ('"+userCode+"','"+teamTitle+"','5')");
		}
		
		
	}

}
