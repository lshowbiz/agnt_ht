package com.joymain.jecs.sys.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

public class JsysStockAccountController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JsysStockAccountController.class);
    
    public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response)throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
		CommonRecord crm = RequestUtil.toCommonRecord(request);
       
        Pager pager = new Pager("jsysStockAccountListTable",request, 20);
        
        List<Map<String,Object>> list = this.getStockAccountList(request, pager, crm);
        
        request.setAttribute("jsysStockAccountListTable_totalRows", pager.getTotalObjects());
        
        return new ModelAndView("sys/jsysStockAccountList", "jsysStockAccountList", list);
    }
    
    /**
     * 获取港股账号信息列表
     * @param request
     * @param pager
     * @param crm
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Map<String,Object>> getStockAccountList(HttpServletRequest request,Pager pager,CommonRecord crm){
    	StringBuffer sbSql = new StringBuffer();
    	/*sbSql.append("select s.create_date, s.update_date, s.user_code,s.user_name,");
    	sbSql.append("s.stock_account,s.fee_status,m.papernumber,m.mobiletele ");
    	sbSql.append(" from jsys_stock_account s left join jmi_member m on s.user_code=m.user_code where 1=1 ");*/
    	
    	sbSql.append("select s.id,s.user_code,s.user_name,s.stock_account,s.fee_status");
    	sbSql.append(" from jsys_stock_account s where 1=1 ");
    	
    	if(!StringUtil.isEmpty(crm.getString("userCode"))){
    		sbSql.append(" and s.user_code like ").append("'%").append(crm.getString("userCode")).append("%'");
    	}
    	if(!StringUtil.isEmpty(crm.getString("userName"))){
    		sbSql.append(" and s.user_name like ").append("'%").append(crm.getString("userName")).append("%'");
    	}
    	
    	sbSql.append(" order by s.id ");
    	
    	ServletContext context = request.getSession().getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		BaseDaoHibernate dao = (BaseDaoHibernate) ctx.getBean("dao");
		
		List<Map<String,Object>> list = dao.findObjectsBySQL(sbSql.toString(), pager);
    	return list;
    }
}
