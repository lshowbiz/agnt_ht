package com.joymain.jecs.fi.webapp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.JfiBankbookTemp;
import com.joymain.jecs.fi.service.JfiBankbookTempManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.fi.model.JfiBankbookTemp;
import com.joymain.jecs.webapp.util.LocaleUtil;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * POS支付校验
 * @author shiyh
 *
 */
public class PosNumCheckController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PosNumCheckController.class);
    private JfiBankbookTempManager jfiBankbookTempManager = null;

    public void setJfiBankbookTempManager(JfiBankbookTempManager jfiBankbookTempManager) {
        this.jfiBankbookTempManager = jfiBankbookTempManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        String billpospayNum=request.getParameter("billpospayNum");
        String ischeckall=request.getParameter("ischeckall");
        
        List jfiBankbookTempList=null;
        List jfiBankbookTempList1=null;
        
        if(!StringUtils.isEmpty(ischeckall)){
        	
        	jfiBankbookTempList=jfiBankbookTempManager.checkAllPosNumFromJfiBankBookTemp();
        	request.setAttribute("jfiBankbookTempList", jfiBankbookTempList);
        	saveMessage(request, LocaleUtil.getLocalText("check.success"));
        	return new ModelAndView("fi/posNumCheckList");
        }
        if(!StringUtils.isEmpty(billpospayNum)){
	        	
	        	jfiBankbookTempList1=jfiBankbookTempManager.checkPosNum(billpospayNum);
	        	request.setAttribute("jfiBankbookTempList1", jfiBankbookTempList1);
	        	request.setAttribute("N", ischeckall);
	        }
        
        return new ModelAndView("fi/posNumCheckList");
    }
}
