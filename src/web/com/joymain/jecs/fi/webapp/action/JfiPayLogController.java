package com.joymain.jecs.fi.webapp.action;

import java.util.List;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.JfiPayLog;
import com.joymain.jecs.fi.service.JfiPayLogManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiPayLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiPayLogController.class);
    private JfiPayLogManager jfiPayLogManager = null;

    public void setJfiPayLogManager(JfiPayLogManager jfiPayLogManager) {
        this.jfiPayLogManager = jfiPayLogManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JfiPayLog jfiPayLog = new JfiPayLog();
        // populate object with request parameters
        BeanUtils.populate(jfiPayLog, request.getParameterMap());

		//List jfiPayLogs = jfiPayLogManager.getJfiPayLogs(jfiPayLog);
		/**** auto pagination ***/
		CommonRecord crm=RequestUtil.toCommonRecord(request);
		/** add by hdg 2016-12-23 */
	    /*CustomField[] fields = crm.getFields();
		w:for(CustomField field : fields) {
			String name=field.getName();
			if(!StringUtils.isEmpty(name)) {
				if("endcreatetime".equals(name)) {
					String value = (String)field.getValue();
					if(!StringUtils.isEmpty(value)) {
						field.setValue(value+" 23:59:59");
					}
					break w;
				}
			}
		}*/
		 /** add by hdg 2016-12-23 */
        Pager pager = new Pager("jfiPayLogListTable",request, 20);
        List jfiPayLogs = jfiPayLogManager.getJfiPayLogsByCrm(crm,pager);
        request.setAttribute("jfiPayLogListTable_totalRows", pager.getTotalObjects());
        /*****/
        /** ----------------- -start -------------------------
         * add by hdg 20160726 
         * 描述：导出excel的时候，数字类型的数据对应不上，所以加上前缀处理
         * 点击导出xls按钮的时候，才进入到这个方法
         */
        if("xls".equals(request.getParameter("jfiPayLogListTable_ev"))){
        	
        	for(int i=0; i<jfiPayLogs.size(); i++) {
            	JfiPayLog payLog = (JfiPayLog)jfiPayLogs.get(i);
            	String dealId = payLog.getDealId();
            	if(!StringUtils.isEmpty(dealId) && !"null".equals(dealId) ) {
            		payLog.setDealId("支付平台处理号 "+payLog.getDealId());
            	}
            }
        }
    	/** ------------------- end ----------------------*/
        return new ModelAndView("fi/jfiPayLogList", Constants.JFIPAYLOG_LIST, jfiPayLogs);
    }
}
