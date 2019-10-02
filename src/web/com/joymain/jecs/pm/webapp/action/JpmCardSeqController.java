package com.joymain.jecs.pm.webapp.action;

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
import com.joymain.jecs.pm.model.JpmCardSeq;
import com.joymain.jecs.pm.service.JpmCardSeqManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpmCardSeqController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpmCardSeqController.class);
    private JpmCardSeqManager jpmCardSeqManager = null;

    public void setJpmCardSeqManager(JpmCardSeqManager jpmCardSeqManager) {
        this.jpmCardSeqManager = jpmCardSeqManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        SysUser defSysUser = SessionLogin.getLoginUser(request);
        
        JpmCardSeq jpmCardSeq = new JpmCardSeq();
        BeanUtils.populate(jpmCardSeq, request.getParameterMap());

        CommonRecord crm=RequestUtil.toCommonRecord(request);
        
		crm.addField("userCode", defSysUser.getUserCode());
		crm.addField("state", "1");
		crm.addField("createBTime", "2013-1-19");
		crm.addField("createETime", "2013-3-1");
		
        Pager pager = new Pager("jpmCardSeqListTable",request, 20);
        List jpmCardSeqs = jpmCardSeqManager.getJpmCardSeqsByCrm(crm,pager);
        request.setAttribute("jpmCardSeqListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("pm/jpmCardSeqList", Constants.JPMCARDSEQ_LIST, jpmCardSeqs);
    }
}
