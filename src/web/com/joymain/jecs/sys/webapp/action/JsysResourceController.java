package com.joymain.jecs.sys.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.model.JsysResource;
import com.joymain.jecs.sys.model.SysModule;
import com.joymain.jecs.sys.service.JsysResourceManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JsysResourceController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JsysResourceController.class);
    private JsysResourceManager jsysResourceManager = null;

    public void setJsysResourceManager(JsysResourceManager jsysResourceManager) {
        this.jsysResourceManager = jsysResourceManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        String parentId=request.getParameter("parentId");
        JsysResource parentResource = null;
		if(!StringUtils.isEmpty(parentId)){
			parentResource = this.jsysResourceManager.getJsysResource(parentId);
		}
		if(parentResource==null){
			parentResource=new JsysResource();
			parentResource.setResName("node.root.name");
		}
		request.setAttribute("parentSysResource", parentResource);


		
        //JsysResource jsysResource = new JsysResource();
        // populate object with request parameters
        //BeanUtils.populate(jsysResource, request.getParameterMap());

		//*List jsysResources = jsysResourceManager.getJsysResources(jsysResource);
		/**** auto pagination ***/
		CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jsysResourceListTable",request, 50);
        List jsysResources = jsysResourceManager.getJsysResourcesByCrm(crm,pager);
        request.setAttribute("jsysResourceListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("sys/jsysResourceList", Constants.JSYSRESOURCE_LIST, jsysResources);
    }
}
