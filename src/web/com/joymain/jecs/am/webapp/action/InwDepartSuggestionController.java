package com.joymain.jecs.am.webapp.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.am.service.InwDepartmentManager;
import com.joymain.jecs.am.service.InwSuggestionManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class InwDepartSuggestionController extends BaseController implements Controller {
   
    private InwDepartmentManager inwDepartmentManager;
    
    private InwSuggestionManager inwSuggestionManager;

    public void setInwDepartmentManager(InwDepartmentManager inwDepartmentManager) {
		this.inwDepartmentManager = inwDepartmentManager;
	}

	public void setInwSuggestionManager(InwSuggestionManager inwSuggestionManager) {
		this.inwSuggestionManager = inwSuggestionManager;
	}

    /**
     * 根据部门ID查询部门权限下的建议-------公共方法(未完,待需求确认过后再做完)
     * @author gw 2014-05-30
     * @param request
     * @param response
     */
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        
        //首先根据部门名称去表inw_department表中查询获得部门名称所对应的ID(这个过程在数据库客户端执行)
        //然后更加ID查询出该部门的权限下的建议
		//------------------------待需求确认下来之后,这个地方再补上
        //--------------------------------------对应动态调整的部门,那个字段暂时不加上去-----未添加,后续补上
        //--------------------------------------(表inw_suggesion加一个字段department_id)---未添加,后续补上
        String departmentId = request.getParameter("id");
        CommonRecord crm = RequestUtil.toCommonRecord(request);
        crm.setValue("departmentId", departmentId);
        String departmentIdList = inwDepartmentManager.getInwDepartmentIdListById(departmentId);
        //根据部门的权限查询该部门所能看到的建议
        Pager pager = new Pager("inwSuggestionsListTable",request,20);
        crm.setValue("departmentIdList", departmentIdList);
        //这个地方需求暂时不明确----------------------------------------因此这个地方先暂时不做
        //-----------------这个建议是仅仅给指定的部门看,还是将建议分配给指定的部门,指定的部门针对这些建议做出回复呢?
        //List inwSuggestions = inwSuggestionManager.getInwSuggestionListByCrm(crm);
        List inwSuggestions = new ArrayList();
        request.setAttribute("inwSuggestionsListTable_totalRows", pager.getTotalObjects());
        return new ModelAndView("am/inwSuggestionForDepart", "inwSuggestionsList", inwSuggestions);
    }
    
}
