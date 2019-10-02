package com.joymain.jecs.mi.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.mi.model.JmiAssure;
import com.joymain.jecs.mi.service.JmiAssureManager;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

public class JmiAssureController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiAssureController.class);
    private JmiAssureManager jmiAssureManager = null;
    private JdbcTemplate jdbcTemplate = null;
	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}   

    public void setJmiAssureManager(JmiAssureManager jmiAssureManager) {
        this.jmiAssureManager = jmiAssureManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        String strAction = request.getParameter("strAction");
        if ("deleteJmiAssure".equals(strAction)) {
			String ids = request.getParameter("jmiAssureIds");
			if(!MeteorUtil.isBlank(ids)){
				String [] s =  ids.split(",");
				for(String id :s){
					jmiAssureManager.removeJmiAssure(Long.valueOf(id));
				}
				String key = "删除成功";
				this.saveMessage(request, key);
			}
		}else if ("syncJmiAssure".equals(strAction)) {
			String ids = request.getParameter("jmiAssureIds");
			JmiAssure o = null;
			String stTime = "";
			String edTime = "";
			if(!MeteorUtil.isBlank(ids)){
				String [] s =  ids.split(",");
				for(String id :s){
					o = jmiAssureManager.getJmiAssure(Long.valueOf(id));
					if(null!=o){
						//业绩担保才同步，其他的不管，均为手工维护
						if("2".equals(o.getAssureType())){
							
							if(null!=o.getStartTime()){
								stTime = MeteorUtil.doDateToConvert(o.getStartTime());
							}
							if(null!=o.getEndTime()){
								edTime = MeteorUtil.doDateToConvert(o.getEndTime());
							}
							 		  
							StringBuffer sb2 = new StringBuffer(" select max(pass_star) as maxstar from jbd_member_link_calc_hist where w_week in( ");
							sb2.append(" select w_year||Lpad(w_week, 2, 0) from jbd_period where 1=1 ");
							if(!MeteorUtil.isBlank(stTime)){
								sb2.append(" and end_TIME >= to_date('"+stTime+"', 'yyyy-mm-dd hh24:mi:ss') ");
							}
							if(!MeteorUtil.isBlank(edTime)){
								sb2.append(" and start_TIME <= to_date('"+edTime+"', 'yyyy-mm-dd hh24:mi:ss') ");
							}
							sb2.append(")  and user_code='"+o.getUserCode()+"' ");
							
							List<Map<String,Object>>  list2 = this.jdbcTemplate.queryForList(sb2.toString());
							if(null!=list2 && list2.size()>0){
								for(Map<String,Object> map2 : list2){
									String maxstar = String.valueOf(map2.get("maxstar"));
									if(null==maxstar || "null"==maxstar){
										maxstar = "0";
									}
									if(Long.valueOf(o.getAssureContent())<=Long.valueOf(maxstar)){
										o.setIsFlag("1");
									}else{
										o.setIsFlag("2");
									}
								}
								jmiAssureManager.saveJmiAssure(o);
							}
						}
					}
				}
				String key = "同步成功";
				this.saveMessage(request, key);
			}
		}

	//List jmiAssures = jmiAssureManager.getJmiAssures(jmiAssure);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jmiAssureListTable",request, 20);
        List jmiAssures = jmiAssureManager.getJmiAssuresByCrm(crm,pager);
        request.setAttribute("jmiAssureListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("mi/jmiAssureList", "jmiAssureList", jmiAssures);
    }
}
