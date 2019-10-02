package com.joymain.jecs.am.webapp.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.am.model.InwDemandsort;
import com.joymain.jecs.am.service.InwDemandsortManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * 创新共赢-需求分类--新的需求
 * @author gw  2013-11-12 
 *
 */
public class InwDemandsortController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(InwDemandsortController.class);
    private InwDemandsortManager inwDemandsortManager = null;

    public void setInwDemandsortManager(InwDemandsortManager inwDemandsortManager) {
        this.inwDemandsortManager = inwDemandsortManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        InwDemandsort inwDemandsort = new InwDemandsort();
        //这句代码是什么意思?????用来将一些 key-value 的值（例如 hashmap）映射到 bean 中的属性。
        BeanUtils.populate(inwDemandsort, request.getParameterMap());
	    CommonRecord crm=RequestUtil.toCommonRecord(request);
	    String strAction = request.getParameter("strAction");
	    //详细查询
	    if("queryDetailInwDemandsort".equals(strAction)){
	         String id = request.getParameter("id");
	         InwDemandsort inwDemandsorts = inwDemandsortManager.getInwDemandsort(id);
	         request.setAttribute("inwDemandsort", inwDemandsorts);
	         SysUser loginSysUser = SessionLogin.getLoginUser(request);
	         //这个路径是固定机器的某个盘符的一个路径
	 		//String FILE_URL = ListUtil.getListValue(loginSysUser.getCompanyCode().toUpperCase(), "jpmproductsaleimage.url", "1");
	         //读取图片的路径----这个路径是本机tomcat下的一个路径
	         String FILE_URL = ConfigUtil.getConfigValue(loginSysUser.getCompanyCode().toUpperCase(),"column.image.demandurl");
	 		 request.setAttribute("FILE_URL", FILE_URL);
	    	 return new ModelAndView("am/inwDemandsortQueryDetail","","");
	    }
	    //一般查询
	    else{
		    //获取标题的名称
		    String name = request.getParameter("name");
		    crm.setValue("name", name);
	        Pager pager = new Pager("inwDemandsortListTable",request, 20);
	        List inwDemandsorts = inwDemandsortManager.getInwDemandsortsByCrm(crm,pager);
	        request.setAttribute("inwDemandsortListTable_totalRows", pager.getTotalObjects());
	        return new ModelAndView("am/inwDemandsortList", "inwDemandsortList", inwDemandsorts);
	    }
    }
}
